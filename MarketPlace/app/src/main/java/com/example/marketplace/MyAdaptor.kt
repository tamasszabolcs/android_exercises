//package com.example.marketplace
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ArrayAdapter
//import androidx.fragment.app.Fragment
//import com.example.marketplace.retrofit.models.ProductsModel
//
//class MyAdaptor(var context: Fragment, var arrayList: ArrayList<ProductsModel>) : ArrayAdapter<ProductsModel>(context, R.layout.list_item,arrayList){
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        val inflater: LayoutInflater = LayoutInflater.from(context)
//        val view: View = inflater.inflate(R.layout.list_item,null)
//        return super.getView(position, convertView, parent)
//    }
//}