package com.example.helloing3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
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
        val btnGallery : Button = this.findViewById(R.id.gallery_button)
        val btnSubjects : Button = this.findViewById(R.id.subject_button)
        val btnSendEmail : Button = this.findViewById(R.id.sendEmail_button)
        val tvSalutation : TextView = this.findViewById(R.id.salutation_textView)

        btnGallery.setOnClickListener {
            tvSalutation.setText("Welcome to Gallery")
            val intent = Intent(this, GalleryActivity::class.java)
            startActivity(intent)
        }

        btnSubjects.setOnClickListener {
            tvSalutation.setText("Welcome to Subjects")
            val intent = Intent(this, SubjectsActivity::class.java)
            startActivity(intent)
        }

        btnSendEmail.setOnClickListener {
            this.sendEmail(arrayOf("obeleloulou@gmail.com", "obele.karen@gmail.com"))
        }
    }

    fun sendEmail(emailAddresses: kotlin.Array<String>){
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_EMAIL, emailAddresses)
        intent.putExtra(Intent.EXTRA_SUBJECT, "Cours de programmation mobile - Test")
        intent.putExtra(Intent.EXTRA_TEXT, "How are you?")

        if (intent.resolveActivity(packageManager) != null){
            startActivity(intent)
        }
    }
}