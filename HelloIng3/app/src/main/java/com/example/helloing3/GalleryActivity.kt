package com.example.helloing3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GalleryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_gallery)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnAjouter : Button = this.findViewById(R.id.ajouter_button)
        val btnConsulter : Button = this.findViewById(R.id.consulter_button)

        btnAjouter.setOnClickListener {
            val intent = Intent(this, AddGalleryActivity::class.java)
            startActivity(intent)
        }

        btnConsulter.setOnClickListener {
            val intent = Intent(this, ListGalleryActivity::class.java)
            startActivity(intent)
        }
    }
}