<template>
  <div class="card">
    <i class="el-icon-goblet-full top-i" @click="onPartyClick" />
    <span>Party!</span>
    <i class="el-icon-setting setting" @click="onSettingClick" />
    <CheckBoxDialog
      :ref="dialogRef"
      :title="dialogTitle"
      :checkedOptionIds="partyLampIds"
      :options="lamps"
      :beforeCloseCallback="updatePartyLampIds"
    />
  </div>
</template>

<script>
// 智慧场景-派对模式
import CheckBoxDialog from "./CheckBoxDialog";
import { mapState, mapActions } from "vuex";

export default {
  name: "IntelligentScene_Party",
  components: {
    CheckBoxDialog,
  },
  computed: {
    ...mapState(["lamps", "partyLampIds"]),
  },
  data() {
    return {
      dialogTitle: "请选择Party相关灯具设备",
      dialogRef: "dialogRef",
    };
  },
  methods: {
    ...mapActions(["updatePartyLampIds"]),
    onPartyClick() {
      // TODO
    },
    onSettingClick() {
      this.$refs[this.dialogRef].showDialog();
    },
  },
};
</script>

<style>
</style>
