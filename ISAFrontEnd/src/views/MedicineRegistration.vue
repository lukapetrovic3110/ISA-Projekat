<template>
  <v-card style="margin-top: 3%" width="40%" class="mx-auto">
    <v-card-title class="justify-center">
      <h1 class="display-1 mt-20">Add Medicine</h1>
    </v-card-title>
    <v-card-text>
      <v-form class="mx-auto ml-20 mr-20">
        <v-text-field
          label="Name"
          v-model="name"
          :rules="[() => !!name || 'This field is required']"
          :error-messages="errorMessages"
          prepend-icon="mdi-account-circle"
        />
        <v-text-field
          label="Code"
          v-model="code"
          :rules="[() => !!code || 'This field is required']"
          :error-messages="errorMessages"
          prepend-icon="mdi-account-circle"
        />
        <v-select
            class = "countryCombo"
            v-model = "medicineForm"
            :items="['POWDER', 'CAPSULE', 'PILL', 'OINTMENT', 'PASTE', 'GEL', 'SOLUTION', 'SYRUP', 'NONE']"
            label="Medicine Form"
            required
        >
        </v-select>
        <v-select
            class = "countryCombo"
            v-model = "prescriptionType"
            :items="['NO_PRESCRIPTION', 'PRESCRIPTION']"
            label="Prescription Type"
            required
        >
        </v-select>
        <v-text-field
          label="Medicine Type"
          v-model="type"
          :rules="[() => !!type || 'This field is required']"
          :error-messages="errorMessages"
          prepend-icon="mdi-account-circle"
        />
        <v-text-field
          label="Loyalty Points"
          v-model="loyaltyPoints"
          :rules="[() => !!loyaltyPoints || 'This field is required']"
          :error-messages="errorMessages"
          prepend-icon="mdi-account-circle"
        />
        <v-text-field
          label="Manufacturer"
          v-model="manufacturer"
          :rules="[() => !!manufacturer || 'This field is required']"
          :error-messages="errorMessages"
          prepend-icon="mdi-account-circle"
        />
        <v-text-field
          label="Precautions"
          v-model="precautions"
          :rules="[() => !!precautions || 'This field is required']"
          :error-messages="errorMessages"
          prepend-icon="mdi-account-circle"
        />
    <div class = "container">
      <v-simple-table>
        <template v-slot:default>
          <thead>
          <tr>
            <th class="text-left">
              Replacement Medicine
            </th>
          </tr>
          </thead>
          <tbody>
          <tr
              v-for="med in medicine"
              :key="med"
          >
            <td>{{ med.name }}</td>
            <td>
              <v-btn
                  color="primary"
                dark
                v-bind="attrs"
                v-on:click="addReplacementMedicine(med)"
                v-on="on"
              >Add replacement medicine</v-btn>
            </td>
          </tr>
          </tbody>
        </template>
      </v-simple-table>
    </div>
    <br>

        <v-row justify="center">
            <v-dialog
            v-model="dialog"
            persistent
            max-width="600px"
            >
            <template v-slot:activator="{ on, attrs }">
                <v-btn
                color="primary"
                dark
                v-bind="attrs"
                v-on="on"
                >
                Medicine Specification
                </v-btn>
            </template>
            <v-card>
                <v-card-title>
                <span class="text-h5">Medicine Specification</span>
                </v-card-title>
                <v-card-text>
                <v-container>
                    <v-row>
                    <v-col cols="12">
                        <v-text-field
                        label="Dosage"
                        v-model= "dosage"
                        required
                        ></v-text-field>
                    </v-col>
                    </v-row>
                    <v-row>
                    <v-col>
                        <v-text-field
                        label="Ingridients"
                        v-model = "ingridientName"
                        required
                        ></v-text-field>
                        <v-btn
                          color="primary"
                          class = "buttonSpec"
                          dark
                          v-bind="attrs"
                          v-on:click="addIngridient(i)"
                          v-on="on"
                        >Add Ingridient</v-btn>
                    </v-col>
                    </v-row>
                    <v-row>
                    <v-col cols="12">
                        <v-text-field
                        label="Contraindication"
                        v-model = "description"
                        required
                        ></v-text-field>
                        <v-btn
                          class = "buttonSpec"
                          color="primary"
                          dark
                          v-bind="attrs"
                          v-on:click="addContraindication(c)"
                          v-on="on"
                        >Add Contraindication</v-btn>
                    </v-col>
                    </v-row>
                    
                </v-container>
                <small>*indicates required field</small>
                </v-card-text>
                <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                    color="blue darken-1"
                    text
                    @click="dialog = false"
                >
                    Close
                </v-btn>
                <v-btn
                    color="blue darken-1"
                    text
                    @click="dialog = false"
                >
                    Save
                </v-btn>
                </v-card-actions>
            </v-card>
            </v-dialog>
        </v-row>
      </v-form>
    </v-card-text>
    <div></div>
    <v-card-actions>
      <v-btn
        class="mx-auto mb-10 mt-10; color:white"
        color="accent"
        elevation="2"
        x-large
        raised
        v-on:click="register">Save</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name: "MedicineRegistration",
  data: () => ({
    dialog: false,
    contraindications: [],
    ingridients:[],
    medicine: null,
    replacementMed: [],
  }),
  mounted() {
      this.axios
        .get("http://localhost:8091/medicine",{
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
        })
        .then(r => (this.medicine = r.data));
  },
  computed: {},
  methods: {
    addReplacementMedicine: function(med){
          this.replacementMed.push(med)
          console.log(this.replacementMed);
    },
    addContraindication: function(c){
          this.contraindications.push(c)
          console.log(this.contraindications);
    },
    addIngridient: function(i){
          this.ingridients.push(i)
          console.log(this.ingridients);
    },
    register() {
    let token = localStorage.getItem("token");
      this.$http
        .post("http://localhost:8091/medicine/addMedicine", {
          name: this.name,
          code: this.code,
          medicineForm: this.medicineForm,
          type: this.type,
          prescriptionType: this.prescriptionType,
          loyaltyPoints: this.loyaltyPoints,
          replacementMedicine: this.replacementMed,
          precautions: this.precautions,
          manufacturer: this.manufacturer,
          medicineSpecification: {
                dosage: this.dosage,
                description: this.description,
                ingridientName: this.ingridientName,
        },
          headers: {
              Authorization: 'Bearer ' + token}
        })
        .then(() => {
            console.log('Successfully added medicine.');
        })
        .catch((er) => {
          alert("Error!");
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
.buttonSpec{
  width: 100%;
  cursor: pointer;
}
</style>