<!-- eslint-disable no-tabs -->
<template>
  <link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
    crossorigin="anonymous"
  />
  <div style="padding: 3% 5%">
    <h2>게시글 수정</h2>
    <hr class="my-4" />
    <PostForm
      v-model:cat="form.cat"
      v-model:title="form.title"
      v-model:content="form.content"
    >
      <template #actions>
        <button
          type="button"
          class="btn btn-outline-danger"
          @click="goDetailPage"
        >
          취소
        </button>
        <button class="btn btn-primary" @click="edit">수정</button>
      </template>
    </PostForm>
  </div>
</template>

<script>
import PostForm from '@/components/posts/PostForm.vue'
import * as boardApi from '@/api/board'

export default {
  components: {
    PostForm
  },
  data() {
    return {
      postId: this.$route.params.id,
      form: {
        cat: '정보',
        title: '',
        content: ''
      }
    }
  },
  created() {
    boardApi.getPostOnly(this.postId).then((res) => {
      this.form = res.data
    })
  },
  methods: {
    goDetailPage() {
      this.$router.push({ name: 'PostDetail', params: { id: this.postId } })
    },
    edit() {
      boardApi.editPost(this.postId, this.form).then(() => {
        alert('수정이 완료되었습니다!')
        this.$router.push({ name: 'PostDetail', params: { id: this.postId } })
      })
    }
  }
}
</script>

<style lang="scss" scoped></style>
