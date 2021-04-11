package cn.edu.nju.software.iot.device.sensor;

import java.util.concurrent.ThreadLocalRandom;

public class BrightnessSensor implements MockSensor {
    @Override
    public String sensorName() {
        return "brightness";
    }

    @Override
    public String sensorId() {
        return "b-1";
    }

    @Override
    public String getInformation() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        return String.valueOf(r.nextInt(100));
    }
}
