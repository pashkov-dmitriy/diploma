package com.mymusic.storage.lib;

import org.springframework.core.io.InputStreamResource;

import java.io.IOException;
import java.io.InputStream;

public class LimitedInputStream extends InputStream {
    private InputStream in;
    private long remaining;
    private long mark = -1;

    public LimitedInputStream(InputStream in, long offset, long limit) throws IOException {
        this.in = in;
        this.remaining = limit - offset + 1;

        // Skip to the start offset
        while (offset > 0) {
            long skipped = in.skip(offset);
            if (skipped == 0) {
                throw new IOException("Skipping to offset failed");
            }
            offset -= skipped;
        }
    }

    @Override
    public int read() throws IOException {
        if (remaining == 0) {
            return -1; // End of the limited stream
        }

        int data = in.read();
        if (data != -1) {
            --remaining;
        }
        return data;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        if (remaining == 0) {
            return -1; // End of the limited stream
        }

        len = (int) Math.min(len, remaining);
        int numRead = in.read(b, off, len);
        if (numRead != -1) {
            remaining -= numRead;
        }
        return numRead;
    }

    @Override
    public void close() throws IOException {
        in.close();
    }

    @Override
    public synchronized void mark(int readlimit) {
        in.mark(readlimit);
        mark = remaining;
    }

    @Override
    public synchronized void reset() throws IOException {
        if (!in.markSupported()) {
            throw new IOException("Mark not supported");
        }
        if (mark == -1) {
            throw new IOException("Mark not set");
        }

        in.reset();
        remaining = mark;
    }

    @Override
    public boolean markSupported() {
        return in.markSupported();
    }
}

