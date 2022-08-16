<template>
  <TheHeader></TheHeader>
  <div class="may" id="may1">
    <div class="back">
      <strong>Menu</strong>
    </div>
    <ul>
      <li>
        <RouterLink to="/about"> <span>식물추천</span> </RouterLink>
      </li>
      <li>
        <RouterLink to="/dictionary"><span>식물사전</span></RouterLink>
      </li>
      <li>
        <RouterLink to="/posts"><span>커뮤니티</span></RouterLink>
      </li>
    </ul>
  </div>


  <h2>식물사전</h2>
  <article id="center">

    <div class="line">
      <div class="box2"><a href="plant/plant1.html">
          <div class="box"><span>식물1</span><img src="image/sns_1.jpg" width="158px"></div>
        </a></div>
      <div class="box2"><a href="plant/plant2.html">
          <div class="box">식물2<img src="image/sns_2.jpg" width="158px"></div>
        </a></div>
      <div class="box2"><a href="plant/plant3.html">
          <div class="box">식물3<img src="image/sns_3.jpg" width="158px"></div>
        </a></div>
      <div class="box2"><a href="plant/plant4.html">
          <div class="box">식물4<img src="image/sns_4.jpg" width="158px"></div>
        </a></div>
      <div class="box2"><a href="plant/plant1.html">
          <div class="box">식물1<img src="image/sns_1.jpg" width="158px"></div>
        </a></div>
    </div>
    <div class="line">
      <div class="box2"><a href="plant/plant5.html">
          <div class="box">식물5<img src="image/sns_5.jpg" width="158px"></div>
        </a></div>
      <div class="box2"><a href="plant/plant6.html">
          <div class="box">식물6<img src="image/sns_6.jpg" width="158px"></div>
        </a></div>
      <div class="box2"><a href="plant/plant7.html">
          <div class="box">식물7<img src="image/sns_7.jpg" width="158px"></div>
        </a></div>
      <div class="box2"><a href="plant/plant8.html">
          <div class="box">식물8<img src="image/sns_8.jpg" width="158px"></div>
        </a></div>
      <div class="box2"><a href="plant/plant5.html">
          <div class="box">식물5<img src="image/sns_5.jpg" width="158px"></div>
        </a></div>
    </div>
    <div class="line">
      <div class="box2"><a href="plant/plant9.html">
          <div class="box">식물9<img src="image/sns_1.jpg" width="158px"></div>
        </a></div>
      <div class="box2"><a href="plant/plant10.html">
          <div class="box">식물10<img src="image/sns_2.jpg" width="158px"></div>
        </a></div>
      <div class="box2"><a href="plant/plant11.html">
          <div class="box">식물11<img src="image/sns_3.jpg" width="158px"></div>
        </a></div>
      <div class="box2"><a href="plant/plant12.html">
          <div class="box">식물12<img src="image/sns_4.jpg" width="158px"></div>
        </a></div>
      <div class="box2"><a href="plant/plant9.html">
          <div class="box">식물9<img src="image/sns_1.jpg" width="158px"></div>
        </a></div>
    </div>
    <div class="line">
      <div class="box2"><a href="plant/plant13.html">
          <div class="box">식물13<img src="image/sns_5.jpg" width="158px"></div>
        </a></div>
      <div class="box2"><a href="plant/plant14.html">
          <div class="box">식물14<img src="image/sns_6.jpg" width="158px"></div>
        </a></div>
      <div class="box2"><a href="plant/plant15.html">
          <div class="box">식물15<img src="image/sns_7.jpg" width="158px"></div>
        </a></div>
      <div class="box2"><a href="plant/plant16.html">
          <div class="box">식물16<img src="image/sns_8.jpg" width="158px"></div>
        </a></div>
      <div class="box2"><a href="plant/plant13.html">
          <div class="box">식물13<img src="image/sns_5.jpg" width="158px"></div>
        </a></div>
    </div>
    <div class="line">
      <div class="box2"><a href="plant/plant13.html">
          <div class="box">식물13<img src="image/sns_5.jpg" width="158px"></div>
        </a></div>
      <div class="box2"><a href="plant/plant14.html">
          <div class="box">식물14<img src="image/sns_6.jpg" width="158px"></div>
        </a></div>
      <div class="box2"><a href="plant/plant15.html">
          <div class="box">식물15<img src="image/sns_7.jpg" width="158px"></div>
        </a></div>
      <div class="box2"><a href="plant/plant16.html">
          <div class="box">식물16<img src="image/sns_8.jpg" width="158px"></div>
        </a></div>
      <div class="box2"><a href="plant/plant13.html">
          <div class="box">식물13<img src="image/sns_5.jpg" width="158px"></div>
        </a></div>
    </div>
    <div class="line">
      <div class="box2"><a href="plant/plant9.html">
          <div class="box">식물9<img src="image/sns_1.jpg" width="158px"></div>
        </a></div>
      <div class="box2"><a href="plant/plant10.html">
          <div class="box">식물10<img src="image/sns_2.jpg" width="158px"></div>
        </a></div>
      <div class="box2"><a href="plant/plant11.html">
          <div class="box">식물11<img src="image/sns_3.jpg" width="158px"></div>
        </a></div>
      <div class="box2"><a href="plant/plant12.html">
          <div class="box">식물12<img src="image/sns_4.jpg" width="158px"></div>
        </a></div>
      <div class="box2"><a href="plant/plant9.html">
          <div class="box">식물9<img src="image/sns_1.jpg" width="158px"></div>
        </a></div>
    </div>

  </article>



</template>



<script>
import TheHeader from '@/layouts/TheHeader.vue';
</script>
<style>
/* typestyle */
* {
  margin: 0 auto;
  padding: 0;
}


#center {
  width: 990px;
  height: auto;
  margin-top: 150px
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


#may1 strong {
  color: beige;
}

#may1 ul li {
  color: darkslategray;
  border-bottom: 1px solid darkslategray;
}

#may1 ul li:nth-child(2) {
  border-bottom: 1px solid darkslategray;
}

#may1 ul li:nth-child(3) {
  border-bottom: 1px solid darkslategray;
}
</style>





<!-- eslint-disable no-tabs
<template>
  <div>
    <h2>식물사전</h2>
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
