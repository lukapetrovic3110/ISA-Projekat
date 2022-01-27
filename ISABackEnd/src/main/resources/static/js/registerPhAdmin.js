Vue.component("registerPhAdmin", {
	data: function () {
		    return {
		    	pharmacyAdministrator: null,
				mode: 'BROWSE'
		    }
	},
	template: `
	<div>
		  <div>
		    <h1 style = "margin:0 auto; text-align:center; background-color:#f1f1f1;">CREATE {{pharmacyAdministrator.userType}} ACCOUNT</h1>
		    <br>
		<table style = "margin:0 auto;">
		    <tr>
		    <th> <label style = "text-align:left;">Email</label></th>
		    <th> <input type="text" v-model = "pharmacyAdministrator.email" placeholder="Enter Email"  style = "width: 355%; padding: 10px;border: none; background: #f1f1f1;" required></th>
		    </tr>
		    <tr>
		    <th><label><b>First Name</b></label></th>
		    <th> <input type="text" v-model = "pharmacyAdministrator.firstName" placeholder="Enter First Name"  style = "width: 355%; padding: 10px;border: none; background: #f1f1f1;" required></th>
		    </tr>
		    <tr>
		    <th><label>Last Name</label></th>
		    <th><input type="text" v-model = "pharmacyAdministrator.lastName" placeholder="Enter Last Name"  style = "width: 355%; padding: 10px; border: none; background: #f1f1f1;" required></th>
		    </tr>
		    <tr>
		    <th><label><b>Address</b></label></th>
		    <td><input type="text" v-model = "pharmacyAdministrator.address.street" placeholder="Enter Street" style = "width: 70%; padding: 10px;  border: none; background: #f1f1f1;" required></td>
		    <td><input type="text" v-model = "pharmacyAdministrator.address.streetNumber" placeholder="Enter Street Number" style = "width: 70%; padding: 10px;  border: none; background: #f1f1f1;" required></td>
		    <td><input type="text" v-model = "pharmacyAdministrator.address.city" placeholder="Enter City" style = "width: 70%; padding: 10px;  border: none; background: #f1f1f1;" required></td>
		    <td><input type="text" v-model = "pharmacyAdministrator.address.country" placeholder="Enter Country" style = "width: 48%; padding: 10px;  border: none; background: #f1f1f1;" required></td>
		    </tr>
		    <tr>
		    <th><label><b>Phone Number</b></label></th>
		    <th><input type="phoneNumber" v-model = "pharmacyAdministrator.phoneNumber"  placeholder="Enter Phone Number"  style = "width: 355%; padding: 10px; border: none; background: #f1f1f1;" required></th>
		    </tr>
		    <tr>
		    <th><label><b>Password</b></label></th>
		    <th><input type="password" v-model = "pharmacyAdministrator.password" placeholder="Enter Password"  style = "width: 355%; padding: 10px; border: none; background: #f1f1f1;" required></th>
		    </tr>
		    <tr>
		    <th><label><b>Repeat Password</b></label></th>
		    <th><input type="password" placeholder="Repeat Password" style = "width: 355%; padding: 10px; border: none; background: #f1f1f1;" required></th>
		    </tr>
		   <tr>
		    <button type="button" v-on:click = "savePhAdmin" style = "background-color: SlateBlue;padding: 10px; border: none;cursor: pointer;width: 675%;opacity: 0.9;">Register</button>
		  </tr>
		    </table>
	</div>
	</div>
		`,
		mounted(){
			axios
			.get('users/newPhAdmin')
			.then(response => (this.pharmacyAdministrator = response.data));
		},
		methods: {
			savePhAdmin : function () {
				axios
				.post("/application/users/createPharmacyAdmin", this.pharmacyAdministrator);
			}	
		}
	});