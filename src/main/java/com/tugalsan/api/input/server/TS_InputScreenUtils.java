package com.tugalsan.api.input.server;

import java.awt.*;
import java.awt.image.*;

public class TS_InputScreenUtils {

    public static Rectangle size() {
        var localGrapicsEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        var screensDevices = localGrapicsEnv.getScreenDevices();
        var allScreenBounds = new Rectangle();
        for (var screen : screensDevices) {
            var screenBounds = screen.getDefaultConfiguration().getBounds();
            allScreenBounds.width += screenBounds.width;
            allScreenBounds.height = Math.max(allScreenBounds.height, screenBounds.height);
        }
        return allScreenBounds;
    }

    public static BufferedImage shotPicture(Rectangle size) {
        return TS_InputCommonUtils.robot().createScreenCapture(size);
    }
}
