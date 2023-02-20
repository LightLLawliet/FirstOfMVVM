package com.example.firstofmvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    init {
        Log.d("asd123", "new instance ${hashCode()}")
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = (application as HelloWorldApp).viewModel
        val input = findViewById<TextInputLayout>(R.id.inputLayout)
        val editText = input.editText!!
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            viewModel.login(editText.text.toString())
        }

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.clearEmail(s.toString())
            }
        })

        viewModel.observe(object : UiStateCallback {
            override fun postEmail(value: String) {
                editText.setText(value)
            }

            override fun postSuccess() {
                input.isErrorEnabled = true
                input.error = ""
            }

            override fun postError(message: String) {
                input.isErrorEnabled = true
                input.error = message
            }
        })

        viewModel.init(savedInstanceState == null)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clear()
    }
}