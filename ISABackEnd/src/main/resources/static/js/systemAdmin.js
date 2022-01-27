Vue.component("sysAdmin", {
	data: function () {
		    return {
		    	sysAdmin: null
		    }
	},
	template: `
	<div>	
		<div style = "background-color: #E0E8F0;; overflow: hidden;">
		  <a href="#/registerPhAdmin" style = "float: left; color: #333; text-align:center; padding: 14px 16px; text-decoration:none; font-size: 17px;">Register Pharmacy</a>
		  <a href="#/registerPhAdmin" style = "float: left; color: #333; text-align:center; padding: 14px 16px; text-decoration:none; font-size: 17px;">Register Pharmacy Admin</a>
		  <a href="#/registerDerm" style = "float: left; color: #333; text-align:center; padding: 14px 16px; text-decoration:none; font-size: 17px;">Register Dermatologist</a>
		  <a href="#/register" style = "float: left; color: #333; text-align:center; padding: 14px 16px; text-decoration:none; font-size: 17px;">Register Supplier</a>
		  <a href="#/addMedicines" style = "float: left; color: #333; text-align:center; padding: 14px 16px; text-decoration:none; font-size: 17px;">Add New Medicine</a>
		  <a href="#/registerDerm" style = "float: left; color: #333; text-align:center; padding: 14px 16px; text-decoration:none; font-size: 17px;">Complaint</a>
		  <a href="#/registerDerm" style = "float: left; color: #333; text-align:center; padding: 14px 16px; text-decoration:none; font-size: 17px;">Loyalty Program</a>
		  <a href="#/registerDerm" style = "float: left; color: #333; text-align:center; padding: 14px 16px; text-decoration:none; font-size: 17px;">System Admin</a>
		</div>
		
		<h2 style = "text-align:center">System Administrator Profile</h2>
		
		
		
</div>
`,
	mounted(){
		axios
	}
});