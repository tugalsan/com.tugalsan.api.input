package com.tugalsan.api.input.server;

import com.tugalsan.api.union.client.TGS_UnionExcuseVoid;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Scanner;

public class TS_InputKeyboardUtils {

    public static String readLineFromConsole() {
        return new Scanner(System.in).nextLine();
    }

    public static TGS_UnionExcuseVoid typeF(int i) {
        return switch (i) {
            case 1 ->
                typeKeyEvent(KeyEvent.VK_F1);
            case 2 ->
                typeKeyEvent(KeyEvent.VK_F2);
            case 3 ->
                typeKeyEvent(KeyEvent.VK_F3);
            case 4 ->
                typeKeyEvent(KeyEvent.VK_F4);
            case 5 ->
                typeKeyEvent(KeyEvent.VK_F5);
            case 6 ->
                typeKeyEvent(KeyEvent.VK_F6);
            case 7 ->
                typeKeyEvent(KeyEvent.VK_F7);
            case 8 ->
                typeKeyEvent(KeyEvent.VK_F8);
            case 9 ->
                typeKeyEvent(KeyEvent.VK_F9);
            case 10 ->
                typeKeyEvent(KeyEvent.VK_F10);
            case 11 ->
                typeKeyEvent(KeyEvent.VK_F11);
            case 12 ->
                typeKeyEvent(KeyEvent.VK_F12);
            default ->
                TGS_UnionExcuseVoid.ofExcuse(TS_InputKeyboardUtils.class.getSimpleName(), "typeF", "un-expected input: " + i);
        };
    }

    public static TGS_UnionExcuseVoid typeUp() {
        return typeKeyEvent(KeyEvent.VK_UP);
    }

    public static TGS_UnionExcuseVoid typeDown() {
        return typeKeyEvent(KeyEvent.VK_DOWN);
    }

    public static TGS_UnionExcuseVoid typeLeft() {
        return typeKeyEvent(KeyEvent.VK_LEFT);
    }

    public static TGS_UnionExcuseVoid typeRight() {
        return typeKeyEvent(KeyEvent.VK_RIGHT);
    }

    public static TGS_UnionExcuseVoid typeTab() {
        return typeKeyEvent(KeyEvent.VK_TAB);
    }

    public static TGS_UnionExcuseVoid typeDelRight() {
        return typeKeyEvent(KeyEvent.VK_DELETE);
    }

    public static TGS_UnionExcuseVoid typeDelLeft() {
        return typeKeyEvent(KeyEvent.VK_BACK_SPACE);
    }

    public static TGS_UnionExcuseVoid typeSpace() {
        return typeKeyEvent(KeyEvent.VK_SPACE);
    }

    public static TGS_UnionExcuseVoid typeEsc() {
        return typeKeyEvent(KeyEvent.VK_ESCAPE);
    }

    public static TGS_UnionExcuseVoid typeEnter() {
        return typeKeyEvent(KeyEvent.VK_ENTER);
    }

    public static TGS_UnionExcuseVoid typeTab(int count) {
        for (var i = 0; i < count; i++) {
            var u = typeKeyEvent(KeyEvent.VK_TAB);
            if (u.isExcuse()) {
                return u;
            }
        }
        return TGS_UnionExcuseVoid.ofVoid();
    }

    public static TGS_UnionExcuseVoid typeKeyEvent(int keyEvent) {
        var u_robot = TS_InputCommonUtils.robot();
        if (u_robot.isExcuse()) {
            return u_robot.toExcuseVoid();
        }
        var robot = u_robot.value();
        robot.keyPress(keyEvent);
        robot.keyRelease(keyEvent);
        return TGS_UnionExcuseVoid.ofVoid();
    }

    public static TGS_UnionExcuseVoid pressKeyEvent(int keyEvent) {
        var u_robot = TS_InputCommonUtils.robot();
        if (u_robot.isExcuse()) {
            return u_robot.toExcuseVoid();
        }
        var robot = u_robot.value();
        robot.keyPress(keyEvent);
        return TGS_UnionExcuseVoid.ofVoid();
    }

    public static TGS_UnionExcuseVoid releaseKeyEvent(int keyEvent) {
        var u_robot = TS_InputCommonUtils.robot();
        if (u_robot.isExcuse()) {
            return u_robot.toExcuseVoid();
        }
        var robot = u_robot.value();
        robot.keyRelease(keyEvent);
        return TGS_UnionExcuseVoid.ofVoid();
    }

    public static TGS_UnionExcuseVoid typeString(String text) {
        toClipboard(text);
        return fromClipboard();
    }

    public static TGS_UnionExcuseVoid fromClipboard() {
        var u_robot = TS_InputCommonUtils.robot();
        if (u_robot.isExcuse()) {
            return u_robot.toExcuseVoid();
        }
        var robot = u_robot.value();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        return TGS_UnionExcuseVoid.ofVoid();
    }

    public static void toClipboard(CharSequence text) {
        var selection = new StringSelection(text.toString());
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
    }

}
