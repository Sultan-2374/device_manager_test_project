package io.applova.health.beans;

public class NetworkInfo {
    private final boolean isConnected;
    private final String networkType;
    private final boolean isVPN;
    private final String networkQuality;
    private final int bandwidth;
    private final String signalStrength;

    public NetworkInfo(boolean isConnected, String networkType,
                       boolean isVPN, String networkQuality, int bandwidth, String signalStrength) {
        this.isConnected = isConnected;
        this.networkType = networkType;
        this.isVPN = isVPN;
        this.networkQuality = networkQuality;
        this.bandwidth = bandwidth;
        this.signalStrength = signalStrength;
    }

    // Getters
    public boolean isConnected() { return isConnected; }
    public String getNetworkType() { return networkType; }
    public boolean isVPN() { return isVPN; }
    public String getNetworkQuality() { return networkQuality; }
    public int getBandwidth() { return bandwidth; }
    public String getSignalStrength() { return signalStrength; }

    @Override
    public String toString() {
        return "NetworkInfo{" +
                "isConnected:" + isConnected +
                ", networkType:'" + networkType + '\'' +
                ", isVPN:" + isVPN +
                ", networkQuality:'" + networkQuality + '\'' +
                ", bandwidth:" + bandwidth +
                ", signalStrength:'" + signalStrength + '\'' +
                '}';
    }
}
