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
public class ColorSettingCommand implements Command {

    private MockIOTDevice device;

    private int color;

    public ColorSettingCommand(MockIOTDevice device, int color) {
        super();
        this.device = device;
        this.color = color;
    }

    @Override
    public void doCommand() {
        device.setColor(color);
    }

}
