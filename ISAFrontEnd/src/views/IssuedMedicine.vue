<template>
  <div id = "homePage">
    <div class = "container">
      <br>
      <v-simple-table>
        <template v-slot:default>
          <thead>
          <tr>
            <th class="text-left">
              Issued medicine
            </th>
            <th class="text-left">
              Type
            </th>
            <th class="text-left">
              Rating
            </th>
          </tr>
          </thead>
          <tbody>
          <tr
              v-for="m in medicine"
              :key="m"
          >
            <td>{{ m.name }}</td>
            <td>{{ m.type }}</td>
            <td>{{ m.rating }}</td>
          </tr>
          </tbody>
        </template>
      </v-simple-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "IssuedMedicine",
  data: function () {
    return {
    medicine: []
    }
  },
  mounted() {
    {
      this.axios
          .get('http://localhost:8091/ePrescription/issuedMedicine/' + localStorage.getItem("userId"), {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
          })
          .then(r => {
            this.medicine = r.data
          })
    }
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