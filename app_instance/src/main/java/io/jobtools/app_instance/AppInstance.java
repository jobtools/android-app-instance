package io.jobtools.app_instance;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;

public class AppInstance {

    private static WeakReference<Application> instance = new WeakReference<>(null);

    public static Application get() {
        if (instance.get() == null) {
            instance = new WeakReference<>(getApplication());
        }

        return instance.get();
    }

    private static Application getApplication()
    {
        try {
            Class className = Class.forName("android.app.ActivityThread");
            Method method = className.getMethod("currentApplication");
            Object result = method.invoke(null, 0);

            return (Application)result;
        } catch (Throwable ignored) {
            Log.e("AppInstance", "You can't use AppInstance in your system.");
            return null;
        }
    }

    public static Context getApplicationContext() {
        return get().getApplicationContext();
    }

    public static void restart(Class startingActivityClass) {
        reserveStartActivity(get(), startingActivityClass);
        System.exit(0);
    }

    private static void reserveStartActivity(Context context, Class startingActivityClass) {
        Intent startingActivity = new Intent(context, startingActivityClass);
        int mPendingIntentId = 123456;
        PendingIntent mPendingIntent = PendingIntent.getActivity(context, mPendingIntentId, startingActivity,
                PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
    }

    public static void terminate(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            activity.finishAffinity();
        }
        System.exit(0);
    }
}