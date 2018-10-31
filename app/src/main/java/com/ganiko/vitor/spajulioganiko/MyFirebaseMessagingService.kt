package com.ganiko.vitor.spajulioganiko

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import fernandosousa.com.br.lmsapp.Prefs

class MyFirebaseMessagingService: FirebaseMessagingService()  {

    override fun onNewToken(token: String?) {
        super.onNewToken(token)
        Log.d("MeuApp", "Novo token: $token")
        Prefs.setString("FB_TOKEN", token!!)
    }

    override fun onMessageReceived(mensagemRemota: RemoteMessage?) {
        Log.d("MeuApp", "chamou onMessageReceived")

        if (mensagemRemota?.notification != null) {
            val titulo = mensagemRemota?.notification?.title
            val texto = mensagemRemota?.notification?.body
            Log.d("MeuApp", titulo)
            Log.d("MeuApp", texto)


        }
    }


}