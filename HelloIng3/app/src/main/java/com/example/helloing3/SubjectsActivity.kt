package com.example.helloing3

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SubjectsActivity : AppCompatActivity() {
    private lateinit var listAdapter: ListAdapterSub
    private lateinit var listData: ListDataSub
    private var dataArrayList = ArrayList<ListDataSub?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_subjects)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val subjectList = arrayOf("Analyse des données", "BDD Avancées", "Conduite Projets Informatiques",
            "Informatique Décisionnelle Introduction", "Installation et Administration Réseau",
            "Interconnection et réseaux haut débit+MSR", "Méthodes formelle de conception",
            "Programmation Mobile", "Programmation système", "Qualité logiciel", "IoT",
            "SIG", "Techniques contractuelles")

        val heureList = arrayOf("40", "40", "30", "40", "70", "60",
            "40", "40", "40", "30", "30", "50", "30")

        val coeffList = arrayOf("4", "4", "3", "4", "6", "5", "4", "4", "4", "3", "2", "5", "2")

        val subjectListView : ListView = findViewById(R.id.subject_listView)
        for(i in subjectList.indices){
            val currentListDataSub = ListDataSub()
            currentListDataSub.subject = subjectList[i]
            currentListDataSub.heure = heureList[i]
            currentListDataSub.coeff = coeffList[i]

            dataArrayList.add(currentListDataSub)
        }

        listAdapter = ListAdapterSub(this@SubjectsActivity,dataArrayList)
        subjectListView.adapter = listAdapter
    }
}