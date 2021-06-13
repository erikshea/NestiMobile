package com.nesti.nestimobile.ui.main.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.nesti.nestimobile.R
import com.nesti.nestimobile.ui.main.view.base.BaseActivity

/**
 * projects section
 */
class ProjectActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_project)
        super.onCreate(savedInstanceState)
        title = resources.getString(R.string.project_title)
    }

    override fun setupUi() {
    }

    /**
     * method called when company logo is pressed
     */
    fun launchSite(view: View) {
        // set up and start "view website" activity
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(resources.getString(R.string.nesti_web_site))
        startActivity(intent)
    }

}