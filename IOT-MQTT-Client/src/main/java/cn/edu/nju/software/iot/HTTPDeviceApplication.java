package cn.edu.nju.software.iot;

import java.util.List;
import java.util.function.BiConsumer;

import cn.edu.nju.software.iot.device.MockIOTDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

/**  
 * @ClassName: HTTPDeviceApplication  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2021年4月1日  
 *  
 */

@SpringBootApplication
public class HTTPDeviceApplication implements CommandLineRunner {

    @Value("${mqtt.id}")
    String deviceId;

    @Autowired
    String postDataUrl;

    @Autowired
    String getDataUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MockIOTDevice device;

    @Autowired
    private BiConsumer<String, String> callback;

    @Override
    public void run(String... args) throws Exception {
        new Thread(() -> {
            @SuppressWarnings("unchecked")
            List<String> responses =
                restTemplate.getForObject(getDataUrl, List.class, deviceId);
            for (String mesg : responses) {
                callback.accept(null, mesg);
            }
        }).start();
        while (true) {// 模拟10s发送一次传感器数据
            Thread.sleep(10000);
            publishInHttp();
        }
    }

    private void publishInHttp() {
        List<String> reports = device.getSensorData();
        for (String report : reports) {
            String response = restTemplate.postForObject(postDataUrl, null,
                String.class, deviceId, report);
            System.out.println(response);
            System.out.println("发送信息：" + report);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(HTTPDeviceApplication.class, args);
    }

}
