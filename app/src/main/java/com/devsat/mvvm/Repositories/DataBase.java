package com.devsat.mvvm.Repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.devsat.mvvm.model.DevicesModel;

import java.util.List;

public class DataBase {

    DataBaseHelper helper;
    SQLiteDatabase database;

    public DataBase(Context context) {
        helper = new DataBaseHelper(context, "Practical", null, 1);
    }

    public void insert(List<DevicesModel> models) {
        ContentValues values = new ContentValues();
        database = helper.getWritableDatabase();
        for (DevicesModel devicesModel : models) {
            values.put(helper.PK_Device, devicesModel.getPK_Device());
            values.put(helper.MacAddress, devicesModel.getMacAddress());
            values.put(helper.Firmware, devicesModel.getFirmware());
            values.put(helper.PK_DeviceType, devicesModel.getPK_DeviceType());
            values.put(helper.PK_DeviceSubType, devicesModel.getPK_DeviceSubType());
            values.put(helper.Server_Device, devicesModel.getServer_Device());
            values.put(helper.Server_Event, devicesModel.getServer_Event());
            values.put(helper.Server_Account, devicesModel.getServer_Account());
            values.put(helper.InternalIP, devicesModel.getInternalIP());
            values.put(helper.LastAliveReported, devicesModel.getLastAliveReported());
            values.put(helper.Platform, devicesModel.getPlatform());
        }

        database.insert(helper.tableDevice, null, values);
        database.close();
    }


    public List<DevicesModel> getDevies() {
        database = helper.getReadableDatabase();
        DevicesModel devicesModel;
        List<DevicesModel> list = null;
        String query = "SELECT * FROM " + helper.tableDevice;
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                devicesModel = new DevicesModel(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(11));
                list.add(devicesModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return list;
    }


    public void update(DevicesModel devicesModel) {
        ContentValues values = new ContentValues();
        database = helper.getWritableDatabase();
        values.put(helper.PK_Device, devicesModel.getPK_Device());
        values.put(helper.MacAddress, devicesModel.getMacAddress());
        values.put(helper.Firmware, devicesModel.getFirmware());
        values.put(helper.PK_DeviceType, devicesModel.getPK_DeviceType());
        values.put(helper.PK_DeviceSubType, devicesModel.getPK_DeviceSubType());
        values.put(helper.Server_Device, devicesModel.getServer_Device());
        values.put(helper.Server_Event, devicesModel.getServer_Event());
        values.put(helper.Server_Account, devicesModel.getServer_Account());
        values.put(helper.InternalIP, devicesModel.getInternalIP());
        values.put(helper.LastAliveReported, devicesModel.getLastAliveReported());
        values.put(helper.Platform, devicesModel.getPlatform());

        database.update(helper.tableDevice, values, helper.PK_Device + " = " + devicesModel.getPK_Device(), null);
        database.close();
    }

    public class DataBaseHelper extends SQLiteOpenHelper {

        String PK_Device = "PK_Device", MacAddress = "MacAddress", PK_DeviceType = "PK_DeviceType", PK_DeviceSubType = "PK_DeviceSubType", Firmware = "Firmware", Server_Device = "Server_Device", Server_Event = "Server_Event", Server_Account = "Server_Account", InternalIP = "InternalIP", LastAliveReported = "LastAliveReported", Platform = "Platform";
        String tableDevice = "TableDevice", createtbl = "Create table " + tableDevice + " ( " + PK_Device + " text, " + MacAddress + " text, " + PK_DeviceType + " text, " + PK_DeviceSubType + "text, " + Firmware + " text, " + Server_Device + " text, " + Server_Event + " text, " + Server_Account + " text, " + InternalIP + " text, " + LastAliveReported + " text, " + Platform + " text)";


        public DataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(createtbl);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
