package com.example.coffescript

import android.annotation.SuppressLint
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
import androidx.core.view.isGone
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
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


class Favorite_frag : Fragment(), Adapter_favorite.Listener {

    val dataModel: DataModel by activityViewModels()
    var id_user_now: Int? = 0


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
        val inflatere = inflater.inflate(R.layout.fragment_favorite_frag, container, false)

        var rc_favorite: RecyclerView = inflatere.findViewById(R.id.rc_favorite)
        val adapter_favorite = Adapter_favorite(this, requireContext())
        val retrofit = Retrofit.Builder().baseUrl("https://server-coffee-script-h669.vercel.app/").addConverterFactory(GsonConverterFactory.create()).build()
        val movie = retrofit.create(RetrofitServies::class.java)

        var image_favorite: ImageView = inflatere.findViewById(R.id.image_favorite)
        var title_favorite: TextView = inflatere.findViewById(R.id.title_not_wishlist)


        rc_favorite.layoutManager = GridLayoutManager(context, 2)
        rc_favorite.adapter = adapter_favorite

        adapter_favorite.productList = ArrayList()


        var list_id_like = arrayListOf<String>()


        Thread{
            val db_profile = DataBase_Profile.getDb_Profile(requireContext())
            var items = db_profile.getDao_Profile().getAllItems()

            activity?.runOnUiThread {
                items.forEach { item ->
                    id_user_now = item.id_user.toInt()
                }
            }

        }.start()

        Handler(Looper.getMainLooper()).postDelayed({
            val databaseWishlist = FirebaseDatabase.getInstance().getReference("users").child("user_$id_user_now").child("wishlist")
            val valueUser = object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (wish in snapshot.children) {
                        val productId = wish.key.toString()  // Чистый ID продукта
                        val productValue = wish.value.toString()  // Значение (обычно true)

                        list_id_like.add(productValue)


                        lifecycleScope.launchWhenStarted {
                            try {
                                var get_products: GetProduct = movie.getProductById()
                                val product: List<Product> = get_products.products!!
                                adapter_favorite.productList = ArrayList()

                                for(elem in product) {
                                    Log.i("QWER", "${elem.name}")


                                        if(list_id_like.contains(elem.id.toString())){
                                            adapter_favorite.addProduct(Product(elem.id, elem.name, elem.price, elem.description, true))

                                        }

                                }

                            }catch (e: Exception){
                                Log.e("QWER", "$e")
                            }
                        }
                        adapter_favorite.notifyDataSetChanged()
                    }
                    if(list_id_like.isNotEmpty()){
                        title_favorite.isGone = true
                        image_favorite.isGone = true
                    }else{
                        title_favorite.isGone = false
                        image_favorite.isGone = false
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            }
            databaseWishlist.addListenerForSingleValueEvent(valueUser)
        }, 100)


        return inflatere
    }

    companion object {
        @JvmStatic
        fun newInstance() =  Favorite_frag()
    }

    override fun onClick(product: Product) {
        dataModel.message_open_product.value = true
        dataModel.name_and_price.value = arrayListOf(product.name.toString(), product.price.toString(), product.id.toString())
        dataModel.message_favorite_track.value = true
    }

    override fun onLike(product: Product) {
        val addWishlistName = FirebaseDatabase.getInstance().getReference("users").child("user_${id_user_now}").child("wishlist").child("like_product_${product.id}")
        addWishlistName.setValue(product.id.toString())
    }

    override fun deleteLike(product: Product) {
        val deleteWishlistProduct = FirebaseDatabase.getInstance().getReference("users").child("user_${id_user_now}").child("wishlist").child("like_product_${product.id}")
        deleteWishlistProduct.removeValue()
    }
}