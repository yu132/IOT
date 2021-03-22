package cn.edu.nju.software.iot.device;

/**  
 * @ClassName: MockSensor  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2021年3月21日  
 *  
 */

public interface MockSensor {

    public String getInformation();

    public String sensorName();

    public String sensorId();

    default String getCurrentReport() {
        return sensorName() + " " + sensorId() + " " + getInformation();
    }

}
