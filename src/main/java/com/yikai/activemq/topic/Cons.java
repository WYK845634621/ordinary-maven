package com.yikai.activemq.topic;

import com.yikai.util.CommonUtil;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2019/12/13 11:11
 */
public class Cons {
    public static final String ACTIVEMQ_URL = "tcp://192.168.187.129:61616";

    public static final String TOPIC_NAME = "topic_yikai_001";

    public static void main(String[] args) throws IOException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = null;
        Session session = null;
        MessageConsumer consumer = null;
        try {
            System.out.println("消费者三号");
            connection = activeMQConnectionFactory.createConnection();
            connection.setClientID("三号");
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic(TOPIC_NAME);
            /**
             * 一定要先运行一次消费者,等于向MQ注册,类似声明订阅了这个主题,
             * 然后再运行生产者发送消息,此时无论消费者是否在线(如果不在线,则下次上线时)都会接收到消息
             */
            TopicSubscriber subscriber = session.createDurableSubscriber(topic, "remark");
            connection.start();

            Message message = subscriber.receive();
            while (null != message){
                TextMessage text = (TextMessage) message;
                System.out.println("接收到  " + text.getText());
                message = subscriber.receive();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            CommonUtil.close(null,consumer,session,connection);
        }
    }

}
