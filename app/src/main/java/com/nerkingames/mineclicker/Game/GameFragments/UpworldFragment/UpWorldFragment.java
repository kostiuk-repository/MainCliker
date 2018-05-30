package com.nerkingames.mineclicker.Game.GameFragments.UpworldFragment;

import android.app.Fragment;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.nerkingames.mineclicker.Game.GameActivity.GameActivity;
import com.nerkingames.mineclicker.Game.GameFragments.JobFragment.JobFragment;
import com.nerkingames.mineclicker.R;
import com.nerkingames.mineclicker.Views.Buttons.PoolButton.AdShow;
import com.nerkingames.mineclicker.Views.Buttons.PoolButton.MineClock;
import com.nerkingames.mineclicker.Views.Buttons.PoolButton.UpBarLeftSide;
import com.nerkingames.mineclicker.Views.Buttons.UpWorldButtons.Food;
import com.nerkingames.mineclicker.Views.Buttons.UpWorldButtons.IViewGetter;
import com.nerkingames.mineclicker.Views.Buttons.UpWorldButtons.Mine;
import com.nerkingames.mineclicker.Views.Buttons.UpWorldButtons.Mobs;
import com.nerkingames.mineclicker.Views.Buttons.UpWorldButtons.UpWorld;
import com.nerkingames.mineclicker.Views.JobControllers.AggressiveMobsWorker;
import com.nerkingames.mineclicker.Views.JobControllers.Arrow;
import com.nerkingames.mineclicker.Views.JobControllers.Book;
import com.nerkingames.mineclicker.Views.JobControllers.CastleBest;
import com.nerkingames.mineclicker.Views.JobControllers.Diamond;
import com.nerkingames.mineclicker.Views.JobControllers.DiamondHoe;
import com.nerkingames.mineclicker.Views.JobControllers.Diamondblock;
import com.nerkingames.mineclicker.Views.JobControllers.EducatedVillager;
import com.nerkingames.mineclicker.Views.JobControllers.FoodWorker;
import com.nerkingames.mineclicker.Views.JobControllers.Fort;
import com.nerkingames.mineclicker.Views.JobControllers.GoldArmor;
import com.nerkingames.mineclicker.Views.JobControllers.GoldIngot;
import com.nerkingames.mineclicker.Views.JobControllers.GoodMobsWorker;
import com.nerkingames.mineclicker.Views.JobControllers.Horse;
import com.nerkingames.mineclicker.Views.JobControllers.IronIngot;
import com.nerkingames.mineclicker.Views.JobControllers.Leather;
import com.nerkingames.mineclicker.Views.JobControllers.MineWorker;
import com.nerkingames.mineclicker.Views.JobControllers.Saw;
import com.nerkingames.mineclicker.Views.JobControllers.Slime;
import com.nerkingames.mineclicker.Views.JobControllers.TowerThree;
import com.nerkingames.mineclicker.Views.JobControllers.TwoTimeHalfRoof;
import com.nerkingames.mineclicker.Views.JobControllers.UpWorldWorker;
import com.nerkingames.mineclicker.Views.JobControllers.VillagerFireTeam;
import com.nerkingames.mineclicker.Views.JobControllers.VillagerHorseTrener;
import com.nerkingames.mineclicker.Views.JobControllers.VillagerKing;
import com.nerkingames.mineclicker.Views.JobControllers.VillagerMayor;
import com.nerkingames.mineclicker.Views.JobControllers.VillagerSimple;
import com.nerkingames.mineclicker.Views.JobControllers.VillagerSoldat;
import com.nerkingames.mineclicker.Views.JobControllers.VillagerWorkDiamond;
import com.nerkingames.mineclicker.Views.JobControllers.VvillagerUpgrageSergant;
import com.nerkingames.mineclicker.Views.JobControllers.Wheat;
import com.nerkingames.mineclicker.Views.JobControllers.WheatBlock;
import com.nerkingames.mineclicker.Views.JobControllers.WoodButton;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.AndroidInjection;



public class UpWorldFragment extends Fragment{

    private static final String TAG = "Up_World_fragment";
    private JobFragment jobFragment;




    private OnFragmentInteractionListener mListener;
    private View view;
    private RelativeLayout relativeLayout;

    public IViewGetter mobs;

    public IViewGetter mine;

    public IViewGetter food;

    public IViewGetter upw;
    private FrameLayout mainStage;
    private BitmapDrawable logo;
    private UpWorldWorker upWorldWorker;
    public static boolean firstLaunch = true;
    private FoodWorker foodWorker;
    private MineWorker mineWorker;
    private AggressiveMobsWorker aggressiveMobsWorker;
    private GoodMobsWorker goodMobsWorker;

    public UpBarLeftSide getUpBarLeftSide() {
        return upBarLeftSide;
    }

    private UpBarLeftSide upBarLeftSide;
    private MineClock mineClock;
    private AdShow adShow;

    public UpWorldFragment() {

    }


    public static synchronized UpWorldFragment newInstance() {
        return new UpWorldFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void onStart() {
        super.onStart();




    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: " +"in up_w_fragment");
        relativeLayout = view.findViewById(R.id.up_w_fr);

        mine = new Mine(getActivity().getApplicationContext());
        food = new Food(getActivity().getApplicationContext());
        mobs = new Mobs(getActivity().getApplicationContext());
        upw = new UpWorld(getActivity().getApplicationContext());
        try {
            jobFragment.setJobMobs(mobs);
            jobFragment.setJobMine(mine);
            jobFragment.setJobUpWorld(upw);
            jobFragment.setJobFood(food);
        } catch (NullPointerException ex){
            GameActivity.myViewPagerFragmentAdapter.getJobFragment().setJobMobs(mobs);
            GameActivity.myViewPagerFragmentAdapter.getJobFragment().setJobMobs(mine);
            GameActivity.myViewPagerFragmentAdapter.getJobFragment().setJobMobs(upw);
            GameActivity.myViewPagerFragmentAdapter.getJobFragment().setJobMobs(food);
        }
        if(!firstLaunch) {
            if (UpWorldWorker.getInstance(getActivity().getApplicationContext()).getIT().getViewCounter() > 0){
                upw.getWorker().subscribe();
                upw.setClickabled();
            }
            if (FoodWorker.getInstance(getActivity().getApplicationContext()).getIT().getViewCounter() > 0){
                food.getWorker().subscribe();
               food.setClickabled();
            }
            if (MineWorker.getInstance(getActivity().getApplicationContext()).getIT().getViewCounter() > 0){
               mine.getWorker().subscribe();
                mine.setClickabled();
            }
            if (AggressiveMobsWorker.getInstance(getActivity().getApplicationContext()).getIT().getViewCounter() > 0
                    || GoodMobsWorker.getInstance(getActivity().getApplicationContext()).getIT().getViewCounter() > 0){
               mobs.getWorker().subscribe();
               mobs.setClickabled();
            }
          upw.setViewContainer(relativeLayout);
           mine.setViewContainer(relativeLayout);
           mobs.setViewContainer(relativeLayout);
          food.setViewContainer(relativeLayout);
        }

        upBarLeftSide = new UpBarLeftSide(getActivity().getApplicationContext());
        mineClock = new MineClock(getActivity().getApplicationContext(),getActivity());
        adShow = new AdShow(getActivity(),getActivity().getBaseContext());
        relativeLayout.addView(upBarLeftSide);
        relativeLayout.addView(mineClock);
        relativeLayout.addView(adShow);
        mineClock.setX(upBarLeftSide.getmWidth());
        adShow.setX(upBarLeftSide.getmWidth() + mineClock.getmWidth());


        mainStage = new FrameLayout(getActivity().getApplicationContext());
        mainStage.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        logo = new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(),R.mipmap.work_logo));
        mainStage.setBackground(logo);
        mainStage.setX(getResources().getDisplayMetrics().widthPixels/2 - logo.getBitmap().getWidth()/2);
        mainStage.setY(getResources().getDisplayMetrics().heightPixels * 0.11f);
        relativeLayout.addView(mainStage);

        //upWorld
        relativeLayout.addView(upw.getThisView());
       upw.getThisView().setY(getResources().getDisplayMetrics().heightPixels / 4.5f);
       upw.getThisView().setX((getResources().getDisplayMetrics().widthPixels -
                    upw.getvWidth()) / 2);
        Log.d("UpWorld", "was inited; ");
        //Food
        relativeLayout.addView(food.getThisView());
        food.getThisView().setY(upw.getThisView().getY() + upw.getvHeight() + (getResources().getDisplayMetrics().heightPixels * 0.02f));
       food.getThisView().setX((getResources().getDisplayMetrics().widthPixels -
                food.getvWidth()) / 2);

        //Mine
        relativeLayout.addView(mine.getThisView());
       mine.getThisView().setY(food.getThisView().getY() + food.getvHeight() + (getResources().getDisplayMetrics().heightPixels * 0.02f));
       mine.getThisView().setX((getResources().getDisplayMetrics().widthPixels -
               mine.getvWidth()) / 2);

        //mobs
        relativeLayout.addView(mobs.getThisView());
       mobs.getThisView().setY(mine.getThisView().getY() + mine.getvHeight() + (getResources().getDisplayMetrics().heightPixels * 0.02f));
       mobs.getThisView().setX((getResources().getDisplayMetrics().widthPixels -
               mobs.getvWidth()) / 2);

        upWorldWorker = UpWorldWorker.getInstance(getActivity().getApplicationContext());
        upWorldWorker.setUpBarLeftSideUpW(getUpBarLeftSide());
        foodWorker = FoodWorker.getInstance(getActivity().getApplicationContext());
        foodWorker.setUpBarLeftSideUpW(getUpBarLeftSide());
        mineWorker = MineWorker.getInstance(getActivity().getApplicationContext());
        mineWorker.setUpBarLeftSideUpW(getUpBarLeftSide());
        aggressiveMobsWorker = AggressiveMobsWorker.getInstance(getActivity().getApplicationContext());
        aggressiveMobsWorker.setUpBarLeftSideUpW(getUpBarLeftSide());
        goodMobsWorker = GoodMobsWorker.getInstance(getActivity().getApplicationContext());
        goodMobsWorker.setUpBarLeftSideUpW(getUpBarLeftSide());
        WoodButton woodButton = WoodButton.getInstance(getActivity().getApplicationContext());
        woodButton.setUpBarLeftSideUpW(getUpBarLeftSide());
        VillagerSimple villagerSimple = VillagerSimple.getInstance(getActivity().getApplicationContext());
        villagerSimple.setUpBarLeftSideUpW(getUpBarLeftSide());
        Wheat wheat = Wheat.getInstance(getActivity().getApplicationContext());
        wheat.setUpBarLeftSideUpW(getUpBarLeftSide());
        IronIngot ironIngot = IronIngot.getInstance(getActivity().getApplicationContext());
        ironIngot.setUpBarLeftSideUpW(getUpBarLeftSide());
        Book book = Book.getInstance(getActivity().getApplicationContext());
        book.setUpBarLeftSideUpW(getUpBarLeftSide());
        Arrow arrow = Arrow.getInstance(getActivity().getApplicationContext());
        arrow.setUpBarLeftSideUpW(getUpBarLeftSide());
        CastleBest castleBest = CastleBest.getInstance(getActivity().getApplicationContext());
        castleBest.setUpBarLeftSideUpW(getUpBarLeftSide());
        VvillagerUpgrageSergant vvillagerUpgrageSergant = VvillagerUpgrageSergant.getInstance(getActivity().getApplicationContext());
        vvillagerUpgrageSergant.setUpBarLeftSideUpW(getUpBarLeftSide());
        Diamond diamond = Diamond.getInstance(getActivity().getApplicationContext());
        diamond.setUpBarLeftSideUpW(getUpBarLeftSide());
        Diamondblock diamondblock = Diamondblock.getInstance(getActivity().getApplicationContext());
        diamondblock.setUpBarLeftSideUpW(getUpBarLeftSide());
        VillagerHorseTrener villagerHorseTrener = VillagerHorseTrener.getInstance(getActivity().getApplicationContext());
        villagerHorseTrener.setUpBarLeftSideUpW(getUpBarLeftSide());
        VillagerFireTeam villagerFireTeam = VillagerFireTeam.getInstance(getActivity().getApplicationContext());
        villagerFireTeam.setUpBarLeftSideUpW(getUpBarLeftSide());
        Fort fort = Fort.getInstance(getActivity().getApplicationContext());
        fort.setUpBarLeftSideUpW(getUpBarLeftSide());
        GoldIngot goldIngot = GoldIngot.getInstance(getActivity().getApplicationContext());
        goldIngot.setUpBarLeftSideUpW(getUpBarLeftSide());
        DiamondHoe diamondHoe = DiamondHoe.getInstance(getActivity().getApplicationContext());
        diamondHoe.setUpBarLeftSideUpW(getUpBarLeftSide());
        Horse horse = Horse.getInstance(getActivity().getApplicationContext());
        horse.setUpBarLeftSideUpW(getUpBarLeftSide());
        VillagerWorkDiamond villagerWorkDiamond = VillagerWorkDiamond.getInstance(getActivity().getApplicationContext());
        villagerWorkDiamond.setUpBarLeftSideUpW(getUpBarLeftSide());
        VillagerKing villagerKing = VillagerKing.getInstance(getActivity().getApplicationContext());
        villagerKing.setUpBarLeftSideUpW(getUpBarLeftSide());
        Leather leather = Leather.getInstance(getActivity().getApplicationContext());
        leather.setUpBarLeftSideUpW(getUpBarLeftSide());
        VillagerMayor villagerMayor = VillagerMayor.getInstance(getActivity().getApplicationContext());
        villagerMayor.setUpBarLeftSideUpW(getUpBarLeftSide());
        Saw saw = Saw.getInstance(getActivity().getApplicationContext());
        saw.setUpBarLeftSideUpW(getUpBarLeftSide());
        GoldArmor goldArmor = GoldArmor.getInstance(getActivity().getApplicationContext());
        goldArmor.setUpBarLeftSideUpW(getUpBarLeftSide());
        Slime slime = Slime.getInstance(getActivity().getApplicationContext());
        slime.setUpBarLeftSideUpW(getUpBarLeftSide());
        EducatedVillager educatedVillager = EducatedVillager.getInstance(getActivity().getApplicationContext());
        educatedVillager.setUpBarLeftSideUpW(getUpBarLeftSide());
        VillagerSoldat villagerSoldat = VillagerSoldat.getInstance(getActivity().getApplicationContext());
        villagerSoldat.setUpBarLeftSideUpW(getUpBarLeftSide());
        TwoTimeHalfRoof twoTimeHalfRoof = TwoTimeHalfRoof.getInstance(getActivity().getApplicationContext());
        twoTimeHalfRoof.setUpBarLeftSideUpW(getUpBarLeftSide());
        TowerThree towerThree = TowerThree.getInstance(getActivity().getApplicationContext());
        towerThree.setUpBarLeftSideUpW(getUpBarLeftSide());
        WheatBlock wheatBlock = WheatBlock.getInstance(getActivity().getApplicationContext());
        wheatBlock.setUpBarLeftSideUpW(getUpBarLeftSide());

        firstLaunch = false;
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: " +"in up_w_fragment");
        if(upw.getDisposable() != null){
           upw.getDisposable().dispose();
        }
        if(food.getDisposable() != null){
            food.getDisposable().dispose();
        }
        if(mine.getDisposable() != null){
           mine.getDisposable().dispose();
        }
        relativeLayout.removeAllViews();
        Log.d("UpWorldF","onStop");
        relativeLayout.removeAllViews();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        relativeLayout.removeAllViews();
        Log.d("UpWorldF","onDestroyView");
        relativeLayout.removeAllViews();
    }

    @Override
    public void onDestroy() {
        relativeLayout.removeAllViews();
        super.onDestroy();
        Log.d("UpWorldF","onDestroy");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_up_world, container, false);


        return view;
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
    public void onDetach() {
        super.onDetach();
        mListener = null;

    }

    @Override
    public void onPause() {
        super.onPause();
        if(upw.getDisposable() != null){
            upw.getDisposable().dispose();
        }
        if(upw.getDisposable() != null){
           upw.getDisposable().dispose();
        }
        if(upw.getDisposable() != null){
           upw.getDisposable().dispose();
        }
        relativeLayout.removeAllViews();
    }

    public void setJobFragment(JobFragment jobFragment) {
        this.jobFragment = jobFragment;
    }


    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
