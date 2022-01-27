<template>
  <div id = "searchMedicine">
    <br>
    <div class = "container">
      <v-simple-table>
        <template v-slot:default>
          <thead>
          <tr>
            <th class="text-left">
              From
            </th>
            <th class="text-left">
              To
            </th>
            <th class="text-left">
            </th>
          </tr>
          </thead>
          <tbody>
          <tr
              v-for="c in complaints"
              :key="c"
          >
            <td>{{ c.patient.firstName + " " + c.patient.lastName }}</td>
            <td>{{ c.pharmacyEmployee.firstName + " " + c.pharmacyEmployee.lastName }}</td>
            <td>
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
                            >Complaint</v-btn>
                          </template>
                          <template v-slot:default="dialog">
                            <v-card>
                              <v-toolbar
                                  color="primary"
                                  dark
                              >Complaint</v-toolbar>
                              <v-simple-table>
                                <template v-slot:default>

                          <v-textarea
                                background-color="white"
                                filled
                                name="input-7-4"
                                label="Complaint:"
                                v-model = "c.textEmp"
                          ></v-textarea>
                 <br>
                 <hr>
                          <v-textarea
                                background-color="white"
                                filled
                                name="input-7-4"
                                label="Answer on complaint:"
                                v-model = "textAnswer"
                          ></v-textarea>
                    <br>
                  </template>
                </v-simple-table>
                <v-btn
                    width="300"
                    text
                    @click="dialog.value = false"
                >Close</v-btn>
                <v-btn
                    width="300"
                    text
                    @click="answerOnComplaintForEmployee(c, dialog)"

                >Answer</v-btn>
                </v-card>
                </template>
                </v-dialog>
                </v-col>
        </td>
          </tr>
          </tbody>
        </template>
      </v-simple-table>
      <br>
      <br>
            <v-simple-table>
        <template v-slot:default>
          <thead>
          <tr>
            <th class="text-left">
              From
            </th>
            <th class="text-left">
              To
            </th>
            <th class="text-left">
            </th>
          </tr>
          </thead>
          <tbody>
          <tr
              v-for="cp in complaintsPharmacy"
              :key="cp"
          >
            <td>{{ cp.patient.firstName + " " + cp.patient.lastName }}</td>
            <td>{{ cp.pharmacy.name }}</td>
            <td>
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
                            >Complaint</v-btn>
                          </template>
                          <template v-slot:default="dialog">
                            <v-card>
                              <v-toolbar
                                  color="primary"
                                  dark
                              >Complaint</v-toolbar>
                              <v-simple-table>
                                <template v-slot:default>

                          <v-textarea
                                background-color="white"
                                filled
                                name="input-7-4"
                                label="Complaint:"
                                v-model = "cp.textPh"
                          ></v-textarea>
                 <br>
                 <hr>
                          <v-textarea
                                background-color="white"
                                filled
                                name="input-7-4"
                                label="Answer on complaint:"
                                v-model = "textAnswer"
                          ></v-textarea>
                    <br>
                  </template>
                </v-simple-table>
                <v-btn
                    width="300"
                    text
                    @click="dialog.value = false"
                >Close</v-btn>
                <v-btn
                    width="300"
                    text
                    @click="answerOnComplaintForPharmacy(cp, dialog)"
                >Answer</v-btn>
                </v-card>
                </template>
                </v-dialog>
                </v-col>
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
  name: "Complaints",
  data: function () {
    return {
      dialog: false,
      complaints: [],
      complaintsPharmacy: [],
      textAnswer: "",
      textAnswerPh: "",
    }
  },
  mounted(){
    this.axios
        .get('http://localhost:8091/complaint/getComplaintsEmployee', {
           headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
        })
        .then(response => (this.complaints = response.data)),
    this.axios
        .get('http://localhost:8091/complaint/getComplaintsPharmacy', {
           headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
        })
        .then(response => (this.complaintsPharmacy = response.data));
  },
  methods: {
    answerOnComplaintForEmployee: function(c, dialog){
      dialog.value = false
      this.axios
          .post("http://localhost:8091/complaint/answerOnComplaintForEmployee",{
                textAnswer: this.textAnswer,
                systemAdminId: localStorage.getItem("userId"),
                complaintEmployeeId: c.compEmpId, 
                }, {
                    headers: {
                         Authorization: 'Bearer ' + localStorage.getItem("token")
                    }})
          .then(
                alert("Your answer is sent!")
          );
    },
     answerOnComplaintForPharmacy: function(cp, dialog){
      dialog.value = false
      this.axios
          .post("http://localhost:8091/complaint/answerOnComplaintForPharmacy",{
                textAnswer: this.textAnswer,
                systemAdminId: localStorage.getItem("userId"),
                complaintPharmacyId: cp.compPhId, 
                }, {
                    headers: {
                         Authorization: 'Bearer ' + localStorage.getItem("token")
                    }})
          .then(
                alert("Your answer is sent!")
          );
    }
  },
  computed:{
    isLogged: function (){
      if (this.token == ""){
        return false
      }else{
        return true
      }
    },
    notFilled: function () {
      if (this.searchMedicine.trim() == ""){
        return false
      }
      else {
        return true
      }
    }
  }
}
</script>

<style scoped>
#search{
  background-color: white;
}
#searchButton{
  color: white;
  border: white;
  border-width: 10px;
}

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
input[type=radio]{
  border-radius: 10px;
  box-shadow: inset 0 1px 1px hsla(0,0%,100%,.8),
  0 0 0 1px hsla(0,0%,0%,.6),
  0 2px 3px hsla(0,0%,0%,.6),
  0 4px 3px hsla(0,0%,0%,.4),
  0 6px 6px hsla(0,0%,0%,.2),
  0 10px 6px hsla(0,0%,0%,.2);
  cursor: pointer;
  display: inline-block;
  height: 15px;
  margin-right: 15px;
  position: relative;
  width: 15px;
  -webkit-appearance: none;
}

input[type="radio"]:after {
  border-radius: 25px;
  box-shadow: inset 0 0 0 1px hsla(0,0%,0%,.4),
  0 1px 1px hsla(0,0%,100%,.8);
  content: '';
  display: block;
  height: 7px;
  left: 4px;
  position: relative;
  top: 4px;
  width: 7px;
}
input[type="radio"]:checked:after {
  background-color: #444;
  box-shadow: inset 0 0 0 1px hsla(0,0%,0%,.4),
  inset 0 2px 2px hsla(0,0%,100%,.4),
  0 1px 1px hsla(0,0%,100%,.8),
  0 0 2px 2px hsla(0,70%,70%,.4);
}

</style>