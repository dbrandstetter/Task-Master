package com.example.taskmaster;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomLogger {
    private static final Logger logger = LoggerFactory.getLogger(CustomLogger.class);

    public static void logCustomInfo(String message, Object... args) {
        logger.info(String.format(message, args));
    }
}
