package br.com.softyes.metrotype

import android.graphics.Color
import android.inputmethodservice.InputMethodService
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import io.flutter.embedding.android.FlutterView
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache

class Service : InputMethodService() {
    private var view: FlutterView? = null
    private var engine: FlutterEngine? = null
    private var cache: FlutterEngineCache? = null

    override fun onCreate() {
        super.onCreate()

        cache = FlutterEngineCache.getInstance()
        engine = cache?.get(Application.ENGINE_ID)
        if (engine == null) error("Engine not found")
    }

    override fun onDestroy() {
        view?.detachFromFlutterEngine()
        engine?.lifecycleChannel?.appIsDetached()

        view = null
        engine = null

        super.onDestroy()
    }

    override fun onWindowShown() {
        super.onWindowShown()

        engine?.lifecycleChannel?.appIsResumed()
        engine?.lifecycleChannel?.aWindowIsFocused()
    }

    override fun onWindowHidden() {
        engine?.lifecycleChannel?.noWindowsAreFocused()
        engine?.lifecycleChannel?.appIsPaused()

        super.onWindowHidden()
    }

    override fun onCreateInputView(): View {
        if (engine == null) error("Engine not found")

        val display = resources.displayMetrics

        view = FlutterView(this).apply {
            setBackgroundColor(Color.TRANSPARENT)
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                (display.heightPixels * 0.43).toInt(),
            )
        }

        val container = FrameLayout(this).apply {
            setBackgroundColor(Color.TRANSPARENT)
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,
            )
        }

        view?.attachToFlutterEngine(engine!!)
        container.addView(view)
        return container
    }
}
