package com.example.coffescript

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels


class Skip_frag_one : Fragment() {

    val dataModel: DataModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { }
    }

    @SuppressLint("MissingInflatedId", "ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflatere = inflater.inflate(R.layout.fragment_skip_frag_one, container, false)

        var liner_1: View = inflatere.findViewById(R.id.liner_1)
        var liner_2: View = inflatere.findViewById(R.id.liner_2)
        var liner_3: View = inflatere.findViewById(R.id.liner_3)

        var pose = 0

        var background_image: ImageView = inflatere.findViewById(R.id.background_image)
        var shadow_back: ImageView = inflatere.findViewById(R.id.shadow_back)
        var main_skip_text: TextView = inflatere.findViewById(R.id.main_text_skip)
        var skip_text: TextView = inflatere.findViewById(R.id.skip_text)
        var button_register: Button = inflatere.findViewById(R.id.button_register)
        var button_started: Button = inflatere.findViewById(R.id.button_get_started)


        button_register.alpha = 0.0f
        button_started.alpha = 0.0f

        button_started.isVisible = false
        button_register.isVisible = false


        shadow_back.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    if(pose == 0){

                        liner_1.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#D9D9D9"))
                        liner_2.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#CE9760"))

                        liner_1.animate().scaleX(0.5f).setDuration(1000).start()
                        liner_2.animate().scaleX(2f).setDuration(1000).start()
                        liner_3.animate().translationX(+20f).setDuration(1000).start()

                        background_image.animate().alpha(0.5f).setDuration(200).start()

                        Handler().postDelayed({
                            background_image.setImageResource(R.drawable.skip_two)
                            shadow_back.setImageResource(R.drawable.shadow_skip_one)
                            background_image.animate().alpha(1f).setDuration(200).start()

                            main_skip_text.text = "Flavorful bean journey"
                        }, 200)

                        pose++
                    }else if(pose == 1){
                        liner_2.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#D9D9D9"))
                        liner_3.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#CE9760"))

                        liner_2.animate().scaleX(1.2f).setDuration(1000).start()
                        liner_3.animate().scaleX(2f).setDuration(1000).start()
                        liner_2.animate().translationX(-10f).setDuration(1000).start()
                        liner_3.animate().translationX(+15f).setDuration(1000).start()

                        background_image.animate().alpha(0.5f).setDuration(200).start()

                        Handler().postDelayed({
                            background_image.setImageResource(R.drawable.skip_three)
                            shadow_back.setImageResource(R.drawable.shadow_skip_one)
                            background_image.animate().alpha(1f).setDuration(200).start()

                            main_skip_text.text = "Unlock bean secrets"

                            button_started.isVisible = true
                            button_register.isVisible = true

                            button_started.animate().alpha(1.0f).setDuration(300).start()
                            button_register.animate().alpha(1.0f).setDuration(300).start()

                        }, 200)

                        pose++
                    }else if(pose == 2){
                        background_image.setImageResource(R.drawable.skip_three)
                        shadow_back.setImageResource(R.drawable.shadow_skip_one)
                    }
                    true
                }

                else -> false
            }
        }

        skip_text.setOnTouchListener { v, event ->
            skip_text.isEnabled = false
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    liner_1.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#D9D9D9"))
                    liner_1.animate().scaleX(0.5f).setDuration(1000).start()


                    liner_2.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#D9D9D9"))
                    liner_3.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#CE9760"))

                    liner_2.animate().scaleX(1.2f).setDuration(1000).start()
                    liner_3.animate().scaleX(2f).setDuration(1000).start()
                    liner_2.animate().translationX(-10f).setDuration(1000).start()
                    liner_3.animate().translationX(+15f).setDuration(1000).start()

                    background_image.animate().alpha(0.5f).setDuration(200).start()

                    Handler().postDelayed({
                        main_skip_text.text = "Unlock bean secrets"

                        background_image.setImageResource(R.drawable.skip_three)
                        shadow_back.setImageResource(R.drawable.shadow_skip_one)
                        background_image.animate().alpha(1f).setDuration(200).start()

                        button_started.isVisible = true
                        button_register.isVisible = true

                        button_started.animate().alpha(1.0f).setDuration(300).start()
                        button_register.animate().alpha(1.0f).setDuration(300).start()
                    }, 200)

                    pose = 2

                    if(pose == 2){
                        background_image.setImageResource(R.drawable.skip_three)
                        shadow_back.setImageResource(R.drawable.shadow_skip_one)
                    }

                    true
                }
                else -> false
            }
        }

        button_started.setOnClickListener{
            dataModel.message_sing_frag.value = true
        }

        button_register.setOnClickListener{
            dataModel.message_register_frag.value = true
        }


        return inflatere
    }

    companion object {

        @JvmStatic
        fun newInstance() = Skip_frag_one()
    }
}