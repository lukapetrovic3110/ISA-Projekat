<template>
  <div>
    <div style = "background-color: #E0E8F0;; overflow: hidden;">
      <a href="/workSchedulePharmacist" style = "float: left; color: #333; text-align:center; padding: 14px 48px; text-decoration:none; font-size: 17px;">Work Calendar</a>
    </div>
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
            <td><input type = "text" v-model = "pharmacist.firstName" disabled/></td>
          </tr>
          <tr>
            <th>Last Name</th>
            <td><input type = "text" v-model = "pharmacist.lastName" disabled/></td>
          </tr>
          <tr>
            <th>Phone Number</th>
            <td><input type = "text" v-model = "pharmacist.phoneNumber" disabled/></td>
          </tr>
           <tr>
             <th>Country</th>
             <td><input type = "text" v-model = "pharmacist.address.country" disabled/></td>
           </tr>
           <tr>
             <th>City</th>
             <td><input type = "text" v-model = "pharmacist.address.city" disabled/></td>
           </tr>
           <tr>
             <th>Street</th>
             <td><input type = "text" v-model = "pharmacist.address.street" disabled/></td>
           </tr>
           <tr>
             <th>Home Number</th>
             <td><input type = "text" v-model = "pharmacist.address.streetNumber" disabled/></td>
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
          medium>
        <router-link to="/editPharmacistInfo" tag="button">Edit</router-link>
      </v-btn>
    </v-row>
  </div>
</template>

<script>
export default {
  name: "PharmacistProfile",
  data: function(){
    return{
      mode: 'BROWSE',
      pharmacist: null,
      medicine: []
    }
  },
  mounted(){
    console.log(this.role)
    console.log(localStorage.getItem("userId"))

    this.axios
        .get('http://localhost:8091/users/' + localStorage.getItem("userId"), {
          headers: {
            Authorization: 'Bearer ' + localStorage.getItem("token")
          }
        })
        .then(response => (this.pharmacist = response.data));
  }
}
</script>

<style scoped>
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