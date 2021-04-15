package cn.edu.nju.software.iot.rule;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;

public class IotRulesEngine {
	private static IotRulesEngine instance = new IotRulesEngine();
	
	public static IotRulesEngine getInstance() {
		return instance;
	}
	
	private Rules rules;
	private RulesEngine rulesEngine;
	
	private IotRulesEngine() {
        this.rules = new Rules();
        this.initRules();

        this.rulesEngine = new DefaultRulesEngine();
	}
	
	private void initRules() {
        rules.register(new HelloWorldRule());
        rules.register(new LampOnRule());
	}
	
	public void fire(Facts facts) {
        rulesEngine.fire(this.rules, facts);
	}
}
