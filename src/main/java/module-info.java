module com.tugalsan.api.input {
    requires java.desktop;
    requires com.tugalsan.api.executable;
    requires com.tugalsan.api.shape;
    requires com.tugalsan.api.thread;
    requires com.tugalsan.api.ide.netbeans;
    requires com.tugalsan.api.file.sound;
    exports com.tugalsan.api.input.server;
}
