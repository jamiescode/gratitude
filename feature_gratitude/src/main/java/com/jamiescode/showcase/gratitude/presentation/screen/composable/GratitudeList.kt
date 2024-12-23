package com.jamiescode.showcase.gratitude.presentation.screen.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jamiescode.showcase.gratitude.domain.model.GratitudeEntry
import com.jamiescode.showcase.gratitude.domain.model.GratitudeGroupDate
import com.jamiescode.showcase.gratitude.presentation.screen.GratitudeViewModel
import com.jamiescode.showcase.theme.getListColors
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun gratitudeList(
    modifier: Modifier,
    groupedEntries: Map<String, List<GratitudeEntry>>,
    scrollState: GratitudeViewModel.ScrollState,
    viewModel: GratitudeViewModel,
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(System.currentTimeMillis()) {
        when (scrollState) {
            GratitudeViewModel.ScrollState.Scroll -> {
                coroutineScope.launch {
                    listState.animateScrollToItem(0)
                }
            }
            GratitudeViewModel.ScrollState.Idle -> {} // No action
        }
    }

    val listColors = getListColors()
    val groupDate = GratitudeGroupDate()
    LazyColumn(modifier = modifier, state = listState, contentPadding = PaddingValues(bottom = 8.dp)) {
        var currentIndex = 0
        groupedEntries.entries.forEach { map ->
            val dateString = map.key
            val entries = map.value

            val date = groupDate.fromDateString(dateString)
            stickyHeader {
                gratitudeDateDivider(date = date, dateStringFallback = dateString)
            }
            items(
                items = entries,
                key = { it.id },
            ) { entry ->
                val backgroundColor = getColorForIndex(currentIndex, listColors)
                gratitudeRow(
                    gratitudeEntry = entry,
                    backgroundColor = backgroundColor,
                    onEditEntry = {},
                    onRemoveEntry = { gratitudeEntry ->
                        viewModel.deleteEntry(gratitudeEntry)
                    },
                )
                currentIndex++
            }
        }
    }
}

fun getColorForIndex(
    index: Int,
    listColors: List<Color>,
): Color {
    val remainder = index % listColors.size
    val colorIndex = listColors.size - remainder - 1
    return listColors[colorIndex]
}
