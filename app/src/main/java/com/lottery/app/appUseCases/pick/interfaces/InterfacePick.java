package com.lottery.app.appUseCases.pick.interfaces;

import com.lottery.app.helperPackages.baseClasses.IBasePresenter;

import java.util.HashMap;
import java.util.List;

public interface InterfacePick {

    interface IFragment {
        void renderPath(List<HashMap<String, Object>> randomPath);
    }

    interface IPresenter extends IBasePresenter<InterfacePick.IFragment> {
        void generatePath(int entryAmount, int maxLength);
    }
}
