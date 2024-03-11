package com.springboot.springbootref;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class LoggerUtil {
    private static final Logger logger = LoggerFactory.getLogger("com.springboot.springbootref");

    private LoggerUtil() {} // Private constructor to prevent direct instantiation

    public static Logger getLogger() {
        return logger;
    }
}
