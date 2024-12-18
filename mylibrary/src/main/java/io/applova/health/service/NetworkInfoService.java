package io.applova.health.service;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.CellInfo;
import android.telephony.CellSignalStrength;
import android.telephony.TelephonyManager;

import java.util.List;

import io.applova.health.beans.NetworkInfo;

public class NetworkInfoService {

    private final Context context;
    private final TelephonyManager telephonyManager;

    public NetworkInfoService(Context context, TelephonyManager telephonyManager) {
        this.context = context;
        this.telephonyManager = telephonyManager;
    }

    public NetworkInfo getNetworkInfo() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // Default values
        boolean isVPN = false;
        String type = "None";
        String networkQuality = "Unknown";
        int bandwidth = 0;
        String signalStrength = "Unknown";

        android.net.Network network = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            network = connectivityManager.getActiveNetwork();
        }
        NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(network);

        if (capabilities != null) {
            boolean isWifi = capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI);
            boolean isMobile = capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR);
            isVPN = capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN);

            if (isWifi) type = "WiFi";
            else if (isMobile) type = "Mobile";
            else if (isVPN) type = "VPN";

            // Bandwidth and quality determination
            bandwidth = capabilities.getLinkDownstreamBandwidthKbps() / 1000; // Mbps
            networkQuality = getNetworkQuality(bandwidth);
            signalStrength = isWifi ? getWifiSignalStrength() : getMobileSignalStrength();
        }

        return new NetworkInfo(!type.equals("None"), type, isVPN, networkQuality, bandwidth, signalStrength);
    }

    private String getWifiSignalStrength() {
        WifiManager wifiManager = (WifiManager)
                context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int rssi = wifiInfo.getRssi();

        if (rssi >= -50) return "Excellent";
        else if (rssi >= -60) return "Very Good";
        else if (rssi >= -70) return "Good";
        else if (rssi >= -80) return "Fair";
        else return "Poor";
    }

//    private String getMobileSignalStrength() {
//        if (telephonyManager == null) return "Unknown";
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            SignalStrength signalStrength = telephonyManager.getSignalStrength();
//            if (signalStrength == null) return "Unknown";
//
//            int gsmSignalStrength = signalStrength.getGsmSignalStrength();
//            if (gsmSignalStrength != 99) {
//                int dBm = -113 + (2 * gsmSignalStrength);
//                if (dBm >= -70) return "Excellent";
//                else if (dBm >= -85) return "Very Good";
//                else if (dBm >= -100) return "Good";
//                else if (dBm >= -110) return "Fair";
//                else return "Poor";
//            }
//        }
//        return "Unknown";
//    }

    public String getMobileSignalStrength() {
        if (telephonyManager == null) return "Unknown";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            List<CellInfo> cellInfoList = telephonyManager.getAllCellInfo();
            if (cellInfoList != null && !cellInfoList.isEmpty()) {
                for (CellInfo cellInfo : cellInfoList) {
                    if (cellInfo.isRegistered()) {
                        CellSignalStrength signalStrength = null;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            signalStrength = cellInfo.getCellSignalStrength();
                        }
                        assert signalStrength != null;
                        int level = signalStrength.getLevel(); // Level ranges from 0 (poor) to 4 (excellent)

                        switch (level) {
                            case 4: return "Excellent";
                            case 3: return "Very Good";
                            case 2: return "Good";
                            case 1: return "Fair";
                            case 0: return "Poor";
                            default: return "Unknown";
                        }
                    }
                }
            }
        }
        return "Unknown";
    }

    private String getNetworkQuality(int bandwidth) {
        if (bandwidth >= 100) return "Excellent";
        else if (bandwidth >= 50) return "Very Good";
        else if (bandwidth >= 20) return "Good";
        else if (bandwidth >= 10) return "Fair";
        else return "Poor";
    }
}
