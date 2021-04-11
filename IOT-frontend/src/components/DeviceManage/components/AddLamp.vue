<template>
  <div class="add-lamp-wrapper" @click="onAddLampClick">
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
      require: true,
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
  width: 140px;
  height: 200px;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 1px dashed #ebeef5;
  font-size: 40px;
  border-radius: 10px;
  box-sizing: border-box;
  margin: 20px;
  background: white;
  box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
  cursor: pointer;
}
.add-lamp-wrapper:hover {
  box-shadow: 0 2px 12px 0 rgb(0 0 0 / 20%);
}
</style>
