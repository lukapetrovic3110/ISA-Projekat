<template>
  <v-card style="margin-top: 3%" width="40%" class="mx-auto">
    <v-card-title class="justify-center">
      <h1 class="display-1 mt-20">Register Pharmacy</h1>
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
          label="Street Name"
          v-model="streetName"
          :rules="[() => !!streetName || 'This field is required']"
          :error-messages="errorMessages"
          prepend-icon="mdi-account-circle"
        />
         <v-text-field
          label="Street Number"
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
          label="Description"
          v-model="description"
          :rules="[() => !!description || 'This field is required']"
          :error-messages="errorMessages"
          prepend-icon="mdi-account-circle"
        />
        <v-text-field
          label="Consultation Price"
          v-model="consultationPrice"
          :rules="[() => !!consultationPrice || 'This field is required']"
          :error-messages="errorMessages"
          prepend-icon="mdi-account-circle"
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
        v-on:click="register">Register</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name: "CreatePharmacy",
  data: () => ({
    countries: ['Afghanistan', 'Albania', 'Algeria', 'Andorra', 'Angola', 'Anguilla', 'Antigua &amp; Barbuda', 'Argentina', 'Armenia', 'Aruba', 'Australia', 'Austria', 'Azerbaijan', 'Bahamas', 'Bahrain', 'Bangladesh', 'Barbados', 'Belarus', 'Belgium', 'Belize', 'Benin', 'Bermuda', 'Bhutan', 'Bolivia', 'Bosnia &amp; Herzegovina', 'Botswana', 'Brazil', 'British Virgin Islands', 'Brunei', 'Bulgaria', 'Burkina Faso', 'Burundi', 'Cambodia', 'Cameroon', 'Cape Verde', 'Cayman Islands', 'Chad', 'Chile', 'China', 'Colombia', 'Congo', 'Cook Islands', 'Costa Rica', 'Cote D Ivoire', 'Croatia', 'Cruise Ship', 'Cuba', 'Cyprus', 'Czech Republic', 'Denmark', 'Djibouti', 'Dominica', 'Dominican Republic', 'Ecuador', 'Egypt', 'El Salvador', 'Equatorial Guinea', 'Estonia', 'Ethiopia', 'Falkland Islands', 'Faroe Islands', 'Fiji', 'Finland', 'France', 'French Polynesia', 'French West Indies', 'Gabon', 'Gambia', 'Georgia', 'Germany', 'Ghana', 'Gibraltar', 'Greece', 'Greenland', 'Grenada', 'Guam', 'Guatemala', 'Guernsey', 'Guinea', 'Guinea Bissau', 'Guyana', 'Haiti', 'Honduras', 'Hong Kong', 'Hungary', 'Iceland', 'India', 'Indonesia', 'Iran', 'Iraq', 'Ireland', 'Isle of Man', 'Israel', 'Italy', 'Jamaica', 'Japan', 'Jersey', 'Jordan', 'Kazakhstan', 'Kenya', 'Kuwait', 'Kyrgyz Republic', 'Laos', 'Latvia', 'Lebanon', 'Lesotho', 'Liberia', 'Libya', 'Liechtenstein', 'Lithuania', 'Luxembourg', 'Macau', 'Macedonia', 'Madagascar', 'Malawi', 'Malaysia', 'Maldives', 'Mali', 'Malta', 'Mauritania', 'Mauritius', 'Mexico', 'Moldova', 'Monaco', 'Mongolia', 'Montenegro', 'Montserrat', 'Morocco', 'Mozambique', 'Namibia', 'Nepal', 'Netherlands', 'Netherlands Antilles', 'New Caledonia', 'New Zealand', 'Nicaragua', 'Niger', 'Nigeria', 'Norway', 'Oman', 'Pakistan', 'Palestine', 'Panama', 'Papua New Guinea', 'Paraguay', 'Peru', 'Philippines', 'Poland', 'Portugal', 'Puerto Rico', 'Qatar', 'Reunion', 'Romania', 'Russia', 'Rwanda', 'Saint Pierre &amp; Miquelon', 'Samoa', 'San Marino', 'Satellite', 'Saudi Arabia', 'Senegal', 'Serbia', 'Seychelles', 'Sierra Leone', 'Singapore', 'Slovakia', 'Slovenia', 'South Africa', 'South Korea', 'Spain', 'Sri Lanka', 'St Kitts &amp; Nevis', 'St Lucia', 'St Vincent', 'St. Lucia', 'Sudan', 'Suriname', 'Swaziland', 'Sweden', 'Switzerland', 'Syria', 'Taiwan', 'Tajikistan', 'Tanzania', 'Thailand', `Timor L'Este`, 'Togo', 'Tonga', 'Trinidad &amp; Tobago', 'Tunisia', 'Turkey', 'Turkmenistan', 'Turks &amp; Caicos', 'Uganda', 'Ukraine', 'United Arab Emirates', 'United Kingdom', 'United States', 'Uruguay', 'Uzbekistan', 'Venezuela', 'Vietnam', 'Virgin Islands (US)', 'Yemen', 'Zambia', 'Zimbabwe'],
    users: [],
  }),
  computed: {},
  methods: {
    register() {
      let token = localStorage.getItem("token");
      this.axios
        .post("http://localhost:8091/pharmacy/createPharmacy",  {
          name: this.name,
          address: {
            street: this.streetName,
            streetNumber: this.streetNumber,
            city: this.city,
            country: this.country
          },
          description: this.description,
          consultationPrice: this.consultationPrice,
          },{
         headers: {
              Authorization: 'Bearer ' + token
        }})
        .then(() => {
            alert("Pharmacy is successfully registred!");

        })
        .catch((er) => {
          alert("Error during registration!");
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