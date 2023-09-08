package com.shegs.wearsapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.shegs.wearsapp.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MenWearsScreen(){

    val images = remember {
        listOf(
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3
        )
    }

    val pagerState = rememberPagerState()

    Scaffold(
        modifier = Modifier
            .padding(vertical = 48.dp)
    ) {
        HorizontalPager(
            pageCount = images.size,
            state = pagerState
        ) {index ->
            val pageOffset = (pagerState.currentPage - index) + pagerState.currentPageOffsetFraction
            val imageSize by animateFloatAsState(
                targetValue = if (pageOffset != 0.0f) 0.75f else 1f,
                animationSpec = tween(durationMillis = 300),
                label = "images"
            )
            Image(
                painter = painterResource(id = images[index]),
                contentDescription = "Image",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .graphicsLayer {
                        scaleX = imageSize
                        scaleY = imageSize
                    },
                contentScale = ContentScale.Crop
            )
        }

    }
}