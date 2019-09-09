import 'dart:async';

import 'package:flutter/services.dart';

class StoreUpdater {
  static const MethodChannel _channel =
      const MethodChannel('store_updater');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static void checkUpdate(){
    _channel.invokeMethod('checkUpdate');
  }
}
