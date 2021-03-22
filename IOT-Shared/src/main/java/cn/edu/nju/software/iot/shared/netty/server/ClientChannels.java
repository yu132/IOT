package cn.edu.nju.software.iot.shared.netty.server;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

/**  
 * @ClassName: ClientChannel  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2021年3月22日  
 *  
 */
@Component
public class ClientChannels {

    private Map<String, Channel> channels;

    public ClientChannels() {
        channels = new HashMap<>();
    }

    public Optional<Channel> getChannel(String name) {
        if (!channels.containsKey(name)) {
            return Optional.empty();
        } else {
            return Optional.of(channels.get(name));
        }
    }

    public void registerChannel(String name, Channel channel) {
        channels.put(name, channel);
    }

}
