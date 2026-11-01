package com.hamzawysat.app.core.security
import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
class TokenManager(context:Context){private val key=MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build();private val prefs=EncryptedSharedPreferences.create(context,"secure_session",key,EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);fun save(token:String){prefs.edit().putString("access_token",token).apply()};fun read():String?=prefs.getString("access_token",null);fun clear(){prefs.edit().remove("access_token").apply()}}
