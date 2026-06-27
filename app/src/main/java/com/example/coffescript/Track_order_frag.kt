package com.example.coffescript

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.zip.DataFormatException

class Track_order_frag : Fragment() {

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
        val inflatere = inflater.inflate(R.layout.fragment_track_order_frag, container, false)

        val button_back_track_order: ImageView = inflatere.findViewById(R.id.back_button_track_order)
        val text_placed_track_order: TextView = inflatere.findViewById(R.id.text_placed_track_order)
        val text_progress_track_order: TextView = inflatere.findViewById(R.id.text_progress_track_order)
        val text_way_track_order: TextView = inflatere.findViewById(R.id.text_way_track_order)
        val text_deliverd_track_order: TextView = inflatere.findViewById(R.id.text_deliverd_track_order)

        var progress_done: ImageView = inflatere.findViewById(R.id.progress_done)
        var way_done: ImageView = inflatere.findViewById(R.id.way_done)
        var deliver_done: ImageView = inflatere.findViewById(R.id.deliver_done)

        var index_date = 0
        var proverka = false

        dataModel.message_my_order_track.observe(this, {
            proverka = it
        })

        button_back_track_order.setOnClickListener{
            if(proverka){
                dataModel.message_my_order.value = true
                dataModel.message_my_order_track.value = false
            }else{
                dataModel.message_cart_button.value = true
            }
        }

        dataModel.date_list_order.observe(this, {
            if(proverka){
                dataModel.index_order.observe(this, {it_index ->
                    index_date = it_index
                })
            }else{
                index_date = it.lastIndex
            }

            text_placed_track_order.text = "${it[index_date][0]}, ${it[index_date][1]}"

            text_progress_track_order.text = "${it[index_date][0]}, ${it[index_date][2]}"
            text_way_track_order.text = "${it[index_date][0]}, ${it[index_date][3]}"
            text_deliverd_track_order.text = "${it[index_date][0]}, ${it[index_date][4]}"


            val current = LocalDateTime.now()
            val formatter_time = DateTimeFormatter.ofPattern("HH:mm")
            val formattedTime = current.format(formatter_time)

            var only_hh_form = DateTimeFormatter.ofPattern("HH")
            var only_hh = current.format(only_hh_form)

            var only_mm_form = DateTimeFormatter.ofPattern("mm")
            var only_mm = current.format(only_mm_form)

            var current_date = LocalDate.now()
            var order_date_format = DateTimeFormatter.ofPattern("dd.MM.yyyy")
            var order_date = LocalDate.parse(it[index_date][0], order_date_format)


            if (current_date.isAfter(order_date) || formattedTime == it[index_date][2] || only_hh.toInt() > it[index_date][2].take(2).toInt() || only_mm.toInt() > it[index_date][2].takeLast(2).toInt()){
                progress_done.setImageResource(R.drawable.track_order_icon_done)
            }
            if (current_date.isAfter(order_date) || formattedTime == it[index_date][3]|| only_hh.toInt() > it[index_date][3].take(2).toInt() || only_mm.toInt() > it[index_date][3].takeLast(2).toInt()){
                way_done.setImageResource(R.drawable.track_order_icon_done)
            }
            if (current_date.isAfter(order_date) || formattedTime == it[index_date][4]|| only_hh.toInt() > it[index_date][4].take(2).toInt() || only_mm.toInt() > it[index_date][4].takeLast(2).toInt()){
                deliver_done.setImageResource(R.drawable.track_order_icon_done)
            }

        })



        return inflatere

    }

    companion object {
        @JvmStatic
        fun newInstance() = Track_order_frag()
    }
}