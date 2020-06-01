package com.kelelas.restaurant.exception;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBException extends RuntimeException{
        private static final Logger logger = LogManager.getLogger(DBException.class);

        public DBException() {
            logger.error("DB Exception");
        }

        public DBException(String message) {
            super(message);
            logger.error(message);
        }

        public DBException(Throwable cause) {
            super(cause);
            logger.error(cause.getStackTrace());
        }
}
