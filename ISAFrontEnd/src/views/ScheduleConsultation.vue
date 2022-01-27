<template>
  <div id = "homePage">
    <div class = "container">
      <h2>Select date and time for consultation with a pharmacist</h2>

  <!--  Date picker-->
      <v-row>
        <v-col
            cols="12"
            sm="6"
            md="4"
        >
          <v-menu
              v-model="menu"
              :close-on-content-click="false"
              :nudge-right="40"
              transition="scale-transition"
              offset-y
              min-width="auto"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-text-field
                  v-model="date"
                  label="Pick date"
                  prepend-icon="mdi-calendar"
                  readonly
                  v-bind="attrs"
                  v-on="on"
              ></v-text-field>
            </template>
            <v-date-picker
                v-model="date"
                @input="menu = false"
            ></v-date-picker>
          </v-menu>
        </v-col>
        <v-spacer></v-spacer>
      </v-row>

      <!--Time picker-->
        <v-row>
         <v-col
          cols="11"
          sm="5"
          >
          <v-menu
              ref="menu"
              v-model="menu2"
              :close-on-content-click="false"
              :nudge-right="40"
              :return-value.sync="time"
              transition="scale-transition"
              offset-y
              max-width="290px"
              min-width="290px"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-text-field
                  v-model="time"
                  label="Pick time"
                  prepend-icon="mdi-clock-time-four-outline"
                  readonly
                  v-bind="attrs"
                  v-on="on"
              ></v-text-field>
            </template>
            <v-time-picker
                v-if="menu2"
                v-model="time"
                full-width
                @click:minute="$refs.menu.save(time)"
            ></v-time-picker>
          </v-menu>
        </v-col>
        <v-spacer></v-spacer>
      </v-row>

      <v-row
          align="center"
          justify="space-around">
        <v-btn
            color="primary"
            elevation="3"
            medium
            v-if="notFilled"
            @click="getPharmacies"
        >Find pharmacies with available pharmacists</v-btn>
      </v-row>
      <br>
      <v-simple-table>
        <template v-slot:default>
          <thead>
          <tr>
            <th>
              Name
            </th>
            <th :class="sortedClass('rating')"
                @click="sortBy('rating')">
              Rating
            </th>
            <th>
              Address
            </th>
            <th :class="sortedClass('consultationPrice')"
                @click="sortBy('consultationPrice')">
              Consultation Price
            </th>
          </tr>
          </thead>
          <tbody>
          <tr
              v-for="p in sortedItems"
              :key="p"
          >
            <td>{{ p.name }}</td>
            <td>{{ p.rating }}</td>
            <td>{{p.address.street + " " + p.address.streetNumber + ", " + p.address.city + ", " + p.address.country}}</td>
            <td>{{p.consultationPrice.toString() + " rsd"}}</td>
            <td>
              <v-btn
                  color="secondary"
                  elevation="3"
                  x-small
                  v-on:click = "pharmacistsForConsultation(p)"
              >See available pharmacits</v-btn>
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
  name: "ScheduleConsultation",
  data: function () {
    return {
      date: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),
      menu: false,
      modal: false,
      menu2: false,
      logged: false,
      time: null,
      modal2: false,
      pharmacies: [],
      searchField: "",
      dateValid: false,
      token: localStorage.getItem("token"),
      sort: {
        key: '',
        isAsc: false
      }
    }
  },
  methods: {
    getPharmacies(){
      this.axios
          .put('http://localhost:8091/workSchedulePharmacist/checkDate',{date: this.date, time: this.time}, {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
          })
          .then(r => {this.dateValid = r.data
     if(this.dateValid) {
       this.axios
           .put('http://localhost:8091/workSchedulePharmacist/getAvailablePharmacies', {
             date: this.date,
             time: this.time
           }, {
             headers: {
               Authorization: 'Bearer ' + localStorage.getItem("token")
             }
           })
           .then(r => {this.pharmacies = r.data
              if(this.pharmacies.length == 0){
                alert("There are no pharamacies with available pharmacist for selected time!")
              }
           })
     }else{
       alert("Please select a date in the future.")
     }
          })
    },

    pharmacistsForConsultation(p){
      localStorage.setItem("pharmacy", p.pharmacyId);
      localStorage.setItem("pharmacyName", p.name);
      localStorage.setItem("date", this.date);
      localStorage.setItem("time", this.time);
      window.location.href = "http://localhost:8080/scheduleConsultationInPharmacy";
    },
    sortedClass (key) {
      return this.sort.key === key ? `sorted ${this.sort.isAsc ? 'asc' : 'desc' }` : '';

    },
    sortBy (key) {
      this.sort.isAsc = this.sort.key === key ? !this.sort.isAsc : false;
      this.sort.key = key;
    },
  },
  computed:{
    isDateInvalid: function(){
      var now = new Date()
      if(this.date.getDate() < now.getDate()){
        alert("jdnjdn")
        return true
      }else{
        return false
      }
    },
    isLogged: function (){
      if (this.token == ""){
        return false
      }else{
        return true
      }
    },
    notFilled: function () {
      if (this.time == null){
        return false
      }
      else {
        return true
      }
    },sortedItems () {
      const list = this.pharmacies.slice();  // ソート時でdataの順序を書き換えないため
      if (this.sort.key !="") {
        list.sort((a, b) => {
          a = a[this.sort.key]
          b = b[this.sort.key]

          return (a === b ? 0 : a > b ? 1 : -1) * (this.sort.isAsc ? 1 : -1)
        });
      }

      return list;
    }
  }
}
</script>

<style scoped>
#searchPharmacies{
  background-color: white;
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
  box-shadow: inset 0 1px 1px hsla(0,0%,100%,.8),
  0 0 0 1px hsla(0,0%,0%,.6),
  0 2px 3px hsla(0,0%,0%,.6),
  0 4px 3px hsla(0,0%,0%,.4),
  0 6px 6px hsla(0,0%,0%,.2),
  0 10px 6px hsla(0,0%,0%,.2);
  cursor: pointer;
  display: inline-block;
  height: 15px;
  margin-right: 15px;
  position: relative;
  width: 15px;
  -webkit-appearance: none;
}

input[type="radio"]:after {
  border-radius: 25px;
  box-shadow: inset 0 0 0 1px hsla(0,0%,0%,.4),
  0 1px 1px hsla(0,0%,100%,.8);
  content: '';
  display: block;
  height: 7px;
  left: 4px;
  position: relative;
  top: 4px;
  width: 7px;
}
input[type="radio"]:checked:after {
  background-color: #444;
  box-shadow: inset 0 0 0 1px hsla(0,0%,0%,.4),
  inset 0 2px 2px hsla(0,0%,100%,.4),
  0 1px 1px hsla(0,0%,100%,.8),
  0 0 2px 2px hsla(0,70%,70%,.4);
}
v-datepicker{
  startDate: '+0d'
}

</style>