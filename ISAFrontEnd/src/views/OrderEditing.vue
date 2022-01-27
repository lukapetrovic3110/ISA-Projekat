<template>
  <div>
    <h1 class="orderEditingCaption">Edit or delete my orders</h1>
    <v-card class="orderEditingCard" elevation="7" justify-center>
      <div>
        <v-data-table
          :headers="headers"
          :items="possibleEditingOrderList"
          :items-per-page="5"
          sort-by="orderId"
        >
          <template v-slot:top>
            <v-toolbar dense dark color="light-blue darken-2">
              <v-spacer></v-spacer>
              <v-toolbar-title class="text-center"
                >List of orders we can edit or delete</v-toolbar-title
              >
              <v-spacer></v-spacer>
              <v-dialog v-model="dialogEditOrder" max-width="60%">
                <v-card>
                  <v-spacer></v-spacer>
                  <v-card-title
                    class="text-h4 justify-center orderEditingCaption"
                    >Edit order</v-card-title
                  >
                  <v-card class="orderEditingCard" elevation="9" justify-center>
                    <v-data-table
                      :headers="headersEdit"
                      :items="orderMedicineItems"
                    >
                      <template v-slot:top>
                        <v-toolbar dark color="light-blue darken-2">
                          <v-spacer></v-spacer>
                          <v-toolbar-title>
                            Medicine items that exist in the order
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
                            {{ row.item.quantity }}
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
                    <v-data-table
                      :headers="headersNew"
                      :items="possibleNewMedicineItems"
                    >
                      <template v-slot:top>
                        <v-toolbar dark color="light-blue darken-2">
                          <v-spacer></v-spacer>
                          <v-toolbar-title>
                            Add new medicine items to the order
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
                    <v-card-text>
                      <v-form class="mx-auto mt-5 mb-5 mr-10 ml-10">
                        <v-row>
                          <v-text-field
                            class="ml-10 mr-10 mt-10 text-center"
                            color="blue"
                            type="text"
                            v-bind:readonly="true"
                            value="If you wish, you can choose a new deadline for the offer"
                          >
                          </v-text-field>
                          <v-date-picker
                            width="100%"
                            v-model="newOfferDeadline"
                            class="ml-10 mr-10 mt-4"
                            :allowed-dates="disablePastDates"
                            :format="datePickerFormat"
                            color="green lighten-1"
                            header-color="primary"
                          ></v-date-picker>
                        </v-row>
                      </v-form>
                    </v-card-text>
                  </v-card>
                  <v-card-actions class="justify-center">
                    <v-btn
                      v-on:click="edit"
                      color="primary"
                      class="btnEdit"
                      x-large
                      width="30%"
                      >Edit</v-btn
                    >
                    <v-btn
                      v-on:click="closeEditOrderDialog"
                      color="primary"
                      class="btnCancel"
                      x-large
                      width="30%"
                      >Cancel</v-btn
                    >
                  </v-card-actions>
                </v-card>
              </v-dialog>

              <v-dialog v-model="dialogDeleteOrder" max-width="60%">
                <v-card>
                  <v-spacer></v-spacer>
                  <v-card-title class="text-h4 justify-center"
                    >Are you sure you want to delete the order ?</v-card-title
                  >

                  <v-spacer></v-spacer>

                  <v-card-actions>
                    <v-spacer></v-spacer>

                    <v-btn color="green" text @click="deleteThisOrder"
                      >Delete</v-btn
                    >
                    <v-spacer></v-spacer>

                    <v-btn color="red" text @click="closeDeleteOrderDialog"
                      >Cancel</v-btn
                    >

                    <v-spacer></v-spacer>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-toolbar>
          </template>
          <template v-slot:[`item.action`]="{ item }">
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue" text @click="editOrder(item)"> EDIT </v-btn>
              <v-spacer></v-spacer>
              <v-btn color="red" text @click="deleteOrder(item)">
                DELETE
              </v-btn>
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
  name: "OrderEditing",
  data: () => ({
    possibleEditingOrderList: [],
    dialogEditOrder: false,
    dialogDeleteOrder: false,
    orderMedicineItems: [],
    possibleNewMedicineItems: [],
    headers: [
      {
        text: "Order ID",
        value: "orderId",
        align: "center",
        sortable: true,
      },
      {
        text: "Pharmacy admin name",
        value: "adminName",
        align: "center",
        sortable: true,
      },
      {
        text: "Offer deadline",
        value: "offerDeadline",
        align: "center",
        sortable: true,
      },
      {
        text: "Order status",
        value: "orderStatus",
        align: "center",
        sortable: true,
      },
      { text: "Action", value: "action", align: "center", sortable: false },
    ],
    headersEdit: [
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
        text: "Old quantity",
        value: "quantity",
        align: "center",
        sortable: true,
        width: "16%",
      },
      {
        text: "New quantity",
        value: "newQuantity",
        align: "center",
        sortable: false,
        width: "16%",
      },
    ],
    headersNew: [
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
    orderItem: null,
    index: null,
    id: null,
    pharmacyAdminId: localStorage.getItem("userId"),
    deleteOrderId: null,
    newOfferDeadline: "",
    oldOfferDeadline: "",
    datePickerFormat: "dd.MM.yyyy.",
    keys1: [],
    values1: [],
    keys2: [],
    values2: [],
    medicineItemInOrderData: new Map(),
    newMedicineItemData: new Map(),
    deadLine: "",
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
        .get("http://localhost:8091/orders/possibleEditingOrders", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => {
          console.log(response.data);
          if (response.data.length != 0)
            alert(
              "There is an order that can be edit or delete by pharmacy admin who created them."
            );
          this.possibleEditingOrderList = response.data;
        });
    },
    editOrder(item) {
      this.index = this.possibleEditingOrderList.indexOf(item);
      this.orderItem = Object.assign({}, item);
      this.dialogEditOrder = true;
      this.oldOfferDeadline = this.orderItem.offerDeadline;

      this.axios
        .get(
          "http://localhost:8091/medicineItem/findMedicineItemsByOrderId/" +
            this.orderItem.orderId,
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((resp) => {
          console.log(resp.data);
          this.orderMedicineItems = resp.data;
        });

      this.axios
        .get(
          "http://localhost:8091/medicineItem/findMedicineItemsNotExistByOrderId/" +
            this.orderItem.orderId,
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((resp) => {
          console.log(resp.data);
          this.possibleNewMedicineItems = resp.data;
        });
    },
    closeEditOrderDialog() {
      this.dialogEditOrder = false;
      this.$nextTick(() => {
        this.orderItem = Object.assign({}, this.defaultItem);
        this.index = -1;
      });
    },
    deleteOrder(item) {
      this.index = this.possibleEditingOrderList.indexOf(item);
      this.orderItem = Object.assign({}, item);
      this.dialogDeleteOrder = true;
      this.deleteOrderId = this.orderItem.orderId;
    },
    closeDeleteOrderDialog() {
      this.dialogDeleteOrder = false;
      this.$nextTick(() => {
        this.orderItem = Object.assign({}, this.defaultItem);
        this.index = -1;
      });
      this.newOfferDeadline = "";
      this.keys1 = [];
      this.values1 = [];
      this.keys2 = [];
      this.values2 = [];
      this.medicineItemInOrderData = new Map();
      this.newMedicineItemData = new Map();
      this.newOfferDeadline = "";
      this.deadLine = null;
    },
    deleteThisOrder() {
      console.log(this.deleteOrderId);
      this.axios
        .get("http://localhost:8091/orders/delete/" + this.deleteOrderId, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((resp) => {
          alert("Order is deleted.");
          console.log(resp.data);
          window.location.href = "/homePagePharmacyAdmin";
        });
    },
    edit() {
      console.log(this.newOfferDeadline);
      if (this.newOfferDeadline == "") {
        var d = new Date(this.oldOfferDeadline);
        this.deadLine = d;
        console.log(this.deadLine);
      } else {
        this.deadLine = this.newOfferDeadline;
      }

      for (let i = 0; i < this.orderMedicineItems.length; i++) {
        var medicineInOrder = this.orderMedicineItems[i];
        if (medicineInOrder.newQuantity == null) {
          medicineInOrder.newQuantity = this.orderMedicineItems[i].quantity;
        }
        var addQuantity = parseInt(medicineInOrder.newQuantity);
        if (addQuantity < 0) {
          alert("You must enter a positive value for the quantity!");
          return;
        } else {
          this.keys1.push(medicineInOrder.medicineId);
          this.values1.push(addQuantity);
          this.medicineItemInOrderData.set(
            medicineInOrder.medicineId,
            addQuantity
          );
        }
      }

      for (let i = 0; i < this.possibleNewMedicineItems.length; i++) {
        var newMedicine = this.possibleNewMedicineItems[i];
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
          this.newMedicineItemData.set(
            newMedicine.medicineId,
            newQuantityForNewMedicine
          );
        }
      }

      this.axios
        .post(
          "http://localhost:8091/orders/editOrder",
          {
            orderId: this.orderItem.orderId,
            offerDeadline: this.deadLine,
            keys1: this.keys1,
            values1: this.values1,
            keys2: this.keys2,
            values2: this.values2,
          },
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          console.log(response.data);
          this.newOfferDeadline = "";
          this.keys1 = [];
          this.values1 = [];
          this.keys2 = [];
          this.values2 = [];
          this.medicineItemInOrderData = new Map();
          this.newMedicineItemData = new Map();
          this.newOfferDeadline = "";
          this.deadLine = null;
          location.reload();
          alert("Successfully updated order!");
        });
    },
  },
};
</script>

<style scoped>
.orderEditingCaption {
  margin-top: 2%;
  margin-bottom: 2%;
  color: rgb(2, 2, 117);
  text-align: center;
  font-weight: bold;
}
.orderEditingCard {
  width: 80%;
  text-align: center;
  margin: auto;
}
.btnEdit {
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
