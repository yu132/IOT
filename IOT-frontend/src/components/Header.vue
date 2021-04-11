<template>
  <div class="header-wrapper">{{ title }}</div>
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

<style scoped>
.header-wrapper {
  height: 60px;
  width: 100%;
  display: flex;
  align-items: center;
  box-sizing: border-box;
  position: fixed;
  left: 0;
  top: 0;
  padding-left: 20px;
  font-size: 28px;
  font-weight: bolder;
  z-index: 1;
  background: #f2f6fc;
  border-bottom: thin solid #dcdfe6;
  box-sizing: border-box;
}
</style>
