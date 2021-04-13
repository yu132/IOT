// Vuex store
import Vuex from 'vuex';
import { api } from '../api';
import { consts } from '../util/consts';
import {
    getRandomColorIndex,
    getRandomBoolean
} from '../util/random';

let partyIntervalId = undefined;

const store = new Vuex.Store({
    state: {
        menuIndex: consts.deviceManageMenuIndex,
        lamps: [],
        leaveHomeLampIds: [],
        returnHomeLampIds: [],
        partyLampIds: [],
        isParty: false
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
        setIsParty (state, isParty) {
            state.isParty = isParty;
        }
    },
    actions: {
        async initStore ({ dispatch }) {
            Promise.all([
                dispatch('initLamps'),
                dispatch('initLeaveHomeLampIds'),
                dispatch('initReturnHomeLampIds'),
                dispatch('initPartyLampIds'),
                dispatch('initIsParty')
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
        async leaveHome ({ state }) {
            const { lamps, leaveHomeLampIds } = state;
            const leaveHomeLamps = lamps.filter(lamp => leaveHomeLampIds.indexOf(lamp.id) >= 0);
            await Promise.all(leaveHomeLamps.map(lamp => api.off(lamp)));
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
        async returnHome ({ state }) {
            const { lamps, returnHomeLampIds } = state;
            const returnHomeLamps = lamps.filter(lamp => returnHomeLampIds.indexOf(lamp.id) >= 0);
            await Promise.all(returnHomeLamps.map(lamp => api.on(lamp)));
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
        async initIsParty ({ commit }) {
            const { data } = await api.getIsParty();
            commit('setIsParty', data);
        },
        async startParty ({ state, commit }) {
            const { lamps, partyLampIds } = state;
            partyIntervalId = setInterval(async () => {
                for (const lamp of lamps)
                {
                    const { color, id } = lamp;
                    if (partyLampIds.indexOf(id) >= 0)
                    {
                        const changeOnOff = getRandomBoolean();
                        if (changeOnOff)
                        {
                            lamp.isOn = !lamp.isOn;
                            if (lamp.isOn)
                            {
                                await api.on(lamp);
                            } else
                            {
                                await api.off(lamp);
                            }
                            const newColor = getRandomColorIndex();
                            if (color !== newColor)
                            {
                                lamp.color = newColor;
                                await api.color(id, newColor);
                            }
                        }
                    }
                }
            }, consts.partyIntervalTimeout);
            const newIsParty = true;
            commit('setIsParty', newIsParty);
            await api.setIsParty(newIsParty);
        },
        async endParty ({ commit }) {
            clearInterval(partyIntervalId);
            const newIsParty = false;
            commit('setIsParty', newIsParty);
            await api.setIsParty(newIsParty);
        }
    }
});

export default store;
