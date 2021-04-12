package cn.edu.nju.software.iot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import cn.edu.nju.software.iot.cloud.netty.server.MessageBuffer;
import cn.edu.nju.software.iot.shared.netty.server.ClientChannels;
import cn.edu.nju.software.iot.shared.netty.server.NettyServer;
import cn.hutool.core.codec.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**  
 * @ClassName: Application  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2021年3月22日  
 *  
 */

@SpringBootApplication
public class CloudApplication implements CommandLineRunner {

    @Autowired
    private NettyServer server;

    @Override
    public void run(String... args) throws Exception {
        new Thread(() -> {
            try {
                server.listen();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        ThreadLocalRandom r = ThreadLocalRandom.current();
        boolean b = false;

        while (true) {
            Thread.sleep(10000);
            b = !b;
            writeMesg("testID1", "device-1", "brightness " + r.nextInt(101));
            MessageBuffer.write("wlan " + (b ? 1 : 0) + " " + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
            MessageBuffer.write("sound " + r.nextInt(100) + " " + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        }
    }

    //
    public void writeMesg(String clientId, String deviceId, String mesg) {
        String data = Base64.encode(mesg);
        server.writeMesg(clientId, deviceId + " " + data);
    }

    public static void main(String[] args) {
        SpringApplication.run(CloudApplication.class, args);
    }

}
