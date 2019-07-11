package com.shanghai.logistics.base;

import android.os.Bundle;
import android.view.View;

import com.shanghai.logistics.app.App;
import com.shanghai.logistics.injections.components.DaggerFragmentComponent;
import com.shanghai.logistics.injections.components.FragmentComponent;
import com.shanghai.logistics.injections.modules.FragmentModule;


import javax.inject.Inject;

public abstract class BaseFragment<T extends BasePresenter> extends SimpleFragment implements BaseView {

    @Inject
    protected T mPresenter;

    private boolean isVisible;
    private boolean isInitialized;

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }


    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        initInject();
        mPresenter.attachView(this);
        super.onViewCreated(view, savedInstanceState);

    }


    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroyView();
    }

    @Override
    public void showErrorMsg(String msg) {
        //  SnackbarUtil.show(((ViewGroup) getActivity().findViewById(android.R.id.content)).getChildAt(0), msg);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            isVisible = true;
            if (!isInitialized) {
                isInitialized = true;
                initEventAndData();
            }
        } else {
            isVisible = false;
        }
        // initEventAndData();
    }


    @Override
    public void stateError() {

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateMain() {

    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    protected abstract void initInject();
}