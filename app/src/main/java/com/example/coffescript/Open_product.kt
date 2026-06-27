package com.example.coffescript

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.findViewTreeFullyDrawnReporterOwner
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels


class Open_product : Fragment() {

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
        val inflatere = inflater.inflate(R.layout.fragment_open_product, container, false)

        var back_button: ImageView = inflatere.findViewById(R.id.back_button)
        var minus_button: ImageView = inflatere.findViewById(R.id.button_minus)
        var plus_button: ImageView = inflatere.findViewById(R.id.button_plus)
        var number_colls: TextView = inflatere.findViewById(R.id.number_cols)
        var name_coffee: TextView = inflatere.findViewById(R.id.name_coffee_open_product)
        var open_product_price: TextView = inflatere.findViewById(R.id.open_product_price)

        var button_small_size: ImageView = inflatere.findViewById(R.id.button_small_size)
        var button_medium_size: ImageView = inflatere.findViewById(R.id.button_medium_size)
        var button_large_size: ImageView = inflatere.findViewById(R.id.button_large_size)
        var text_small_size: TextView = inflatere.findViewById(R.id.text_small_size)
        var text_large_size: TextView = inflatere.findViewById(R.id.text_large_size)
        var text_medium_size: TextView = inflatere.findViewById(R.id.text_medium_size)

        var back_ingredients: ImageView = inflatere.findViewById(R.id.back_ingredients)
        var image_ingredients: ImageView = inflatere.findViewById(R.id.image_ingredients)
        var back_ingredients_2: ImageView = inflatere.findViewById(R.id.back_ingredients_2)
        var image_ingredients_2: ImageView = inflatere.findViewById(R.id.image_ingredients_2)
        var lemon_ingredients: ImageView = inflatere.findViewById(R.id.lemon_igredients)
        var lemon_ingredients_2: ImageView = inflatere.findViewById(R.id.lemon_igredients_2)
        var none_ingredients: ImageView = inflatere.findViewById(R.id.none_igredients)
        var none_ingredients_2: ImageView = inflatere.findViewById(R.id.none_igredients_2)

        var textView5: TextView = inflatere.findViewById(R.id.textView5)
        var textView8: TextView = inflatere.findViewById(R.id.textView8)
        var imageView24: ImageView = inflatere.findViewById(R.id.imageView24)


        var button_add_cart: Button = inflatere.findViewById(R.id.button_add_cart)
        var id_image: String = "0"

        var text_igredients: TextView = inflatere.findViewById(R.id.text_igredients)


        var price = 0
        var base_price = 0
        var new_price = 0
        var int_colls = 1

        var pose_ingredients = 0

        var proverka = false
        var proverka_2 = false

        none_ingredients_2.animate().alpha(0.0f).start()
        lemon_ingredients_2.animate().alpha(0.0f).start()

        dataModel.message_favorite_track.observe(this, {
            proverka = it
        })

        back_button.setOnClickListener{
            if(proverka){
                dataModel.message_favorite.value = true
            }else{
                dataModel.message_home_frag.value = true
            }
            Log.d("DB_CART",
                "Список всего и вся: ${proverka}"
            )
        }

        minus_button.setOnClickListener{
            if(int_colls > 1){
                int_colls--
                number_colls.text = int_colls.toString()
            }
            new_price = price * int_colls

            open_product_price.text = new_price.toString()
        }

        plus_button.setOnClickListener{
            if(int_colls < 20){
                int_colls++
                number_colls.text = int_colls.toString()
            }

            new_price = price * int_colls

            open_product_price.text = new_price.toString()
        }

        dataModel.name_and_price.observe(this, {
            price = it[1].toInt()
            base_price = it[1].toInt()
            new_price = it[1].toInt()

            open_product_price.text = price.toString()
            name_coffee.text = it[0]

            id_image = it[2]
        })


        button_small_size.setOnClickListener{
            button_small_size.setImageResource(R.drawable.button_small_size_press)
            button_medium_size.setImageResource(R.drawable.button_medium_size)
            button_large_size.setImageResource(R.drawable.button_large_size)

            text_small_size.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            text_medium_size.setTextColor(ContextCompat.getColor(requireContext(), R.color.button_color))
            text_large_size.setTextColor(ContextCompat.getColor(requireContext(), R.color.button_color))

            price = (base_price - 5)
            new_price = (base_price - 5) * int_colls

            open_product_price.text = new_price.toString()
        }

        button_medium_size.setOnClickListener{
            button_small_size.setImageResource(R.drawable.button_small_size)
            button_medium_size.setImageResource(R.drawable.button_medium_size_press)
            button_large_size.setImageResource(R.drawable.button_large_size)

            text_small_size.setTextColor(ContextCompat.getColor(requireContext(), R.color.button_color))
            text_medium_size.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            text_large_size.setTextColor(ContextCompat.getColor(requireContext(), R.color.button_color))

            price = base_price
            new_price = base_price * int_colls
            open_product_price.text = new_price.toString()
        }

        button_large_size.setOnClickListener{
            button_small_size.setImageResource(R.drawable.button_small_size)
            button_medium_size.setImageResource(R.drawable.button_medium_size)
            button_large_size.setImageResource(R.drawable.button_large_size_press)

            text_small_size.setTextColor(ContextCompat.getColor(requireContext(), R.color.button_color))
            text_medium_size.setTextColor(ContextCompat.getColor(requireContext(), R.color.button_color))
            text_large_size.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

            price = (base_price + 5)
            new_price = (base_price + 5) * int_colls

            open_product_price.text = new_price.toString()


        }

        back_ingredients.setOnClickListener{
//            back_ingredients.animate().translationX(-405f).setDuration(5000).start()
//            back_ingredients.animate().translationY(125f).setDuration(5000).start()
        }



//        val gestureDetector = GestureDetectorCompat(requireContext(), MyGestureListener(lemon_ingredients, back_ingredients, image_ingredients,  none_ingredients, none_ingredients_2, pose_ingredients))
//
//        val gestureDetector_2 = GestureDetectorCompat(requireContext(), MyGestureListener(back_ingredients, image_ingredients, lemon_ingredients, none_ingredients, none_ingredients_2, pose_ingredients))


        back_ingredients.setOnTouchListener(object : OnSwipeTouchListener(context) {
            override fun onSwipeLeft() {
                //обработка свайпа влево
                if(pose_ingredients == 0) {
                    image_ingredients.animate().translationX(-405f).setDuration(500).start()
                    image_ingredients.animate().translationY(125f).setDuration(500).start()
                    back_ingredients.animate().translationX(-405f).setDuration(500).start()
                    back_ingredients.animate().translationY(125f).setDuration(500).start()

                    image_ingredients_2.animate().translationX(0.0f).setDuration(0).start()
                    image_ingredients_2.animate().translationY(0.0f).setDuration(0).start()
                    back_ingredients_2.animate().translationX(0.0f).setDuration(0).start()
                    back_ingredients_2.animate().translationY(0.0f).setDuration(0).start()

                    lemon_ingredients.animate().translationX(-405f).setDuration(500).start()
                    lemon_ingredients.animate().translationY(-125f).setDuration(500).start()

//                        none_image.animate().translationX(-405f).setDuration(500).start()
//                        none_image.animate().translationY(-125f).setDuration(500).start()

                    none_ingredients_2.animate().alpha(1.0f).setDuration(400).start()
                    none_ingredients.animate().alpha(0.0f).setDuration(400).start()

                    none_ingredients_2.animate().translationX(0.0f).setDuration(0).start()
                    none_ingredients_2.animate().translationY(0.0f).setDuration(0).start()

                    pose_ingredients++

                    text_igredients.text = "Lemon"
                }
            }
            override fun onSwipeRight() {
                if(pose_ingredients == 0){

                    none_ingredients_2.animate().translationX(-405f).setDuration(500).start()
                    none_ingredients_2.animate().translationY(-125f).setDuration(500).start()

//                    none_ingredients.animate().translationX(405f).setDuration(500).start()
//                    none_ingredients.animate().translationY(-125f).setDuration(500).start()

                    image_ingredients_2.animate().translationX(0.0f).setDuration(500).start()
                    image_ingredients_2.animate().translationY(0.0f).setDuration(500).start()
                    back_ingredients_2.animate().translationX(0.0f).setDuration(500).start()
                    back_ingredients_2.animate().translationY(0.0f).setDuration(500).start()

//                    lemon_ingredients.animate().translationY(0.0f).setDuration(500).start()
//                    lemon_ingredients.animate().translationX(0.0f).setDuration(500).start()

                    lemon_ingredients_2.animate().alpha(0.0f).setDuration(400).start()
                    lemon_ingredients.animate().alpha(1.0f).setDuration(400).start()

                    lemon_ingredients.animate().translationX(-810f).setDuration(0).start()
                    lemon_ingredients.animate().translationY(10f).setDuration(0).start()




//                    none_ingredients_2.animate().alpha(0.0f).setDuration(400).start()
                    Handler().postDelayed({
//                        none_ingredients.animate().alpha(1.0f).setDuration(0).start()
                        image_ingredients.animate().alpha(0.0f).setDuration(400).start()
                        back_ingredients.animate().alpha(0.0f).setDuration(400).start()
                    }, 500)

                    text_igredients.text = "Milk"

                    pose_ingredients = 2

                }
            }
        })
        lemon_ingredients.setOnTouchListener(object : OnSwipeTouchListener(context) {
            override fun onSwipeLeft() {
                //обработка свайпа влево
                if(pose_ingredients == 1){

                    none_ingredients_2.animate().translationX(-405f).setDuration(500).start()
                    none_ingredients_2.animate().translationY(-125f).setDuration(500).start()

                    lemon_ingredients.animate().translationX(-810f).setDuration(500).start()
                    lemon_ingredients.animate().translationY(10f).setDuration(500).start()

                    image_ingredients_2.animate().translationX(0.0f).setDuration(0).start()
                    image_ingredients_2.animate().translationY(0.0f).setDuration(0).start()
                    back_ingredients_2.animate().translationX(0.0f).setDuration(0).start()
                    back_ingredients_2.animate().translationY(0.0f).setDuration(0).start()

                    image_ingredients_2.animate().alpha(1.0f).setDuration(400).start()
                    back_ingredients_2.animate().alpha(1.0f).setDuration(400).start()
                    none_ingredients_2.animate().alpha(1.0f).setDuration(400).start()
                    image_ingredients.animate().alpha(0.0f).setDuration(400).start()
                    back_ingredients.animate().alpha(0.0f).setDuration(400).start()

                    pose_ingredients++

                    text_igredients.text = "None"
                }
            }
            override fun onSwipeRight() {
                if(pose_ingredients == 1){

                    image_ingredients.animate().translationX(0.0f).setDuration(500).start()
                    image_ingredients.animate().translationY(0.0f).setDuration(500).start()
                    back_ingredients.animate().translationX(0.0f).setDuration(500).start()
                    back_ingredients.animate().translationY(0.0f).setDuration(500).start()

                    lemon_ingredients.animate().translationY(0.0f).setDuration(500).start()
                    lemon_ingredients.animate().translationX(0.0f).setDuration(500).start()

                    lemon_ingredients_2.animate().alpha(0.0f).setDuration(400).start()

                    image_ingredients_2.animate().alpha(0.0f).setDuration(400).start()
                    back_ingredients_2.animate().alpha(0.0f).setDuration(400).start()

                    none_ingredients_2.animate().alpha(0.0f).setDuration(400).start()
                    none_ingredients.animate().alpha(1.0f).setDuration(400).start()

                    pose_ingredients--


                    text_igredients.text = "Milk"

                }
            }
        })

        none_ingredients_2.setOnTouchListener(object : OnSwipeTouchListener(context) {
            override fun onSwipeLeft() {
                //обработка свайпа влево
                if(pose_ingredients == 2){

                    image_ingredients_2.animate().translationX(-405f).setDuration(500).start()
                    image_ingredients_2.animate().translationY(-125f).setDuration(500).start()
                    back_ingredients_2.animate().translationX(-405f).setDuration(500).start()
                    back_ingredients_2.animate().translationY(-125f).setDuration(500).start()



                    image_ingredients.animate().translationX(0.0f).setDuration(0).start()
                    image_ingredients.animate().translationY(0.0f).setDuration(0).start()
                    back_ingredients.animate().translationX(0.0f).setDuration(0).start()
                    back_ingredients.animate().translationY(0.0f).setDuration(0).start()
                    lemon_ingredients.animate().translationY(0.0f).setDuration(0).start()
                    lemon_ingredients.animate().translationX(0.0f).setDuration(0).start()



                    Handler().postDelayed({
                        image_ingredients.animate().alpha(1.0f).setDuration(0).start()
                        back_ingredients.animate().alpha(1.0f).setDuration(0).start()
                    }, 500)

                    none_ingredients_2.animate().translationX(-810f).setDuration(500).start()
                    none_ingredients_2.animate().translationY(10f).setDuration(500).start()

                    lemon_ingredients_2.animate().alpha(0.0f).setDuration(400).start()
                    lemon_ingredients.animate().alpha(1.0f).setDuration(400).start()



                    text_igredients.text = "Milk"

                    pose_ingredients = 0

                }
            }

            override fun onSwipeRight() {
                if(pose_ingredients == 2){


                    lemon_ingredients.animate().translationX(-405f).setDuration(500).start()
                    lemon_ingredients.animate().translationY(-125f).setDuration(500).start()

                    none_ingredients_2.animate().translationY(0.0f).setDuration(500).start()
                    none_ingredients_2.animate().translationX(0.0f).setDuration(500).start()

                    lemon_ingredients_2.animate().alpha(0.0f).setDuration(400).start()

                    image_ingredients.animate().alpha(1.0f).setDuration(400).start()
                    back_ingredients.animate().alpha(1.0f).setDuration(400).start()
                    image_ingredients_2.animate().alpha(0.0f).setDuration(400).start()
                    back_ingredients_2.animate().alpha(0.0f).setDuration(400).start()

                    pose_ingredients--

                    text_igredients.text = "Lemon"

                }
            }
        })



        val db = DataBase_Cart.getDb(requireContext())

        button_add_cart.setOnClickListener{
//            dataModel.order_add_in_db.value = true
            Thread{
                Log.d("DATA_DAO_DAO", "Начало...........")
                val item = Entity_Item(null, "${name_coffee.text}", "${text_igredients.text}", "${open_product_price.text}", "${number_colls.text}", "${id_image}")
                db.getDao().insertItem(item)
//                db.getDao().deleteAllItems()
//                db.getDao().resetAutoIncrement()
//                var items = db.getDao().getAllItems()
//
//                items.forEach { item ->
//                    Log.d("DB_CART",
//                        "ID: ${item.id} | " +
//                                "Товар: ${item.name_product} | " +
//                                "Цена: ${item.title_price} | " +
//                                "Состав: ${item.title_elem} | " +
//                                "Кол-во: ${item.title_kol}"
//                    )
//                }
            }.start()
        }

        dataModel.message_baker.observe(this, {
            if(it){
                textView5.isGone = true
                textView8.isGone = true
                imageView24.isGone = true
                none_ingredients.isGone = true
                image_ingredients.isGone = true
                image_ingredients_2.isGone = true
                back_ingredients.isGone = true
                back_ingredients_2.isGone = true
                lemon_ingredients.isGone = true
                button_large_size.isGone = true
                button_medium_size.isGone = true
                button_small_size.isGone = true
                text_igredients.isGone = true
                text_large_size.isGone = true
                text_small_size.isGone = true
                text_medium_size.isGone = true
            }
        })

        return inflatere

    }

    companion object {
        @JvmStatic
        fun newInstance() = Open_product()
    }
}