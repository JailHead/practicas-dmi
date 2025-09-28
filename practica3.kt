package com.example.practica3

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var cnum1: EditText
    lateinit var cnum2: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        cnum1 = findViewById<EditText>(R.id.gnum1)
        cnum2 = findViewById<EditText>(R.id.gnum2)
    }

    private fun operation(): Int {
        var num1: Int
        var num2: Int

        num1 = Integer.parseInt(cnum1.text.toString())
        num2 = Integer.parseInt(cnum2.text.toString())

        return num1 + num2
    }

    public fun button(view: View) {
        if (cnum1.text.toString().isEmpty()) {
            Toast.makeText(this, "Please enter a number in the first field", Toast.LENGTH_SHORT).show()
            return
        }

        if (cnum2.text.toString().isEmpty()) {
            Toast.makeText(this, "Please enter a number in the second field", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            var result: Int
            result = operation()
            Toast.makeText(this, "THE RESULT IS: $result", Toast.LENGTH_SHORT).show()
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Please enter valid numbers only", Toast.LENGTH_SHORT).show()
        }
    }
}
