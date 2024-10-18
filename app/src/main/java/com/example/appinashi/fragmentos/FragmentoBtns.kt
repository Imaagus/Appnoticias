package com.example.appinashi.fragmentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.example.appinashi.R

class FragmentoBtns : Fragment(){
    var listener : FragmentoBtnsInterfaz? = null
    lateinit var btnMostrarContenido1 : ImageButton
    lateinit var btnMostrarContenido2 : ImageButton
    lateinit var btnMostrarContenido3 : ImageButton
    lateinit var btnMostrarContenido4 : ImageButton
    lateinit var btnMostrarContenido5 : ImageButton
    lateinit var btnMostrarContenido6 : ImageButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.primer_fragmento, container, false)
        val view = inflater.inflate(R.layout.fragmento_btns, container, false)
        btnMostrarContenido1 = view.findViewById(R.id.btnFragmento1)
        btnMostrarContenido1.setOnClickListener {
            listener?.mostrarContenido(Fragmento1())
        }
        btnMostrarContenido2 = view.findViewById(R.id.btnFragmento2)
        btnMostrarContenido2.setOnClickListener {
            listener?.mostrarContenido(Fragmento2())
        }
        btnMostrarContenido3 = view.findViewById(R.id.btnFragmento3)
        btnMostrarContenido3.setOnClickListener {
            listener?.mostrarContenido(Fragmento3())
        }
        btnMostrarContenido4 = view.findViewById(R.id.btnFragmento4)
        btnMostrarContenido4.setOnClickListener {
            listener?.mostrarContenido(Fragmento4())
        }
        btnMostrarContenido5 = view.findViewById(R.id.btnFragmento5)
        btnMostrarContenido5.setOnClickListener {
            listener?.mostrarContenido(Fragmento5())
        }
        btnMostrarContenido6 = view.findViewById(R.id.btnFragmento6)
        btnMostrarContenido6.setOnClickListener {
            listener?.mostrarContenido(Fragmento6())
        }
        return view
    }
}