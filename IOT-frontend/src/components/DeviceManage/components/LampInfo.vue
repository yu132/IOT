<template>
  <div class="lamp-info-wrapper card" :class="{ 'is-on': lamp.isOn }">
    <div class="header row-height">
      <span>
        {{ lamp.name }}
      </span>
    </div>
    <!-- TODO -->
    <div class="color-wrapper row-height">
      <span> 颜色: {{ colorStr }} </span>
    </div>
    <div class="lightness-wrapper row-height">
      <span> lightness: {{ lamp.lightness }} </span>
    </div>
    <div class="last-use-time-wrapper row-height">
      <span>
        {{ lastUseTimeStr }}
      </span>
    </div>
    <div class="footer row-height">
      <!-- TODO -->
      <span class="is-connect-prompt">
        {{ isConnectedStr }}
      </span>
      <span
        class="remove-device clickable-span"
        :style="{ color: '#F56C6C' }"
        @click="onRemoveDeviceClick"
      >
        移除设备
      </span>
      <span
        class="operate-device clickable-span"
        :style="{ color: '#409EFF' }"
        @click="onOperateDeviceClick"
      >
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
      return this.lamp.isConnected ? "已连接" : "连接已断开";
    },
    lastUseTimeStr() {
      const time = new Date(this.lamp.lastUseTime);
      const hour = time.getHours();
      const minute = time.getMinutes();
      const second = time.getSeconds();
      const timeStr = `${hour}:${minute}:${second}`;

      const oneDateTime = 24 * 60 * 60 * 1000;
      const dateCount =
        Math.floor(Date.now() / oneDateTime) - Math.floor(time / oneDateTime);
      const dateStr =
        dateCount === 0
          ? "今天"
          : dateCount === 1
          ? "昨天"
          : `${dateCount}天前`;
      return `${dateStr} ${timeStr}`;
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
  font-size: 18px;
  text-align: center;
  position: relative;
  border-bottom: 1px solid #ebeef5;
}
.row-height {
  width: 100%;
  height: 24px;
  box-sizing: border-box;
  line-height: 24px;
  padding: 0 3px;
}
.last-use-time-wrapper {
  position: absolute;
  left: 0;
  bottom: 24px;
  text-align: right;
  color: #909399;
  font-size: 10px;
}
.footer {
  font-size: 14px;
  text-align: right;
  position: absolute;
  left: 0;
  bottom: 0;
  border-top: 1px solid #ebeef5;
}
.footer > .is-connect-prompt {
  font-size: 12px;
  float: left;
  opacity: 0.8;
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
