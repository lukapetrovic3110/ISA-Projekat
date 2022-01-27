<template>
  <div>

    <h1 style = "margin:0 auto; text-align:center;">Pharmacist profile</h1>
    <br>
    <div class = "container">
      <v-simple-table>
        <template v-slot:default>
          <tr>
            <th colspan="2" id="personal_info"><h3>Personal information</h3></th>
          </tr>
          <tr>
            <th>Name</th>
            <td><input type = "text" v-model = "pharmacist.firstName" /></td>
          </tr>
          <tr>
            <th>Last Name</th>
            <td><input type = "text" v-model = "pharmacist.lastName" /></td>
          </tr>
          <tr>
            <th>Phone Number</th>
            <td><input type = "text" v-model = "pharmacist.phoneNumber" /></td>
          </tr>
          <tr>
            <th>Country</th>
            <td><input type = "text" v-model = "pharmacist.address.country" /></td>
          </tr>
          <tr>
            <th>City</th>
            <td><input type = "text" v-model = "pharmacist.address.city" /></td>
          </tr>
          <tr>
            <th>Street</th>
            <td><input type = "text" v-model = "pharmacist.address.street" /></td>
          </tr>
          <tr>
            <th>Home Number</th>
            <td><input type = "text" v-model = "pharmacist.address.streetNumber" /></td>
          </tr>
          <tr>
            <th>E-mail</th>
            <td>{{pharmacist.email}}</td>
          </tr>
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
    <div class = "container">
      <v-simple-table>
        <template v-slot:default>
          <tr>
            <th colspan="2" id="change_password"><h3>Change Password</h3></th>
          </tr>
          <tr>
            <th>Current Password</th>
            <td><input type = "password" v-model = "oldPass" /></td>
          </tr>
          <tr>
            <th>New Password</th>
            <td><input type = "password" v-model = "newPass" /></td>
          </tr>
          <tr>
            <th>Rewrite New Password</th>
            <td><input type = "password" v-model = "rewritePass" /></td>
          </tr>
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
          v-on:click="savePassword()"
      >Save password</v-btn>
    </v-row>
  </div>
</template>

<script>

import router from "../router";

export default {
  name: "EditDermatologistInfo",
  data: function(){
    return{
      mode: 'BROWSE',
      pharmacist: null,
      pharmacist_id: null,
      oldPass: "",
      newPass: "",
      rewritePass: "",
      email: localStorage.getItem("email")
    }
  },
  mounted(){
    this.axios
        .get('http://localhost:8091/users/' + localStorage.getItem("userId"), {
          headers: {
            Authorization: 'Bearer ' + localStorage.getItem("token")
          }
        })
        .then(response => (this.pharmacist = response.data));
  },
  methods: {

    saveInformation : function() {
      this.mode = "BROWSE";

      this.axios
          .post("http://localhost:8091/users/update", this.pharmacist, {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
          })
          .then(resp => {
            console.log(resp.data);
            router.push('pharmacistProfile');
          })
    },
    savePassword: function (){
      this.mode = "BROWSE";

      if (this.newPass !== this.rewritePass){
        alert("Password must match!")
      } else{
        const passwordChanger={
          oldPassword: this.oldPass,
          newPassword: this.newPass,
          email: this.email,
          rewritePassword: this.rewritePass
        }

        this.axios
            .post("http://localhost:8091/auth/changeUserPassword", passwordChanger,{
              headers: {
                Authorization: 'Bearer ' + localStorage.getItem("token")
              }})
            .then(resp => {
              console.log(resp.data);
              router.push('pharmacistProfile');
            })
      }
    }
  }
}
</script>

<style scoped>
#personal_info{
  text-align: center;
}
#change_password{
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