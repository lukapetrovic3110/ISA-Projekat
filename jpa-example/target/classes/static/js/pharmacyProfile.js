Vue.component("pharmacyProfile", {
    data: function() {
        return{
			name: '',
        	addressStreet: '',
			streetNumber: '',
			city: '',
			country: '',
			raiting: '',
			description: '',
			pharmacy: null,
			backup:[]
        }
    },
    template:`
    <div id="pharmacyProfile">
		<h1>Pharmacy Profile</h1>
			<div id="pharmacyProfileForm" class="form-group" class="container">
				<form>
					<label for="exampleFormControlInput1">Pharmacy Name</label>
					<input type="text" class="form-control" id="exampleFormControlInput1" disabled="true" v-model="name" name="name"/>
				
					<label for="exampleFormControlInput1">Pharmacy Street</label>
					<input type="text" class="form-control" id="exampleFormControlInput1" disabled="true" v-model="addressStreet" name="addressStreet"/>

					<label for="exampleFormControlInput1">Pharmacy Street Number</label>
					<input type="number" class="form-control" id="exampleFormControlInput1" disabled="true" v-model="streetNumber" name="streetNumber"/>


					<label for="exampleFormControlInput1">Pharmacy City</label>
					<input type="text" class="form-control" id="exampleFormControlInput1" disabled="true" v-model="city" name="city"/>
					

					<label for="exampleFormControlInput1">Pharmacy Country</label>
					<input type="text" class="form-control" id="exampleFormControlInput1" disabled="true" v-model="country" name="country"/>

					<label for="exampleFormControlInput1">Pharmacy Raiting</label>
					<input type="number" class="form-control" id="exampleFormControlInput1" disabled="true" v-model="raiting" name="raiting"/>
					
					<label for="exampleFormControlInput1">Pharmacy Description</label>
					<input type="text" class="form-control" id="exampleFormControlInput1" disabled="true" v-model="description" name="description"/>
				</form>	
			</div>
    </div>
    `,
    mounted(){
        axios
		.get('pharmacy/getPharmacyById/1')
		.then(response => {
		if(response.data != null){
				this.pharmacy = response.data;
				this.name = response.data.name;
				this.addressStreet = response.data.street;
				this.streetNumber = response.data.streetNumber;
				this.city = response.data.city;
				this.country = response.data.country;
				this.raiting = response.data.raiting;
				this.description = response.data.description;
				this.backup = [this.name, this.addressStreet, this.streetNumber, this.city, this.country , this.raiting, this.description];
		}
		});
    },
    methods: {

    }
  
});