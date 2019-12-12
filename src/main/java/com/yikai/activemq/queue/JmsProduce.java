package com.yikai.activemq.queue;


import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Description 如果是点对点传输的话  目的地就是队列;  如果是一对多传输的话,目的地就是主题;
 * @Tips
 * @Author yikai.wang
 * @Date 2019/12/11 16:36
 */
public class JmsProduce {

    public static final String ACTIVEMQ_URL = "tcp://192.168.187.129:61616";

    public static final String QUEUE_NAME = "queue_yikai_001";

    public static void main(String[] args) {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        try {
            Connection connection = activeMQConnectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue(QUEUE_NAME);
            MessageProducer producer = session.createProducer(queue);
            TextMessage message = session.createTextMessage("这是第二条数据");
            producer.send(message);

            producer.close();
            session.close();
            connection.close();
            System.out.println("发送完成");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
