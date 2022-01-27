<template>
  <div id = "homePage">
    <div class = "container">

      <v-simple-table>
        <template v-slot:default>
          <thead>
          <tr>
            <th>
              Date and Time
            </th>
            <th>
              Penalty Type
            </th>
            <th>
              Pharmacy
            </th>
          </tr>
          </thead>
          <tbody>
          <tr
              v-for="p in user.penalties"
              :key="p"
          >
            <td>{{p.date}}</td>
            <td>{{ p.penaltyType}}</td>
            <td>{{ p.pharmacy.name}}</td>
          </tr>
          </tbody>
        </template>
      </v-simple-table>

    </div>
  </div>
</template>

<script>
export default {
  name: "MyPenalties",
  data: function () {
    return {
      user: {}
    }
  },
  mounted() {
    this.axios
        .get('http://localhost:8091/users/' + localStorage.getItem("userId"), {
          headers: {
            Authorization: 'Bearer ' + localStorage.getItem("token")
          }
        })
        .then(response => (this.user = response.data));
  },
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