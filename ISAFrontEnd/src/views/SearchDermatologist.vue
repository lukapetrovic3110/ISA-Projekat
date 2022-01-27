<template>
  <div>
    <h1 id="searchDermatologistsCaption">Search and filter dermatologists</h1>
    <div class="pt-1">
      <v-card
        width="40%"
        class="mx-auto mt-3"
        v-bind:style="{ opacity: opacity }"
      >
        <v-card-text>
          <v-form class="mx-auto mt-5 mb-5 mr-10 ml-10">
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="First name"
                :rules="name"
                maxlength="25"
                counter="25"
                v-model="searchFirstName"
                color="blue"
                type="text"
                :counter-value="v => v.trim().length"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Last name"
                maxlength="50"
                counter="50"
                :rules="name"
                v-model="searchLastName"
                color="blue"
                type="text"
                :counter-value="v => v.trim().length"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Enter min for rating"
                v-model="minRating"
                color="blue"
                type="number"
                min="0"
                max="10"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Enter max for rating"
                v-model="maxRating"
                color="blue"
                type="number"
                min="0"
                max="10"
              />
            </v-row>
          </v-form>
        </v-card-text>

        <v-card-actions class="justify-center">
          <v-btn
            v-on:click="searchDermatologist"
            color="info"
            class="mt-2 mr-10 p-5 mb-5"
            x-medium
            width="260px"
            height="40px"
            >Search dermatologist</v-btn
          >
          <v-btn
            v-on:click="resetSearch"
            color="info"
            class="mt-2 ml-10 p-5 mb-5"
            x-medium
            width="260px"
            height="40px"
            >Reset search</v-btn
          >
        </v-card-actions>
      </v-card>
    </div>
    <div class="mt-2 ml-10 mr-10">
      <p id="dermatologistsSubtitle">Dermatologists</p>
      <v-simple-table id="searchTable">
        <template v-slot:default>
          <thead>
            <tr>
              <th :class="sortedClass('firstName')" class="text-center" @click="sortBy('firstName')">First name</th>
              <th :class="sortedClass('lastName')" class="text-center" @click="sortBy('lastName')">Last name</th>
              <th :class="sortedClass('rating')" class="text-center" @click="sortBy('rating')">Raiting of a dermatologist</th>
              <th :class="sortedClass('pharmacyNames')" class="text-center" @click="sortBy('pharmacyNames')">Work in pharmacies</th>
            </tr>
          </thead>
          <tbody v-bind:hidden="showList">
            <tr v-for="item in dermatologists" :key="item.firstName">
              <td class="text-center">{{ item.firstName }}</td>
              <td class="text-center">{{ item.lastName }}</td>
              <td class="text-center">{{ item.rating }}</td>
              <td class="text-center">{{ item.pharmacyNames }}</td> 
            </tr>
          </tbody>
          <tbody v-bind:hidden="!showList">
            <tr v-for="item in sortedItems" :key="item.firstName">
              <td class="text-center">{{ item.firstName }}</td>
              <td class="text-center">{{ item.lastName }}</td>
              <td class="text-center">{{ item.rating }}</td>
              <td class="text-center">{{ item.pharmacyNames }}</td> 
            </tr>
          </tbody>
        </template>
      </v-simple-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "SearchDermatologist",
  data: () => ({
    opacity: 0.9,
    searchFirstName: "",
    searchLastName: "",
    minRating: "",
    maxRating: "",
    dermatologists: [],
    dermatologistList: [],
    allDermatologists: null, 
    showList: false,
    sort: {
      key: '',
      isAsc: false,
    },
    userType: "",
    userId: "",
    pharmacyAdminLogged: false,
    pharmacyAdmin: null,
  }),
  mounted() {
    this.init();
  },
  methods: {
    init() {
      this.userType = localStorage.getItem("userType");
      this.userId = localStorage.getItem("userId");


      this.axios
      .get("http://localhost:8091/dermatologists/all", {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      })
      .then(response => {
        console.log(response.data);
        this.allDermatologists = response.data;

        if (this.userType == 'PH_ADMINISTRATOR') {
            this.pharmacyAdminLogged = true;
            this.axios
            .get("http://localhost:8091/pharmacyAdmin/" + this.userId, {
              headers: {
                Authorization: "Bearer " + localStorage.getItem("token"),
              }
            })
            .then(resp => {
              console.log(resp.data);
              this.pharmacyAdmin = resp.data;
              alert("Now is logged " + this.pharmacyAdmin.firstName  + " " + this.pharmacyAdmin.lastName + " ( " + this.userType + " ) , who work in " + this.pharmacyAdmin.pharmacyName + " pharmacy.");
              for(let i = 0; i < this.allDermatologists.length; i++)
              {
                if (this.allDermatologists[i].pharmacyNames.includes(this.pharmacyAdmin.pharmacyName)) {
                  this.dermatologists.push(this.allDermatologists[i]);
                }
              }
            })
            .catch(err => console.log(err));
          }
          else 
          {
            alert("Now is logged " + this.userType + ".");
            this.pharmacyAdminLogged = false;
            this.dermatologists = this.allDermatologists;
          }
      })
      .catch(err => console.log(err));
    },
    searchDermatologist() {
      if (this.searchFirstName != "" && this.searchLastName == "") {
        console.log("Search dermatologists by first name");
        this.axios
          .get(
            "http://localhost:8091/dermatologists/searchDermatologistsByFirstName/" +
              this.searchFirstName,
            {
              headers: {
                Authorization: 'Bearer '+ localStorage.getItem("token")
              },
            }
          )
          .then((response) => {
            console.log(response.data);
            this.dermatologistList = [];

            if (this.pharmacyAdminLogged) {
              for(let i = 0; i < response.data.length; i++)
              {
                if (response.data[i].pharmacyNames.includes(this.pharmacyAdmin.pharmacyName)) {
                  this.dermatologistList.push(response.data[i]);
                }
              }
            } else {
              this.dermatologistList = response.data;
            }


            this.showList = true;
            if (response.data.length == 0) {
              this.dermatologistList = [];
              alert("No results found try another search");
              this.clearInputFields();
            }
          })
          .catch(err => console.log(err));

      } else if (this.searchFirstName == "" && this.searchLastName != "") {
        console.log("Search dermatologists by last name");
        this.axios
          .get(
            "http://localhost:8091/dermatologists/searchDermatologistsByLastName/" +
              this.searchLastName,
            {
              headers: {
                Authorization: 'Bearer ' + localStorage.getItem("token")
              },
            }
          )
          .then((response) => {
            console.log(response.data);
            this.dermatologistList = [];

            if (this.pharmacyAdminLogged) {
              for(let i = 0; i < response.data.length; i++)
              {
                this.adminPharmacy = this.pharmacyAdmin.pharmacyName;
                if (response.data[i].pharmacyNames.includes(this.adminPharmacy)) {
                  this.dermatologistList.push(response.data[i]);
                }
              }
            } else {
              this.dermatologistList = response.data;
            }

            this.showList = true;
            if (response.data.length == 0) {
              this.dermatologistList = [];
              alert("No results found try another search");
              this.clearInputFields();
            }
          });
      } else if (this.searchFirstName != "" && this.searchLastName != "") {
        console.log("Search dermatologist by first and last name");
        this.axios
          .get(
            "http://localhost:8091/dermatologists/searchDermatologists/" +
              this.searchFirstName +
              "/" +
              this.searchLastName,
            {
              headers: {
                Authorization: 'Bearer ' + localStorage.getItem("token")
              },
            }
          )
          .then((response) => {
            console.log(response.data);
            this.dermatologistList = [];

            if (this.pharmacyAdminLogged) {
              for(let i = 0; i < response.data.length; i++)
              {
                if (response.data[i].pharmacyNames.includes(this.pharmacyAdmin.pharmacyName)) {
                  this.dermatologistList.push(response.data[i]);
                }
              }
            } else {
              this.dermatologistList = response.data;
            }

            this.showList = true;
            if (response.data.length == 0) {
              this.dermatologistList = [];
              alert("No results found try another search");
              this.clearInputFields();
            }
          });
      } else if (this.minRating != "" && this.maxRating != "") {
        if (
          Number(this.minRating) < 0 || Number(this.minRating) > 10 ||
          Number(this.maxRating) < 0 || Number(this.maxRating) > 10
        ) {
          alert("Rating must be in the range 0 to 10");
          this.clearInputFields();
        } else if (Number(this.minRating) > Number(this.maxRating)) {
          alert("Not valid input for rating, min rating must be less than max rating");
          this.clearInputFields();
        } else {
          console.log("Search dermatologist by rating");
          this.axios
            .get(
              "http://localhost:8091/dermatologists/filterDermatologistByRating/" +
                this.minRating +
                "/" +
                this.maxRating,
              {
                headers: {
                  Authorization: 'Bearer ' + localStorage.getItem("token")
                },
              }
            )
            .then((response) => {
              console.log(response.data);
              this.dermatologistList = [];

              if (this.pharmacyAdminLogged) {
                for(let i = 0; i < response.data.length; i++)
                {
                  if (response.data[i].pharmacyNames.includes(this.pharmacyAdmin.pharmacyName)) {
                    this.dermatologistList.push(response.data[i]);
                  }
                }
              } else {
                this.dermatologistList = response.data;
              }

              this.showList = true;
              if (response.data.length == 0) {
                this.dermatologistList = [];
                alert("No results found try another search");
                this.clearInputFields();
              }
            });
        }
      } else {
        alert(
          "The fields based on which the search is performed are empty! You must fill in at least one of the search fields!"
        );
      }
    },
    resetSearch() {
      console.log("Reset search");
      this.showList = false;
      this.clearInputFields();
    },
    clearInputFields() {
      this.searchFirstName = "";
      this.searchLastName = "";
      this.minRating = "";
      this.maxRating = "";
    },
    sortedClass(key) { 
      return this.sort.key === key ? `sorted ${this.sort.isAsc ? 'asc' : 'desc'}` : '';
    },
    sortBy (key) { 
      this.sort.isAsc = this.sort.key === key ? !this.sort.isAsc : false;
      this.sort.key = key; 
    }
  },
  computed: {
    sortedItems () {
      if (!this.showList) {
        var list = this.dermatologists.slice();
      } else {
        list = this.dermatologistList.slice();
      }

      if (this.sort.key !='') {
        list.sort((a,b) => {
          a = a[this.sort.key]
          b = b[this.sort.key]

          return (a === b ? 0 : a > b ? 1 : -1) * (this.sort.isAsc ? 1 : -1)
        });
      }
      return list;
    }
  }
};
</script>

<style scoped>
#searchDermatologistsCaption {
  margin-top: 2%;
  margin-bottom: 2%;
  color: rgb(2, 2, 117);
  text-align: center;
  font-weight: bold;
}

#dermatologistsSubtitle {
  margin-top: 2%;
  margin-bottom: 2%;
  color: rgb(2, 2, 117);
  text-align: center;
  font-size: 1.8em;
  font-weight: bold;
}
</style>