Vue.component("seeOffers", {
	data: function () {
		    return {
		    	seeOffers: null,
		    	supplier: null,
		    	showOrders: null,
				mode: 'BROWSE'	
		    }
	},
	template: `
	<div>
	<div>
	
	<h2 style = "margin:0 auto; text-align:center; background-color:#f1f1f1;">My Offers</h2>
	<br>
	<br>
		<table style = "margin: 0 auto; width:100%; background-color: #E0E8F0;">
		<tr>
		<h2 style = "text-align:right;">Filter orders by:</h2>
		<td><button v-on:click = "filtrateOffersA" style = "background-color:SlateBlue; padding: 10px; border: none; cursor: pointer; width:100%; opacity: 0.9;" type="button" v-bind:disabled = "mode != 'BROWSE'">Accepted</button></td>
		<td><button v-on:click = "filtrateOffersD" style = "background-color:SlateBlue; padding: 10px; border: none; cursor: pointer; width:100%; opacity: 0.9;" type="button" v-bind:disabled = "mode != 'BROWSE'">Declined</button></td>
		<td><button v-on:click = "filtrateOffersW" style = "background-color:SlateBlue; padding: 10px; border: none; cursor: pointer; width:100%; opacity: 0.9;" type="button" v-bind:disabled = "mode != 'BROWSE'">Waiting</button></td>
		<td><button v-on:click = "showAll" style = "background-color:SlateBlue; padding: 10px; border: none; cursor: pointer; width:100%; opacity: 0.9;" type="button" v-bind:disabled = "mode != 'BROWSE'">No filter</button></td>
		</tr>
		
		<tr style = "background-color: #60A0A0;">
		<td style = "border: 1px solid black;">Delivery Deadline</td>
		<td style = "border: 1px solid black;">Price</td>
		<td style = "border: 1px solid black;">Offer Status</td>
		<td style = "border: 1px solid black;">Order Status</td>
		<td style = "border: 1px solid black;">Pharmacy Name</td>
		</tr>
		<tr v-for = "o in seeOffers" style = "border: 1px solid black; background-color: #E0E8F0;">
				<td style = "border: 1px solid black;">{{o.deliveryDeadline.substring(0,10)}}</td>
				<td style = "border: 1px solid black;">{{o.price}}</td>
				<td style = "border: 1px solid black;">{{o.status}}</td>
				<td style = "border: 1px solid black;">{{o.order.orderStatus}}</td>
				<td style = "border: 1px solid black;">{{o.order.pharmacy.name}}</td>
			</tr>
		</table>
		<br>
		<br>
		
		<h2 style = "text-align:center; background-color:#f1f1f1;">New Arrived Orders</h2>
			
		<table style = " width:100%; background-color: #E0E8F0;">
		<tr style = "background-color: #60A0A0;">
			<td style = "border: 1px solid black;">Offer deadline</td>
			<td style = "border: 1px solid black;">Status</td>
			<td style = "border: 1px solid black;">Pharmacy</td>
			<td style = "border: 1px solid black; width:10%">Give suggest</td>
		</tr>
		<tr v-for = "os in showOrders" style = "background-color: #E0E8F0;">				
				<td style = "border: 1px solid black;">{{os.offerDeadline.substring(0,10)}}</td>
				<td style = "border: 1px solid black;">{{os.orderStatus}}</td>
				<td style = "border: 1px solid black;">{{os.pharmacy.name}}</td>
				<td style = "border: 1px solid black;">
					<a href = "#/suggestOffer">Suggest offer</a>
				</td>
		</tr>
		</table>
		
	</div>
	
</div>
`,
	mounted(){
		axios
		.get('offers/seeOffers/3')
		.then(response => (this.seeOffers = response.data));

		axios
		.get('orders/allOrders')
		.then(response => (this.showOrders = response.data));
	},
	methods: {
		showAll: function(){
			axios
			.get("/application/offers/seeOffers/3")
			.then(response => (this.seeOffers = response.data));
		},
		filtrateOffersA: function(event){
			axios
			.get("/application/offers/filtrate/ACCEPTED/3")
			.then(response => (this.seeOffers = response.data));
		},
		filtrateOffersD: function(event){
			axios
			.get("/application/offers/filtrate/DECLINED/3")
			.then(response => (this.seeOffers = response.data));
		},
		filtrateOffersW: function(event){
			axios
			.get("/application/offers/filtrate/WAITING/3")
			.then(response => (this.seeOffers = response.data));
		},
		showOrders: function(os){
			axios
			.get('/application/orders/allOrders', os)
			.then(response => (this.showOrders = response.data));
		}
	}
});