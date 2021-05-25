package com.tdgroup.cicdexample

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private var valueX: EditText? = null
    private  var valueY:EditText? = null
    private var result: TextView? = null
    private var btnSum: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        btnSum?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                if (TextUtils.isDigitsOnly(valueX!!.text) && TextUtils.isDigitsOnly(valueY!!.getText())) {
                    Toast.makeText(this@MainActivity, "Tinh Tong", Toast.LENGTH_SHORT).show()
                    sum()
                }
            }
        })
    }

    private fun initView() {
        valueX = findViewById<View>(R.id.edt_x) as EditText
        valueY = findViewById<View>(R.id.edt_y) as EditText
        result = findViewById<View>(R.id.tv_sum) as TextView
        btnSum = findViewById<View>(R.id.btn_ok) as Button
    }

    private fun sum() {
        val val1 = valueX!!.text.toString().toInt()
        val val2: Int = valueY!!.getText().toString().toInt()
        val answer = val1 + val2
        result!!.text = answer.toString() + ""
    }
}