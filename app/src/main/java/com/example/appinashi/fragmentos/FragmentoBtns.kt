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
        val view = inflater.inflate(R.layout.fragmento_btns, container, false)
        btnMostrarContenido1 = view.findViewById(R.id.btnFragmento1)
        btnMostrarContenido1.setOnClickListener {
            val fragment = Fragmento1()
            val bundle = Bundle()
            bundle.putString("categoria", "business")
            fragment.arguments = bundle
            listener?.mostrarContenido(fragment)
        }

        btnMostrarContenido2 = view.findViewById(R.id.btnFragmento2)
        btnMostrarContenido2.setOnClickListener {
            val fragment = Fragmento2()
            val bundle = Bundle()
            bundle.putString("categoria", "entertainment")
            fragment.arguments = bundle
            listener?.mostrarContenido(fragment)
        }
        btnMostrarContenido3 = view.findViewById(R.id.btnFragmento3)
        btnMostrarContenido3.setOnClickListener {
            val fragment = Fragmento3()
            val bundle = Bundle()
            bundle.putString("categoria", "health")
            fragment.arguments = bundle
            listener?.mostrarContenido(fragment)
        }
        btnMostrarContenido4 = view.findViewById(R.id.btnFragmento4)
        btnMostrarContenido4.setOnClickListener {
            val fragment = Fragmento4()
            val bundle = Bundle()
            bundle.putString("categoria", "science")
            fragment.arguments = bundle
            listener?.mostrarContenido(fragment)
        }
        btnMostrarContenido5 = view.findViewById(R.id.btnFragmento5)
        btnMostrarContenido5.setOnClickListener {
            val fragment = Fragmento5()
            val bundle = Bundle()
            bundle.putString("categoria", "sports")
            fragment.arguments = bundle
            listener?.mostrarContenido(fragment)
        }
        btnMostrarContenido6 = view.findViewById(R.id.btnFragmento6)
        btnMostrarContenido6.setOnClickListener {
            val fragment = Fragmento6()
            val bundle = Bundle()
            bundle.putString("categoria", "technology")
            fragment.arguments = bundle
            listener?.mostrarContenido(fragment)
        }
        return view
    }
}