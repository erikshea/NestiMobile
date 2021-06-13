package com.nesti.nestimobile.ui.main.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.nesti.nestimobile.R
import com.nesti.nestimobile.ui.main.view.base.BaseActivity

/**
 * contact section
 */
class ContactActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_contact)
        super.onCreate(savedInstanceState)
        title = resources.getString(R.string.contact_title)
    }

    override fun setupUi() { }

    /**
     * called when email is pressed
     */
    fun contactEmail(view: View) {
        // set up and start "compose email" activity
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:" + resources.getString(R.string.contact_email))
        startActivity(intent)
    }

    /**
     * called when phone number is pressed
     */
    fun contactPhone(view: View) {
        // set up and start "dial number" activity
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:" + resources.getString(R.string.contact_telephone))
        startActivity(intent)
    }
}