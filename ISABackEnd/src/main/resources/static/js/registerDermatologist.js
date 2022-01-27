Vue.component("registerDerm", {
	data: function () {
		    return {
		    	registered: false,
		    	dermatologist: null,
		    	emailError: "",
		    	firstNameError: "",
		    	lastNameError: "",
		    	streetError: "",
		    	streetNumberError: "",
		    	cityError: "",
		    	countryError: "",
		    	phoneNumberError: "",
		    	error: "",
		    	passwordError: ""
		    }
	},
	template: `
	<div id="login">
		<h1>Registration</h1>
		<form v-on:submit.prevent="isValidForm" method="post">
			<div class="container" v-bind:hidden="registered">
				<div class="form-floating">
					<input class="form-control" id="floatingInput" v-model="dermatologist.email" placeholder="Enter Email" required/>
					<label for="floatingInput">Email</label>
					<p>{{emailError}}</p>
				</div>
				
				<div class="form-floating mt-2 mb-2">
					<input class="form-control" id="floatingInput" v-model="dermatologist.firstName" placeholder="Enter Last Name" required/>
					<label for="floatingInput">First Name</label>
				<p>{{firstNameError}}</p>
				</div>
				
				<div class="form-floating mt-2 mb-2">
					<input class="form-control" id="floatingInput" v-model="dermatologist.lastName" placeholder="Enter First Name" required/>
					<label for="floatingInput">Last Name</label>
					<p>{{lastNameError}}</p>
				</div>
			
				<div class="form-floating mt-2 mb-2">
					<input class="form-control" id="floatingInput" v-model="dermatologist.address.street" placeholder="Enter Street" required/>
					<label for="floatingInput">Street</label>
					<p>{{streetError}}</p>
				</div>
				
				<div class="form-floating mt-2 mb-2">
					<input class="form-control" id="floatingInput" v-model="dermatologist.address.streetNumber" placeholder="Enter Street Number" required/>
					<label for="floatingInput">Street Number</label>
					<p>{{streetNumberError}}</p>
				</div>
				
				<div class="form-floating mt-2 mb-2">
					<input class="form-control" id="floatingInput" v-model="dermatologist.address.city" placeholder="Enter City" required/>
					<label for="floatingInput">City</label>
					<p>{{cityError}}</p>
				</div>
				
				<div class="form-floating mt-2 mb-2">
					<input class="form-control" id="floatingInput" v-model="dermatologist.address.country" placeholder="Enter Country" required/>
					<label for="floatingInput">Country</label>
					<p>{{countryError}}</p>
				</div>
					
				<div class="form-floating mt-2 mb-2">
					<input class="form-control" id="floatingInput" v-model="dermatologist.phoneNumber" placeholder="Enter Phone Number" required/>
					<label for="floatingInput">Phone Number</label>
					<p>{{phoneNumberError}}</p>
				</div>
				
				<div class="form-floating">
					<input type="password" class="form-control" id="floatingPassword" v-model="dermatologist.password" placeholder="Enter Password">
					<label for="floatingPassword">Password</label>
					<p>{{passwordError}}</p>
				</div>
				
				<div class="form-floating">
					<input type="password" class="form-control" id="floatingPassword" placeholder="Repeat Password" required>
					<label for="floatingPassword">Repeat Password</label>
					<p>{{passwordError}}</p>
				</div>
				
				<p id="errorMessage">{{error}}</p>
				<div id="registerButton" class="form-floating">
					<button class="w-100 btn btn-lg btn-primary" v-on:click = "saveDermatologist" type="submit">Sign up!</button>
				</div>
			</div>
		</form>
	</div>
		`,
		mounted(){
			axios
			.get('users/newDermatologist')
			.then(response => (this.dermatologist = response.data));
		},
		methods: {
			saveDermatologist : function () {
				axios
				.post("/application/users/createDermatologist", this.dermatologist);
			},
			isValidForm: function(e) {
				if(dermatologist == null) {
					error = "Greska";
				}
			}
		}
	});

