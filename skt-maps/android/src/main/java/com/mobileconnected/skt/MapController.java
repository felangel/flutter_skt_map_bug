package com.mobileconnected.skt;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.View;

import com.skt.Tmap.TMapView;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugin.platform.PlatformView;

final class MapController implements PlatformView, MethodCallHandler {
    private final Context context;
    private final PluginRegistry.Registrar registrar;
    private final MethodChannel channel;
    private final TMapView tmapView;

    MapController(int id, Context context, PluginRegistry.Registrar registrar) {
        this.context = context;
        this.registrar = registrar;
        this.channel = new MethodChannel(registrar.messenger(), "SKTMap_" + id);
        this.channel.setMethodCallHandler(this);        

        // Setup TMapView
        this.tmapView = new TMapView(context);
        tmapView.setCenterPoint(126.9780, 37.5665);
        tmapView.setZoomLevel(15);

        try {
            ApplicationInfo appInfo = this.context.getPackageManager().getApplicationInfo(
                    this.context.getPackageName(), PackageManager.GET_META_DATA);
            this.tmapView.setSKTMapApiKey(appInfo.metaData.getString("com.skt.android.geo.API_KEY"));
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Error retrieving application metadata", e);
        }

        this.tmapView.setHttpsMode(true);            
    }

    @Override
    public View getView() {
        return tmapView;
    }

    @Override
    public void dispose() {
        // No operation
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        switch (call.method) {
            default:
                result.notImplemented();
        }
    }
}
