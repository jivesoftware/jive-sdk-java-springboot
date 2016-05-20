package com.jivesoftware.sdk.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JiveSDKUtils {
    private static final Logger log = LoggerFactory.getLogger(JiveSDKUtils.class);
    
    public static String encodeUrl(String url) {
        try {
            return URLEncoder.encode(url, "UTF-8");
        }
        catch (UnsupportedEncodingException uee) {
            //TODO: LOGGER
            System.err.println("Failed encoding URL using UTF-8 charset" + uee.getMessage());
            //noinspection deprecation
            return url;
        }
    }

    public static String decodeUrl(String url) {
        try {
            return URLDecoder.decode(url, "UTF-8");
        }
        catch (UnsupportedEncodingException uee) {
            //TODO: LOGGER
            System.err.println("Failed decoding URL using UTF-8 charset" + uee.getMessage());
            //noinspection deprecation
            return url;
        }
    }

    /**
     * Check if all the items are presented and not empty
     *
     * @param items : Checked items
     * @return :
     * true -> if all the items are presented.
     * false -> if at least one is absent or empty.
     */
    public static boolean isAllExist(Object... items) {
        if (items == null) {
            return false;
        }

        for (Object item : items) {
            if (item == null || item.toString().isEmpty()) {
                return false;
            }
        }

        return true;
    }

    private static final String ACTIVITIES = "/activities";

    /**
     * Workaround to external stream url - Trimming /activities suffix
     *
     * @param url The input URL
     * @return A valid push URL either for tiles / ext. streams
     */
    public static String normalizeItemUrl(String url) {
        return url != null && url.endsWith(ACTIVITIES) ?
                url.substring(0, url.lastIndexOf(ACTIVITIES)) :
                url;
    }

    public static String getJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (IOException ioe) {
            log.error("Unknown Error",ioe);
        } // end try/catch
        return null;
    } // end getJson

} // end class
