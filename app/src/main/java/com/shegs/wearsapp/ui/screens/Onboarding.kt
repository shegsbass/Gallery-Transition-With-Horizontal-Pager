package com.shegs.wearsapp.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.shegs.wearsapp.data.listData
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun GetStartedScreen(navController: NavController) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    val (selectedPage, setSelectedPage) = remember {
        mutableStateOf(0)
    }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            setSelectedPage(page)
        }
    }

    Scaffold {
        Column {
            HorizontalPager(
                count = listData.size,
                state = pagerState,
                modifier = Modifier.weight(0.6f)
            ) { page ->
                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(listData[page].resId))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    LottieAnimation(
                        composition,
                        /// looping the animation
                        iterations = LottieConstants.IterateForever,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        listData[page].title,
                        style = MaterialTheme.typography.titleLarge,
                    )
                    Box(modifier = Modifier.height(24.dp))
                    Text(
                        listData[page].desc,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                for (i in listData.indices) {
                    Box(
                        modifier = Modifier
                            .padding(end = if (i == listData.size - 1) 0.dp else 5.dp)
                            .width(if (i == selectedPage) 20.dp else 10.dp)
                            .height(10.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                if (i == selectedPage) Color.Red else Color.Blue.copy(
                                    alpha = 0.1f
                                )
                            )
                    )
                }
            }

            // only show if not last page
            if (selectedPage != listData.size - 1) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    TextButton(
                        onClick = {
                            scope.launch {
                                /// animate to last page
                                pagerState.animateScrollToPage(listData.size - 1)
                            }
                        },
                        modifier = Modifier.height(56.dp)
                    ) {
                        Text(text = "Skip")
                    }

                    Button(
                        onClick = {
                            scope.launch {
                                /// iterate to next screen
                                val nextPage = selectedPage + 1
                                pagerState.animateScrollToPage(nextPage)
                            }
                        },
                        modifier = Modifier.height(56.dp)
                    ) {
                        Text(text = "Next")
                    }
                }
            }

            /// show only in last page
            if (selectedPage == listData.size - 1) {
                Button(
                    onClick = {
                              navController.navigate("tabScreen")
                        Log.d("Navigation", "Navigating to tabScreen")
                    },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .height(56.dp)
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    Text(text = "Get Started")
                }
            }
        }
    }
}