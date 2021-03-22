package cn.edu.nju.software.iot.device.command;

/**  
 * @ClassName: Command  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2021年3月22日  
 *  
 */
public interface Command {

    public final static Command EMPTY_COMMAND = new Command() {

        @Override
        public void doCommand() {}
    };

    public void doCommand();

}
