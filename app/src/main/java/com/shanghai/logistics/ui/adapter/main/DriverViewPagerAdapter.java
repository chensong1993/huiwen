package com.shanghai.logistics.ui.adapter.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.shanghai.logistics.ui.driver_fragment.DriverHomeFragment;
import com.shanghai.logistics.ui.driver_fragment.DriverMeFragment;
import com.shanghai.logistics.ui.driver_fragment.DriverOrderFragment;
import com.shanghai.logistics.ui.driver_fragment.DriverReleaseFragment;
import com.shanghai.logistics.ui.fragment.BlankFragment;
import com.shanghai.logistics.ui.user_fragment.UserLinkmanFragment;
import com.shanghai.logistics.ui.user_fragment.UserOrderFragment;

import java.util.ArrayList;

/**
 *
 */
public class DriverViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private Fragment currentFragment;

    public DriverViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        fragments.clear();
        fragments.add(new DriverHomeFragment());
        fragments.add(new DriverOrderFragment());
        fragments.add(new DriverReleaseFragment());
        fragments.add(new UserLinkmanFragment());
        fragments.add(new DriverMeFragment());
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