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
    boolean isOn, connected;
    int brightness, color;

    public boolean setBrightness(int brightness) {
        if (brightness < 0 || brightness > 100) {
            System.out.println("灯的亮度值非法！");
            return false;
        }
        this.brightness = brightness;
        System.out.println("灯的亮度被调整为：" + brightness);
        return changeBrightnessOfLight(brightness);
    }

    public boolean setColor(int color) {
        if (color < 0 || brightness > 10) {
            System.out.println("灯的颜色值非法！");
            return false;
        }
        this.color = color;
        System.out.println("灯的颜色被调整为：" + color);
        return changeColor(color);
    }

    public boolean setOn() {
        if (isOn) {
            System.out.println("灯已被打开！");
            return false;
        }
        isOn = true;
        System.out.println("灯成功被打开！");
        return changeToOn();
    }

    public boolean setOff() {
        if (!isOn) {
            System.out.println("灯已被关闭！");
            return false;
        }
        isOn = false;
        System.out.println("灯成功被关闭！");
        return changeToOff();
    }

    public boolean disconnect() {
        if (!connected) {
            System.out.println("灯已断开连接！");
            return false;
        }
        connected = false;
        System.out.println("灯成功断开连接！");
        return changeToDisconnected();
    }

    public boolean isOn() {
        return isOn;
    }

    public int getBrightness() {
        return brightness;
    }

    public int getColor() {
        return color;
    }

    public boolean changeBrightnessOfLight(int brightness) {return true;}
    public boolean changeColor(int color) {return true;}
    public boolean changeToOn() {return true;}
    public boolean changeToOff() {return true;}
    public boolean changeToDisconnected() {return true;}
}
