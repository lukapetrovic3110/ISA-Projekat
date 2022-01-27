<template>
  <div>
    <h1 id="changePasswordCaption">Change Password First Login</h1>
    <br/>
    <div class = "container">
      <v-simple-table>
        <template v-slot:default>
          <tr>
            <th>Email:</th>
            <td><input type = "text" v-model = "email" disabled/></td>
          </tr>
          <tr>
            <th>Old Password:</th>
            <input type="password" class="form-control form-control-lg" required v-model="oldPass"/>
          </tr>
          <tr>
            <th>New Password:</th>
            <td><input type = "password" v-model = "newPass" /></td>
          </tr>
          <tr>
            <th>Rewrite New Password:</th>
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
          v-on:click="saveInformation()"
      >Save</v-btn>
    </v-row>
  </div>
</template>

<script>

export default {
  name: "ChangePasswordFirstLogin",
  data: function () {
    return {
      mode: 'BROWSE',
      oldPass: "",
      newPass: "",
      rewritePass: "",
      email: localStorage.getItem("email"),
    }
  },

  methods: {
    saveInformation : function() {
      this.mode = "BROWSE";

      if (this.oldPass == '' && this.newPass == '' && this.rewritePassword == ''){
        alert("Fill all the required fields!")
      }

      if (this.oldPass === this.newPass){
        alert("Password can not be same as old!")
      }

      if (this.rewritePass !== this.newPass){
        alert("New password and rewrite password must match!")
      }

      const passwordChanger={
        oldPassword: this.oldPass,
        newPassword: this.newPass,
        email: this.email,
        rewritePassword: this.rewritePass
    }

      this.axios
        .post("http://localhost:8091/auth/firstLogin", passwordChanger,{
        headers: {
          Authorization: 'Bearer ' + localStorage.getItem("token")
        }})
        .then(resp => {
          alert("Successfully changed password!")
          console.log(resp.data);
          if (localStorage.getItem("userType") == "DERMATOLOGIST") {
            window.location.href = "http://localhost:8080/homePageDermatologist";
          } else if (localStorage.getItem("userType")  == "PHARMACIST") {
            window.location.href = "http://localhost:8080/homePagePharmacist";
          } else if (localStorage.getItem("userType") == "PH_ADMINISTRATOR") {
            window.location.href = "http://localhost:8080/homePagePharmacyAdmin";
          } else if (localStorage.getItem("userType") == "SYS_ADMINISTRATOR") {
            window.location.href = "http://localhost:8080/homePageSystemAdmin";
          }else
            window.location.href = "http://localhost:8080/NavigationBar";
        });
    }
  }
}
</script>

<style scoped>
#changePasswordCaption {
  margin-top: 2%;
  margin-bottom: 2%;
  text-align: center;
  color: rgb(2, 2, 117);
  text-align: center;
  font-weight: bold;
}

#personal_info{
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