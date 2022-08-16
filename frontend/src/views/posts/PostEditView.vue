<!-- eslint-disable no-tabs -->
<template>
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
      boardApi
        .editPost(this.postId, {
          cat: this.form.cat,
          title: this.form.title,
          content: this.form.content
        })
        .then(() => {
          alert('수정이 완료되었습니다!')
          this.$router.push({ name: 'PostDetail', params: { id: this.postId } })
        })
    }
  }
}
</script>

<style lang="scss" scoped></style>
