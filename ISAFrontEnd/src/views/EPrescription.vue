<template>
  <div id = "searchMedicine">
    <br>
    <div class = "container">
          <template>
                <input type="file" id="file" ref="file"  v-on:change="handleFileUpload()" />
                 <v-btn
                    color="secondary"
                    elevation="3"
                    small
                    v-on:click = "submitFile()"
                  >Check Availability</v-btn> 
          </template>
          </div>
    <br>
    <div class = "container">
    <h3>Available In:</h3>
      <v-simple-table>
        <template v-slot:default>
          <thead>
          <tr>
            <th class="text-left">
              Name
            </th>
            <th class="text-left">
              Medicine
            </th>
            <th class="text-left">
              Price
            </th>
          </tr>
          </thead>
          <tbody>
          <tr
              v-for="a in result.pharmacyAvailabilityDTO" :key="a"
          >
            <td>{{ a.pharmacy.name }}</td>
            <td>{{ a.sumPrice}}</td>
            <td> <v-btn
                    color="secondary"
                    elevation="3"
                    small
                    v-on:click = "buyMedicine(a)"
                  >Buy Medicine</v-btn> </td>
          </tr>
          </tbody>
        </template>
      </v-simple-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "EPrescription",
  data: function () {
    return {
      file: '',
      pharmacies: [],
      availableInPharmacies: [],
      result: {},
      result2: {},
      EPrescriptionList:[],
      EPrescriptionBuyMedicineDTO: [{
            code: '',
            qrCodeDTOs : [],
            pharmacyId : null
      }]
    }
  },
  mounted(){},
  methods: {
    handleFileUpload(){
      this.file = this.$refs.file.files[0];
    },
    submitFile(){
      let formData = new FormData();

      formData.append('file', this.file);
           

      this.axios.post( 'http://localhost:8091/ePrescription/file', formData, {
          headers: {
              'Content-Type': 'multipart/form-data',
                'Authorization': 'Bearer ' + localStorage.getItem('token')
          }
        }).then(response => (this.pharmacyAvailabilityDTO = response.data.pharmacyAvailabilityDTO,
                            this.qrcodeDTO = response.data.qrcodeDTO,
                             this.code = response.data.code,
                             this.result = response.data,
                             
                             this.dto = {
                               name: this.result.qrcodeDTO.name,
                               code: this.result.qrcodeDTO.code,
                               quantity: this.result.qrcodeDTO.quantity
                             },

                             this.EPrescriptionList.push(this.dto),
                             console.log(response.data.qrcodeDTO.name)
                             ));
                  
    },
    buyMedicine: function(a){

       this.EPrescriptionBuyMedicineDTO = {
            code: this.code,
            qrcodeDTO: this.EPrescriptionList,
            pharmacyId : a.pharmacy.pharmacyId
      }

      this.axios.post( 'http://localhost:8091/ePrescription/buyMedicineInPharmacy', 
         this.EPrescriptionBuyMedicineDTO, {
          headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('token')
          }
        }).then(response => (
          console.log("Successfully bought medicine"),
          this.result2 = response.data));
    }
  },

  computed:{
    isLogged: function (){
      if (this.token == ""){
        return false
      }else{
        return true
      }
    },
    notFilled: function () {
      if (this.searchMedicine.trim() == ""){
        return false
      }
      else {
        return true
      }
    }
  }
}
</script>

<style scoped>
#search{
  background-color: white;
}
#searchButton{
  color: white;
  border: white;
  border-width: 10px;
}

.container {
  display: block;
  position: relative;
  padding-left: 35px;
  margin-bottom: 12px;
  cursor: pointer;
  font-size: 18px;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}
input[type=radio]{
  border-radius: 10px;
  box-shadow: inset 0 1px 1px hsla(0,0%,100%,.8),
  0 0 0 1px hsla(0,0%,0%,.6),
  0 2px 3px hsla(0,0%,0%,.6),
  0 4px 3px hsla(0,0%,0%,.4),
  0 6px 6px hsla(0,0%,0%,.2),
  0 10px 6px hsla(0,0%,0%,.2);
  cursor: pointer;
  display: inline-block;
  height: 15px;
  margin-right: 15px;
  position: relative;
  width: 15px;
  -webkit-appearance: none;
}

input[type="radio"]:after {
  border-radius: 25px;
  box-shadow: inset 0 0 0 1px hsla(0,0%,0%,.4),
  0 1px 1px hsla(0,0%,100%,.8);
  content: '';
  display: block;
  height: 7px;
  left: 4px;
  position: relative;
  top: 4px;
  width: 7px;
}
input[type="radio"]:checked:after {
  background-color: #444;
  box-shadow: inset 0 0 0 1px hsla(0,0%,0%,.4),
  inset 0 2px 2px hsla(0,0%,100%,.4),
  0 1px 1px hsla(0,0%,100%,.8),
  0 0 2px 2px hsla(0,70%,70%,.4);
}

</style>