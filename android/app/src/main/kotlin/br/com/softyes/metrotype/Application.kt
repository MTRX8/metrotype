package br.com.softyes.metrotype

import android.app.Application
import io.flutter.FlutterInjector
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

class Application : Application() {
    companion object {
        const val ENGINE_ID = "metrotype_engine"
    }

    override fun onCreate() {
        super.onCreate()

        val loader = FlutterInjector.instance().flutterLoader()
        loader.startInitialization(this)
        loader.ensureInitializationComplete(this, null)

        val engine = FlutterEngine(this)
        val cache = FlutterEngineCache.getInstance()
        val entrypoint = DartExecutor.DartEntrypoint(
            loader.findAppBundlePath(),
            "imeMain"
        )

        engine.dartExecutor.executeDartEntrypoint(entrypoint)
        cache.put(ENGINE_ID, engine)
    }
}