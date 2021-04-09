<template>
  <div>
    IntelligentScene
    <ReturnHome />
    <Party />
    <LeaveHome />
  </div>
</template>

<script>
// 智慧场景
import LeaveHome from "./components/LeaveHome";
import Party from "./components/Party";
import ReturnHome from "./components/ReturnHome";

export default {
  name: "IntelligentScene",
  components: {
    LeaveHome,
    Party,
    ReturnHome,
  },
};
</script>

<style>
</style>
