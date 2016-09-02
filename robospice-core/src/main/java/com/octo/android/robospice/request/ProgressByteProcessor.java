package com.octo.android.robospice.request;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Inspired from Guava com.google.common.io.ByteProcessor
 */
public class ProgressByteProcessor {

    final OutputStream bos;
    long progress;
    final long total;
    SpiceRequest<?> spiceRequest;

    public ProgressByteProcessor(SpiceRequest<?> spiceRequest, final OutputStream bos, final long total) {
        this.bos = bos;
        this.total = total;
        this.spiceRequest = spiceRequest;
    }

    public boolean processBytes(final byte[] buffer, final int offset, final int length) throws IOException {
        bos.write(buffer, offset, length);
        progress += length - offset;
        spiceRequest.publishProgress((float) progress / total);
        return !Thread.interrupted();
    }
}
