Vue.component("register", {
	data: function () {
		    return {
		    	newUser: {
		    		address:{}
		    },
				emailError : "",
				passwordError : "",
				confirmPasswordError : "",
				firstNameError : "",
				lastNameError : "",
				streetError: "",
				streetNumberError: "",
				cityError: "",
				countryError: "",
				phoneNumberError: "",
				mode: 'BROWSE'
		    }
	},
	template: `
	<div>
	<h3 style = "text-align:center;">Registration</h3>
	<form action = "" class = "main-form" >
						<div class = "modal-body " >
							<div class="container" >
								<div class="row justify-content-sm-center">
									<div class="col-md-4">
										<input class = "form-control" type = "text" placeholder = "Enter Email" v-model="newUser.email" required>
										<label style="color:red">{{emailError}}</label></br>
									</div>
								</div>
								
								<div class="row justify-content-sm-center">
									<div class="col-md-4">
										<input class = "form-control" type = "password" placeholder = "Enter Password" v-model="newUser.password" required pattern=".{8,}"  title="8 characters minimum" >
										<label style="color:red">{{passwordError}}</label></br>
									</div>
								</div>
								
								<div class="row justify-content-sm-center">
									<div class="col-md-4">
										<input class = "form-control" type = "password" placeholder = "Confirm Password" v-model="newUser.passwordConfirm" required pattern=".{8,}"  title="8 characters minimum" >
										<label style="color:red">{{confirmPasswordError}}</label></br>
									</div>
								</div>
								
								<div class="row justify-content-sm-center">
									<div class="col-md-4">
										<input class = "form-control" type = "firstName" placeholder = "Enter First Name " v-model="newUser.firstName" required>
										<label style="color:red">{{firstNameError}}</label></br>
									</div>
								</div>
								
								<div class="row justify-content-sm-center">
									<div class="col-md-4">
										<input class = "form-control" type = "lastName" placeholder = "Enter Last Name " v-model="newUser.lastName" required>
										<label style="color:red">{{lastNameError}}</label></br>
									</div>
								</div>		
								
								<div class="row justify-content-sm-center">
									<div class="col-md-4">
										<input class = "form-control" type = "street" placeholder = "Enter Street " v-model="newUser.address.street" required>
										<label style="color:red">{{streetError}}</label></br>
									</div>
								</div>	

								
								<div class="row justify-content-sm-center">
									<div class="col-md-4">
										<input class = "form-control" type = "streetNumber" placeholder = "Enter Street Number " v-model="newUser.address.streetNumber" required>
										<label style="color:red">{{streetNumberError}}</label></br>
									</div>
								</div>	
								
								<div class="row justify-content-sm-center">
									<div class="col-md-4">
										<input class = "form-control" type = "city" placeholder = "Enter City" v-model="newUser.address.city" required>
										<label style="color:red">{{cityError}}</label></br>
									</div>
								</div>	
							
								<div class="row justify-content-sm-center">
									<div class="col-md-4">
										<input class = "form-control" type = "country" placeholder = "Enter Country" v-model="newUser.address.country" required>
										<label style="color:red">{{countryError}}</label></br>
									</div>
								</div>	
				
								<div class="row justify-content-sm-center">
									<div class="col-md-4">
										<input class = "form-control" type = "phoneNumber" placeholder = "Enter Phone Number" v-model="newUser.phoneNumber" required>
										<label style="color:red">{{phoneNumberError}}</label></br>
									</div>
								</div>	
			
								
								<div class="row justify-content-sm-center" style="margin-top: 10px;">
									<div class="col-md-4">
										<button type = "button" class="btn btn-primary" v-on:click="saveSupplier(newUser)">Register</button>
									</div>
									
								</div>
							</div>
						</div>
		</form>		
</div>
		`,
		mounted(){
			this.newUser.UserType = "PATIENT";
		},
		methods: {
			saveSupplier : function (supplier) {
	
				if(supplier.email == "" || supplier.email == null){
					this.emailError = "This field must be filled";
				}
				if(supplier.password == "" || supplier.password == null){
					this.passwordError = "This field must be filled";
				}
				if(supplier.passwordConfirm == "" || supplier.passwordConfirm == null){
					this.confirmPasswordError = "This field must be filled";
				}
				if(supplier.firstName == "" || supplier.firstName == null){
					this.firstNameError = "This field must be filled";
				}
				if(supplier.lastName == "" || supplier.lastName == null){
					this.lastNameError = "This field must be filled";
				}
				if(supplier.address.street == "" || supplier.address.street == null){
					this.streetError = "This field must be filled";
				}
				if(supplier.address.streetNumber == "" || supplier.address.streetNumber == null){
					this.streetNumberError = "This field must be filled";
				}
				if(supplier.address.city == "" || supplier.address.city == null){
					this.cityError = "This field must be filled";
				}
				if(supplier.address.country == "" || supplier.address.country == null){
					this.countryError = "This field must be filled";
				}
				if(supplier.phoneNumber == "" || supplier.phoneNumber == null){
					this.phoneNumberError = "This field must be filled";
				}
				
				if(supplier.email != "" && supplier.email != undefined && supplier.email != null 
						&& supplier.password != "" && supplier.password != undefined && supplier.password != null
						&& supplier.passwordConfirm != "" && supplier.passwordConfirm != undefined && supplier.passwordConfirm != null 
						&& supplier.firstName != "" && supplier.firstName != undefined && supplier.firstName != null 
						&& supplier.lastName != "" && supplier.lastName != undefined && supplier.lastName != null
						&& supplier.address.street != "" && supplier.address.street != undefined && supplier.address.street != null
						&& supplier.address.streetNumber != "" && supplier.address.streetNumber != undefined && supplier.address.streetNumber != null
						&& supplier.address.city != "" && supplier.address.city != undefined && supplier.address.city != null
						&& supplier.address.country != "" && supplier.address.country != undefined && supplier.address.country != null
						&& supplier.phoneNumber != "" && supplier.phoneNumber != undefined && supplier.phoneNumber != null)
				{
				
					if(supplier.password != supplier.passwordConfirm){
						this.confirmPasswordError = "Passwords don't match";
					}else{
					var u = {email : supplier.email, password : supplier.password, firstName : supplier.firstName, lastName : supplier.lastName,
							 street : supplier.address.street, streetNumber : supplier.address.streetNumber,
							 city : supplier.address.city, country : supplier.address.country,
							 phoneNumber : supplier.phoneNumber};
					axios
					.post("/application/users/createSupplier", u)
				          .then(response => { 
			        	  if(response.data != null){
			        		  (alert("User " + u.firstName + " " + u.lastName + " is successfuly registered."));
										this.emailError = "";
										this.passwordError = "";
										this.confirmPasswordError = "";
										this.firstNameError = "";
										this.lastNameError = "";
										this.streetError = "";
										this.streetNumberError = "";
										this.cityError = "";
										this.countryError = "";
										this.phoneNumberError = "";
							}
			        	  else {
			        		  (alert("User with that email already exists!"));
			        	  }  
					});
				}
			}
		}
		}
	});