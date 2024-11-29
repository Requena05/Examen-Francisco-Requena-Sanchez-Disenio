package com.example.examen_francisco_requena_sanchez_disenio

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doOnTextChanged
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var nombrePOKE: TextInputEditText
    private lateinit var layoutNombrePOKE: TextInputLayout
    private lateinit var campoentrenador: TextInputLayout
    private lateinit var fecha: TextInputEditText
    private lateinit var campofecha: TextInputLayout
    private lateinit var entrenador: TextInputEditText
    private lateinit var Estatura: TextInputEditText
    private lateinit var campOEstatura: TextInputLayout

    private lateinit var añadir: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val Spinner: Spinner = findViewById(R.id.tipos)
        nombrePOKE = findViewById(R.id.nombrepokemon)
        layoutNombrePOKE = findViewById(R.id.camponombrepokemon)
        campoentrenador = findViewById(R.id.camponombre_entrenador)
        fecha = findViewById(R.id.fecha)
        campOEstatura = findViewById(R.id.estaturapokemon)
        Estatura = findViewById(R.id.Estatura)
        campofecha = findViewById(R.id.campofecha)
        entrenador = findViewById(R.id.nombre_entrenador)
        añadir = findViewById(R.id.añadir)
        var lista = ArrayList<String>()
        val opciones = arrayOf(
            "Agua",
            "Fuego",
            "Tierra",
            "Electrico",
            " Volador",
            " Fantasma",
            "Normal",
            "Hielo",
            "Dragon",
            "Planta"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opciones)
        Spinner.adapter = adapter

        //El boton añadir se mostrará cuando todos los campos esten llenos y correctos
        if (campofecha.error != null || nombrePOKE.error != null || layoutNombrePOKE.error != null || campoentrenador.error != null) {
            añadir.visibility = View.INVISIBLE
        }else{
            añadir.visibility = View.VISIBLE

            añadir.setOnClickListener {
                //Le pasaremos los datos a la otra activity
                val intent = Intent(this, ListadoPokemon::class.java)
                intent.putExtra("nombre", nombrePOKE.text.toString())
                intent.putExtra("fecha",fecha.text)
                intent.putExtra("estatura", Estatura.text.toString())
                intent.putExtra("tipo", Spinner.selectedItem.toString())
                startActivity(intent)

            }

            fecha.setOnClickListener {
                val buider = MaterialDatePicker.Builder.datePicker()
                val modalQueSeAbre = buider.build()
                modalQueSeAbre.show(supportFragmentManager, "DATE_PICKER")
                modalQueSeAbre.addOnPositiveButtonClickListener {
                    fecha.setText(modalQueSeAbre.headerText)

                    //el usuario no puede ser menor de edad
                    if (modalQueSeAbre.selection!! > System.currentTimeMillis() + 86400000) {
                        campofecha.error = "Fecha no valida"
                    } else {
                        campofecha.error = null
                    }
                }
            }


            nombrePOKE.doOnTextChanged { text, start, count, after ->

                if (nombrePOKE.text.toString().length < 3 || nombrePOKE.text.toString().length > 25) {
                    layoutNombrePOKE.error = "Nombre no permitido"

                } else
                    layoutNombrePOKE.error = null
            }
            //Entrenador: Debe tener al menos una vocal y no podrá tener más de 25
            //caracteres.
            entrenador.doOnTextChanged { text, start, count, after ->
                var cont = 0
                if (entrenador.text.toString().length < 3 && entrenador.text.toString().length > 25 && !entrenador.text.toString()
                        .contains("a") && !entrenador.text.toString()
                        .contains("e") && !entrenador.text.toString()
                        .contains("i") && !entrenador.text.toString().contains("o")
                ) {
                    campoentrenador.error = "Nombre no permitido"
                } else {
                    campoentrenador.error = null
                }
            }


        }
    }
}