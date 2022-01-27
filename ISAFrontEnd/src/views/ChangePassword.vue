<template>
  <div>
    <br>
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
  name: "ChangePassword",
  data: function () {
    return {
      oldPass: "",
      newPass: "",
      rewritePass: "",
      email: localStorage.getItem("email")
    }
  },
  methods:{
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
              router.push('patient');
            })
      }
    }
  }
}
</script>

<style scoped>
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