package io.applova.health.helper;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

import io.applova.health.beans.AllMetricsInfo;

public class MetricsFormatter {

    public static String formatToRequest(AllMetricsInfo allMetricsInfo) {
        try {
            // Create a map to hold the formatted data
            Map<String, Object> formattedData = new HashMap<>();

            // Extract data from BatteryInfo
            if (allMetricsInfo.getBatteryInfo() != null) {
                formattedData.put("batteryLevel", allMetricsInfo.getBatteryInfo().getLevel());
                formattedData.put("isCharging", allMetricsInfo.getBatteryInfo().isCharging());
                formattedData.put("batteryTemperature", allMetricsInfo.getBatteryInfo().getTemperature());
                formattedData.put("batteryVoltage", allMetricsInfo.getBatteryInfo().getVoltage());
            }

            // Extract data from ClientInfo
            if (allMetricsInfo.getClientInfo() != null) {
                formattedData.put("deviceId", allMetricsInfo.getClientInfo().getDeviceId());
                formattedData.put("businessId", allMetricsInfo.getClientInfo().getBusinessId());
            }

            // Extract data from NetworkInfo
            if (allMetricsInfo.getNetworkInfo() != null) {
                formattedData.put("isConnected", allMetricsInfo.getNetworkInfo().isConnected());
                formattedData.put("networkType", allMetricsInfo.getNetworkInfo().getNetworkType());
                formattedData.put("networkQuality", allMetricsInfo.getNetworkInfo().getNetworkQuality());
                formattedData.put("bandwidth", allMetricsInfo.getNetworkInfo().getBandwidth());
                formattedData.put("signalStrength", allMetricsInfo.getNetworkInfo().getSignalStrength());
            }

            // Extract data from StorageInfo
            if (allMetricsInfo.getStorageInfo() != null) {
                formattedData.put("totalInternalStorage", allMetricsInfo.getStorageInfo().getTotalInternalStorage());
                formattedData.put("availableInternalStorage", allMetricsInfo.getStorageInfo().getAvailableInternalStorage());
                formattedData.put("totalExternalStorage", allMetricsInfo.getStorageInfo().getTotalExternalStorage());
                formattedData.put("availableExternalStorage", allMetricsInfo.getStorageInfo().getAvailableExternalStorage());
                formattedData.put("appSize", allMetricsInfo.getStorageInfo().getAppSize());
                formattedData.put("userData", allMetricsInfo.getStorageInfo().getUserData());
                formattedData.put("cacheSize", allMetricsInfo.getStorageInfo().getCacheSize());
                formattedData.put("totalAppSize", allMetricsInfo.getStorageInfo().getTotalAppSize());
                formattedData.put("realmSize", allMetricsInfo.getStorageInfo().getRealmDatabaseSize());
            }

            // Extract data from DeviceInfo
            if (allMetricsInfo.getDeviceInfo() != null) {
                formattedData.put("manufacturer", allMetricsInfo.getDeviceInfo().getManufacturer());
                formattedData.put("model", allMetricsInfo.getDeviceInfo().getModel());
                formattedData.put("androidVersion", allMetricsInfo.getDeviceInfo().getAndroidVersion());
                formattedData.put("sdkVersion", allMetricsInfo.getDeviceInfo().getSdkVersion());
            }

            // Convert to JSON using Jackson
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(formattedData);

        } catch (Exception e) {
            throw new RuntimeException("Failed to format metrics data to JSON", e);
        }
    }

    private void pushDeviceInfoToAPI(String ID, String totalRam, String availableRam, String usedRam) {

    }

}
