import { instance } from '../util/request';
import { consts } from '../util/consts';
const {
    onUrl,
    offUrl,
    brightnessUrl,
    colorUrl,
    disconnectUrl,
    getLampsUrl,
    getDataChartInfoUrl
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
     * 获取数据图表信息
     * @returns {Promise<Array<{ name: String, href: String }>>}
     */
    getDataChartInfo () {
        return instance.get(getDataChartInfoUrl);
    }
};

export { api };
