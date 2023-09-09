package com.shegs.wearsapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shegs.wearsapp.R
import com.shegs.wearsapp.data.MenImageInfo

@Composable
fun DisplayItemDetails(selectedItem: MenImageInfo) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        DragBar()
        
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = selectedItem.title,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onPrimary,
            fontFamily = FontFamily(Font(R.font.rubik_bold)),
            fontWeight = FontWeight(600),
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = selectedItem.description,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.surfaceVariant,
            fontFamily = FontFamily(Font(R.font.rubik_regular)),
            fontWeight = FontWeight(300),
        )
    }
}

@Composable
fun DragBar() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .width(100.dp)
                .height(1.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center
        ) {

        }
    }
}