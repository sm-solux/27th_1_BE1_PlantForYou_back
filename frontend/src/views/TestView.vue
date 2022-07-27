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
import axios from 'axios'
const host = 'http://ec2-15-165-93-45.ap-northeast-2.compute.amazonaws.com:8080'

export default {
  components: {},
  data() {
    return {
      post: []
    }
  },
  setup() {},
  created() {
    axios
      .get(host + '/api/posts/1')
      .then((res) => {
        this.post = res.data
        console.log(res)
      })
      .catch((err) => {
        console.log(err)
      })
  },
  mounted() {},
  unmounted() {},
  methods: {
    likes() {
      axios
        .post(host + '/api/posts/likes/' + this.post.postId)
        .then((res) => {
          console.log(res)
        })
        .catch((err) => {
          console.log(err)
        })
    },
    scrap() {
      axios
        .post(host + '/api/posts/scrap/' + this.post.postId)
        .then((res) => {
          console.log(res)
        })
        .catch((err) => {
          console.log(err)
        })
    },

    unlikes() {
      axios
        .delete(host + '/api/posts/likes/' + this.post.postId)
        .then((res) => {
          console.log(res)
        })
        .catch((err) => {
          console.log(err)
        })
    },
    unscrap() {
      axios
        .delete(host + '/api/posts/scrap/' + this.post.postId)
        .then((res) => {
          console.log(res)
        })
        .catch((err) => {
          console.log(err)
        })
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
