package cn.edu.nju.software.iot.device;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**  
 * @ClassName: MockLight  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2021年3月22日  
 *  
 */
@Component
@ConditionalOnProperty(prefix = "device", name = "is-light",
    havingValue = "true")
public class MockLight {

    public void setBrightness(int brightness) {
        if (brightness < 0 || brightness > 100) {
            return;
        }
        System.out.println("灯的亮度被调整为：" + brightness);
        // changeBrightnessOfLight();//假定将调用该本地方法调整灯的亮度
    }

    // public native void changeBrightnessOfLight();

}
