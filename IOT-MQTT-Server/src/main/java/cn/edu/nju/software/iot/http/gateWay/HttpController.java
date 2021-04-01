package cn.edu.nju.software.iot.http.gateWay;

import java.util.List;

import cn.edu.nju.software.iot.shared.netty.client.NettyClient;
import cn.hutool.core.codec.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**  
 * @ClassName: HttpController  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2021年4月1日  
 *  
 */
@RestController
public class HttpController {

    @Autowired
    private NettyClient nettyClient;

    @Autowired
    private MesgCache mesgCache;

    @RequestMapping("/post-data")
    public String postData(@RequestParam String data,
        @RequestParam String deviceId) {

        String dataBase64 = Base64.encode(data);
        nettyClient.sendMesg(deviceId + " " + dataBase64);
        return "Success";
    }

    @RequestMapping("/get-data")
    public List<String> getData(@RequestParam String deviceId) {
        return mesgCache.getData(deviceId);
    }

}
