package com.shegs.wearsapp.data

import androidx.compose.runtime.Composable
import com.shegs.wearsapp.ui.screens.FootWearsScreen
import com.shegs.wearsapp.ui.screens.MenWearsScreen
import com.shegs.wearsapp.ui.screens.WomenWearsScreen

typealias ComposableFun = @Composable ()->Unit

sealed class TabItem(val title:String,val screens:ComposableFun) {

    object MenWears : TabItem(title = "Men Wears", screens = { MenWearsScreen() })
    object WomenWears: TabItem(title = "Women Wears", screens={ WomenWearsScreen() })
    object FootWears : TabItem(title = "Foot Wears", screens = { FootWearsScreen() })


}