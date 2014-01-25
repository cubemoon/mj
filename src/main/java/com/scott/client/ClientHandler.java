package com.scott.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import com.scott.majiang.proto.GamingProtocol.JoinTableS2C;
import com.scott.majiang.proto.GamingProtocol.Majiang;
import com.scott.majiang.proto.GamingProtocol.MessageType;
import com.scott.majiang.proto.GamingProtocol.TableInfo;

public class ClientHandler extends SimpleChannelHandler {

    private static final Logger logger = Logger.getLogger(ClientHandler.class.getName());

    // Stateful properties
    private volatile Channel channel;
    
    @Override
    public void messageReceived(
            ChannelHandlerContext ctx, final MessageEvent e) {
    	Object message = e.getMessage();
    	if(!(message instanceof Majiang)){
    		//return err
    	}
    	else{
     		Majiang mjRaw = (Majiang) message;
     		Majiang.Builder mj = Majiang.newBuilder(mjRaw);
	        //为了节省代码量,这里这里判断是request还是response.
	        if(mj.getType().equals(MessageType.JOIN_TABLE_S2C)) {
	        	Majiang.Builder mjBuilder = Majiang.newBuilder();
	     	    mjBuilder.setType(MessageType.JOIN_TABLE_C2S);
	            ctx.getChannel().write(mjBuilder.build());
	            logger.info("entered a table"+mj.getJoinTableS2C().getTableInfo().getName());
	        } 
	        else if(mj.getType().equals(MessageType.JOIN_TABLE_C2S)) {
 	        	Majiang.Builder mjBuilder = Majiang.newBuilder();
 	     	    mjBuilder.setType(MessageType.JOIN_TABLE_S2C);
 	     	    
 	     	    TableInfo.Builder tBuilder = TableInfo.newBuilder();
 	     	    tBuilder.setName("{Royal Scott Table}");
 	     	    
 	     	    JoinTableS2C.Builder jt = JoinTableS2C.newBuilder();
 	     	    jt.setTableInfo(tBuilder);
 	     	    
 	     	    mjBuilder.setJoinTableS2C(jt);
 	     	    
 	            ctx.getChannel().write(mjBuilder.build());
 	            logger.info("provided a table :"+mj.getJoinTableS2C().getTableInfo().getName());
 	        } 
    	}
    }
    
    
    public void joinTable(){
	    Majiang.Builder mjBuilder = Majiang.newBuilder();
	    mjBuilder.setType(MessageType.JOIN_TABLE_C2S);
	    Majiang mj = mjBuilder.build();
	    channel.write(mj);    
    }

    @Override
    public void handleUpstream(
            ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
        if (e instanceof ChannelStateEvent) {
            logger.info(e.toString());
        }
        super.handleUpstream(ctx, e);
    }

    @Override
    public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e)
            throws Exception {
        channel = e.getChannel();
        super.channelOpen(ctx, e);
    }

    @Override
    public void exceptionCaught(
            ChannelHandlerContext ctx, ExceptionEvent e) {
        logger.log(
                Level.WARNING,
                "Unexpected exception from downstream.",
                e.getCause());
        e.getChannel().close();
    }
}
