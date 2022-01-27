Vue.component("homePage", {
	data: function () {
		    return {
		    	logged: false,
		    	pharmacies: []
		    }
	},
	template: `
	<div>		
			<table style = "width: 100%; border-bottom: 1px solid #ddd;">
				<tr><th colspan="3">Pharmacies</th></tr>
				<tr style = "border-bottom: 1px solid #ddd;">
					<td style = "border-bottom: 1px solid #ddd;">Name</td>
					<td style = "border-bottom: 1px solid #ddd;">Rating</td>
					<td style = "border-bottom: 1px solid #ddd;">Description</td>
				</tr>
				<tr style = "border-bottom: 1px solid #ddd;" v-for="p in pharmacies">
					<td style = "border-bottom: 1px solid #ddd;" >{{p.name}}</td>
					<td style = "border-bottom: 1px solid #ddd;">{{p.rating}}</td>
					<td style = "border-bottom: 1px solid #ddd;">{{p.description}}</td>
				</tr>
			</table>
		</div>

		`,
		mounted(){
		axios
		.get('/application/pharmacy')
		.then(response => (this.pharmacies = response.data));
		
	}
	});