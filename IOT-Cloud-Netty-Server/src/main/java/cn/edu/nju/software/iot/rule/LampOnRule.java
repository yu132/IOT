package cn.edu.nju.software.iot.rule;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "Hello World rule", description = "Always say hello world", priority = 2)
public class LampOnRule {

    @Condition
    public boolean when(@Fact("leaveHomeLampIds") String[] leaveHomeLampIds, @Fact("returnHomeLampIds") String[] returnHomeLampIds, @Fact("partyLampIds") String[] partyLampIds, @Fact("isParty") boolean isParty, @Fact("isReturnHome") boolean isReturnHome, @Fact("isLeaveHome") boolean isLeaveHome) {
    	System.out.println(leaveHomeLampIds);
    	System.out.println(returnHomeLampIds);
    	System.out.println(partyLampIds);
    	System.out.println(isParty);
    	System.out.println(isReturnHome);
    	System.out.println(isLeaveHome);
    	return true;
    }

    @Action
    public void then() throws Exception {
        System.out.println("LampOnRule");
    }
}
