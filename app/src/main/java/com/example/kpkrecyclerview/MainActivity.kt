package com.example.kpkrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kpkrecyclerview.adapter.PelangganAdapter
import com.example.kpkrecyclerview.model.Pelanggan
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class MainActivity : AppCompatActivity() {
    //buat global variable untuk menampung data pelanggan
    var listPlg = mutableListOf<Pelanggan>()
    //untuk dapat di refresh datanya
    val adapterPlg = PelangganAdapter(listPlg)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //panggil fungsi insert database
        initTampilan()

        //menambahkan data secara manual
        //listPlg.add(Pelanggan("4412345678", "Doa Ibu", "Jln asdasd", "R1", "900"))
        //listPlg.add(Pelanggan("4412345675", "Doa Ibu", "Jln asdasd", "R1", "9100"))
        //listPlg.add(Pelanggan("4412345674", "Doa Ibu", "Jln asdasd", "R1", "9200"))
        //listPlg.add(Pelanggan("4412345672", "Doa Ibu", "Jln asdasd", "R1", "9300"))
        //listPlg.add(Pelanggan("4412345671", "Doa Ibu", "Jln asdasd", "R1", "9400"))

        //masukkan data ke recycler view
        rv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PelangganAdapter (listPlg)
        }
    }
    //fungsi insert inputan data untuk ke database
    fun initTampilan(){
        btnsimpan.setOnClickListener{
            database.use {
                insert("pelanggan",
                    "idpel" to idpel.text.toString(),
                    "nama" to nama.text.toString(),
                    "alamat" to alamat.text.toString(),
                    "tarif" to tarif.text.toString(),
                    "daya" to daya.text.toString()
                )
                listPlg.add(Pelanggan(
                    idpel.text.toString(),
                    nama.text.toString(),
                    alamat.text.toString(),
                    tarif.text.toString(),
                    daya.text.toString()

                )
                )

            }
            adapterPlg.notifyDataSetChanged()

        }

    }
    // menampilkan data ketika aplikasi ter pause
    override fun onResume() {
        bacaDatabase()
        super.onResume()
    }

    fun bacaDatabase(){
        database.use {
            select("pelanggan").exec {
                listPlg.clear()
                while (this.moveToNext()){
                    listPlg.add(Pelanggan(
                        getString(getColumnIndex("idpel")),
                        getString(getColumnIndex("nama")),
                        getString(getColumnIndex("alamat")),
                        getString(getColumnIndex("tarif")),
                        getString(getColumnIndex("daya"))


                    )
                    )
                }
                adapterPlg.notifyDataSetChanged()
            }
        }
    }
}

