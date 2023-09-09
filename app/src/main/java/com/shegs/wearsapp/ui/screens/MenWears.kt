package com.shegs.wearsapp.ui.screens

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shegs.wearsapp.R
import com.shegs.wearsapp.data.MenImageInfo
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalMaterialApi::class
)
@Composable
fun MenWearsScreen() {

    var selectedItemIndex by remember { mutableStateOf(-1) }

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded }
    )
    val coroutineScope = rememberCoroutineScope()

    BackHandler(sheetState.isVisible) {
        coroutineScope.launch { sheetState.hide() }
    }

    val menWears = remember {
        listOf(
            MenImageInfo(R.drawable.image1, "Corporate Wears", "Corporate attire isn't just about clothes; it's about projecting confidence, competence, and ambition. Dressing well is a strategy for success in the business world. Your outfit speaks volumes about your attention to detail and dedication to excellence. So, when you put on your professional attire, remember, it's not just clothing; it's your armor for success."),
            MenImageInfo(R.drawable.image2, "Bucket Hat", "Unleash Your Inner Trendsetter. The bucket hat isn't just headwear; it's a symbol of effortless cool. It's perfect for sunny days, casual outings, or adding a touch of style to any outfit. Wear it with confidence, whether you choose a bold pattern or a classic design. The bucket hat is your canvas for self-expression, a timeless accessory that exudes relaxed charm."),
            MenImageInfo(R.drawable.image3, "Sweatshirt", "Cozy Comfort, Effortless Style. A sweatshirt is more than just cozy; it's a style statement. Slip into one for unmatched comfort on lazy weekends or after a long day. But comfort doesn't mean sacrificing style. With the right fit and design, a sweatshirt can be a trendy addition to your wardrobe. Pair it with jeans, sneakers, and you're effortlessly chic. It's a versatile piece that embraces your laid-back side without compromising on style.")
        )
    }

    val matrix = remember {
        ColorMatrix()
    }

    // Create a new PagerState with the desired initial page
    val pagerState = rememberPagerState(initialPage = 0)

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            if (selectedItemIndex != -1)
            DisplayItemDetails(menWears[selectedItemIndex])
                       },
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        sheetContentColor = MaterialTheme.colorScheme.onPrimary,
        sheetBackgroundColor = MaterialTheme.colorScheme.onPrimaryContainer,
        modifier = Modifier.fillMaxSize()
    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 40.dp, end = 40.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Scaffold(
            modifier = Modifier
                .padding(vertical = 12.dp)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
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
                            .clickable {
                                selectedItemIndex = index // Update the selected item index
                                coroutineScope.launch {
                                    sheetState.show() // Show the bottom sheet
                                }

                            }
                    ) {

                        Image(
                            painter = painterResource(id = imageInfo.imageResource),
                            contentDescription = "Image",
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = 150.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .graphicsLayer {
                                    scaleX = imageSize
                                    scaleY = imageSize
                                },
                            contentScale = ContentScale.Crop,
                            colorFilter = ColorFilter.colorMatrix(matrix)
                        )
                    }

                    Column(

                    ) {
                        Spacer(modifier = Modifier.height(400.dp))
                        Text(
                            text = imageInfo.title,
                            fontSize = 24.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontFamily = FontFamily(Font(R.font.rubik_bold)),
                            fontWeight = FontWeight(600),
                        )
                        Text(
                            text = imageInfo.description,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            lineHeight = 16.sp,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontFamily = FontFamily(Font(R.font.rubik_regular)),
                            fontWeight = FontWeight(300),
                        )
                    }

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
            .fillMaxWidth()
            .padding(start = 32.dp, end = 32.dp, top = 28.dp, bottom = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Browse",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )

        Icon(
            painter = painterResource(id = R.drawable.account_circle),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
        )
    }
}