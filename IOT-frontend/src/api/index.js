import { instance } from '../util/request';
import { consts } from '../util/consts';
import {
    resetData,
    setLampsData,
    getLampsData,
    getLeaveHomeLampIdsData,
    getPartyLampIdsData,
    getReturnHomeLampIdsData,
    setLeaveHomeLampIdsData,
    setPartyLampIdsData,
    setReturnHomeLampIdsData,
    setIsPartyData,
    getIsPartyData
} from '../util/storage';

const {
    onUrl,
    offUrl,
    brightnessUrl,
    colorUrl,
    disconnectUrl,
    getLampsUrl,
    getLeaveHomeLampIdsUrl,
    getReturnHomeLampIdsUrl,
    getPartyLampIdsUrl,
    setLeaveHomeLampIdsUrl,
    setReturnHomeLampIdsUrl,
    setPartyLampIdsUrl,
    leaveHomeUrl,
    returnHomeUrl,
    getIsPartyUrl,
    setIsPartyUrl,
    useMock
} = consts;

let api = {
    /**
     * 打开灯具
     * @param {{ name: String, id: String, isOn: Boolean, color: String, brightness: Number, isConnected: Boolean, lastUseTime: Number }} lamp 
     * @returns {Promise<void>}
     */
    on (lamp) {
        lamp.isOn = true;
        lamp.lastUseTime = Date.now();
        return instance.post(onUrl, `id=${ lamp.id }`);
    },
    /**
     * 关闭灯具
     * @param {{ name: String, id: String, isOn: Boolean, color: String, brightness: Number, isConnected: Boolean, lastUseTime: Number }} lamp 
     * @returns {Promise<void>}
     */
    off (lamp) {
        lamp.isOn = false;
        lamp.lastUseTime = Date.now();
        return instance.post(offUrl, `id=${ lamp.id }`);
    },
    /**
     * 调整亮度
     * @param {String} lampId 
     * @param {Number} brightness 1-100
     * @returns {Promise<void>}
     */
    brightness (lampId, brightness) {
        return instance.post(brightnessUrl, `id=${ lampId }&brightness=${ brightness }`);
    },
    /**
     * 调整颜色
     * @param {String} lampId 
     * @param {Number} color 0-2
     * @returns {Promise<void>}
     */
    color (lampId, color) {
        return instance.post(colorUrl, `id=${ lampId }&color=${ color }`);
    },
    /**
     * 连接设备
     * TODO 后端暂无该接口
     * @param {String} lampId 
     * @returns {Promise<void>}
     */
    connect (lampId) {
        console.log(`[INFO] connect lampId: ${ lampId }`);
        return Promise.resolve();
    },
    /**
     * 断开连接
     * @param {String} lampId 
     * @returns {Promise<void>}
     */
    disconnect (lampId) {
        return instance.post(disconnectUrl, `id=${ lampId }`);
    },
    /**
     * 获取灯泡数据
     * TODO 确认颜色为 下标 还是 颜色值
     * color 为备选颜色常量
     * 亮度 0-100
     * @returns {Promise<Array<{ name: String, id: String, isOn: Boolean, color: String, brightness: Number, isConnected: Boolean, lastUseTime: Number }>>}
     */
    async getLamps () {
        // 数据格式适配
        const result = await instance.get(getLampsUrl);
        const { data } = result;
        if (data)
        {
            data.forEach(lamp => {
                if (lamp.isConnected === undefined)
                {
                    lamp.isConnected = !!lamp.connected;
                }
                if (lamp.name === undefined)
                {
                    lamp.name = '灯';
                }
                if (lamp.id === undefined)
                {
                    lamp.id = `HT${ Math.random().toFixed(8).substr(2) }`;
                }
                if (lamp.lastUseTime === undefined)
                {
                    const oneDay = 24 * 60 * 60 * 1000;
                    lamp.lastUseTime = Date.now() - Math.random() * 3 * oneDay;
                }
            });
        }
        return Promise.resolve(result);
    },
    /**
     * 获取离家相关灯具ID列表
     * @returns {Promise<Array<String>>}
     */
    getLeaveHomeLampIds () {
        return instance.get(getLeaveHomeLampIdsUrl);
    },
    /**
     * 获取归家相关灯具ID列表
     * @returns {Promise<Array<String>>}
     */
    getReturnHomeLampIds () {
        return instance.get(getReturnHomeLampIdsUrl);
    },
    /**
     * 获取Party相关灯具ID列表
     * @returns {Promise<Array<String>>}
     */
    getPartyLampIds () {
        return instance.get(getPartyLampIdsUrl);
    },
    /**
     * 设置离家相关灯具ID列表
     * @param {Array<String>} lampIds 
     * @returns {Promise<void>}
     */
    setLeaveHomeLampIds (lampIds) {
        return instance.post(setLeaveHomeLampIdsUrl, `lampIds=${ lampIds }`);
    },
    /**
     * 设置归家相关灯具ID列表
     * @param {Array<String>} lampIds 
     * @returns {Promise<void>}
     */
    setReturnHomeLampIds (lampIds) {
        return instance.post(setReturnHomeLampIdsUrl, `lampIds=${ lampIds }`);
    },
    /**
     * 设置Party相关灯具ID列表
     * @param {Array<String>} lampIds 
     * @returns {Promise<void>}
     */
    setPartyLampIds (lampIds) {
        return instance.post(setPartyLampIdsUrl, `lampIds=${ lampIds }`);
    },
    /**
     * 离家
     * @returns {Promise<void>}
     */
    leaveHome () {
        return instance.get(leaveHomeUrl);
    },
    /**
     * 回家
     * @returns {Promise<void>}
     */
    returnHome () {
        return instance.get(returnHomeUrl);
    },
    /**
     * 获取Party模式开启状态
     * @returns {Promise<Boolean>}
     */
    getIsParty () {
        return instance.get(getIsPartyUrl);
    },
    /**
     * 设置Party模式开启状态
     * @param {Boolean} isParty 
     * @returns {Promise<void>}
     */
    setIsParty (isParty) {
        return instance.post(setIsPartyUrl, `isParty=${ isParty }`);
    }
};

function updateLampTemplate (lampId, updateLampCallback) {
    const lamps = getLampsData();
    for (const lamp of lamps)
    {
        if (lamp.id === lampId)
        {
            updateLampCallback(lamp);
        }
    }
    setLampsData(lamps);
}

const mockApi = {
    on (lamp) {
        const now = Date.now();
        lamp.isOn = true;
        lamp.lastUseTime = now;
        updateLampTemplate(lamp.id, l => {
            l.isOn = true;
            l.lastUseTime = now;
        });
        return Promise.resolve();
    },
    off (lamp) {
        const now = Date.now();
        lamp.isOn = false;
        lamp.lastUseTime = now;
        updateLampTemplate(lamp.id, l => {
            l.isOn = false;
            l.lastUseTime = now;
        });
        return Promise.resolve();
    },
    brightness (lampId, brightness) {
        updateLampTemplate(lampId, lamp => {
            lamp.brightness = brightness;
        });
        return Promise.resolve();
    },
    color (lampId, color) {
        updateLampTemplate(lampId, lamp => {
            lamp.color = color;
        });
        return Promise.resolve();
    },
    connect (lampId) {
        updateLampTemplate(lampId, lamp => {
            lamp.isConnected = true;
        });
        return Promise.resolve();
    },
    disconnect (lampId) {
        updateLampTemplate(lampId, lamp => {
            lamp.isConnected = false;
        });
        return Promise.resolve();
    },
    getLamps () {
        const data = getLampsData();
        return Promise.resolve({ data });
    },
    getLeaveHomeLampIds () {
        const data = getLeaveHomeLampIdsData();
        return Promise.resolve({ data });
    },
    getReturnHomeLampIds () {
        const data = getReturnHomeLampIdsData();
        return Promise.resolve({ data });
    },
    getPartyLampIds () {
        const data = getPartyLampIdsData();
        return Promise.resolve({ data });
    },
    setLeaveHomeLampIds (lampIds) {
        setLeaveHomeLampIdsData(lampIds);
        return Promise.resolve();
    },
    setReturnHomeLampIds (lampIds) {
        setReturnHomeLampIdsData(lampIds);
        return Promise.resolve();
    },
    setPartyLampIds (lampIds) {
        setPartyLampIdsData(lampIds);
        return Promise.resolve();
    },
    leaveHome () {
        return Promise.resolve();
    },
    returnHome () {
        return Promise.resolve();
    },
    getIsParty () {
        const data = getIsPartyData();
        return Promise.resolve({ data });
    },
    setIsParty (isParty) {
        setIsPartyData(isParty);
        return Promise.resolve();
    }
};

resetData();

if (useMock)
{
    api = mockApi;
}

export { api };
