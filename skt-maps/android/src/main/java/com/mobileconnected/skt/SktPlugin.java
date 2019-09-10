package com.mobileconnected.skt;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.concurrent.atomic.AtomicInteger;

import io.flutter.plugin.common.PluginRegistry.Registrar;

public class SktPlugin implements Application.ActivityLifecycleCallbacks {

    private static final int CREATED = 1;
    private static final int STARTED = 2;
    private static final int RESUMED = 3;
    private static final int PAUSED = 4;
    private static final int STOPPED = 5;
    private static final int DESTROYED = 6;

    private final AtomicInteger state = new AtomicInteger(0);
    private final int registrarActivityHashCode;

    public static void registerWith(Registrar registrar) {
        if (registrar.activity() == null) {
            return;
        }

        final SktPlugin plugin = new SktPlugin(registrar);

        registrar.activity()
                 .getApplication()
                 .registerActivityLifecycleCallbacks(plugin);

        registrar.platformViewRegistry()
                 .registerViewFactory("SKTMap", new MapFactory(registrar));
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (activity.hashCode() != registrarActivityHashCode) {
            return;
        }
        state.set(CREATED);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        if (activity.hashCode() != registrarActivityHashCode) {
            return;
        }
        state.set(STARTED);
    }

    @Override
    public void onActivityResumed(Activity activity) {
        if (activity.hashCode() != registrarActivityHashCode) {
            return;
        }
        state.set(RESUMED);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        if (activity.hashCode() != registrarActivityHashCode) {
            return;
        }
        state.set(PAUSED);
    }

    @Override
    public void onActivityStopped(Activity activity) {
        if (activity.hashCode() != registrarActivityHashCode) {
            return;
        }
        state.set(STOPPED);
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        // No operation
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        if (activity.hashCode() != registrarActivityHashCode) {
            return;
        }
        state.set(DESTROYED);
    }

    // Private Methods

    private SktPlugin(Registrar registrar) {
        this.registrarActivityHashCode = registrar.activity().hashCode();
    }
}
