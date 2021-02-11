const employee = {template: '<employee></employee>'}
const sysAdmin = {template: '<sysAdmin></sysAdmin>'}
const register = {template: '<register></register>'}
const logIn = {template: '<logIn></logIn>'}
const supplier = {template: '<supplier></supplier>'}
const addMedicines = {template: '<addMedicines></addMedicines>'}
const searchPharmacists = {template: '<searchPharmacists></searchPharmacists>'}
const patient = {template: '<patient></patient>'}
const seeOffers = {template: '<seeOffers></seeOffers>'}
const registerPhAdmin = {template: '<registerPhAdmin></registerPhAdmin>'}
const registerDerm = {template: '<registerDerm></registerDerm>'}
const allpatients = {template: '<allpatients></allpatients>'}

const router = new VueRouter({
	mode: 'hash',
	routes: [
		{path: '/employee', component: employee},
		{path: '/sysAdmin', component: sysAdmin},
		{path: '/register', component: register},
		{path: '/logIn', component: logIn},
		{path: '/supplier', component: supplier},
		{path: '/addMedicines', component: addMedicines},
		{path: '/searchPharmacists', component: searchPharmacists},
		{path: '/patient', component: patient},
		{path: '/seeOffers', component: seeOffers},
		{path: '/registerPhAdmin', component: registerPhAdmin},
		{path: '/registerDerm', component: registerDerm},
		{path: '/allpatients', component: allpatients}

	]
});

var app = new Vue({
	router,
	el: '#routerMode'
});