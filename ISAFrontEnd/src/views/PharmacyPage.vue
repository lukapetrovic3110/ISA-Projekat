<template>
<div id = "homePage">
  <div class = "container">
  <div text-align= "center">
    <h3>{{this.pharmacyName}}
      <v-btn
        class="ma-2"
        :loading="loading"
        :disabled="loading"
        color="secondary"
        @click="loader = 'loading'"
        v-on:click="subscribeToSalesAndPromotion(pharmacyId)"
      >
        Subscribe
      </v-btn>
      </h3>
  </div>
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
        <td>{{c.dermatologist.firstName + " " + c.dermatologist.lastName}}</td>
        <td>{{c.dermatologist.rating}}</td>
        <td>{{c.price + " rsd"}}</td>
        <td>
          <v-btn
              color="secondary"
              elevation="3"
              x-small
              v-on:click = "scheduleExamination(c)"
          >Schedule examination</v-btn>
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
  name: "PharmacyPage",
  data: function () {
    return {
      pharmacyId : {},
      pharmacyName : localStorage.getItem("pharmacyName"),
      examinations : [],
      patient: null,
      pharmacies: null,
      sort: {
        key: '',
        isAsc: false
      }
      }
    },
mounted() {
  {
    this.pharmacyId = localStorage.getItem("pharmacy"),
    this.pharmacyName = localStorage.getItem("pharmacyName"),
        this.pharmacyName = localStorage.getItem("pharmacyName"),
        this.axios
            .get('http://localhost:8091/users/' + localStorage.getItem("userId"), {
              headers: {
                Authorization: 'Bearer ' + localStorage.getItem("token")
              }
            })
            .then(response => (this.patient = response.data));
        this.axios
            .get('http://localhost:8091/examination/getByPharmacy/' + this.pharmacyId, {
              headers: {
                Authorization: 'Bearer ' + localStorage.getItem("token")
              }
            })
            .then(response => (this.examinations = response.data));

  }
},
  methods: {
    sortedClass (key) {
      return this.sort.key === key ? `sorted ${this.sort.isAsc ? 'asc' : 'desc' }` : '';

    },
    sortBy (key) {
      this.sort.isAsc = this.sort.key === key ? !this.sort.isAsc : false;
      this.sort.key = key;
    },
    scheduleExamination(examination){
      examination.patient = this.patient
      this.axios
          .put('http://localhost:8091/examination/schedule', examination, {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
          })
          .then(
                  alert("Examination scheduled!"),
              window.location.href = "http://localhost:8080/upcomingVisits"
          );
       if(this.patient.penalty < 3) {
        this.axios
            .put('http://localhost:8091/examination/schedule', examination, {
              headers: {
                Authorization: 'Bearer ' + localStorage.getItem("token")
              }
            })
            .then(
                alert("Examination scheduled!"),
                window.location.href = "http://localhost:8080/upcomingVisits"
            );
      }else{
        alert("You can schedule an examination because you have 3 or more penalties. This option" +
            " will be available next month")
      }
    },
     subscribeToSalesAndPromotion(pharmacyId){
      pharmacyId = localStorage.getItem("pharmacy")
      this.axios
        .post('http://localhost:8091/promotions/subscribeToPharmacy/' + pharmacyId, {}, {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
          })
          .then(r => {
                  this.pharmacy = r.data
                  alert("Successfully subscribed to " + localStorage.getItem("pharmacyName") + " pharmacy!")
          })
          .catch(() => {
                  alert("You are already subscribed!")
          });
     }
  },
  computed: {
    sortedItems () {
      const list = this.examinations.slice();  // ソート時でdataの順序を書き換えないため
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