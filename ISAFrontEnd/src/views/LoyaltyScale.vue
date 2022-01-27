<template>
  <v-card style="margin-top: 3%" width="40%" class="mx-auto">
    <v-card-title class="justify-center">
      <h1 class="display-1 mt-20">Define Loyalty Scale</h1>
    </v-card-title>
    <v-card-text>
      <v-form class="mx-auto ml-20 mr-20">
        <v-text-field
          label="Min points"
          v-model="from"
          prepend-icon="mdi-account-circle"
        />
        <v-text-field
          label="Max points"
          v-model="to"
          prepend-icon="mdi-account-circle"
        />
        <v-autocomplete
            class="countryCombo"
            v-model="loyaltyCategory"
            :items="loyaltyCategory"
            label="LoyaltyCategory"
            placeholder="Select..."
        />
      </v-form>
    </v-card-text>
    <div></div>
    <v-card-actions>
      <v-btn
        class="mx-auto mb-5; color:white"
        color="accent"
        elevation="2"
        x-large
        raised
        v-on:click="defineLoyaltyScale">Define</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name: "LoyaltyScale",
  data: () => ({
    loyaltyCategory: ['REGULAR', 'SILVER', 'GOLD'],
    }),
  computed: {},
  methods: {
    defineLoyaltyScale() {
      let token = localStorage.getItem("token");
      this.axios
        .post("http://localhost:8091/systemAdmin/defineLoyaltyScale",  {
            from: this.from,
            to: this.to,
            loyaltyCategory: this.loyaltyCategory
        },{
         headers: {
              Authorization: 'Bearer ' + token
        }})
        .then(() => {
            alert("You are created loyalty scale!");

        })
        .catch((er) => {
          alert("Try again later!");
          console.log(er.response.data);
        });
    },
  },
};
</script>


<style scoped>
.countryCombo{
  width: 96%;
  margin-left: 4%;
  cursor: pointer;
}
</style>