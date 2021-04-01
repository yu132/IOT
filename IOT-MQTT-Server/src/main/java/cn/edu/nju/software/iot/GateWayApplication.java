package cn.edu.nju.software.iot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

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
public class GateWayApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context =
            SpringApplication.run(GateWayApplication.class, args);

        MQTTStarter starter = context.getBean(MQTTStarter.class);

        starter.run();
    }

}
