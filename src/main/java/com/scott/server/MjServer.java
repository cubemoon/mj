package com.scott.server;


import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class MjServer {
	 private static final Logger logger = Logger.getLogger(MjServer.class.getName());
	public static void main(String[] args) throws Exception {
       // Configure the server.
        ServerBootstrap bootstrap = new ServerBootstrap(
                 new NioServerSocketChannelFactory(
                         Executors.newCachedThreadPool(),
                         Executors.newCachedThreadPool()));
 
         // Set up the event pipeline factory.
         bootstrap.setPipelineFactory(new ServerPipelineFactory());
 
         // Bind and start to accept incoming connections.
         bootstrap.bind(new InetSocketAddress(8090));
         
         logger.info("Majing Server is ready...");
     }
}
