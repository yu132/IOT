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
  margin-top: 64px;
}
.intelligent-scene-wrapper > .card > span {
  font-size: 28px;
  height: 42px;
  line-height: 42px;
  margin: 32px 0;
  width: 150px;
  text-align: center;
}
.intelligent-scene-wrapper > .card > .setting {
  font-size: 32px;
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
@media all and (max-width: 768px) {
  .intelligent-scene-wrapper {
    flex-direction: column;
  }
  .intelligent-scene-wrapper > .card {
    min-width: 400px;
    flex-direction: row;
    justify-content: space-evenly;
  }
  .intelligent-scene-wrapper > .card > .top-i {
    margin-top: 0;
  }
}
</style>
