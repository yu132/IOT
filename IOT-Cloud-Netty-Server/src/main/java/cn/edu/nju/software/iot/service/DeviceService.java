package cn.edu.nju.software.iot.service;

public interface DeviceService {
    public String turnOn(String id);
    public String turnOff(String id);
    public String changeBrightness(String id, int brightness);
    public String changeColor(String id, int color);
    public String disconnect(String id);
    public String[] getLamps();
}
