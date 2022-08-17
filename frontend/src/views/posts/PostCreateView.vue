<!-- eslint-disable no-tabs -->
<template>
  <TheDarkHeader></TheDarkHeader>

  <div style="padding: 5% 10%">
    <h2>게시글 등록</h2>
    <hr class="my-4" />
    <PostForm v-model:title="form.title" v-model:content="form.content">
      <template #selectCat>
        <select
          :value="form.cat"
          v-model="form.cat"
          class="form-select"
          style="width: 100px"
        >
          <option value="정보">정보</option>
          <option value="질문">질문</option>
          <option value="사담">사담</option>
        </select>
      </template>
    </PostForm>

    <div class="d-flex flex-row-reverse gap-2 mt-4">
      <button type="button" class="btn btn-outline-dark" @click="goListPage">
        목록
      </button>
      <button class="btn btn-primary" @click="createPost">저장</button>
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
      form: {
        cat: '정보',
        title: '',
        content: ''
      }
    }
  },
  methods: {
    goListPage() {
      this.$router.push({ name: 'PostList' })
    },
    createPost() {
      boardApi
        .createPost({
          cat: this.form.cat,
          title: this.form.title,
          content: this.form.content
        })
        .then()
        .catch((err) => console.log(err.config))
      alert('등록이 완료되었습니다.')
      setTimeout(() => {
        this.$router.push({ name: 'PostList' })
      }, 1000)
    }
  }
}
</script>

<style lang="scss" scoped></style>
