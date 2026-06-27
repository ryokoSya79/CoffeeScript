package com.example.coffescript

import android.annotation.SuppressLint
import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.core.view.isGone
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels

class Get_start_frag : Fragment() {

    val dataModel: DataModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflatere = inflater.inflate(R.layout.fragment_get_start_frag, container, false)

        var image_get_start: ImageView = inflatere.findViewById(R.id.imageView)
        image_get_start.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
        var coffeesctipt_logo: ImageView = inflatere.findViewById(R.id.coffeesctipt_logo)

        var button_start: Button = inflatere.findViewById(R.id.button_start)

        button_start.setOnClickListener{
            coffeesctipt_logo.isGone = true
            dataModel.message_start_frag.value = true
        }

        return inflatere
    }

    companion object {
        @JvmStatic
        fun newInstance() = Get_start_frag()
    }
}