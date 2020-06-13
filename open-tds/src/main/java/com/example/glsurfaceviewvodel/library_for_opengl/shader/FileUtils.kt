package com.example.glsurfaceviewvodel.library_for_opengl.shader

import android.content.Context
import android.content.res.Resources
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

object FileUtils {
    fun readTextFromRaw(context: Context, resourceId: Int): String {
        val stringBuilder = StringBuilder()
        try {
            var bufferedReader: BufferedReader? = null
            try {
                val inputStream = context.resources.openRawResource(resourceId)
                bufferedReader = BufferedReader(InputStreamReader(inputStream))
                var line: String?
                line = bufferedReader.readLine()
                while (line != null) {
                    stringBuilder.append(line)
                    stringBuilder.append("\r\n")
                    line = bufferedReader.readLine()
                }
            } finally {
                bufferedReader?.close()
            }
        } catch (ioex: IOException) {
            ioex.printStackTrace()
        } catch (nfex: Resources.NotFoundException) {
            nfex.printStackTrace()
        }

        return stringBuilder.toString()
    }
}
