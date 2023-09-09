package com.shegs.wearsapp.data

import androidx.annotation.RawRes
import com.shegs.wearsapp.R

data class GetStartedData(@RawRes val resId: Int, val title: String, val desc: String)

val listData = listOf(
    GetStartedData(
        R.raw.animate8,
        "Welcome to Our Fashion World!",
        "Explore the latest fashion trends and footwear collections for men and women. Let's get started!",
    ),
    GetStartedData(
        R.raw.animate9,
        "Trendy Apparel for All",
        "Elevate Your Style. Discover the perfect outfit for any occasion. Dress up with confidence!",
    ),
    GetStartedData(
        R.raw.animate7,
        "Step into Stylish Footwear",
        "Find Your Perfect Pair. From sneakers to heels, we've got stylish footwear for every step you take.",
    ),
    GetStartedData(
        R.raw.animate10,
        "Stay Fashion-Forward",
        "Stay in the know with exclusive offers and style updates. Exclusive offers awaits you.",
    ),
)