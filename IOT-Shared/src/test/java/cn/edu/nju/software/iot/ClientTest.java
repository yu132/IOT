package cn.edu.nju.software.iot;

import java.util.concurrent.locks.LockSupport;

import cn.edu.nju.software.iot.shared.netty.client.NettyClient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ClientTest {

    @Autowired
    NettyClient client;

    @Test
    public void test() throws InterruptedException {
        client.connect();
        client.sendMesg("Hello World!");
        LockSupport.park();
    }

}
