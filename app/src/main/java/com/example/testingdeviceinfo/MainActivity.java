package com.example.testingdeviceinfo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

        // Get the button from the layout
        Button btnTriggerJob = findViewById(R.id.btnTriggerJob);

        // Pass the button to the reusable method
        setupJobTriggerButton(btnTriggerJob);
    }

    /**
     * Sets up a button to trigger the DeviceInfoJob when clicked.
     *
     * @param button The button to set the click listener on.
     */
    private void setupJobTriggerButton(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClientInfo clientInfo = new ClientInfo();
                clientInfo.setContext(MainActivity.this);

                DeviceInfoJob deviceInfoJob = new DeviceInfoJob();
                deviceInfoJob.initialize(clientInfo);

                Toast.makeText(MainActivity.this, "Job triggered!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

