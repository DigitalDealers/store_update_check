#import "StoreUpdaterPlugin.h"
#import <store_updater/store_updater-Swift.h>

@implementation StoreUpdaterPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftStoreUpdaterPlugin registerWithRegistrar:registrar];
}
@end
