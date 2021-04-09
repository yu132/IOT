<template>
  <div>{{ title }}</div>
</template>

<script>
// 头部组件
import { mapState } from "vuex";

export default {
  name: "Header",
  computed: {
    ...mapState(["title"]),
  },
};
</script>

<style>
</style>
