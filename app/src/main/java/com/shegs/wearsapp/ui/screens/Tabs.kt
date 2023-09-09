package com.shegs.wearsapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Tab
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.shegs.wearsapp.data.TabItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainContent() {
    val list = listOf(TabItem.MenWears, TabItem.WomenWears, TabItem.FootWears)
    val pagerState = rememberPagerState(initialPage = 0)


    Column(modifier = Modifier.fillMaxSize()) {
        Tabs(tabs = list, pagerState = pagerState)
        TabContent(tabs = list, pagerState = pagerState)
    }

}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {

    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        containerColor = Color.White,
        indicator = { tabPositions ->
            Modifier.height(0.dp)

        },
        modifier = Modifier.background(Color.Transparent)
        ) {
        tabs.forEachIndexed { index, tabItem ->

            Tab(
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = { Text(
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Start,
                    text = tabItem.title,
                    style = TextStyle(
                        color = if (pagerState.currentPage == index) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.outlineVariant
                    )
                ) },
                selectedContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                unselectedContentColor = MaterialTheme.colorScheme.outlineVariant,
                enabled = true
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabContent(tabs:List<TabItem>, pagerState: PagerState) {
    HorizontalPager(count = tabs.size,state=pagerState) { page ->
        tabs[page].screens()

    }
}