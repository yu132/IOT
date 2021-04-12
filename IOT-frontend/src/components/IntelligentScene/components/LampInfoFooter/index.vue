<template>
  <div class="footer-wrapper">
    <LampMiniInfo v-for="lamp in lamps" :key="lamp.id" :lamp="lamp" />
  </div>
</template>

<script>
// 智慧场景-底部灯具信息组件
import { mapState } from "vuex";
import LampMiniInfo from "./components/LampMiniInfo";

export default {
  name: "IntelligentScene_LampInfoFooter",
  components: {
    LampMiniInfo,
  },
  computed: {
    ...mapState(["lamps"]),
  },
};
</script>

<style scoped>
.footer-wrapper {
  height: 60px;
  width: 100%;
  display: flex;
  align-items: center;
  position: absolute;
  left: 0;
  bottom: 0;
  overflow: scroll hidden;
  box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
}
.footer-wrapper::-webkit-scrollbar {
  display: none;
}
</style>
