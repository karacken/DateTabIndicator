package com.karacken.datetabindicator;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DateTabIndicator extends FrameLayout implements View.OnClickListener {

    private List<Integer> days = new ArrayList<>();
    private int selectedPageIndex = 0;
    private int selectedDayIndex = 0;
    private static final int[] DAY_TV_RES_ID = {R.id.day_mon, R.id.day_tue, R.id.day_wed, R.id.day_thu, R.id.day_fri, R.id.day_sat,
            R.id.day_sun};

    public DateTabIndicator(@NonNull Context context) {
        super(context);
        init();
    }

    public DateTabIndicator(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DateTabIndicator(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private TextView monFixedIndicator;
    private TextView tueFixedIndicator;
    private TextView wedFixedIndicator;
    private TextView thuFixedIndicator;
    private TextView friFixedIndicator;
    private TextView satFixedIndicator;
    private TextView sunFixedIndicator;
    private View lineIndicator;
    private ViewPager daysPager;
    private DaysPagerAdapter daysPagerAdapter;
    private Handler mHandler = new Handler();
    private void init() {
        addView(LayoutInflater.from(getContext()).inflate(R.layout.date_tab_indicator, this, false));
        monFixedIndicator = findViewById(R.id.mon_fixed_indicator);
        tueFixedIndicator = findViewById(R.id.tue_fixed_indicator);
        wedFixedIndicator = findViewById(R.id.wed_fixed_indicator);
        thuFixedIndicator = findViewById(R.id.thu_fixed_indicator);
        friFixedIndicator = findViewById(R.id.fri_fixed_indicator);
        satFixedIndicator = findViewById(R.id.sat_fixed_indicator);
        sunFixedIndicator = findViewById(R.id.sun_fixed_indicator);
        lineIndicator = findViewById(R.id.line_indicator);
        daysPager = findViewById(R.id.days_pager);
        daysPagerAdapter = new DaysPagerAdapter();
        daysPager.setAdapter(daysPagerAdapter);
        daysPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectedPageIndex = position;
                mHandler.removeCallbacks(selectDayRunnable);
                mHandler.postDelayed(selectDayRunnable,500);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mHandler.postDelayed(selectDayRunnable,500);
    }

    class DaysPagerAdapter extends PagerAdapter {


        @Override
        public int getCount() {
            return (int) Math.ceil(days.size() / 7f);
        }


        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, final int position) {
            final View view = LayoutInflater.from(getContext()).inflate(R.layout.days_pager_item, container, false);
            TextView dayMonTv = view.findViewById(R.id.day_mon);
            TextView dayTueTv = view.findViewById(R.id.day_tue);
            TextView dayWedTv = view.findViewById(R.id.day_wed);
            TextView dayThuTv = view.findViewById(R.id.day_thu);
            TextView dayFriTv = view.findViewById(R.id.day_fri);
            TextView daySatTv = view.findViewById(R.id.day_sat);
            TextView daySunTv = view.findViewById(R.id.day_sun);

            //Setting Day Text
            int index = position * 7;
            dayMonTv.setText(days.size() > index ? String.valueOf(days.get(index)) : "");
            dayTueTv.setText(days.size() > (++index) ? String.valueOf(days.get(index)) : "");
            dayWedTv.setText(days.size() > (++index) ? String.valueOf(days.get(index)) : "");
            dayThuTv.setText(days.size() > (++index) ? String.valueOf(days.get(index)) : "");
            dayFriTv.setText(days.size() > (++index) ? String.valueOf(days.get(index)) : "");
            daySatTv.setText(days.size() > (++index) ? String.valueOf(days.get(index)) : "");
            daySunTv.setText(days.size() > (++index) ? String.valueOf(days.get(index)) : "");

            //Setting Day VISIBILITY
            index = position * 7;
            dayMonTv.setVisibility(days.size() > index && days.get(index) != 0 ? VISIBLE : INVISIBLE);
            dayTueTv.setVisibility(days.size() > ++index && days.get(index) != 0 ? VISIBLE : INVISIBLE);
            dayWedTv.setVisibility(days.size() > ++index && days.get(index) != 0 ? VISIBLE : INVISIBLE);
            dayThuTv.setVisibility(days.size() > ++index && days.get(index) != 0 ? VISIBLE : INVISIBLE);
            dayFriTv.setVisibility(days.size() > ++index && days.get(index) != 0 ? VISIBLE : INVISIBLE);
            daySatTv.setVisibility(days.size() > ++index && days.get(index) != 0 ? VISIBLE : INVISIBLE);
            daySunTv.setVisibility(days.size() > ++index && days.get(index) != 0 ? VISIBLE : INVISIBLE);

            //Setting Selection
            dayMonTv.setSelected(false);
            dayTueTv.setSelected(false);
            dayWedTv.setSelected(false);
            dayThuTv.setSelected(false);
            dayFriTv.setSelected(false);
            daySatTv.setSelected(false);
            daySunTv.setSelected(false);

            //Setting Click Listener
            dayMonTv.setOnClickListener(DateTabIndicator.this);
            dayTueTv.setOnClickListener(DateTabIndicator.this);
            dayWedTv.setOnClickListener(DateTabIndicator.this);
            dayThuTv.setOnClickListener(DateTabIndicator.this);
            dayFriTv.setOnClickListener(DateTabIndicator.this);
            daySatTv.setOnClickListener(DateTabIndicator.this);
            daySunTv.setOnClickListener(DateTabIndicator.this);


            view.setTag(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

            return view == object;
        }

    }

    public List<Integer> getDays() {
        return days;
    }

    public void setDays(List<Integer> days) {
        this.days = days;
        daysPagerAdapter.notifyDataSetChanged();
    }

    private View prevDayTv = null;

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.day_mon:
                selectDay(v, 0);
                break;
            case R.id.day_tue:
                selectDay(v, 1);
                break;
            case R.id.day_wed:
                selectDay(v, 2);
                break;
            case R.id.day_thu:
                selectDay(v, 3);
                break;
            case R.id.day_fri:
                selectDay(v, 4);
                break;
            case R.id.day_sat:
                selectDay(v, 5);
                break;
            case R.id.day_sun:
                selectDay(v, 6);
                break;
        }

    }

    /**
     * @param dayIndex 0-6
     */
    private void selectDay(View v, int dayIndex) {
        selectedDayIndex = dayIndex;
        int dayListIndex = selectedPageIndex * 7 + dayIndex;
        if (prevDayTv != null) prevDayTv.setSelected(false);
        v.setSelected(true);
        prevDayTv = v;
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        int center_point = location[0]%getWidth() + v.getWidth() / 2;
        lineIndicator.animate().x(center_point - lineIndicator.getWidth() / 2);

        if (days.size() > dayListIndex && days.get(dayListIndex)!=0) {
            //listener
        } else {
            for (int i = 0; i < 7; i++) {
                dayListIndex = selectedPageIndex * 7 + i;
                if (days.get(dayListIndex)==1 || days.size()-1 == dayListIndex) {
                    setSelectedDay(i);
                    break;
                }

            }
        }

    }

    /**
     * @param dayIndex 0-6
     */
    public void setSelectedDay(int dayIndex) {
        if (dayIndex >= 0 && dayIndex < 7) {
            selectedDayIndex = dayIndex;
            View view = daysPager.findViewWithTag(selectedPageIndex);
            selectDay(view.findViewById(DAY_TV_RES_ID[dayIndex]),dayIndex);
        }

    }
    private Runnable selectDayRunnable = new Runnable() {
        @Override
        public void run() {
            setSelectedDay(selectedDayIndex);
        }
    };

}
