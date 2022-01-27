Vue.component("suggestOffer", {
	data: function () {
		    return {
		    	suggestOffer: null,
				mode: 'BROWSE',
				backup: []
		    }
	},
	template: 
	`
	<div id = "suggestOffer">
		
		<div class="container">
	    	<h2>Suggest Offer</h2>
	    <hr>
	
	    <label for="price"><b>Price</b></label>
	    <input type="text" placeholder="Enter Price" name="price" id="price" required>
	
	    <label for="deadline"><b>Offer Deadline</b></label>
	    <input type="date" placeholder="Enter Deadline" name="deadline" id="deadline" required>
		
		<button type="submit" class="sendSuggestion">Send Suggestion</button>
		
		</div>
	</div>
	`,
	mounted(){
		axios
	}
});