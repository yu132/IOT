<template>
  <div class="card">
    <i class="el-icon-s-home top-i" @click="onReturnHomeClick" />
    <span>归家</span>
    <i class="el-icon-setting setting" @click="onSettingClick" />
    <CheckBoxDialog
      :ref="dialogRef"
      :title="dialogTitle"
      :checkedOptionIds="returnHomeLampIds"
      :options="lamps"
      :beforeCloseCallback="updateReturnHomeLampIds"
    />
  </div>
</template>

<script>
// 智慧场景-归家
import CheckBoxDialog from "./CheckBoxDialog";
import { mapState, mapActions } from "vuex";

export default {
  name: "IntelligentScene_ReturnHome",
  components: {
    CheckBoxDialog,
  },
  computed: {
    ...mapState(["lamps", "returnHomeLampIds"]),
  },
  data() {
    return {
      dialogTitle: "请选择离家相关灯具设备",
      dialogRef: "dialogRef",
    };
  },
  methods: {
    ...mapActions(["updateReturnHomeLampIds", "returnHome"]),
    onReturnHomeClick() {
      this.returnHome();
    },
    onSettingClick() {
      this.$refs[this.dialogRef].showDialog();
    },
  },
};
</script>

<style>
</style>
