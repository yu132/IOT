<template>
  <div class="lamp-info-wrapper" :class="{ 'is-on': lamp.isOn }">
    <div class="header">
      <i class="el-icon-table-lamp"></i>
      <span>{{ lamp.name }}</span>
      <span>{{ isConnectedStr }}</span>
    </div>
    <div class="setting">
      <!-- TODO -->
      <div>颜色: {{ colorStr }}</div>
      <div>lightness: {{ lamp.lightness }}</div>
    </div>
    <div class="last-use-time-str-wrapper">{{ lastUseTimeStr }}</div>
    <div class="btns-wrapper">
      <!-- TODO -->
      <el-button type="danger" plain>移除设备</el-button>
      <el-button type="primary" plain>{{ turnOnOffStr }}</el-button>
    </div>
  </div>
</template>

<script>
// 设备管理-灯泡信息
import { consts } from "./../../../util/consts";
export default {
  name: "DeviceManage_LampInfo",
  props: {
    lamp: {
      type: Object,
      require: true,
    },
  },
  computed: {
    colorStr() {
      return consts.colors[this.lamp.color];
    },
    isConnectedStr() {
      return this.lamp.isConnected ? "已连接" : "已断开连接";
    },
    lastUseTimeStr() {
      const time = new Date(this.lamp.lastUseTime);
      const year = time.getFullYear();
      const month = time.getMonth() + 1;
      const date = time.getDate();
      const hour = time.getHours();
      const minute = time.getMinutes();
      const second = time.getSeconds();
      return `${year}-${month}-${date} ${hour}:${minute}:${second}`;
    },
    turnOnOffStr() {
      return this.lamp.isOn ? "关灯" : "开灯";
    },
  },
};
</script>

<style>
.lamp-info-wrapper {
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>
