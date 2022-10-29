package org.luggage_delivery.exceptions;
/*
  User: admin
  Cur_date: 23.10.2022
  Cur_time: 15:32
*/

public class DataBaseException extends Exception {

    public DataBaseException() {}

    public DataBaseException(String message) {
        super(message);
    }

    public DataBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataBaseException(Throwable cause) {
        super(cause);
    }

    public DataBaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
