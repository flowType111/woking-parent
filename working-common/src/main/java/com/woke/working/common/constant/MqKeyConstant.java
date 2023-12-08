package com.woke.working.common.constant;

public class MqKeyConstant {

    /**
     * mq交换机
     *
     */
    public class MqExchange{
        public static final String exchange = "DEFAULT_EXCHANGE";
    }

    /**
     * 队列
     *
     */
    public class MqTopic{
        public static final String orderTopic = "pay_order_topic";
    }

    /**
     * routingKey
     *
     */
    public class RoutingKey{
        public static final String routingKey = "ROUTING_KEY";
    }
}
