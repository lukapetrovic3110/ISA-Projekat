Vue.component("searchDermatologists", {
    data: function(){
        return {
            dermatologists: null,
            dermatologistList: null,
            showList: false,
            searchFirstName: '',
            searchLastName: '',
            minRaiting: '',
            maxRaiting: '',
        }
    },
    template: `
    <div id = "searchDermatologists">
        <h1>Search and filter dermatologists</h1>
        <table>		
            <tr>
               <td>
                    <input class = "input" type="text" class="form-control" v-model="searchFirstName"  placeholder="Enter first name">
               </td>
            </tr>
            <tr>
               <td>
                    <input class = "input" type="text" class="form-control" v-model="searchLastName"  placeholder="Enter last name">
               </td>
            </tr>
            <tr>	
               <td>
                    <input class = "input" type="number" min=0  class="form-control" v-model="minRaiting"  placeholder="Enter min for raiting">
                </td>
            </tr>
            <tr>	
                <td>
                    <input class = "input" type="number" max=10 class="form-control"  v-model="maxRaiting"  placeholder="Enter max for raiting">
                </td>	
            </tr>
            <tr>
                <td>
                    <button class="w-40 h-20 btn btn-lg btn-primary" type="submit" v-on:click="cancelSearch">Cancel search</button>
                </td>
                <td>
                    <button class="w-40 h-20 btn btn-lg btn-primary" type="submit" v-on:click="searchDermatologist">Search dermatologist</button>
                </td>
            </tr>
        </table>
        
		<h2>Dermatologists</h2>
        
        <table id="searchTable">
            <tr>
                <th>First name</th>
                <th>Last name</th>
                <th>Raiting of a dermatologist</th>
            </tr>
            
            <tr v-bind:hidden="showList" v-for="d in dermatologists">
                <td>{{d.firstName}}</td>
                <td>{{d.lastName}}</td>
      			<td>{{d.raiting}}</td>
            </tr>
                
            <tr v-bind:hidden="!showList" v-for="d in dermatologistList">
                <td>{{d.firstName}}</td>
                <td>{{d.lastName}}</td>
      			<td>{{d.raiting}}</td>
            </tr>
        </table>
    </div> 
    ` ,
    mounted() {
        axios
        .get('dermatologists/all')
        .then(response => (this.dermatologists = response.data));
    },
    methods: {
		searchDermatologist: function(){
		
		    if(this.searchFirstName != '' && this.searchLastName == ''){
		    	axios
    			.get("dermatologists/searchDermatologistsByFirstName/" + this.searchFirstName)
    			.then(response => {
    				this.dermatologistList = response.data;
    				this.showList = true;
    			}); 
		    }
		    else if(this.searchFirstName == '' && this.searchLastName != '')
		    {
		    	axios
    			.get("dermatologists/searchDermatologistsByLastName/" + this.searchLastName)
    			.then(response => {
    				this.dermatologistList = response.data;
    				this.showList = true;
    			}); 
		    }
    		else if(this.searchFirstName !='' && this.searchLastName != ''){
    			axios
    			.get("dermatologists/searchDermatologists/" + this.searchFirstName + "/" + this.searchLastName)
    			.then(response => {
    				this.dermatologistList = response.data;
    				this.showList = true;
    			});    		
    		}
    		else if (this.minRaiting != '' && this.maxRaiting != '')
            {
                axios
    			.get("dermatologists/filterDermatologistByRaiting/" +  this.minRaiting + "/" + this.maxRaiting)
    			.then(response => {
    				this.dermatologistList = response.data;
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