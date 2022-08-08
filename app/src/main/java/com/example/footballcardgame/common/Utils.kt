package com.example.footballcardgame.common

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.footballcardgame.R

object Utils {
    fun replaceFragment(someFragment: Fragment?, fragmentManager: FragmentManager) {
        val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
        if (someFragment != null) {
            transaction.replace(R.id.nav_host_fragment_content_main, someFragment)
        }
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun EditText.afterTextChanged(afterTextChanged: (String) -> (Unit)) {
        this.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                afterTextChanged.invoke(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
        })
    }

    fun EditText.validate(message: String, validator: (String) -> Boolean) {
        this.afterTextChanged {
            this.error = if (validator(it)) null else message
        }
        this.error = if (validator(this.text.toString())) null else message
    }
}