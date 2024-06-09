package com.mymusic.storage;

import com.mymusic.storage.services.AudioStreamLoader;
import com.mymusic.storage.services.S3Service;
import com.mymusic.storage.utils.RangeValuesUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AudioStreamLoaderTest {

    @Mock
    private S3Service s3Service;

    @Mock
    private RangeValuesUtils rangeValuesUtils;

    @InjectMocks
    private AudioStreamLoader audioStreamLoader;

    private byte[] sampleBytes;

    @BeforeEach
    public void setUp() {
        sampleBytes = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    }

    @Test
    public void testLoadPartialMediaFromStorage_NoRangeValues_LoadsEntireFile() throws Exception {
        Long id = 1L;
        when(s3Service.getTrackObject(id)).thenReturn(sampleBytes);

        AudioStreamLoader.StreamingResponseResult result = audioStreamLoader.loadPartialMediaFromStorage(id, null);

        assertNotNull(result);
        assertEquals(0, result.start());
        assertEquals(sampleBytes.length - 1, result.end());
        assertEquals(sampleBytes.length, result.filesize());
    }

    @Test
    public void testLoadPartialMediaFromStorage_WithValidRangeValues_LoadsPartialFile() throws Exception {
        Long id = 1L;
        when(s3Service.getTrackObject(id)).thenReturn(sampleBytes);
        when(rangeValuesUtils.extractRangeValues("bytes=2-5")).thenReturn(new long[]{2, 5});

        AudioStreamLoader.StreamingResponseResult result = audioStreamLoader.loadPartialMediaFromStorage(id, "bytes=2-5");

        assertNotNull(result);
        assertEquals(2, result.start());
        assertEquals(5, result.end());
        assertEquals(sampleBytes.length, result.filesize());
    }

    @Test
    public void testLoadPartialMediaFromStorage_RangeEndBeyondFileSize_AdjustsToFileEnd() throws Exception {
        Long id = 1L;
        when(s3Service.getTrackObject(id)).thenReturn(sampleBytes);
        when(rangeValuesUtils.extractRangeValues("bytes=2-50")).thenReturn(new long[]{2, 50});

        AudioStreamLoader.StreamingResponseResult result = audioStreamLoader.loadPartialMediaFromStorage(id, "bytes=2-50");

        assertNotNull(result);
        assertEquals(2, result.start());
        assertEquals(sampleBytes.length - 1, result.end());
        assertEquals(sampleBytes.length, result.filesize());
    }

    @Test
    public void testLoadEntireMediaFile() throws Exception {
        byte[] bytes = {1, 2, 3, 4, 5};
        AudioStreamLoader.StreamingResponseResult result = audioStreamLoader.loadEntireMediaFile(bytes);

        assertNotNull(result);
        assertEquals(0, result.start());
        assertEquals(bytes.length - 1, result.end());
        assertEquals(bytes.length, result.filesize());
    }
}
