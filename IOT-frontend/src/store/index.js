// Vuex store

import Vuex from 'vuex';

const store = new Vuex.Store({
    state: {
        title: 'IOT 设备管理'
    },
    mutations: {
        setTitle (state, title) {
            state.title = title;
        }
    }
});

export default store;
