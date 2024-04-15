package com.tugalsan.api.input.server;

import com.tugalsan.api.union.client.TGS_UnionExcuse;
import java.awt.AWTException;
import java.awt.Robot;

public class TS_InputCommonUtils {

    public static TGS_UnionExcuse<Robot> robot() {
        if (robot != null) {
            return robot;
        }
        Robot _robot;
        try {
            _robot = new Robot();
        } catch (AWTException ex) {
            return TGS_UnionExcuse.ofExcuse(ex);
        }
        return robot = TGS_UnionExcuse.of(_robot);
    }
    private static volatile TGS_UnionExcuse<Robot> robot = null;
}
