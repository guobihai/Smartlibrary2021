package com.smart.textrunapp.camara2.util;

public interface OnDecoderPlayer {

    void offer(byte[] data);

    byte[] pool();

    void setVideoParamerter(int width, int height, int fps);

    void onFinish();
}
