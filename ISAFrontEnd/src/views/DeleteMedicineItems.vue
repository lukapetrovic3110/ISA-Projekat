<template>
  <div>
    <h1 id="deleteMedicineItemsCaption">
      Delete medicine items in {{ pharmacyName }} pharmacy
    </h1>
    <v-card id="medicinesInPharmacyCard" justify-center>
      <div>
        <v-data-table :headers="headers" :items="medicinesInPharmacy">
          <template v-slot:top>
            <v-toolbar dense dark color="light-blue darken-2">
              <v-spacer></v-spacer>
              <v-toolbar-title class="text-center">
                All available medicine items
              </v-toolbar-title>
              <v-dialog v-model="dialogDeleteMedicineItem" max-width="60%">
                <v-card>
                  <v-spacer></v-spacer>
                  <v-card-title class="text-h4 justify-center"
                    >Are you sure you want to delete this medicine item
                    ?</v-card-title
                  >
                  <v-card-actions>
                    <v-spacer></v-spacer>

                    <v-btn color="red" text @click="deleteMedicine"
                      >Delete</v-btn
                    >
                    <v-spacer></v-spacer>

                    <v-btn color="primary" text @click="closeDeleteMedicineItem"
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
              <v-btn color="red" text @click="deleteMedicineItem(item)"
                >DELETE MEDICINE ITEM</v-btn
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
  name: "DeleteMedicineItems",
  data: () => ({
    medicinesInPharmacy: [],
    dialogDeleteMedicineItem: false,
    headers: [
      {
        text: "Medicine item ID",
        value: "medicineItemId",
        aling: "center",
        sortable: true,
      },
      {
        text: "Medicine code",
        value: "medicineCode",
        align: "center",
        sortable: true,
      },
      {
        text: "Medicine name",
        value: "name",
        align: "center",
        sortable: true,
      },
      {
        text: "Medicine type",
        value: "type",
        align: "center",
        sortable: true,
      },
      {
        text: "Medicine form",
        value: "medicineForm",
        align: "center",
        sortable: true,
      },
      {
        text: "Prescription type",
        value: "prescriptionType",
        align: "center",
        sortable: true,
      },
      {
        text: "Quantity",
        value: "quantity",
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
    editedMedicine: null,
    deleteMedicineItemId: null,
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
        .get(
          "http://localhost:8091/medicineItem/findMedicineItemsByPharmacy/" +
            localStorage.getItem("pharmacyId"),
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          console.log(response.data);
          this.medicinesInPharmacy = response.data;
        });
    },
    deleteMedicineItem(item) {
      this.editedIndex = this.medicinesInPharmacy.indexOf(item);
      this.editedMedicine = Object.assign({}, item);
      this.dialogDeleteMedicineItem = true;
      this.deleteMedicineItemId = this.editedMedicine.medicineItemId;
    },
    deleteMedicine() {
      this.axios
        .post(
          "http://localhost:8091/medicineItem/deleteMedicineItemByMedicineId/", 
          {
            deleteMedicineItemId: this.deleteMedicineItemId,
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
            alert("Successfully deleted medicine item!");
            window.location.href = "http://localhost:8080/medicinesInPharmacy"
          } else {
            alert(
              "Dear pharmacy admin, the patient has reserved this medicine, it cannot be deleted from the pharmacy at the moment."
            );
            window.location.href = "http://localhost:8080/homePagePharmacyAdmin";
          }
        });

      this.closeDeleteMedicineItem();
    },
    closeDeleteMedicineItem() {
      this.dialogDeleteMedicineItem = false;
      this.$nextTick(() => {
        this.editedMedicine = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      });
    },
  },
};
</script>


<style scoped>
#deleteMedicineItemsCaption {
  margin-top: 2%;
  margin-bottom: 2%;
  color: rgb(2, 2, 117);
  text-align: center;
  font-weight: bold;
}
#medicinesInPharmacyCard {
  width: 80%;
  text-align: center;
  margin: auto;
}
</style>
