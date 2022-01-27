<template>
  <div id = "homePage">
    <div class = "container">
          <br>
          <h3>Filtrate by status:</h3>

          <label>NEW</label>
          <input type="radio" value="NEW" name="status" @change = "filtrate( 'NEW' )">

          <label>PROCESSED</label>
          <input type="radio" value="PROCESSED" name="status" @change = "filtrate('PROCESSED')">

          <label>DECLINED</label>
          <input type="radio" value="DECLINED" name="status" @change = "filtrate('DECLINED')">

          <br>
          <br>
      <v-simple-table>
        <template v-slot:default>
          <thead>
          <tr>
            <th :class="sortedClass('date')"
                @click="sortBy('date')">
              Date and Time
            </th>
            <th>
              Pharmacy
            </th>
            <th>
              ePrescription Status
            </th>
          </tr>
          </thead>
          <tbody>
          <tr
              v-for="c in sortedItems"
              :key="c"
          >
            <td>{{c.date}}</td>
            <td>{{ c.pharmacy.name}}</td>
            <td>{{c.status}}</td>
          </tr>
          </tbody>
        </template>
      </v-simple-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "MyEPrescription",
  data: function () {
    return {
      sort: {
        key: '',
        isAsc: false
      },
      ePrescription: []
    }
  },
  mounted() {
    {
      this.axios
          .get('http://localhost:8091/ePrescription/getByPatient/' + localStorage.getItem("userId"), {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
          })
          .then(r => {
            this.ePrescription = r.data
          })
    }
  },
  methods: {
    sortedClass(key) {
      return this.sort.key === key ? `sorted ${this.sort.isAsc ? 'asc' : 'desc'}` : '';

    },
    filtrate(value){
      this.axios
          .get('http://localhost:8091/ePrescription/filtrate/' + localStorage.getItem('userId')+ "/" + value, {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem("token")
            }
          })
          .then(r => {
            this.ePrescription = r.data
          })
    },
    sortBy(key) {
      this.sort.isAsc = this.sort.key === key ? !this.sort.isAsc : false;
      this.sort.key = key;
    }
  },
  computed:{
    sortedItems () {
      const list = this.ePrescription.slice();  // ソート時でdataの順序を書き換えないため
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