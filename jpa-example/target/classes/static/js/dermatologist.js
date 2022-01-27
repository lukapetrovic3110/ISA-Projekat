Vue.component("employee",{
	data: function () {
		    return {
				employee: null,
				mode: 'BROWSE',
				backup: []
			}				
}, 
	template: `
		<div>
			<table>
				<tr><th>HOME PAGE FOR:   {{employee.userType}}</th></tr>
				<tr><th>FirstName:</th><td><input type = "text" style = "width : 100%; padding: 10px; border: none; background: #f1f1f1;" v-model= "employee.firstName"  v-bind:disabled = "mode != 'EDIT'"/></td></tr>
				<tr><th>LastName:</th><td><input type = "text" style = "width : 100%; padding: 10px; border: none; background: #f1f1f1;" v-model= "employee.lastName"  v-bind:disabled = "mode != 'EDIT'"/></td><tr>
				<tr><th>PhoneNumber:</th><td><input type = "text" style = "width : 100%; padding: 10px; border: none; background: #f1f1f1;" v-model= "employee.phoneNumber"  v-bind:disabled = "mode != 'EDIT'"/></td><tr>
				<tr><th>Street:</td><th><input type = "text" style = "width : 100%; padding: 10px; border: none; background: #f1f1f1;" v-model= "employee.address.street"  v-bind:disabled = "mode != 'EDIT'"/></td></tr>
				<tr><th>StreetNumber</th><td><input type = "text" style = "width : 100%; padding: 10px; border: none; background: #f1f1f1;" v-model= "employee.address.streetNumber"  v-bind:disabled = "mode != 'EDIT'"/></td></tr>
				<tr><th>City</th><td><input type = "text" style = "width : 100%; padding: 10px; border: none; background: #f1f1f1;" v-model= "employee.address.city"  v-bind:disabled = "mode != 'EDIT'"/></td></tr>
				<tr><th>Country</th><td><input type = "text" style = "width : 100%; padding: 10px; border: none; background: #f1f1f1;" v-model= "employee.address.country"  v-bind:disabled = "mode != 'EDIT'"/></td></tr>
				<tr><th>E-mail:</th><td>{{employee.email}}</td><tr>
				<tr><th>Password:</th><td><input type = "text" style = "width : 100%; padding: 10px; border: none; background: #f1f1f1;" v-model= "employee.password" v-bind:disabled = "mode != 'EDIT'"/></td><tr>
				<tr>
					<td><button v-on:click = "editEmployee" style = "background-color:SlateBlue; padding: 10px; border: none; cursor: pointer; width:100%; opacity: 0.9;" type="button" v-bind:disabled = "mode != 'BROWSE'">Edit</button></td>
					<td><button v-on:click = "saveEmployee" style = "background-color:SlateBlue; padding: 10px; border: none; cursor: pointer; width:100%; opacity: 0.9;" type="button" v-bind:disabled = "mode != 'EDIT'">Save</button></td>
				</tr>	
			</table>
		</div>
		`,
	mounted() {
		axios
		.get('users/10')
		.then(response => (this.employee = response.data));
	},
	methods: {
		editEmployee : function () {
			this.mode = "EDIT";
		},
		saveEmployee : function () {
			this.mode = "BROWSE";
			axios
			.post("/application/users/update", this.employee);
		}
	}
});