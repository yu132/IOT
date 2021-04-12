<template>
  <div class="lamp-info-wrapper">
    <i
      class="el-icon-table-lamp"
      :style="{
        color: lampColor,
        opacity: lampOpacity,
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
      return 0.5 + this.lamp.lightness * 0.005;
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
  margin: 10px;
  min-width: 80px;
}
.lamp-info-wrapper > i {
  font-size: 32px;
}
.lamp-info-wrapper > span {
  font-size: 12px;
}
</style>
