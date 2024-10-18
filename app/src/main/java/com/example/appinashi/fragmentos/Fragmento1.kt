package com.example.appinashi.fragmentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.appinashi.R

class Fragmento1 : Fragment() {
    lateinit var btnPrueba: Button
    lateinit var tvPrueba: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragmento1, container, false)
        btnPrueba = view.findViewById(R.id.btnPrueba)
        tvPrueba = view.findViewById(R.id.tvPrueba)
        btnPrueba.setOnClickListener {
            tvPrueba.text = "Hola esta es una prueba"
        }
        return view
    }
}