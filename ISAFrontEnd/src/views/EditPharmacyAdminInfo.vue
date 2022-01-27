<template>
  <div>
    <h1 id="myPersonalInformation">Edit personal information</h1>
    <div class="pt-1">
      <v-card id="personalInfoCard" v-bind:style="{ opacity: opacity }">
        <v-card-text>
          <v-form class="mx-auto mt-5 mb-5 mr-10 ml-10">
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="First name"
                v-model="pharmacyAdmin.firstName"
                color="blue"
                type="text"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Last name"
                v-model="pharmacyAdmin.lastName"
                color="blue"
                type="text"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Phone number"
                v-model="pharmacyAdmin.phoneNumber"
                color="blue"
                type="text"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Street"
                v-model="pharmacyAdmin.address.street"
                color="blue"
                type="text"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Street number"
                v-model="pharmacyAdmin.address.streetNumber"
                color="blue"
                type="text"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="City"
                v-model="pharmacyAdmin.address.city"
                color="blue"
                type="text"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Country"
                v-model="pharmacyAdmin.address.country"
                color="blue"
                type="text"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="E-mail"
                v-model="pharmacyAdmin.email"
                color="blue"
                type="text"
                v-bind:readonly="true"
              />
            </v-row>
          </v-form>
        </v-card-text>
        <v-card-actions class="justify-center">
          <v-btn
            v-on:click="save"
            color="info"
            class="mt-2 mr-10 p-5 mb-5"
            x-medium
            width="270px"
            height="40px"
            >Save</v-btn
          >
          <v-btn
            v-on:click="cancel"
            color="info"
            class="mt-2 ml-10 p-5 mb-5"
            x-medium
            width="240px"
            height="40px"
            >Cancel</v-btn
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
        .get("http://localhost:8091/users/" + this.pharmacyAdminId, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => {
          console.log(response.data);
          this.pharmacyAdmin = response.data;
        })
        .catch((err) => console.log(err));
    }, save() {
        this.axios
        .post("http://localhost:8091/users/update", this.pharmacyAdmin, {
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem("token"),
            }
        });
        alert("Successfully modified personal information!");
        window.location.href = "/pharmacyAdmin";
    }, cancel() {
        alert("Canceled change personal information!");
        window.location.href = "/homePagePharmacyAdmin";
    }
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