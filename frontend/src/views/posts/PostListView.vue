<!-- eslint-disable no-tabs -->
<template>
  <TheDarkHeader></TheDarkHeader>

  <div style="padding: 5% 10%">
    <div class="row g-2">
      <div class="col-auto me-auto"><h2>게시글 목록</h2></div>

      <div class="col-auto">
        <button class="btn btn-outline-dark" @click="goWrite">글쓰기</button>
      </div>
    </div>

    <hr class="my-4" />

    <AppGrid :items="posts" col-class="col-12 col-md-6 col-lg-4">
      <template v-slot="{ item }">
        <PostItem
          :cat="item.cat"
          :title="item.title"
          :content="item.content"
          :createdDate="item.createdDate"
          :likes="item.likes"
          :scraps="item.scraps"
          @click="goPage(item.postId)"
        ></PostItem>
      </template>
    </AppGrid>

    <br />
    <br />
    <br />
    <br />

    <div
      class="d-flex flex-row justify-content-center gap-5"
      style="backtround-color: red"
    >
      <span
        :key="num"
        v-for="num in page"
        @click="getPostList(num)"
        style="cursor: pointer"
      >
        <strong v-if="num === pageNumber">{{ num + 1 }}</strong>
        <span v-else>{{ num + 1 }}</span>
      </span>
    </div>
  </div>

  <br />
  <br />
  <br />
</template>

<script>
import AppGrid from '@/components/app/AppGrid.vue'
import PostItem from '@/components/posts/PostItem.vue'
import TheDarkHeader from '@/layouts/TheDarkHeader.vue'
import * as boardApi from '@/api/board'

export default {
  components: {
    AppGrid,
    PostItem,
    TheDarkHeader
  },
  data() {
    return {
      posts: [],
      page: [],
      pageNumber: 0
    }
  },
  created() {
    this.getPostList(0)
  },
  methods: {
    goPage(postId) {
      this.$router.push({
        name: 'PostDetail',
        params: { id: postId }
      })
    },
    goWrite() {
      this.$router.push({ name: 'PostCreate' })
    },
    getPostList(pageNumber) {
      boardApi.getPostList(pageNumber).then((res) => {
        this.posts = res.data.content
        this.makePage(res.data.totalPages, res.data.pageable.pageNumber)
      })
    },
    makePage(totalPages, pageNumber) {
      this.page = []
      this.pageNumber = pageNumber
      for (let i = 0; i < totalPages; i++) {
        this.page.push(i)
      }
    }
  }
}
</script>

<style lang="scss" scoped></style>
