Vue.component("homePageDermatologist", {
	data: function () {
		    return {
		    	homePageDermatologist: null
		    }
	},
	template: `
	<div>	
		<div style = "background-color: #E0E8F0;; overflow: hidden;">
		  <a href="#/allPatients" style = "float: left; color: #333; text-align:center; padding: 14px 50px; text-decoration:none; font-size: 17px;">My Patients</a>
		  <a href="#/startExamination" style = "float: left; color: #333; text-align:center; padding: 14px 50px; text-decoration:none; font-size: 17px;">Start Examination</a>
		  <a href="#/workCalendar" style = "float: left; color: #333; text-align:center; padding: 14px 50px; text-decoration:none; font-size: 17px;">Work Calendar</a>
		  <a href="#/scheduleVacation" style = "float: left; color: #333; text-align:center; padding: 14px 50px; text-decoration:none; font-size: 17px;">Schedule Vacation</a>
		  <a href="#/myProfile" style = "float: left; color: #333; text-align:center; padding: 14px 50px; text-decoration:none; font-size: 17px;">My Profile</a>
		  <a href="#/scheduleAppointment" style = "float: left; color: #333; text-align:center; padding: 14px 50px; text-decoration:none; font-size: 17px;">Schedule new Appointment</a>
		</div>
		
		<h2 style = "text-align:center">Home page for dermatologist</h2>		
		
</div>
`,
	mounted(){
		axios
	}
});