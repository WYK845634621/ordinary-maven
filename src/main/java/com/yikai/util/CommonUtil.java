package com.yikai.util;

import javax.jms.*;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2019/12/12 14:14
 */
public class CommonUtil {

    public static void close(MessageProducer producer, MessageConsumer consumer, Session session, Connection connection){
        if (producer != null){
            try {
                producer.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
        if (consumer != null){
            try {
                consumer.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
        if (session != null){
            try {
                session.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
        if (connection != null){
            try {
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

}
