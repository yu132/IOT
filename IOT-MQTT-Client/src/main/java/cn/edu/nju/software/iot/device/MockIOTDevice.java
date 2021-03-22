package cn.edu.nju.software.iot.device;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**  
 * @ClassName: IOTDevice  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2021年3月21日  
 *  
 */
@Component
public class MockIOTDevice {

    @Autowired(required = false)
    private List<MockSensor> sensors;

    @Autowired(required = false)
    private MockLight light;

    public List<String> getSensorData() {
        List<String> reports = new ArrayList<>();
        for (MockSensor sensor : sensors) {
            reports.add(sensor.getCurrentReport());
        }
        return reports;
    }

    public void setBrightnessOfLight(int brightness) {
        light.setBrightness(brightness);
    }

    public boolean isLight() {
        return light != null;
    }

    public boolean isSensor() {
        return sensors != null;
    }

}
