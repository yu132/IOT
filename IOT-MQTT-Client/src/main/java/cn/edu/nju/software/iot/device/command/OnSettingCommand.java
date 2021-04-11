package cn.edu.nju.software.iot.device.command;

import cn.edu.nju.software.iot.device.MockIOTDevice;

/**  
 * @ClassName: BrightnessSettingCommand  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2021年3月22日  
 *  
 */
public class OnSettingCommand implements Command {

    private MockIOTDevice device;

    public OnSettingCommand(MockIOTDevice device) {
        super();
        this.device = device;
    }

    @Override
    public void doCommand() {
        device.setOn();
    }

}
