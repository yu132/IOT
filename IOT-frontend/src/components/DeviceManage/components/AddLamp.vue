<template>
  <div class="add-lamp-wrapper card" @click="onAddLampClick">
    <i class="el-icon-plus" />
  </div>
</template>

<script>
// 设备管理-添加灯泡设备
export default {
  name: "DeviceManage_AddLamp",
  props: {
    lamp: {
      type: Object,
      required: true,
    },
  },
  methods: {
    onAddLampClick() {
      // TODO
      console.log("onAddLampClick");
    },
  },
};
</script>

<style>
.add-lamp-wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 40px;
  cursor: pointer;
}
</style>
