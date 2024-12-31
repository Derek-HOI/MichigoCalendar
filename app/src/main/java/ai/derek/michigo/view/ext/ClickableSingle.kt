package com.hyknowk.presentation.ext

import android.annotation.SuppressLint
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role

/**
 * clickable single
 * ref.
 * https://velog.io/@silmxmail/Jetpack-Compose%EC%97%90%EC%84%9C-%EB%8B%A4%EC%A4%91-%ED%81%B4%EB%A6%AD-%EB%B0%A9%EC%A7%80
 */
@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.clickableSingle(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit
) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "clickable"
        properties["enabled"] = enabled
        properties["onClickLabel"] = onClickLabel
        properties["role"] = role
        properties["onClick"] = onClick
    }
) {
    multipleEventsCutter { manager ->
        Modifier.clickable(
            enabled = enabled,
            onClickLabel = onClickLabel,
            onClick = { manager.processEvent { onClick() } },
            role = role,
            indication = LocalIndication.current,
            interactionSource = remember { MutableInteractionSource() }
        )
    }
}
