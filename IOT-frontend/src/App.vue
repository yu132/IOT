<template>
  <div id="app">
    <Header />
    <div class="one-page">
      <DeviceManage v-show="menuIndex === deviceManageMenuIndex" />
      <IntelligentScene v-show="menuIndex === intelligentSceneMenuIndex" />
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import Header from "./components/Header";
import DeviceManage from "./components/DeviceManage";
import IntelligentScene from "./components/IntelligentScene";
import { consts } from "./util/consts";

export default {
  name: "App",
  components: {
    Header,
    DeviceManage,
    IntelligentScene,
  },
  computed: {
    ...mapState(["menuIndex"]),
  },
  data() {
    const {
      deviceManageMenuIndex,
      intelligentSceneMenuIndex,
      dataChartMenuIndex,
    } = consts;
    return {
      deviceManageMenuIndex,
      intelligentSceneMenuIndex,
      dataChartMenuIndex,
    };
  },
  methods: {
    ...mapActions(["initStore"]),
  },
  mounted() {
    this.initStore();
  },
};
</script>

<style>
html,
body {
  width: 100%;
  height: 100%;
  box-sizing: border-box;
  margin: 0;
}
.clickable-span {
  cursor: pointer;
  opacity: 0.8;
  font-weight: bold;
}
.clickable-span:hover {
  opacity: 1;
}
</style>

<style scoped>
#app {
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
  box-sizing: border-box;
  margin: 0;
  overflow: hidden scroll;
  background: #e9ecf3;

  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}
#app::-webkit-scrollbar {
  display: none;
}
#app > .one-page {
  width: 100%;
  height: 100%;
  box-sizing: border-box;
  position: relative;
  margin: 60px 0;
}
</style>
