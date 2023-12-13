package com.bsd.betatts;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileSaver {

    public static void MP3Saver(byte[] mp3Content, String filePath) throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            outputStream.write(mp3Content);
        }
    }
}