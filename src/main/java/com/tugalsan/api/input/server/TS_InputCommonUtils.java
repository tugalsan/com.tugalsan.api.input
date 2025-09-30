package com.tugalsan.api.input.server;

import module com.tugalsan.api.function;
import module java.desktop;

public class TS_InputCommonUtils {
    
    private TS_InputCommonUtils(){
        
    }

    public static Robot robot() {
        if (robot != null) {
            return robot;
        }
        return TGS_FuncMTCUtils.call(() -> new Robot(), e -> null);
    }
    private static volatile Robot robot = null;
}
