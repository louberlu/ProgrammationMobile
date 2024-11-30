package com.example.calculatrice

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import net.objecthunter.exp4j.ExpressionBuilder

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

        val septButton : Button = findViewById(R.id.sept_button)
        val huitButton : Button = findViewById(R.id.huit_button)
        val neufButton : Button = findViewById(R.id.neuf_button)
        val quatreButton : Button = findViewById(R.id.quatre_button)
        val cinqButton : Button = findViewById(R.id.cinq_button)
        val sixButton : Button = findViewById(R.id.six_button)
        val troisButton : Button = findViewById(R.id.trois_button)
        val deuxButton : Button = findViewById(R.id.deux_button)
        val unButton : Button = findViewById(R.id.un_button)
        val zeroButton : Button = findViewById(R.id.zero_button)
        val tvCalcul : TextView = findViewById(R.id.calcul_textView)
        val tvres : TextView = findViewById(R.id.res_textView)
        val btmoins : Button = findViewById(R.id.moins_button)
        val btplus : Button = findViewById(R.id.plus_button)
        val bttimes : Button = findViewById(R.id.times_button)
        val btdivise : Button = findViewById(R.id.divise_button)
        val btmodulo : Button = findViewById(R.id.modulo_button)
        val btegal : Button = findViewById(R.id.egal_button)
        val cbutton : Button = findViewById(R.id.c_button)
        val btdot : Button = findViewById(R.id.dot_button)

        btegal.setOnClickListener {
            val exp = tvCalcul.text.toString()
            val eval = ExpressionBuilder(exp).build()
            val res = eval.evaluate()
            clear(tvres)
            affiche(res.toString(), tvres)
            clear(tvCalcul)
            affiche(res.toString(), tvCalcul)
        }
        cbutton.setOnClickListener {
            this.clear(tvCalcul)
        }
        septButton.setOnClickListener {
            this.affiche(septButton.text.toString(), tvCalcul)
        }
        huitButton.setOnClickListener {
            this.affiche(huitButton.text.toString(), tvCalcul)
        }
        neufButton.setOnClickListener {
            this.affiche(neufButton.text.toString(), tvCalcul)
        }
        quatreButton.setOnClickListener {
            this.affiche(quatreButton.text.toString(), tvCalcul)
        }
        cinqButton.setOnClickListener {
            this.affiche(cinqButton.text.toString(), tvCalcul)
        }
        sixButton.setOnClickListener {
            this.affiche(sixButton.text.toString(), tvCalcul)
        }
        unButton.setOnClickListener {
            this.affiche(unButton.text.toString(), tvCalcul)
        }
        zeroButton.setOnClickListener {
            this.affiche(zeroButton.text.toString(), tvCalcul)
        }
        deuxButton.setOnClickListener {
            this.affiche(deuxButton.text.toString(), tvCalcul)
        }
        troisButton.setOnClickListener {
            this.affiche(troisButton.text.toString(), tvCalcul)
        }
        btmoins.setOnClickListener {
            this.affiche(btmoins.text.toString(), tvCalcul)
        }
        btplus.setOnClickListener {
            this.affiche(btplus.text.toString(), tvCalcul)
        }
        bttimes.setOnClickListener {
            this.affiche(bttimes.text.toString(), tvCalcul)
        }
        btdivise.setOnClickListener {
            this.affiche(btdivise.text.toString(), tvCalcul)
        }
        btmodulo.setOnClickListener {
            this.affiche(btmodulo.text.toString(), tvCalcul)
        }
        btdot.setOnClickListener {
            this.affiche(btdot.text.toString(), tvCalcul)
        }
    }

    fun affiche(exp: CharSequence, tvCalcul: TextView){
        tvCalcul.setText(tvCalcul.text.toString()+exp)
    }
    fun clear(tv: TextView){
        tv.setText("")
    }
}
