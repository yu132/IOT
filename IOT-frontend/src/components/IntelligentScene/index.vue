<template>
  <div>
    IntelligentScene
    <ReturnHome />
    <Party />
    <LeaveHome />
    <LampInfoFooter />
  </div>
</template>

<script>
// 智慧场景
import LeaveHome from "./components/LeaveHome";
import Party from "./components/Party";
import ReturnHome from "./components/ReturnHome";
import LampInfoFooter from "./components/LampInfoFooter";

export default {
  name: "IntelligentScene",
  components: {
    LeaveHome,
    Party,
    ReturnHome,
    LampInfoFooter,
  },
};
</script>

<style>
</style>
