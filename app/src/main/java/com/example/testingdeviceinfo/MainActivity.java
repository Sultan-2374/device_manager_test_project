package com.example.testingdeviceinfo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import io.applova.health.beans.ClientInfo;
import io.applova.health.job.DeviceInfoJob;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Realm
        Realm.init(this);

        // Create and initialize ClientInfo
        ClientInfo clientInfo = new ClientInfo();
        clientInfo.setContext(this);

        // Create and initialize DeviceInfoJob
        DeviceInfoJob deviceInfoJob = new DeviceInfoJob();
        deviceInfoJob.initialize(clientInfo);  // Schedule job on app start
    }
}
