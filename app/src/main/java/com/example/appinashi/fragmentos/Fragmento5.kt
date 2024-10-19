package com.example.appinashi.fragmentos

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.appinashi.apiRequest.PostEndPoint
import com.example.appinashi.R
import com.example.appinashi.apiRequest.ResponseData
import com.example.appinashi.apiRequest.RetroFitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Fragmento5 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragmento5, container, false)


        val categoria = arguments?.getString("categoria") ?: "general"

        val accessKey = "1f196924ee3db7bcd5de96a6ba5945f0"
        val countries = "ar,us,gb"
        val language = "es, en"

        val api = RetroFitClient.retrofit.create(PostEndPoint::class.java)
        val callPost = api.getPosts(accessKey, categoria, countries, language)


        callPost.enqueue(object : Callback<ResponseData> {
            override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                if (response.isSuccessful && response.body() != null) {
                    val posts = response.body()!!.data
                    if (posts.isNotEmpty()) {
                        val linearLayoutContent = view.findViewById<LinearLayout>(R.id.linearLayoutContent)
                        linearLayoutContent.removeAllViews()

                        posts.forEach { post ->
                            val titleTextView = TextView(requireContext())
                            titleTextView.text = "● ${post.title}"
                            titleTextView.textSize = 22f
                            titleTextView.setPadding(16, 16, 16, 8)

                            val descriptionTextView = TextView(requireContext())
                            descriptionTextView.text = post.description ?: "Descripción no disponible"
                            descriptionTextView.textSize = 16f
                            descriptionTextView.setPadding(16, 8, 16, 16)

                            linearLayoutContent.addView(titleTextView)
                            linearLayoutContent.addView(descriptionTextView)
                        }
                    } else {
                        Log.e("Response", "No hay posts disponibles.")
                        view.findViewById<TextView>(R.id.tvResetService).apply {
                            text = "No hay posts disponibles."
                            visibility = View.VISIBLE
                        }
                    }
                } else {
                    Log.e("Response", "Error en la respuesta: ${response.code()} - ${response.message()}")
                    view.findViewById<TextView>(R.id.tvResetService).apply {
                        text = "Error: ${response.code()} - ${response.message()}"
                        visibility = View.VISIBLE
                    }
                }
            }

            override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                Log.e("ERROR", t.message ?: "Error desconocido")
                view.findViewById<TextView>(R.id.tvResetService).apply {
                    text = "ERROR: ${t.message}"
                    visibility = View.VISIBLE
                }
            }
        })
        return view
    }
}