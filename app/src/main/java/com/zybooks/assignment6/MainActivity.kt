package com.zybooks.assignment6

import android.os.Bundle
import android.widget.AutoCompleteTextView.Validator
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val edName = findViewById<EditText>(R.id.editName)
        val btuShowName = findViewById<Button>(R.id.button)
        val result = findViewById<TextView>(R.id.showNameTextView)

        btuShowName.setOnClickListener{
            val name = edName.text.toString()
            if (name.isNotEmpty()){
                result.text = "Hello: $name"
            }else{
                result.text = "Please Enter Your Name!!"
            }
        }
    }
}