package com.yikai.activemq.queue;


import com.yikai.util.CommonUtil;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Date;

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
        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;
        try {
            connection = activeMQConnectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue(QUEUE_NAME);
            producer = session.createProducer(queue);
            for (int i = 1; i < 11; i++) {
                TextMessage message = session.createTextMessage(new Date() + "的消息编号:  " + i);
                producer.send(message);
                System.out.println(message.getText());
//                Thread.sleep(30000);
            }

            System.out.println("发送成功");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            CommonUtil.close(producer,null,session,connection);
        }
    }
}
