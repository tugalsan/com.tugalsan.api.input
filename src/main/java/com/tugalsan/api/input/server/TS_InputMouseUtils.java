package com.tugalsan.api.input.server;

import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import com.tugalsan.api.shape.client.TGS_ShapeLocation;
import com.tugalsan.api.thread.server.TS_ThreadRunUtils;

public class TS_InputMouseUtils {

    public static TGS_ShapeLocation getLocation() {
        var point = MouseInfo.getPointerInfo().getLocation();
        return new TGS_ShapeLocation(point.x, point.y);
    }

    public static void mouseClickLeft(TGS_ShapeLocation<Integer> loc) {
        try {
            var bot = new Robot();
            bot.mouseMove(loc.x, loc.y);
            bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void mouseClickRight(TGS_ShapeLocation<Integer> loc) {
        try {
            var bot = new Robot();
            bot.mouseMove(loc.x, loc.y);
            bot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
            bot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
//    public static void main(String[] s) {
//        while (true) {
//            TS_ThreadUtils.waitForSeconds(2);
//            System.out.println(getLocation());
//        }
//    }
}
