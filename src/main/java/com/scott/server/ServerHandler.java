package com.scott.server;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
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

public class ServerHandler extends SimpleChannelHandler{
	 private static final Logger logger = Logger.getLogger(ServerHandler.class.getName());
    @Override
      public void handleUpstream(
              ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
          if (e instanceof ChannelStateEvent) {
              logger.info(e.toString());
          }
          super.handleUpstream(ctx, e);
      }
  
    @Override
     public void messageReceived(
             ChannelHandlerContext ctx, MessageEvent e) {
    
//           Locations locations = (Locations) e.getMessage();
//             long currentTime = System.currentTimeMillis();
             Object message = e.getMessage();
         	if(!(message instanceof Majiang)){
         		//return err
         	}
         	else{
         		Majiang mjRaw = (Majiang) message;
         		Majiang.Builder mj = Majiang.newBuilder(mjRaw);
     	        //为了节省代码量,这里这里判断是request还是response.
     	        if(mj.getType().equals(MessageType.JOIN_TABLE_C2S)) {
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
     	        else if(mj.getType().equals(MessageType.JOIN_TABLE_S2C)) {
    	        	Majiang.Builder mjBuilder = Majiang.newBuilder();
    	     	    mjBuilder.setType(MessageType.JOIN_TABLE_C2S);
    	            ctx.getChannel().write(mjBuilder.build());
    	            logger.info("entered a table"+mj.getJoinTableS2C().getTableInfo().getName());
    	        } 
         	}
          }
     
        @Override
         public void exceptionCaught(
                 ChannelHandlerContext ctx, ExceptionEvent e) {
             logger.log(
                      Level.WARN,
                      "Unexpected exception from downstream.",
                     e.getCause());
             e.getChannel().close();
          }
     
//         private static String toString(Continent c) {
//              return "" + c.name().charAt(0) + c.name().toLowerCase().substring(1);
//         }
}
