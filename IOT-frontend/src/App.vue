<template>
  <div id="app">
    <Header />
    <DeviceManage />
    <IntelligentScene />
    <DataChart />
  </div>
</template>

<script>
import Header from "./components/Header";
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
