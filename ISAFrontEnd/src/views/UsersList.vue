<template>
  <div class="container">
    <v-table>
      <tr>
        <v-text-field
            label="Enter Patient Name"
             hide-details="auto"
            v-model="fName"
            background-color="white"
        ></v-text-field>
         <td align="center"><v-btn
          color="primary"
          elevation="2"
          medium
          v-on:click= "searchPatient"
      >Search Patients</v-btn></td>
      </tr>
    </v-table>
    <br>
    <br>
    <v-simple-table>
      <template v-slot:default>
        <thead>
        <tr>
          <th :class="sortedClass('firstName')" class="text-left"
              @click="sortBy('firstName')">
            FirstName
          <th :class="sortedClass('lastName')" class="text-left"
              @click="sortBy('lastName')">
            LastName
          </th>
          <th :class="sortedClass('dateAndTime')" class="text-left"
              @click="sortBy('dateAndTime')">
            Appointment Date
          </th>
          <th class="text-left">
            SeeProfile
          </th>
          <th class="text-left">
            StartAppointment
          </th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="p in sortedItems" :key="p">
          <td>{{ p.firstName }}</td>
          <td>{{ p.lastName }}</td>
          <td>{{ p.dateAndTime }}</td>
          <td align="left">
            <v-btn
                color="primary"
                elevation="2"
                v-on:click = "seeProfile(p)"
                small
                >See profile</v-btn></td>
          <td align="left">
            <v-btn
                color="primary"
                elevation="2"
                v-on:click = "startAppointment(p)"
                small
                >Start Appointment</v-btn></td>
        </tr>
        </tbody>
      </template>
    </v-simple-table>
  </div>
</template>

<script>
export default {
  name: "UsersList",
  data: () => ({
      allpatients: [],
      mode: 'BROWSE',
      backup: [],
      paId : null,
      patient: {},
      employee : {},
      user: {},
      fName: '',
      sort: {
        key: '',
        isAsc: false
      }

  }),
  mounted() {

      if(localStorage.getItem("userType") == "DERMATOLOGIST"
        || localStorage.getItem("userType") == "PHARMACIST") {

        this.axios
            .get('http://localhost:8091/users/' + localStorage.getItem("userId"), {
              headers: {
                Authorization: 'Bearer ' + localStorage.getItem("token")
              }
            })
           .then(response => {
              this.employee = response.data

             const ne={
               fullName : '',
               employeeId : this.employee.userId
             }
              this.axios
                  .put("http://localhost:8091/users/searchUser", ne, {
                    headers: {
                      Authorization: 'Bearer ' + localStorage.getItem("token")
                    }
                  })
                  .then((resp) => this.allpatients = resp.data)
              })
        }
      else {
          window.location.href = "http://localhost:8080/logIn";
        }
  },
  methods: {
    searchPatient: function () {

      const ne={
        fullName : this.fName,
        employeeId : this.employee.userId
      }

      this.axios
          .put("http://localhost:8091/users/searchUser", ne, {
              headers: {
                Authorization: 'Bearer ' + localStorage.getItem("token")
              }
            })
          .then((resp) => (this.allpatients = resp.data))
    },
    sortedClass (key) {
      return this.sort.key === key ? `sorted ${this.sort.isAsc ? 'asc' : 'desc' }` : '';

    },
    sortBy (key) {
      this.sort.isAsc = this.sort.key === key ? !this.sort.isAsc : false;
      this.sort.key = key;
    },
    seeProfile: function(p) {
      localStorage.setItem("patientId", p.patientId);
      window.location.href = "http://localhost:8080/DermatologistPatientProfile";
    },
    startAppointment: function (p) {
      localStorage.setItem("patientId", p.patientId);
      if(localStorage.getItem("userType") == "DERMATOLOGIST"){
        window.location.href = "http://localhost:8080/reportForExamination";
      }
      else{
        window.location.href = "http://localhost:8080/reportForConsultation";
      }
    }
  },
  computed:{
    sortedItems () {
      const list = this.allpatients.slice();  // ソート時でdataの順序を書き換えないため
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
};
</script>

<style>
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