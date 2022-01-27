Vue.component("searchMedicine", {
	data: function () {
		    return {
		    	medicines: [],
		    	availableInPharmacies: [],
		    	searchMedicine: ""
		    }
	},
	template: `
	<div id = "searchMedicine">	

			<h2>Search Medicine</h2>
			<br>
			<label>Search Medicines:</label>
			<input type="search" v-model = "searchMedicine">
			<button type="submit" v-on:click = "searchMedicines">Search</button>
			<br>
			<label class = "filtrate">Filtrate:</label>
			<button type="submit">Rating</button>
			
			<div class = "container">
			<table class = "table">
				<tr style = "background-color: #60A0A0;">
					<td>Name</td>
					<td>Type</td>
					<td>Rating</td>
				</tr>
				<tr v-for="m in medicines">
					<td>{{m.name}}</td>
					<td>{{m.type}}</td>
					<td>{{m.rating}}</td>
				</tr>
			</table>
			</div>
			
			<div class = "container">
			
				<h3>Available In</h3>
		
				<table class = "table">
					<tr style = "background-color: #60A0A0;">
						<td>Name</td>
						<td>Price</td>
					</tr>
					<tr v-for="a in availableInPharmacies">
						<td>{{a.name}}</td>
						<td>{{a.priceTag}}</td>
					</tr>
				</table>
			</div>
		</div>
		`,
		mounted(){
		axios
		.get('/application/medicine')
		.then(response => (this.medicines = response.data));
		axios
		.get('/application/pharmacy')
		.then(response => (this.availableInPharmacies = response.data));
	},
	methods: {
		searchMedicines: function() {
			axios
			.get("/application/medicine/getMedicineByName/" + this.searchMedicine)
			.then(response => (this.medicines = response.data));
			}
	}
	});