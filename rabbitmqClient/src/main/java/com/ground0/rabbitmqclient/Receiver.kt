package com.ground0.rabbitmqclient

import com.ground0.rabbitmqclient.model.MessageReceived
import com.rabbitmq.client.AMQP.BasicProperties
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DefaultConsumer
import com.rabbitmq.client.Envelope
import rx.subjects.BehaviorSubject
import rx.subjects.PublishSubject

object Receiver {

  const val QUEUE_NAME = "test_queue"

  public fun listen(callback: MessageBus) {
    val factory = ConnectionFactory()
    factory.host = "192.168.0.8"
    val connection = factory.newConnection()
    val channel = connection.createChannel()

    channel.queueDeclare(QUEUE_NAME, false, false, false, null)
    println(" [*] Waiting for messages. To exit press CTRL+C")

    val consumer = object : DefaultConsumer(channel) {
      override fun handleDelivery(
        consumerTag: String?,
        envelope: Envelope?,
        properties: BasicProperties?,
        body: ByteArray?
      ) {
        body?.let {
          val message = String(body, Charsets.UTF_8)
          println(" [x] Received '$message'")
          callback.onNext(MessageReceived(message))
        }
      }
    }

    channel.basicConsume(QUEUE_NAME, true, consumer)
  }
}