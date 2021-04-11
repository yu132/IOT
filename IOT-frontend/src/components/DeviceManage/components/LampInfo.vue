<template>
  <div class="lamp-info-wrapper card" :class="{ 'is-on': lamp.isOn }">
    <div class="header row-height">
      <span>{{ lamp.name }}</span>
    </div>
    <!-- TODO -->
    <div class="color-wrapper row-height">
      <span>颜色: {{ colorStr }}</span>
    </div>
    <div class="lightness-wrapper row-height">
      <span>lightness: {{ lamp.lightness }}</span>
    </div>
    <div class="info-wrapper row-height">
      <span class="is-connect-prompt">{{ isConnectedStr }}</span>
      <span>{{ lastUseTimeStr }}</span>
    </div>
    <div class="footer row-height">
      <!-- TODO -->
      <span class="remove-device clickable-span" @click="onRemoveDeviceClick"
        >移除设备</span
      >
      <span class="operate-device clickable-span" @click="onOperateDeviceClick">
        {{ turnOnOffStr }}
      </span>
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
      required: true,
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
      const hour = time.getHours();
      const minute = time.getMinutes();
      const second = time.getSeconds();
      return `${hour}:${minute}:${second}`;
    },
    turnOnOffStr() {
      return this.lamp.isOn ? "关灯" : "开灯";
    },
  },
  methods: {
    onRemoveDeviceClick() {
      // TODO
      console.log("onRemoveDeviceClick");
    },
    onOperateDeviceClick() {
      // TODO
      console.log("onOperateDeviceClick");
    },
  },
};
</script>

<style scoped>
.lamp-info-wrapper {
  width: 180px;
  display: flex;
  flex-direction: column;
  font-size: 14px;
  position: relative;
}
.header {
  width: 100%;
  font-size: 18px;
  box-sizing: border-box;
  text-align: center;
  position: relative;
  border-bottom: 1px solid #ebeef5;
}
.row-height {
  height: 24px;
  line-height: 24px;
}
.footer {
  width: 100%;
  font-size: 12px;
  box-sizing: border-box;
  text-align: right;
  position: absolute;
  left: 0;
  bottom: 0;
  border-top: 1px solid #ebeef5;
}
</style>
