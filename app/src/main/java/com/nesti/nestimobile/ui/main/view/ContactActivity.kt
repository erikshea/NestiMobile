package com.nesti.nestimobile.ui.main.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.nesti.nestimobile.R
import com.nesti.nestimobile.ui.main.adapter.MainAdapter
import com.nesti.nestimobile.ui.main.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class ContactActivity : BaseActivity<MainViewModel>() {
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_contact)
        super.onCreate(savedInstanceState)
        title = resources.getString(R.string.contact_title)
    }

    override fun setupUI() {

    }

    override fun setupViewModel() {
    }

    fun contactEmail(view: View) {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:" + resources.getString(R.string.contact_email))
        startActivity(intent)
    }

    fun contactPhone(view: View) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:" + resources.getString(R.string.contact_telephone))
        startActivity(intent)
    }
}