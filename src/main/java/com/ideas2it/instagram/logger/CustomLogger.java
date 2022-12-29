/**
 * copyrights ideas2it.
 */
package com.ideas2it.instagram.logger;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * use to log about the information, warning, error messages at required scenario.
 *
 * @version 1.0
 * @since 29 Dec 2022
 * @author Yogeshwar S
 */
public class CustomLogger {
    private final Logger logger;

    public CustomLogger(Class<?> className) {
        logger = LogManager.getLogger(className);
    }

    /**
     * logs the information message.
     *
     * @param  message of function
     */
    public void info(String message) {
        logger.info(message);
    }

    /**
     * logs the warning message.
     *
     * @param  message of function
     */
    public void warn(String message) {
        logger.warn(message);
    }

    /**
     * logs the error message.
     *
     * @param message of function
     */
    public void error(String message) {
        logger.error(message);
    }
}