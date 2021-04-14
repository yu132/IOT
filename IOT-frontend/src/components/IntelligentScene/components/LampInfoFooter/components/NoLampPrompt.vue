<template>
  <div class="no-lamp-prompt-wrapper">
    <span>
      未链接设备，请前往
      <span class="clickable-span" @click="onDeviceManageSpanClick">
        设备管理
      </span>
      页面添加设备
    </span>
  </div>
</template>

<script>
// 智慧场景-底部灯具信息组件-无灯泡设备提示
import { mapMutations } from "vuex";
import { consts } from "../../../../../util/consts";

export default {
  name: "IntelligentScene_LampInfoFooter_NoLampPrompt",
  methods: {
    ...mapMutations(["setMenuIndex"]),
    onDeviceManageSpanClick() {
      this.setMenuIndex(consts.deviceManageMenuIndex);
    },
  },
};
</script>

<style scoped>
.no-lamp-prompt-wrapper {
  width: 100%;
  height: 100%;
  box-sizing: border-box;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>
