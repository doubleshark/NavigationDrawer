package com.example.srk.navigationdrawer;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.hadiidbouk.charts.ChartProgressBar;
import com.hadiidbouk.charts.OnBarClickedListener;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class DashboardFragment extends Fragment {

    View v;
    ViewPager viewPager;
    TabLayout tabLayout;
    PieChart pieChart;
    ChartProgressBar mChart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_dashboard,container,false);

        viewPager=(ViewPager)v.findViewById(R.id.viewPa);
        viewPager.setPadding(50,0,50,0);

        ownPagerAdapter own = new ownPagerAdapter(getActivity());
        viewPager.setAdapter(own);
        tabLayout=(TabLayout)v.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager,true);

        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new MyTimer(),5000,6000);

        piechart();
        chartprogressbar();


        return v;
    }

    class MyTimer extends TimerTask {

        @Override
        public void run() {
            DashboardFragment.this.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                        if(viewPager.getCurrentItem()==0){
                            viewPager.setCurrentItem(1);
                        }else if(viewPager.getCurrentItem()==1){
                            viewPager.setCurrentItem(2);
                        }else if(viewPager.getCurrentItem()==2){
                            viewPager.setCurrentItem(0);
                        }
                }
            });
        }
    }

    private void runOnUiThread(Runnable runnable) {

    }

    private void piechart() {


        pieChart = v.findViewById(R.id.piechart);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(30f);

        ArrayList<PieEntry> yValues = new ArrayList<>();

        yValues.add(new PieEntry(34f,"Party A"));
        yValues.add(new PieEntry(23f,"Party B"));

        pieChart.animateXY(1000,1000, Easing.EaseInCubic);

        PieDataSet dataSet = new PieDataSet(yValues, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data = new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);

        pieChart.setData(data);

    }


    private void chartprogressbar() {

        ArrayList<com.hadiidbouk.charts.BarData> dataList = new ArrayList<>();

        com.hadiidbouk.charts.BarData data;

        data = new com.hadiidbouk.charts.BarData("Jan", 8.0f, "8.0₹");
        dataList.add(data);

        data = new com.hadiidbouk.charts.BarData("Feb", 1.8f, "1.8₹");
        dataList.add(data);

        data = new com.hadiidbouk.charts.BarData("Mar", 7.3f, "7.3₹");
        dataList.add(data);

        data = new com.hadiidbouk.charts.BarData("Apr", 6.2f, "6.2₹");
        dataList.add(data);

        data = new com.hadiidbouk.charts.BarData("May", 6.2f, "6.2₹");
        dataList.add(data);

        data = new com.hadiidbouk.charts.BarData("Jun", 6.2f, "6.2₹");
        dataList.add(data);

        data = new com.hadiidbouk.charts.BarData("Jul", 6.2f, "6.2₹");
        dataList.add(data);

        data = new com.hadiidbouk.charts.BarData("Aug", 6.2f, "6.2₹");
        dataList.add(data);

        data = new com.hadiidbouk.charts.BarData("Sep", 3.3f, "3.3₹");
        dataList.add(data);

        mChart = (ChartProgressBar) v.findViewById(R.id.ChartProgressBar);

        mChart.setDataList(dataList);
        mChart.build();
        mChart.setOnBarClickedListener(new OnBarClickedListener() {
            @Override
            public void onBarClicked(int i) {

            }
        });
        mChart.removeBarValues();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mChart.resetBarValues();
            }
        }, 2000);


    }
    

}
