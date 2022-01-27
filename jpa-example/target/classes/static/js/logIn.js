Vue.component("logIn", {
	data: function () {
		    return {
		    	currentUser: {},
		    	logged: false,
		    	user: null,
		    	emailError: "",
		    	passwordError: "",
		    	error: ""
		    }
	},
	template: `
	<div id="login">
		<h1>Log In</h1>
		<form v-on:submit.prevent="isValidForm" method="post">
			<div class="container" v-bind:hidden="logged">
				<div class="form-floating mt-2 mb-2">
					<input class="form-control" id="floatingInput" v-model="currentUser.email" placeholder="Enter Email">
					<label for="floatingInput">Email</label>
					<p>{{emailError}}</p>
				</div>
				<div class="form-floating">
					<input type="password" class="form-control" id="floatingPassword" v-model="currentUser.password" placeholder="Enter Password">
					<label for="floatingPassword">Password</label>
					<p>{{passwordError}}</p>
				</div>
				<p id="errorMessage">{{error}}</p>
				<div id="loginButton" class="form-floating">
					<button class="w-100 btn btn-lg btn-primary" type="submit">Sign in!</button>
				</div>
			</div>
		</form>
	</div>
		`,
		mounted(){
			axios
		}
	});