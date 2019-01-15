package com.bw.movie.film.details;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.bw.movie.util.EmptyUtil;
import java.util.ArrayList;

/*-----------
 *🖐说明:
 *   viewpager 适配器
 */
public class MyAdapter extends FragmentPagerAdapter {



    private ArrayList<Fragment> mList = new ArrayList<>();

    public void setList(ArrayList<Fragment> list) {
        if (this.mList != null && this.mList.size() > 0) {
            this.mList.clear();
        }
        this.mList.addAll(list);
    }

    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return mList.get(i);
    }

    @Override
    public int getCount() {
        if (EmptyUtil.isNull(mList) == false) {
            return mList.size();
        }
        return 0;
    }
}
