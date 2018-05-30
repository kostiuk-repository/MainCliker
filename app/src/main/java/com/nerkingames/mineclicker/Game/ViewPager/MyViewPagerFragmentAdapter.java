package com.nerkingames.mineclicker.Game.ViewPager;


import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;


import com.nerkingames.mineclicker.Game.GameFragments.AchievementsFragment.AchievementsFragment;
import com.nerkingames.mineclicker.Game.GameFragments.JobFragment.JobFragment;
import com.nerkingames.mineclicker.Game.GameFragments.UpworldFragment.UpWorldFragment;

/**
 * Created by vladyslav on 28.02.18.
 */

public class MyViewPagerFragmentAdapter extends FragmentPagerAdapter {

    public JobFragment getJobFragment() {
        return jobFragment;
    }

    JobFragment jobFragment;
    UpWorldFragment upWorldFragment;
    AchievementsFragment achievementsFragment;

    public MyViewPagerFragmentAdapter(FragmentManager fm, ViewPager viewPager) {
        super(fm);
        jobFragment = JobFragment.newInstance();
        upWorldFragment = UpWorldFragment.newInstance();
        upWorldFragment.setJobFragment(jobFragment);
        jobFragment.setViewPager(viewPager);
        achievementsFragment = AchievementsFragment.newInstance();
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0) {
            return jobFragment;
        } else if (position == 1) {
            return upWorldFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
