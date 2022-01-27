<template>
  <div id = "homePage">
    <div class = "container">
      <br>
      <v-simple-table>
        <template v-slot:default>
          <thead>
          <tr>
            <th>
              Subscribed to
            </th>
            <th>
              Rating
            </th>
            <th>
              Address
            </th>
          </tr>
          </thead>
          <tbody>
          <tr
              v-for="p in pharmacies"
              :key="p"
          >
            <td>{{ p.name }}</td>
            <td>{{ p.rating }}</td>
            <td>{{p.address.street + " " + p.address.streetNumber + ", " + p.address.city + ", " + p.address.country}}</td>
          </tr>
          </tbody>
        </template>
      </v-simple-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "MySubscriptions",
  data: function () {
    return {
      pharmacies: []
    }
  },
  mounted(){

    this.axios
        .get('http://localhost:8091/users/subscriptions/' + localStorage.getItem("userId"), {
          headers: {
            Authorization: 'Bearer ' + localStorage.getItem("token")
          }
        })
        .then(response => (this.pharmacies = response.data));
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