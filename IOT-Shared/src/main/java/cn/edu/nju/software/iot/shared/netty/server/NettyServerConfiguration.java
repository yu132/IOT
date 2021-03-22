package cn.edu.nju.software.iot.shared.netty.server;

import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**  
 * @ClassName: NettyServerConfiguration  
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

public class NettyServerConfiguration {

    @Bean(name = "nettyServer")
    public NettyServer nettyServer(@Value("${netty.server.port}") int port,
        Consumer<String> nettyServerMesgHandle, IdentityAuthentication auth,
        ClientChannels channels) {

        return new NettyServer(port, nettyServerMesgHandle, auth, channels);
    }

    @Bean
    public IdentityAuthentication
        auth(@Value("${netty.server.ids}") List<String> ids) {

        return new IdentityAuthentication(ids);
    }


}
