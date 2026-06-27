package com.example.coffescript

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.coffescript.Model.Product
import com.example.coffescript.Model.Product_cart
import org.w3c.dom.Text

class Adapter_Checkout (private val myContext: Context): RecyclerView.Adapter<Adapter_Checkout.Holder>() {
    var productList = ArrayList<Product_cart>()

    class Holder (itemView: View, private val context: Context): RecyclerView.ViewHolder(itemView){
        var name_item: TextView = itemView.findViewById(R.id.name_item_chekout)
        var image_item_chekout: ImageView = itemView.findViewById(R.id.image_item_chekout)
        var title_elem: TextView = itemView.findViewById(R.id.title_elem_chekout)
        var title_price: TextView = itemView.findViewById(R.id.title_price_chekout)
        var id: Int? = 0


        fun bind(listItem: Product_cart, myContext: Context, list_data: ArrayList<Product_cart>, adapterCart: Adapter_Checkout){
            name_item.text = listItem.name
            title_elem.text = "With ${listItem.title_elem}"
            title_price.text = "$${listItem.title_price}"
            id = listItem.id

//            var proeb_price =

            val db = DataBase_Cart.getDb(context)
            val resourceId = context.resources.getIdentifier("product_$id", "drawable", context.packageName)
            image_item_chekout.setImageResource(resourceId)


        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter_Checkout.Holder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_prod_chekout, parent, false)
        return Holder(itemView, myContext)
    }

    override fun onBindViewHolder(holder: Adapter_Checkout.Holder, position: Int) {
        holder.bind(productList[position], myContext, productList, this)
    }

    override fun getItemCount(): Int {
        return productList.size
    }


    fun addProduct(product: Product_cart){
        productList.add(product)
        notifyDataSetChanged()
    }

}