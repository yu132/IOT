<template>
  <div
    class="lamp-info-wrapper card"
    :class="{ 'is-on': lamp.isOn, 'not-connected': !lamp.isConnected }"
  >
    <div class="header row-height">
      <span>
        {{ lamp.name }}
      </span>
    </div>
    <div class="color-wrapper row-height">
      <span class="info-for">颜色：</span>
      <span
        class="color-rect"
        :style="{ backgroundColor: lampColor }"
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
        :disabled="!lamp.isConnected"
        @change="onLightnessChange"
      ></el-slider>
    </div>
    <div class="last-use-time-wrapper">
      <i
        class="el-icon-table-lamp"
        :style="{
          color: lampColor,
          opacity: lampOpacity,
        }"
      />
      <span>
        {{ lastUseTimeStr }}
      </span>
    </div>
    <div class="footer row-height">
      <span class="connect-prompt">
        {{ isConnectedStr }}
      </span>
      <span
        class="connect-control-span clickable-span"
        @click="onConnectControlClick"
      >
        {{ connectControlStr }}
      </span>
      <span class="turn-on-off-span clickable-span" @click="onTurnOnOffClick">
        {{ turnOnOffStr }}
      </span>
    </div>
  </div>
</template>

<script>
// 设备管理-灯泡信息
import { consts } from "../../../util/consts";
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
    lampColor() {
      return this.colors[this.lamp.color];
    },
    lampOpacity() {
      return 0.4 + this.lamp.lightness * 0.004;
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
    connectControlStr() {
      return this.lamp.isConnected ? "断开连接" : "连接设备";
    },
    turnOnOffStr() {
      return this.lamp.isOn ? "关灯" : "开灯";
    },
  },
  methods: {
    onColorRectClick() {
      if (!this.lamp.isConnected) {
        return;
      }
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
    async onConnectControlClick() {
      const { id, isConnected } = this.lamp;
      if (isConnected) {
        await api.disconnect(id);
      } else {
        await api.connect(id);
      }
      this.lamp.isConnected = !isConnected;
    },
    async onTurnOnOffClick() {
      if (!this.lamp.isConnected) {
        return;
      }
      const { id, isOn } = this.lamp;
      if (isOn) {
        await api.off(id);
      } else {
        await api.on(id);
      }
      this.lamp.isOn = !isOn;
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
  flex: 1;
  display: flex;
  align-items: flex-end;
  justify-content: flex-end;
  position: relative;
  text-align: right;
  color: #909399;
  font-size: 10px;
  padding: 0 6px;
}
.last-use-time-wrapper > .el-icon-table-lamp {
  position: absolute;
  left: 25%;
  bottom: 50%;
  transform: translate(-50%, 50%);
  font-size: 60px;
  filter: drop-shadow(2px 4px 6px black);
}
.footer {
  font-size: 14px;
  text-align: right;
  border-top: 1px solid #ebeef5;
}
.footer > .connect-prompt {
  font-size: 12px;
  float: left;
  opacity: 0.8;
}
.footer > .connect-control-span {
  color: #f56c6c;
}
.footer > .turn-on-off-span {
  color: #409eff;
}
.not-connected {
  filter: brightness(0.8);
}
.not-connected .color-rect,
.not-connected .turn-on-off-span {
  cursor: not-allowed;
}
</style>
