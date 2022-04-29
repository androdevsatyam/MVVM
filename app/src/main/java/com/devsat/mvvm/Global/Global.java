package com.devsat.mvvm.Global;

import com.devsat.mvvm.apicall.Connection;
import com.devsat.mvvm.apicall.ConnectionInterface;

public class Global {

    public static String Base_Url="https://veramobile.mios.com/test_android/";

    public static ConnectionInterface getApiCon(){
        return Connection.getCon().create(ConnectionInterface.class);
    }
}
