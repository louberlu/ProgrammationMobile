package com.example.helloing3

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailedListGalleryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //binding = ActivityDetailedListGalleryBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_detailed_list_gallery)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val detailedTextView : TextView = findViewById(R.id.detailed_textView)
        val detailFonctionTv : TextView = findViewById(R.id.detailFonction_tv)
        val detailClasseTv : TextView = findViewById(R.id.detailClasse_tv)
        val detailEtaTv : TextView = findViewById(R.id.detailEta_tv)
        val detailedImageView : ImageView = findViewById(R.id.detailed_imageView)

        val intent = this.intent
        if(intent != null){
            val name = intent.getStringExtra("name")
            val fonction = intent.getStringExtra("fonction")
            val classe = intent.getIntExtra("classe", R.string.bienfait_classe)
            val etablissement = intent.getIntExtra("etablissement", R.string.bienfait_etablissement)
            val image = intent.getIntExtra("image", R.mipmap.bienfait)

            detailedTextView.text = name
            detailFonctionTv.text = fonction
            detailClasseTv.setText(classe)
            detailEtaTv.setText(etablissement)
            detailedImageView.setImageResource(image)
        }
    }
}