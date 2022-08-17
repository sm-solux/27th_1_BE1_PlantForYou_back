<!-- eslint-disable no-tabs -->
<template>
  <TheDarkHeader></TheDarkHeader>

  <div style="padding: 5% 10%">
    <h2>게시글 수정</h2>
    <hr class="my-4" />
    <PostForm v-model:title="form.title" v-model:content="form.content">
      <template #selectCat>
        <select
          :value="form.cat"
          class="form-select"
          style="width: 100px"
          disabled
        >
          <option value="정보">정보</option>
          <option value="질문">질문</option>
          <option value="사담">사담</option>
        </select>
      </template>
    </PostForm>

    <div class="d-flex flex-row-reverse gap-2 mt-4">
      <button
        type="button"
        class="btn btn-outline-danger"
        @click="goDetailPage"
      >
        취소
      </button>
      <button class="btn btn-primary" @click="editPost">수정</button>
    </div>
  </div>
</template>

<script>
import PostForm from '@/components/posts/PostForm.vue'
import TheDarkHeader from '@/layouts/TheDarkHeader.vue'
import * as boardApi from '@/api/board'

export default {
  components: {
    PostForm,
    TheDarkHeader
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
    editPost() {
      boardApi
        .editPost(this.postId, {
          title: this.form.title,
          content: this.form.content
        })
        .then()
        .catch((err) => console.log(err.config))
      alert('수정이 완료되었습니다!')
      setTimeout(() => {
        this.$router.push({ name: 'PostDetail', params: { id: this.postId } })
      }, 1000)
    }
  }
}
</script>

<style lang="scss" scoped></style>
