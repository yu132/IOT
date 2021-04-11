<template>
  <div class="device-manage-wrapper">
    <AddLamp />
    <LampInfo v-for="lamp in lamps" :lamp="lamp" :key="lamp.id" />
    <NoLampPrompt v-if="noLamps" />
  </div>
</template>

<script>
// 设备管理
import LampInfo from "./components/LampInfo";
import NoLampPrompt from "./components/NoLampPrompt";
import AddLamp from "./components/AddLamp";
import { mapState } from "vuex";

export default {
  name: "DeviceManage",
  components: {
    LampInfo,
    NoLampPrompt,
    AddLamp,
  },
  computed: {
    ...mapState(["lamps"]),
    noLamps() {
      return this.lamps === undefined || this.lamps.length === 0;
    },
  },
};
</script>

<style>
.device-manage-wrapper {
  width: 100%;
  height: 100%;
  box-sizing: border-box;
  display: flex;
  flex-wrap: wrap;
}
</style>
