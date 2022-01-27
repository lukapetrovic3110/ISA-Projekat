<template>
  <div id = "editSupplier">

  <div class = "container">
    <h1 style = "text-align:center">Edit supplier profile</h1>
    <br/>
    <v-simple-table style = "margin:0 auto; padding: 20px; width: 100%">
    <template v-slot:default>
      <tr>
        <th><h3>Personal information</h3></th>
      </tr>
      <hr>
      <tr>
        <th>First Name</th>
        <td><input type = "text" v-model = "supplier.firstName"  /></td>
      </tr>
      <tr>
        <th>Last Name</th>
        <td><input type = "text" v-model = "supplier.lastName" /></td>
      </tr>
      <tr>
        <th>Phone Number</th>
        <td><input type = "text" v-model = "supplier.phoneNumber" /></td>
      </tr>
      <tr>
        <th>Country</th>
        <td><input type = "text" v-model = "supplier.address.country" /></td>
      </tr>
      <tr>
        <th>City</th>
        <td><input type = "text" v-model = "supplier.address.city" /></td>
      </tr>
      <tr>
        <th>Street</th>
        <td><input type = "text" v-model = "supplier.address.street" /></td>
      </tr>
      <tr>
        <th>Street Number</th>
        <td><input type = "text" v-model = "supplier.address.streetNumber" /></td>
      </tr>
      <tr>
        <th>E-mail</th>
        <td>{{supplier.email}}</td>
      </tr>
      <tr>
      <tr>
        <th>Password</th>
        <td><input type = "password" v-model = "supplier.password" /></td>
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
              v-on:click = "saveInformation"
          >SAVE</v-btn>
  </v-row>
  </div>
</template>

<script>

export default {
  name: "EditSupplierProfile",
  data: function(){
    return{
      mode: 'BROWSE',
      supplier: null
    }
  },
  mounted(){
    this.axios
        .get('http://localhost:8091/users/' + localStorage.getItem("userId"),{ 
          headers: {
                  Authorization: 'Bearer ' + localStorage.getItem("token")}
          
        })      
        .then(response => (this.supplier = response.data));
  },
  methods: {
    saveInformation : function(){
      this.axios
          .post("http://localhost:8091/users/update", this.supplier, 
          { 
          headers: {
                  Authorization: 'Bearer ' + localStorage.getItem("token")}
         })
         .then(
                  window.location.href = "http://localhost:8080/supplier");
    }
  }
}
</script>

<style scoped>

</style>
