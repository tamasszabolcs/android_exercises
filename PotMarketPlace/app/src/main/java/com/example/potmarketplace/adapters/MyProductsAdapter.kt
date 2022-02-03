package com.example.potmarketplace.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.potmarketplace.R
import com.example.potmarketplace.models.Product

class MyProductsAdapter (
    var myProductsList: MutableList<Product>,
    private val onItemClickListener: OnItemClickListener
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
            private val deleteButton = itemView.findViewById<ImageView>(R.id.delete_image_view)
            private val activeImageView = itemView.findViewById<ImageView>(R.id.active_inactive_image_view)
            private val activeTextView = itemView.findViewById<TextView>(R.id.active_inactive_text_view)


            @SuppressLint("SetTextI18n")
            fun bindProduct( amountType: String, priceType: String,  userName: String,
                             pricePerUnit: String, title: String, isActive: Boolean) {
                productTitleTextView.text = title
                productPriceTextView.text = "$pricePerUnit $priceType/$amountType"
                ownerNameTextView.text = userName
                if(isActive == true){
                    activeTextView.text = "active"
                    Glide.with(itemView.context)
                        .load(R.drawable.ic_active_product)
                        .placeholder(R.drawable.ic_active_product)
                        .error(R.drawable.ic_active_product)
                        .circleCrop()
                        .into(activeImageView)
                }
                else{
                    activeTextView.text = "inactive"
                    Glide.with(itemView.context)
                        .load(R.drawable.ic_inactive_product)
                        .placeholder(R.drawable.ic_inactive_product)
                        .error(R.drawable.ic_inactive_product)
                        .circleCrop()
                        .into(activeImageView)
                }

            }
            init {
                itemView.setOnClickListener {
                    val position = layoutPosition
                    onItemClickListener.onItemClick(position)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            lateinit var holder: RecyclerView.ViewHolder

            holder = ProductHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.my_market_recyclerview_element, parent, false)
            )

            return holder
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

            val currentItem = myProductsList[position]

            if(holder is ProductHolder){

                holder.bindProduct(currentItem.amountType ,currentItem.priceType, currentItem.ownerName, currentItem.pricePerUnit, currentItem.title,currentItem.isActive)

            }

        }

        override fun getItemCount(): Int {
            return myProductsList.size
        }

    }