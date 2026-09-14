import 'package:metrotype/data/services/service.dart';

/// UI sound service.
abstract class SoundService extends Service {
  /// UI sound service.
  const new();

  /// Plays the sound by registered [key].
  Future<void> play(String key);

  /// Registers a list of sound files from [assets].
  Future<void> loadAll(Map<String, String> assets);

  /// Registers a sound file from [asset].
  Future<void> load({required String key, required String asset});
}
