<template>
  <TheDarkHeader></TheDarkHeader>

  <div style="padding: 5% 10%">
    <div class="album py-5">
      <div class="d-flex flex-row justify-content-between">
        <div class="row row-cols-md-5 g-3">
          <div class="col" :key="plant.plantDictId" v-for="plant in plants">
            <a href="">
              <div class="card shadow-sm">
                <img
                  :src="plant.imgUrl"
                  class="bd-placeholder-img card-img-top"
                  width="100%"
                  height="250"
                  role="img"
                  preserveAspectRatio="xMidYMid slice"
                  focusable="false"
                  @click="goPlantDetail(plant.plantDictId)"
                />
              </div>
            </a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import TheDarkHeader from '@/layouts/TheDarkHeader.vue'
import * as plantApi from '@/api/plant'

export default {
  components: {
    TheDarkHeader
  },
  data() {
    return {
      plants: []
    }
  },
  created() {
    plantApi.getPlantList().then((res) => {
      this.plants = res.data
    })
  },
  methods: {
    goPlantDetail(plantDictId) {
      this.$router.push({
        name: 'PlantDetail',
        params: { id: plantDictId }
      })
    }
  }
}
</script>
<style scoped>
/* typestyle */
* {
  margin: 0 auto;
  padding: 0;
}

#center {
  width: 990px;
  height: auto;
  margin-top: 150px;
}

body {
  background-color: beige;
}

.box {
  width: 185px;
  height: 185px;
  border: 3px outset rgb(85, 148, 148);
  background-color: whitesmoke;
  font-size: 12px;
  display: inline-flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-around;
}

.box img {
  margin-bottom: 8px;
}

.big_box {
  align-items: center;
  background-color: azure;
  width: 835px;
  height: 100%;
  min-height: 800px;
  padding: 10px;
  font-size: 21px;
  font-weight: 800px;
  line-height: 40px;
}

.test {
  text-decoration: none;
  color: rgb(36, 102, 143);
  font-size: 25px;
}

.test:hover {
  color: rgb(80, 40, 107);
}

.box:hover {
  transition: 0.15s;
  transform: scale(1.08);
}

.box2 {
  display: inline;
}

.line {
  margin: 5px;
}

.small_box {
  margin-top: 20px;
  border: 3px solid rgb(198, 205, 205);
  padding: 11px;
}

.typename {
  font-size: 32px;
  font-family: 'Tiro Tamil', serif;
  color: rgba(27, 45, 82, 0.715);
}

image {
  margin-bottom: 0p;
}

.logout {
  width: 55px;
  height: 30px;
  margin: 10px 0 0 780px;
  border: 0;
  outline: none;
  border-radius: 100px;
  box-shadow: 1px 1px 1px rgb(109, 109, 109), -1px -1px 1px rgb(237, 237, 237);
  transition: 0.3s;
  background-color: rgb(229, 241, 241);
  position: fixed;
}
</style>
