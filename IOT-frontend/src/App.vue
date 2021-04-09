<template>
  <el-container id="app">
    <Header />
    <el-main>
      <DeviceManage />
      <IntelligentScene />
      <DataChart />
    </el-main>
    <Footer />
  </el-container>
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
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
