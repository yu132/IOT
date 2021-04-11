<template>
  <div id="app">
    <Header />
    <div class="one-page">
      <DeviceManage />
    </div>
    <div class="one-page">
      <IntelligentScene />
    </div>
    <div class="one-page">
      <DataChart />
      <Footer />
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";
import Header from "./components/Header";
import Footer from "./components/Footer";
import DeviceManage from "./components/DeviceManage";
import IntelligentScene from "./components/IntelligentScene";
import DataChart from "./components/DataChart";

export default {
  name: "App",
  components: {
    Header,
    DeviceManage,
    IntelligentScene,
    DataChart,
    Footer,
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
  width: 10px;
}
#app::-webkit-scrollbar-thumb {
  border-radius: 10px;
  background: #00adff57;
}
#app::-webkit-scrollbar-track {
  border-radius: 10px;
  box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
}
#app > .one-page {
  width: 100%;
  height: 100%;
  box-sizing: border-box;
  padding: 20px;
  padding-top: 60px;
  position: relative;
}
</style>
