package com.example.practica4

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var cnum1: EditText
    lateinit var cnum2: EditText
    lateinit var cresult: TextView

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
        cresult = findViewById<TextView>(R.id.gresult)
    }

    private fun isEmptyField(editText: EditText): Boolean {
        return editText.text.toString().isEmpty()
    }

    private fun isValidRange1(value: Int): Boolean {
        return value in 1..50
    }

    private fun isValidRange2(value: Double): Boolean {
        return value in 7.5..12.3
    }

    public fun aceptar(view: View) {
        cresult.text = ""

        if (isEmptyField(cnum1)) {
            Toast.makeText(this, "Error: Number 1 field is empty", Toast.LENGTH_SHORT).show()
            return
        }

        if (isEmptyField(cnum2)) {
            Toast.makeText(this, "Error: Number 2 field is empty", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            var num1: Int = Integer.parseInt(cnum1.text.toString())
            var num2: Double = cnum2.text.toString().toDouble()

            if (!isValidRange1(num1)) {
                Toast.makeText(this, "Error: Number 1 must be between 1 and 50", Toast.LENGTH_SHORT).show()
                return
            }

            if (!isValidRange2(num2)) {
                Toast.makeText(this, "Error: Number 2 must be between 7.5 and 12.3", Toast.LENGTH_SHORT).show()
                return
            }

            var res: Double = num1 + num2
            cresult.text = res.toString()

        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Error: Please enter valid numbers", Toast.LENGTH_SHORT).show()
        }
    }
}