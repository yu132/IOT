package cn.edu.nju.software.iot.service.impl;

import org.springframework.stereotype.Service;

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
    public void leaveHome() {
        // TODO 触发规则引擎
    }

    @Override
    public void returnHome() {
        // TODO 触发规则引擎
    }

    @Override
    public boolean getIsParty() {
        return this.isParty;
    }

    @Override
    public void setIsParty(boolean isParty) {
        this.isParty = isParty;
        // TODO 触发规则引擎
    }
}
