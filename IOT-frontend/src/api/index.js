import { instance } from '../util/request';
import {
    getLampsUrl,
    getDataChartInfoUrl
} from '../util/consts';

const api = {
    /**
     * 获取灯泡数据
     * @returns {Promise<Array>}
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

export default api;
