<!-- eslint-disable no-tabs -->
<template>
  <TheHeader></TheHeader>
  <div style="padding: 5% 10%">
    <h2>게시글 등록</h2>
    <hr class="my-4" />
    <PostForm
      v-model:cat="form.cat"
      v-model:title="form.title"
      v-model:content="form.content"
    >
      <template #actions>
        <button type="button" class="btn btn-outline-dark" @click="goListPage">
          목록
        </button>
        <button class="btn btn-primary" @click="create">저장</button>
      </template>
    </PostForm>
  </div>
</template>

<script>
import PostForm from '@/components/posts/PostForm.vue'
import TheHeader from '@/layouts/TheHeader.vue'
import * as boardApi from '@/api/board'

export default {
  components: {
    PostForm,
    TheHeader
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
    create() {
      boardApi
        .createPost(this.form)
        .then()
        .catch((err) => console.log(err.config))
      alert('등록이 완료되었습니다!')
      this.$router.push({ name: 'PostList' })
    }
  }
}
</script>

<style lang="scss" scoped></style>
