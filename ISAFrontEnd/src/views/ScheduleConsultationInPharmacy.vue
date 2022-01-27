<template>
  <div id = "homePage">
    <div class = "container">
    <h2>{{this.pharmacyName}}</h2>
    <h2>Consultation</h2>
    <h3>{{"Date: " + this.date}}</h3>
    <h3>{{"Time: " + this.time}}</h3>
      <br>
      <v-simple-table>
        <template v-slot:default>
          <thead>
          <tr>
            <th>
              First Name
            </th>
            <th>
              Last Name
            </th>
            <th :class="sortedClass('rating')"
                @click="sortBy('rating')">
              Rating
            </th>
          </tr>
          </thead>
          <tbody>
          <tr
              v-for="p in sortedItems"
              :key="p"
          >
            <td>{{ p.firstName }}</td>
            <td>{{ p.lastName }}</td>
            <td>{{p.rating}}</td>
            <td>
              <v-btn
                  color="secondary"
                  elevation="3"
                  x-small
                  v-on:click = "scheduleConsultation(p)"
              >Schedule Consultation</v-btn>
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
  name: "ScheduleConsultationInPharmacy",
  data: function () {
    return {
      pharmacyName: localStorage.getItem("pharmacyName"),
      date: localStorage.getItem("date"),
      time: localStorage.getItem("time"),
      sort: {
        key: '',
        isAsc: false
      },
      pharmacyId: localStorage.getItem("pharmacy"),
      pharmacists: [],
      conDTO : [{
        date: '',
        time: '',
        pharmacyId : '',
        pharmacist: []
      }],
      pharm : []
    }
  },
  mounted() {
    {
          this.axios
              .put('http://localhost:8091/workSchedulePharmacist/getAvailablePharmacistsInPharmacy' ,{date:this.date,
              time:this.time, pharmacyId:this.pharmacyId}, {
                headers: {
                  Authorization: 'Bearer ' + localStorage.getItem("token")
                }
              })
              .then(response => (this.pharmacists = response.data));


      this.axios
          .get('http://localhost:8091/users/' + localStorage.getItem("userId"), {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
          })
          .then(response => (this.patient = response.data));
    }
  },
  methods: {
    scheduleConsultation(ph){
      this.pharm = ph

      this.conDTO = {
        date:this.date,
        time:this.time,
        pharmacyId:this.pharmacyId,
        pharmacist: this.pharm
      }
      const request={
        dto : this.conDTO,
        patientId : this.patient.userId
      }

      if(this.patient.penalty <3) {
        this.axios
            .post('http://localhost:8091/consultation/create', request, {
              headers: {
                Authorization: 'Bearer ' + localStorage.getItem("token")
              }
            })
            .then(window.location.href = "http://localhost:8080/upcomingVisits");
      }else{
        alert("You can't schedule a consultation because you have 3 or more penalties. This option" +
            " will be available next month")
      }
    },
    sortedClass(key) {
      return this.sort.key === key ? `sorted ${this.sort.isAsc ? 'asc' : 'desc'}` : '';

    },
    sortBy(key) {
      this.sort.isAsc = this.sort.key === key ? !this.sort.isAsc : false;
      this.sort.key = key;
    }
  },
    computed:{
      sortedItems () {
        const list = this.pharmacists.slice();  // ソート時でdataの順序を書き換えないため
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