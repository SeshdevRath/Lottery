package com.lottery.app.helperPackages.baseClasses;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public interface IBasePresenter<T> {

    void init();

    void onAttach(Context context);

    void onCreateView(Bundle bundle, T view, View fragmentContainer);

    void onViewCreated(View view, Bundle savedInstanceState);

    void unpackBundle(Bundle bundle);

    void registerView(T view);

    void initializeViews();

    void onCreate(Bundle bundle);

    void onStart();

    void onRestart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void onDestroyView();

    void onSaveInstanceState(Bundle outState);

    void onPostCreate(Bundle savedInstanceState);

    void onNewIntent(Bundle extras);

    void onActivityResult(int requestCode, int resultCode, Intent data);
}
