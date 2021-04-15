package cn.edu.nju.software.iot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cn.edu.nju.software.iot.shared.netty.server.NettyServer;
import cn.hutool.core.codec.Base64;

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

//        ThreadLocalRandom r = ThreadLocalRandom.current();
//
//        Scanner scan = new Scanner(System.in);
//        while (true) {
//            Thread.sleep(10000);
//            scan.nextInt();
//            writeMesg("testID1", "device-1", "brightness " + r.nextInt(101));
//            scan.nextInt();
//            writeMesg("testID1", "device-1", "color " + r.nextInt(9));
//            scan.nextInt();
//            writeMesg("testID1", "device-1", "on");
//            scan.nextInt();
//            writeMesg("testID1", "device-1", "off");
//            scan.nextInt();
//            writeMesg("testID1", "device-1", "disconnect");
//        }
    }

    public void writeMesg(String clientId, String deviceId, String mesg) {
        String data = Base64.encode(mesg);
        server.writeMesg(clientId, deviceId + " " + data);
    }

    public static void main(String[] args) {
        SpringApplication.run(CloudApplication.class, args);
    }

}
