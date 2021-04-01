package cn.edu.nju.software.iot.http.gateWay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

/**  
 * @ClassName: MesgCache  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2021年4月1日  
 *  
 */
@Component
public class MesgCache {

    private Map<String, List<String>> datas = new HashMap<>();

    public synchronized void cacheData(String deviceId, String data) {
        datas.computeIfAbsent(deviceId, (x) -> new ArrayList<>()).add(data);
    }

    public synchronized List<String> getData(String deviceId) {
        List<String> list = datas.remove(deviceId);
        if (list == null) {
            return Collections.emptyList();
        }
        return list;
    }

}
