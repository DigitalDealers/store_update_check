package com.didi.store_updater

import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

class StoreUpdaterPlugin(var registrar: Registrar) : MethodCallHandler {
  companion object {
    @JvmStatic
    fun registerWith(registrar: Registrar) {
      val channel = MethodChannel(registrar.messenger(), "store_updater")
      channel.setMethodCallHandler(StoreUpdaterPlugin(registrar))
    }
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    if (call.method == "getPlatformVersion") {
      result.success("Android ${android.os.Build.VERSION.RELEASE}")
    } else if (call.method == "checkUpdate") {
      onCheckUpdate()
    } else {
      result.notImplemented()
    }
  }

  fun onCheckUpdate() {
    val appUpdateManager = AppUpdateManagerFactory.create(registrar.context())
    val appUpdateInfoTask = appUpdateManager.appUpdateInfo
    appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
      if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
      ) {
        appUpdateManager.startUpdateFlowForResult(
                // Pass the intent that is returned by 'getAppUpdateInfo()'.
                appUpdateInfo,
                // Or 'AppUpdateType.FLEXIBLE' for flexible updates.
                AppUpdateType.IMMEDIATE,
                // The current activity making the update request.
                this.registrar.activity(),
                // Include a request code to later monitor this update request.
                333)
      }
    }
  }
}
