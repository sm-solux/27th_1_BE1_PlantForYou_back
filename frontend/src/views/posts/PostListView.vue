<!-- eslint-disable no-tabs -->
<template>
  <link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
    crossorigin="anonymous"
  />
  <div style="padding: 3% 5%">
    <h2>게시글 목록</h2>
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
  </div>
</template>

<script>
import AppGrid from '@/components/app/AppGrid.vue'
import PostItem from '@/components/posts/PostItem.vue'
import * as boardApi from '@/api/board'

export default {
  components: {
    AppGrid,
    PostItem
  },
  data() {
    return {
      posts: []
    }
  },
  created() {
    boardApi.getPostList().then((res) => {
      this.posts = res.data.content
    })
  },
  methods: {
    goPage(postId) {
      this.$router.push({
        name: 'PostDetail',
        params: { id: postId }
      })
    }
  }
}
</script>

<style lang="scss" scoped></style>
