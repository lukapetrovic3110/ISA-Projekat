<template>
 <div class="container">
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
  name: "WorkSchedulePharmacist",
  data: function() {
    return {
      selectedEvent: {},
      selectedElement: null,
      selectedOpen: false,
      patientName: null,
      patientLastName: null,
      dialog: false,
      timeInt : null,
      pharmacist: null,
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
    if(localStorage.getItem("userType") == "PHARMACIST"){
      this.axios
          .get('http://localhost:8091/users/' + localStorage.getItem("userId"), {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
          })
          .then(response => (
              this.pharmacist = response.data,
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
      //alert(this.timeInt.startDate.toString() + "   " + this.timeInt.endDate.toString())
       this.axios
          .put('http://localhost:8091/consultation/allForPharmacist/' + this.pharmacist.userId, this.timeInt, {
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
                name: "Consultation",
                start: first,
                startTime: this.timeApp,
                duration:this.consultations[i].duration,
                patient: this.consultations[i].patient,
                patientName: this.consultations[i].patient.firstName + " " + this.consultations[i].patient.lastName,
                end: second,
                color: this.colors[this.rnd(0, this.colors.length - 1)],
                timed: !allDay
              })
            }
            this.events = events
          });
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
      window.location.href = "http://localhost:8080/reportForConsultation";
    },
    viewDay ({ date }) {
      this.focus = date
      this.type = 'day'
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


</style>