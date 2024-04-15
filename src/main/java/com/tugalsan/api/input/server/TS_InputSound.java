//https://github.com/eugenp/tutorials/blob/master/core-java-modules/core-java-os/src/main/java/com/baeldung/example/soundapi/WaveDataUtil.java
package com.tugalsan.api.input.server;

import com.tugalsan.api.coronator.client.*;
import com.tugalsan.api.runnable.client.TGS_RunnableType1;
import com.tugalsan.api.thread.server.sync.TS_ThreadSyncTrigger;
import com.tugalsan.api.thread.server.async.TS_ThreadAsync;
import com.tugalsan.api.union.client.TGS_UnionExcuse;
import com.tugalsan.api.union.client.TGS_UnionExcuseVoid;
import java.io.*;
import java.nio.file.*;
import javax.sound.sampled.*;

public class TS_InputSound {
    
    public static TS_InputSound of(TS_ThreadSyncTrigger killTrigger, Path file, TGS_RunnableType1<Throwable> onError) {
        var _this = new TS_InputSound();
        _this.file = file;
        _this.format = TGS_Coronator.of(AudioFormat.class).coronateAs(val -> {
            var encoding = AudioFormat.Encoding.PCM_SIGNED;
            var rate = 44100.0f;
            var channels = 2;
            var sampleSize = 16;
            var bigEndian = true;
            return new AudioFormat(encoding, rate, sampleSize, channels, (sampleSize / 8) * channels, rate, bigEndian);
        });
        TS_ThreadAsync.now(killTrigger, kt -> {
            var u_line = _this.getTargetDataLineForRecord();
            if (u_line.isExcuse()) {
                return;
            }
            try (var out = new ByteArrayOutputStream(); var line = u_line.value()) {
                var frameSizeInBytes = _this.format.getFrameSize();
                var bufferLengthInFrames = line.getBufferSize() / 8;
                var bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
                _this.pumpByteOutputStream(killTrigger, out, line, bufferLengthInBytes);
                _this.audioInputStream = new AudioInputStream(line);
                _this.audioInputStream = _this.convertToAudioIStream(out, frameSizeInBytes);
                _this.audioInputStream.reset();
            } catch (IOException ex) {
                onError.run(ex);
            }
        });
        
        return _this;
    }
    
    private TS_InputSound() {
        
    }
    private Path file;
    private AudioFormat format;
    private AudioInputStream audioInputStream;
    private boolean kill = false;
    
    public TS_InputSound kill() {
        kill = true;
        return this;
    }
    
    private void pumpByteOutputStream(TS_ThreadSyncTrigger killTrigger, ByteArrayOutputStream out, TargetDataLine line, int bufferLengthInBytes) {
        var data = new byte[bufferLengthInBytes];
        int numBytesRead;
        line.start();
        while (!kill) {
            if ((numBytesRead = line.read(data, 0, bufferLengthInBytes)) == -1) {
                break;
            }
            out.write(data, 0, numBytesRead);
            if (killTrigger.hasTriggered()) {
                kill = true;
            }
        }
    }
    
    private AudioInputStream convertToAudioIStream(ByteArrayOutputStream out, int frameSizeInBytes) {
        var audioBytes = out.toByteArray();
        var bais = new ByteArrayInputStream(audioBytes);
        var audioStream = new AudioInputStream(bais, format, audioBytes.length / frameSizeInBytes);
        var milliseconds = (long) ((audioInputStream.getFrameLength() * 1000) / format.getFrameRate());
        var duration = milliseconds / 1000.0;
        System.out.println("Recorded duration in seconds:" + duration);
        return audioStream;
    }
    
    private TGS_UnionExcuse<TargetDataLine> getTargetDataLineForRecord() {
        try {
            var info = new DataLine.Info(TargetDataLine.class, format);
            System.out.println("line.info: " + info.toString());
            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("line not supported: " + info.toString());
                return null;
            }
            var line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format, line.getBufferSize());
            return TGS_UnionExcuse.of(line);
        } catch (LineUnavailableException ex) {
            return TGS_UnionExcuse.ofExcuse(ex);
        }
    }
    
    public TGS_UnionExcuseVoid saveToFile() {
        try {
            var fileType = AudioFileFormat.Type.WAVE;
            System.out.println("Saving...");
            if (null == fileType || audioInputStream == null) {
                return TGS_UnionExcuseVoid.ofExcuse(TS_InputSound.class.getSimpleName(), "saveToFile", "null == fileType || audioInputStream == null");
            }
            var myFile = file.toFile();
            audioInputStream.reset();
            var i = 0;
            while (myFile.exists()) {
                var temp = "" + i + myFile.getName();
                myFile = new File(temp);
            }
            AudioSystem.write(audioInputStream, fileType, myFile);
            System.out.println("Saved " + myFile.getAbsolutePath());
            return TGS_UnionExcuseVoid.ofVoid();
        } catch (IOException ex) {
            return TGS_UnionExcuseVoid.ofExcuse(ex);
        }
    }
}
