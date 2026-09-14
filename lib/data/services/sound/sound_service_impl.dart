import 'package:metrotype/data/services/sound/sound_service.dart';
import 'package:native_haptics_and_audio/native_haptics_and_audio.dart';

///
class SoundServiceImpl extends SoundService {
  ///
  new();

  late final NativeHapticsAndAudioRepository _audio;

  final _sounds = <String, Sound>{};

  @override
  Future<void> play(String key) => _audio.play(_sounds[key]!);

  @override
  Future<void> init() {
    _audio = NativeHapticsAndAudioRepository.instance;
    return _audio.initialize();
  }

  @override
  Future<void> load({
    required String key,
    required String asset,
  }) {
    _sounds.addEntries([_toSound(key, asset)]);
    return _audio.preload(CustomSound(asset));
  }

  @override
  Future<void> loadAll(
    Map<String, String> assets,
  ) {
    _sounds.addAll(assets.map(_toSound));
    return _audio.preloadAll(_sounds.entries.map((e) => e.value));
  }

  @override
  Future<void> dispose() => _audio.release();

  MapEntry<String, CustomSound> _toSound(
    String key,
    String value,
  ) => MapEntry(key, CustomSound(value));
}
