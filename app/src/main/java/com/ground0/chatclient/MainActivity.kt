package com.ground0.chatclient

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ground0.rabbitmqclient.MessageBus
import com.ground0.rabbitmqclient.Receiver
import com.ground0.rabbitmqclient.model.MessageReceived
import kotlinx.android.synthetic.main.activity_main.a_main_text

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    initListener()
  }

  private fun initListener() {
    Thread(Runnable {
      Receiver.listen(object : MessageBus {
        override fun onNext(event: MessageReceived) {
          a_main_text.post { a_main_text.text = "${a_main_text.text}${event.message}" }
        }
      })
    }).start()
  }
}
