package blog.recomposed.demosrecomposed.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import blog.recomposed.demosrecomposed.navigation.destinationScreens
import blog.recomposed.demosrecomposed.ui.theme.DemosRecomposedTheme

@Composable
fun MainScreen(
    onScreenSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(destinationScreens) { screen ->
            Button(
                onClick = { onScreenSelected(screen.route) },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(screen.title)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    DemosRecomposedTheme {
        MainScreen(onScreenSelected = {})
    }
}
