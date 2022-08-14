<!-- eslint-disable no-tabs -->
<template>
  <div>
    <h2>{{ post.title }}</h2>
    <div>
      <span>{{ post.cat }}</span> | <span>조회수 {{ post.hits }}</span> |
      <span>좋아요수 {{ post.likes }}</span> |
      <span>스크랩수 {{ post.scraps }}</span>
    </div>
    <br />
    <div>
      <span>작성자 {{ post.writerName }}</span> |
      <span>작성일 {{ post.createdDate }}</span>
    </div>

    <br />
    <button v-if="!post.isLikes" @click="likes">좋아요 등록</button>
    <button v-if="post.isLikes" @click="unlikes">좋아요 취소</button>
    &nbsp;
    <button v-if="!post.isScrap" @click="scrap">스크랩 등록</button>
    <button v-if="post.isScrap" @click="unscrap">스크랩 취소</button>
    <br />
    <br />
    <div>
      <p>{{ post.content }}</p>
    </div>

    <br />
    <br />
    <br />
    <div>
      <div :key="cmt.commentId" v-for="cmt in post.commentList">
        <span>{{ cmt.writerName }}</span> | <span>{{ cmt.content }}</span> |
        <span>{{ cmt.createdDate }}</span>
        <div :key="child.commentId" v-for="child in cmt.childList">
          <div v-if="!child.isDelete" class="cmt2">
            <span>{{ child.writerName }}</span> |
            <span>{{ child.content }}</span> |
            <span>{{ child.createdDate }}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import * as boardApi from '@/api/board'

export default {
  components: {},
  data() {
    return {
      post: []
    }
  },
  setup() {},
  created() {
    boardApi
      .getPost(1)
      .then((res) => {
        this.post = res.data
        console.log(res)
      })
      .catch((err) => {
        console.log(err.config)
      })
  },
  mounted() {},
  unmounted() {},
  methods: {
    async likes() {
      const res = await boardApi.likes(this.post.postId)
      this.post.likes = res.data.likes
      this.post.isLikes = res.data.isLikes
    },
    async scrap() {
      const res = await boardApi.scrap(this.post.postId)
      this.post.scraps = res.data.scraps
      this.post.isScrap = res.data.isScrap
    },

    async unlikes() {
      const res = await boardApi.unlikes(this.post.postId)
      this.post.likes = res.data.likes
      this.post.isLikes = res.data.isLikes
    },
    async unscrap() {
      const res = await boardApi.unscrap(this.post.postId)
      this.post.scraps = res.data.scraps
      this.post.isScrap = res.data.isScrap
    }
  }
}
</script>

<style scoped>
.cmt2 {
  text-indent: 50px;
  font-weight: bold;
  color: #42b983;
}
</style>
