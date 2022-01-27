<template>
  <div>
    <h1 id="deleteDermatologistCaption">
      Delete dermatologist in {{ pharmacyName }} pharmacy
    </h1>
    <v-card id="deleteDermatologistInPharmacyCard" justify-center>
      <div>
        <v-data-table :headers="dermatologistHeaders" :items="dermatologistInPharmacy">
          <template v-slot:top>
            <v-toolbar dense dark color="light-blue darken-2">
              <v-spacer></v-spacer>
              <v-toolbar-title class="text-center">
                Dermatologists
              </v-toolbar-title>
              <v-dialog v-model="dialogDeleteDermatologist" max-width="60%">
                <v-card>
                  <v-spacer></v-spacer>
                  <v-card-title class="text-h4 justify-center"
                    >Are you sure you want to delete this dermatologist
                    ?</v-card-title
                  >
                  <v-card-actions>
                    <v-spacer></v-spacer>

                    <v-btn color="red" text @click="deleteDermatologist"
                      >Delete</v-btn
                    >
                    <v-spacer></v-spacer>

                    <v-btn color="primary" text @click="closeDeleteSelectedDermatologist"
                      >Cancel</v-btn
                    >

                    <v-spacer></v-spacer>
                  </v-card-actions>
                </v-card>
              </v-dialog>
              <v-spacer></v-spacer>
            </v-toolbar>
          </template>
          <template v-slot:[`item.action`]="{ item }">
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="red" text @click="deleteSelectedDermatologist(item)"
                >DELETE THIS DERMATOLOGIST</v-btn
              >
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
  name: "DeletePharmacist",
  data: () => ({
    dermatologistInPharmacy: [],
    dialogDeleteDermatologist: false,
    dermatologistHeaders: [
      { 
        text: "Dermatologist ID",
        value: "dermatologistId",
        align: "center",
        sortable: true,
      },
      {
        text: "First name",
        value: "firstName",
        align: "center",
        sortable: true,
      },
      {
        text: "Last name",
        value: "lastName",
        align: "center",
        sortable: true,
      },
      {
        text: "Phone number",
        value: "phoneNumber",
        align: "center",
        sortable: true,
      },
      {
        text: "E-mail",
        value: "email",
        align: "center",
        sortable: true,
      },
      {
        text: "Street name",
        value: "street",
        align: "center",
        sortable: true,
      },
      {
        text: "Home number",
        value: "homeNumber",
        align: "center",
        sortable: true,
      },
      {
        text: "City",
        value: "city",
        align: "center",
        sortable: true,
      },
      {
        text: "Rating of dermatologist",
        value: "rating",
        align: "center",
        sortable: true,
      },
      {
          value: "action",
          align: "center",
          sortable: false,
      }
    ],
    pharmacy: null,
    pharmacyName: "",
    editedIndex: null,
    editedDermatologist: null,
    deleteDermatologistId: null,
  }),
  mounted() {
    this.initialize();
  },
  methods: {
    initialize() {
      this.axios
        .get(
          "http://localhost:8091/pharmacy/" +
            localStorage.getItem("pharmacyId"),
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((resp) => {
          console.log(resp.data);
          this.pharmacy = resp.data;
          this.pharmacyName = this.pharmacy.name;
        });

      this.axios
        .get("http://localhost:8091/dermatologists/dermatologistsByPharmacy", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => {
          this.dermatologistInPharmacy = response.data;
        });

    },
    deleteSelectedDermatologist(item) {
      this.editedIndex = this.dermatologistInPharmacy.indexOf(item);
      this.editedDermatologist = Object.assign({}, item);
      this.dialogDeleteDermatologist = true;
      this.deleteDermatologistId = this.editedDermatologist.dermatologistId;
    },
    deleteDermatologist() {
       this.axios
        .post(
          "http://localhost:8091/dermatologists/deleteDermatologist",
          {
            deleteDermatologistId: this.deleteDermatologistId,
          },
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          console.log(response.data);
          if (response.data) {
              alert("Successfully deleted dermatologist!");
              window.location.href = "http://localhost:8080/myPharmacy";
          } else {
            alert(
              "Dear pharmacy admin, a dermatologist has an appointment or dermatologist is on vacation, and cannot be deleted from the pharmacy."
            );
            window.location.href =
              "http://localhost:8080/homePagePharmacyAdmin";
          }
        });
      this.closeDeleteSelectedDermatologist();
    },
    closeDeleteSelectedDermatologist() {
      this.dialogDeleteDermatologist = false;
      this.$nextTick(() => {
        this.editedDermatologist = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      });
    },
  },
};
</script>


<style scoped>
#deleteDermatologistCaption {
  margin-top: 2%;
  margin-bottom: 2%;
  color: rgb(2, 2, 117);
  text-align: center;
  font-weight: bold;
}
#deleteDermatologistInPharmacyCard {
  width: 80%;
  text-align: center;
  margin: auto;
}
</style>
