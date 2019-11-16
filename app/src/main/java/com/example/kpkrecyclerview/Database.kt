package com.example.kpkrecyclerview

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper
import org.jetbrains.anko.db.PRIMARY_KEY
import org.jetbrains.anko.db.TEXT
import org.jetbrains.anko.db.createTable
import org.w3c.dom.Text

class Database(ctx: Context):
        ManagedSQLiteOpenHelper(ctx, "kpk", null, 1) {

            //membuat companion object agar objek database bisa di akses oleh semua aktivity
            //menggunakan objek database yang sama
            companion object {
                private var instance : Database? = null

                @Synchronized
                fun getInstance(ctx: Context): Database{
                    if (instance == null){
                        instance = Database(ctx.applicationContext)
                    }
                    return  instance!!
                }
            }
        override fun onCreate(db: SQLiteDatabase?) {

            //membuat table database
            db?.createTable("pelanggan", true, "idpel" to TEXT + PRIMARY_KEY,
                "nama" to TEXT,
                "alamat" to TEXT,
                "tarif" to TEXT,
                "daya" to TEXT
                )
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        }
    }
    //akses property dari context
    val Context.database: Database
        get() = Database.getInstance(applicationContext)