package com.shanghai.logistics.ui.adapter.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;
import com.shanghai.logistics.ui.mian_fragment.AddressListFragment;
import com.shanghai.logistics.ui.mian_fragment.DynamicFragment;
import com.shanghai.logistics.ui.mian_fragment.MainMeFragment;
import com.shanghai.logistics.ui.mian_fragment.MessageFragment;

import java.util.ArrayList;

/**
 * @author
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private Fragment currentFragment;

    public MainViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        fragments.clear();
        fragments.add(new MessageFragment());
        fragments.add(new AddressListFragment());
        fragments.add(new DynamicFragment());
        fragments.add(new MainMeFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (getCurrentFragment() != object) {
            currentFragment = ((Fragment) object);
        }
        super.setPrimaryItem(container, position, object);
    }

    /**
     * Get the current fragment
     */
    public Fragment getCurrentFragment() {
        return currentFragment;
    }
}