package cn.edu.nju.software.iot.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.nju.software.iot.service.DeviceService;

/**
 * @ClassName: DeviceController
 *
 * @Description: TODO(这里用一句话描述这个类的作用)
 *
 * @author 王子硕
 *
 * @date 2021年4月10日
 *
 */

@RestController
@RequestMapping()
@CrossOrigin("*")
public class DeviceController {

    @Resource(name = "device-service")
    private DeviceService deviceService;

    @RequestMapping(value = "on")
    public String turnOn(HttpServletRequest req) {
        String id = req.getParameter("id");
        return deviceService.turnOn(id);
    }

    @RequestMapping(value = "off")
    public String turnOff(HttpServletRequest req) {
        String id = req.getParameter("id");
        return deviceService.turnOff(id);
    }

    @RequestMapping(value = "brightness")
    public String changeBrightness(HttpServletRequest req) {
        String id = req.getParameter("id");
        String brightness = req.getParameter("brightness");
        return deviceService.changeBrightness(id, Integer.parseInt(brightness));
    }

    @RequestMapping(value = "color")
    public String changeColor(HttpServletRequest req) {
        String id = req.getParameter("id");
        String color = req.getParameter("color");
        return deviceService.changeColor(id, Integer.parseInt(color));
    }

    @RequestMapping(value = "disconnect")
    public String disconnect(HttpServletRequest req) {
        String id = req.getParameter("id");
        return deviceService.disconnect(id);
    }
    
    @RequestMapping(value = "getLamps")
    public String[] getLamps(HttpServletRequest req) {
        return deviceService.getLamps();
    }
}
