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
            System.out.println("二号消费者");
            connection = activeMQConnectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue(JmsProduce.QUEUE_NAME);
            consumer = session.createConsumer(queue);
            //消费的两种方式
            //监听器式的非阻塞
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    if (message != null && message instanceof TextMessage){
                        TextMessage m = (TextMessage) message;
                        try {
                            System.out.println(m.getText());
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            //阻塞式的接收
//            while (true){
//                TextMessage mes = (TextMessage) consumer.receive();
//                if (mes != null){
//                    System.out.println(mes.getText());
//                }else {
//                    break;
//                }
//
//            }

            System.out.println("接收完成");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
//            System.in.read();
            CommonUtil.close(null,consumer,session,connection);
        }
    }

}
