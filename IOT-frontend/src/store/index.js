// Vuex store
import Vuex from 'vuex';
import { api } from '../api';
import { consts } from '../util/consts';

const store = new Vuex.Store({
    state: {
        menuIndex: consts.deviceManageMenuIndex,
        lamps: [],
        dataChartInfos: []
    },
    mutations: {
        setMenuIndex (state, menuIndex) {
            state.menuIndex = menuIndex;
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
