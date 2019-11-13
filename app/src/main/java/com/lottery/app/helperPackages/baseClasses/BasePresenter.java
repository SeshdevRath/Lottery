package com.lottery.app.helperPackages.baseClasses;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BasePresenter<T> implements IBasePresenter<T> {

    protected T view;

    @Override
    public void init() {

    }

    @Override
    public void onAttach(Context context) {

    }

    @Override
    public void onCreateView(Bundle bundle, T view, View fragmentContainer) {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public void unpackBundle(Bundle bundle) {

    }

    @Override
    public void registerView(T view) {
        this.view = view;
    }

    @Override
    public void initializeViews() {

    }

    @Override
    public void onCreate(Bundle bundle) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onDestroyView() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    protected T getView(){
        if (view == null)
            throw new NullPointerException("View should not be null");
        return view;

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onNewIntent(Bundle extras) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
