<template>
  <div>
    <h1 id="requestVacationCaption">Vacation requests from pharmacists</h1>
    <v-card id="requestVacationCard" justify-center>
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
              >Pending vacation requests</v-toolbar-title
            >
            <v-spacer></v-spacer>

            <v-dialog v-model="dialogAcceptRequest" max-width="60%">
              <v-card>
                <v-spacer></v-spacer>
                <v-card-title class="text-h4 justify-center"
                  >Are you sure you want to accept this vacation request
                  ?</v-card-title
                >
                <v-card-actions>
                  <v-spacer></v-spacer>

                  <v-btn color="green" text @click="acceptRequest"
                    >Accept</v-btn
                  >
                  <v-spacer></v-spacer>

                  <v-btn color="red" text @click="closeAcceptRequest"
                    >Cancel</v-btn
                  >

                  <v-spacer></v-spacer>
                </v-card-actions>
              </v-card>
            </v-dialog>

            <v-dialog v-model="dialogDeclineRequest" max-width="60%">
              <v-card>
                <v-spacer></v-spacer>
                <v-card-title class="text-h4 justify-center"
                  >Enter an explanation</v-card-title
                >
                <v-card-text>
                  <v-spacer></v-spacer>
                  <v-text-field
                    class="ml-5 mr-5"
                    label="Explanation"
                    v-model="explanation"
                    color="blue"
                    type="text"
                  />
                  <v-spacer></v-spacer>
                </v-card-text>

                <v-spacer></v-spacer>

                <v-card-actions>
                  <v-spacer></v-spacer>

                  <v-btn color="green" text @click="declineRequest"
                    >Decline</v-btn
                  >
                  <v-spacer></v-spacer>

                  <v-btn color="red" text @click="closeDeclineRequest"
                    >Cancel</v-btn
                  >

                  <v-spacer></v-spacer>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-toolbar>
        </template>
        <template v-slot:[`item.actions`]="{ item }">
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="green" text @click="acceptVacation(item)"> ACCEPT </v-btn>
            <v-spacer></v-spacer>
            <v-btn color="red" text @click="declineVacation(item)"> DECLINE </v-btn>
            <v-spacer></v-spacer>
          </v-card-actions>
        </template>
      </v-data-table>
    </div>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "RequestsVacationPharmacists",
  data: () => ({
    vacatoionList: [],
    dialogDeclineRequest: false,
    dialogAcceptRequest: false,
    editedItem: null,
    explanation: null,
    headers: [
      {
        text: "Vacation ID",
        value: "vacationId",
        align: "center",
        sortable: true,
      },
      { text: "Pharmacist ID", value: "pharmacistId", align: "center" },
      { text: "First name", value: "firstName", align: "center" },
      { text: "Last name", value: "lastName", align: "center" },
      { text: "E-mail", value: "email", align: "center" },
      { text: "Start date", value: "startDate", align: "center" },
      { text: "End date", value: "endDate", align: "center" },
      { text: "Status", value: "status", align: "center", sortable: false },
      { text: "Actions", value: "actions", align: "center", sortable: false },
    ],
    vacationId: null,
  }),
  mounted() {
    this.initialize();
  },
  methods: {
    initialize() {
      this.axios
        .get("http://localhost:8091/pharmacistVacation/allWithStatusWaiting", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => {
          console.log(response.data);
          this.vacatoionList = response.data;
        });
    },

    declineVacation(item) {
      this.editedIndex = this.vacatoionList.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialogDeclineRequest = true;
      this.vacatoionId = this.editedItem.vacationId;
    },

    acceptVacation(item) {
      this.editedIndex = this.vacatoionList.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialogAcceptRequest = true;
      this.vacatoionId = this.editedItem.vacationId;
    },

    acceptRequest() {
      this.vacatoionList.splice(this.editedIndex, 1);
      this.axios
        .post(
          "http://localhost:8091/pharmacistVacation/accept",
          {
            vacationId: this.vacatoionId,
          },
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then(response => 
        {
          console.log(response.data);
          alert("The vacation request was successfully accepted!");
        });
      this.closeAcceptRequest();
    },

    declineRequest() {
      this.vacatoionList.splice(this.editedIndex, 1);
      this.axios
        .post(
          "http://localhost:8091/pharmacistVacation/decline",
          {
            vacationId: this.vacatoionId,
            explanation: this.explanation,
          },
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then(response => 
        {
          console.log(response.data);
          alert("The vacation request was declined!");
        });
      this.closeDeclineRequest();
    },

    closeDeclineRequest() {
      this.explanation = "";
      this.dialogDeclineRequest = false;
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      });
    },

    closeAcceptRequest() {
      this.dialogAcceptRequest = false;
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
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
#requestVacationCard {
  width: 80%;
  text-align: center;
  margin: auto;
}
</style>
 