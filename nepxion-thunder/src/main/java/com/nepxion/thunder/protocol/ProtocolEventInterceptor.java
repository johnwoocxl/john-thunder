package com.nepxion.thunder.protocol;

/**
 * <p>Title: Nepxion Thunder</p>
 * <p>Description: Nepxion Thunder For Distribution</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: Nepxion</p>
 * @author Neptune
 * @email 1394997@qq.com
 * @version 1.0
 */

import com.google.common.eventbus.Subscribe;
import com.nepxion.thunder.common.eventbus.EventControllerFactory;
import com.nepxion.thunder.common.eventbus.EventControllerType;

public abstract class ProtocolEventInterceptor {
    public ProtocolEventInterceptor() {
        EventControllerFactory.getSingletonController(EventControllerType.ASYNC).register(this);
    }

    @Subscribe
    public void listen(ProtocolEvent event) {
        onEvent(event);
    }
    
    protected abstract void onEvent(ProtocolEvent event);
}