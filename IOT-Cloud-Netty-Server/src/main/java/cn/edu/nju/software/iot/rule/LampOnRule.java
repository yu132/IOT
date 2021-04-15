package cn.edu.nju.software.iot.rule;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import cn.edu.nju.software.iot.service.DeviceService;

@Rule(name = "LampOnRule", description = "回家的时候要开灯", priority = 1)
public class LampOnRule {
	
	private String lampId;
    private DeviceService deviceService;
	
	public LampOnRule(String lampId, DeviceService deviceService) {
		this.lampId = lampId;
		this.deviceService = deviceService;
	}

    @Condition
    public boolean when(@Fact("leaveHomeLampIds") String[] leaveHomeLampIds, @Fact("returnHomeLampIds") String[] returnHomeLampIds, @Fact("isReturnHome") boolean isReturnHome, @Fact("isLeaveHome") boolean isLeaveHome) {
    	if (isReturnHome) {
    		for (String id : returnHomeLampIds) {
    			if (id.equals(this.lampId)) {
    				return true;
    			}
    		}
    	}
    	return false;
    }

    @Action
    public void then() throws Exception {
        System.out.println("LampOnRule 触发 lampId: " + this.lampId);
        this.deviceService.turnOn(this.lampId);
    }
}
