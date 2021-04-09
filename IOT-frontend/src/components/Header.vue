<template>
  <el-header>{{ title }}</el-header>
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
