import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:store_updater/store_updater.dart';

void main() {
  const MethodChannel channel = MethodChannel('store_updater');

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await StoreUpdater.platformVersion, '42');
  });
}
