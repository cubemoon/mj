package com.scott.client;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import com.scott.majiang.proto.GamingProtocol.Majiang;
import com.scott.majiang.proto.GamingProtocol.MessageType;

public class MjClient {
	
	   private final static String host = "localhost";
	   private final static int port = 8090;
	   
	   public static void main(String[] args) throws Exception {
	        // Set up.
	        ClientBootstrap bootstrap = new ClientBootstrap(
	                new NioClientSocketChannelFactory(
	                        Executors.newCachedThreadPool(),
	                        Executors.newCachedThreadPool()));

	        // Configure the event pipeline factory.
	        bootstrap.setPipelineFactory(new ClientPipelineFactory());

	        // Make a new connection.
	        ChannelFuture connectFuture =
	            bootstrap.connect(new InetSocketAddress(host, port));

	        // Wait until the connection is made successfully.
//	        Channel channel = connectFuture.awaitUninterruptibly().getChannel();

	        // Get the handler instance to initiate the request.
//	        ClientHandler handler =
//	            channel.getPipeline().get(ClientHandler.class);
//	        handler.writeRequested(ctx, e)
	      //创建无连接传输channel的辅助类(UDP),包括client和server
//	        ChannelFuture future = bootstrap.connect(new InetSocketAddress(
//	                 "zhenqin-k45vm", 8080));
	        /*
	         * 给足够的时间, 让建立连接
	         */
	        try {
	        	connectFuture.await(500);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        /* 连接成功否? */
	        if(connectFuture.isSuccess()){
	            Channel w = connectFuture.getChannel();

	    	    Majiang.Builder mjBuilder = Majiang.newBuilder();
	    	    mjBuilder.setType(MessageType.JOIN_TABLE_C2S);
	    	    Majiang mj = mjBuilder.build();

	            ChannelFuture f = w.write(mj);
	            //立即同步, 不等缓冲区满了. 否则还得等待缓冲区满了才会发送给远程
	            f.awaitUninterruptibly();

	        }
	        
	        // Request and get the response.
//	        List<String> response = handler.getLocalTimes(cities);
	        
	        // Close the connection.
//	        channel.close().awaitUninterruptibly();

	        // Shut down all thread pools to exit.
//	        bootstrap.releaseExternalResources();
	    }

}
