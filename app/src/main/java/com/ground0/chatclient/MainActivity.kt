package com.ground0.chatclient

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ground0.rabbitmqclient.Receiver

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    initListener()
  }

  private fun initListener() {
    Thread(Runnable { Receiver.listen() }).start()
  }
}
