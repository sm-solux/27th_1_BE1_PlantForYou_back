<!-- eslint-disable no-tabs -->
<template>
  <div>
    <form>
      <div class="mb-3 d-flex flex-row">
        <slot name="selectCat"></slot>
        &nbsp;&nbsp;&nbsp;
        <input
          :value="title"
          @input="$emit('update:title', $event.target.value)"
          type="text"
          class="form-control"
          id="title"
          placeholder="제목"
        />
      </div>

      <div class="mb-3">
        <textarea
          :value="content"
          @input="$emit('update:content', $event.target.value)"
          class="form-control"
          id="content"
          rows="10"
          placeholder="내용"
        ></textarea>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  props: {
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
