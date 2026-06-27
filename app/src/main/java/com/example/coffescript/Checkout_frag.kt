package com.example.coffescript

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.createBitmap
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coffescript.Model.Product_cart
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Checkout_frag : Fragment() {

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
        val inflatere = inflater.inflate(R.layout.fragment_checkout_frag, container, false)

        val rc_product_checkout: RecyclerView = inflatere.findViewById(R.id.rc_item_products_checkout)
        var adapter_rc_checkout = Adapter_Checkout(requireContext())
        val db = DataBase_Cart.getDb(requireContext())
        var button_back_checkout: ImageView = inflatere.findViewById(R.id.back_button_checkout)
        var button_add_card: Button = inflatere.findViewById(R.id.button_add_chekout)
        var title_add_card: TextView = inflatere.findViewById(R.id.title_add_card)
        var title_credit_or_debit: TextView = inflatere.findViewById(R.id.title_credit_or_debit)
        var button_payment: Button = inflatere.findViewById(R.id.button_payment)

        val current = LocalDateTime.now()
        val plus3 = current.plusMinutes(3)
        val plus7 = current.plusMinutes(7)
        val plus10 = current.plusMinutes(10)


        val formatter_time = DateTimeFormatter.ofPattern("HH:mm")
        val formattedTime = current.format(formatter_time)

        val formatter_date = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val formattedDate = current.format(formatter_date)

        var schetchik_items = 0
        var global_global_price = 0


        var list_date_order_frag = arrayListOf<String>()


        button_payment.setOnClickListener{
            list_date_order_frag.add(formattedDate)
            list_date_order_frag.add(formattedTime)
            list_date_order_frag.add(plus3.format(formatter_time))
            list_date_order_frag.add(plus7.format(formatter_time))
            list_date_order_frag.add(plus10.format(formatter_time))
            list_date_order_frag.add(schetchik_items.toString())
            list_date_order_frag.add(global_global_price.toString())

            Log.d("DB_CART",
                "Список: ${list_date_order_frag}"
            )
            Log.d("DB_CART",
                "Список: ${list_date_order_frag.reversed()}"
            )

//            dataModel.date_list_order.value = arrayListOf(list_date_order_frag)

            dataModel.date_list_order.value?.add(list_date_order_frag)
            dataModel.message_payment_done.value = true
        }

        button_back_checkout.setOnClickListener{
            dataModel.message_cart_button.value = true
        }

        button_add_card.setOnClickListener{
            dataModel.message_add_card.value = true
        }

        dataModel.list_add_card.observe(this, {
            if(it.isNotEmpty()){
                title_add_card.text = it[0].toString()
                title_credit_or_debit.text = "Debit"
            }
        })


        Thread{
            val items = db.getDao().getAllItems()

            requireActivity().runOnUiThread{
                items.forEach{ item ->
                    schetchik_items++
                    global_global_price += item.title_price.toInt()
                    adapter_rc_checkout.addProduct(
                        Product_cart(
                            item.id_image.toInt(),
                            item.name_product,
                            item.title_price,
                            item.title_elem,
                            item.title_kol
                        )
                    )
                }
            }
        }.start()

        rc_product_checkout.layoutManager = LinearLayoutManager(context)
        rc_product_checkout.adapter = adapter_rc_checkout





        return inflatere
    }

    companion object {
        @JvmStatic
        fun newInstance() = Checkout_frag()
    }
}