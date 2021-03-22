package cn.edu.nju.software.iot.device.command;

import cn.edu.nju.software.iot.device.MockIOTDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**  
 * @ClassName: CommandMesgParser  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2021年3月22日  
 *  
 */

@Component
public class CommandMesgParser {

    @Autowired
    private MockIOTDevice device;

    public Command parseCommand(String commandMesgFromCloud) {

        if (commandMesgFromCloud == null || commandMesgFromCloud.equals("")) {
            return Command.EMPTY_COMMAND;
        }

        String[] sp = commandMesgFromCloud.split(" ");

        switch (sp[0]) {
            case "brightness":
                try {
                    int brightness = Integer.valueOf(sp[1]);
                    return new BrightnessSettingCommand(device, brightness);
                } catch (Exception e) {
                    return Command.EMPTY_COMMAND;
                }
            default:
                return Command.EMPTY_COMMAND;
        }
    }

}
