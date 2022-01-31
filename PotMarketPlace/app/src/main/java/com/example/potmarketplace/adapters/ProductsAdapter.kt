package com.example.potmarketplace.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.potmarketplace.R
import com.example.potmarketplace.models.Product

class ProductsAdapter(
    var productsList: MutableList<Product>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }


    @SuppressLint("NotifyDataSetChanged")
    inner class ProductHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val productPriceTextView = itemView.findViewById<TextView>(R.id.price_text_view)
        private val ownerNameTextView = itemView.findViewById<TextView>(R.id.owner_name_text_view)
        private val productTitleTextView = itemView.findViewById<TextView>(R.id.product_name_text_view)
        private val button = itemView.findViewById<Button>(R.id.order_now_button)

        @SuppressLint("SetTextI18n")
        fun bindProduct( amountType: String, priceType: String,  userName: String,
                         pricePerUnit: String, title: String) {
            productTitleTextView.text = title
            productPriceTextView.text = "$pricePerUnit $priceType/$amountType"
            ownerNameTextView.text = userName
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        lateinit var holder: RecyclerView.ViewHolder

        holder = ProductHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.timeline_recyclerview_element, parent, false)
        )

        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val currentItem = productsList[position]
        if(holder is ProductHolder){
            holder.bindProduct(currentItem.amountType ,currentItem.priceType, currentItem.ownerName, currentItem.pricePerUnit, currentItem.title)
        }
    }

    override fun getItemCount(): Int {
        return productsList.size
    }
}