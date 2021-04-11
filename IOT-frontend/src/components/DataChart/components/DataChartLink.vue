<template>
  <div>
    <a :href="href">
      {{ name }}
    </a>
  </div>
</template>

<script>
// 数据图表-数据图表超链接
export default {
  name: "DataChart-DataChartLink",
  props: {
    href: {
      type: String,
      required: true,
    },
    name: {
      type: String,
      required: true,
    },
  },
};
</script>

<style>
</style>
