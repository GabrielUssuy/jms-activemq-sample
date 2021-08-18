package br.com.gabrielussuy.sample.jms.classic.topic;

import br.com.gabrielussuy.sample.jms.utils.Order;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Scanner;

public class TopicConsumer {

    private static final String TOPIC_NAME = "store";
    private static final String CLIENT_ID = "inventory";
    private static final String SIGNATURE = "signature";

    public static void main(String[] args) throws NamingException, JMSException {
//        System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES","*");

        InitialContext context = new InitialContext();
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
        ((ActiveMQConnectionFactory) factory).setTrustAllPackages(true);
        Connection connection = factory.createConnection();
        connection.setClientID(CLIENT_ID);
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic  topic = (Topic ) context.lookup(TOPIC_NAME);

        MessageConsumer consumer = session.createDurableSubscriber(topic, SIGNATURE);

        consumer.setMessageListener(message -> {
            ObjectMessage objectMessage = (ObjectMessage)message;
            try {
                Order order = (Order) objectMessage.getObject();
                System.out.println(order.getId());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });

        new Scanner(System.in).nextLine();

        session.close();
        connection.close();
        context.close();
    }
}
