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

    private EmptyUtil emptyUtil = new EmptyUtil();


    private ArrayList<Fragment> list = new ArrayList<>();

    public void setList(ArrayList<Fragment> list) {
        if (this.list != null && this.list.size() > 0) {
            this.list.clear();
        }
        this.list.addAll(list);
    }

    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        if (emptyUtil.isNull(list) == false) {
            return list.size();
        }
        return 0;
    }
}
