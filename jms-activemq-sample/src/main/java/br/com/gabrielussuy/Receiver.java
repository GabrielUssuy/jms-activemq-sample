package br.com.gabrielussuy;

import javax.jms.Message;
import java.util.Scanner;
import javax.jms.Queue;
import javax.jms.QueueReceiver;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.InitialContext;

public class Receiver {

    public static void main(String[] args) throws Exception {
        InitialContext ctx = new InitialContext();
        QueueConnectionFactory cf = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
        QueueConnection conexao = cf.createQueueConnection();
        conexao.start();

        QueueSession sessao = conexao.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue fila = (Queue) ctx.lookup("financeiro");
        Receiver receiver = (Receiver) sessao.createReceiver(fila);

        Message message = receiver.
        System.out.println(message);

        new Scanner(System.in).nextLine();

        sessao.close();
        conexao.close();
        ctx.close();
    }
}
