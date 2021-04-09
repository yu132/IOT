import { instance } from '../util/request';
import { getLampsUrl } from '../util/consts';

const api = {
    /**
     * 获取灯泡数据
     * @returns {Promise<Array>}
     */
    getLamps () {
        return instance.get(getLampsUrl);
    }
};

export default api;
