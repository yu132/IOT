package cn.edu.nju.software.iot.service.impl;

import cn.edu.nju.software.iot.cloud.netty.server.MessageBuffer;
import cn.edu.nju.software.iot.service.DeviceService;
import cn.edu.nju.software.iot.shared.netty.server.NettyServer;
import cn.hutool.core.codec.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    public void writeMesg(String clientId, String deviceId, String mesg) {
        String data = Base64.encode(mesg);
        server.writeMesg(clientId, deviceId + " " + data);
    }

    @Override
    public String turnOn(String id) {
        MessageBuffer.write(id + " 1 " + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        writeMesg("testID1", "device-1", "on");
        return "true";
    }

    @Override
    public String turnOff(String id) {
        MessageBuffer.write(id + " 0 " + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        writeMesg("testID1", "device-1", "off");
        return "true";
    }

    @Override
    public String changeBrightness(String id, int brightness) {
//        MessageBuffer.write(id + " " + 1 + " " + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        writeMesg("testID1", "device-1", "brightness " + brightness);
        return "true";
    }

    @Override
    public String changeColor(String id, int color) {
//        MessageBuffer.write(id + " " + 1 + " " + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        writeMesg("testID1", "device-1", "color " + color);
        return "true";
    }

    @Override
    public String disconnect(String id) {
//        MessageBuffer.write(id + " " + 1 + " " + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        writeMesg("testID1", "device-1", "disconnect");
        return "true";
    }

}