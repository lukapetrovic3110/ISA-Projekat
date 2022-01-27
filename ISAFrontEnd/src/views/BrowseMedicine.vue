<template>
  <div id = "searchMedicine">
    <br>
    <div class = "container">
    <h3>Search Medicines:</h3>
    <input placeholder="Enter medicine" type="search" v-model = "searchMedicine" id="search" name="searchMedicine" required>
    <v-btn
        color="secondary"
        elevation="3"
        small
        v-on:click = "searchMedicines"
        v-if="notFilled"
    >Search</v-btn>
    <br>
    <h3>Filtrate by rating higher than:</h3>

    <label>Rating 10</label>
    <input type="radio" value=5 name="rating" @change = "filtrate(10)">

    <label>Rating 9</label>
    <input type="radio" value=4 name="rating" @change = "filtrate(9)">

    <label>Rating 8</label>
    <input type="radio" value=3 name="rating" @change = "filtrate(8)">

    <label>Rating 7</label>
    <input type="radio" value=2 name="rating" @change = "filtrate(7)">

    <label>Rating 6</label>
    <input type="radio" value=1 name="rating" @change = "filtrate(6)">


      <v-simple-table>
        <template v-slot:default>
          <thead>
          <tr>
            <th class="text-left">
              Name
            </th>
            <th class="text-left">
              Type
            </th>
            <th class="text-left">
              Rating
            </th>
          </tr>
          </thead>
          <tbody>
          <tr
              v-for="m in medicines"
              :key="m"
          >
            <td>{{ m.name }}</td>
            <td>{{ m.type }}</td>
            <td>{{ m.rating }}</td>
            
                <td><v-col cols="auto">
                        <v-dialog
                            transition="dialog-top-transition"
                            max-width="600"
                        >
                          <template v-slot:activator="{ on, attrs }">
                            <v-btn
                                color="primary"
                                small
                                v-bind="attrs"
                                v-on="on"
                            >Medicine Specification</v-btn>
                          </template>
                          <template v-slot:default="dialog">
                            <v-card>
                              <v-toolbar
                                  color="primary"
                                  dark
                              >Medicine Specification</v-toolbar>
                              <v-simple-table>
                                <template v-slot:default>
                    <tr>
                      <td  width="40"></td>
                      <td class="text-left" >Name:</td>
                      <td  width="50"></td>
                      <td class="text-left">{{ m.name }}</td>
                    </tr>
                    <br>
                    <tr>
                      <td  width="40"></td>
                      <td class="text-left" width="240">Dosage:</td>
                      <td  width="50"></td>
                      <td class="text-left">{{ m.medicineSpecification.dosage }}</td>
                    </tr>
                    <br>
                    <tr 
                          v-for="i in m.medicineSpecification.ingridient"
                          :key="i"
                    >
                      <td  width="40"></td>
                      <td class="text-left" width="240">Ingridients:</td>
                      <td  width="50"></td>
                      <td class="text-left">{{ i.name }}</td>
                    </tr>
                    <br>
                    <tr           
                          v-for="c in m.medicineSpecification.contraindication"
                          :key="c"
                    >
                      <td  width="40"></td>
                      <td class="text-left" width="240">Contraindications:</td>
                      <td  width="50"></td>
                      <td class="text-left">{{ c.description }}</td>
                    </tr>
                    <br>
                  </template>
                </v-simple-table>
                <v-btn
                    width="300"
                    text
                    @click="dialog.value = false"
                >Close</v-btn>
                </v-card>
                </template>
                </v-dialog>
                </v-col>
        </td>
          </tr>
          </tbody>
        </template>
      </v-simple-table>
    </div>

    <div class = "container">
    <h3>Available In:</h3>
      <v-simple-table>
        <template v-slot:default>
          <thead>
          <tr>
            <th class="text-left">
              Name
            </th>
            <th class="text-left">
              Medicine
            </th>
            <th class="text-left">
              Price
            </th>
          </tr>
          </thead>
          <tbody>
          <tr
              v-for="a in availableInPharmacies" :key="a"
          >
            <td>{{ a.pharmacy.name }}</td>
            <td>{{a.priceTag.medicine.name}}</td>
            <td>{{a.priceTag.price}}</td>
            <td>
              <v-dialog
                  transition="dialog-top-transition"
                  max-width="600"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                      color="primary"
                      small
                      v-bind="attrs"
                      v-if="isLogged"
                      v-on="on"
                  >Make a reservation</v-btn>
                </template>
                <template v-slot:default="dialog">
                  <v-card>
                    <v-toolbar
                        color="primary"
                        dark
                    >Reservation until:</v-toolbar>
                    <template>
                      <v-row justify="center">
                        <v-date-picker v-model="picker"></v-date-picker>
                      </v-row>
                    </template>
    <v-btn
        width="300"
        small
        color="primary"
        @click="makeReservation(a, dialog)"
    >Make a reservation</v-btn>
    <v-btn
        width="300"
        color="primary"
        small
        @click="dialog.value=false"
    >Cancel</v-btn>
    </v-card>
    </template>
    </v-dialog>
            </td>
          </tr>
          </tbody>
        </template>
      </v-simple-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "BrowseMedicine",
  data: function () {
    return {
      dialog: false,
      dateValid:false,
      picker: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),
      medicines: [],
      token: localStorage.getItem("token"),
      medicineSpecification: {
          dosage: "",
          contraindication: {
              description: ""
          },
          ingridient: {
              name: ""
          },
      },
      availableInPharmacies: [],
      searchMedicine: "",
      patient: {}
    }
  },
  mounted(){
    this.axios
        .get('http://localhost:8091/medicine')
        .then(response => (this.medicines = response.data));
  },
  methods: {
    searchMedicines: function() {
      this.getUser()
      this.axios
          .get("http://localhost:8091/medicine/getMedicineByName/" + this.searchMedicine)
          .then(response => (this.medicines = response.data));
       this.axios
          .get("http://localhost:8091/medicine/checkMedicineInPharmacy/" + this.searchMedicine)
          .then(r => (this.availableInPharmacies = r.data));
    },
    filtrate: function(rating) {
      this.axios  
          .get("http://localhost:8091/medicine/filtrate/" + rating)
          .then(response => (this.medicines = response.data));
    },
    makeReservation: function(a, dialog){
      dialog.value = false
      if(this.patient.penalty < 3) {
        this.axios
            .put('http://localhost:8091/reservation/checkDate',{date: this.picker}, {
              headers: {
                Authorization: 'Bearer ' + localStorage.getItem("token")
              }
            })
            .then(r => {
              this.dateValid = r.data
              if(this.dateValid) {
                this.axios
                    .post("http://localhost:8091/reservation/create", {
                      dto: a, date: this.picker,
                      userId: localStorage.getItem("userId")
                    }, {
                      headers: {
                        Authorization: 'Bearer ' + localStorage.getItem("token")
                      }
                    })
                    .then(window.location.href = "http://localhost:8080/medicineReservations");
              }else{
                alert("Please select a date in the future.")
              }
            });
      }else{
        alert("You can't schedule make a reservation because you have 3 or more penalties. This option" +
            " will be available next month")
      }
    },
    getUser: function (){
      this.axios
          .get('http://localhost:8091/users/' + localStorage.getItem("userId"), {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
          })
          .then(response => (this.patient = response.data));
    }
  },

  computed:{
    isLogged: function (){
      if (this.token == "" || localStorage.getItem("userType") != "PATIENT"){
        return false
      }else{
        return true
      }
    },
    notFilled: function () {
      if (this.searchMedicine.trim() == ""){
        return false
      }
      else {
        return true
      }
    }
  }
}
</script>

<style scoped>
#search{
  background-color: white;
}
#searchButton{
  color: white;
  border: white;
  border-width: 10px;
}

.container {
  display: block;
  position: relative;
  padding-left: 35px;
  margin-bottom: 12px;
  cursor: pointer;
  font-size: 18px;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}
input[type=radio]{
  border-radius: 10px;
  cursor: pointer;
  display: inline-block;
  height: 15px;
  margin-right: 15px;
  position: relative;
  width: 15px;
  -webkit-appearance: none;
}

</style>