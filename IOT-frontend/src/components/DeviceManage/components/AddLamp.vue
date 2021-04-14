<template>
  <div class="add-lamp-wrapper card" @click="onAddLampClick">
    <i class="el-icon-plus" />
  </div>
</template>

<script>
// 设备管理-添加灯泡设备
export default {
  name: "DeviceManage_AddLamp",
  methods: {
    onAddLampClick() {
      // TODO
      this.$message("这个还没有做完");
      console.log("onAddLampClick");
    },
  },
};
</script>

<style>
.add-lamp-wrapper {
  width: 180px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 40px;
  cursor: pointer;
}
</style>
