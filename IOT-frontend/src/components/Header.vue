<template>
  <el-menu :default-active="menuIndex" mode="horizontal" @select="handleSelect">
    <el-menu-item :index="deviceManageMenuIndex">设备管理</el-menu-item>
    <el-menu-item :index="intelligentSceneMenuIndex">智慧场景</el-menu-item>
    <el-menu-item :index="dataChartMenuIndex">数据图表</el-menu-item>
  </el-menu>
</template>

<script>
// 头部组件
import { mapState, mapMutations } from "vuex";
import { consts } from "../util/consts";

export default {
  name: "Header",
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
    ...mapMutations(["setMenuIndex"]),
    handleSelect(index) {
      if (index === this.dataChartMenuIndex) {
        this.jumpToDataChart();
        return;
      }
      this.setMenuIndex(index);
    },
    jumpToDataChart() {
      window.location = consts.dataChartUrl;
    },
  },
};
</script>

<style scoped>
.el-menu {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  z-index: 2000;
}
</style>
