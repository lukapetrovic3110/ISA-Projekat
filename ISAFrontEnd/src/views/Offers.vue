<template>
<div id="offer">
  <div class = "container">

        <v-row
            align="center"
            justify="space-around"
            style="margin-top: 3%" 
        >
        <h2 
            text-align:left
            color:blue
        >Filter offers by:</h2>
            <v-btn
                v-on:click = "filtrateOffersA"
            >Accepted</v-btn>
            <v-btn
                v-on:click = "filtrateOffersD"
            >Declined</v-btn>
            <v-btn
                v-on:click = "filtrateOffersW"
            >Waiting</v-btn>
            <v-btn
                v-on:click = "showAll"
            >No Filter</v-btn>
        </v-row>


      <v-simple-table style="margin-top: 1%"  >
        <template v-slot:default>
          <thead>
          <tr>
            <th class="text-left">
              Delivery Deadline
            </th>
            <th class="text-left">
              Offer Status
            </th>
            <th class="text-left">
              Order Status
            </th>
            <th class="text-left">
              Price
            </th>
            <th class="text-left">
              Pharmacy
            </th>
          </tr>
          </thead>
          <tbody>
          <tr
              v-for="offer in offers"
              :key="offer"
          >
            <td>{{ offer.deliveryDeadline.substr(0,10) }}</td>
            <td>{{ offer.status }}</td>
            <td>{{ offer.order.orderStatus}}</td>
            <td>{{ offer.price }}</td>
            <td>{{ offer.order.pharmacy.name }}</td>
            <td>
          <v-dialog
                transition="dialog-top-transition"
                max-width="600"
                      >
                        <template v-slot:activator="{ on, attrs }">
                          <v-btn
                              color="primary"
                              small
                              v-bind="attrs"
                              v-on="on"
                          >Change Offer</v-btn>
                        </template>
                        <template v-slot:default="dialog">
                          <v-card>
                            <v-toolbar
                                color="primary"
                                dark
                            >Offer:</v-toolbar>
                            
                          <v-text-field
                            label="Price"
                            v-model="offer.price"
                            :rules="[() => !!price || 'This field is required']"
                            prepend-icon="mdi-account-circle"
                          />                  
                            <template>
                              <v-row justify="center">
                                <v-date-picker v-model="offer.deliveryDeadline"></v-date-picker>
                              </v-row>
                            </template>
            <v-btn
                width="300"
                text
                @click="changeOffer(offer, dialog)"
            >Send</v-btn>
            </v-card>
            </template>
            </v-dialog>

            </td>
          </tr>
          </tbody>
        </template>
      </v-simple-table>


  <h2 
            text-align:left
            color:blue
          >New arrived orders:</h2>

      <v-simple-table style="margin-top: 1%;"  
            show-select  >
        <h2 >New arrived orders:</h2>

        <template v-slot:default>
          <thead>
          <tr>
            <th class="text-left">
              Offer Deadline
            </th>
            <th class="text-left">
              Status
            </th>
            <th class="text-left">
              Pharmacy
            </th>
          </tr>
          </thead>
          <tbody>
          <tr
              v-for="order in orders"
              :key="order"
          >
            <td>{{ order.offerDeadline.substr(0,10)}}</td>
            <td>{{ order.orderStatus }}</td>
            <td>{{ order.name }}</td>
            <td>

        <v-dialog
                  transition="dialog-top-transition"
                  max-width="600"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                      color="primary"
                      small
                      v-bind="attrs"
                      v-on="on"
                  >Create Offer</v-btn>
                </template>
                <template v-slot:default="dialog">
                  <v-card>
                    <v-toolbar
                        color="primary"
                        dark
                    >Offer:</v-toolbar>
                    
                  <v-text-field
                    label="Price"
                    v-model="price"
                    :rules="[() => !!price || 'This field is required']"
                    prepend-icon="mdi-account-circle"
                  />                  
                    <template>
                      <v-row justify="center">
                        <v-date-picker v-model="deliveryDeadline"></v-date-picker>
                      </v-row>
                    </template>
    <v-btn
        width="300"
        text
        @click="addOfferForOrder(order, dialog)"
    >Send</v-btn>
    </v-card>
    </template>
    </v-dialog>
        </td>
         </tr>
          </tbody>
        </template>
      </v-simple-table>


    </div>
</div>
</template>

<script>

export default {
    name: "Offers",
    data: function() {
     return {
        menu2: false,
        logged: false,
        offers: [],
        offer: null,
        orders: [],
        singleSelect: false,
        selected: [],
        isApproved: false
     }
    },
    mounted() { 
    {
        this.axios
            .get('http://localhost:8091/offers/seeOffers/' + localStorage.getItem("userId"), {
              headers: {
                  Authorization: 'Bearer ' + localStorage.getItem("token")
            }
            })
            .then(r => (this.offers = r.data));
        
        this.axios
            .get('http://localhost:8091/orders/allOrders', {
              headers: {
                  Authorization: 'Bearer ' + localStorage.getItem("token")
            }
            })
            .then(r => (this.orders = r.data))
    }   
    },
    methods: {
        filtrateOffersA: function(){
			this.axios
			.get("http://localhost:8091/offers/filtrate/ACCEPTED/" + localStorage.getItem("userId"), {
          headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
          }
      })
			.then(response => (this.offers = response.data));
		},
        filtrateOffersD: function(){
			this.axios
			.get("http://localhost:8091/offers/filtrate/DECLINED/" + localStorage.getItem("userId"), {
          headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
          }
      })
			.then(response => (this.offers = response.data));
		},
     filtrateOffersW: function(){
			this.axios
			.get("http://localhost:8091/offers/filtrate/WAITING/" + localStorage.getItem("userId"), {
          headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
          }
      })
			.then(response => (this.offers = response.data));
        },
     showAll: function() {
            this.axios
            .get('http://localhost:8091/offers/seeOffers/' + localStorage.getItem("userId"), {
              headers: {
                Authorization: 'Bearer ' + localStorage.getItem("token")
            }
            })
            .then(r => (this.offers = r.data));
        },
     disablePastDates(val) {
      return val >= new Date().toISOString().substr(0, 10);
    },
      addOfferForOrder: function(order, dialog){
        dialog.value = false
        this.axios
          .post("http://localhost:8091/offers/createOffer/" + order.orderId + "/add", {
              id: order.orderId,
              price: this.price,
              deliveryDeadline: this.deliveryDeadline,
              isApproved: this.isApproved
              },{
              headers: {
                  Authorization: 'Bearer ' + localStorage.getItem("token")}
            })
            .then(() => {
                this.isApproved = true
                console.log('Successfully sent offer.');
            })
            .catch((er) => {
              alert("Date is passed!");
              console.log(er.response.data);
            });
          },
    
      changeOffer: function(offer, dialog){
        dialog.value = false
        this.axios
          .post("http://localhost:8091/offers/changeOffer", offer,{
              headers: {
                  Authorization: 'Bearer ' + localStorage.getItem("token")}
            })
            .then(() => {

                console.log('Successfully changed offer.');
            })
            .catch((er) => {
              alert("Error!");
              console.log(er.response.data);
            });
          }
    },
  }
</script>

<style scoped>

td{
    padding: 7%;
}

h2 {

    text-align:center;
    color:white
}

.buttonSpec{
  width: 100%;
  cursor: pointer;
}
</style>