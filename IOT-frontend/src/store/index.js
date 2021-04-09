// Vuex store

import Vuex from 'vuex';

const store = new Vuex.Store({
    state: {
        title: 'IOT 设备管理',
        lamps: []
    },
    mutations: {
        setTitle (state, title) {
            state.title = title;
        },
        setLamps (state, lamps) {
            state.lamps = [...lamps];
        }
    },
    actions: {
        async initLamps ({ commit }) {
            // TODO 获取灯泡数据
            commit('setLamps', []);
        }
    }
});

export default store;
