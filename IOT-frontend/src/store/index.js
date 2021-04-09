// Vuex store
import Vuex from 'vuex';
import api from '../api';

const store = new Vuex.Store({
    state: {
        title: 'IOT 设备管理',
        lamps: []
    },
    mutations: {
        setTitle (state, title) {
            state.title = title;
        },
        setLamps (state, lamps = []) {
            state.lamps = [...lamps];
        }
    },
    actions: {
        async initLamps ({ commit }) {
            const { data } = await api.getLamps();
            commit('setLamps', data);
        }
    }
});

export default store;
