package com.lottery.app.helperPackages.networking;

public interface NetworkErrorListener<T> {

    void onErrorResponse(Throwable t);

    void onErrorResponse(T response, int statusCode, String apiUrl);
}
