package com.lottery.app.appUseCases.pick.presenters;

import android.os.Bundle;
import android.view.View;

import com.lottery.app.appUseCases.pick.interfaces.InterfacePick;
import com.lottery.app.helperPackages.baseClasses.BasePresenter;
import com.lottery.app.helperPackages.utils.RandomUtils;

public class PresenterFragmentPick extends BasePresenter<InterfacePick.IFragment> implements InterfacePick.IPresenter  {

    private View rootView;

    @Override
    public void onCreateView(Bundle bundle, InterfacePick.IFragment view, View fragmentContainer) {
        super.onCreateView(bundle, view, fragmentContainer);
        registerView(view);
        rootView = fragmentContainer;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void generatePath(int entryAmount, int maxLength) {
        view.renderPath(RandomUtils.generateRandomPath(entryAmount, maxLength));
    }
}
