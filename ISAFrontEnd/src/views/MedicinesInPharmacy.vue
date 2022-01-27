<template>
  <div>
    <h1 id="medicinesInPharmacyCaption">
      Medicines in {{ pharmacyName }} pharmacy
    </h1>
    <v-card id="medicinesInPharmacyCard">
      <div>
        <v-data-table
          :headers="headers"
          :items="medicinesInPharmacy"
          sort-by="quantity"
        >
          <template v-slot:top>
            <v-toolbar dense dark color="light-blue darken-2">
              <v-spacer></v-spacer>
              <v-toolbar-title class="text-center">
                All medicine items
              </v-toolbar-title>
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
  name: "MedicinesInPharmacy",
  data: () => ({
    medicinesInPharmacy: [],
    headers: [
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
    ],
    pharmacy: null,
    pharmacyName: "",
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
  },
};
</script>


<style scoped>
#medicinesInPharmacyCaption {
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
