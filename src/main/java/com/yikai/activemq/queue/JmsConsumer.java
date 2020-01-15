package com.yikai.activemq.queue;

import com.yikai.util.CommonUtil;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2019/12/12 14:31
 */
public class JmsConsumer {

    public static void main(String[] args) throws IOException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(JmsProduce.ACTIVEMQ_URL);
        Connection connection = null;
        Session session = null;
        MessageConsumer consumer = null;
        try {
            connection = activeMQConnectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue(JmsProduce.QUEUE_NAME);
            consumer = session.createConsumer(queue);
            while (true){
                TextMessage message = (TextMessage) consumer.receive(4000L);
                if (null != message){
                    System.out.println(message.getText());
                }else {
                    break;
                }
            }
//            consumer.setMessageListener(new MessageListener() {
//                @Override
//                public void onMessage(Message message) {
//                    if (message != null && message instanceof  TextMessage){
//                        TextMessage m = (TextMessage)message;
//                        try {
//                            System.out.println(m.getText());
//                        } catch (JMSException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            });
        }catch (Exception e){
            e.printStackTrace();
        }finally {
//            System.in.read();
            CommonUtil.close(null,consumer,session,connection);
        }
    }

}
