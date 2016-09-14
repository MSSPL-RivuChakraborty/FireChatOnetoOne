package com.massoftind.rnd.firechatonetoone.webservices;

import android.os.Build;

/**
 * Created by developer on 13/4/16.
 */
public class Constant {


//   public static final String API_BASE_URL = "http://ec2-52-63-90-54.ap-southeast-2.compute.amazonaws.com/server/api_1_0_0/user/";//"https://prgwave.whstl.com/server/api_1_0_0/user/";//
       public static final String API_BASE_URL="https://prgwave.whstl.com/server/api_1_0_0/user/";


    public static final String NETWORK_SWITCH_FILTER = "co.nz.quantiful.wave.NETWORK_SWITCH_FILTER";

    public static class CryptoKey{
        public static final String CRYPTO_COLUMN = "WAVE_android"+ Build.MODEL+"@columns";
        public static final String CRYPTO_VALUE = "WAVE_android"+ Build.MODEL+"@values";
    }

    public static class Config {
        public static final boolean DEVELOPER_MODE = false;
    }

}
