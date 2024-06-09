package com.mymusic.storage.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;

@Component
public class RangeValuesUtils {

    public long[] extractRangeValues(String rangeValues) throws NumberFormatException {
        if (rangeValues == null || !rangeValues.startsWith("bytes=")) {
            throw new IllegalArgumentException(
                    "Range values is incorrect or null"
            );
        }


        String[] rangesAndTotal = rangeValues.replace("bytes=", "").split("/");
        String[] startAndEnd = rangesAndTotal[0].split("-");

        long start = Long.parseLong(startAndEnd[0]);

        if (start < 0) {
            throw new IllegalArgumentException(
                    "Range values is incorrect or null"
            );
        }

        long end = (startAndEnd.length > 1 && !startAndEnd[1].isEmpty()) ? Long.parseLong(startAndEnd[1]) : -1;

        return new long[]{start, end};
    }

    public String numericStringValue(String origVal)
    {
        String retVal = "";
        if (StringUtils.hasText(origVal))
        {
            retVal = origVal.replaceAll("[^0-9]", "");
        }

        return retVal;
    }

    public long safeParseStringValueToLong(String valToParse, long defaultVal)
    {
        long retVal = defaultVal;
        if (StringUtils.hasText(valToParse))
        {
            try
            {
                retVal = Long.parseLong(valToParse);
            }
            catch (NumberFormatException ex)
            {
                retVal = defaultVal;
            }
        }

        return retVal;
    }
}
