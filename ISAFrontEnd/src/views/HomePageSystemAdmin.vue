<template>
    <div id="pharmacyAdminMenu">
      <a class="a_style" href="/systemAdmin">My Profile</a>
      <a class="a_style" href="/createPhAdmin">Add Pharmacy Admin</a>
      <a class="a_style" href="/createSupplier">Add Supplier</a>
      <a class="a_style" href="/createDermatologist">Add Dermatologist</a>
      <a class="a_style" href="/createPharmacy">Add Pharmacy</a>
      <a class="a_style" href="/medicineRegistration">Add Medicine</a>
      <a class="a_style" href="/complaints">Complaints</a>
      <a class="a_style" href="/loyaltyScale">Define Loyalty Scale</a>
    </div> 
</template>

<script>
export default {
    name: "HomePageSystemAdmin",

mounted(){
   this.axios
          .get('http://localhost:8091/systemAdmin/calculateLoyaltyScale', {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
          })
          .then(console.log("aaaa"));
},
}

</script>

<style scoped>
#pharmacyAdminMenu {
    background-color: #E0E8F0; 
    overflow: hidden;
    opacity: 0.9;
}
.a_style {
   float: left; 
   color: black; 
   text-align:center; 
   padding: 14px 32px; 
   text-decoration:none; 
   font-size: 17px;
}
</style>
