import Vue from 'vue';
// TODO 按需引用
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue';
import Vuex from 'vuex';
// TODO 修改 title

Vue.use(Vuex);
Vue.use(ElementUI);

Vue.config.productionTip = false;

new Vue({
  store: require('./store').default,
  render: h => h(App),
}).$mount('#app');
