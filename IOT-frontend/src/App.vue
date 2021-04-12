<template>
  <div id="app">
    <Header />
    <div class="one-page">
      <DeviceManage v-show="menuIndex === deviceManageMenuIndex" />
      <IntelligentScene v-show="menuIndex === intelligentSceneMenuIndex" />
      <DataChart v-show="menuIndex === dataChartMenuIndex" />
      <Footer />
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import Header from "./components/Header";
import Footer from "./components/Footer";
import DeviceManage from "./components/DeviceManage";
import IntelligentScene from "./components/IntelligentScene";
import DataChart from "./components/DataChart";
import { consts } from "./util/consts";

export default {
  name: "App",
  components: {
    Header,
    DeviceManage,
    IntelligentScene,
    DataChart,
    Footer,
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
}
</style>
