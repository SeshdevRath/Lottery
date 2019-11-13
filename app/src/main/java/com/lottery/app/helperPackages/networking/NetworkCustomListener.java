package com.lottery.app.helperPackages.networking;

public interface NetworkCustomListener<T> {
    void onResponse(T response, String apiUrl);
}
