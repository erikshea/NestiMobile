package com.nesti.nestimobile.ui.main.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.nesti.nestimobile.R
import com.nesti.nestimobile.ui.main.view.base.BaseActivity

/**
 * team section
 */
class TeamActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_team)
        super.onCreate(savedInstanceState)
        title = resources.getString(R.string.team_title)
    }

    override fun setupUi() {}
}