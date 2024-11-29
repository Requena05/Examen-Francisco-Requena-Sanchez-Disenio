package com.example.examen_francisco_requena_sanchez_disenio

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ListadoPokemon : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_listado_pokemon)
        val nombre = intent.getStringExtra("nombre")
        val tipo = intent.getStringExtra("tipo")
        val estatura= intent.getStringExtra("estatura")
        val fecha_atrapado=intent.getStringExtra("fecha")
        val textView = findViewById<TextView>(R.id.verdatos)
        textView.text = "Nombre: $nombre, Tipo: $tipo, Estatura: $estatura, Fecha atrapado: $fecha_atrapado"
        //Guarda los pokemon en un array

     }
    }

