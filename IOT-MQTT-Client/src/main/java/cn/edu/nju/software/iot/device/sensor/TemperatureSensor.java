package cn.edu.nju.software.iot.device.sensor;

import java.util.concurrent.ThreadLocalRandom;

public class TemperatureSensor implements MockSensor {
    @Override
    public String sensorName() {
        return "temperature";
    }

    @Override
    public String sensorId() {
        return "t-1";
    }

    @Override
    public String getInformation() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        return String.valueOf(r.nextInt(10) + 25);
    }
}
