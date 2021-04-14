<template>
  <div class="card">
    <i class="el-icon-house top-i" @click="onLeaveHomeClick" />
    <span>离家</span>
    <i class="el-icon-setting setting" @click="onSettingClick" />
    <CheckBoxDialog
      :ref="dialogRef"
      :title="dialogTitle"
      :checkedOptionIds="leaveHomeLampIds"
      :options="lamps"
      :beforeCloseCallback="updateLeaveHomeLampIds"
    />
  </div>
</template>

<script>
// 智慧场景-离家
import CheckBoxDialog from "./CheckBoxDialog";
import { mapState, mapActions } from "vuex";

export default {
  name: "IntelligentScene_LeaveHome",
  components: {
    CheckBoxDialog,
  },
  computed: {
    ...mapState(["lamps", "leaveHomeLampIds"]),
  },
  data() {
    return {
      dialogTitle: "请选择离家相关灯具设备",
      dialogRef: "dialogRef",
    };
  },
  methods: {
    ...mapActions(["updateLeaveHomeLampIds", "leaveHome"]),
    onLeaveHomeClick() {
      this.leaveHome();
    },
    onSettingClick() {
      this.$refs[this.dialogRef].showDialog();
    },
  },
};
</script>

<style>
</style>
