<!-- eslint-disable no-tabs -->
<template>
  <AppCard>
    <h5 class="card-title text-truncate">[ {{ cat }} ] {{ title }}</h5>
    <p class="card-text text-truncate">
      {{ content }}
    </p>
    <template #footer>
      <div class="d-flex flex-row justify-content-between">
        <span class="text-muted">{{ createdDate }}</span>
        <span>
          <i class="bi bi-heart-fill"></i
          ><span>&nbsp;{{ likes }}</span> &nbsp;<i
            class="bi bi-bookmark-fill"
          ></i>
          <span>&nbsp;{{ scraps }}</span>
        </span>
      </div>
    </template>
  </AppCard>
</template>

<script>
import AppCard from '@/components/app/AppCard.vue'
export default {
  components: {
    AppCard
  },
  props: {
    cat: {
      type: String
    },
    title: {
      type: String
    },
    content: {
      type: String
    },
    createdDate: {
      type: String
    },
    likes: {
      type: Number
    },
    scraps: {
      type: Number
    }
  }
}
</script>

<style lang="scss" scoped></style>
