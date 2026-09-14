/// Represents a service.
abstract class Service {
  /// Represents a service.
  const new();

  /// Initializes the service.
  Future<void> init();

  /// Disposes the service.
  Future<void> dispose();
}
