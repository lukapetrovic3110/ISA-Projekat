Vue.component("searchPharmacists", {
    data: function() {
            return {
                pharmacists: null,
                pharmacistList: null,
                showList: false,
                searchFirstName: '',
                searchLastName: '',
                minRaiting: '',
                maxRaiting: ''
            }
    }, 
    template: `
    <div id = "searchPharmacists">
        <h1>Search and filter pharmacists</h1>
        <table>		
            <tr>
               <td>
                    <input class = "input" type="text" class="form-control" id="floatingInput" v-model="searchFirstName"  placeholder="Enter first name">
               </td>
            </tr>
            <tr>
               <td>
                    <input class = "input" type="text" class="form-control" id="floatingInput" v-model="searchLastName"  placeholder="Enter last name">
               </td>
            </tr>
            <tr>	
               <td>
                    <input class = "input" type="number" min=0  class="form-control" id="floatingInput" v-model="minRaiting"  placeholder="Enter min for raiting">
                </td>
            </tr>
            <tr>	
                <td>
                    <input class = "input" type="number" max=10 class="form-control" id="floatingInput" v-model="maxRaiting"  placeholder="Enter max for raiting">
                </td>	
            </tr>
            <tr>
                <td>
                    <button class="w-40 h-20 btn btn-lg btn-primary" type="submit" v-on:click="cancelSearch">Cancel search</button>
                </td>
                <td>
                    <button class="w-40 h-20 btn btn-lg btn-primary" type="submit" v-on:click="searchPharmacist">Search pharmacists</button>
                </td>
            </tr>
        </table>
        
		<h2>Pharmacists</h2>
        
        <table id="searchTable">
            <tr>
                <th>First name</th>
                <th>Last name</th>
                <th>Raiting of a pharmacist</th>
                <th>Pharmacy name</th>
            </tr>
            
            <tr v-bind:hidden="showList" v-for="p in pharmacists">
                <td>{{p.firstName}}</td>
                <td>{{p.lastName}}</td>
      			<td>{{p.raiting}}</td>
      			<td>{{p.pharmacyName}}</td>
            </tr>
                
            <tr v-bind:hidden="!showList" v-for="p in pharmacistList">
                <td>{{p.firstName}}</td>
                <td>{{p.lastName}}</td>
      			<td>{{p.raiting}}</td>
      			<td>{{p.pharmacyName}}</td>
            </tr>
        </table>
    </div>
    `,
    mounted() {
        axios
        .get('pharmacists/all')
        .then(response => (this.pharmacists = response.data));
    },
    methods: {
		searchPharmacist: function(){
		
		    if(this.searchFirstName != '' && this.searchLastName == ''){
		    	axios
    			.get("pharmacists/searchPharmacistsByFirstName/" + this.searchFirstName)
    			.then(response => {
    				this.pharmacistList = response.data;
    				this.showList = true;
    			}); 
		    }
		    else if(this.searchFirstName == '' && this.searchLastName != '')
		    {
		    	axios
    			.get("pharmacists/searchPharmacistsByLastName/" + this.searchLastName)
    			.then(response => {
    				this.pharmacistList = response.data;
    				this.showList = true;
    			}); 
		    }
    		else if(this.searchFirstName !='' && this.searchLastName != ''){
    			axios
    			.get("pharmacists/searchPharmacists/" + this.searchFirstName + "/" + this.searchLastName)
    			.then(response => {
    				this.pharmacistList = response.data;
    				this.showList = true;
    			});    		
    		}
    		else if (this.minRaiting != '' && this.maxRaiting != '')
            {
                axios
    			.get("pharmacists/filterPharmacistByRaiting/" +  this.minRaiting + "/" + this.maxRaiting)
    			.then(response => {
    				this.pharmacistList = response.data;
    				this.showList = true;
    			}); 
            }
            else{
    			this.showList = false;
    		}		
    	},
    	cancelSearch: function(){
			this.showList = false;
			this.searchFirstName = '';
			this.searchLastName = '';
			this.minRaiting = '';
			this.maxRaiting = '';
		}
    }
})