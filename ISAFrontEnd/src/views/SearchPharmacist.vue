<template>
  <div>
    <h1 id="searchPharmacistsCaption">Search and filter pharmacists</h1>
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
                maxlength="25"
                counter="25"
                :rules="name"
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
            v-on:click="searchPharmacist"
            color="info"
            class="mt-2 mr-10 p-5 mb-5"
            x-medium
            width="260px"
            height="40px"
            >Search pharmacist</v-btn
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
      <p id="pharmacistsSubtitle">Pharmacists</p>
      <v-simple-table id="searchTable">
        <template v-slot:default>
          <thead>
            <tr>
              <th :class="sortedClass('firstName')" class="text-center" @click="sortBy('firstName')">First name</th>
              <th :class="sortedClass('lastName')" class="text-center" @click="sortBy('lastName')">Last name</th>
              <th :class="sortedClass('rating')" class="text-center" @click="sortBy('rating')">Raiting of a pharmacist</th>
              <th :class="sortedClass('pharmacyName')" class="text-center" @click="sortBy('pharmacyName')">Work in pharmacy</th>
            </tr>
          </thead>
          <tbody v-bind:hidden="showList">
            <tr v-for="item in pharmacists" :key="item.firstName">
              <td class="text-center">{{ item.firstName }}</td>
              <td class="text-center">{{ item.lastName }}</td>
              <td class="text-center">{{ item.rating }}</td>
              <td class="text-center">{{ item.pharmacyName }}</td>
            </tr>
          </tbody>
          <tbody v-bind:hidden="!showList">
            <tr v-for="item in sortedItems" :key="item.firstName">
              <td class="text-center">{{ item.firstName }}</td>
              <td class="text-center">{{ item.lastName }}</td>
              <td class="text-center">{{ item.rating }}</td>
              <td class="text-center">{{ item.pharmacyName }}</td>
            </tr>
          </tbody>
        </template>
      </v-simple-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "SearchPharmacist",
  data: () => ({
    opacity: 0.9,
    searchFirstName: "",
    searchLastName: "",
    minRating: "",
    maxRating: "",
    pharmacists: [],
    pharmacistList: [],
    allPharmacists: null,
    showList: false,
    sort: {
      key: '',
      isAsc: false,
    },
    userType: "", 
    userId: "",
    pharmacyAdminLogged: false,
  }),
  mounted() {
    this.init();
  },
  methods: {
    init() {
      this.userType = localStorage.getItem("userType");
      this.userId = localStorage.getItem("userId");

      this.axios
      .get("http://localhost:8091/pharmacists/all", {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      })
      .then(response => {
        console.log(response.data);
        this.allPharmacists = response.data;

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
              for(let i = 0; i < this.allPharmacists.length; i++)
              {
                if (this.pharmacyAdmin.pharmacyName === this.allPharmacists[i].pharmacyName) {
                  this.pharmacists.push(this.allPharmacists[i]);
                }
              }
            })
            .catch(err => console.log(err));
          }
          else 
          {
            alert("Now is logged " + this.userType + ".");
            this.pharmacyAdminLogged = false;
            this.pharmacists = this.allPharmacists;
          }
      })
      .catch(err => console.log(err));

     
    },
    searchPharmacist() {
      if (this.searchFirstName != "" && this.searchLastName == "") {
        console.log("Search pharmacists by first name");
        this.axios
          .get(
            "http://localhost:8091/pharmacists/searchPharmacistsByFirstName/" +
              this.searchFirstName,
            {
              headers: {
                Authorization: "Bearer " + localStorage.getItem("token"),
              },
            }
          )
          .then(response => {
            this.pharmacistList = [];
            console.log(response.data);

            if (this.pharmacyAdminLogged) {
              for(var i = 0; i < response.data.length; i++)
              {
                if (this.pharmacyAdmin.pharmacyName === response.data[i].pharmacyName) {
                    this.pharmacistList.push(response.data[i]);
                }
              }
            } else 
            {
              this.pharmacistList = response.data;
            }

            this.showList = true;
            if (response.data.length == 0) {
              this.pharmacistList = [];
              alert("No results found try another search");
              this.clearInputFields();
            }
          })
          .catch((err) => console.log(err));
      } else if (this.searchFirstName == "" && this.searchLastName != "") {
        console.log("Search pharmacists by last name");
        this.axios
          .get(
            "http://localhost:8091/pharmacists/searchPharmacistsByLastName/" +
              this.searchLastName,
            {
              headers: {
                Authorization: 'Bearer ' + localStorage.getItem("token"),
              },
            }
          )
          .then((response) => {
            this.pharmacistList = [];
            console.log(response.data);

            if (this.pharmacyAdminLogged) {
              for(var i = 0; i < response.data.length; i++)
              {
                if (this.pharmacyAdmin.pharmacyName === response.data[i].pharmacyName) {
                    this.pharmacistList.push(response.data[i]);
                }
              }
            } else 
            {
              this.pharmacistList = response.data;
            }

            this.showList = true;
            if (response.data.length == 0) {
              this.pharmacistList = [];
              alert("No results found try another search");
              this.clearInputFields();
            }
          });
      } else if (this.searchFirstName != "" && this.searchLastName != "") {
        console.log("Search pharmacist by first and last name");
        this.axios
          .get(
            "http://localhost:8091/pharmacists/searchPharmacists/" +
              this.searchFirstName +
              "/" +
              this.searchLastName,
            {
              headers: {
                Authorization: "Bearer " + localStorage.getItem("token"),
              },
            }
          )
          .then((response) => {
            this.pharmacistList = [];
            console.log(response.data);

            if (this.pharmacyAdminLogged) {
              for(var i = 0; i < response.data.length; i++)
              {
                if (this.pharmacyAdmin.pharmacyName === response.data[i].pharmacyName) {
                    this.pharmacistList.push(response.data[i]);
                }
              }
            } else 
            {
              this.pharmacistList = response.data;
            }

            this.showList = true;
            if (response.data.length == 0) {
              this.pharmacistList = [];
              alert("No results found try another search");
              this.clearInputFields();
            }
          });
      } else if (this.minRating != "" && this.maxRating != "") {
        if (
          Number(this.minRating) < 0 ||
          Number(this.minRating) > 10 ||
          Number(this.maxRating) < 0 ||
          Number(this.maxRating) > 10
        ) {
          alert("Rating must be in the range 0 to 10");
          this.clearInputFields();
        } else if (Number(this.minRating) > Number(this.maxRating)) {
          alert("Not valid input for rating, min rating must be less than max rating");
          this.clearInputFields();
        } else 
        {
          console.log("Search pharmacist by rating");
          this.axios
            .get(
              "http://localhost:8091/pharmacists/filterPharmacistByRating/" +
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
              this.pharmacistList = [];
              console.log(response.data);
              if (this.pharmacyAdminLogged) {
                for(var i = 0; i < response.data.length; i++)
                {
                  if (this.pharmacyAdmin.pharmacyName === response.data[i].pharmacyName) {
                      this.pharmacistList.push(response.data[i]);
                  }
                }
              } else 
              {
                this.pharmacistList = response.data;
              }
              this.showList = true;
              if (response.data.length == 0) {
                this.pharmacistList = [];
                alert("No results found try another search");
                this.clearInputFields();
              }
            });

        }
      } 
      else {
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
      // sort example : https://jsfiddle.net/bc_rikko/q1k4ooq8/
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
        var list = this.pharmacists.slice();
      } else {
        list = this.pharmacistList.slice();
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
  },
};
</script>

<style scoped>
#searchPharmacistsCaption {
  margin-top: 2%;
  margin-bottom: 2%;
  color: rgb(2, 2, 117);
  text-align: center;
  font-weight: bold;
}

#pharmacistsSubtitle {
  margin-top: 2%;
  margin-bottom: 2%;
  color: rgb(2, 2, 117);
  text-align: center;
  font-size: 1.8em;
  font-weight: bold;
}
</style>