package com.tdgroup.cicdexample

import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class MainActivityTest {
    lateinit var mActivity : MainActivity
    lateinit var valueX : EditText
    lateinit var valueY : EditText
    lateinit var result : TextView
    lateinit var addButton : Button
    @Before
    fun setup(){
        mActivity = Robolectric.buildActivity(MainActivity::class.java).create().get()
        valueX = mActivity.findViewById<EditText>(R.id.edt_x)
        valueY = mActivity.findViewById<EditText>(R.id.edt_y)
        result = mActivity.findViewById<TextView>(R.id.tv_sum)
        addButton = mActivity.findViewById<Button>(R.id.btn_ok)
    }
    @Test
    fun testNotNull(){
        assertNotNull(valueX);
        assertNotNull(valueY);
        assertNotNull(result);
        assertNotNull(addButton);
        assertNotNull(mActivity);
    }
    @Test
    fun testInputValue(){
        assertEquals(TextUtils.isDigitsOnly(valueX.getText()),true)
        assertEquals(TextUtils.isDigitsOnly(valueY.getText()),true);
    }

    @Test
    fun testResult(){
        valueX.setText(8)
        valueY.setText(8)
        addButton.performClick()
    }



}