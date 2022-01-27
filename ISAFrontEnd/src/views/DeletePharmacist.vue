<template>
  <div>
    <h1 id="deletePharmacistCaption">
      Delete pharmacist in {{ pharmacyName }} pharmacy
    </h1>
    <v-card id="deletePharmacistInPharmacyCard" justify-center>
      <div>
        <v-data-table
          :headers="pharmacistHeaders"
          :items="pharmacistsInPharmacy"
        >
          <template v-slot:top>
            <v-toolbar dense dark color="light-blue darken-2">
              <v-spacer></v-spacer>
              <v-toolbar-title class="text-center">
                Pharmacists
              </v-toolbar-title>
              <v-dialog v-model="dialogDeletePharmacist" max-width="60%">
                <v-card>
                  <v-spacer></v-spacer>
                  <v-card-title class="text-h4 justify-center"
                    >Are you sure you want to delete this pharmacist
                    ?</v-card-title
                  >
                  <v-card-actions>
                    <v-spacer></v-spacer>

                    <v-btn color="red" text @click="deletePharmacist"
                      >Delete</v-btn
                    >
                    <v-spacer></v-spacer>

                    <v-btn
                      color="primary"
                      text
                      @click="closeDeleteSelectedPharmacist"
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
              <v-btn color="red" text @click="deleteSelectedPharmacist(item)"
                >DELETE THIS PHARMACIST</v-btn
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
    pharmacistsInPharmacy: [],
    dialogDeletePharmacist: false,
    pharmacistHeaders: [
      {
        text: "Pharmacist ID",
        value: "pharmacistId",
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
        text: "Rating of pharmacist",
        value: "rating",
        align: "center",
        sortable: true,
      },
      {
        value: "action",
        aling: "center",
        sortable: false,
      },
    ],
    pharmacy: null,
    pharmacyName: "",
    editedIndex: null,
    editedPharmacist: null,
    deletePharmacistId: null,
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
        .get("http://localhost:8091/pharmacists/pharmacistsByPharmacy", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => {
          this.pharmacistsInPharmacy = response.data;
        });
    },
    deleteSelectedPharmacist(item) {
      this.editedIndex = this.pharmacistsInPharmacy.indexOf(item);
      this.editedPharmacist = Object.assign({}, item);
      this.dialogDeletePharmacist = true;
      this.deletePharmacistId = this.editedPharmacist.pharmacistId;
    },
    deletePharmacist() {
      this.axios
        .post(
          "http://localhost:8091/pharmacists/deletePharmacist",
          {
            deletePharmacistId: this.deletePharmacistId,
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
            alert("Successfully deleted pharmacist!");
            window.location.href = "http://localhost:8080/myPharmacy";
          } else {
            alert(
              "Dear pharmacy admin, the pharmacist has scheduled consultation or pharmacist is on vacation, and cannot be deleted from the pharmacy."
            );
            window.location.href =
              "http://localhost:8080/homePagePharmacyAdmin";
          }
        });
    
      
      this.closeDeleteSelectedPharmacist();
    },
    closeDeleteSelectedPharmacist() {
      this.dialogDeletePharmacist = false;
      this.$nextTick(() => {
        this.editedPharmacist = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      });
    },
  },
};
</script>


<style scoped>
#deletePharmacistCaption {
  margin-top: 2%;
  margin-bottom: 2%;
  color: rgb(2, 2, 117);
  text-align: center;
  font-weight: bold;
}
#deletePharmacistInPharmacyCard {
  width: 80%;
  text-align: center;
  margin: auto;
}
</style>
