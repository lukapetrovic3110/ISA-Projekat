<template>
  <div>
    <br>
    <div class = "container">
      <v-simple-table>
        <template v-slot:default>
          <tr>
            <th colspan="2" id="personal_info"><h3>Edit personal information</h3></th>
          </tr>
          <tr>
            <th>Name</th>
            <td><input type = "text" v-model = "patient.firstName" /></td>
          </tr>
          <tr>
            <th>Last Name</th>
            <td><input type = "text" v-model = "patient.lastName" /></td>
          </tr>
          <tr>
            <th>Phone Number</th>
            <td><input type = "text" v-model = "patient.phoneNumber" /></td>
          </tr>
          <tr>
            <th>Country</th>
            <td><input type = "text" v-model = "patient.address.country" /></td>
          </tr>
          <tr>
            <th>City</th>
            <td><input type = "text" v-model = "patient.address.city" /></td>
          </tr>
          <tr>
            <th>Street</th>
            <td><input type = "text" v-model = "patient.address.street" /></td>
          </tr>
          <tr>
            <th>Home Number</th>
            <td><input type = "text" v-model = "patient.address.streetNumber" /></td>
          </tr>
          <tr>
            <th>E-mail</th>
            <td>{{patient.email}}</td>
          </tr>
        </template>
      </v-simple-table>
    </div>
    <br>

    <div class = "container">
      <v-simple-table>
        <template v-slot:default>
          <thead>
          <tr>
            <th colspan="2" id="loyalty"><h3>Loyalty card</h3></th>
          </tr>
          <tr>
            <th class="text-left">
              Loyalty points
            </th>
            <th class="text-left">
              Loyalty category
            </th>
          </tr>
          </thead>
          <tbody>
          <tr style="align-content: center">
            <td>{{loyalty_card.points}}</td>
            <td>{{loyalty_card.loyaltyCategory}}</td>
          </tr>
          </tbody>
        </template>
      </v-simple-table>
    </div>

    <div class = "container">
      <v-simple-table>
        <template v-slot:default>
          <thead>
          <tr>
            <th id="allergies">
              <h3>Allergies to medicine</h3>
            </th>
          </tr>
          </thead>
          <tbody>
          <tr
              v-for="m in patient.allergy.medicine"
              :key="m"
          >
            <td>{{ m.name }}</td>
          </tr>
          </tbody>
        </template>
      </v-simple-table>
    </div>
    <br>

    <div class = "container">
      <v-simple-table>
        <template v-slot:default>
          <thead>
          <tr>
            <th class="text-left">
              Medicine
            </th>
          </tr>
          </thead>
          <tbody>
          <tr
              v-for="med in medicine"
              :key="med"
          >
            <td>{{ med.name }}</td>
            <td>
              <v-btn
                  color="secondary"
                  elevation="3"
                  x-small
                  v-on:click = "addAllergy(med)"
              >Add medicine to allergy</v-btn>
            </td>
          </tr>
          </tbody>
        </template>
      </v-simple-table>
    </div>
    <br>
    <v-row
        align="center"
        justify="space-around">
      <v-btn
          color="primary"
          elevation="3"
          medium
          v-on:click="saveInformation()"
      >Save</v-btn>
    </v-row>
  </div>
</template>

<script>
import router from "../router";

export default {
  name: "EditPatientInfo",
  data: function(){
    return{
      mode: 'BROWSE',
      patient: null,
      loyalty_card: null,
      medicine: [],
      patient_id: null
    }
  },
  mounted(){
    this.axios
        .get('http://localhost:8091/users/' + localStorage.getItem("userId"), {
          headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
        })
        .then(response => (this.patient = response.data,
            this.axios
                .put('http://localhost:8091/medicine/forAllergies', this.patient.allergy, {
                  headers: {
                    Authorization: 'Bearer ' + localStorage.getItem("token")
                  }})
                .then(response => (this.medicine = response.data)
        )));
    this.axios
        .get('http://localhost:8091/loyaltyCard/user/' + localStorage.getItem("userId"), {
          headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
        })
        .then(response => (this.loyalty_card = response.data));

  },
  methods: {

    saveInformation : function(){
      this.mode = "BROWSE";

      this.axios
          .post("http://localhost:8091/users/update", this.patient, {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
          });

      this.axios
          .put('http://localhost:8091/medicine/forAllergies', this.patient.allergy, {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
          })
          .then(router.push('patient'));
    },
    addAllergy :function(med){
      this.patient_id = this.patient.userId;
      this.axios
          .put("http://localhost:8091/allergy/" + this.patient_id + "/add", med, {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
          })
          .then((response) =>{
            this.patient.allergy.medicine = response.data;
            this.axios
                .put('http://localhost:8091/medicine/forAllergies', this.patient.allergy, {
                  headers: {
                      Authorization: 'Bearer ' + localStorage.getItem("token")
                }
                })
                .then(response => (this.medicine = response.data));
    });
    }
  }
}
</script>

<style scoped>
#personal_info,#loyalty,#allergies{
  text-align: center;
}
th{
  text-align: justify;
  padding: 7px;
}
input[type = text]{
  text-align: justify;
}
input[type = password]{
  text-align: justify;
}
</style>