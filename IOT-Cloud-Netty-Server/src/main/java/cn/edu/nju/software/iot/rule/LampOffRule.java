package cn.edu.nju.software.iot.rule;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import cn.edu.nju.software.iot.service.DeviceService;

@Rule(name = "LampOffRule", description = "离开家的时候要关灯", priority = 2)
public class LampOffRule {
	
	private String lampId;
    private DeviceService deviceService;
	
	public LampOffRule(String lampId, DeviceService deviceService) {
		this.lampId = lampId;
		this.deviceService = deviceService;
	}

    @Condition
    public boolean when(@Fact("leaveHomeLampIds") String[] leaveHomeLampIds, @Fact("isLeaveHome") boolean isLeaveHome) {
    	if (isLeaveHome) {
    		for (String id : leaveHomeLampIds) {
    			if (id.equals(this.lampId)) {
    				return true;
    			}
    		}
    	}
    	return false;
    }

    @Action
    public void then() throws Exception {
        System.out.println("LampOffRule 触发 lampId: " + this.lampId);
        this.deviceService.turnOn(this.lampId);
    }
}
