package com.shreyas.nytimes.utils

import android.util.Log
import com.google.gson.Gson
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.nio.charset.StandardCharsets

object TestJsonUtils {

    private val TAG = TestJsonUtils::class.java.simpleName

    fun <T> getObjectFromJsonFile(
        jsonFile: String,
        tClass: Class<T>?
    ): T? {
        var inputStream: InputStream? = null
        try {
            inputStream = this.javaClass.classLoader!!.getResourceAsStream(jsonFile)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            val json = String(buffer, StandardCharsets.UTF_8)
            return Gson().fromJson(json, tClass)
        } catch (exception: Exception) {
            Log.d(TAG, "Exception: ${exception.message}")
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close()
                } catch (exception: IOException) {
                    Log.d(TAG, "IOException: ${exception.message}")
                }
            }
        }
        return null
    }

    fun getResponseAsString(fileName: String): String {
        val location = this.javaClass.classLoader!!.getResource(fileName)
        val file = File(location.path)
        return String(file.readBytes())
    }
}