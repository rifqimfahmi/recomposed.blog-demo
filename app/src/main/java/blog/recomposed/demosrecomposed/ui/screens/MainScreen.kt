package blog.recomposed.demosrecomposed.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        destinationScreens.forEach { screen ->
            Button(onClick = { onScreenSelected(screen.route) }) {
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
