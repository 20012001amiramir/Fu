package ru.tstst.schoolboy.data.repository

import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.fu.data.network.Api
import com.example.fu.data.network.request.TokenRequest
import ru.tstst.schoolboy.data.persistent.LocalStorage
import com.example.fu.util.ErrorHandler
import javax.inject.Inject

class TokenRepository @Inject constructor(
    private val api: Api,
    private val errorHandler: ErrorHandler,
    private val localStorage: LocalStorage
) {
    private suspend fun registerToken(token: String){
        return api.registerToken(TokenRequest(token))
    }
    private suspend fun unregisterToken(token: String) {
        return api.unregisterToken(TokenRequest(token))
    }
    fun registerTokenWithUnregister(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }
            val token = task.result
            CoroutineScope(Dispatchers.IO).launch {
                try{
                    registerToken(token!!)
                    Log.d("register-device-token", token)
                    localStorage.fcmToken = token
                }catch (e: Throwable) {
                    if (e !is CancellationException) {
                        errorHandler.proceed(e)
                    }
                }
            }
        })
    }

    fun unregisterTokenWithUnregister(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }

            val token = task.result
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    if(localStorage.fcmToken != null) {
                        unregisterToken(localStorage.fcmToken!!)
                    }
                    unregisterToken(token!!)
                    Log.d("unregister-device-token", token)
                    localStorage.fcmToken = null
                } catch (e: Throwable) {
                    if (e !is CancellationException) {
                        errorHandler.proceed(e)
                    }
                }
            }
        })
    }
}