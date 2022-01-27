<template>
  <div>
    <h1 id="myPersonalInformation">My personal information</h1>
    <div class="pt-1">
      <v-card id="personalInfoCard" v-bind:style="{ opacity: opacity }">
        <v-card-text>
          <v-form class="mx-auto mt-5 mb-5 mr-10 ml-10">
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="First name"
                v-model="firstName"
                color="blue"
                type="text"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Last name"
                v-model="lastName"
                color="blue"
                type="text"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Phone number"
                v-model="phoneNumber"
                color="blue"
                type="text"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Street"
                v-model="street"
                color="blue"
                type="text"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Street number"
                v-model="streetNumber"
                color="blue"
                type="text"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="City"
                v-model="city"
                color="blue"
                type="text"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Country"
                v-model="country"
                color="blue"
                type="text"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Pharmacy name"
                v-model="pharmacyName"
                color="blue"
                type="text"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="E-mail"
                v-model="email"
                color="blue"
                type="text"
                v-bind:readonly="true"
              />
            </v-row>
          </v-form>
        </v-card-text>
        <v-card-actions class="justify-center">
          <v-btn
            v-on:click="editPersonalInfo"
            color="info"
            class="mt-2 mr-10 p-5 mb-5"
            x-medium
            width="270px"
            height="40px"
            >Edit personal information</v-btn
          >
          <v-btn
            v-on:click="changePassword"
            color="info"
            class="mt-2 ml-10 p-5 mb-5"
            x-medium
            width="240px"
            height="40px"
            >Change password</v-btn
          >
        </v-card-actions>
      </v-card>
    </div>
  </div>
</template>

<script>
export default {
  name: "PharmacyAdmin",
  data: () => ({

      opacity: 0.9,
      firstName: "",
      lastName: "",
      phoneNumber: "",
      street: "",
      streetNumber: "",
      city: "",
      country: "",
      pharmacyName: "",
      email: "",
      pharmacyAdmin: null,
      pharmacyAdminId: "",
  
  }),
  mounted() {
    this.viewPersonalInfo();
  },
  methods: {
    viewPersonalInfo() {
      this.pharmacyAdminId = localStorage.getItem("userId");

      this.axios
        .get("http://localhost:8091/pharmacyAdmin/" + this.pharmacyAdminId, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => {
          console.log(response.data);
          this.pharmacyAdmin = response.data;
          this.firstName = this.pharmacyAdmin.firstName;
          this.lastName = this.pharmacyAdmin.lastName;
          this.phoneNumber = this.pharmacyAdmin.phoneNumber;
          this.street = this.pharmacyAdmin.street;
          this.streetNumber = this.pharmacyAdmin.streetNumber;
          this.city = this.pharmacyAdmin.city;
          this.country = this.pharmacyAdmin.country;
          this.pharmacyName = this.pharmacyAdmin.pharmacyName;
          this.email = this.pharmacyAdmin.email;
        })
        .catch((err) => console.log(err));
    }, editPersonalInfo() {
        window.location.href = "http://localhost:8080/editPharmacyAdminInfo";
    }, changePassword() {
        window.location.href = "http://localhost:8080/changePharmacyAdminPassword";
    },
  },
};
</script>

<style scoped>
#myPersonalInformation {
  margin-top: 2%;
  margin-bottom: 2%;
  color: rgb(2, 2, 117);
  text-align: center;
  font-weight: bold;
}
#personalInfoCard {
  width: 40%;
  margin: auto;
}
</style>