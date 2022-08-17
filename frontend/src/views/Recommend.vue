<template>
  <TheBeigeHeader></TheBeigeHeader>
  <div></div>

  <section class="main2">
    <div class="inner">
      <div class="mainTitle">
        <div class="topBox">
          <p class="tit"><span>Plant for You</span>나를 위한 추천 식물은?</p>
          <p class="txt">이젠 혼자 말고, 식물과 함께</p>
          <div class="bottomBox">
            <span @click="$router.push('/test')"
              ><img src="../assets/ico_csr.png" alt=""
            /></span>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script setup>
import TheBeigeHeader from '@/layouts/TheBeigeHeader.vue'
import { useRoute } from 'vue-router'

const route = useRoute()
console.log('route.path: ', route.path)
</script>

<style scoped>
.main2 .topBox {
  width: 780px;
  position: absolute;
  top: 53%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
}

.main2 .topBox {
  font-size: 46px;
  color: #fff;
  line-height: 1.3;
  font-weight: 500;
  margin-bottom: 25px;
}

.main2 .topBox .tit {
  border-bottom: 4px solid white;
  padding-bottom: 23px;
}

.main2 .topBox .tit span {
  display: block;
  font-weight: 700;
}

.main2 .topBox .txt {
  color: #fff;
  font-size: 17px;
  line-height: 1.4;
  letter-spacing: -1px;
  opacity: 0.8;
  margin-top: 44px;
  font-style: italic;
}

.main2 .inner .bottomBox {
  margin-top: 30px;
}

/*section main*/
.main2 {
  width: 100%;
  height: auto;
  background-image: url(../assets/main_visual_05.jpg);
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  position: relative;
}

.main2 .inner {
  width: 1400px;
  height: 750px;
  margin: 0 auto;
  position: relative;
  color: white;
}
</style>

<!-- eslint-disable no-tabs
<template>
  <div>
    <h2>식물추천</h2>
    <p>{{ $route.path }}</p>
    <p>{{ $route.name }}</p>
    <button class="btn btn-primary" @click="$router.push('/')">
      Home으로 이동
    </button>
  </div>
</template>

<script setup>
import { useRoute } from 'vue-router'

const route = useRoute()
console.log('route.path: ', route.path)
</script>

<style lang="scss" scoped></style> -->
