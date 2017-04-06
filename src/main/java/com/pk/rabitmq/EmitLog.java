package com.pk.rabitmq;


import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


	public class EmitLog {

	    private static final String EXCHANGE_NAME = "logs";

	    public static void main(String[] argv)
	                  throws java.io.IOException, TimeoutException {

	        ConnectionFactory factory = new ConnectionFactory();
	        factory.setHost("localhost");
	        Connection connection = factory.newConnection();
	        Channel channel = connection.createChannel();

	        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
Scanner scanner=new Scanner(System.in);
System.out.println("Enter the leterr:");
scanner.nextLine();
	        String message = "hi praveen";

	        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
	        System.out.println(" [x] Sent '" + message + "'");

	        channel.close();
	        connection.close();
	    }
	    //...
	}