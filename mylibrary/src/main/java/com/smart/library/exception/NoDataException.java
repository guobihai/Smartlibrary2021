package com.smart.library.exception;

/**
 * @Author zrh
 * @Created 4/22/19
 * @Editor zrh
 * @Edited 4/22/19
 * @Type
 * @Layout
 * @Api
 */
public class NoDataException extends Exception {


    @Override
    public String getMessage() {
        return "no data";
    }
}
