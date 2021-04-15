package cn.edu.nju.software.iot.service.impl;

import org.jeasy.rules.api.Facts;
import org.springframework.stereotype.Service;

import cn.edu.nju.software.iot.rule.IotRulesEngine;
import cn.edu.nju.software.iot.service.DeviceService;
import cn.edu.nju.software.iot.service.IntelligentSceneService;

/**
 * @ClassName: IntelligentSceneServiceImpl
 *
 * @author 张李承
 *
 * @date 2021年4月14日
 *
 */
@Service("intelligent-scene-service")
public class IntelligentSceneServiceImpl implements IntelligentSceneService {

    private String[] leaveHomeLampIds = {};
    private String[] returnHomeLampIds = {};
    private String[] partyLampIds = {};
    private boolean isParty = false;
    private boolean isReturnHome = false;
    private boolean isLeaveHome = false;
    
    private Facts createNewFacts() {
    	Facts facts = new Facts();
        facts.put("leaveHomeLampIds", this.leaveHomeLampIds);
        facts.put("returnHomeLampIds", this.returnHomeLampIds);
        facts.put("isReturnHome", this.isReturnHome);
        facts.put("isLeaveHome", this.isLeaveHome);
    	return facts;
    }
    
    private void fireRulesEngine(DeviceService deviceService) {
        IotRulesEngine.getInstance().init(deviceService);
        IotRulesEngine.getInstance().fire(this.createNewFacts());
    }

    @Override
    public String[] getLeaveHomeLampIds() {
        return this.leaveHomeLampIds;
    }

    @Override
    public void setLeaveHomeLampIds(String[] lampIds) {
        this.leaveHomeLampIds = lampIds;
    }

    @Override
    public String[] getReturnHomeLampIds() {
        return this.returnHomeLampIds;
    }
    
    @Override
    public void setReturnHomeLampIds(String[] lampIds) {
        this.returnHomeLampIds = lampIds;
    }

    @Override
    public String[] getPartyLampIds() {
        return this.partyLampIds;
    }

    @Override
    public void setPartyLampIds(String[] lampIds) {
        this.partyLampIds = lampIds;
    }

    @Override
    public void leaveHome(DeviceService deviceService) {
    	this.isLeaveHome = true;
        this.fireRulesEngine(deviceService);
    	this.isLeaveHome = false;
    }

    @Override
    public void returnHome(DeviceService deviceService) {
    	this.isReturnHome = true;
        this.fireRulesEngine(deviceService);
    	this.isReturnHome = false;
    }

    @Override
    public boolean getIsParty() {
        return this.isParty;
    }

    @Override
    public void setIsParty(boolean isParty) {
        this.isParty = isParty;
    }
}
