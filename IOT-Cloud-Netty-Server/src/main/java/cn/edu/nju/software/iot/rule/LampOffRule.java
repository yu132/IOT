package cn.edu.nju.software.iot.rule;

import javax.annotation.Resource;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import cn.edu.nju.software.iot.service.DeviceService;

@Rule(name = "LampOffRule", description = "离开家的时候要关灯", priority = 2)
public class LampOffRule {
	
	private String lampId;
	
	public LampOffRule(String lampId) {
		this.lampId = lampId;
	}

    @Resource(name = "device-service")
    private DeviceService deviceService;

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
