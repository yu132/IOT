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
