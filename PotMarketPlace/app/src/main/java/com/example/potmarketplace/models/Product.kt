package com.example.potmarketplace.models

data class Product(var rating: Float,
                   var amountType: String,
                   var priceType: String,
                   var productId: String,
                   var ownerName: String,
                   var isActive: Boolean,
                   var pricePerUnit : String,
                   var units: String,
                   var description: String,
                   var title: String
){
    init{
        amountType = dropUnNeededChars(amountType)
        priceType = dropUnNeededChars(priceType)
        productId = dropUnNeededChars(productId)
        ownerName =dropUnNeededChars(ownerName)
        pricePerUnit =dropUnNeededChars(pricePerUnit)
        units = dropUnNeededChars(units)
        description = dropUnNeededChars(description)
        title =dropUnNeededChars(title)
    }

    private fun dropUnNeededChars(str: String) : String{

        var strNew = str

        if(strNew.contains('\"')){
            strNew = str.drop(1)
        }

        if(strNew.contains('\"')){
            strNew = strNew.dropLast(1)
        }

        return strNew
    }
}
