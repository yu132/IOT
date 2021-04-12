package cn.edu.nju.software.iot.mqtt.gateWay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**  
 * @ClassName: GateWayMesgHandler  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2021年3月22日  
 *  
 */

@Component
public class GateWayMesgHandler {
    @Autowired
    private IOTDeviceAuth iOTDeviceAuth;

    public void handleMesgFromCloud(String mesg) {
        // 处理从云端对网关控制的逻辑
        String[] sp = mesg.split(" ");
        switch (sp[0]) {
            case "add":
                iOTDeviceAuth.addDeviceId(sp[1]);
                break;
            case "remove":
                iOTDeviceAuth.removeDeviceId(sp[1]);
                break;
            default:
                System.out.println("未定义的命令" + sp[0]);
        }
    }

}
