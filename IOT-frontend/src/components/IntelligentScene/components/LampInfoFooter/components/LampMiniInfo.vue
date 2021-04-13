<template>
  <div class="lamp-info-wrapper" :class="{ 'is-off': !lamp.isOn }">
    <i
      class="el-icon-table-lamp"
      :style="{
        color: lampColor,
        opacity: lampOpacity,
        filter: lampFilter,
      }"
    />
    <span>{{ lamp.name }}</span>
  </div>
</template>

<script>
// 智慧场景-底部灯具信息组件-灯泡缩略信息
import { consts } from "../../../../../util/consts";

export default {
  name: "IntelligentScene_LampInfoFooter_LampMiniInfo",
  props: {
    lamp: {
      type: Object,
      required: true,
    },
  },
  computed: {
    lampColor() {
      return consts.colors[this.lamp.color];
    },
    lampOpacity() {
      return 0.4 + this.lamp.lightness * 0.004;
    },
    lampFilter() {
      return this.lamp.isOn
        ? `drop-shadow(2px 4px 6px ${this.lampColor})`
        : `brightness(0.5)`;
    },
  },
};
</script>

<style scoped>
.lamp-info-wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  padding: 10px;
  height: 100%;
  box-sizing: border-box;
  background: white;
}
.lamp-info-wrapper > .el-icon-table-lamp {
  font-size: 32px;
  filter: drop-shadow(2px 4px 6px black);
}
.is-off > .el-icon-table-lamp {
  filter: grayscale(0.8);
}
.lamp-info-wrapper > span {
  font-size: 12px;
}
</style>
