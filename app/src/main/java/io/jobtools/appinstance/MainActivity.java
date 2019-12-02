package io.jobtools.appinstance;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import io.jobtools.app_instance.AppInstance;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppInstance.getApplicationContext();
        AppInstance.terminate(this);
    }
}
