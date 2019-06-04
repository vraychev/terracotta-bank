package com.joshcummings.codeplay.terracotta.defense.fs;

import java.io.IOException;
import java.io.InputStream;

import fi.solita.clamav.ClamAVClient;

public class VirusCheckingImageDetector implements ImageDetector {
    private final ImageDetector delegate;

    public VirusCheckingImageDetector(ImageDetector delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean isAnImage(String name, InputStream is) throws IOException {
        ClamAVClient cl = new ClamAVClient("localhost", 3310);
        byte[] reply = cl.scan(is);
        if (!ClamAVClient.isCleanReply(reply)) {
            throw new IOException("Virus detected!");
        }
        return delegate.isAnImage(name, is);
    }
}
