<template>
    <div class = "container">
      <v-simple-table>
        <template v-slot:default>
          <tr>
            <td align="center"><v-btn
                color="primary"
                elevation="2"
                large
                 v-on:click= "findNextTerm"
              >Choose From Work Schedule</v-btn></td>
            <td align="center"><v-btn
                color="primary"
                elevation="2"
                large
                v-on:click="chooseDateAndTime"
              >Choose New Date And Time</v-btn></td>
          </tr>
        </template>
      </v-simple-table>
      <br>
      <v-simple-table v-if="chooseExisting">
        <thead class="text-center">
          <th>Date and time</th>
          <th>Schedule</th>
        </thead>
        <tbody>
        <tr class="text-center"
          v-for="t in existingTerms"
          :key="t"
        >
          <td>{{ t.dateAndTime }}</td>
          <td align="center" colspan="2"><v-btn
              color="primary"
              elevation="2"
              width="200"
              small
            v-on:click= "scheduleExamination(t)"
            >Schedule</v-btn></td>
        </tr>
        </tbody>
      </v-simple-table>
      <br><br>
      <!--  Date picker-->
      <v-simple-table  v-if="chooseNew">
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
  name: "ScheduleExaminationDermatologist",
  data: function () {
    return{
      date: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),
      menu: false,
      modal: false,
      menu2: false,
      dermatologistt: null,
      existingTerms: {},
      chooseExisting: false,
      exam: null,
      chooseNew: false,
      patient_id : localStorage.getItem("patientId"),
      pharmacy_id: localStorage.getItem("pharmacyId"),
      pharmacyy: null,
      patient: null,
      available : false
    }
  },
  mounted() {

    if(localStorage.getItem("userType") == "DERMATOLOGIST") {

      this.appId = localStorage.getItem("appointmentId")
        this.axios
            .get('http://localhost:8091/users/' + localStorage.getItem("userId"), {
              headers: {
                Authorization: 'Bearer ' + localStorage.getItem("token")
              }
            })
            .then(response => (this.dermatologistt = response.data));

      if(localStorage.getItem("appointmentId") == null) {
        alert("None appointment is started!")
        window.location.href = "http://localhost:8080/homePageDermatologist";
      } else {
        this.axios
            .get('http://localhost:8091/users/' + localStorage.getItem("patientId"), {
              headers: {
                Authorization: 'Bearer ' + localStorage.getItem("token")
              }
            })
            .then(response => (this.patient = response.data));

        this.axios
            .get('http://localhost:8091/pharmacy/' + localStorage.getItem("pharmacyId"), {
              headers: {
                Authorization: 'Bearer ' + localStorage.getItem("token")
              }
            })
            .then(response => (this.pharmacyy = response.data));

        this.axios
            .post('http://localhost:8091/medicine/endExam/' + this.currentExamination.appointmentId, {}, {
              headers: {
                Authorization: 'Bearer ' + localStorage.getItem("token")
              }
            })
            .then(response => {
              this.app = response.data
              localStorage.removeItem("appointmentId")
            });
      }
    }
    else
      {
        window.location.href = "http://localhost:8080/logIn";
      }
  },
  methods: {
    findNextTerm: function (){

      if(localStorage.getItem("appointmentId") == null){
        alert("None appointment is started!")
        window.location.href = "http://localhost:8080/homePagePharmacist";
      } else {

        const dataForTerm = {
          pharmacyId: localStorage.getItem("pharmacyId"),
          dermatologistId: localStorage.getItem("userId"),
          patientId: localStorage.getItem("patientId")
        }

        this.axios
            .put('http://localhost:8091/examination/findValidNextTerm', dataForTerm, {
              headers: {
                Authorization: 'Bearer ' + localStorage.getItem("token")
              }
            })
            .then(response => {
              this.existingTerms = response.data
              this.chooseExisting = true
            })
      }
    },
    scheduleExamination(examination){
      examination.patient = this.patient

      const dataForTerm={
        pharmacyId: localStorage.getItem("pharmacyId"),
        dermatologistId: localStorage.getItem("userId"),
        patientId: localStorage.getItem("patientId")
      }

      this.axios
          .put('http://localhost:8091/examination/schedule', examination, {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
          })
          .then( response => {
            this.existingTerms = response.data
            alert("Examination scheduled!"),
                this.axios
                    .put('http://localhost:8091/examination/findValidNextTerm', dataForTerm, {
                      headers: {
                        Authorization: 'Bearer ' + localStorage.getItem("token")
                      }
                    })
                    .then(response => {
                      this.existingTerms = response.data
                      this.chooseExisting = true
                      this.chooseNew = false
                    }),
            window.location.href = "http://localhost:8080/reportForExamination"
          });
    },
    createNewDateTerm: function() {

      this.exam = {
        dateAndTime: this.date + "T" + this.time + ".00",
        duration: 30,
        price: 3000,
        cancelled: false,
        points: 20,
        patient: this.patient,
        pharmacy: this.pharmacyy,
        dermatologist: this.dermatologistt
      }

      this.axios
          .put('http://localhost:8091/examination/createNewExamination', this.exam, {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
          })
          .then( response => {
            this.available = response.data,
            this.chooseNew = true,
                this.chooseExisting = false
            if(this.available){
              alert("Examination scheduled!"),
              window.location.href = "http://localhost:8080/reportForExamination"
            }
            else {
              alert("You did not choose available term! Choose again!")
            }
          })
    },
    chooseDateAndTime: function (){
      this.chooseNew = true,
      this.chooseExisting = false
    },
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