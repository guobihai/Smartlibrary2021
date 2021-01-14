package com.smart.library.exception;

import java.io.IOException;

/**
 * @Author zrh
 * @Created 4/22/19
 * @Editor zrh
 * @Edited 4/22/19
 * @Type
 * @Layout
 * @Api
 */
public class NoNetworkException extends IOException {


    @Override
    public String getMessage() {
        return "no network";
    }
}
