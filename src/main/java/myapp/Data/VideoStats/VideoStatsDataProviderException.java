
package myapp;

import java.lang.Exception;

public class VideoStatsDataProviderException extends Exception { 
    public VideoStatsDataProviderException(String errorMessage) {
        super(errorMessage);
    }
}