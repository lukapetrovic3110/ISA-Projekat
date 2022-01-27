<template>
  <div id = "homePage">
    <div class = "container">
      <v-simple-table>
        <template v-slot:default>
          <thead>
          <tr>
            <th :class="sortedClass('dateAndTime')"
                @click="sortBy('dateAndTime')">
              Date and Time
            </th>
            <th :class="sortedClass('duration')"
                @click="sortBy('duration')">
              Duration
            </th>
            <th>
              Pharmacy
            </th>
            <th>
              Address
            </th>
            <th>
              Dermatologist
            </th>
            <th :class="sortedClass('price')"
                @click="sortBy('price')">
              Price
            </th>
          </tr>
          </thead>
          <tbody>
          <tr
              v-for="c in sortedItems"
              :key="c"
          >
            <td>{{c.dateAndTime}}</td>
            <td>{{ c.duration + " minutes"}}</td>
            <td>{{ c.pharmacy.name}}</td>
            <td>{{c.pharmacy.address.street + " " + c.pharmacy.address.streetNumber + ", " +
            c.pharmacy.address.city + ", " + c.pharmacy.address.country}}</td>
            <td>{{c.dermatologist.firstName + " " + c.dermatologist.lastName}}</td>
            <td>{{c.price + " rsd"}}</td>
          </tr>
          </tbody>
        </template>
      </v-simple-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "PreviousExaminations",
  data: function () {
    return {
      examinations: [],
      sort: {
        key: '',
        isAsc: false
      }
    }
  },
  mounted() {
    {
      this.axios
          .get('http://localhost:8091/examination/getPreviousExaminations/' + localStorage.getItem("userId"), {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
          })
          .then(response => (this.examinations = response.data));
    }
  },
  methods: {
    sortedClass(key) {
      return this.sort.key === key ? `sorted ${this.sort.isAsc ? 'asc' : 'desc'}` : '';

    },
    sortBy(key) {
      this.sort.isAsc = this.sort.key === key ? !this.sort.isAsc : false;
      this.sort.key = key;
    }
  },
    computed:{
      sortedItems () {
        const list = this.examinations.slice();  // ソート時でdataの順序を書き換えないため
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
</style>