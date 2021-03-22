package cn.edu.nju.software.iot.shared.netty.server;

import java.nio.charset.Charset;
import java.util.Optional;
import java.util.function.Consumer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**  
 * @ClassName: NettyServer  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2021年3月22日  
 *  
 */
public class NettyServer {

    private int port;

    private final static Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private Consumer<String> mesgHandler;

    private IdentityAuthentication identityAuthentication;

    private ClientChannels channels;

    public NettyServer(int port, Consumer<String> mesgHandler,
        IdentityAuthentication identityAuthentication,
        ClientChannels channels) {
        super();
        this.port = port;
        this.mesgHandler = mesgHandler;
        this.identityAuthentication = identityAuthentication;
        this.channels = channels;
    }

    // netty 服务端启动
    public void listen() throws InterruptedException {

        // 用来接收进来的连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 用来处理已经被接收的连接，一旦bossGroup接收到连接，就会把连接信息注册到workerGroup上
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // nio服务的启动类
            ServerBootstrap sbs = new ServerBootstrap();
            // 配置nio服务参数
            sbs.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class) // 说明一个新的Channel如何接收进来的连接
                .option(ChannelOption.SO_BACKLOG, 128) // tcp最大缓存链接个数
                .childOption(ChannelOption.SO_KEEPALIVE, true) // 保持连接
                .handler(new LoggingHandler(LogLevel.INFO)) // 打印日志级别
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel)
                        throws Exception {

                        ChannelPipeline pipeline = socketChannel.pipeline();

                        pipeline.addLast(new LengthFieldBasedFrameDecoder(
                            Integer.MAX_VALUE, 0, 4, 0, 4));
                        pipeline.addLast(new LengthFieldPrepender(4, false));

                        pipeline.addLast(new StringDecoder(DEFAULT_CHARSET));

                        pipeline.addLast(new StringEncoder(DEFAULT_CHARSET));

                        pipeline
                            .addLast(new SimpleChannelInboundHandler<String>() {

                                @Override
                                protected void channelRead0(
                                    ChannelHandlerContext ctx, String msg) {

                                    if (identityAuthentication
                                        .registered(socketChannel)) {
                                        ctx.fireChannelRead(msg);
                                    } else {

                                        System.out.println(msg);

                                        String[] sp = msg.split(" ");

                                        if (sp.length != 2
                                            || !sp[0].equals("auth")
                                            || !identityAuthentication
                                                .registerChannelWithId(sp[1],
                                                    socketChannel)) {

                                            System.out.println("认证失败");
                                            socketChannel.close();
                                        }
                                    }
                                }
                            });

                        pipeline
                            .addLast(new SimpleChannelInboundHandler<String>() {

                                @Override
                                protected void channelRead0(
                                    ChannelHandlerContext ctx, String msg) {
                                    handleMesg(msg);
                                }

                            });
                    }
                });

            System.err.println("server 开启--------------");
            // 绑定端口，开始接受链接
            ChannelFuture cf = sbs.bind(port).sync();

            // 开多个端口
            // ChannelFuture cf2 = sbs.bind(3333).sync();
            // cf2.channel().closeFuture().sync();

            // 等待服务端口的关闭；在这个例子中不会发生，但你可以优雅实现；关闭你的服务

            cf.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public void writeMesg(String clientName, String mesg) {
        Optional<Channel> channel = channels.getChannel(clientName);
        if (channel.isPresent()) {
            System.out.println("发送数据到" + clientName + ":" + mesg);
            channel.get().writeAndFlush(mesg);
        }
    }

    private void handleMesg(String str) {
        mesgHandler.accept(str);
    }

}
