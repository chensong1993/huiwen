package com.shanghai.logistics.base;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.gyf.immersionbar.components.ImmersionOwner;
import com.gyf.immersionbar.components.ImmersionProxy;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

public abstract class SimpleFragment extends SupportFragment  implements ImmersionOwner{

    protected View mView;
    protected Activity mActivity;
    protected Context mContext;
    private Unbinder mUnBinder;
    protected boolean isInited = false;
    private static final String TAG="SimpleFragment";
    /**
     * ImmersionBar代理类
     */
    private ImmersionProxy mImmersionProxy = new ImmersionProxy(this);
    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), null);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnBinder = ButterKnife.bind(this, view);
        isInited = true;

    }

    protected void onPostResume() {
        onPostResume();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initEventAndData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnBinder.unbind();
      // App.refWatcher.watch(this);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mImmersionProxy.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImmersionProxy.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mImmersionProxy.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mImmersionProxy.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mImmersionProxy.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mImmersionProxy.onDestroy();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        mImmersionProxy.onHiddenChanged(hidden);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mImmersionProxy.onConfigurationChanged(newConfig);
    }

    /**
     * 懒加载，在view初始化完成之前执行
     * On lazy after view.
     */
    @Override
    public void onLazyBeforeView() {
    }

    /**
     * 懒加载，在view初始化完成之后执行
     * On lazy before view.
     */
    @Override
    public void onLazyAfterView() {
    }

    /**
     * Fragment用户可见时候调用
     * On visible.
     */
    @Override
    public void onVisible() {
    }

    /**
     * Fragment用户不可见时候调用
     * On invisible.
     */
    @Override
    public void onInvisible() {
    }

    /**
     * @param resId           FramLayout的ID
     * @param clss            要显示类
     * @param bundle          传递的参数
     * @param fragmentManager
     */
    public static void startFragment(int resId, Class<? extends SimpleFragment> clss, Bundle bundle, FragmentManager fragmentManager) {


        startFragment(resId, clss, bundle, fragmentManager, 0);
    }


    /**
     * @param resId           FramLayout的ID
     * @param clss            要显示类
     * @param bundle          传递的参数
     * @param saveType        保存类型  是否保存 instance   默认0不保存 | 1保存 |其他待定
     * @param fragmentManager
     */
    public static void startFragment(int resId, Class<? extends SimpleFragment> clss, Bundle bundle, FragmentManager fragmentManager, int saveType) {
        String Tag = clss.getName();
        if (fragmentManager == null) {
            Log.e(TAG, "startFragment: fragmentManager =" + fragmentManager);
            return;
        }
        Fragment fragmentByTag = fragmentManager.findFragmentByTag(Tag);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (fragmentByTag == null) {
            try {
                fragmentByTag = clss.newInstance();
                fragmentByTag.setArguments(bundle);
                if (saveType == 0)
                    fragmentTransaction.add(resId, fragmentByTag).show(fragmentByTag);
                else if (saveType == 1) {
                    fragmentTransaction.add(resId, fragmentByTag, Tag).show(fragmentByTag);
//                    fragmentTransaction.setPrimaryNavigationFragment(fragmentByTag);
                }
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            try {
                fragmentByTag.setArguments(bundle);
                Log.i(TAG, "startFragment:已经有了 ");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, "startFragment: IllegalStateException");
            }
            //移除已经常创建的 重新生成
            if(saveType==0){
                fragmentTransaction.detach(fragmentByTag).attach(fragmentByTag).show(fragmentByTag);
            }
           //
            //直接显示已经存在的
            fragmentTransaction.show(fragmentByTag);
        }

        fragmentTransaction.addToBackStack(null);//默认返回界面为空
        if (saveType == 1) fragmentTransaction.addToBackStack(Tag);//默认返回界面为空
        List<Fragment> fragments = fragmentManager.getFragments();
        int size = fragments.size();
        Log.e(TAG, "startFragment: size" + size);
        if (fragments != null && fragments.size() > 0) {
            for (Fragment fragment : fragments) {
                String name = fragment.getClass().getName();
                boolean hidden = fragment.isHidden();
                Log.e(TAG, "startFragment: name:" + name);
                Log.e(TAG, "startFragment: hidden:" + hidden);
                //此出必须判断fragment是否为null！
                if (fragment != null && !clss.isInstance(fragment)) {

                    fragmentTransaction.hide(fragment);
                    boolean hidden1 = fragment.isHidden();
                    Log.e(TAG, "startFragment: hidden1:" + hidden1);
                }
            }
        }
        //为了避免报这样的异常  需要把fragmentTransaction.commit();改成fragmentTransaction.commitAllowingStateLoss();
//        java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
//        at android.support.v4.app.FragmentManagerImpl.checkStateLoss(FragmentManager.java:2055)
//        at android.support.v4.app.FragmentManagerImpl.enqueueAction(FragmentManager.java:2078)
//        at android.support.v4.app.FragmentManagerImpl.popBackStack(FragmentManager.java:790)
//        fragmentTransaction.commit();
        fragmentTransaction.commit();

        return;
    }
    /**
     * 是否可以实现沉浸式，当为true的时候才可以执行initImmersionBar方法
     * Immersion bar enabled boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean immersionBarEnabled() {
        return true;
    }
    protected abstract int getLayoutId();
    protected abstract void initEventAndData();
}
