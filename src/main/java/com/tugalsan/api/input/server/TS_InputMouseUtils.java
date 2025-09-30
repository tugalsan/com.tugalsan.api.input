package com.tugalsan.api.input.server;

import module com.tugalsan.api.function;
import module com.tugalsan.api.shape;
import module java.desktop;

public class TS_InputMouseUtils {

    private TS_InputMouseUtils() {

    }

    public static TGS_ShapeLocation getLocation() {
        var point = MouseInfo.getPointerInfo().getLocation();
        return new TGS_ShapeLocation(point.x, point.y);
    }

    public static void mouseMove(int x, int y) {
        TGS_FuncMTCUtils.run(() -> {
            var robot = TS_InputCommonUtils.robot();
            robot.mouseMove(x, y);
        });
    }

    public static void mouseMove(TGS_ShapeLocation<Integer> loc) {
        mouseMove(loc.x, loc.y);
    }

    public static void mousePressLeft() {
        TGS_FuncMTCUtils.run(() -> {
            var robot = TS_InputCommonUtils.robot();
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        });
    }

    public static void mousePressRelease() {
        TGS_FuncMTCUtils.run(() -> {
            var robot = TS_InputCommonUtils.robot();
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        });
    }

    public static void mouseClickLeft(TGS_ShapeLocation<Integer> loc) {
        TGS_FuncMTCUtils.run(() -> {
            var robot = TS_InputCommonUtils.robot();
            robot.mouseMove(loc.x, loc.y);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        });
    }

    public static void mouseClickRight(TGS_ShapeLocation<Integer> loc) {
        TGS_FuncMTCUtils.run(() -> {
            var robot = TS_InputCommonUtils.robot();
            robot.mouseMove(loc.x, loc.y);
            robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
        });
    }
}
