package com.example.helloing3

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ListGalleryActivity : AppCompatActivity() {

    //private lateinit var binding: ActivityListGalleryBinding
    private lateinit var listAdapter: ListAdapter
    private lateinit var listData: ListData
    private var dataArrayList = ArrayList<ListData?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityListGalleryBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_gallery)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nameList = arrayOf("MBOYI Bienfait", "MISSENGUET BOUKAMBA Cabrel", "Camarade Anonyme",
            "NODJIADOUM Christian", "ZENG KOUEREY Doguy Brahane", "REMADJI Eric",
            "PITIMBAYE Eveline", "ASSOUME IKAPI Fred", "DJEKOURBOUA Janvier",
            "NKILI OBELE Karen Fifi", "BELINGONE Larry", "BEBANE MOUKOUMBI Marina",
            "DZIDJINYO Komlan Maurice", "NZOGHE ASSENGONE Yolande Saly", "AGBOSSA Yao Serge",
            "MOUBAROU MAYOMBO Ted", "BAWANA Théodore")

        val fonctionList = arrayOf("Chef de Classe", "Aucune", "Masquotte",
            "Aucune", "Aucune", "Aucune",
            "Aucune", "Aucune", "Aucune",
            "Sous-chef de classe", "Aucune", "Aucune",
            "Aucune", "Aucune", "Aucune",
            "Aucune", "Aucune")

        val imageList = arrayOf(R.mipmap.bienfait, R.mipmap.cabrel, R.mipmap.camarade_anonyme,
            R.mipmap.christian, R.mipmap.doguy, R.mipmap.eric,
            R.mipmap.eveline, R.mipmap.fred, R.mipmap.janvier,
            R.mipmap.karen, R.mipmap.larry, R.mipmap.marina,
            R.mipmap.maurice, R.mipmap.saly, R.mipmap.serge,
            R.mipmap.ted, R.mipmap.th_odore)

        val classeList = arrayOf(R.string.bienfait_classe, R.string.cabrel_classe,
            R.string.camarade_anonyme_classe, R.string.christian_classe,
            R.string.doguy_classe, R.string.eric_classe, R.string.eveline_classe,
            R.string.fred_classe, R.string.janvier_classe, R.string.karen_classe,
            R.string.larry_classe, R.string.marina_classe, R.string.maurice_classe,
            R.string.saly_classe, R.string.serge_classe, R.string.ted_classe,
            R.string.th_odore_classe)

        val etablissementList = arrayOf(
            R.string.bienfait_etablissement,
            R.string.cabrel_etablissement,
            R.string.camarade_anonyme_etablissement,
            R.string.christian_etablissement,
            R.string.doguy_etablissement,
            R.string.eric_etablissement,
            R.string.eveline_etablissement,
            R.string.fred_etablissement,
            R.string.janvier_etablissement,
            R.string.karen_etablissement,
            R.string.larry_etablissement,
            R.string.marina_etablissement,
            R.string.maurice_etablissement,
            R.string.saly_etablissement,
            R.string.serge_etablissement,
            R.string.ted_etablissement,
            R.string.th_odore_etablissement
        )

        val photosListView : ListView = findViewById(R.id.photos_listView)

        for (i in imageList.indices){
            val currentListData = ListData()

            currentListData.name = nameList[i]
            currentListData.fonction = fonctionList[i]
            currentListData.classe = classeList[i].toString()
            currentListData.etablissement = etablissementList[i].toString()
            currentListData.image = imageList[i]

            dataArrayList.add(currentListData)
        }

        listAdapter = ListAdapter(this@ListGalleryActivity,dataArrayList)
        photosListView.adapter = listAdapter
        photosListView.isClickable = true

        photosListView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l  ->
            val intent = Intent(this@ListGalleryActivity, DetailedListGalleryActivity::class.java)
            intent.putExtra("name", nameList[i])
            intent.putExtra("fonction", fonctionList[i])
            intent.putExtra("classe", classeList[i])
            intent.putExtra("etablissement", etablissementList[i])
            intent.putExtra("image", imageList[i])
            startActivity(intent)
        }
    }
}