package md.com.quiz.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import md.com.quiz.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Topics#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Topics extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public Topics() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Topics.
     */
    // TODO: Rename and change types and number of parameters
    public static Topics newInstance(String param1, String param2) {
        Topics fragment = new Topics();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_topcs, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        Activity context=getActivity();
        TabLayout tabLayout=context.findViewById(R.id.tabLayout);
        ViewPager viewPager=context.findViewById(R.id.viewPager);
        CustomPagerFragments customPagerFragments=new CustomPagerFragments(getChildFragmentManager());
        customPagerFragments.addFragments(new PopularFragments(),"Popular Topics");
        customPagerFragments.addFragments(new AllTopics(),"All Topics");
        viewPager.setAdapter(customPagerFragments);
        tabLayout.setupWithViewPager(viewPager);

    }
    class CustomPagerFragments extends FragmentPagerAdapter {
        ArrayList<Fragment> fragments=new ArrayList<>();
        ArrayList<String> fragmentTitle=new ArrayList<>();

        public CustomPagerFragments(FragmentManager fm) {
            super(fm);
        }
        public void addFragments(Fragment fragment, String title)
        {
            fragments.add(fragment);
            fragmentTitle.add(title);
        }
        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }
        public CharSequence getPageTitle(int position)
        {
            return fragmentTitle.get(position);
        }
        @Override
        public int getCount() {
            return fragments.size();
        }
    }

}
