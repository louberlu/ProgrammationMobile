package com.example.convertnumle

import android.os.Bundle
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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnConvertir : Button = findViewById(R.id.convertir_button)
        val tvFr : TextView = findViewById(R.id.fr_textView)
        val tvEn : TextView = findViewById(R.id.en_textView)
        val tvDe : TextView = findViewById(R.id.de_textView)
        val eTvNumber : EditText = findViewById(R.id.nombre_editTextNumber)

        val convertFr = FrenchConvertNumber()
        val convertEn = EnglishConvertNumber()
        val convertDe = GermanConvertNumber()

        btnConvertir.setOnClickListener {
            tvFr.text = convertFr.convert(eTvNumber.text.toString().toLong())
            tvEn.text = convertEn.convert(eTvNumber.text.toString().toLong())
            tvDe.text = convertDe.convert(eTvNumber.text.toString().toLong())
        }
    }
}