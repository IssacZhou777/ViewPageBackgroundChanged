package com.jellyjoe.viewpagerbgchange;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    private TextView mColor;
    private ViewPager mViewPage;
    private String[] mColors = {"#ff0000", "#00ff00", "#0000ff"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
    }

    private void initView() {
        mColor = (TextView)findViewById(R.id.color);
        mViewPage = (ViewPager)findViewById(R.id.viewpager);
        View[] views = {View.inflate(this,R.layout.view_item,null), View.inflate(this,R.layout.view_item,null),
                View.inflate(this,R.layout.view_item,null)};
        mViewPage.setAdapter(new MyPageAdapter(views));
        mViewPage.setBackgroundColor(Color.parseColor(mColors[0]));
        mViewPage.setCurrentItem(0);

        mViewPage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int fontPosition, float offset, int i) {
                if(fontPosition < 2 && fontPosition >= 0) {
                    int nowColor = calculateColar(mColors[fontPosition], mColors[fontPosition + 1], offset);
                    mViewPage.setBackgroundColor(nowColor);
                    mColor.setBackgroundColor(nowColor);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int position) {

            }
        });
    }

    public class MyPageAdapter extends PagerAdapter {
        private View[] mViews;
        MyPageAdapter(View[] views) {
            this.mViews = views;
        }
        @Override
        public int getCount() {
            return mViews.length;
        }

        public View[] getmViews() {
            return mViews;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViews[position]);
            return mViews[position];
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViews[position]);
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return false;
        }
    }

    private int calculateColar(String color1, String color2, float offset) {
        int fontColor = Color.parseColor(color1);
        int laterColor = Color.parseColor(color2);
        int ColorRed = (int)(Color.red(fontColor) * (1-offset) + Color.red(laterColor) * (offset));
        int ColorGreen = (int)(Color.green(fontColor) * (1-offset) + Color.green(laterColor) * (offset ));
        int ColorBlue = (int) (Color.blue(fontColor) * (1-offset) + Color.blue(laterColor) * (offset ));
        mColor.setText("R:" + ColorRed + "---G:" + ColorGreen + "---B:" + ColorBlue);
        return Color.rgb(ColorRed, ColorGreen, ColorBlue);
    }
}
