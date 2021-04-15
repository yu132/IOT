package cn.edu.nju.software.iot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cn.edu.nju.software.iot.service.DeviceService;
import cn.edu.nju.software.iot.shared.netty.server.NettyServer;
import cn.hutool.core.codec.Base64;

/**
 * @ClassName: RouterService
 *
 * @Description: TODO(这里用一句话描述这个类的作用)
 *
 * @author 余定邦
 *
 * @date 2020年12月24日
 *
 */

@Service("device-service")
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private NettyServer server;
    
//    @Autowired
//    private RestTemplate restTemplate;
    
    private String url = "http://123.206.230.74:8080/getInfo";

    public void writeMesg(String clientId, String deviceId, String mesg) {
        String data = Base64.encode(mesg);
        server.writeMesg(clientId, deviceId + " " + data);
    }

    @Override
    public String turnOn(String id) {
        writeMesg("testID1", "device-1", "on");
//        restTemplate.postForObject(url, null, String.class, "device-1",
//        		id + "_1_" + (int) (Math.random() * 100) + "_1");
        return "true";
    }

    @Override
    public String turnOff(String id) {
        writeMesg("testID1", "device-1", "off");
//        restTemplate.postForObject(url, null, String.class, "device-1",
//        		id + "_0_" + (int) (Math.random() * 100) + "_0");
        return "true";
    }

    @Override
    public String changeBrightness(String id, int brightness) {
        writeMesg("testID1", "device-1", "brightness " + brightness);
        return "true";
    }

    @Override
    public String changeColor(String id, int color) {
        writeMesg("testID1", "device-1", "color " + color);
        return "true";
    }

    @Override
    public String disconnect(String id) {
        writeMesg("testID1", "device-1", "disconnect");
        return "true";
    }

    @Override
    public String[] getLamps() {
    	return new String[] { "device-1", "device-2", "device-3", "device-4" };
    }
}