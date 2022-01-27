<template>
  <div>
    <h1 id="promotionCaption">Define a promotion</h1>
    <div class="pt-1">
      <v-card id="promoCard" v-bind:style="{ opacity: opacity }">
        <v-card-text>
          <v-form class="mx-auto mt-5 mb-5 mr-10 ml-10">
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Caption"
                v-model="caption"
                color="blue"
                type="text"
              />
            </v-row>
            <v-row>
              <v-textarea
                class="ml-10 mr-10"
                outlined
                name="input-7-4"
                label="Content"
                v-model="content"
                type="text"
              ></v-textarea>
            </v-row>
            <v-row>
              <v-date-picker
                width="100%"
                class="ml-10 mr-10 mt-4"
                v-model="startOfPromotion"
                :allowed-dates="disablePastDates"
                :format="datePickerFormat"
                color="green lighten-1"
                header-color="primary"
              ></v-date-picker>
            </v-row>
            <v-row>
              <v-date-picker
                width="100%"
                class="ml-10 mr-10 mt-4"
                v-model="endOfPromotion"
                :allowed-dates="disablePastDates"
                :format="datePickerFormat"
                max="2024-08-20"
                color="green lighten-1"
                header-color="primary"
              ></v-date-picker>
            </v-row>
            <v-row> </v-row>
          </v-form>
        </v-card-text>
        <v-card-actions class="justify-center">
          <v-btn
            v-on:click="createPromotion"
            color="info"
            class="mt-2 mr-10 p-5 mb-5"
            x-medium
            width="270px"
            height="40px"
            >Create</v-btn
          >
          <v-btn
            v-on:click="cancelPromotion"
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
  name: "Promotion",
  data: () => ({
    opacity: 0.9,
    caption: "",
    content: "",
    startOfPromotion: "",
    endOfPromotion: "",
    pharmacyId: "",
    datePickerFormat: "dd.MM.yyyy.",
  }),
  methods: {
    disablePastDates(val) {
      return val >= new Date().toISOString().substr(0, 10);
    },

    createPromotion() {
      if (
        this.content == "" ||
        this.content == "" ||
        this.startOfPromotion == "" ||
        this.endOfPromotion == ""
      ) {
        alert(
          "Please fill in all the information needed to create the promotion."
        );
      }
      if (this.endOfPromotion < this.startOfPromotion) {
        alert(
          "Please check, the end date of the promotion must be after the start date."
        );
      } else {
        this.pharmacyId = localStorage.getItem("pharmacyId");
        this.axios
          .post(
            "http://localhost:8091/promotions/savePromotionAndSendEmail",
            {
              caption: this.caption,
              content: this.content,
              startOfPromotion: this.startOfPromotion,
              endOfPromotion: this.endOfPromotion,
              pharmacyId: this.pharmacyId,
            },
            {
              headers: {
                Authorization: "Bearer " + localStorage.getItem("token"),
              },
            }
          ).then(resp => {
              console.log(resp);
              alert(
                "Congrats! Successfully created a promotion."
              );
              window.location.href = "/homePagePharmacyAdmin";
          })
          .catch((err) => {
            console.log(err);
            if (err.response.status == 400) {
              alert("Check the data you have entered!");
            } else if (err.response.status == 401) {
              alert(
                "You are not login as a pharmacy administrator, ie. you are not authenticated."
              );
            } else {
              alert("Server problem!");
            }
          });
      }
    },
    cancelPromotion() {
      alert("Canceled making promotion!");
      window.location.href = "/homePagePharmacyAdmin";
    },
  },
};
</script>

<style scoped>
#promotionCaption {
  margin-top: 2%;
  margin-bottom: 2%;
  color: rgb(2, 2, 117);
  text-align: center;
  font-weight: bold;
}
#promoCard {
  width: 40%;
  margin: auto;
}
</style>