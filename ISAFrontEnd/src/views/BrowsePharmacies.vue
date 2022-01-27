<template>
  <div id = "homePage">
    <div class = "container">
    <h3>Search Pharmacies:</h3>
    <input placeholder="Enter name or city" type="search" v-model = "searchField" id="searchPharmacies" name="searchPharmacies" required>
    <v-btn
        color="secondary"
        elevation="3"
        small
        v-on:click = "searchPharmacies"
        v-if="notFilled"
    >Search</v-btn>
    <br>
    <h3>Filtrate by rating higher than:</h3>


    <label>Rating 10</label>
    <input type="radio" value=10 name="rating" @change = "filtrate(10)">

    <label>Rating 9</label>
    <input type="radio" value=9 name="rating" @change = "filtrate(9)">

    <label>Rating 8</label>
    <input type="radio" value=8 name="rating" @change = "filtrate(8)">

    <label>Rating 7</label>
    <input type="radio" value=7 name="rating" @change = "filtrate(7)">

    <label>Rating 6</label>
    <input type="radio" value=6 name="rating" @change = "filtrate(6)">

      <v-simple-table>
        <template v-slot:default>
          <thead>
          <tr>
            <th :class="sortedClass('name')"
                @click="sortBy('name')">
              Name
            </th>
            <th :class="sortedClass('rating')"
                @click="sortBy('rating')">
              Rating
            </th>
            <th :class="sortedClass('address')"
                @click="sortBy('address')">
              Address
            </th>
          </tr>
          </thead>
          <tbody>
          <tr
              v-for="p in sortedItems"
              :key="p"
          >
            <td>{{ p.name }}</td>
            <td>{{ p.rating }}</td>
            <td>{{p.address.street + " " + p.address.streetNumber + ", " + p.address.city + ", " + p.address.country}}</td>
            <td>
              <v-btn
                  color="secondary"
                  elevation="3"
                  x-small
                  v-on:click = "openPharmacyPage(p)"
                  v-if="isLogged"
              >Visit</v-btn>
            </td>
          </tr>
          </tbody>
        </template>
      </v-simple-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "BrowsePharmacies",

  data: function () {
    return {
      logged: false,
      pharmacies: [],
      searchResult: [],
      filtrateResult: [],
      searchField: "",
      token: localStorage.getItem("token"),
      sort: {
        key: '',
        isAsc: false
      }
    }
  },
mounted() {
  {
  this.axios
      .get('http://localhost:8091/pharmacy')
      .then(r => {
        this.pharmacies = r.data
        this.searchResult = this.pharmacies
        this.filtrateResult = this.pharmacies
      })
  }
},
  methods: {
    openPharmacyPage(p){
      localStorage.setItem("pharmacy", p.pharmacyId);
      localStorage.setItem("pharmacyName", p.name);
      window.location.href = "http://localhost:8080/pharmacyPage";
    },
    sortedClass (key) {
      return this.sort.key === key ? `sorted ${this.sort.isAsc ? 'asc' : 'desc' }` : '';

    },
    sortBy (key) {
      this.sort.isAsc = this.sort.key === key ? !this.sort.isAsc : false;
      this.sort.key = key;
    },
    searchPharmacies: function() {
      this.axios
          .get("http://localhost:8091/pharmacy/getByNameOrAddress/" + this.searchField)
          .then(response => {
            this.searchResult = response.data
            this.filtrateResult = this.searchResult
          })
    },
    filtrate: function(rating){
      this.axios
          .put("http://localhost:8091/pharmacy/filtrateByRating/" + rating, {pharmacies: this.searchResult})
          .then(response => (this.filtrateResult = response.data))
    }
  },
  computed:{
    isLogged: function (){
      if (this.token == ""){
        return false
      }else{
        return true
      }
    },
    notFilled: function () {
      if (this.searchField.trim() == ""){
        return false
      }
      else {
        return true
      }
    },sortedItems () {
      const list = this.filtrateResult.slice();  // ソート時でdataの順序を書き換えないため
      if (this.sort.key !="") {
        list.sort((a, b) => {
          a = a[this.sort.key]
          b = b[this.sort.key]

          return (a === b ? 0 : a > b ? 1 : -1) * (this.sort.isAsc ? 1 : -1)
        });
      }

      return list;
    }
  }
}
</script>

<style scoped>
#searchPharmacies{
  background-color: white;
}

.container {
  display: block;
  position: relative;
  padding-left: 35px;
  margin-bottom: 12px;
  cursor: pointer;
  font-size: 18px;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

input[type=radio]{
  border-radius: 10px;
  box-shadow: inset 0 1px 1px hsla(0,0%,100%,.8),
  0 0 0 1px hsla(0,0%,0%,.6),
  0 2px 3px hsla(0,0%,0%,.6),
  0 4px 3px hsla(0,0%,0%,.4),
  0 6px 6px hsla(0,0%,0%,.2),
  0 10px 6px hsla(0,0%,0%,.2);
  cursor: pointer;
  display: inline-block;
  height: 15px;
  margin-right: 15px;
  position: relative;
  width: 15px;
  -webkit-appearance: none;
}

input[type="radio"]:after {
  border-radius: 25px;
  box-shadow: inset 0 0 0 1px hsla(0,0%,0%,.4),
  0 1px 1px hsla(0,0%,100%,.8);
  content: '';
  display: block;
  height: 7px;
  left: 4px;
  position: relative;
  top: 4px;
  width: 7px;
}
input[type="radio"]:checked:after {
  background-color: #444;
  box-shadow: inset 0 0 0 1px hsla(0,0%,0%,.4),
  inset 0 2px 2px hsla(0,0%,100%,.4),
  0 1px 1px hsla(0,0%,100%,.8),
  0 0 2px 2px hsla(0,70%,70%,.4);
}

</style>