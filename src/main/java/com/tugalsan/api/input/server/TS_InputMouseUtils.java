package com.tugalsan.api.input.server;

import com.tugalsan.api.function.client.maythrow.checkedexceptions.TGS_FuncMTCEUtils;
import java.awt.*;
import java.awt.event.*;
import com.tugalsan.api.shape.client.*;


public class TS_InputMouseUtils {

    public static TGS_ShapeLocation getLocation() {
        var point = MouseInfo.getPointerInfo().getLocation();
        return new TGS_ShapeLocation(point.x, point.y);
    }

    public static void mouseMove(int x, int y) {
        TGS_FuncMTCEUtils.run(() -> {
            var robot = TS_InputCommonUtils.robot();
            robot.mouseMove(x, y);
        });
    }

    public static void mouseMove(TGS_ShapeLocation<Integer> loc) {
        mouseMove(loc.x, loc.y);
    }

    public static void mousePressLeft() {
        TGS_FuncMTCEUtils.run(() -> {
            var robot = TS_InputCommonUtils.robot();
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        });
    }

    public static void mousePressRelease() {
        TGS_FuncMTCEUtils.run(() -> {
            var robot = TS_InputCommonUtils.robot();
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        });
    }

    public static void mouseClickLeft(TGS_ShapeLocation<Integer> loc) {
        TGS_FuncMTCEUtils.run(() -> {
            var robot = TS_InputCommonUtils.robot();
            robot.mouseMove(loc.x, loc.y);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        });
    }

    public static void mouseClickRight(TGS_ShapeLocation<Integer> loc) {
        TGS_FuncMTCEUtils.run(() -> {
            var robot = TS_InputCommonUtils.robot();
            robot.mouseMove(loc.x, loc.y);
            robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
        });
    }
}
