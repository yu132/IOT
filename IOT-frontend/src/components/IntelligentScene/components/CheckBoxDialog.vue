<template>
  <el-dialog
    class="check-box-dialog"
    :title="title"
    :visible.sync="visible"
    :before-close="onBeforeClose"
  >
    <el-checkbox
      :indeterminate="isIndeterminate"
      v-model="checkAll"
      @change="onCheckAllChange"
    >
      全选
    </el-checkbox>
    <el-checkbox-group v-model="checkedOptions" @change="updateCheckAll">
      <el-checkbox v-for="option in options" :label="option" :key="option.id">
        {{ option.name }}
      </el-checkbox>
    </el-checkbox-group>
  </el-dialog>
</template>

<script>
// 智慧场景-多选弹窗
export default {
  name: "IntelligentScene_CheckBoxDialog",
  props: {
    title: {
      type: String,
      required: true,
    },
    checkedOptionIds: {
      type: Array,
      required: true,
    },
    options: {
      type: Array,
      required: true,
    },
    beforeCloseCallback: {
      type: Function,
      required: true,
    },
  },
  data() {
    return {
      visible: false,
      checkAll: false,
      checkedOptions: [],
      isIndeterminate: true,
    };
  },
  methods: {
    onCheckAllChange(isCheckAll) {
      this.checkedOptions = isCheckAll ? this.options : [];
      this.isIndeterminate = false;
    },
    updateCheckedOptions() {
      const checkedOptions = [];
      for (const option of this.options) {
        if (this.checkedOptionIds.indexOf(option.id) >= 0) {
          checkedOptions.push(option);
        }
      }
      this.checkedOptions = checkedOptions;
    },
    updateCheckAll() {
      const checkedCount = this.checkedOptions.length;
      this.checkAll = checkedCount === this.options.length;
      this.isIndeterminate =
        checkedCount > 0 && checkedCount < this.options.length;
    },
    showDialog() {
      this.updateCheckedOptions();
      this.updateCheckAll();
      this.visible = true;
    },
    async onBeforeClose(closeDialog) {
      const newIds = this.checkedOptions.map((option) => option.id);
      await this.beforeCloseCallback(newIds);
      closeDialog();
    },
  },
};
</script>

<style>
.check-box-dialog .el-dialog__header {
  display: flex;
}
.check-box-dialog .el-dialog__body {
  padding: 20px 0;
}
.check-box-dialog .el-checkbox {
  margin: 10px 30px;
}
</style>
