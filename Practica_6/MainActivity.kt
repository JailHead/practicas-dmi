package com.example.practica6

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var cdato: EditText
    private lateinit var cinfo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        cdato = findViewById(R.id.gdato)
        cinfo = findViewById(R.id.ginfo)
    }

    fun Guardar(vista: View) {
        val texto = cdato.text.toString().trim()

        if (texto.isNotEmpty()) {
            guardar(texto)
            cdato.text.clear()
        } else {
            Toast.makeText(
                this,
                "Por favor ingrese un texto antes de guardar",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun Leer(vista: View) {
        val textoLeido = leer()

        if (textoLeido.isNotEmpty()) {
            cinfo.text = textoLeido
        } else {
            cinfo.text = ""
            Toast.makeText(
                this,
                "No hay información para mostrar",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun VerUbicacion(vista: View) {
        try {
            val rutaSD = baseContext.getExternalFilesDir(null)?.absolutePath
                ?: throw Exception("No se pudo acceder al almacenamiento")

            val carpeta = File(rutaSD, "Carpeta")
            val archivoFisico = File(carpeta, "datos.txt")

            if (!archivoFisico.exists()) {
                Toast.makeText(
                    this,
                    "El archivo aún no existe. Guarda algo primero.",
                    Toast.LENGTH_LONG
                ).show()
                return
            }

            // Intent para abrir el gestor de archivos en la ubicación del archivo
            val intent = Intent(Intent.ACTION_VIEW)
            val uri = FileProvider.getUriForFile(
                this,
                "${applicationContext.packageName}.fileprovider",
                archivoFisico
            )

            intent.setDataAndType(uri, "text/plain")
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

            try {
                startActivity(intent)
            } catch (e: Exception) {
                // Si no hay app para abrir archivos de texto, mostramos la ruta
                mostrarRutaArchivo(carpeta.absolutePath)
            }

        } catch (e: Exception) {
            Toast.makeText(
                this,
                "Error al abrir ubicación: ${e.localizedMessage}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun mostrarRutaArchivo(ruta: String) {
        // Mostrar diálogo con la ruta
        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
        builder.setTitle("Ubicación del Archivo")
        builder.setMessage("Ruta completa:\n\n$ruta/datos.txt\n\nPuedes acceder a esta ubicación desde cualquier gestor de archivos.")
        builder.setPositiveButton("Copiar Ruta") { _, _ ->
            copiarAlPortapapeles(ruta)
        }
        builder.setNegativeButton("Cerrar", null)
        builder.show()
    }

    private fun copiarAlPortapapeles(texto: String) {
        val clipboard = getSystemService(CLIPBOARD_SERVICE) as android.content.ClipboardManager
        val clip = android.content.ClipData.newPlainText("Ruta del archivo", texto)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(this, "Ruta copiada al portapapeles", Toast.LENGTH_SHORT).show()
    }

    private fun guardar(texto: String) {
        try {
            val rutaSD = baseContext.getExternalFilesDir(null)?.absolutePath
                ?: throw Exception("No se pudo acceder al almacenamiento")

            val carpeta = File(rutaSD, "Carpeta").apply {
                if (!exists()) {
                    mkdirs()
                }
            }

            val archivoFisico = File(carpeta, "datos.txt")
            archivoFisico.appendText("$texto\n")

            Toast.makeText(
                this,
                "✓ Dato guardado correctamente",
                Toast.LENGTH_SHORT
            ).show()

        } catch (e: Exception) {
            Toast.makeText(
                this,
                "Error al guardar: ${e.localizedMessage}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun leer(): String {
        return try {
            val rutaSD = baseContext.getExternalFilesDir(null)?.absolutePath
                ?: return ""

            val carpeta = File(rutaSD, "Carpeta")
            val archivoFisico = File(carpeta, "datos.txt")

            if (archivoFisico.exists()) {
                archivoFisico.readText()
            } else {
                Toast.makeText(
                    this,
                    "El archivo no existe",
                    Toast.LENGTH_SHORT
                ).show()
                ""
            }

        } catch (e: Exception) {
            Toast.makeText(
                this,
                "Error al leer: ${e.localizedMessage}",
                Toast.LENGTH_LONG
            ).show()
            ""
        }
    }
}