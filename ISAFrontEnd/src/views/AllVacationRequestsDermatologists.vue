<template>
  <div>
    <h1 id="requestVacationCaption">All vacation requests from dermatologists</h1>
    <v-card id="allRequestVacationCard" justify-center>
    <div>
      <v-data-table
        :headers="headers"
        :items="vacatoionList"
        sort-by="vacationId"
        class="elevation-1"
      >
        <template v-slot:top>
          <v-toolbar dense dark color="light-blue darken-2">
            <v-spacer></v-spacer>
            <v-toolbar-title class="text-center"
              >All vacation requests</v-toolbar-title
            >
            <v-spacer></v-spacer>
          </v-toolbar>
        </template>
      </v-data-table>
    </div>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "AllVacationRequestsDermatologists",
  data: () => ({
    vacatoionList: [],
    headers: [
      {
        text: "Vacation ID",
        value: "vacationId",
        align: "center",
        sortable: true,
      },
      { text: "Dermatologist ID", value: "dermatologistId", align: "center" },
      { text: "First name", value: "firstName", align: "center" },
      { text: "Last name", value: "lastName", align: "center" },
      { text: "E-mail", value: "email", align: "center" },
      { text: "Start date", value: "startDate", align: "center" },
      { text: "End date", value: "endDate", align: "center" },
      { text: "Status", value: "status", align: "center", sortable: false },
    ],
  }),
  mounted() {
    this.initialize();
  },
  methods: {
    initialize() {
      this.axios
        .get("http://localhost:8091/dermatologistVacation/all", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => {
          console.log(response.data);
          this.vacatoionList = response.data;
        });
    },
  },
};
</script>

<style scoped>
#requestVacationCaption {
  margin-top: 2%;
  margin-bottom: 2%;
  color: rgb(2, 2, 117);
  text-align: center;
  font-weight: bold;
}
#allRequestVacationCard {
  width: 80%;
  text-align: center;
  margin: auto;
}
</style>
 