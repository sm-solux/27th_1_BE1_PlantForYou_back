<template>
  <div>
    <TheDarkHeader></TheDarkHeader>

    <div style="padding: 5% 10%">
      <h2>[ {{ post.cat }} ] {{ post.title }}</h2>
      <br />
      <div class="d-flex flex-row justify-content-around">
        <span>
          <span>{{ post.writerName }} </span>
          &nbsp;&nbsp;&nbsp;
          <span class="text-muted">{{ post.createdDate }} </span>
        </span>

        <span style="font-size: 18px">
          <i class="bi bi-heart" v-if="!post.isLikes" @click="likes"></i>
          <i class="bi bi-heart-fill" v-if="post.isLikes" @click="unlikes"></i>
          <span>&nbsp;{{ post.likes }}</span>

          &nbsp;&nbsp;&nbsp;

          <i class="bi bi-bookmark" v-if="!post.isScrap" @click="scrap"></i>
          <i
            class="bi bi-bookmark-fill"
            v-if="post.isScrap"
            @click="unscrap"
          ></i>
          <span>&nbsp;{{ post.scraps }}</span>
        </span>
      </div>

      <br />
      <hr class="my-4" />
      <br />
      <p>{{ post.content }}</p>

      <br />
      <br />

      <div class="row g-2">
        <div class="col-auto me-auto"></div>

        <div class="col-auto">
          <button class="btn btn-outline-dark" @click="goListPage">목록</button>
        </div>

        <div v-if="post.isMyPost" class="col-auto">
          <button class="btn btn-outline-primary" @click="goEditPage">
            수정
          </button>
        </div>

        <div v-if="post.isMyPost" class="col-auto">
          <button class="btn btn-outline-danger" @click="removePost">
            삭제
          </button>
        </div>
      </div>

      <br />
      <br />

      <div class="row g-2">
        <div class="list-group">
          <div
            class="list-group-item d-flex gap-3 py-3 flex-column"
            aria-current="true"
            :key="cmt.commentId"
            v-for="cmt in post.commentList"
          >
            <div class="d-flex gap-2 w-100 flex-column" style="padding: 5px">
              <div class="d-flex flex-row justify-content-between">
                <h6 class="mb-0">{{ cmt.writerName }}</h6>
                <small class="opacity-50 text-nowrap">
                  <a href="#cmt_input" @click="saveParentId(cmt.commentId)"
                    >대댓글</a
                  >
                  &nbsp;
                  <span v-if="cmt.isMyComment">
                    <a href="#">수정</a>
                    &nbsp;
                    <a href="#" @click="removeComment(cmt.commentId)">삭제</a>
                    &nbsp;&nbsp;
                  </span>
                  {{ cmt.createdDate }}</small
                >
              </div>
              <div class="mb-0 opacity-75 d-flex">
                {{ cmt.content }}
              </div>
            </div>

            <div class="list-group" style="margin: 0px -10px -10px 15px">
              <div
                class="list-group-item d-flex gap-3 py-3 flex-column"
                aria-current="true"
                :key="child.commentId"
                v-for="child in cmt.childList"
              >
                <div
                  class="d-flex gap-2 w-100 flex-column"
                  style="padding: 5px"
                >
                  <div class="d-flex flex-row justify-content-between">
                    <h6 v-if="!child.isDelete" class="mb-0">
                      {{ child.writerName }}
                    </h6>
                    <div
                      v-if="child.isDelete"
                      class="mb-0 opacity-75 d-flex flex-row"
                    >
                      (알 수 없음)
                    </div>

                    <small class="opacity-50 text-nowrap">
                      <span v-if="child.isMyComment">
                        <a href="#">수정</a>
                        &nbsp;
                        <a href="#" @click="removeComment(child.commentId)"
                          >삭제</a
                        >
                        &nbsp;&nbsp;
                      </span>
                      {{ child.createdDate }}</small
                    >
                  </div>

                  <div
                    v-if="!child.isDelete"
                    class="mb-0 opacity-75 d-flex"
                    style="text-align: left"
                  >
                    {{ child.content }}
                  </div>
                  <div
                    v-if="child.isDelete"
                    class="mb-0 opacity-75 d-flex flex-row"
                  >
                    삭제된 대댓글입니다.
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div class="list-group">
          <form class="d-flex" role="search">
            <input
              class="form-control me-2"
              placeholder="댓글을 입력해주세요"
              v-model="content"
              id="cmt_input"
            />
            <div class="col-auto">
              <button class="btn btn-outline-success" @click="createComment">
                작성
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>

    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
  </div>
</template>

<script>
import TheDarkHeader from '@/layouts/TheDarkHeader.vue'
import * as boardApi from '@/api/board'

export default {
  components: {
    TheDarkHeader
  },
  data() {
    return {
      postId: this.$route.params.id,
      post: {},
      content: '',
      parentId: null
    }
  },
  created() {
    boardApi.getPost(this.postId).then((res) => {
      this.post = res.data
    })
  },
  methods: {
    goListPage() {
      this.$router.push({ name: 'PostList' })
    },
    goEditPage() {
      this.$router.push({ name: 'PostEdit', params: { id: this.postId } })
    },
    saveParentId(parentId) {
      this.parentId = parentId
    },
    createComment() {
      boardApi
        .saveComment({
          content: this.content,
          parentId: this.parentId,
          postId: this.postId
        })
        .then((res) => {
          this.post.commentList = res.data
          this.parentId = null
        })
    },
    removePost() {
      if (confirm('삭제 하시겠습니까?') === false) {
        return
      }
      boardApi
        .deletePost(this.postId)
        .then()
        .catch((err) => console.log(err.config))
      alert('삭제가 완료되었습니다.')
      setTimeout(() => {
        this.$router.push({ name: 'PostList' })
      }, 1000)
    },
    removeComment(commentId) {
      if (confirm('삭제 하시겠습니까?') === false) {
        return
      }
      boardApi.deleteComment(commentId).then((res) => {
        this.post.commentList = res.data
        this.parentId = null
      })
    },
    async likes() {
      const res = await boardApi.likes(this.postId)
      this.post.likes = res.data.likes
      this.post.isLikes = res.data.isLikes
    },
    async scrap() {
      const res = await boardApi.scrap(this.postId)
      this.post.scraps = res.data.scraps
      this.post.isScrap = res.data.isScrap
    },

    async unlikes() {
      const res = await boardApi.unlikes(this.postId)
      this.post.likes = res.data.likes
      this.post.isLikes = res.data.isLikes
    },
    async unscrap() {
      const res = await boardApi.unscrap(this.postId)
      this.post.scraps = res.data.scraps
      this.post.isScrap = res.data.isScrap
    }
  }
}
</script>

<style lang="scss" scoped></style>
