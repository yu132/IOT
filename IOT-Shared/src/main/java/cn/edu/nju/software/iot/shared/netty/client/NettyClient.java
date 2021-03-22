package cn.edu.nju.software.iot.shared.netty.client;

import java.nio.charset.Charset;
import java.util.function.Consumer;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**  
 * @ClassName: NettyClient  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2021年3月21日  
 *  
 */

public class NettyClient {

    private Channel channel;

    private Consumer<String> mesgFromColudServerHandler;

    private final static Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private String identitifyCode;
    private String ip;
    private int port;
    private Bootstrap bs;

    public NettyClient(String identitifyCode, String ip, int port)
        throws InterruptedException {

        EventLoopGroup bossGroup = new NioEventLoopGroup();

        Bootstrap bs = new Bootstrap();

        bs.group(bossGroup).channel(NioSocketChannel.class)
            .option(ChannelOption.SO_KEEPALIVE, true)
            .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel)
                    throws Exception {

                    ChannelPipeline pipeline = socketChannel.pipeline();

                    pipeline.addLast(new LengthFieldBasedFrameDecoder(
                        Integer.MAX_VALUE, 0, 4, 0, 4));
                    pipeline.addLast(new LengthFieldPrepender(4, false));

                    pipeline.addLast(new StringDecoder(DEFAULT_CHARSET));

                    pipeline.addLast(new StringEncoder(DEFAULT_CHARSET));

                    pipeline.addLast(new SimpleChannelInboundHandler<String>() {

                        @Override
                        protected void channelRead0(ChannelHandlerContext ctx,
                            String msg) {
                            handleMesg(msg);
                        }

                    });
                }
            });

        this.identitifyCode = identitifyCode;
        this.ip = ip;
        this.port = port;
        this.bs = bs;

    }

    public void connect() throws InterruptedException {
        // 客户端开启
        ChannelFuture cf = bs.connect(ip, port).sync();

        channel = cf.channel();

        channel.writeAndFlush("auth " + identitifyCode);

        System.out.println("发送认证");
    }

    private void handleMesg(String str) {
        mesgFromColudServerHandler.accept(str);
    }

    public synchronized void sendMesg(String str) {
        channel.writeAndFlush(str);
    }

    public void setMesgFromColudServerHandler(
        Consumer<String> mesgFromColudServerHandler) {
        this.mesgFromColudServerHandler = mesgFromColudServerHandler;
    }

}
