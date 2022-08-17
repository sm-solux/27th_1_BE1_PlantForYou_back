<template>
  <TheDarkHeader></TheDarkHeader>

  <div style="padding: 5% 10%">
    <div class="d-flex flex-column">
      <div class="d-flex flex-row justify-content-center gap-5">
        <img
          :src="plant.imgUrl"
          class="bd-placeholder-img rounded-circle"
          width="330"
          height="330"
          role="img"
          alt="식물 사진"
          preserveAspectRatio="xMidYMid slice"
          focusable="false"
        />
        <div class="d-flex flex-row align-items-center">
          <div class="col p-4 d-flex flex-column position-static">
            <h3 class="mb-4">{{ plant.name }}</h3>
            <div>
              <p class="card-text">
                <strong>볕양</strong>&nbsp;&nbsp;{{ sunAmtEmo }}
              </p>
              <p class="card-text">
                <strong>난이도</strong>&nbsp;&nbsp;{{ difficultyEmo }}
              </p>
              <p class="card-text">
                <strong>사이즈</strong>&nbsp;&nbsp;{{ sizeEmo }}
              </p>
              <p class="card-text">
                <strong>한 줄 기능</strong>&nbsp;&nbsp;&nbsp;{{
                  plant.funcHead
                }}
              </p>
            </div>
          </div>
        </div>
      </div>

      <br />
      <br />

      <div class="row g-2">
        <div class="col-auto me-auto"></div>

        <div class="col-auto">
          <button class="btn btn-outline-dark" @click="goListPage">목록</button>
        </div>
      </div>

      <br />

      <div class="row g-4 py-5 row-cols-1">
        <div class="feature col">
          <h4 class="mb-3">☀️ 적정 볕양과 온도는 얼마인가요?</h4>
          <p>
            {{ plant.sunAmtDesc }}
          </p>
        </div>
        <hr class="my-4" />

        <div class="feature col">
          <h4 class="mb-3">💧 물과 비료는 얼마나 주어야 하나요?</h4>
          <p>
            {{ plant.difficultyDesc }}
          </p>
        </div>
        <hr class="my-4" />

        <div class="feature col">
          <h4 class="mb-3">🌳 얼마나 크게 자라나요?</h4>
          <p>
            {{ plant.sizeDesc }}
          </p>
        </div>
        <hr class="my-4" />

        <div class="feature col">
          <h4 class="mb-3">
            <strong>'{{ plant.name }}'</strong> 에 대해 설명해주세요!
          </h4>
          <p>
            {{ plant.plantDesc }}
          </p>
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
      plantId: this.$route.params.id,
      sunAmtEmo: '',
      difficultyEmo: '',
      sizeEmo: '',
      plant: {}
    }
  },
  created() {
    plantApi.getPlant(this.plantId).then((res) => {
      this.plant = res.data
      this.sunAmtEmo = '☀️'.repeat(this.plant.sunAmtLevel)
      this.difficultyEmo = '💧'.repeat(this.plant.difficultyLevel)
      this.sizeEmo = '🌳'.repeat(this.plant.sizeLevel)
    })
  },
  methods: {
    goListPage() {
      this.$router.push({ name: 'PlantList' })
    }
  }
}
</script>
