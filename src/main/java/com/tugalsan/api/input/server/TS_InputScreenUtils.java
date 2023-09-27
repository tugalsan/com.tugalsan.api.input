package com.tugalsan.api.input.server;

import java.awt.*;
import java.awt.image.*;

public class TS_InputScreenUtils {

//    public static Rectangle size() {
//        var localGrapicsEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        var screensDevices = localGrapicsEnv.getScreenDevices();
//        var allScreenBounds = new Rectangle();
//        for (var screen : screensDevices) {
//            var screenBounds = screen.getDefaultConfiguration().getBounds();
//            allScreenBounds.width += screenBounds.width;
//            allScreenBounds.height = Math.max(allScreenBounds.height, screenBounds.height);
//        }
//        return allScreenBounds;
//    }
    public static Rectangle size(float scale) {
        var r = size();
        r.x = (int) (r.x * scale);
        r.y = (int) (r.y * scale);
        r.width = (int) (r.width * scale);
        r.height = (int) (r.height * scale);
        return r;
    }

    public static Rectangle size() {
        var rectangle = new Rectangle(0, 0, 0, 0);
        for (var graphicsDevice : GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()) {
            rectangle = rectangle.union(graphicsDevice.getDefaultConfiguration().getBounds());
        }
        return rectangle;
    }

    public static float scale() {
        return Toolkit.getDefaultToolkit().getScreenResolution() / 96f;
    }

    public static BufferedImage shotPicture(Rectangle size) {
        return TS_InputCommonUtils.robot().createScreenCapture(size);
    }
}
