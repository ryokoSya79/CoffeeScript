package com.example.coffescript

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.example.coffescript.Model.Product

class Adapter (val listener: Listener, private val myContext: Context): RecyclerView.Adapter<Adapter.Holder>() {
    var productList = ArrayList<Product>()

    class Holder (itemView: View, private val context: Context): RecyclerView.ViewHolder(itemView){
        var name_item: TextView = itemView.findViewById(R.id.name_item)
        var image_item: ImageView = itemView.findViewById(R.id.image_item)
        var description_item: TextView = itemView.findViewById(R.id.description_item)
        var price_item: TextView = itemView.findViewById(R.id.price_item)
        var id: Int? = 0
        var like_button: ImageView = itemView.findViewById(R.id.like_button)
        var like: Boolean? = false


        fun bind(listItem: Product, listener: Listener, myContext: Context){
            name_item.text = listItem.name
            price_item.text = listItem.price
            description_item.text = listItem.description
            id = listItem.id
            like = listItem.like

            val resourceId = context.resources.getIdentifier("product_$id", "drawable", context.packageName)
            image_item.setImageResource(resourceId)

            Log.i("POKOPOKO", "А тут что-то есть: ${id}")



            if(like!! != true){

                like_button.setImageResource(R.drawable.like_ic)
            }else{
                like_button.setImageResource(R.drawable.like_press_ic)
            }

            like_button.setOnClickListener{
                if(like!!){
                    like = false
                    like_button.setImageResource(R.drawable.like_ic)
                    listener.deleteLike(listItem)


                }else{
                    like = true
                    like_button.setImageResource(R.drawable.like_press_ic)
                    listener.onLike(listItem)
                }
            }

            itemView.setOnClickListener{
                if(id == 17 || id == 18 || id == 19 || id == 20){
                    listener.onBaker(listItem)
                }else{
                    listener.onClick(listItem)
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.Holder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return Holder(itemView, myContext)
    }

    override fun onBindViewHolder(holder: Adapter.Holder, position: Int) {
        if(position <= 6){
            holder.bind(productList[position], listener, myContext)
        }

    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun addProduct(product: Product){
        productList.add(product)
        notifyDataSetChanged()
    }

    interface Listener{
        fun onClick(product: Product)
        fun onLike(product: Product)
        fun deleteLike(product: Product)
        fun onBaker(product: Product)
    }

}