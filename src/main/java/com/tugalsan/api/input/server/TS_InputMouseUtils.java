package com.tugalsan.api.input.server;

import java.awt.*;
import java.awt.event.*;
import com.tugalsan.api.shape.client.*;
import com.tugalsan.api.union.client.TGS_UnionExcuseVoid;

public class TS_InputMouseUtils {

    public static TGS_ShapeLocation getLocation() {
        var point = MouseInfo.getPointerInfo().getLocation();
        return new TGS_ShapeLocation(point.x, point.y);
    }

    public static TGS_UnionExcuseVoid mouseMove(int x, int y) {
        var u_robot = TS_InputCommonUtils.robot();
        if (u_robot.isExcuse()) {
            return u_robot.toExcuseVoid();
        }
        var robot = u_robot.value();
        robot.mouseMove(x, y);
        return TGS_UnionExcuseVoid.ofVoid();
    }

    public static TGS_UnionExcuseVoid mouseMove(TGS_ShapeLocation<Integer> loc) {
        return mouseMove(loc.x, loc.y);
    }

    public static TGS_UnionExcuseVoid mousePressLeft() {
        var u_robot = TS_InputCommonUtils.robot();
        if (u_robot.isExcuse()) {
            return u_robot.toExcuseVoid();
        }
        var robot = u_robot.value();
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        return TGS_UnionExcuseVoid.ofVoid();
    }

    public static TGS_UnionExcuseVoid mousePressRelease() {
        var u_robot = TS_InputCommonUtils.robot();
        if (u_robot.isExcuse()) {
            return u_robot.toExcuseVoid();
        }
        var robot = u_robot.value();
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        return TGS_UnionExcuseVoid.ofVoid();
    }

    public static TGS_UnionExcuseVoid mouseClickLeft(TGS_ShapeLocation<Integer> loc) {
        var u_robot = TS_InputCommonUtils.robot();
        if (u_robot.isExcuse()) {
            return u_robot.toExcuseVoid();
        }
        var robot = u_robot.value();
        robot.mouseMove(loc.x, loc.y);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        return TGS_UnionExcuseVoid.ofVoid();
    }

    public static TGS_UnionExcuseVoid mouseClickRight(TGS_ShapeLocation<Integer> loc) {
        var u_robot = TS_InputCommonUtils.robot();
        if (u_robot.isExcuse()) {
            return u_robot.toExcuseVoid();
        }
        var robot = u_robot.value();
        robot.mouseMove(loc.x, loc.y);
        robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
        return TGS_UnionExcuseVoid.ofVoid();
    }
}
