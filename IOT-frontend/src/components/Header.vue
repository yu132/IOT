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
  background: #ebeef5;
  border-bottom: 1px solid #ebeef5;
  box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
}
</style>
