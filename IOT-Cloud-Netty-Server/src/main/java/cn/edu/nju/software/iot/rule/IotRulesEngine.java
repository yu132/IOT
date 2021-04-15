package cn.edu.nju.software.iot.rule;

import javax.annotation.Resource;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;

import cn.edu.nju.software.iot.service.DeviceService;

public class IotRulesEngine {
	private static IotRulesEngine instance = new IotRulesEngine();
	
	public static IotRulesEngine getInstance() {
		return instance;
	}
	
	private Rules rules;
	private RulesEngine rulesEngine;

    @Resource(name = "device-service")
    private DeviceService deviceService;
	
	private IotRulesEngine() {
        this.rules = new Rules();
        this.initRules();

        this.rulesEngine = new DefaultRulesEngine();
	}
	
	private void initRules() {
		String[] lampIds = this.deviceService.getLamps();
		for (String lampId : lampIds) {			
			rules.register(new LampOnRule(lampId));
			rules.register(new LampOffRule(lampId));
		}
	}
	
	public void fire(Facts facts) {
        rulesEngine.fire(this.rules, facts);
	}
}
