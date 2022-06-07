package com.example.laundryapp.components

import android.graphics.Bitmap
import android.graphics.Color
import android.util.Log
import androidx.compose.runtime.Composable
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException

@Composable
fun QrGenerator(code:String) : Bitmap {
    val width = 800
    val height = 800
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val codeWriter = MultiFormatWriter()
    try {
        val bitMatrix = codeWriter.encode(code, BarcodeFormat.QR_CODE, width, height)
        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
            }
        }
    } catch (e: WriterException) {
//        Log.d(ContentValues.TAG, "generateQRCode: ${e.message}")
        Log.d("debug", "generateQRCode: ${e.message}")
    }
    return bitmap
}