package cn.edu.nju.software.iot.shared.netty.server;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import io.netty.channel.Channel;

/**  
 * @ClassName: IdentityAuthentication  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2021年3月22日  
 *  
 */
public class IdentityAuthentication {

    private Set<String> channelsID = new HashSet<>();

    private Set<Channel> channelRegistered = new HashSet<>();

    @Autowired
    private ClientChannels channels;

    public IdentityAuthentication(List<String> ids) {
        channelsID.addAll(ids);
    }

    public Optional<Channel> getChannel(String name) {
        return channels.getChannel(name);
    }

    public boolean registerChannelWithId(String id, Channel channel) {
        if (channelsID.contains(id)) {
            channels.registerChannel(id, channel);
            channelRegistered.add(channel);
            return true;
        }
        return false;
    }

    public boolean registered(Channel channel) {
        return channelRegistered.contains(channel);
    }

}
