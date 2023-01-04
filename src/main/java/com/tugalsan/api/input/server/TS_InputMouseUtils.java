package com.tugalsan.api.input.server;

import java.awt.*;
import java.awt.event.*;
import com.tugalsan.api.shape.client.*;
import com.tugalsan.api.unsafe.client.*;

public class TS_InputMouseUtils {

    public static TGS_ShapeLocation getLocation() {
        var point = MouseInfo.getPointerInfo().getLocation();
        return new TGS_ShapeLocation(point.x, point.y);
    }

    public static void mouseMove(TGS_ShapeLocation<Integer> loc) {
        TGS_UnSafe.execute(() -> {
            var bot = new Robot();
            bot.mouseMove(loc.x, loc.y);
        });
    }

    public static void mousePressLeft(TGS_ShapeLocation<Integer> loc) {
        TGS_UnSafe.execute(() -> {
            var bot = new Robot();
            bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            bot.mouseMove(loc.x, loc.y);
        });
    }

    public static void mousePressRelease(TGS_ShapeLocation<Integer> loc) {
        TGS_UnSafe.execute(() -> {
            var bot = new Robot();
            bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            bot.mouseMove(loc.x, loc.y);
        });
    }
    
    public static void mouseClickLeft(TGS_ShapeLocation<Integer> loc) {
        TGS_UnSafe.execute(() -> {
            var bot = new Robot();
            bot.mouseMove(loc.x, loc.y);
            bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        });
    }

    public static void mouseClickRight(TGS_ShapeLocation<Integer> loc) {
        TGS_UnSafe.execute(() -> {
            var bot = new Robot();
            bot.mouseMove(loc.x, loc.y);
            bot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
            bot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
        });
    }
}
