<template>
  <div id = "homePage">
    <div class = "container">
      <br>
      <h3>Upcoming examinations</h3>
      <br>
      <v-simple-table>
        <template v-slot:default>
          <thead>
          <tr>
            <th>
              Date and Time
            </th>
            <th>
              Duration
            </th>
            <th>
              Pharmacy
            </th>
            <th>
              Address
            </th>
            <th>
              Dermatologist
            </th>
            <th :class="sortedClass('dermatologist')"
                @click="sortBy('dermatologist')">
              Dermatologist Rating
            </th>
            <th :class="sortedClass('price')"
                @click="sortBy('price')">
              Price
            </th>
          </tr>
          </thead>
          <tbody>
          <tr
              v-for="c in sortedItems"
              :key="c"
          >
            <td>{{c.dateAndTime}}</td>
            <td>{{ c.duration + " minutes"}}</td>
            <td>{{ c.pharmacy.name}}</td>
            <td>{{c.pharmacy.address.street + " " + c.pharmacy.address.streetNumber + ", " +
            c.pharmacy.address.city + ", " + c.pharmacy.address.country}}</td>
            <td>{{c.dermatologist.firstName + " " + c.dermatologist.lastName}}</td>
            <td>{{c.dermatologist.rating}}</td>
            <td>{{c.price + " rsd"}}</td>
            <td>
              <v-btn
                  color="secondary"
                  elevation="3"
                  x-small
                  v-on:click = "cancelExamination(c)"
              >Cancel examination</v-btn>
            </td>
          </tr>
          </tbody>
        </template>
      </v-simple-table>
        <br>
      <h3>Upcoming consultations</h3>
      <br>
      <v-simple-table>
        <template v-slot:default>
          <thead>
          <tr>
            <th>
              Date and Time
            </th>
            <th>
              Duration
            </th>
            <th>
              Pharmacy
            </th>
            <th>
              Address
            </th>
            <th>
              Pharmacist
            </th>
            <th :class="sortedClass('pharmacist')"
                @click="sortBy('pharmacist')">
              Pharmacist Rating
            </th>
            <th :class="sortedClass('price')"
                @click="sortBy('price')">
              Price
            </th>
          </tr>
          </thead>
          <tbody>
          <tr
              v-for="c in sortedItemsCons"
              :key="c"
          >
            <td>{{c.dateAndTime}}</td>
            <td>{{ c.duration + " minutes"}}</td>
            <td>{{ c.pharmacy.name}}</td>
            <td>{{c.pharmacy.address.street + " " + c.pharmacy.address.streetNumber + ", " +
            c.pharmacy.address.city + ", " + c.pharmacy.address.country}}</td>
            <td>{{c.pharmacist.firstName + " " + c.pharmacist.lastName}}</td>
            <td>{{c.pharmacist.rating}}</td>
            <td>{{c.price + " rsd"}}</td>
            <td>
              <v-btn
                  color="secondary"
                  elevation="3"
                  x-small
                  v-on:click = "cancelConsultation(c)"
              >Cancel consultation</v-btn>
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
  name: "UpcomingVisits",
  data: function () {
    return {
      token: localStorage.getItem("token"),
      sort: {
        key: '',
        isAsc: false
      },
      examinations: [],
      consultations:[],
      result: false
    }
  },
  mounted() {
    {
      this.axios
          .get('http://localhost:8091/examination/getByPatientId/' + localStorage.getItem("userId"), {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
          })
          .then(response => (this.examinations = response.data));

      this.axios
          .get('http://localhost:8091/consultation/getByPatientId/' + localStorage.getItem("userId"), {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
          })
          .then(response => (this.consultations = response.data));

    }
  },
  methods: {
    sortedClass(key) {
      return this.sort.key === key ? `sorted ${this.sort.isAsc ? 'asc' : 'desc'}` : '';

    },
    sortBy(key) {
      this.sort.isAsc = this.sort.key === key ? !this.sort.isAsc : false;
      this.sort.key = key;
    },
    cancelExamination(examination) {
      this.axios
          .put('http://localhost:8091/examination/cancel', examination, {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
          })
          .then(response => {
            this.result = response.data;
              if(this.result == false){
                alert("Unable to cancel examination! Examination is scheduled within next 24h.")
              }else {
                alert("Examination cancelled!"),
                    window.location.href = "http://localhost:8080/upcomingVisits"
              }
            });
    },
    cancelConsultation(consultation){
      this.axios
          .put('http://localhost:8091/consultation/cancel', consultation, {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
          })
          .then(response => {
            this.result = response.data;
            if(this.result == false){
              alert("Unable to cancel consultation! Consulation is scheduled within next 24h.")
            }else {
              alert("Consultation cancelled!"),
                  window.location.href = "http://localhost:8080/upcomingVisits"
            }
          });
    }
  },
  computed:{
    sortedItems () {
      const list = this.examinations.slice();
      if (this.sort.key !="") {
        list.sort((a, b) => {
          a = a[this.sort.key]
          b = b[this.sort.key]

          return (a === b ? 0 : a > b ? 1 : -1) * (this.sort.isAsc ? 1 : -1)
        });
      }

      return list;
    },
    sortedItemsCons () {
      const list = this.consultations.slice();
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