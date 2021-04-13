<template>
  <div class="card" :class="{ 'is-party': isParty }">
    <i class="el-icon-goblet-full top-i" @click="onPartyClick" />
    <span>{{ title }}</span>
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
    ...mapState(["lamps", "partyLampIds", "isParty"]),
    title() {
      return this.isParty ? "Party~~~" : "开始 Party!";
    },
  },
  data() {
    return {
      dialogTitle: "请选择Party相关灯具设备",
      dialogRef: "dialogRef",
    };
  },
  methods: {
    ...mapActions(["updatePartyLampIds", "startParty", "endParty"]),
    onPartyClick() {
      if (this.isParty) {
        this.endParty();
      } else {
        this.startParty();
      }
    },
    onSettingClick() {
      this.$refs[this.dialogRef].showDialog();
    },
  },
};
</script>

<style>
@keyframes rotate {
  from {
    transform: rotate(0);
  }
  to {
    transform: rotate(360deg);
  }
}
.is-party > .top-i {
  animation: rotate 2s linear infinite;
  color: #409eff;
  box-shadow: inset 0 2px 12px 0 rgb(0 0 0 / 20%) !important;
}
</style>
