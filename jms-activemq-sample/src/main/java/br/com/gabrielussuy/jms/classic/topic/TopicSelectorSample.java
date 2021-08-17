package br.com.gabrielussuy.jms.classic.topic;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Scanner;

public class TopicSelectorSample {

    private static final String TOPIC_NAME = "store";
    private static final String CLIENT_ID = "inventory";
    private static final String SELECTOR_SIGNATURE = "selector_signature";

    public static void main(String[] args) throws NamingException, JMSException {
        InitialContext context = new InitialContext();
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");

        Connection connection = factory.createConnection();
        connection.setClientID(CLIENT_ID);
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic  topic = (Topic ) context.lookup(TOPIC_NAME);

        MessageConsumer consumer = session.createDurableSubscriber(topic, SELECTOR_SIGNATURE, "ebook=true", false);

        consumer.setMessageListener(message -> {
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println(textMessage.getText());
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
