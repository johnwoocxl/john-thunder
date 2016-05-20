package com.nepxion.thunder.protocol.netty;

/**
 * <p>Title: Nepxion Thunder</p>
 * <p>Description: Nepxion Thunder For Distribution</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import io.netty.channel.ChannelException;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;

import java.net.SocketAddress;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nepxion.thunder.common.container.ExecutorContainer;
import com.nepxion.thunder.common.thread.ThreadPoolFactory;
import com.nepxion.thunder.protocol.ClientExecutorAdapter;
import com.nepxion.thunder.protocol.ProtocolRequest;
import com.nepxion.thunder.protocol.ProtocolResponse;

public class NettyClientHandler extends SimpleChannelInboundHandler<ProtocolResponse> {
    private static final Logger LOG = LoggerFactory.getLogger(NettyClientHandler.class);

    private ExecutorContainer executorContainer;
    private boolean transportLogPrint;
    private boolean heartBeatLogPrint;
    
    public NettyClientHandler(ExecutorContainer executorContainer, boolean transportLogPrint, boolean heartBeatLogPrint) {
        this.executorContainer = executorContainer;
        this.transportLogPrint = transportLogPrint;
        this.heartBeatLogPrint = heartBeatLogPrint;
    }

    @SuppressWarnings("all")
    @Override
    protected void channelRead0(final ChannelHandlerContext context, final ProtocolResponse response) throws Exception {
        final String interfaze = response.getInterface();
        ThreadPoolFactory.createThreadPoolClientExecutor(interfaze).submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                if (transportLogPrint) {
                    LOG.info("Response from server={}, service={}", getRemoteAddress(context), interfaze);
                }
                
                try {
                    ClientExecutorAdapter clientExecutorAdapter = executorContainer.getClientExecutorAdapter();
                    clientExecutorAdapter.handle(response);
                } catch (Exception e) {
                    LOG.error("Client handle failed", e);
                } finally {
                    ReferenceCountUtil.release(response);
                }

                return null;
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) throws Exception {
        // LOG.error("Unexpected exception from downstream, cause={}", cause.getMessage(), cause);
        LOG.warn("Unexpected exception from downstream, remote address={}", getRemoteAddress(context), cause);
        
        if (cause instanceof ChannelException) {
            LOG.error("The channel is closed for remote address={}", getRemoteAddress(context), cause);
            
            context.close();
        }
    }
    
    public SocketAddress getRemoteAddress(ChannelHandlerContext context) {
        return context.channel().remoteAddress();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext context, Object e) throws Exception {
        // 读写空闲时,发送心跳信息
        if (e instanceof IdleStateEvent) {
            /*IdleStateEvent event = (IdleStateEvent) e;
            IdleState state = event.state();
            if (state == IdleState.WRITER_IDLE) {*/
                ProtocolRequest request = new ProtocolRequest();
                request.setHeartbeat(true);
                request.setInterface(NettyHeartbeat.class.getName());
                request.setMethod("beat");
                request.setAsync(true);

                if (heartBeatLogPrint) {
                    LOG.info("Send heart beat request...");
                }

                context.writeAndFlush(request);
            /*}*/
        }
    }
}