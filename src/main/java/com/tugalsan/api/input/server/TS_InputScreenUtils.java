package com.tugalsan.api.input.server;

import com.tugalsan.api.unsafe.client.*;
import java.awt.*;
import java.awt.image.*;

public class TS_InputScreenUtils {

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

    public static BufferedImage shotPicture(Rectangle size) {
        return robot().createScreenCapture(size);
    }

    public static Robot robot() {
        return TGS_UnSafe.call(() -> new Robot());
    }

    public static BufferedImage shotPictures(Robot robot, Rectangle size) {
        return robot.createScreenCapture(size);
    }
}
