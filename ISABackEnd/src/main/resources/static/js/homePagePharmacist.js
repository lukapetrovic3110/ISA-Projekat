Vue.component("homePagePharmacist", {
	data: function () {
		    return {
		    	homePagePharmacist: null
		    }
	},
	template: `
	<div>	
		<div style = "background-color: #E0E8F0;; overflow: hidden;">
		  <a href="#/allPatients" style = "float: left; color: #333; text-align:center; padding: 14px 32px; text-decoration:none; font-size: 17px;">My Patients</a>
		  <a href="#/startCounseling" style = "float: left; color: #333; text-align:center; padding: 14px 32px; text-decoration:none; font-size: 17px;">Start Counseling</a>
		  <a href="#/workCalendar" style = "float: left; color: #333; text-align:center; padding: 14px 32px; text-decoration:none; font-size: 17px;">Work Calendar</a>
		  <a href="#/dispenseMedicine" style = "float: left; color: #333; text-align:center; padding: 14px 32px; text-decoration:none; font-size: 17px;">Dispense Medicine</a>
		  <a href="#/scheduleVacation" style = "float: left; color: #333; text-align:center; padding: 14px 32px; text-decoration:none; font-size: 17px;">Schedule Vacation</a>
		  <a href="#/myProfile" style = "float: left; color: #333; text-align:center; padding: 14px 32px; text-decoration:none; font-size: 17px;">My Profile</a>
		  <a href="#/scheduleCounseling" style = "float: left; color: #333; text-align:center; padding: 14px 32px; text-decoration:none; font-size: 17px;">Schedule New Counseling</a>
		</div>
		
		<h2 style = "text-align:center">Home page for pharmacist</h2>
		
		
		
</div>
`,
	mounted(){
		axios
	}
});