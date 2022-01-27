<template>
  <div>
    <div class = "container">
      <v-simple-table>
        <template v-slot:default>
          <tr>
            <td align="center"><v-btn
                color="primary"
                elevation="2"
                medium
                v-on:click= "examinationStart"
                :disabled="isActive"
            >Start Consultation</v-btn></td>
            <td align="center"><v-btn
                color="primary"
                elevation="2"
                medium
                v-on:click="patientDidntCome"
                :disabled="isPatientCome"
            >Patient did not come.</v-btn></td>
          </tr>
        </template>
      </v-simple-table>
      <div v-if="this.mode == 'STARTED'">
        <br><br>
        <v-textarea
            background-color="white"
            filled
            name="input-7-4"
            label="Information about consultation:"
            v-model = "infoAppointment"
        ></v-textarea>
        <h3>Browse new medicine for patient:</h3>
        <v-simple-table>
          <template v-slot:default>
            <thead>
            <tr>
              <th class="text-left" width="570">
                Name
              </th>
              <th class="text-left">
                Medicine specification
              </th>
              <th class="text-left">
                Availability
              </th>
            </tr>
            </thead>
            <tbody>
            <tr
                v-for="m in medicines"
                :key="m"
            >
              <td>{{ m.name }}</td>
              <td><v-col cols="auto">
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
                    >Medicine Specification</v-btn>
                  </template>
                  <template v-slot:default="dialog">
                    <v-card>
                      <v-toolbar
                          color="primary"
                          dark
                      >Medicine Specification</v-toolbar>
                      <v-simple-table>
                        <template v-slot:default>
            <tr>
              <td  width="40"></td>
              <td class="text-left" >Name:</td>
              <td  width="50"></td>
              <td class="text-left">{{ m.name }}</td>
            </tr>
            <br>
            <tr>
              <td  width="40"></td>
              <td class="text-left" width="240">Dosage:</td>
              <td  width="50"></td>
              <td class="text-left">{{ m.medicineSpecification.dosage }}</td>
            </tr>
            <br>
            <tr>
              <td  width="40"></td>
              <td class="text-left" width="240">Ingridients:</td>
              <td  width="50"></td>
              <td class="text-left">{{ m.medicineSpecification.ingridient }}</td>
            </tr>
            <br>
            <tr>
              <td  width="40"></td>
              <td class="text-left" width="240">Contraindications:</td>
              <td  width="50"></td>
              <td class="text-left">{{ m.medicineSpecification.contraindication }}</td>
            </tr>
            <br>
          </template>
        </v-simple-table>
        <v-btn
            width="300"
            text
            @click="dialog.value = false"
        >Close</v-btn>
        </v-card>
</template>
</v-dialog>
</v-col>
</td>
<td><v-col cols="auto">
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
          v-on:click="findSubstituteMedicine(m)"
      >Is medicine Available?</v-btn>
    </template>
    <template v-slot:default="dialog">
      <v-card>
        <v-toolbar
            color="primary"
            dark
        >Is medicine available?</v-toolbar>
        <v-simple-table>
          <template v-slot:default>
            <tbody v-if="medicineAvailable == false && notAvailable == false">
            <tr><th colspan="2" class="text-center">Browse substitute medicine:</th></tr>
            <tr>
              <th class="text-center" width="250">
                Name
              </th>
              <th class="text-center">
                Choose
              </th>
            </tr>
            <tr>
            <tr
                v-for="sm in substituteMedicines"
                :key="sm"
            >
              <td align="center">{{ sm.name }}</td>

              <td align="center">
                <v-btn
                    color="primary"
                    elevation="2"
                    small
                    v-on:click="chooseSubstitute(sm)">
                  Choose
                </v-btn>
              </td>
            </tr>
            </tbody>
            <tbody v-else-if="notAvailable">
            <tr><th colspan="2" class="text-center">Medicine is not available and it does not have replacement!</th></tr>
            </tbody>
            <tbody v-else>
            <tr><th colspan="2" class="text-center">Medicine is available!</th></tr>
            </tbody>
          </template>
        </v-simple-table>
        <h4 align="center" style="margin-top:20px;">Duration of therapy (in days):</h4>
        <v-text-field
            v-model= "durRecommend"
            type="number" min = "1" oninput="validity.valid||(value='');"
            filled
            style="margin-top:10px; font-size:14px;">
        </v-text-field>
        <br>
        <v-btn
            width="300"
            text
            @click="dialog.value = false"
        >Close</v-btn>
        <v-btn
            width="300"
            text
            @click="dialog.value = false"
            v-on:click="recommendMedicine"
            :disabled="notAvailable"
        >Recommend</v-btn>
      </v-card>
    </template>
  </v-dialog>
</v-col>
</td>
</tr>
</tbody>
</template>
</v-simple-table>
<br>
<br>
<table>
<tr>
  <td align="center" width="650">
    <v-btn
        color="primary"
        elevation="2"
        v-on:click="home"
        large
    >HomePage</v-btn></td>
  <td align="center" width="650">
    <v-btn
        color="primary"
        elevation="2"
        :disabled="isEnded"
        v-on:click="endConsultation"
        large
    >End Consultation</v-btn></td>
  <td align="center"><v-btn color="primary" elevation="2" large
   :disabled:="ended"
    v-on:click="scheduleNew"
  >Schedule New Consultation</v-btn></td>
</tr>
</table>
</div>
</div>
</div>
</template>

<script>
export default {
  name: "ReportForConsultation",
  data: function () {
    return {
      pharmacist: null,
      dateStart: new Date().getDate(),
      currentExamination: null,
      endedRep: false,
      ended: true,
      medicines: [],
      substituteMedicines: [],
      patient: null,
      notAvailable: false,
      mode: '',
      mmm: [],
       med: [],
      isActive: false,
      isPatientCome: false,
      available: true,
      medicineAvailable: false,
      patient_id: localStorage.getItem("patientId"),
      durRecommend: "",
      infoAppointment: "",
      recommendationsDTO: [],
      recommendationDTO: {
        medicine: [],
        duration: ''
      }
      ,
      reportDTO: [{
        info: '',
        appointmentId: null,
        recommendations: []
      }
      ],
      isEnded: false,
    }
  },
  mounted() {

    if(localStorage.getItem("userType") == "PHARMACIST"){

      this.axios
          .get('http://localhost:8091/users/' + localStorage.getItem("userId"), {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }})
          .then(response => (this.pharmacist = response.data))

      this.axios
          .get('http://localhost:8091/users/' + this.patient_id, {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }})
          .then(response => (this.patient = response.data,
              this.axios
                  .put('http://localhost:8091/medicine/forAllergies', this.patient.allergy, {
                    headers: {
                      Authorization: 'Bearer ' + localStorage.getItem("token")
                    }
                  })
                  .then(response => (this.medicines = response.data)
                  )))
    }  else {
      window.location.href = "http://localhost:8080/logIn";
    }

  },
  methods: {
    patientDidntCome: function() {
      this.dateStart = new Date()

      if(localStorage.getItem("patientId") != null) {
        const dfa = {
          dateAndTime: this.dateStart,
          pharmId: this.pharmacist.userId,
          patientId: localStorage.getItem("patientId")
        }

        this.axios
            .put('http://localhost:8091/consultation/findCurrentTerm', dfa, {
              headers: {
                Authorization: 'Bearer ' + localStorage.getItem("token")
              }
            })
            .then(response => {
              this.currentExamination = response.data
              if (this.currentExamination.appointmentId != null) {
              this.isPatientCome = true
              this.isActive = true
              this.axios
                .post('http://localhost:8091/users/increasePenaltyConsultation', this.currentExamination, {
                  headers: {
                    Authorization: 'Bearer ' + localStorage.getItem("token")
                  }})
                .then(
                    window.location.href = "http://localhost:8080/homePagePharmacist"
                )
              }else{
                alert("There is no scheduled appointment at this time!")
              }
            })
      }
      else {
        alert("You did not choose patient for examination!")
      }
    },
    examinationStart: function (){
      this.ended = true
      this.dateStart = new Date()
      if(localStorage.getItem("patientId") != "") {

        const dfa = {
          dateAndTime: this.dateStart,
          pharmId: this.pharmacist.userId,
          patientId: localStorage.getItem("patientId")
        }

        this.axios
            .put('http://localhost:8091/consultation/findCurrentTerm', dfa, {
              headers: {
                Authorization: 'Bearer ' + localStorage.getItem("token")
              }
            })
            .then(response => {
              this.currentExamination = response.data
              if (this.currentExamination.appointmentId != null) {
                this.mode = 'STARTED',
                    this.isActive = true
                localStorage.setItem("appointmentId", this.currentExamination.appointmentId)
                localStorage.setItem("pharmacyId", this.currentExamination.pharmacy.pharmacyId)
                localStorage.setItem("patientId", this.currentExamination.patient.userId)
                console.log(this.currentExamination)
              } else {
                alert("There is no scheduled appointment at this time!")
              }
            })
      } else{
        alert("You did not choose patient for consultation!")
      }
    },
    endConsultation: function(){
      this.isEnded = true
      this.reportDTO = {
        info: this.infoAppointment,
        appointmentId : this.currentExamination.appointmentId,
        recommendations: this.recommendationsDTO
      }
      this.axios
          .post('http://localhost:8091/medicine/addReportPharm/' + this.currentExamination.appointmentId, this.reportDTO, {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }})
          .then(response => {
              this.endedRep = response.data
              if(this.endedRep != null){
                    this.ended = false;
                    localStorage.removeItem("appointmentId")
                    localStorage.removeItem("patientId")
              }
            })
    },
    findSubstituteMedicine: function (sm){
      this.med = sm

      const swa={
        oldMedicine: this.med,
        medicinesWithoutAllergy: this.medicines
      }

      const ca={
        pharmacyId: this.currentExamination.pharmacy.pharmacyId,
        medicineAvailable: this.med
      }

      this.axios
          .put('http://localhost:8091/medicine/checkAvailability', ca, {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
          })
          .then(response => {
            this.available = response.data
            if (this.available == false) {
              this.medicineAvailable = false
              this.axios
                  .put('http://localhost:8091/medicine/substituteMedicine', swa, {
                    headers: {
                      Authorization: 'Bearer ' + localStorage.getItem("token")
                    }
                  })
                  .then(response => {
                    this.substituteMedicines = response.data
                    if(!this.substituteMedicines.length){
                      this.notAvailable = true
                      alert("Medicine is not available and it does not have replacement.")
                    }
                  })
            } else {
              this.mmm = this.med
              this.notAvailable = false
              this.medicineAvailable = true
            }
          })
    },
    chooseSubstitute: function(smed){
      this.mmm = smed

      const ca={
        pharmacyId: localStorage.getItem("pharmacyId"),
        medicineAvailable: this.mmm
      }

      this.axios
          .put('http://localhost:8091/medicine/checkAvailability', ca, {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
          }) .then(response => this.available = response.data)
    },
    recommendMedicine: function(){
      this.recommendationDTO={
        medicine: this.mmm,
        duration: this.durRecommend
      }

      this.notAvailable = false
      this.recommendationsDTO.push(this.recommendationDTO)
    },
    scheduleNew(){
      this.reportDTO = {
        info: this.infoAppointment,
        appointmentId: this.currentExamination.appointmentId,
        recommendations: this.recommendationsDTO
      }

      this.axios
          .post('http://localhost:8091/medicine/addReportPharmAndSchedule/' + this.currentExamination.appointmentId, this.reportDTO, {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }})
          .then( response => {
            this.currentExamination = response.data
            localStorage.setItem("appointmentId", this.currentExamination.appointmentId)
            localStorage.setItem("patientId", this.currentExamination.patient.userId)
            localStorage.setItem("pharmacyId", this.currentExamination.pharmacy.pharmacyId)
            window.location.href = "http://localhost:8080/scheduleConsultationPharmacist";
          })
    },
    home(){
      window.location.href = "http://localhost:8080/homePagePharmacist";
    }
  }
}
</script>

