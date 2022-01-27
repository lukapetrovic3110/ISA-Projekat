<template>
  <div>
    <p id="message">{{ message }}</p>
  </div>
</template>

<script>
export default {
  name: "ConfirmRegistration",
  data: () => ({
    confirmationToken: "",
    message: "",
  }),
  mounted() {
    this.init();
  },
  methods: {
    init() {
      var href = window.location.href;
      var paths = [];
      paths = href.split("/");
      this.confirmationToken = paths[4];
      alert(this.confirmationToken);
      this.$http
        .put(
          "http://localhost:8091/auth/confirm_account/" +
            this.confirmationToken,
          {}
        )
        .then((response) => {
          console.log(response);
          this.message = "You have successfully verified your account!";
          alert("You can now log in to the system!");
          this.redirectToLogIn();
        })
        .catch((err) => {
          console.log(err);
          this.message =
            "Your token is invalid or expiried! Please, contact system admin!";
        });
    },
    redirectToLogIn() {
      window.location.href = "http://localhost:8080/logIn";
    },
  },
};
</script>

<style scoped>
#message {
  margin-top: 10%;
  padding: 10px;
  text-align: center;
  font-weight: bolder;
  font-size: 2.5em;
  text-decoration: none;
  color: darkblue;
}
</style>