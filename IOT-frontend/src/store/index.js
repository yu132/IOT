// Vuex store
import Vuex from 'vuex';
import { api } from '../api';

const store = new Vuex.Store({
    state: {
        title: 'IOT 设备管理',
        lamps: [],
        dataChartInfos: []
    },
    mutations: {
        setTitle (state, title) {
            state.title = title;
        },
        setLamps (state, lamps = []) {
            state.lamps = [...lamps];
        },
        setDataChartInfos (state, dataChartInfos = []) {
            state.dataChartInfos = [...dataChartInfos];
        }
    },
    actions: {
        async initStore ({ dispatch }) {
            Promise.all([
                dispatch('initLamps'),
                dispatch('initDataChartInfos')
            ]);
        },
        async initLamps ({ commit }) {
            const { data } = await api.getLamps();
            commit('setLamps', data);
        },
        async initDataChartInfos ({ commit }) {
            const { data } = await api.getDataChartInfo();
            commit('setDataChartInfos', data);
        }
    }
});

export default store;
