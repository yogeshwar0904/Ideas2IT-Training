package com.ideas2it.logger;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * use to log the information.
 * 
 * @version 1.0 13 Oct 2022
 * @author Yogeshwar S
 */
public class CustomLogger {
    private static Logger logger = LogManager.getLogger(CustomLogger.class);

    /**
     * logs the information message.
     *
     * @param String message
     */
    public static void info(String message) {
        logger.info(message);
    }

    /**
     * logs the warning message.
     *
     * @param String message
     */
    public static void warn(String message) {
        logger.warn(message);
    }

    /**
     * logs the error message.
     * 
     * @param String message
     */
    public static void error(String message) {
        logger.error(message);
    }

    /**
     * logs the fatal message.
     * 
     * @param String message
     */
    public static void fatal(String message) {
        logger.fatal(message);
    }
}