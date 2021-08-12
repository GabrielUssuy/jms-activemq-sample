package br.com.gabrielussuy.jms.classic.queue;

import javax.jms.Message;
import java.util.Scanner;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.InitialContext;

public class QueueReceiverSample {

    private static final String QUEUE_NAME = "financial";

    public static void main(String[] args) throws Exception {
        InitialContext ctx = new InitialContext();
        QueueConnectionFactory cf = (QueueConnectionFactory)ctx.lookup("ConnectionFactory");

        QueueConnection connection = cf.createQueueConnection();
        connection.start();

        QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = (Queue) ctx.lookup(QUEUE_NAME);
        QueueReceiver receiver = (QueueReceiver) session.createReceiver(queue);

        Message message = receiver.receive();
        System.out.println(message);

        new Scanner(System.in).nextLine();

        session.close();
        connection.close();
        ctx.close();
    }
}
