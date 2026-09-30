package com.semenova.vkandroidproject.ui.theme

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.semenova.vkandroidproject.SecondActivity
import androidx.core.net.toUri

object IntentUtils {
    
    fun startSecondActivity(context: Context, text: String) {
        context.startActivity(SecondActivity.newIntent(context, text))
    }
    
    @SuppressLint("QueryPermissionsNeeded")
    fun startImplicitIntent(context: Context, text: String) {
        if (Regex("""^\+7\d{10}$""").matches(text)
        ) {
            openImplicitIntent(
                context = context,
                action = Intent.ACTION_DIAL,
                text = "tel:$text"
            )
        } else {
            Toast.makeText(
                context,
                "Неправльно набран номер",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    
    fun shareTextIntent(context: Context, text: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, text)
        }
        context.startActivity(Intent.createChooser(intent, "Поделиться через…"))
    }
    
    private fun openImplicitIntent(context: Context, action: String, text: String) {
        val intent = Intent(action).apply {
            data = text.toUri()
        }
        if (intent.resolveActivity(context.packageManager) != null
        ) {
            context.startActivity(intent)
        }
    }
}