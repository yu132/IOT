package cn.edu.nju.software.iot.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cn.edu.nju.software.iot.service.IntelligentSceneService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: IntelligentSceneController
 *
 * @Description: 智慧场景相关控制
 *
 * @author 张李承
 *
 * @date 2021年4月14日
 *
 */
@RestController
@RequestMapping()
public class IntelligentSceneController {

    @Resource(name = "intelligent-scene-service")
    private IntelligentSceneService intelligentSceneService;

    @RequestMapping(value = "getLeaveHomeLampIds")
    public String[] getLeaveHomeLampIds(HttpServletRequest req) {
        return intelligentSceneService.getLeaveHomeLampIds();
    }

    @RequestMapping(value = "setLeaveHomeLampIds")
    public void setLeaveHomeLampIds(HttpServletRequest req) {
        String[] lampIds = req.getParameterValues("lampIds");
        intelligentSceneService.setLeaveHomeLampIds(lampIds);
    }

    @RequestMapping(value = "getReturnHomeLampIds")
    public String[] getReturnHomeLampIds(HttpServletRequest req) {
        return intelligentSceneService.getReturnHomeLampIds();
    }

    @RequestMapping(value = "setReturnHomeLampIds")
    public void setReturnHomeLampIds(HttpServletRequest req) {
        String[] lampIds = req.getParameterValues("lampIds");
        intelligentSceneService.setReturnHomeLampIds(lampIds);
    }

    @RequestMapping(value = "getPartyLampIds")
    public String[] getPartyLampIds(HttpServletRequest req) {
        return intelligentSceneService.getPartyLampIds();
    }

    @RequestMapping(value = "setPartyLampIds")
    public void setPartyLampIds(HttpServletRequest req) {
        String[] lampIds = req.getParameterValues("lampIds");
        intelligentSceneService.setPartyLampIds(lampIds);
    }

    @RequestMapping(value = "leaveHome")
    public void leaveHome(HttpServletRequest req) {
        intelligentSceneService.leaveHome();
    }

    @RequestMapping(value = "returnHome")
    public void returnHome(HttpServletRequest req) {
        intelligentSceneService.returnHome();
    }

    @RequestMapping(value = "getIsParty")
    public boolean getIsParty(HttpServletRequest req) {
        return intelligentSceneService.getIsParty();
    }

    @RequestMapping(value = "setIsParty")
    public void setIsParty(HttpServletRequest req) {
        boolean isParty = Boolean.valueOf(req.getParameter("isParty"));
        intelligentSceneService.setIsParty(isParty);
    }
}
