package com.example.coffescript

import android.annotation.SuppressLint
import android.content.Context
import android.media.Image

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isGone
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coffescript.Interface.RetrofitServies
import com.example.coffescript.Model.GetProduct
import com.example.coffescript.Model.Product
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.lang.Exception


class Home_frag : Fragment(), Adapter.Listener{

    val dataModel: DataModel by activityViewModels()
    var id_user_now: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflatere = inflater.inflate(R.layout.fragment_home_frag, container, false)
        var text_hot_coffee: TextView = inflatere.findViewById(R.id.text_hot_coffee)
        var text_hot_teas: TextView = inflatere.findViewById(R.id.text_hot_teas)
        var text_ice_teas: TextView = inflatere.findViewById(R.id.text_ice_teas)
        var text_bakery: TextView = inflatere.findViewById(R.id.text_bakery)
        var name: TextView = inflatere.findViewById(R.id.name_user)
        var rc_item_products: RecyclerView = inflatere.findViewById(R.id.rc_item_products)


        var pro_1: Boolean
        var pro_2: Boolean
        var pro_3: Boolean
        var pro_4: Boolean

        var list_id_like = arrayListOf<String>()

        var schet_user = 0

        val retrofit = Retrofit.Builder().baseUrl("https://server-coffee-script-h669.vercel.app/").addConverterFactory(GsonConverterFactory.create()).build()
        val movie = retrofit.create(RetrofitServies::class.java)


        lifecycleScope.launchWhenStarted {
            try {
                var get_products: GetProduct = movie.getProductById()
                val product: List<Product> = get_products.products!!

                for(elem in product) {
//                    Log.i("POKOPOKO", "${elem.name}")
//                    if(elem.id!! <= 6){
//
//                        if(list_id_like.contains(elem.id.toString())){
//                            adapter_rc.addProduct(Product(elem.id, elem.name, elem.price, elem.description, true))
//
//                        }else{
//                            adapter_rc.addProduct(Product(elem.id, elem.name, elem.price, elem.description, false))
//                        }
//                    }
                }

            }catch (e: Exception){
                Log.e("QWER", "$e")
            }
        }
//        adapter_rc.notifyDataSetChanged()

        Log.i("KOKO", "Number user: $schet_user")



        Thread{
            val db_profile = DataBase_Profile.getDb_Profile(requireContext())
//            db_profile.getDao_Profile().deleteAllItems()
//            db_profile.getDao_Profile().resetAutoIncrement()
            var items = db_profile.getDao_Profile().getAllItems()

            Log.d("DB_CART", "--------------------------------------")

            activity?.runOnUiThread {
                items.forEach { item ->
                    name.text = item.name
                    id_user_now = item.id_user.toInt()

//                    Log.d("DB_CART",
//                        "Список всего и вся: ${id_user_now}"
//                    )

                    Log.d("DB_CART", "ID: ${item.id} | " +
                            "Товар: ${item.name} | " +
                            "Цена: ${item.email} | " +
                            "Состав: ${item.password} | " +
                            "Кол-во: ${item.id_user}"
                    )


                }
            }

        }.start()

        rc_item_products.layoutManager = GridLayoutManager(context, 2)

        var adapter_rc = Adapter(this, requireContext())

        rc_item_products.adapter = adapter_rc

        Handler(Looper.getMainLooper()).postDelayed({
            val databaseWishlist = FirebaseDatabase.getInstance().getReference("users").child("user_$id_user_now").child("wishlist")
            val valueUser = object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (wish in snapshot.children) {
                        val productId = wish.key.toString()  // Чистый ID продукта
                        val productValue = wish.value.toString()  // Значение (обычно true)

                        list_id_like.add(productValue)

                        Log.i("QWER", "Lalalala ${id_user_now}")


//                        rc_item_products.adapter = adapter_rc
                        lifecycleScope.launchWhenStarted {
                            try {
                                var get_products: GetProduct = movie.getProductById()
                                val product: List<Product> = get_products.products!!
                                adapter_rc.productList = ArrayList()

                                for(elem in product) {
                                    Log.i("QWER", "${elem.name}")
                                    if(elem.id!! <= 6){
                                        if(list_id_like.contains(elem.id.toString())){
                                            adapter_rc.addProduct(Product(elem.id, elem.name, elem.price, elem.description, true))

                                        }else{
                                            adapter_rc.addProduct(Product(elem.id, elem.name, elem.price, elem.description, false))
                                        }
                                    }
                                }

                            }catch (e: Exception){
                                Log.e("QWER", "$e")
                            }
                        }
                        adapter_rc.notifyDataSetChanged()

                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            }
            databaseWishlist.addListenerForSingleValueEvent(valueUser)
        }, 100)

        text_hot_coffee.setOnClickListener{
            pro_1 = true
            pro_2 = false
            pro_3 = false
            pro_4 = false

            text_press(text_hot_coffee, text_ice_teas, text_hot_teas, text_bakery, pro_1, pro_2, pro_3, pro_4, rc_item_products, movie)

            adapter_rc.productList = arrayListOf()
            adapter_rc.notifyDataSetChanged()

            Log.i("POKOPOKO", "Запуск был")


            lifecycleScope.launchWhenStarted {
                try {
                    var get_products: GetProduct = movie.getProductById()
                    val product: List<Product> = get_products.products!!
                    adapter_rc.productList = ArrayList()

                    Handler(Looper.getMainLooper()).postDelayed({
                        val databaseWishlist = FirebaseDatabase.getInstance().getReference("users").child("user_$id_user_now").child("wishlist")
                        val valueUser = object : ValueEventListener{
                            override fun onDataChange(snapshot: DataSnapshot) {
                                for (wish in snapshot.children) {
                                    val productId = wish.key.toString()  // Чистый ID продукта
                                    val productValue = wish.value.toString()  // Значение (обычно true)

                                    list_id_like.add(productValue)
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                TODO("Not yet implemented")
                            }

                        }
                        databaseWishlist.addListenerForSingleValueEvent(valueUser)
                    }, 100)

                    for(elem in product) {
                        if(elem.id!! <= 6){


                            if(list_id_like.contains(elem.id.toString())){
                                adapter_rc.addProduct(Product(elem.id, elem.name, elem.price, elem.description, true))


                            }else{
                                adapter_rc.addProduct(Product(elem.id, elem.name, elem.price, elem.description, false))
                                Log.i("POKOPOKO", "${elem.name}, ${elem.id}")

                            }
                        }
                    }

                }catch (e: Exception){
                    Log.e("QWER", "$e")
                }
            }
            adapter_rc.notifyDataSetChanged()

            Log.i("BOBOBO", "Position ${adapter_rc.productList}")

        }

        text_ice_teas.setOnClickListener{
            pro_1 = false
            pro_2 = true
            pro_3 = false
            pro_4 = false

            text_press(text_hot_coffee, text_ice_teas, text_hot_teas, text_bakery, pro_1, pro_2, pro_3, pro_4, rc_item_products, movie)

            adapter_rc.productList = arrayListOf()
            adapter_rc.notifyDataSetChanged()


            lifecycleScope.launchWhenStarted {
                try {
                    var get_products: GetProduct = movie.getProductById()
                    val product: List<Product> = get_products.products!!
                    adapter_rc.productList = ArrayList()

                    Log.i("QWER", "Есть ли что-то")


                    Handler(Looper.getMainLooper()).postDelayed({
                        val databaseWishlist = FirebaseDatabase.getInstance().getReference("users").child("user_$id_user_now").child("wishlist")
                        val valueUser = object : ValueEventListener{
                            override fun onDataChange(snapshot: DataSnapshot) {
                                for (wish in snapshot.children) {
                                    val productId = wish.key.toString()  // Чистый ID продукта
                                    val productValue = wish.value.toString()  // Значение (обычно true)

                                    list_id_like.add(productValue)
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                TODO("Not yet implemented")
                            }

                        }
                        databaseWishlist.addListenerForSingleValueEvent(valueUser)
                    }, 100)

                    for(elem in product) {
                        Log.i("QWER", "${elem.name}")
                        Log.i("QWER", "Есть ${elem.name} что-то")


                        if(elem.id!! > 6 && elem.id!! <= 12){
                            if(list_id_like.contains(elem.id.toString())){
                                adapter_rc.addProduct(Product(elem.id, elem.name, elem.price, elem.description, true))

                            }else{
                                adapter_rc.addProduct(Product(elem.id, elem.name, elem.price, elem.description, false))
                            }
                        }
                    }

                }catch (e: Exception){
                    Log.e("QWER", "$e")
                }
            }
            adapter_rc.notifyDataSetChanged()
        }

        text_hot_teas.setOnClickListener{
            pro_1 = false
            pro_2 = false
            pro_3 = true
            pro_4 = false

            text_press(text_hot_coffee, text_ice_teas, text_hot_teas, text_bakery, pro_1, pro_2, pro_3, pro_4, rc_item_products, movie)

            adapter_rc.productList = arrayListOf()
            adapter_rc.notifyDataSetChanged()

            lifecycleScope.launchWhenStarted {
                try {
                    var get_products: GetProduct = movie.getProductById()
                    val product: List<Product> = get_products.products!!
                    adapter_rc.productList = ArrayList()



                    for(elem in product) {
                        Log.i("QWER", "${elem.id}")
                        if(elem.id!! > 12 && elem.id!! <= 16){
                            if(list_id_like.contains(elem.id.toString())){
                                adapter_rc.addProduct(Product(elem.id, elem.name, elem.price, elem.description, true))
                            }else{
                                adapter_rc.addProduct(Product(elem.id, elem.name, elem.price, elem.description, false))
                            }
                        }
                    }

                }catch (e: Exception){
                    Log.e("QWER", "$e")
                }
            }
            adapter_rc.notifyDataSetChanged()
        }

        text_bakery.setOnClickListener{
            pro_1 = false
            pro_2 = false
            pro_3 = false
            pro_4 = true

            text_press(text_hot_coffee, text_ice_teas, text_hot_teas, text_bakery, pro_1, pro_2, pro_3, pro_4, rc_item_products, movie)

            adapter_rc.productList = arrayListOf()
            adapter_rc.notifyDataSetChanged()

            lifecycleScope.launchWhenStarted {
                try {
                    var get_products: GetProduct = movie.getProductById()
                    val product: List<Product> = get_products.products!!
                    adapter_rc.productList = ArrayList()

                    for(elem in product) {
                        Log.i("QWER", "${elem.name}")
                        if(elem.id!! > 16 && elem.id!! <= 20){
                            if(list_id_like.contains(elem.id.toString())){
                                adapter_rc.addProduct(Product(elem.id, elem.name, elem.price, elem.description, true))

                            }else{
                                adapter_rc.addProduct(Product(elem.id, elem.name, elem.price, elem.description, false))
                            }
                        }
                    }

                }catch (e: Exception){
                    Log.e("QWER", "$e")
                }
            }
            adapter_rc.notifyDataSetChanged()
        }

        dataModel.data_user.observe(this, {
            schet_user = it
            Log.i("KOKO", "DataModel user: $it")

        })

        return inflatere
    }


    @SuppressLint("ResourceAsColor")
    fun text_press(text_1: TextView, text_2: TextView, text_3: TextView, text_4: TextView, pro_1: Boolean, pro_2: Boolean, pro_3: Boolean, pro_4: Boolean, rc_item_products: RecyclerView, movie: RetrofitServies){
        if(pro_1){
            text_1.typeface = ResourcesCompat.getFont(requireContext(), R.font.inter_semibold)
            text_2.typeface = ResourcesCompat.getFont(requireContext(), R.font.inter_medium)
            text_3.typeface = ResourcesCompat.getFont(requireContext(), R.font.inter_medium)
            text_4.typeface = ResourcesCompat.getFont(requireContext(), R.font.inter_medium)

            text_1.setTextColor(ContextCompat.getColor(requireContext(), R.color.button_color))
            text_2.setTextColor(ContextCompat.getColor(requireContext(), R.color.none))
            text_3.setTextColor(ContextCompat.getColor(requireContext(), R.color.none))
            text_4.setTextColor(ContextCompat.getColor(requireContext(), R.color.none))

        }else if(pro_2){
            text_1.typeface = ResourcesCompat.getFont(requireContext(), R.font.inter_medium)
            text_2.typeface = ResourcesCompat.getFont(requireContext(), R.font.inter_semibold)
            text_3.typeface = ResourcesCompat.getFont(requireContext(), R.font.inter_medium)
            text_4.typeface = ResourcesCompat.getFont(requireContext(), R.font.inter_medium)

            text_1.setTextColor(ContextCompat.getColor(requireContext(), R.color.none))
            text_2.setTextColor(ContextCompat.getColor(requireContext(), R.color.button_color))
            text_3.setTextColor(ContextCompat.getColor(requireContext(), R.color.none))
            text_4.setTextColor(ContextCompat.getColor(requireContext(), R.color.none))


        }else if(pro_3){
            text_1.typeface = ResourcesCompat.getFont(requireContext(), R.font.inter_medium)
            text_2.typeface = ResourcesCompat.getFont(requireContext(), R.font.inter_medium)
            text_3.typeface = ResourcesCompat.getFont(requireContext(), R.font.inter_semibold)
            text_4.typeface = ResourcesCompat.getFont(requireContext(), R.font.inter_medium)

            text_1.setTextColor(ContextCompat.getColor(requireContext(), R.color.none))
            text_2.setTextColor(ContextCompat.getColor(requireContext(), R.color.none))
            text_3.setTextColor(ContextCompat.getColor(requireContext(), R.color.button_color))
            text_4.setTextColor(ContextCompat.getColor(requireContext(), R.color.none))
        }else if(pro_4){
            text_1.typeface = ResourcesCompat.getFont(requireContext(), R.font.inter_medium)
            text_2.typeface = ResourcesCompat.getFont(requireContext(), R.font.inter_medium)
            text_3.typeface = ResourcesCompat.getFont(requireContext(), R.font.inter_medium)
            text_4.typeface = ResourcesCompat.getFont(requireContext(), R.font.inter_semibold)

            text_1.setTextColor(ContextCompat.getColor(requireContext(), R.color.none))
            text_2.setTextColor(ContextCompat.getColor(requireContext(), R.color.none))
            text_3.setTextColor(ContextCompat.getColor(requireContext(), R.color.none))
            text_4.setTextColor(ContextCompat.getColor(requireContext(), R.color.button_color))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = Home_frag()
    }

    override fun onClick(product: Product) {
        dataModel.message_open_product.value = true
        dataModel.message_baker.value = false
        dataModel.name_and_price.value = arrayListOf(product.name.toString(), product.price.toString(), product.id.toString())
        dataModel.message_favorite_track.value = false
    }

    override fun onLike(product: Product) {
        val addWishlistName = FirebaseDatabase.getInstance().getReference("users").child("user_${id_user_now}").child("wishlist").child("like_product_${product.id}")
        addWishlistName.setValue(product.id.toString())
    }

    override fun deleteLike(product: Product) {
        val deleteWishlistProduct = FirebaseDatabase.getInstance().getReference("users").child("user_${id_user_now}").child("wishlist").child("like_product_${product.id}")
        deleteWishlistProduct.removeValue()
    }

    override fun onBaker(product: Product) {
        dataModel.message_baker.value = true
        dataModel.message_open_product.value = true
    }
}