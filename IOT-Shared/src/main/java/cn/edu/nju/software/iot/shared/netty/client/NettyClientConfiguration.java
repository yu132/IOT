package cn.edu.nju.software.iot.shared.netty.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**  
 * @ClassName: NettyClientConfiguration  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2021年3月22日  
 *  
 */

@Configuration
@ConditionalOnProperty(prefix = "netty", name = "is-client",
    havingValue = "true")

public class NettyClientConfiguration {

    @Bean
    public NettyClient nettyClient(
        @Value("${netty.client.id}") String identitifyCode,
        @Value("${netty.client.server-ip}") String ip,
        @Value("${netty.client.server-port}") int port)
        throws InterruptedException {

        return new NettyClient(identitifyCode, ip, port);
    }

}
