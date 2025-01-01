package ai.derek.michigo.view.viewmodel

sealed class MainSideEffect {
    data class Toast(val text: String) : MainSideEffect()
    data class ShowWebView(val url: String) : MainSideEffect()
}
