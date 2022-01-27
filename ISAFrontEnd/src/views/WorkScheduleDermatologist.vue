<template>
  <div class="container">
    <v-simple-table>
      <v-col cols="auto">
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
                v-on:click="findPharmacies"
            >Choose Pharmacy</v-btn>
          </template>
          <template v-slot:default="dialog">
            <v-card>
              <v-toolbar
                  color="primary"
                  dark
              >Choose Pharmacy</v-toolbar>
              <v-simple-table>
                <template v-slot:default>
                  <tbody>
                  <tr>
                    <th class="text-center" width="250">
                      Name
                    </th>
                    <th class="text-center">
                      Choose
                    </th>
                  </tr>
                  <tr
                      v-for="p in pharmacies"
                      :key="p"
                  >
                    <td align="center">{{ p.name }}</td>
                    <td align="center">
                      <v-btn
                          color="primary"
                          elevation="2"
                          small
                          @click="dialog.value = false"
                          v-on:click="choosePharmacy(p)">
                        Choose
                      </v-btn>
                    </td>
                  </tr>
                  </tbody>
                </template>
              </v-simple-table>
              <br>
              <v-btn
                  width="300"
                  text
                  @click="dialog.value = false"
              >Close</v-btn>
            </v-card>
          </template>
        </v-dialog>
      </v-col>
    </v-simple-table>
    <br>
    <v-sheet
        tile
        height="54"
        class="d-flex"
    >
      <v-btn
          icon
          class="ma-2"
          @click="$refs.calendar.prev()"
      >
        <v-icon>mdi-chevron-left</v-icon>
      </v-btn>
      <v-select
          v-model="type"
          :items="types"
          dense
          outlined
          hide-details
          class="ma-2"
          label="type"
      ></v-select>
      <v-spacer></v-spacer>
      <v-btn
          icon
          class="ma-2"
          @click="$refs.calendar.next()"
      >
        <v-icon>mdi-chevron-right</v-icon>
      </v-btn>
    </v-sheet>
    <v-sheet height="600">
      <v-calendar
          ref="calendar"
          v-model="value"
          :weekdays="weekday"
          :type="type"
          :events="events"
          :event-overlap-mode="mode"
          :event-overlap-threshold="30"
          :event-color="getEventColor"
          @change="getEvents"
          @click:event="showEvent"
          @click:more="viewDay"
          @click:date="viewDay"
      >
      </v-calendar>
    </v-sheet>
    <template>
      <v-col cols="auto">
        <v-menu
            v-model="selectedOpen"
            :close-on-content-click="false"
            :activator="selectedElement"
            offset-x
        >
          <v-card
              color="grey lighten-4"
              min-width="350px"
              flat
          >
            <v-toolbar :color="selectedEvent.color"></v-toolbar>
            <v-toolbar-title class="text-center" v-html="selectedEvent.name"></v-toolbar-title>
            <v-card-actions>
              <v-card-text>
                <v-simple-table>
                  <tr><td>Date And Time: </td><td>{{selectedEvent.startTime}}</td></tr>
                  <tr><td>Duration: </td><td>{{selectedEvent.duration}}</td></tr>
                  <tr><td>Patient: </td><td>{{selectedEvent.patientName}}</td></tr>
                  <tr><td>Pharmacy: </td><td>{{selectedEvent.pharmacyName}}</td></tr>
                </v-simple-table>
              </v-card-text>
              <v-btn
                  text
                  color="secondary"
                  v-on:click="startApp(selectedEvent)"
              >Start
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-menu>
      </v-col>
    </template>
  </div>
</template>

<script>
export default {
  name: "WorkScheduleDermatologist",
  data: function() {
    return {
      pharmacies: [],
      selectedPh: null,
      selectedEvent: {},
      selectedElement: null,
      selectedOpen: false,
      patientName: null,
      patientLastName: null,
      dialog: false,
      timeInt : null,
      dermatologist: null,
      year : null,
      month : null,
      day : null,
      hours : null,
      minutes : null,
      minutesEnd: null,
      startTime: null,
      endTime: null,
      consultations: [],
      type: 'month',
      types: ['month', 'week', 'day'],
      mode: 'stack',
      weekday: [1, 2, 3, 4, 5, 6, 0],
      value: '',
      events: [],
      notYear: false,
      colors: ['blue', 'indigo', 'deep-purple', 'cyan', 'green', 'orange', 'grey darken-1'],
      timeApp: null,
      appTime: null
    }
  },
  mounted() {
    if(localStorage.getItem("userType") == "DERMATOLOGIST"){

      this.axios
          .get('http://localhost:8091/users/' + localStorage.getItem("userId"), {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
          })
          .then(response => (
              this.dermatologist = response.data,
                  this.getEvents));
    } else{
      window.location.href = "http://localhost:8080/logIn";
    }
  },
  methods: {
    getEvents: function ({start, end}) {

      const min = new Date(`${start.date}T00:00:00`)
      const max = new Date(`${end.date}T23:59:59`)

      this.timeInt = {
        startDate : min,
        endDate : max
      }

      if(this.selectedPh.pharmacyId != null){
        const dfp={
          timeInterval : this.timeInt,
          pharmacyId : this.selectedPh.pharmacyId
        }

      //alert(this.timeInt.startDate.toString() + "   " + this.timeInt.endDate.toString())
      this.axios
          .put('http://localhost:8091/examination/allForDermatologist/' + this.dermatologist.userId, dfp, {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
          })
          .then( response => {
            this.consultations = response.data;
            const events = []

            for(let i = 0; i < this.consultations.length; i++){
              const allDay = this.rnd(0, 3) === 0
              const first = new Date(this.consultations[i].dateAndTime - (this.consultations[i].dateAndTime % 900000))
              const secondTimestamp = 30 * 60 * 1000
              const second = new Date(first.getTime() + secondTimestamp)

              this.appTime =  new Date(this.consultations[i].dateAndTime)
              this.year = this.appTime.getFullYear()
              this.month = this.appTime.getMonth() + 1
              this.day = this.appTime.getDate()
              this.hours = this.appTime.getHours()
              this.minutes = this.appTime.getMinutes()

              this.timeApp =  this.year + "-" + this.month + "-" + this.day +
                  " " + this.hours + ":" + this.minutes

              events.push({
                name: "Examination",
                start: first,
                startTime: this.timeApp,
                duration:this.consultations[i].duration,
                patient: this.consultations[i].patient,
                patientName: this.consultations[i].patient.firstName + " " + this.consultations[i].patient.lastName,
                pharmacyName: this.consultations[i].pharmacy.name,
                end: second,
                color: this.colors[this.rnd(0, this.colors.length - 1)],
                timed: !allDay
              })
            }
            this.events = events
          });
      }
    },
    getEventColor: function(event) {
      return event.color
    },
    rnd (a, b) {
      return Math.floor((b - a + 1) * Math.random()) + a
    },
    showEvent ({ nativeEvent, event }) {
      const open = () => {
        this.selectedEvent = event
        this.selectedElement = nativeEvent.target
        requestAnimationFrame(() => requestAnimationFrame(() => this.selectedOpen = true))
      }
      if (this.selectedOpen) {
        this.selectedOpen = false
        requestAnimationFrame(() => requestAnimationFrame(() => open()))
      } else {
        open()
      }
      nativeEvent.stopPropagation()
    },
    startApp: function(event){
      localStorage.setItem("patientId", event.patient.userId)
      window.location.href = "http://localhost:8080/reportForExamination";
    },
    viewDay ({ date }) {
      this.focus = date
      this.type = 'day'
    },
    choosePharmacy: function (p){
      this.selectedPh = p
    },
    findPharmacies: function(){
      this.axios
          .get('http://localhost:8091/pharmacy/getAllPharmacyByDermatologist/' + this.dermatologist.userId, {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }})
          .then( response => {
            this.pharmacies = response.data
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