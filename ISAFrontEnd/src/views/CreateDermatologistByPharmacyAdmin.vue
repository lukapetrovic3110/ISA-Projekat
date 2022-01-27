<template>
  <div>
    <h1 id="createDermatologistByPharmacyAdminCaption">
      Create dermatologist for {{ pharmacyName }} pharmacy
    </h1>
    <div class="pt-1">
      <v-card id="dermatologistInfoCard" v-bind:style="{ opacity: opacity }">
        <v-card-text>
          <v-form class="mx-auto mt-5 mb-5 mr-10 ml-10">
            <v-text-field
              label="First name"
              v-model="firstName"
              :rules="[() => !!firstName || 'This field is required']"
              :error-messages="errorMessages"
              prepend-icon="mdi-account-circle"
            />
            <v-text-field
              label="Last name"
              v-model="lastName"
              :rules="[() => !!lastName || 'This field is required']"
              :error-messages="errorMessages"
              prepend-icon="mdi-account-circle"
            />
            <v-text-field
              label="Street name"
              v-model="streetName"
              :rules="[() => !!streetName || 'This field is required']"
              :error-messages="errorMessages"
              prepend-icon="mdi-account-circle"
            />
            <v-text-field
              label="Street number"
              v-model="streetNumber"
              :rules="[() => !!streetNumber || 'This field is required']"
              :error-messages="errorMessages"
              prepend-icon="mdi-account-circle"
            />
            <v-text-field
              label="City"
              v-model="city"
              :rules="[() => !!city || 'This field is required']"
              :error-messages="errorMessages"
              prepend-icon="mdi-account-circle"
            />
            <v-autocomplete
              class="countryCombo"
              v-model="country"
              :rules="[() => !!country || 'This field is required']"
              :items="countries"
              label="Country"
              placeholder="Select..."
            />
            <v-text-field
              label="Phone Number"
              v-model="phoneNumber"
              :rules="[() => !!phoneNumber || 'This field is required']"
              :error-messages="errorMessages"
              prepend-icon="mdi-account-circle"
            />
            <v-text-field
              label="E-mail"
              v-model="email"
              :rules="[() => !!email || 'This field is required']"
              :error-messages="errorMessages"
              prepend-icon="mdi-account-circle"
            />
            <v-text-field
              :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
              :type="showPassword ? 'text' : 'password'"
              label="Password"
              v-model="password"
              :rules="[() => !!password || 'This field is required']"
              :error-messages="errorMessages"
              hint="Password must contain minimum 8 characters, 1 uppercase, 1 lowercase and 1 number and 1 special character."
              prepend-icon="mdi-lock"
              @click:append="showPassword = !showPassword"
            />
            <v-text-field
              :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
              :type="showPassword ? 'text' : 'password'"
              label="Repeat password"
              v-model="repeatedPassword"
              :rules="[
                () => !!repeatedPassword || 'This field is required',
                passwordConfirmationRule,
              ]"
              :error-messages="errorMessages"
              hint="Password must contain minimum 8 characters, 1 uppercase, 1 lowercase and 1 number and 1 special character."
              prepend-icon="mdi-lock"
              @click:append="showPassword = !showPassword"
            />
          </v-form>
        </v-card-text>
        <v-card-actions>
          <v-btn
            class="mx-auto mb-5; color:white"
            color="primary"
            elevation="6"
            x-large
            raised
            v-on:click="createDermatologist"
            >Create</v-btn
          >
          <v-btn
            class="mx-auto mb-5; color:white"
            color="primary"
            elevation="6"
            x-large
            raised
            v-on:click="cancel"
            >Cancel</v-btn
          >
        </v-card-actions>
      </v-card>
    </div>
  </div>
</template>

<script>
export default {
  name: "CreateDermatologistByPharmacyAdmin",
  data: () => ({
    opacity: 0.9,
    countries: [
      "Srbija",
      "Republika Srpska",
      "Bosna i Hercegovina",
      "Crna Gora",
      "Hrvatska",
    ],
    errorMessages: "",
    showPassword: false,
    email: "",
    password: "",
    firstName: "",
    lastName: "",
    streetName: "",
    streetNumber: "",
    city: "",
    country: "",
    phoneNumber: "",
    repeatedPassword: "",
    dermatologist: null,
    pharmacy: null,
    pharmacyName: "",
  }),
  computed: {
    passwordConfirmationRule() {
      return () =>
        this.password === this.repeatedPassword || "Password must match.";
    },
  },
  mounted() {
    this.initialize();
  },
  methods: {
    initialize() {
      this.axios
        .get(
          "http://localhost:8091/pharmacy/getPharmacyById/" +
            localStorage.getItem("pharmacyId"),
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          console.log(response.data);
          this.pharmacy = response.data;
          this.pharmacyName = response.data.name;
        })
        .catch((err) => console.log(err));
    },
    createDermatologist() {
      this.axios
        .post(
          "http://localhost:8091/dermatologists/createDermatologistByPharmacyAdmin",
          {
            pharmacyId: localStorage.getItem("pharmacyId"),
            email: this.email,
            password: this.password,
            firstName: this.firstName,
            lastName: this.lastName,
            phoneNumber: this.phoneNumber,
            address: {
              street: this.streetName,
              streetNumber: this.streetNumber,
              city: this.city,
              country: this.country,
            },
          },
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          console.log(response.data);
          this.dermatologist = response.data;
          if (this.dermatologist != null) {
            alert("Dermatologist is successfully created!");
            window.location.href = "http://localhost:8080/myPharmacy";
          } else {
            alert("Dear pharmacy admin, dermatologist can't be created.");
            window.location.href =
              "http://localhost:8080/homePagePharmacyAdmin";
          }
        });
    },
    cancel() {
      window.location.href = "http://localhost:8080/homePagePharmacyAdmin";
    },
  },
};
</script>

<style scoped>
#createDermatologistByPharmacyAdminCaption {
  margin-top: 2%;
  margin-bottom: 2%;
  color: rgb(2, 2, 117);
  text-align: center;
  font-weight: bold;
}
#dermatologistInfoCard {
  width: 40%;
  margin: auto;
}
.countryCombo {
  width: 96%;
  margin-left: 5%;
  cursor: pointer;
}
</style>