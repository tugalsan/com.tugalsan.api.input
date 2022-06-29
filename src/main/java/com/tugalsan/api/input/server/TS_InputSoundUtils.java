//https://github.com/eugenp/tutorials/blob/master/core-java-modules/core-java-os/src/main/java/com/baeldung/example/soundapi/WaveDataUtil.java
package com.tugalsan.api.input.server;

import java.io.*;
import javax.sound.sampled.*;

public class TS_InputSoundUtils implements Runnable {

    private AudioInputStream audioInputStream;
    private AudioFormat format;
    public Thread thread;
    private double duration;

    public static AudioFormat createDefaultAudioFormat() {
        var encoding = AudioFormat.Encoding.PCM_SIGNED;
        var rate = 44100.0f;
        int channels = 1;
        var sampleSize = 8;
        var bigEndian = true;
        return new AudioFormat(encoding, rate, sampleSize, channels, (sampleSize / 8) * channels, rate, bigEndian);
    }

    public TS_InputSoundUtils() {
        this(createDefaultAudioFormat());
    }

    public TS_InputSoundUtils(AudioFormat format) {
        super();
        this.format = format;
    }

    public void start() {
        thread = new Thread(this);
        thread.setName("Capture Microphone");
        thread.start();
    }

    public void stop() {
        thread = null;
    }

    @Override
    public void run() {
        duration = 0;

        try ( var out = new ByteArrayOutputStream();  var line = getTargetDataLineForRecord();) {

            var frameSizeInBytes = format.getFrameSize();
            var bufferLengthInFrames = line.getBufferSize() / 8;
            var bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
            buildByteOutputStream(out, line, frameSizeInBytes, bufferLengthInBytes);
            this.audioInputStream = new AudioInputStream(line);
            this.audioInputStream = convertToAudioIStream(out, frameSizeInBytes);
            audioInputStream.reset();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
    }

    public void buildByteOutputStream(final ByteArrayOutputStream out, final TargetDataLine line, int frameSizeInBytes, final int bufferLengthInBytes) throws IOException {
        var data = new byte[bufferLengthInBytes];
        var numBytesRead = 0;
        line.start();
        while (thread != null) {
            if ((numBytesRead = line.read(data, 0, bufferLengthInBytes)) == -1) {
                break;
            }
            out.write(data, 0, numBytesRead);
        }
    }

    public AudioInputStream convertToAudioIStream(final ByteArrayOutputStream out, int frameSizeInBytes) {
        byte audioBytes[] = out.toByteArray();
        ByteArrayInputStream bais = new ByteArrayInputStream(audioBytes);
        AudioInputStream audioStream = new AudioInputStream(bais, format, audioBytes.length / frameSizeInBytes);
        long milliseconds = (long) ((audioInputStream.getFrameLength() * 1000) / format.getFrameRate());
        duration = milliseconds / 1000.0;
        System.out.println("Recorded duration in seconds:" + duration);
        return audioStream;
    }

    public TargetDataLine getTargetDataLineForRecord() {
        TargetDataLine line;
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
        System.out.println("line.info: " + info.toString());
        if (!AudioSystem.isLineSupported(info)) {
            System.out.println("line not supported: " + info.toString());
            return null;
        }
        try {
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format, line.getBufferSize());
        } catch (final Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return line;
    }

    public boolean saveToFile(String fileLabel) {
        AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;

        System.out.println("Saving...");
        if (null == fileLabel || null == fileType || audioInputStream == null) {
            return false;
        }
        File myFile = new File(fileLabel + "." + fileType.getExtension());
        try {
            audioInputStream.reset();
        } catch (Exception e) {
            return false;
        }
        int i = 0;
        while (myFile.exists()) {
            String temp = "" + i + myFile.getName();
            myFile = new File(temp);
        }
        try {
            AudioSystem.write(audioInputStream, fileType, myFile);
        } catch (Exception ex) {
            return false;
        }
        System.out.println("Saved " + myFile.getAbsolutePath());
        return true;
    }
}
