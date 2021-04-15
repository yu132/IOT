package cn.edu.nju.software.iot.rule;

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
    private DeviceService deviceService;
    private boolean inited = false;
	
	private IotRulesEngine() { }
	
	public void init(DeviceService deviceService) {
		if (this.inited) {
			return;
		}
        this.rules = new Rules();
		this.deviceService = deviceService;
		String[] lampIds = this.deviceService.getLamps();
		for (String lampId : lampIds) {			
			rules.register(new LampOnRule(lampId, deviceService));
			rules.register(new LampOffRule(lampId, deviceService));
		}
        this.rulesEngine = new DefaultRulesEngine();
        this.inited = true;
	}
	
	public void fire(Facts facts) {
        rulesEngine.fire(this.rules, facts);
	}
}
