import { instance } from '../util/request';
import { consts } from '../util/consts';
const {
    getLampsUrl,
    getDataChartInfoUrl
} = consts;

const api = {
    /**
     * 获取灯泡数据
     * TODO 添加更多灯泡属性
     * @returns {Promise<Array<{ name: String, id: String, isOn: Boolean }>>}
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
