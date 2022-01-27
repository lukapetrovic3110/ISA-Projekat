<template>
  <div>
    <h1 id="defineFreeTermWithDermatologistCaption">
      Define free term for examination with dermatologist who work in
      {{ this.pharmacyName }} pharmacy
    </h1>
    <v-card id="defineFreeTermWithDermatologistCard">
      <v-data-table
        :headers="headers"
        :items="dermatologistList"
        :items-per-page="10"
      >
        <template v-slot:top>
          <v-toolbar dense dark color="light-blue darken-2">
            <v-spacer></v-spacer>
            <v-toolbar-title class="text-center">
              Select one of the dermatologists who works in the
              {{ pharmacyName }} pharmacy
            </v-toolbar-title>
            <v-spacer></v-spacer>
          </v-toolbar>
        </template>
        <v-spacer></v-spacer>
        <template v-slot:[`item.action`]="{ item }">
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="primary" text @click="selectDermatologist(item)">
              SELECT DERMATOLOGIST
            </v-btn>
            <v-spacer></v-spacer>
          </v-card-actions>
        </template>
      </v-data-table>
      <div v-if="showDermatologistVacationList">
        <v-data-table
          :headers="headersVacation"
          :items="dermatologistVacationList"
          :items-per-page="5"
        >
          <template v-slot:top>
            <v-toolbar dense dark color="light-blue darken-2">
              <v-spacer></v-spacer>
              <v-toolbar-title class="text-center">
                Current vacation list for a dermatologist {{ dermatologist }}
              </v-toolbar-title>
              <v-spacer></v-spacer>
            </v-toolbar>
          </template>
        </v-data-table>
      </div>
      <div v-if="showAllScheduledExaminationForDermatologistList">
        <v-data-table
          :headers="headersExamination"
          :items="allScheduledExaminationForDermatologistList"
          :items-per-page="10"
        >
          <template v-slot:top>
            <v-toolbar dense dark color="light-blue darken-2">
              <v-spacer></v-spacer>
              <v-toolbar-title class="text-center">
                Scheduled examination in {{ pharmacyName }} pharmacy with
                dermatologist {{ dermatologist }}
              </v-toolbar-title>
              <v-spacer></v-spacer>
            </v-toolbar>
          </template>
        </v-data-table>
      </div>
      <div v-if="showShift || showValidFor">
        <h2 id="shiftText">{{ shift }}</h2>
        <h3 id="validForText">{{ validFor }}</h3>
      </div>
      <v-card-text v-if="showDateAndTime">
        <v-form class="mx-auto" width="30%">
          <v-menu
            v-model="menu1"
            :close-on-content-click="false"
            class="pl-5 mt-10"
            :nudge-right="460"
            transition="scale-transition"
            offset-y
            min-width="auto"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-text-field
                v-model="dateExamination"
                label="Choose date for examination"
                prepend-icon="mdi-calendar"
                :allowed-dates="disablePastDates"
                v-bind:readonly="true"
                class="examinationField2"
                v-bind="attrs"
                v-on="on"
              ></v-text-field>
            </template>

            <v-date-picker
              v-model="dateExamination"
              :allowed-dates="disablePastDates"
              color="green lighten-1"
              header-color="primary"
              @input="menu1 = false"
            ></v-date-picker>
          </v-menu>

          <v-menu
            ref="menu1"
            v-model="menu2"
            :close-on-content-click="false"
            :nudge-right="460"
            class="pl-5 mt-10"
            :return-value.sync="time"
            transition="scale-transition"
            offset-y
            min-width="16%"
            max-width="25%"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-text-field
                v-model="time"
                label="Choose the start time of the examination"
                prepend-icon="mdi-clock-time-four-outline"
                v-bind:readonly="true"
                class="examinationField2"
                v-bind="attrs"
                v-on="on"
              ></v-text-field>
            </template>
            <v-time-picker
              v-if="menu2"
              v-model="time"
              full-width
              header-color="primary"
              color="green lighten-1"
              @click:minute="$refs.menu1.save(time)"
            ></v-time-picker>
          </v-menu>

          <v-text-field
            v-model="examinationDuration"
            type="number"
            class="examinationField1"
            min="0"
            label="Enter duration for examination"
            color="primary"
          ></v-text-field>

          <v-text-field
            v-model="examinationPrice"
            type="number"
            class="examinationField1"
            min="0"
            label="Enter price ( RSD ) for examination"
            color="primary"
          ></v-text-field>
        </v-form>
      </v-card-text>
      <v-card-actions class="justify-center" v-if="showBtn">
        <v-btn
          @click="createFreeTerm"
          color="primary"
          class="btnCreate"
          x-large
          width="30%"
          >Create</v-btn
        >
        <v-btn
          v-on:click="cancel"
          color="primary"
          class="btnCancel"
          x-large
          width="30%"
          >Cancel</v-btn
        >
      </v-card-actions>
    </v-card>
  </div>
</template>


<script>
export default {
  name: "DefineFreeTermWithDermatologist",
  data: () => ({
    pharmacyAdmin: null,
    dermatologistList: [],
    dermatologistVacationList: [],
    allScheduledExaminationForDermatologistList: [],
    pharmcayId: localStorage.getItem("pharmacyId"),
    pharmacyName: "",
    dermatologist: "",
    selectedDermatologist: null,
    headers: [
      {
        text: "Dermatologist ID",
        value: "userId",
        align: "center",
        sortable: true,
      },
      {
        text: "First name",
        value: "firstName",
        align: "center",
        sortable: true,
      },
      {
        text: "Last name",
        value: "lastName",
        align: "center",
        sortable: true,
      },
      {
        text: "E-mail",
        value: "email",
        align: "center",
        sortable: true,
      },
      {
        text: "Phone number",
        value: "phoneNumber",
        align: "center",
        sortable: true,
      },
      {
        value: "action",
        aling: "center",
        sortable: false,
      },
    ],
    headersVacation: [
      {
        text: "Dermatologist name",
        value: "dermatologistName",
        align: "center",
        sortable: true,
      },
      {
        text: "Email",
        value: "email",
        align: "center",
        sortable: true,
      },
      {
        text: "Description",
        value: "description",
        align: "center",
        sortable: true,
      },
    ],
    headersExamination: [
      {
        text: "Examination ID",
        value: "appointmentId",
        align: "center",
        sortable: true,
      },
      {
        text: "Date and time",
        value: "dateAndTime",
        align: "center",
        sortable: true,
      },
      {
        text: "Duration",
        value: "duration",
        align: "center",
        sortable: true,
      },
      {
        text: "Price ( RSD )",
        value: "price",
        align: "center",
        sortable: true,
      },
    ],
    showDermatologistVacationList: false,
    showAllScheduledExaminationForDermatologistList: false,
    showShift: false,
    showValidFor: false,
    shift: "",
    validFor: "",
    menu1: false,
    time: null,
    menu2: false,
    dateExamination: new Date(
      Date.now() - new Date().getTimezoneOffset() * 60000
    )
      .toISOString()
      .substr(0, 10),
    showDateAndTime: false,
    examinationDuration: null,
    examinationPrice: null,
    showBtn: false,
    freeTerm: null,
  }),
  mounted() {
    this.initialize();
  },
  methods: {
    disablePastDates(val) {
      return (
        val >=
        new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
          .toISOString()
          .substr(0, 10)
      );
    },
    initialize() {
      this.axios
        .get(
          "http://localhost:8091/pharmacyAdmin/" +
            localStorage.getItem("userId"),
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          console.log(response.data);
          this.pharmacyAdmin = response.data;
        });

      this.axios
        .get("http://localhost:8091/pharmacy/" + this.pharmcayId, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((resp) => {
          console.log(resp.data);
          this.pharmacy = resp.data;
          this.pharmacyName = this.pharmacy.name;
        });

      this.axios
        .get(
          "http://localhost:8091/dermatologists/allDermatologistByPharmacyId/" +
            this.pharmcayId,
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => (this.dermatologistList = response.data));
    },
    selectDermatologist(item) {
      console.log(item);
      this.selectedDermatologist = item;
      this.axios
        .get(
          "http://localhost:8091/dermatologistVacation/isOnVacation/" +
            item.userId,
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          this.dermatologistVacationList = response.data;
          if (this.dermatologistVacationList.length > 0) {
            this.showDermatologistVacationList = true;
            this.dermatologist = item.firstName + " " + item.lastName;
          } else {
            this.showDermatologistVacationList = false;
          }
        });

      this.axios
        .get(
          "http://localhost:8091/workScheduleDermatologist/findShiftByDermatologistId/" +
            item.userId,
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          this.shift = response.data;
          if (this.shift != "") {
            this.showShift = true;
          } else {
            this.showShift = false;
          }
        });

      this.axios
        .get(
          "http://localhost:8091/workScheduleDermatologist/findValidForByDermatologistId/" +
            item.userId,
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          this.validFor = response.data;
          if (this.validFor != "") {
            this.showValidFor = true;
          } else {
            this.showValidFor = false;
          }
        });

      this.axios
        .get(
          "http://localhost:8091/examination/findAllScheduledExaminationInPharmacyByDermatologist/" +
            item.userId,
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          this.allScheduledExaminationForDermatologistList = response.data;
          if (this.allScheduledExaminationForDermatologistList.length > 0) {
            this.showAllScheduledExaminationForDermatologistList = true;
          } else {
            this.showAllScheduledExaminationForDermatologistList = false;
          }
        });

      this.showDateAndTime = true;
      this.showBtn = true;
    },
    createFreeTerm() {
      console.log(this.selectedDermatologist);

      if (
        this.examinationPrice == null ||
        this.examinationDuration == null ||
        this.dateExamination == null ||
        this.time == null
      ) {
        alert(
          "The fields based on which the create free term for dermatologist is performed must not be empty!"
        );
      } else if (this.examinationPrice <= 0 || this.examinationDuration <= 0) {
        alert(
          "Examination price and examination duration must be positive number!"
        );
      } else {
        console.log(this.time);
        console.log(this.dateExamination);
        var timeString = this.time + ":00";
        console.log(timeString);
        var dateAndTimeExamination = new Date(
          this.dateExamination.toString() + " " + timeString
        );
        console.log(dateAndTimeExamination);

        this.axios
          .post(
            "http://localhost:8091/examination/createFreeTermExamination",
            {
              dermatologist: this.selectedDermatologist,
              pharmacy: this.pharmacy,
              dateAndTimeExamination: dateAndTimeExamination,
              duration: this.examinationDuration,
              price: this.examinationPrice,
            },
             {
              headers: {
                Authorization: "Bearer " + localStorage.getItem("token"),
              },
            }
          )
          .then((response) => {
            this.freeTerm = response.data;
            console.log(this.freeTerm);
            alert(this.freeTerm.answer);
            if (this.freeTerm.status) {
              window.location.href = "/homePagePharmacyAdmin"; 
            } else {
               location.reload();
            }
          });
      }
    },
    cancel() {
      alert("Canceled defining free term with dermatologist!");
      window.location.href = "/homePagePharmacyAdmin";
    },
  },
};
</script>


<style scoped>
#defineFreeTermWithDermatologistCaption {
  margin-top: 2%;
  margin-bottom: 2%;
  color: rgb(2, 2, 117);
  text-align: center;
  font-weight: bold;
}
#defineFreeTermWithDermatologistCard {
  width: 80%;
  text-align: center;
  margin: auto;
}
#shiftText {
  color: rgb(2, 2, 117);
  text-align: center;
}
#validForText {
  color: rgb(2, 2, 117);
  text-align: center;
}
.examinationField1 {
  padding-left: 22%;
  width: 80%;
}
.examinationField2 {
  padding-left: 20%;
  width: 80%;
}
.btnCreate {
  margin: 5%;
  padding-top: 10%;
}
.btnCancel {
  margin-top: 5%;
  margin-left: 22%;
  margin-bottom: 5%;
  padding-top: 10%;
}
</style>