<template>
    <div id="pharmacyAdminMenu">
      <a class="a_style" href="/pharmacyAdmin">My Profile</a>
      <a class="a_style" href="/myPharmacy">My Pharmacy</a>
      <a class="a_style" href="/mapExample">Pharmacy on the map</a>
      <a class="a_style" href="/medicinesInPharmacy">Medicines in pharmacy</a>
      <a class="a_style" href="/deleteMedicineItems">Delete medicine items</a>
      <a class="a_style" href="/searchDermatologist">Search and filter dermatologists</a>
      <a class="a_style" href="/defineFreeTermWithDermatologist">Define free term for examination with dermatologist</a>
      <a class="a_style" href="/createPharmacistByPharmacyAdmin">Create pharmacist</a>
      <a class="a_style" href="/deletePharmacist">Delete pharmacist</a>
      <a class="a_style" href="/createDermatologistByPharmacyAdmin">Create dermatologist</a>
       <a class="a_style" href="/deleteDermatologist">Delete dermatologist</a>
      <a class="a_style" href="/searchPharmacist">Search and filter pharmacists</a>
      <a class="a_style" href="/promotion">Define a promotion</a>
      <a class="a_style" href="/makeAnOrder">Make an order</a>
      <a class="a_style" href="/orderEditing">Edit my orders</a>
      <a class="a_style" href="/pharmacyOrders">All pharmacy orders</a>
      <a class="a_style" href="/requestsVacationPharmacists">Pending vacation requests from pharmacists</a>
      <a class="a_style" href="/allVacationRequestsPharmacists">All vacation requests from pharmacists</a>
    </div> 
</template>

<script>
export default {
    name: "HomePagePharmacyAdmin"
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
