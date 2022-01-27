<template>
  <div>
    <h1 id="makeAnOrderCaption">Make an order for a pharmacy</h1>
    <v-card id="makeAnOrderCard" elevation="10" justify-center>
      <div>
        <v-data-table
          :headers="headers"
          :items="medicineItemsPharmacy"
          :items-per-page="5"
        >
          <template v-slot:top>
            <v-toolbar dense dark color="light-blue darken-2">
              <v-spacer></v-spacer>
              <v-toolbar-title class="text-center">
                Medicines that exist in the {{ pharmacyName }} pharmacy
              </v-toolbar-title>
              <v-spacer></v-spacer>
            </v-toolbar>
          </template>
          <template v-slot:item="row">
            <tr>
              <td>
                {{ row.item.medicineId }}
              </td>
              <td>
                {{ row.item.medicineCode }}
              </td>
              <td>
                {{ row.item.name }}
              </td>
              <td>
                {{ row.item.type }}
              </td>
              <td>
                {{ row.item.medicineForm }}
              </td>
              <td>
                <v-text-field
                  type="number"
                  min="0"
                  v-model="row.item.newQuantity"
                >
                  {{ row.item.newQuantity }}
                </v-text-field>
              </td>
            </tr>
          </template>
        </v-data-table>
      </div>
      <div>
        <v-data-table
          class="mt-5 elevation-1"
          :headers="headers"
          :items="potentiallyNewMedicineItems"
          :items-per-page="10"
        >
          <template v-slot:top>
            <v-toolbar dense dark color="light-blue darken-2">
              <v-spacer></v-spacer>
              <v-toolbar-title class="text-center">
                New medicines that the pharmacy has not ordered so far
              </v-toolbar-title>
              <v-spacer></v-spacer>
            </v-toolbar>
          </template>
          <template v-slot:item="row">
            <tr>
              <td>
                {{ row.item.medicineId }}
              </td>
              <td>
                {{ row.item.medicineCode }}
              </td>
              <td>
                {{ row.item.name }}
              </td>
              <td>
                {{ row.item.type }}
              </td>
              <td>
                {{ row.item.medicineForm }}
              </td>
              <td>
                <v-text-field
                  type="number"
                  min="0"
                  v-model="row.item.newQuantity"
                >
                  {{ row.item.newQuantity }}
                </v-text-field>
              </td>
            </tr>
          </template>
        </v-data-table>
      </div>
      <v-card-text>
        <v-form class="mx-auto mt-5 mb-5 mr-10 ml-10">
          <v-row>
            <v-text-field
              class="ml-10 mr-10 mt-10 text-center"
              color="blue"
              type="text"
              v-bind:readonly="true"
              value="Please select below an offer deadline date"
            >
            </v-text-field>
            <v-date-picker
              width="100%"
              class="ml-10 mr-10 mt-4"
              v-model="offerDeadline"
              :allowed-dates="disablePastDates"
              :format="datePickerFormat"
              color="green lighten-1"
              header-color="primary"
            ></v-date-picker>
          </v-row>
        </v-form>
      </v-card-text>
      <v-card-actions class="justify-center">
        <v-btn
          v-on:click="createOreder"
          color="primary"
          class="btnCreate"
          x-large
          width="30%"
          >Create</v-btn
        >
        <v-btn
          v-on:click="cancel"
          color="primary"
          class="btnCancel"
          x-large
          width="30%"
          >Cancel</v-btn
        >
      </v-card-actions>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "MakeAnOrder",
  data: () => ({
    medicineItemsPharmacy: [],
    potentiallyNewMedicineItems: [],
    medicineInPharmacyData: new Map(),
    newMedicineInPharmacyData: new Map(),
    headers: [
      {
        text: "Medicine ID",
        value: "medicineId",
        align: "center",
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
        text: "New quantity",
        value: "newQuantity",
        align: "center",
        sortable: false,
        width: "16%",
      },
    ],
    pharmacy: null,
    pharmacyName: "",
    pharmacyId: "",
    offerDeadline: "",
    datePickerFormat: "dd.MM.yyyy.",
    keys1: [],
    values1: [],
    keys2: [],
    values2: [],
  }),
  mounted() {
    this.initialize();
  },
  methods: {
    disablePastDates(val) {
      return val >= new Date().toISOString().substr(0, 10);
    },
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
          this.medicineItemsPharmacy = response.data;
        });

      this.axios
        .get(
          "http://localhost:8091/medicineItem/findPotentiallyNewMedicineItemsForPharmacy/" +
            localStorage.getItem("pharmacyId"),
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          console.log(response.data);
          this.potentiallyNewMedicineItems = response.data;
        });
    },
    createOreder() {
      if (this.offerDeadline == "") {
        alert("Not defined offer deadline. Please enter it.");
      } else {
        for (let i = 0; i < this.medicineItemsPharmacy.length; i++) {
          var medicine = this.medicineItemsPharmacy[i];
          if (medicine.newQuantity == null) {
            medicine.newQuantity = 0;
          }
          var addQuantity = parseInt(medicine.newQuantity);
          if (addQuantity < 0) {
            alert("You must enter a positive value for the quantity!");
            return;
          }
          if (addQuantity > 0) {
            this.keys1.push(medicine.medicineId);
            this.values1.push(addQuantity);
            this.medicineInPharmacyData.set(medicine.medicineId, addQuantity);
          }
        }

        for (let i = 0; i < this.potentiallyNewMedicineItems.length; i++) {
          var newMedicine = this.potentiallyNewMedicineItems[i];
          if (newMedicine.newQuantity == null) {
            newMedicine.newQuantity = 0;
          }
          var newQuantityForNewMedicine = parseInt(newMedicine.newQuantity);
          if (newQuantityForNewMedicine < 0) {
            alert("You must enter a positive value for the quantity!");
            return;
          }

          if (newQuantityForNewMedicine > 0) {
            this.keys2.push(newMedicine.medicineId);
            this.values2.push(newQuantityForNewMedicine);
            this.newMedicineInPharmacyData.set(newMedicine.medicineId, newQuantityForNewMedicine);
          }
        }

        this.axios
          .post(
            "http://localhost:8091/orders/createOrder",
             { 
              
                pharmacyAdminId: localStorage.getItem("userId"),
                pharmacyId:  this.pharmacy.pharmacyId,
                offerDeadline: this.offerDeadline,
                keys1: this.keys1,
                values1: this.values1,
                keys2: this.keys2,
                values2: this.values2
        
            },
            {
               headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
               },
            }
          )
          .then((response) => {
            console.log(response.data);
            alert("Successfully created a order for pharmacy.");
            window.location.href = "/pharmacyOrders";
          });
      }
    },
    cancel() {
      alert("Canceled making order!");
      window.location.href = "/homePagePharmacyAdmin";
    },
  },
};
</script>


<style scoped>
#makeAnOrderCaption {
  margin-top: 2%;
  margin-bottom: 2%;
  color: rgb(2, 2, 117);
  text-align: center;
  font-weight: bold;
}
#makeAnOrderCard {
  width: 80%;
  text-align: center;
  margin: auto;
}
.btnCreate {
  margin: 5%;
  padding-top: 10%;
}
.btnCancel {
  margin-top: 5%;
  margin-left: 25%;
  margin-bottom: 5%;
  padding-top: 10%;
}
</style>