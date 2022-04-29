package com.devsat.mvvm.model;

public class DevicesModel {
    String PK_Device,MacAddress,PK_DeviceType,PK_DeviceSubType,Firmware,Server_Device,Server_Event,Server_Account,InternalIP,LastAliveReported,Platform;

    public DevicesModel(String PK_Device, String macAddress, String PK_DeviceType, String PK_DeviceSubType, String firmware, String server_Device, String server_Event, String server_Account, String internalIP, String lastAliveReported, String platform) {
        this.PK_Device = PK_Device;
        MacAddress = macAddress;
        this.PK_DeviceType = PK_DeviceType;
        this.PK_DeviceSubType = PK_DeviceSubType;
        Firmware = firmware;
        Server_Device = server_Device;
        Server_Event = server_Event;
        Server_Account = server_Account;
        InternalIP = internalIP;
        LastAliveReported = lastAliveReported;
        Platform = platform;
    }


    public void setPlatform(String platform) {
        Platform = platform;
    }

    public String getPK_Device() {
        return PK_Device;
    }

    public String getMacAddress() {
        return MacAddress;
    }

    public String getPK_DeviceType() {
        return PK_DeviceType;
    }

    public String getPK_DeviceSubType() {
        return PK_DeviceSubType;
    }

    public String getFirmware() {
        return Firmware;
    }

    public String getServer_Device() {
        return Server_Device;
    }

    public String getServer_Event() {
        return Server_Event;
    }

    public String getServer_Account() {
        return Server_Account;
    }

    public String getInternalIP() {
        return InternalIP;
    }

    public String getLastAliveReported() {
        return LastAliveReported;
    }

    public String getPlatform() {
        return Platform;
    }
}
