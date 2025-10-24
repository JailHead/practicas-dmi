package com.example.practica5

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var cfecha: EditText
    private lateinit var chora: EditText
    private lateinit var calendar: Calendar

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        cfecha = findViewById<EditText>(R.id.gfecha)
        chora = findViewById<EditText>(R.id.ghora)
        calendar = Calendar.getInstance()
    }

    public fun seleccionarFecha(view: View) {
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateFecha()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    public fun seleccionarHora(view: View) {
        val timePickerDialog = TimePickerDialog(
            this,
            { _, hourOfDay, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)
                updateHora()
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true // true para formato 24 horas, false para formato 12 horas AM/PM
        )
        timePickerDialog.show()
    }

    private fun updateFecha() {
        val formato = "dd/MMM/yyyy"
        val sdf = SimpleDateFormat(formato, Locale.US)
        cfecha.setText(sdf.format(calendar.time))
    }

    private fun updateHora() {
        val formato = "HH:mm"
        val sdf = SimpleDateFormat(formato, Locale.US)
        chora.setText(sdf.format(calendar.time))
    }
}