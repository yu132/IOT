package cn.edu.nju.software.iot.cloud.netty.server;

import java.util.function.Consumer;

import cn.hutool.core.codec.Base64;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**  
 * @ClassName: MesgHandleConfiguration  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2021年3月22日  
 *  
 */

@Configuration
@ConditionalOnProperty(prefix = "netty", name = "is-server",
    havingValue = "true")

public class MesgHandleConfiguration {

    // 定义需要的引用资源，通过spring注入

    @Bean(name = "nettyServerMesgHandle")
    public Consumer<String> handler() {
        return (str) -> {

            String[] sp = str.split(" ");

            String topic = sp[0];
            String data = Base64.decodeStr(sp[1]);

            System.out.println("服务器接收到消息：" + topic + " " + data);
            // 处理从网关接收到的数据
        };
    }

}
