package com.nesti.nestimobile.ui.main.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.nesti.nestimobile.R
import com.nesti.nestimobile.ui.main.adapter.MainAdapter
import com.nesti.nestimobile.ui.main.viewmodel.MainViewModel


class ProjectActivity : BaseActivity<MainViewModel>() {
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_project)
        super.onCreate(savedInstanceState)
        title = resources.getString(R.string.project_title)
    }

    override fun setupUI() {
    }

    override fun setupViewModel() {
    }

    fun contactPhone(view: View) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(resources.getString(R.string.nesti_web_site))
        startActivity(intent)
    }

}