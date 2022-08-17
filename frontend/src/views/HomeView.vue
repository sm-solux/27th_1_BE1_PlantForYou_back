<template>
  <TheBeigeHeader></TheBeigeHeader>
  <div>
    <section class="main">
      <div class="inner">
        <div class="mainTitle">
          <h2>나에게 딱맞는 반려식물은?</h2>
          <p>
            나의 성격과 라이프스타일, 공간에 딱 맞는 반려식물을 추천해드려요!
            <br />반려식물 MBTI 하러 GOGO!<br />
          </p>
          <span class="btn" @click="goTestPage">TEST 시작 </span>
        </div>
      </div>
    </section>
  </div>
</template>
<script>
import TheBeigeHeader from '@/layouts/TheBeigeHeader.vue'

export default {
  components: {
    TheBeigeHeader
  },
  methods: {
    goTestPage() {
      this.$router.push('/test')
    }
  }
}
</script>
<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700&display=swap');

/*layout*/
body {
  font-family: 'Nanum Gothic', sans-serif;
  font-size: 16px;
  color: #333;
  line-height: 1.25;
}

.main {
  width: 100%;
  height: auto;
  background-image: url(../assets/main_5month_02.jpg);
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  position: relative;
}

.main .inner {
  width: 1400px;
  height: 800px;
  margin: 0 auto;
  position: relative;
  color: white;
}

.main .inner .mainTitle {
  width: 653px;
  height: auto;
}

.main .inner .mainTitle h2 {
  position: absolute;
  top: 36%;
  right: 22%;
  font-size: 50px;
  line-height: 1.3;
  animation-name: titani;
  animation-duration: 1s;
}

@keyframes titani {
  0% {
    opacity: 0;
    top: 0;
  }

  30% {
    opacity: 0;
    top: 0;
  }

  100% {
    opacity: 1;
    top: 30%;
  }
}

.main .inner .mainTitle p {
  position: absolute;
  right: 23%;
  top: 47%;
  font-size: 19px;
  line-height: 1.6;
  animation-name: titani-p;
  animation-duration: 0.5s;
  text-align: right;
}

@keyframes titani-p {
  0% {
    opacity: 0;
    transform: scale(0);
  }

  30% {
    opacity: 0;
    transform: scale(0);
  }

  100% {
    opacity: 1;
    transform: scale(1);
  }
}

.main .inner .mainTitle .btn {
  position: absolute;
  right: 23%;
  top: 59%;
  display: inline-block;
  padding: 15px 25px;
  font-size: 18px;
  color: #fff;
  background: #000;
  animation-name: titani-btn;
  animation-duration: 2s;
}

@keyframes titani-btn {
  0% {
    opacity: 0;
    top: 70%;
  }

  30% {
    opacity: 0;
    top: 70%;
  }

  100% {
    opacity: 1;
    top: 63%;
  }
}
</style>

<!-- eslint-disable no-tabs
<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png" />
    <HelloWorld msg="Welcome to Your Vue.js App" />
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'HomeView',
  components: {
    HelloWorld
  }
}
</script> -->
