package com.bittech.everthing.core.interceptor.impl;

import com.bittech.everthing.core.interceptor.Fileinterceptor;

import java.io.File;

public class FilePrintInterceptor implements Fileinterceptor {
    @Override
    public void apply(File file) {
        System.out.println(file.getAbsolutePath());
    }
}
