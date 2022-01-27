<template>
  <div id = "systemAdminProfile">

  <div class = "container">
    <h1 style = "text-align:center" >System Admin Profile</h1>
    <br/>
    <v-simple-table style = "margin:0 auto; padding: 20px; width: 100%">
    <template v-slot:default>
      <tr>
        <th><h3>Personal information</h3></th>
      </tr>
      <hr>
      <tr>
        <th>First Name</th>
        <td><input type = "text" v-model = "systemAdmin.firstName" disabled /></td>
      </tr>
      <br>
      <tr>
        <th>Last Name</th>
        <td><input type = "text" v-model = "systemAdmin.lastName" disabled/></td>
      </tr>
      <br>
      <tr>
        <th>Phone Number</th>
        <td><input type = "text" v-model = "systemAdmin.phoneNumber" disabled/></td>
      </tr>
      <br>
      <tr>
        <th>Country</th>
        <td><input type = "text" v-model = "systemAdmin.address.country" disabled/></td>
      </tr>
      <br>
      <tr>
        <th>City</th>
        <td><input type = "text" v-model = "systemAdmin.address.city" disabled/></td>
      </tr>
      <br>
      <tr>
        <th>Street</th>
        <td><input type = "text" v-model = "systemAdmin.address.street" disabled/></td>
      </tr>
      <br>
      <tr>
        <th>Street Number</th>
        <td><input type = "text" v-model = "systemAdmin.address.streetNumber" disabled/></td>
      </tr>
      <br>
      <tr>
        <th>E-mail</th>
        <td>{{systemAdmin.email}}</td>
      </tr>
      <br>
      <tr>
      <tr>
        <th>Password</th>
        <td><input type = "password" v-model = "systemAdmin.password" disabled/></td>
      </tr>
      <br>
      </template>
    </v-simple-table>
    </div>
    <br>
 
  </div>
</template>

<script>

export default {
  name: "systemAdminProfile",
  data: function(){
    return{
      mode: 'BROWSE',
      systemAdmin: null
    }
  },
  mounted(){
    this.axios
        .get('http://localhost:8091/users/' + localStorage.getItem("userId"), {
          headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
          }
            })
        .then(response => (this.systemAdmin = response.data));
  }
}
</script>

<style scoped>

</style>
