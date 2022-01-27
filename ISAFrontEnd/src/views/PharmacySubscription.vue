<template>
  <div id = "homePage">
    <div class = "container">
    <h3>Pharmacies:</h3>    
      <v-simple-table>
        <template v-slot:default>
          <thead>
          <tr>
            <th >
              Name
            </th>
            <th >
              Rating
            </th>
            <th >
              Address
            </th>
          </tr>
          </thead>
          <tbody>
          <tr
              v-for="p in pharmacies"
              :key="p"
          >
            <td>{{ p.name }}</td>
            <td>{{ p.rating }}</td>
            <td>{{p.address.street + " " + p.address.streetNumber + ", " + p.address.city + ", " + p.address.country}}</td>
            <td>
              <v-btn
                  color="secondary"
                  elevation="3"
                  x-small
                  v-on:click = "openPharmacyPage(p)"
                  v-if="isLogged"
              >Visit</v-btn>
            </td>
            <td>              
            <v-btn>   
                  <v-col cols="auto">
                <v-dialog
                    transition="dialog-top-transition"
                    max-width="600"
                >
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn
                        color="secondary"
                        elevation="3"
                        x-small
                        v-bind="attrs"
                        v-on="on"
                        v-if="isLogged"
                    >Unsubscribe</v-btn>
                  </template>
                  <template v-slot:default="dialog">
                    <v-card>
                      <v-toolbar
                          color="primary"
                          dark
                      >Unsubscribe to {{p.name}}</v-toolbar>
                        <v-textarea
                                background-color="white"
                                filled
                                readonly
                                name="input-7-4"
                                label="You won't get notifications about new sales and promotions in pharmacy. Are you sure?"
                          ></v-textarea>
                    <v-btn
                          width="300"
                          text
                          @click="unsubscribeToSalesAndPromotion(p, dialog)"
                      >Unsubscribe</v-btn>
                    <v-btn
                          width="300"
                          text
                      >Cancel</v-btn>
                    </v-card>
                </template>
              </v-dialog>
            </v-col>
              </v-btn>
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
  name: "PharmacySubscription",
  data: function () {
    return {
      logged: false,
      dialog: false,
      pharmacies: [],
      searchField: "",
      token: localStorage.getItem("token")
    }
  },
mounted() {
  {
  this.axios
      .get('http://localhost:8091/pharmacy/getSubscribedPharmacyForPatient/' + localStorage.getItem("userId"), {
              headers: {
                Authorization: 'Bearer ' + localStorage.getItem("token")
            }
            })
      .then(r => (this.pharmacies = r.data))
  }
},
  methods: {
    openPharmacyPage(p){
      localStorage.setItem("pharmacy", p.pharmacyId);
      localStorage.setItem("pharmacyName", p.name);
      window.location.href = "http://localhost:8080/pharmacyPage";
    },
    unsubscribeToSalesAndPromotion(p, dialog){
      dialog.value = false
      this.axios
        .post('http://localhost:8091/promotions/unsubscribeToPharmacy/' + p.pharmacyId, {}, {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
          })
          .then(r => {
                  this.pharmacy = r.data
                  alert("Successfully unsubscribed to " + p.name + " pharmacy!")
                  window.location.href = "http://localhost:8080/pharmacySubscription"

          })
          .catch(() => {
                  alert("Successful unsubscription!")
          })
    },
    setPharmacyId(){
        localStorage.setItem("pharmacy")
    }
  },
  
  computed:{
    isLogged: function (){
      if (this.token == ""){
        return false
      }else{
        return true
      }
    },
    notFilled: function () {
      if (this.searchField.trim() == ""){
        return false
      }
      else {
        return true
      }
    },
  }
}
</script>

<style scoped>
#searchPharmacies{
  background-color: white;
}

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

input[type=radio]{
  border-radius: 10px;
  box-shadow: inset 0 1px 1px hsla(0,0%,100%,.8),
  0 0 0 1px hsla(0,0%,0%,.6),
  0 2px 3px hsla(0,0%,0%,.6),
  0 4px 3px hsla(0,0%,0%,.4),
  0 6px 6px hsla(0,0%,0%,.2),
  0 10px 6px hsla(0,0%,0%,.2);
  cursor: pointer;
  display: inline-block;
  height: 15px;
  margin-right: 15px;
  position: relative;
  width: 15px;
  -webkit-appearance: none;
}

input[type="radio"]:after {
  border-radius: 25px;
  box-shadow: inset 0 0 0 1px hsla(0,0%,0%,.4),
  0 1px 1px hsla(0,0%,100%,.8);
  content: '';
  display: block;
  height: 7px;
  left: 4px;
  position: relative;
  top: 4px;
  width: 7px;
}
input[type="radio"]:checked:after {
  background-color: #444;
  box-shadow: inset 0 0 0 1px hsla(0,0%,0%,.4),
  inset 0 2px 2px hsla(0,0%,100%,.4),
  0 1px 1px hsla(0,0%,100%,.8),
  0 0 2px 2px hsla(0,70%,70%,.4);
}

</style>