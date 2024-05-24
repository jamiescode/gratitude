package uk.co.jamiecruwys.gratitude.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.asFlow
import uk.co.jamiecruwys.gratitude.presentation.screen.composable.gratitudeInput
import uk.co.jamiecruwys.gratitude.presentation.screen.composable.gratitudeListState

@Composable
fun gratitudeScreen(viewModel: GratitudeViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.loadEntries()
    }

    val state =
        viewModel.stateLiveData.asFlow().collectAsState(
            initial = GratitudeViewModel.State.Initial,
        )

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        gratitudeListState(
            listModifier = Modifier.fillMaxWidth().weight(1f).verticalScroll(rememberScrollState()),
            state = state.value,
        )
        gratitudeInput(viewModel)
    }
}