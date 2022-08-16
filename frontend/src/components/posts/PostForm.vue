<!-- eslint-disable no-tabs -->
<template>
  <div>
    <form>
      <div class="mb-3 d-flex flex-row">
        <select :value="cat" class="form-select" style="width: 100px">
          <option value="정보">정보</option>
          <option value="질문">질문</option>
          <option value="사담">사담</option>
        </select>
        &nbsp;&nbsp;&nbsp;
        <input
          :value="title"
          type="text"
          class="form-control"
          id="title"
          placeholder="제목"
        />
      </div>

      <div class="mb-3">
        <textarea
          :value="content"
          class="form-control"
          id="content"
          rows="10"
          placeholder="내용"
        ></textarea>
      </div>

      <div class="d-flex flex-row-reverse gap-2 mt-4">
        <slot name="actions"></slot>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  props: {
    cat: {
      type: String
    },
    title: {
      type: String
    },
    content: {
      type: String
    }
  }
}
</script>

<style lang="scss" scoped></style>
