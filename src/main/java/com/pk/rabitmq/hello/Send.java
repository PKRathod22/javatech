package com.pk.rabitmq.hello;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;

public class Send {
	private final static String QUEUE_NAME = "hello";

	public static void main(String[] argv) throws java.io.IOException, TimeoutException {

		// create a connection to the server
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		
		// create a channel
		while (true) {
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();

			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter the message : ");
			String message = scanner.nextLine();
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
			System.out.println(" [x] Sent '" + message + "'");
			channel.close();
			connection.close();

		}


	}
}