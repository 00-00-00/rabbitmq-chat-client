package com.ground0.rabbitmqclient

import com.ground0.rabbitmqclient.model.MessageReceived

interface MessageBus {
  fun onNext(event: MessageReceived)
}