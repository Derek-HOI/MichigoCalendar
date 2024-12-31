package ai.derek.michigo.view.ext

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "Light Mode",
    showBackground = true,
    backgroundColor = 0xFFF8F8F8
)
@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    backgroundColor = 0xFF121212
)
annotation class MichigoPreview

@Preview(
    name = "Light Mode",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    backgroundColor = 0xFF1A1A1A
)
annotation class MichigoDialogPreview

@Preview(name = "Full Preview", showSystemUi = true)
annotation class PreviewWithSystemUi
