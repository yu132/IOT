<template>
  <div class="intelligent-scene-wrapper">
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
.intelligent-scene-wrapper {
  width: 100%;
  height: 100%;
  box-sizing: border-box;
  display: flex;
  align-items: center;
  justify-content: space-evenly;
}
.intelligent-scene-wrapper > .card {
  height: 400px;
  width: 25%;
  min-width: 200px;
  border-radius: 10px;
  box-sizing: border-box;
  background: white;
  box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
  margin: 20px;
  border: thin solid #ebeef5;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  font-size: 32px;
  position: relative;
}
.intelligent-scene-wrapper > .card > .top-i {
  border-radius: 50%;
  box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
  font-size: 64px;
  width: 128px;
  height: 128px;
  line-height: 128px;
  text-align: center;
  margin-bottom: 32px;
}
.intelligent-scene-wrapper > .card > .setting {
  position: absolute;
  bottom: 32px;
}
.intelligent-scene-wrapper > .card > .top-i:hover {
  cursor: pointer;
  color: #409eff;
}
.intelligent-scene-wrapper > .card > .top-i:active {
  box-shadow: inset 0 2px 12px 0 rgb(0 0 0 / 10%);
}
.intelligent-scene-wrapper > .card > .setting:hover {
  cursor: pointer;
  color: #40a0ffbd;
}
.intelligent-scene-wrapper > .card > .setting:active {
  cursor: pointer;
  color: #40a0ff;
}
</style>
