package com.example.srk.navigationdrawer.Fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.srk.navigationdrawer.R;
import com.example.srk.navigationdrawer.Adapter.ViewPagerAdapter;

public class ActivitiesFragment extends Fragment {

    View v;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_activities,container,false);
        tabLayout = v.findViewById(R.id.tablayout_id);
        viewPager = v.findViewById(R.id.viewpager_id);
        viewPager.setOffscreenPageLimit(3);
        adapter = new ViewPagerAdapter(getChildFragmentManager());

        // Add Fragment Here
        adapter.AddFragment(new FragmentBookBank(),"");
        adapter.AddFragment(new FragmentIssue(),"");
        adapter.AddFragment(new FragmentFine(),"");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_bookbank);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_issue);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_fine);

        return v;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


//       public void callfromMainActivity() {
//
//       Toast.makeText(getActivity(), "Activies Fragment called", Toast.LENGTH_SHORT).show();
//    }
}
