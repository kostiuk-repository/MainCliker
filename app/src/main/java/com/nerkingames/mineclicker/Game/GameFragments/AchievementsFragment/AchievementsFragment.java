package com.nerkingames.mineclicker.Game.GameFragments.AchievementsFragment;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.nerkingames.mineclicker.R;
import com.nerkingames.mineclicker.Views.Buttons.PoolButton.AdShow;
import com.nerkingames.mineclicker.Views.Buttons.PoolButton.MineClock;
import com.nerkingames.mineclicker.Views.Buttons.PoolButton.UpBarLeftSide;
import com.nerkingames.mineclicker.Views.JobControllers.AggressiveMobsWorker;
import com.nerkingames.mineclicker.Views.JobControllers.FoodWorker;
import com.nerkingames.mineclicker.Views.JobControllers.GoodMobsWorker;
import com.nerkingames.mineclicker.Views.JobControllers.MineWorker;
import com.nerkingames.mineclicker.Views.JobControllers.UpWorldWorker;

import dagger.android.AndroidInjection;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AchievementsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AchievementsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AchievementsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    View v;
    RelativeLayout archFrag;
    FrameLayout mainStage;
    BitmapDrawable logo;
    private UpWorldWorker upWorldWorker;
    private FoodWorker foodWoker;
    private MineWorker mineWorker;
    private AggressiveMobsWorker aggressiveMobsWorker;
    private GoodMobsWorker goodMobsWorker;

    public UpBarLeftSide getUpBarLeftSide() {
        return upBarLeftSide;
    }

    private UpBarLeftSide upBarLeftSide;


    private OnFragmentInteractionListener mListener;
    private MineClock mineClock;
    private AdShow adShow;
    private FrameLayout buttonContainer;
    private ScrollView buttonScrollContainer;
    private ListView listForAChivments;
    private Bitmap bitmap;

    public AchievementsFragment() {

    }



    public static synchronized AchievementsFragment newInstance() {
        return new AchievementsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_achievements, container, false);
        return v;
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        AndroidInjection.inject(this);
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        archFrag = v.findViewById(R.id.arch_fr);



    }

    @Override
    public void onResume() {
        super.onResume();
        upBarLeftSide = new UpBarLeftSide(getActivity().getApplicationContext());
        mineClock = new MineClock(getActivity().getApplicationContext(),getActivity());
        adShow = new AdShow(getActivity(),getActivity().getBaseContext());
        archFrag.addView(upBarLeftSide);
        archFrag.addView(mineClock);
        archFrag.addView(adShow);
        mineClock.setX(upBarLeftSide.getmWidth());
        adShow.setX(upBarLeftSide.getmWidth() + mineClock.getmWidth());

        mainStage = new FrameLayout(getActivity().getApplicationContext());
        mainStage.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        logo = new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(),R.mipmap.achivment_logo));
        mainStage.setBackground(logo);
        mainStage.setX(getResources().getDisplayMetrics().widthPixels/2 - logo.getBitmap().getWidth()/2);
        mainStage.setY(getResources().getDisplayMetrics().heightPixels * 0.11f);
        archFrag.addView(mainStage);

        int wight;
        int height;

        buttonContainer = new FrameLayout(getActivity().getApplicationContext());
        buttonContainer.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        buttonScrollContainer = new ScrollView(getActivity().getApplicationContext());
        listForAChivments = new ListView(getActivity().getApplicationContext());
        listForAChivments.setLayoutParams(new ListView.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)));
        bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.achivment_fon);
        wight = bitmap.getWidth();
        height = bitmap.getHeight();
        buttonScrollContainer.setLayoutParams(new FrameLayout.LayoutParams((int)(wight*0.9f), (int)(height*0.9f)));
        buttonContainer.setBackground(getResources().getDrawable(R.mipmap.achivment_fon));
        archFrag.addView(buttonContainer);
        buttonContainer.setX((getResources().getDisplayMetrics().widthPixels - wight)/2);
        buttonContainer.setY(getResources().getDisplayMetrics().heightPixels*0.225f);
        buttonContainer.addView(buttonScrollContainer);
        buttonScrollContainer.setX(wight*0.05f);
        buttonScrollContainer.setY(height*0.048f);
        buttonScrollContainer.setScrollbarFadingEnabled(true);
        buttonScrollContainer.addView(listForAChivments);
        buttonScrollContainer.setVerticalFadingEdgeEnabled(true);
        buttonScrollContainer.setFadingEdgeLength(50);
        listForAChivments.setVerticalFadingEdgeEnabled(true);
        listForAChivments.setFadingEdgeLength(50);

        upWorldWorker = UpWorldWorker.getInstance(getActivity().getApplicationContext());
        upWorldWorker.setUpBarLeftSideAch(getUpBarLeftSide());
        foodWoker = FoodWorker.getInstance(getActivity().getApplicationContext());
        foodWoker.setUpBarLeftSideAch(getUpBarLeftSide());
        mineWorker = MineWorker.getInstance(getActivity().getApplicationContext());
        mineWorker.setUpBarLeftSideAch(getUpBarLeftSide());
        aggressiveMobsWorker = AggressiveMobsWorker.getInstance(getActivity().getApplicationContext());
        aggressiveMobsWorker.setUpBarLeftSideAch(getUpBarLeftSide());
        goodMobsWorker = GoodMobsWorker.getInstance(getActivity().getApplicationContext());
        goodMobsWorker.setUpBarLeftSideAch(getUpBarLeftSide());




    }

    @Override
    public void onStop() {
        super.onStop();
        archFrag.removeAllViews();

    }

    @Override
    public void onPause() {
        super.onPause();
        archFrag.removeAllViews();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
