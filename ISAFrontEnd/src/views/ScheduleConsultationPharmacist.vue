<template>
  <div class = "container">
  <!--  Date picker-->
  <v-simple-table>
    <thead class="text-center">
    <th colspan="2">Schedule New Consultation</th>
    <br>
    <br>
    </thead>
    <tr>
      <th>Pick date:</th>
      <th>Pick time:</th>
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
      <!--Time picker-->
      <td>
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
                background-color="white"
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
      </td>
    </tr>
    <tr>
      <td align="center" colspan="2"><v-btn
          color="primary"
          elevation="2"
          width="200"
          x-large
          v-on:click= "createNewDateTerm"
      >Schedule</v-btn></td>
    </tr>
    <br>
  </v-simple-table>
</div>
</template>

<script>
export default {
  name: "ScheduleConsultationPharmacist.vue",
  data: function () {
    return{
      date: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),
      menu: false,
      modal: false,
      menu2: false,
      pharmacistt: null,
      patient_id : localStorage.getItem("patientId"),
      pharmacy_id: localStorage.getItem("pharmacyId"),
      pharmacyy: null,
      patient: null,
      available : false,
      cons: null
    }
  },
  mounted() {
    if(localStorage.getItem("userType") == "PHARMACIST"){

      this.appId = localStorage.getItem("appointmentId")
      this.axios
          .get('http://localhost:8091/users/' + localStorage.getItem("userId"), {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
          })
          .then(response => (this.pharmacistt = response.data));

      this.axios
          .get('http://localhost:8091/users/' + localStorage.getItem("patientId"), {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
          })
          .then(response => (this.patient = response.data));

      this.axios
          .get('http://localhost:8091/pharmacy/2', {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
          })
          .then(response => (this.pharmacyy = response.data));
      if (this.appId == null) {
        alert("None appointment is started!")
        window.location.href = "http://localhost:8080/homePagePharmacist";
      } else {
        this.axios
            .post('http://localhost:8091/medicine/endExam/' + this.currentExamination.appointmentId, {}, {
              headers: {
                Authorization: 'Bearer ' + localStorage.getItem("token")
              }
            })
            .then(response => {
              this.app = response.data
              localStorage.removeItem("appointmentId"),
                  window.location.href = "http://localhost:8080/homePagePharmacist"
            });
      }
    } else{
      window.location.href = "http://localhost:8080/logIn";
    }
  },
  methods: {
    createNewDateTerm: function() {

      this.cons = {
        dateAndTime: this.date + "T" + this.time + ".00",
        duration: 30,
        price: 3000,
        cancelled: false,
        points: 20,
        patient: this.patient,
        pharmacy: this.pharmacyy,
        pharmacist: this.pharmacistt
      }

      this.axios
          .put('http://localhost:8091/consultation/createNewConsultation', this.cons, {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
          })
          .then( response => {
            this.available = response.data
            if(this.available){
              alert("Examination scheduled!"),
                  window.location.href = "http://localhost:8080/homePagePharmacist"
            }
            else {
              alert("You did not choose available term! Choose again!")
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