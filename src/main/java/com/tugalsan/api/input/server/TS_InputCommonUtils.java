package com.tugalsan.api.input.server;

import com.tugalsan.api.function.client.maythrow.checkedexceptions.TGS_FuncMTCEUtils;
import java.awt.Robot;

public class TS_InputCommonUtils {

    public static Robot robot() {
        if (robot != null) {
            return robot;
        }
        return TGS_FuncMTCEUtils.call(() -> new Robot(), e -> null);
    }
    private static volatile Robot robot = null;
}
