package ai.derek.michigo.view.ui.dialog

import ai.derek.michigo.R
import ai.derek.michigo.view.ext.bounceClick
import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.hyknowk.presentation.ext.clickableSingle

@Composable
fun WebViewDialog(
    url: String,
    onClose: () -> Unit
) {
    Dialog(
        onDismissRequest = {
            onClose()
        },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        FullScreenWebView(url = url, onClose = onClose)
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun FullScreenWebView(url: String, onClose: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        // WebView 표시
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                WebView(context).apply {
                    webViewClient = WebViewClient()
                    settings.javaScriptEnabled = true
                    loadUrl(url)
                }
            }
        )
        // 상단 닫기 버튼
        Box(
            modifier = Modifier
                .padding(8.dp)
                .bounceClick()
                .background(color = Color(0x3AFFFFFF), shape = RoundedCornerShape(99.dp))
                .padding(4.dp)
                .align(Alignment.TopEnd)
                .clickableSingle(onClick = onClose)
        ) {
            Image(
                painter = painterResource(R.drawable.round_close_24),
                contentDescription = "닫기"
            )
        }
    }
}
