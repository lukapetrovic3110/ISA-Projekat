<template>
  <v-card style="margin-top: 10%" width="30%" class="mx-auto">
    <v-card-title class="justify-center">
      <h1 class="display-1 mt-20">LogIn</h1>
    </v-card-title>
    <v-card-text>
      <v-form class="mx-auto ml-20 mr-20">
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
          hint="At least 8 characters"
          prepend-icon="mdi-lock"
          @click:append="showPassword = !showPassword"
        ></v-text-field>
      </v-form>
    </v-card-text>
    <div></div>
    <v-card-actions>
      <v-btn
        class="mx-auto mb-5; color:white"
        color="primary"
        elevation="3"
        x-large
        raised
        v-on:click="logIn"
        >Log In</v-btn
      >
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name: "Login",
  data: () => ({
    showPassword: false,
    email: "",
    password: "",
    users: [],
  }),
  computed: {
    user() {
      return { email: this.email, password: this.password };
    },
  },
  methods: {
    logIn() {
      this.$http
        .post("http://localhost:8091/auth/login", {
          email: this.email,
          password: this.password,
        })
        .then((resp) => {
          console.log(resp.data);
          try {
            localStorage.setItem("email", this.user.email);
            localStorage.setItem("token", resp.data.accessToken);
            localStorage.setItem("userId", resp.data.user.userId);
            localStorage.setItem("userType", resp.data.user.userType);
            localStorage.setItem("first_login", resp.data.user.firstLogin);

            if (resp.data.user.userType == "PATIENT") {
              window.location.href = "http://localhost:8080/homePagePatient";
            } else {
              if (resp.data.user.firstLogin == false) {
                try {
                  localStorage.setItem("pharmacyId", resp.data.user.pharmacy.pharmacyId);
                } catch (error) {
                  console.error(error);
                }
                window.location.href =
                  "http://localhost:8080/changePasswordFirstLogin";
           
              } else {
                if (resp.data.user.userType == "DERMATOLOGIST") {
                  window.location.href =
                    "http://localhost:8080/homePageDermatologist";
                } else if (resp.data.user.userType == "PHARMACIST") {
                   window.location.href =
                    "http://localhost:8080/homePagePharmacist";
                } else if (resp.data.user.userType == "PH_ADMINISTRATOR") {
                  localStorage.setItem("pharmacyId", resp.data.user.pharmacy.pharmacyId);
                  window.location.href = "http://localhost:8080/homePagePharmacyAdmin";
                  alert("Now " + resp.data.user.firstName + " " + resp.data.user.lastName + " has logged in to the Pharmacy system, as " + localStorage.getItem("userType") + ".");
                } else if(resp.data.user.userType == "SYS_ADMINISTRATOR"){
                  window.location.href = "http://localhost:8080/HomePageSystemAdmin";
                }
                else
                  window.location.href = "http://localhost:8080/NavigationBar";
              }
            }
          } catch (error) {
            alert(error);
          }
        })

        .catch((er) => {
          alert("Invalid email and/or password! Please, try again!");
          this.email = "";
          this.password = "";
          console.log(er.response.data);
        });
    },
  },
};
</script>