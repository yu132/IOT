<template>
  <div class="footer-wrapper">
    <LampMiniInfo v-for="lamp in connectedLamps" :key="lamp.id" :lamp="lamp" />
    <NoLampPrompt v-if="noConnectedLamps" />
  </div>
</template>

<script>
// 智慧场景-底部灯具信息组件
import { mapState } from "vuex";
import LampMiniInfo from "./components/LampMiniInfo";
import NoLampPrompt from "./components/NoLampPrompt";

export default {
  name: "IntelligentScene_LampInfoFooter",
  components: {
    LampMiniInfo,
    NoLampPrompt,
  },
  computed: {
    ...mapState(["lamps"]),
    connectedLamps() {
      return this.lamps.filter((lamp) => lamp.isConnected);
    },
    noConnectedLamps() {
      return this.connectedLamps.length === 0;
    },
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
