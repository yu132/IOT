package cn.edu.nju.software.iot.device.sensor;

import java.util.concurrent.ThreadLocalRandom;

public class PressureSensor implements MockSensor {
    @Override
    public String sensorName() {
        return "pressure";
    }

    @Override
    public String sensorId() {
        return "p-1";
    }

    @Override
    public String getInformation() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        return String.valueOf(r.nextInt(300) + 500);
    }
}
