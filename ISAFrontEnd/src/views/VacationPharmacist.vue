<template>
  <div class = "container">
    <!--  Date picker-->
    <v-simple-table>
      <thead class="text-center">
      <th colspan="2">Schedule Vacation</th>
      <br>
      <br>
      </thead>
      <tr>
        <th>Pick Start Date:</th>
        <th>Pick End Date:</th>
      </tr>
      <tr>
        <td>
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
                  background-color="white"
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

          <v-spacer></v-spacer>

        </td>
        <td>
          <v-menu
              v-model="menu2"
              :close-on-content-click="false"
              :nudge-right="40"
              transition="scale-transition"
              offset-y
              min-width="auto"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-text-field
                  background-color="white"
                  v-model="date2"
                  label="Pick date"
                  prepend-icon="mdi-calendar"
                  readonly
                  v-bind="attrs"
                  v-on="on"
              ></v-text-field>
            </template>
            <v-date-picker
                v-model="date2"
                @input="menu2 = false"
            ></v-date-picker>
          </v-menu>

          <v-spacer></v-spacer>

        </td>
      </tr>
      <tr>
        <td align="center" colspan="2"><v-btn
            color="primary"
            elevation="2"
            width="200"
            x-large
            v-on:click= "sendRequest"
        >Request</v-btn></td>
      </tr>
      <br>
    </v-simple-table>
  </div>
</template>

<script>
export default {
  name: "VacationPharmacist",
  data: function () {
    return{
      date: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),
      date2: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),
      menu: false,
      menu2: false,
      vacat: null,
      pharmacistt: null,
      requested: false
    }
  },
  mounted() {
    if(localStorage.getItem("userType") == "PHARMACIST"){
      this.axios
          .get('http://localhost:8091/users/' + localStorage.getItem("userId"), {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
          })
          .then(response => (this.pharmacistt = response.data));
    } else{
      window.location.href = "http://localhost:8080/logIn";
    }
  },
  methods: {
    sendRequest: function() {
      this.vacat = {
        startDate : this.date,
        endDate: this.date2,
        pharmacist: this.pharmacistt
      }

      this.axios
          .post('http://localhost:8091/pharmacistVacation/requestVacationPharmacist', this.vacat, {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
          })
          .then( response => {
            this.requested = response.data
            if(this.requested){
              alert("Vacation requested!"),
                  window.location.href = "http://localhost:8080/homePagePharmacist"
            }
            else {
              alert("Start Date  or/and End Date is not valid!")
            }
          })

    }
  }
}
</script>


<style scoped>
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
v-datepicker{
  startDate: '+0d'
}
</style>