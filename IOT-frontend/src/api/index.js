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
    getReturnHomeLampIdsUrl
} = consts;

const api = {
    /**
     * 打开灯具
     * @param {*} lampId 
     */
    on (lampId) {
        return instance.post(onUrl, { lampId });
    },
    /**
     * 关闭灯具
     * @param {*} lampId 
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
     * @param {*} lampId 
     */
    connect (lampId) {
        console.log(`[INFO] connect lampId: ${ lampId }`);
        return Promise.resolve();
    },
    /**
     * 断开连接
     * @param {*} lampId 
     */
    disconnect (lampId) {
        return instance.post(disconnectUrl, { lampId });
    },
    /**
     * 获取灯泡数据
     * TODO 添加更多灯泡属性
     * color 备选颜色常量
     * 亮度 0-100
     * @returns {Promise<Array<{ name: String, id: String, isOn: Boolean, color: String, lightness: Number, isConnected: Boolean, lastUseTime: Number }>>}
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
    }
};

export { api };
