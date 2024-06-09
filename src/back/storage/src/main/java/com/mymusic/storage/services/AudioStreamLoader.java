package com.mymusic.storage.services;


import com.mymusic.storage.exceptions.ResourceNotFoundException;
import com.mymusic.storage.utils.RangeValuesUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


@Service
@RequiredArgsConstructor
public class AudioStreamLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(AudioStreamLoader.class);

    private final S3Service s3Service;
    private final RangeValuesUtils rangeValuesUtils;

    public record StreamingResponseResult(
            long start,
            long end,
            long filesize,
            StreamingResponseBody body
    ) {
        public long getContentLength() {
            return  (end - start) + 1;
        }
    }

    private StreamingResponseResult loadPartialMediaFromStorage(
            byte[] bytes,
            long startPos,
            long endPos
    ) throws ResourceNotFoundException, IOException {
        StreamingResponseBody responseStream;

        long filesize = bytes.length;

        if (startPos < 0L) {
            startPos = 0;
        }
        if (filesize > 0L)
        {
            if (startPos >= filesize)
            {
                startPos = filesize - 1L;
            }

            if (endPos >= filesize)
            {
                endPos = filesize - 1L;
            }
        }
        else
        {
            startPos = 0L;
            endPos = 0L;
        }
        byte[] buffer = new byte[1024];

        long finalEndPos = endPos;
        long finalStartPos = startPos;
        responseStream = outputStream -> {
            try (InputStream is = new ByteArrayInputStream(bytes)) {
                is.skip(finalStartPos);
                long pos = finalStartPos;
                int bytesRead;
                while (pos <= finalEndPos && (bytesRead = is.read(buffer, 0, (int) Math.min(buffer.length, finalEndPos - pos + 1))) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                    pos += bytesRead;
                }
                outputStream.flush();
            }
        };

        return new StreamingResponseResult(startPos, endPos, filesize, responseStream);
    }

    public StreamingResponseResult loadPartialMediaFromStorage(
            Long id,
            String rangeValues
    ) throws ResourceNotFoundException, IOException, IllegalArgumentException {

        var bytes = s3Service.getTrackObject(id);

        if (!StringUtils.hasText(rangeValues))
        {
            return loadEntireMediaFile(bytes);
        }

        long fileSize = bytes.length;
        long[] ranges = rangeValuesUtils.extractRangeValues(rangeValues);
        long rangeStart = ranges[0];
        long rangeEnd = ranges[1];


        if ((rangeEnd == 0L || rangeEnd == -1L) && fileSize > 0L)
        {
            rangeEnd = fileSize - 1;
        }
        if (fileSize < rangeEnd)
        {
            rangeEnd = fileSize - 1;
        }

        return loadPartialMediaFromStorage(bytes, rangeStart, rangeEnd);
    }

    public StreamingResponseResult loadEntireMediaFile(
            byte[] bytes
    ) throws IOException
    {
        long fileSize = bytes.length;
        long endPos;
        if (fileSize > 0L)
        {
            endPos = fileSize - 1;
        }
        else
        {
            endPos = 0L;
        }

        return loadPartialMediaFromStorage(bytes, 0, endPos);
    }
}
