package com.nepxion.thunder.protocol;

/**
 * <p>Title: Nepxion Thunder</p>
 * <p>Description: Nepxion Thunder For Distribution</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import java.util.UUID;

import com.nepxion.thunder.common.config.ReferenceConfig;

public class ProtocolRequest extends ProtocolMessage {
    private static final long serialVersionUID = 3399899702039468806L;
    
    private ReferenceConfig referenceConfig;
    
    public ProtocolRequest() {
        String messageId = UUID.randomUUID().toString();
        
        super.setMessageId(messageId);
    }
    
    // Request的MessageId自动产生，不需要设置
    public void setMessageId(String messageId) {
        
    }

    public ReferenceConfig getReferenceConfig() {
        return referenceConfig;
    }

    public void setReferenceConfig(ReferenceConfig referenceConfig) {
        this.referenceConfig = referenceConfig;
    }
}