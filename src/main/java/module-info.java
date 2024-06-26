module com.tugalsan.api.input {
    requires java.desktop;
    requires com.tugalsan.api.unsafe;
    requires com.tugalsan.api.log;
    requires com.tugalsan.api.union;
    
    requires com.tugalsan.api.callable;
    requires com.tugalsan.api.shape;
    requires com.tugalsan.api.tuple;
    
    requires com.tugalsan.api.thread;
    requires com.tugalsan.api.file.sound;
    exports com.tugalsan.api.input.server;
}
