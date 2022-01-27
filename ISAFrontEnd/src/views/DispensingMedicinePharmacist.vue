<template>
  <div class="container">
    <br>
    <br>
    <v-simple-table>
      <tr>
        <td align="center"><input type="text" width="250" v-model = "reservationCode" placeholder="Enter the reservation code:"></td>
        <td align="center"><v-dialog
            transition="dialog-top-transition"
            max-width="600"
        >

        <template v-slot:activator="{ on, attrs }">
            <v-btn
                v-bind="attrs"
                v-on="on"
                width="250"
                color="primary"
                elevation="2"
                x-large
                v-on:click="searchResult"
            >Search</v-btn>
          </template>
          <template v-slot:default="dialog">
            <v-card>
              <v-toolbar
                  color="primary"
                  dark
              >Search result:</v-toolbar>
              <v-simple-table>
                <template v-slot:default>
                  <tbody v-if="resultTrue">
                    <tr><th colspan="3" class="text-center">MEDICINE IS ISSUED!</th></tr>
                    <tr>
                      <th class="text-center">Code</th>
                      <th class="text-center">Medicine</th>
                      <th class="text-center">Patient email</th>
                    </tr>
                    <tr>
                      <td class="text-center">{{resultReservation.reservationCode }}</td>
                      <td class="text-center">{{ resultReservation.medicineItem.medicine.name }}</td>
                      <td class="text-center">{{ resultReservation.patient.email }} </td>
                    </tr>
                  </tbody>
                  <tbody v-else>
                    <tr><th colspan="2" class="text-center">RESERVATION CODE IS NOT VALID!</th></tr>
                 </tbody>
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
        </td>
      </tr>
    </v-simple-table>
    </div>
</template>

<script>
export default {
  name: "DispensingMedicinePharmacist",
  data: function () {
    return {
      pharmId : localStorage.getItem("pharmacyId"),
      resultTrue: true,
      reservationCode: '',
      dateClicked: null,
      timeClicked: "",
      year : null,
      month : null,
      day : null,
      hours : null,
      minutes : null,
      seconds: null,
      resultReservation: null,
      pharmacist: null
    }
  },
  mounted() {

    this.axios
        .get('http://localhost:8091/users/' + localStorage.getItem("userId"), {
          headers: {
            Authorization: 'Bearer ' + localStorage.getItem("token")
          }
        })
        .then(response => (this.pharmacist = response.data));
  },
  methods: {
    searchResult: function(){

      this.dateClicked = new Date()
      this.year = this.dateClicked.getFullYear()
      this.month = this.dateClicked.getMonth() + 1
      this.day = this.dateClicked.getDate()
      this.hours = this.dateClicked.getHours()
      this.minutes = this.dateClicked.getMinutes()
      this.seconds = this.dateClicked.getSeconds()

      this.timeClicked = this.year + "-" + this.month + "-" + this.day +
          " " + this.hours + ":" + this.minutes + ":" + this.seconds


      const resChecker={
        deadlineDate: this.timeClicked,
        resCode: this.reservationCode,
        pharmacyId : this.pharmacist.pharmacy.pharmacyId
      }

      this.axios
          .put('http://localhost:8091/reservation/findReservation', resChecker, {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
          })
            .then(response => {
              this.resultReservation = response.data;
              if(this.resultReservation.reservationId == null){
                this.resultTrue = false
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


</style>