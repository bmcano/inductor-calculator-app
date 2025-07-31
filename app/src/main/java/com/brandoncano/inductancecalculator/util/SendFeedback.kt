package com.brandoncano.inductancecalculator.util

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri
import com.brandoncano.inductancecalculator.R

/**
 * Job: Takes the user to compose an email with a predefined subject and empty body.
 */
object SendFeedback {

    fun execute(context: Context) {
        val version = context.getString(R.string.version)
        val emailLink = "mailto:brandoncano.development@gmail.com?subject=[Feedback] - Inductor Color Code (${version})"
        val intent = Intent(Intent.ACTION_SENDTO, emailLink.toUri())
        try {
            val title = "Send email"
            context.startActivity(Intent.createChooser(intent, title))
        } catch (ex: Exception) {
            ex.printStackTrace()
            ErrorDialog.build(context, context.getString(R.string.error_send_feedback))
        }
    }
}
