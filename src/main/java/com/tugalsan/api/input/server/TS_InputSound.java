//https://github.com/eugenp/tutorials/blob/master/core-java-modules/core-java-os/src/main/java/com/baeldung/example/soundapi/WaveDataUtil.java
package com.tugalsan.api.input.server;

import com.tugalsan.api.function.client.maythrow.uncheckedexceptions.TGS_FuncMTUCEEffectivelyFinal;
import com.tugalsan.api.function.client.maythrow.checkedexceptions.TGS_FuncMTCEUtils;
import com.tugalsan.api.log.server.TS_Log;
import com.tugalsan.api.thread.server.sync.TS_ThreadSyncTrigger;
import com.tugalsan.api.thread.server.async.run.TS_ThreadAsyncRun;
import com.tugalsan.api.union.client.TGS_UnionExcuse;
import com.tugalsan.api.union.client.TGS_UnionExcuseVoid;

import java.io.*;
import java.nio.file.*;
import javax.sound.sampled.*;

public class TS_InputSound {

    private static final TS_Log d = TS_Log.of(TS_InputSound.class);

    public static TS_InputSound of(TS_ThreadSyncTrigger killTrigger, Path file) {
        return new TS_InputSound(killTrigger, file);
    }

    public TS_InputSound(TS_ThreadSyncTrigger killTrigger, Path file) {
        this.file = file;
        format = TGS_FuncMTUCEEffectivelyFinal.of(AudioFormat.class).coronateAs(val -> {
            var encoding = AudioFormat.Encoding.PCM_SIGNED;
            var rate = 44100.0f;
            var channels = 2;
            var sampleSize = 16;
            var bigEndian = true;
            return new AudioFormat(encoding, rate, sampleSize, channels, (sampleSize / 8) * channels, rate, bigEndian);
        });
        TS_ThreadAsyncRun.now(killTrigger, kt -> {
            TGS_FuncMTCEUtils.run(() -> {
                var u_line = getTargetDataLineForRecord();
                try (var out = new ByteArrayOutputStream(); var line = u_line.value()) {
                    var frameSizeInBytes = format.getFrameSize();
                    var bufferLengthInFrames = line.getBufferSize() / 8;
                    var bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
                    pumpByteOutputStream(killTrigger, out, line, bufferLengthInBytes);
                    audioInputStream = new AudioInputStream(line);
                    audioInputStream = convertToAudioIStream(out, frameSizeInBytes);
                    audioInputStream.reset();
                }
            });
        });
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
        return TGS_FuncMTCEUtils.call(() -> {
            var info = new DataLine.Info(TargetDataLine.class, format);
            if (!AudioSystem.isLineSupported(info)) {
                return TGS_UnionExcuse.ofExcuse(d.className, "getTargetDataLineForRecord", "line not supported: " + info.toString());
            }
            var line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format, line.getBufferSize());
            return TGS_UnionExcuse.of(line);
        }, e -> {
            return TGS_UnionExcuse.ofExcuse(e);
        });
    }

    public TGS_UnionExcuseVoid saveToFile() {
        return TGS_FuncMTCEUtils.call(() -> {
            var fileType = AudioFileFormat.Type.WAVE;
            if (null == fileType || audioInputStream == null) {
                return TGS_UnionExcuseVoid.ofExcuse(d.className, "saveToFile", "null == fileType || audioInputStream == null");
            }
            var myFile = file.toFile();
            audioInputStream.reset();
            var i = 0;
            while (myFile.exists()) {
                var temp = "" + i + myFile.getName();
                myFile = new File(temp);
            }
            AudioSystem.write(audioInputStream, fileType, myFile);
            return TGS_UnionExcuseVoid.ofVoid();
        }, e -> {
            return TGS_UnionExcuseVoid.ofExcuse(e);
        });
    }
}
