Vue.component("allpatients",{
	data: function () {
		    return {
				allpatients: null,
				mode: 'BROWSE',
				backup: [],
				datas : null,
				user : {},
				selectedUser: {},
			}				
}, 
	template: `
		<div>
			<table>		
				<tr>
					<td><input type="text" v-model = "datas" placeholder="Enter name"></td>
					<td><button v-on:click = "searchPatient" style = "background-color:SlateBlue; padding: 10px; border: none; cursor: pointer; opacity: 0.9;" type="button" v-bind:disabled = "mode != 'BROWSE'">Search</button></td>				
				</tr>
			</table>		
			<table style = "width: 100%; border-bottom: 1px solid #ddd;">
				<tr style = "border-bottom: 1px solid #ddd;">
					<td style = "border-bottom: 1px solid #ddd;">FIRSTNAME:</td>
					<td style = "border-bottom: 1px solid #ddd;">LASTNAME:</td>
				</tr>
				<tr style = "border-bottom: 1px solid #ddd;" v-for="p in allpatients" v-on:click="selectUser(p)" v-bind:class="{selected: selectedUser.id===p.id}">
					<td style = "border-bottom: 1px solid #ddd;" >{{p.firstName}}</td>
					<td style = "border-bottom: 1px solid #ddd;">{{p.lastName}}</td>
					<li class="nav-item">
              			<a class="nav-link" href="#/patient">See profile</a>
            		</li>
				</tr>
			</table>
		</div>
		`,
	mounted() {
		axios
		.get('users/allpatients')
		.then(response => (this.allpatients = response.data));
	},
	methods: {
		searchPatient : function () {
			axios
			.post("users/searchUser", this.datas)
			.then(response => (this.allpatients = response.data));
			
		},
		selectUser : function(user){
			this.selectedUser = user;
		}
	}
});