package com.woke.working.web.send;

public interface MqSenderService {

    void sendMessage(String exchange,Boolean transacted, String routingKey,Object e);
}
