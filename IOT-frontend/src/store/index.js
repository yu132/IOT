// Vuex store
import Vuex from 'vuex';
import { api } from '../api';
import { consts } from '../util/consts';

const store = new Vuex.Store({
    state: {
        menuIndex: consts.deviceManageMenuIndex,
        lamps: [],
        leaveHomeLampIds: [],
        returnHomeLampIds: [],
        partyLampIds: []
    },
    mutations: {
        setMenuIndex (state, menuIndex) {
            state.menuIndex = menuIndex;
        },
        setLamps (state, lamps = []) {
            state.lamps = [...lamps];
        },
        setLeaveHomeLampIds (state, leaveHomeLampIds = []) {
            state.leaveHomeLampIds = [...leaveHomeLampIds];
        },
        setReturnHomeLampIds (state, returnHomeLampIds = []) {
            state.returnHomeLampIds = [...returnHomeLampIds];
        },
        setPartyLampIds (state, partyLampIds = []) {
            state.partyLampIds = [...partyLampIds];
        },
    },
    actions: {
        async initStore ({ dispatch }) {
            Promise.all([
                dispatch('initLamps'),
                dispatch('initLeaveHomeLampIds'),
                dispatch('initReturnHomeLampIds'),
                dispatch('initPartyLampIds'),
            ]);
        },
        async initLamps ({ commit }) {
            const { data } = await api.getLamps();
            commit('setLamps', data);
        },
        async initLeaveHomeLampIds ({ commit }) {
            const { data } = await api.getLeaveHomeLampIds();
            commit('setLeaveHomeLampIds', data);
        },
        async updateLeaveHomeLampIds ({ commit }, newIds) {
            // TODO 后端请求
            console.log(`updateLeaveHomeLampIds newIds:${ newIds }`);
            commit('setLeaveHomeLampIds', newIds);
        },
        async initReturnHomeLampIds ({ commit }) {
            const { data } = await api.getReturnHomeLampIds();
            commit('setReturnHomeLampIds', data);
        },
        async updateReturnHomeLampIds ({ commit }, newIds) {
            // TODO 后端请求
            console.log(`updateReturnHomeLampIds newIds:${ newIds }`);
            commit('setReturnHomeLampIds', newIds);
        },
        async initPartyLampIds ({ commit }) {
            const { data } = await api.getPartyLampIds();
            commit('setPartyLampIds', data);
        },
        async updatePartyLampIds ({ commit }, newIds) {
            // TODO 后端请求
            console.log(`updatePartyLampIds newIds:${ newIds }`);
            commit('setPartyLampIds', newIds);
        },
    }
});

export default store;
