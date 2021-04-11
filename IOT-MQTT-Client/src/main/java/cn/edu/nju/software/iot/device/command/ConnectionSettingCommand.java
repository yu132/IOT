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
public class ConnectionSettingCommand implements Command {

    private MockIOTDevice device;

    private boolean connected;

    public ConnectionSettingCommand(MockIOTDevice device, boolean connect) {
        super();
        this.device = device;
        connected = connect;
    }

    @Override
    public void doCommand() {
        if (connected) {

        }
        else
            device.disconnect();
    }

}
