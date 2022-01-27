<template>
  <div id = "homePage">
    <div class = "container">
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
            <th :class="sortedClass('adress')"
                @click="sortBy('adress')">
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
                  v-on:click = "choosenPharmacy(p)"
                  v-if="isLogged"
              >Choose</v-btn>
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
      .get('http://localhost:8091/pharmacy', 
      {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
          })
      .then(r => (this.pharmacies = r.data))
  }
},
  methods: {
    sortedClass (key) {
      return this.sort.key === key ? `sorted ${this.sort.isAsc ? 'asc' : 'desc' }` : '';

    },
    sortBy (key) {
      this.sort.isAsc = this.sort.key === key ? !this.sort.isAsc : false;
      this.sort.key = key;
    },
    choosenPharmacy(p){
        localStorage.setItem("pharmacy", p.pharmacyId);
        localStorage.setItem("pharmacyName", p.name);
        window.location.href = "http://localhost:8080/createPhAdmin";     
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
      const list = this.pharmacies.slice();  // ソート時でdataの順序を書き換えないため
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