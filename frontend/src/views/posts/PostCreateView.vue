<!-- eslint-disable no-tabs -->
<template>
  <link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
    crossorigin="anonymous"
  />
  <div class="py-4">
    <div style="padding: 3% 5%">
      <h2>게시글 등록</h2>
      <hr class="my-4" />
      <PostForm
        v-model:cat="form.cat"
        v-model:title="form.title"
        v-model:content="form.content"
      >
        <template #actions>
          <button
            type="button"
            class="btn btn-outline-dark"
            @click="goListPage"
          >
            목록
          </button>
          <button class="btn btn-primary" @click="create">저장</button>
        </template>
      </PostForm>
    </div>
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
      boardApi.createPost(this.form).then(() => {
        alert('등록이 완료되었습니다!')
        this.$router.push({ name: 'PostList' })
      })
    }
  }
}
</script>

<style lang="scss" scoped></style>
