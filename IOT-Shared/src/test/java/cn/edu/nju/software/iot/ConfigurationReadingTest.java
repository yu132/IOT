package cn.edu.nju.software.iot;

import java.util.List;

import cn.edu.nju.software.iot.shared.netty.server.NettyServer;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**  
 * @ClassName: ConfigurationReadingTest  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2021年3月22日  
 *  
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigurationReadingTest {

    @Value("${a.b}")
    int val;

    @Value("${netty.ids}")
    List<String> ids;

    @Autowired
    NettyServer nettyServer;

    @Test
    public void test() {
        System.out.println(val);
        System.out.println(ids);
        System.out.println(nettyServer);
    }

}
