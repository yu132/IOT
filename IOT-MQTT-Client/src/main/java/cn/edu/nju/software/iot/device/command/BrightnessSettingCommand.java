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
public class BrightnessSettingCommand implements Command {

    private MockIOTDevice device;

    private int brightness;

    public BrightnessSettingCommand(MockIOTDevice device, int brightness) {
        super();
        this.device = device;
        this.brightness = brightness;
    }

    @Override
    public void doCommand() {
        device.setBrightnessOfLight(brightness);
    }

}
