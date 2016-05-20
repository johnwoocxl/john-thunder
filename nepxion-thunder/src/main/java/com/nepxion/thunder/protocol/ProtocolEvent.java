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

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.nepxion.thunder.common.constant.ThunderConstants;
import com.nepxion.thunder.common.entity.ActionType;
import com.nepxion.thunder.common.entity.ApplicationType;
import com.nepxion.thunder.common.entity.MethodKey;
import com.nepxion.thunder.common.entity.ProtocolType;
import com.nepxion.thunder.common.eventbus.Event;
import com.nepxion.thunder.common.util.ExceptionUtil;

public class ProtocolEvent extends Event implements Serializable {
    private static final long serialVersionUID = 8471746963028869465L;

    // 标识服务提供方，服务调用方
    private ApplicationType applicationType;

    // 标识生产事件，消费事件(当协议类型为消息队列时有效)
    private ActionType actionType;

    // 标识协议类型
    private ProtocolType protocolType;

    // 标识协议事件
    private ProtocolMessage protocolMessage;

    public ProtocolEvent(ApplicationType applicationType, ActionType actionType, ProtocolType protocolType, ProtocolMessage protocolMessage) {
        super();

        this.applicationType = applicationType;
        this.actionType = actionType;
        this.protocolType = protocolType;
        this.protocolMessage = protocolMessage;
    }

    public ApplicationType getApplicationType() {
        return applicationType;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public ProtocolType getProtocolType() {
        return protocolType;
    }

    public ProtocolMessage getProtocolMessage() {
        return protocolMessage;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("applicationType", applicationType);
        map.put("actionType", actionType);
        map.put("protocolType", protocolType);
        map.put("traceId", protocolMessage.traceId);
        map.put("messageId", protocolMessage.messageId);
        map.put("fromCluster", protocolMessage.fromCluster);
        map.put("fromUrl", protocolMessage.fromUrl);
        map.put("toCluster", protocolMessage.toCluster);
        map.put("toUrl", protocolMessage.toUrl);
        map.put("processStartTime", new SimpleDateFormat(ThunderConstants.DATE_FORMAT).format(new Date(protocolMessage.processStartTime)));
        map.put("processEndTime", new SimpleDateFormat(ThunderConstants.DATE_FORMAT).format(new Date(protocolMessage.processEndTime)));
        map.put("deliverStartTime", new SimpleDateFormat(ThunderConstants.DATE_FORMAT).format(new Date(protocolMessage.deliverStartTime)));
        map.put("deliverEndTime", new SimpleDateFormat(ThunderConstants.DATE_FORMAT).format(new Date(protocolMessage.deliverEndTime)));
        map.put("processedTime", (protocolMessage.processEndTime - protocolMessage.processStartTime) + " ms");
        map.put("deliveredTime", (protocolMessage.deliverEndTime - protocolMessage.deliverStartTime) + " ms");
        map.put("totalTime", (protocolMessage.processEndTime - protocolMessage.processStartTime + protocolMessage.deliverEndTime - protocolMessage.deliverStartTime) + " ms");
        map.put("interface", protocolMessage.interfaze);
        map.put("method", protocolMessage.method);
        map.put("parameterTypes", MethodKey.toParameterTypes(protocolMessage.parameterTypes));
        map.put("async", protocolMessage.async);
        map.put("callback", protocolMessage.callback);
        map.put("timeout", protocolMessage.timeout);
        map.put("broadcast", protocolMessage.broadcast);
        map.put("feedback", protocolMessage.feedback);
        map.put("exception", toException());

        return map;
    }
    
    public String toException() {
        return ExceptionUtil.toExceptionString(protocolMessage.getException());
    }
}