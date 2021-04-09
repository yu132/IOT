<template>
  <div>
    DataChart
    <DataChartLink
      v-for="{ name, href } in dataChartInfos"
      :key="name"
      :name="name"
      :href="href"
    />
  </div>
</template>

<script>
// 数据图表
import { mapState } from "vuex";
import DataChartLink from "./components/DataChartLink";

export default {
  name: "DataChart",
  components: {
    DataChartLink,
  },
  computed: {
    ...mapState(["dataChartInfos"]),
  },
};
</script>

<style>
</style>
