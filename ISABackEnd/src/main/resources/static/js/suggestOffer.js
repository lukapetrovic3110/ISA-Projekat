Vue.component("suggestOffer", {
	data: function () {
		    return {
		    offer: {
			    price = "",
			    offerDeadline = "mm/dd/yyyy"
			    },
			mode: 'BROWSE'
		    }
	},
	template: 
	`
	<div id = "suggestOffer">
		
		<div class="container">
	    	<h2>Suggest Offer</h2>
	    <hr>
	
	    <label for="price"><b>Price</b></label>
	    <input type="text" placeholder="Enter Price" name="price" id="price" v-model="offer.price" required>
	
	    <label for="deadline"><b>Offer Deadline</b></label>
	    <input type="date" placeholder="Enter Deadline" name="deadline" id="deadline"  v-model="offer.offerDeadline" required>
		
		<button type="submit" class="sendSuggestion" v-on:click = "suggestOffers">Send Suggestion</button>
		
		</div>
	</div>
	`,
	mounted(){
		axios
	},
	methods:{ 
		suggestOffers: function() {
			axios
			.post("/application/offers/suggestOffer", this.offer);
		}
	}
});
