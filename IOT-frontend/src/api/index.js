import { instance } from '../util/request';
import { consts } from '../util/consts';
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
    getIsPartyUrl,
    setIsPartyUrl
} = consts;

const api = {
    /**
     * 打开灯具
     * @param {String} lampId 
     */
    on (lampId) {
        return instance.post(onUrl, { lampId });
    },
    /**
     * 关闭灯具
     * @param {String} lampId 
     */
    off (lampId) {
        return instance.post(offUrl, { lampId });
    },
    /**
     * 调整亮度
     * @param {String} lampId 
     * @param {Number} brightness 1-100
     */
    brightness (lampId, brightness) {
        return instance.post(brightnessUrl, { lampId, brightness });
    },
    /**
     * 调整颜色
     * @param {String} lampId 
     * @param {Number} color 0-2
     */
    color (lampId, color) {
        return instance.post(colorUrl, { lampId, color });
    },
    /**
     * 连接设备
     * TODO 后端暂无该接口
     * @param {String} lampId 
     */
    connect (lampId) {
        console.log(`[INFO] connect lampId: ${ lampId }`);
        return Promise.resolve();
    },
    /**
     * 断开连接
     * @param {String} lampId 
     */
    disconnect (lampId) {
        return instance.post(disconnectUrl, { lampId });
    },
    /**
     * 获取灯泡数据
     * TODO 添加更多灯泡属性
     * color 备选颜色常量
     * 亮度 0-100
     * @returns {Promise<Array<{ name: String, id: String, isOn: Boolean, color: String, brightness: Number, isConnected: Boolean, lastUseTime: Number }>>}
     */
    getLamps () {
        return instance.get(getLampsUrl);
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
     * 获取Party模式开启状态
     * @returns {Promise<Boolean>}
     */
    getIsParty () {
        return instance.get(getIsPartyUrl);
    },
    /**
     * 设置Party模式开启状态
     * @param {Boolean} isParty 
     * @returns {Promise<Boolean>}
     */
    setIsParty (isParty) {
        return instance.post(setIsPartyUrl, { isParty });
    }
};

export { api };
