Vue.component("patient",{
	data: function(){
		return{
			mode: 'BROWSE',
			patient: null,
			loyalty_card: null,
			medicine: [],
			patient_id: null
	}
},
template:
	`
	<div>
	<h1 style = "margin:0 auto; text-align:center;">User profile</h1>
	<br/>
		<table style = "margin:0 auto; padding: 20px;">
		<tr>
		<th><h3>Personal information</h3></th>
		</tr>
		<tr>
		<th>Name</th>
		<td><input type = "text" v-model = "patient.firstName" v-bind:disabled = "mode =='BROWSE'"/></td>
		</tr>
		<tr>
		<th>Last Name</th>
		<td><input type = "text" v-model = "patient.lastName" v-bind:disabled = "mode =='BROWSE'"/></td>
		</tr>
		<tr>
		<th>Phone Number</th>
		<td><input type = "text" v-model = "patient.phoneNumber" v-bind:disabled = "mode =='BROWSE'"/></td>
		</tr>
		<tr>
		<th>Country</th>
		<td><input type = "text" v-model = "patient.address.country" v-bind:disabled = "mode =='BROWSE'"/></td>
		</tr>
		<tr>
		<th>City</th>
		<td><input type = "text" v-model = "patient.address.city" v-bind:disabled = "mode =='BROWSE'"/></td>
		</tr>
		<tr>
		<th>Street</th>
		<td><input type = "text" v-model = "patient.address.street" v-bind:disabled = "mode =='BROWSE'"/></td>
		</tr>
		<tr>
		<th>Home Number</th>
		<td><input type = "text" v-model = "patient.address.streetNumber" v-bind:disabled = "mode =='BROWSE'"/></td>
		</tr>
		<tr>
		<th>E-mail</th>
		<td>{{patient.email}}</td>
		</tr>
		<tr>
        <tr>
		<th>Password</th>
		<td><input type = "text" v-model = "patient.password" v-bind:disabled = "mode =='BROWSE'"/></td>
		</tr>
		</table>
		
		<table style = "margin:0 auto; padding: 20px;">
		<tr>
		<th><h3>Loyalty card</h3></th>
		</tr>
		<tr>
		<th>Loyalty Points</th>
		<td>{{this.loyalty_card.points}}</td>
		</tr>
		<tr>
		<th>Loyalty Category</th>
		<td>{{this.loyalty_card.loyaltyCategory}}</td>
		</tr>
		</table>
		
		<table style = "margin:0 auto; padding: 20px;">
		<tr>
		<th><h3>Allergies to medicine</h3></th>
		</tr>
		<tr v-for="m in patient.allergy.medicine">
		<td>{{m.name}}</td>
		</tr>
		</table>
		
		<table style = "margin:0 auto; padding: 20px;">
		<tr>
		<th><h3>Medicine</h3></th>
		</tr>
		<tr v-for="med in medicine">
		<td>{{med.name}}</td>
		<td><button  type="button" v-on:click = "addAllergy(med)" v-bind:disabled = "mode !='EDIT'" >Add allergy to medicine</button>
		</td>
		</tr>
		</table>
		
		<table style = "margin:0 auto; padding: 20px;">
		<tr>
		<td><button type="button" v-on:click = "editInformation" type="button" v-bind:disabled ="mode !='BROWSE'">Edit</button>
		</td>
		<td><button  type="button" v-on:click = "saveInformation" v-bind:disabled = "mode =='BROWSE'" >Save</button>
		</td>
		</tr>
		<table>
		
		
	</div>
	`,
	mounted(){
		axios
		.get('/application/users/1')
		.then(response => (this.patient = response.data));
		axios
		.get('/application/loyaltyCard/user/1')
		.then(response => (this.loyalty_card = response.data));
		
	},
	methods: {
	
	editInformation : function(event){
		this.mode = "EDIT";
		this.patient_id = this.patient.userId;
		
		axios
		.put('/application/medicine/forAllergies', this.patient.allergy)
		.then(response => (this.medicine = response.data));
		},
		
	saveInformation : function(event){
		this.mode = "BROWSE";
		
		axios
				.post("/application/users/update", this.patient);
		
		axios
		.put('/application/medicine/forAllergies', this.patient.allergy)
		.then(response => (this.medicine = response.data));
		},
	addAllergy :function(med){
		
		this.mode = "ADD";
		
		axios
		.put("/application/allergy/" + this.patient_id + "/add", med)
		.then(response => (this.patient.allergy.medicine = response.data));
		
		}
	}
});