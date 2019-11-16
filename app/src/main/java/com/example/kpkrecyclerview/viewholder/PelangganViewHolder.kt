package com.example.kpkrecyclerview.viewholder

import android.view.LayoutInflater
import android.view.TextureView
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kpkrecyclerview.R
import com.example.kpkrecyclerview.model.Pelanggan

class PelangganViewHolder (inflater: LayoutInflater, parent: ViewGroup):
      RecyclerView.ViewHolder(inflater.inflate(R.layout.item_pelanggan, parent, false))

{
    //deklarasi variable untuk memegang komponen pada layout
    var idpel: TextView? = null
    var nama: TextView? = null
    var alamat: TextView? = null
    var trfdya: TextView? = null

    //ambil komponen dari layout, masukkan ke variable
    init {
        idpel = itemView.findViewById(R.id.idpel)
        nama = itemView.findViewById(R.id.nama)
        alamat = itemView.findViewById(R.id.alamat)
        trfdya = itemView.findViewById(R.id.trfdya)
    }
    //binding data pelanggan ke komponen (yang sudah dimasukkan variable)
    fun bind(data: Pelanggan){
        idpel?.text = data.idpel
        nama?.text = data.nama
        alamat?.text = data.alamat
        trfdya?.text = "${data.tarif} / ${data.daya}VA"
    }
}