<template>
  <div>
    DeviceManage
    <div v-if="hasLamps">
      <LampInfo v-for="lamp in lamps"  :lamp="lamp" :key="lamp.id" />
    </div>
    <NoLampPrompt v-else />
    <AddLamp />
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
    hasLamps() {
      return this.lamps && this.lamps.length;
    },
  },
};
</script>

<style>
</style>
