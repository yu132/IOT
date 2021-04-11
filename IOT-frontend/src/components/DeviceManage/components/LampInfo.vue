<template>
  <div class="lamp-info-wrapper card" :class="{ 'is-on': lamp.isOn }">
    <div class="header row-height">
      <span>
        {{ lamp.name }}
      </span>
    </div>
    <div class="color-wrapper row-height">
      <span class="info-for">颜色：</span>
      <span
        class="color-rect"
        :style="{ backgroundColor: colorStr }"
        @click="onColorRectClick"
      />
      <div class="color-selector-wrapper" v-show="colorSelectorWrapperShow">
        <span
          class="color-selector-rect"
          v-for="(color, index) in colors"
          :key="color"
          :style="{ backgroundColor: color }"
          @click="() => onColorSelectorClick(index)"
        />
      </div>
    </div>
    <div class="lightness-wrapper row-height">
      <span class="info-for">亮度：</span>
      <el-slider
        v-model="lightness"
        :style="{ flex: 1 }"
        @change="onLightnessChange"
      ></el-slider>
    </div>
    <div class="last-use-time-wrapper row-height">
      <span>
        {{ lastUseTimeStr }}
      </span>
    </div>
    <div class="footer row-height">
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
import { api } from "../../../api";
export default {
  name: "DeviceManage_LampInfo",
  props: {
    lamp: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      colorSelectorWrapperShow: false,
      lightness: this.lamp.lightness,
      colors: consts.colors,
    };
  },
  computed: {
    colorStr() {
      return this.colors[this.lamp.color];
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
    onColorRectClick() {
      if (this.colorSelectorWrapperShow) {
        return;
      }
      this.colorSelectorWrapperShow = true;
      setTimeout(() => {
        document.addEventListener("click", this.onDocumentClick);
      });
    },
    onDocumentClick() {
      this.colorSelectorWrapperShow = false;
      document.removeEventListener("click", this.onDocumentClick);
    },
    async onColorSelectorClick(colorIndex) {
      await api.color(this.lamp.id, colorIndex);
      this.lamp.color = colorIndex;
    },
    async onLightnessChange() {
      await api.brightness(this.lamp.id, this.lightness);
      this.lamp.lightness = this.lightness;
    },
    async onRemoveDeviceClick() {
      // TODO
      await api.disconnect(this.lamp.id);
      this.lamp.isConnected = false;
    },
    async onOperateDeviceClick() {
      const { id, isOn } = this.lamp;
      if (isOn) {
        await api.off(id);
      } else {
        await api.on(id);
      }
      this.lamp.isOn = !isOn;

      this.updateLastUseTime();
    },
    updateLastUseTime() {
      this.lamp.lastUseTime = Date.now();
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
  padding: 0 6px;
}
.color-wrapper,
.lightness-wrapper {
  display: flex;
  align-items: center;
  padding-right: 10px;
}
.color-wrapper > .color-rect {
  flex: 1;
  height: 16px;
  cursor: pointer;
  opacity: 0.8;
}
.color-wrapper > .color-rect:hover {
  opacity: 1;
}
.color-selector-wrapper {
  display: flex;
  padding: 3px;
  border: 1px solid #ebeef5;
  background: white;
  border-radius: 3px;
  position: absolute;
  right: 10px;
  transform: translateY(24px);
  z-index: 1;
}
.color-selector-wrapper > .color-selector-rect {
  width: 25px;
  height: 25px;
  cursor: pointer;
  opacity: 0.8;
}
.color-selector-wrapper > .color-selector-rect:hover {
  opacity: 1;
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
