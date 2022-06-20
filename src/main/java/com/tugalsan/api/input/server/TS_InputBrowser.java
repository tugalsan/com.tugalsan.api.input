package com.tugalsan.api.input.server;

import com.tugalsan.api.shape.client.TGS_ShapeLocation;
import com.tugalsan.api.thread.server.TS_ThreadRunUtils;
import com.tugalsan.api.file.sound.server.TS_FileSoundUtils;
import com.tugalsan.api.ide.netbeans.server.TS_NetbeansUtils;
import com.tugalsan.api.thread.server.*;
import java.util.Arrays;
import java.util.Objects;

public class TS_InputBrowser {

    public static void refreshServerBrowser(TS_ThreadKillableInterface killable, String username, String password) {
        if (!TS_NetbeansUtils.isNetbeansExists()) {
            return;
        }
        var tag = TS_InputBrowser.class.getSimpleName() + ".refreshServerBrowser#";
        TS_ThreadRunUtils.once(() -> {
            TS_FileSoundUtils.beep();
            var locOpenChrome = new TGS_ShapeLocation(30, 143);
            var locChooseFirstTab = new TGS_ShapeLocation(115, 19);
            var locClearCache = new TGS_ShapeLocation(1162, 53);
            var locRefresh = new TGS_ShapeLocation(181, 64);
            var locs = new TGS_ShapeLocation[]{
                locOpenChrome,
                locChooseFirstTab,
                locClearCache,
                locRefresh
            };
            Arrays.stream(locs).forEachOrdered(l -> {
                TS_ThreadWaitUtils.seconds(killable, Objects.equals(l, locRefresh) ? 5 : 2, tag + 1);
                TS_InputMouseUtils.mouseClickLeft(l);
            });

            var locUsername = new TGS_ShapeLocation(245, 191);
            TS_ThreadWaitUtils.seconds(killable, 3, tag + 1);
            TS_InputMouseUtils.mouseClickLeft(locUsername);
            TS_ThreadWaitUtils.seconds(killable, 1, tag + 1);
            TS_InputKeyboardUtils.typeString(username);
            TS_ThreadWaitUtils.seconds(killable, 1, tag + 1);
            TS_InputKeyboardUtils.typeTab(2);
            TS_ThreadWaitUtils.seconds(killable, 1, tag + 1);
            TS_InputKeyboardUtils.typeString(password);
            TS_ThreadWaitUtils.seconds(killable, 1, tag + 1);
            TS_InputKeyboardUtils.typeEnter();

//                var locTables = new TGS_ShapeLocation(297, 122);
//                var locTableOpen = new TGS_ShapeLocation(303, 161);
//                var locListTable = new TGS_ShapeLocation(181, 429);
//                var locPressYes = new TGS_ShapeLocation(445, 121);
//                locs = new TGS_ShapeLocation[]{
//                    locTables,
//                    locTableOpen,
//                    locListTable,
//                    locPressYes
//                };
//                Arrays.stream(locs).forEachOrdered(l -> {
//                    TS_ThreadUtils.waitForSeconds(2);
//                    TS_MouseUtils.mouseClickLeft(l);
//                });
//                TS_ThreadUtils.waitForSeconds(2);
//                TS_KeyboardUtils.typeF(12);
        });
    }
}
