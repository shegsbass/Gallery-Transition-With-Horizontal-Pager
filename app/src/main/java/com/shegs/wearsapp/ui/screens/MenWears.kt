package com.shegs.wearsapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shegs.wearsapp.R
import com.shegs.wearsapp.data.MenImageInfo

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MenWearsScreen() {

    val menWears = remember {
        listOf(
            MenImageInfo(R.drawable.image1, "Title 1", "Description 1"),
            MenImageInfo(R.drawable.image2, "Title 2", "Description 2"),
            MenImageInfo(R.drawable.image3, "Title 3", "Description 3")
        )
    }

    val matrix = remember {
        ColorMatrix()
    }

    // Create a new PagerState with the desired initial page
    val pagerState = rememberPagerState(initialPage = 0)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TopSection()

        Scaffold(
            modifier = Modifier
                .padding(vertical = 48.dp)
        ) {
            HorizontalPager(
                pageCount = menWears.size,
                state = pagerState
            ) { index ->
                val pageOffset =
                    (pagerState.currentPage - index) + pagerState.currentPageOffsetFraction
                val imageSize by animateFloatAsState(
                    targetValue = if (pageOffset != 0.0f) 0.75f else 1f,
                    animationSpec = tween(durationMillis = 300),
                    label = "images"
                )

                LaunchedEffect(key1 = imageSize) {
                    if (pageOffset != 0.0f) {
                        matrix.setToSaturation(0f)
                    } else {
                        matrix.setToSaturation(1f)
                    }
                }

                val imageInfo = menWears[index]

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {

                    Image(
                        painter = painterResource(id = imageInfo.imageResource),
                        contentDescription = "Image",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 80.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .graphicsLayer {
                                scaleX = imageSize
                                scaleY = imageSize
                            },
                        contentScale = ContentScale.Crop,
                        colorFilter = ColorFilter.colorMatrix(matrix)
                    )

                    Column(
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .align(Alignment.BottomStart)
                    ) {
                        Text(
                            text = imageInfo.title,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.scrim
                        )
                        Text(text = imageInfo.description, color = Color.Black)
                    }
                }

            }
        }
    }
}

@Composable
fun TopSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Browse",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.scrim
        )

        Image(
            painter = painterResource(id = R.drawable.account_circle),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
        )
    }
}