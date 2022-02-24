package com.plandel.threadaula

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var button1: Button
    lateinit var buttonIniciar: Button
    lateinit var buttonParar: Button
    var TAG = "MainActivity"
    private var numero = 0
    private val handle = Handler()
    private var pararExecucao = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button1)
        buttonIniciar = findViewById(R.id.buttonIniciar)
        buttonParar = findViewById(R.id.buttonParar)

        button1.setOnClickListener{

        }

        buttonIniciar.setOnClickListener {

//            val thread = MyThread()
//            thread.start()

            pararExecucao = false
            val runnable = MyRunnable()
            Thread(runnable).start()

        }

        buttonParar.setOnClickListener {

            pararExecucao = true
        }
    }


    //CRIAÇÃO COM UMA CLASSE RUNNABLE
    inner class MyRunnable : Runnable {
        override fun run() {

            for(i in 0..15){

                if(pararExecucao) return

                numero = i
                Log.d(TAG, "Iniciar Tread:$numero")

                runOnUiThread(Runnable {
                    buttonIniciar.text = "Iniciar Tread:$numero"
                })

//                handle.postDelayed(Runnable {
//                    buttonIniciar.text = "Iniciar Tread:$numero"
//                },5000)

                Thread.sleep(1000)
            }
        }

    }

    fun pararThread(){

    }

    //CRIAÇÃO DE UMA SEGUNDA
    inner class MyThread : Thread() {
        override fun run() {
            super.run()

            for(i in 1..15){
                Log.d(TAG, "Iniciar Tread:$i")
                Thread.sleep(1000)
            }
        }
    }
}