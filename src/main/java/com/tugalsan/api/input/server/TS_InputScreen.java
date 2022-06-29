package com.tugalsan.api.input.server;

import java.awt.*;
import java.awt.image.*;

public class TS_InputScreen {

    public static Rectangle size() {
        var localGrapicsEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        var screensDevices = localGrapicsEnv.getScreenDevices();
        Rectangle allScreenBounds = new Rectangle();
        for (GraphicsDevice screen : screensDevices) {
            Rectangle screenBounds = screen.getDefaultConfiguration().getBounds();
            allScreenBounds.width += screenBounds.width;
            allScreenBounds.height = Math.max(allScreenBounds.height, screenBounds.height);
        }
        return allScreenBounds;
    }

    public BufferedImage shot(Rectangle size) throws Exception {
        return new Robot().createScreenCapture(size);
    }
}
