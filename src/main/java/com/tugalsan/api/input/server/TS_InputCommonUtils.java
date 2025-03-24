package com.tugalsan.api.input.server;

import com.tugalsan.api.function.client.maythrowexceptions.checked.TGS_FuncMTCUtils;
import java.awt.Robot;

public class TS_InputCommonUtils {

    public static Robot robot() {
        if (robot != null) {
            return robot;
        }
        return TGS_FuncMTCUtils.call(() -> new Robot(), e -> null);
    }
    private static volatile Robot robot = null;
}
