package com.nerkingames.mineclicker.Game.GameFragments.JobFragment;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.nerkingames.mineclicker.R;
import com.nerkingames.mineclicker.Views.Buttons.PoolButton.AdShow;
import com.nerkingames.mineclicker.Views.Buttons.PoolButton.MineClock;
import com.nerkingames.mineclicker.Views.Buttons.PoolButton.UpBarLeftSide;
import com.nerkingames.mineclicker.Views.Buttons.UpWorldButtons.IViewGetter;
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

import dagger.android.AndroidInjection;
import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;


public class JobFragment extends Fragment {



    private RelativeLayout fragmentContainer;
    private FrameLayout buttonContainer;
    private ScrollView buttonScrollContainer;
    private GridLayout gridForButtons;
    private Bitmap bitmap;
    private FrameLayout mainStage;
    private BitmapDrawable logo;
    private static UpBarLeftSide upBarLeftSide;
    private MineClock mineClock;
    private AdShow adShow;
    private ObjectInitializer objectInitializer;
    private View thisView;
    public static UpBarLeftSide getUpBarLeftSide() {
        return upBarLeftSide;
    }
    private UpWorldWorker upWorldWorker;
    private FoodWorker foodWoker;
    private MineWorker mineWorker;
    private AggressiveMobsWorker aggressiveMobsWorker;
    private GoodMobsWorker goodMobsWorker;
    private IViewGetter upWorld;
    private IViewGetter mine;
    private static boolean fflaunch = true;

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    ViewPager viewPager;

    public void setJobUpWorld(IViewGetter upWorld) {
        this.upWorld = upWorld;
    }

    public void setJobMine(IViewGetter mine) {
        this.mine = mine;
    }

    public void setJobMobs(IViewGetter mobs) {
        this.mobs = mobs;
    }

    public void setJobFood(IViewGetter food) {
        this.food = food;
    }

    private IViewGetter mobs;
    private IViewGetter food;



    @Override
    public void onStart() {
        super.onStart();
        fragmentContainer = thisView.findViewById(R.id.job_fragment_container);

    }

    private OnFragmentInteractionListener mListener;




    public JobFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        upBarLeftSide = new UpBarLeftSide(getActivity().getApplicationContext());
        mineClock = new MineClock(getActivity().getApplicationContext(),getActivity());
        adShow = new AdShow(getActivity(),getActivity().getBaseContext());
        fragmentContainer.addView(upBarLeftSide);
        fragmentContainer.addView(mineClock);
        fragmentContainer.addView(adShow);
        mineClock.setX(upBarLeftSide.getmWidth());
        adShow.setX(upBarLeftSide.getmWidth() + mineClock.getmWidth());


        mainStage = new FrameLayout(getActivity().getApplicationContext());
        mainStage.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        logo = new BitmapDrawable(getResources(),BitmapFactory.decodeResource(getResources(),R.mipmap.kraft_logo));
        mainStage.setBackground(logo);
        mainStage.setX(getResources().getDisplayMetrics().widthPixels/2 - logo.getBitmap().getWidth()/2);
        mainStage.setY(getResources().getDisplayMetrics().heightPixels * 0.11f);
        fragmentContainer.addView(mainStage);
        initialiseStructure();
        upWorldWorker = UpWorldWorker.getInstance(getActivity().getApplicationContext());
        upWorldWorker.setUpBarLeftSideJob(getUpBarLeftSide());
        foodWoker = FoodWorker.getInstance(getActivity().getApplicationContext());
        foodWoker.setUpBarLeftSideJob(getUpBarLeftSide());
        mineWorker = MineWorker.getInstance(getActivity().getApplicationContext());
        mineWorker.setUpBarLeftSideJob(getUpBarLeftSide());
        aggressiveMobsWorker = AggressiveMobsWorker.getInstance(getActivity().getApplicationContext());
        aggressiveMobsWorker.setUpBarLeftSideJob(getUpBarLeftSide());
        goodMobsWorker = GoodMobsWorker.getInstance(getActivity().getApplicationContext());
        goodMobsWorker.setUpBarLeftSideJob(getUpBarLeftSide());
        WoodButton woodButton = WoodButton.getInstance(getActivity().getApplicationContext());
        woodButton.setUpBarLeftSideJob(getUpBarLeftSide());
        VillagerSimple villagerSimple = VillagerSimple.getInstance(getActivity().getApplicationContext());
        villagerSimple.setUpBarLeftSideJob(getUpBarLeftSide());
        Wheat wheat = Wheat.getInstance(getActivity().getApplicationContext());
        wheat.setUpBarLeftSideJob(getUpBarLeftSide());
        IronIngot ironIngot = IronIngot.getInstance(getActivity().getApplicationContext());
        ironIngot.setUpBarLeftSideJob(getUpBarLeftSide());
        Book book = Book.getInstance(getActivity().getApplicationContext());
        book.setUpBarLeftSideJob(getUpBarLeftSide());
        Arrow arrow = Arrow.getInstance(getActivity().getApplicationContext());
        arrow.setUpBarLeftSideJob(getUpBarLeftSide());
        CastleBest castleBest = CastleBest.getInstance(getActivity().getApplicationContext());
        castleBest.setUpBarLeftSideJob(getUpBarLeftSide());
        VvillagerUpgrageSergant vvillagerUpgrageSergant = VvillagerUpgrageSergant.getInstance(getActivity().getApplicationContext());
        vvillagerUpgrageSergant.setUpBarLeftSideJob(getUpBarLeftSide());
        Diamond diamond = Diamond.getInstance(getActivity().getApplicationContext());
        diamond.setUpBarLeftSideJob(getUpBarLeftSide());
        Diamondblock diamondblock = Diamondblock.getInstance(getActivity().getApplicationContext());
        diamondblock.setUpBarLeftSideJob(getUpBarLeftSide());
        VillagerHorseTrener villagerHorseTrener = VillagerHorseTrener.getInstance(getActivity().getApplicationContext());
        villagerHorseTrener.setUpBarLeftSideJob(getUpBarLeftSide());
        VillagerFireTeam villagerFireTeam = VillagerFireTeam.getInstance(getActivity().getApplicationContext());
        villagerFireTeam.setUpBarLeftSideJob(getUpBarLeftSide());
        Fort fort = Fort.getInstance(getActivity().getApplicationContext());
        fort.setUpBarLeftSideJob(getUpBarLeftSide());
        GoldIngot goldIngot = GoldIngot.getInstance(getActivity().getApplicationContext());
        goldIngot.setUpBarLeftSideJob(getUpBarLeftSide());
        DiamondHoe diamondHoe = DiamondHoe.getInstance(getActivity().getApplicationContext());
        diamondHoe.setUpBarLeftSideJob(getUpBarLeftSide());
        Horse horse = Horse.getInstance(getActivity().getApplicationContext());
        horse.setUpBarLeftSideJob(getUpBarLeftSide());
        VillagerWorkDiamond villagerWorkDiamond = VillagerWorkDiamond.getInstance(getActivity().getApplicationContext());
        villagerWorkDiamond.setUpBarLeftSideJob(getUpBarLeftSide());
        VillagerKing villagerKing = VillagerKing.getInstance(getActivity().getApplicationContext());
        villagerKing.setUpBarLeftSideJob(getUpBarLeftSide());
        Leather leather = Leather.getInstance(getActivity().getApplicationContext());
        leather.setUpBarLeftSideJob(getUpBarLeftSide());
        VillagerMayor villagerMayor = VillagerMayor.getInstance(getActivity().getApplicationContext());
        villagerMayor.setUpBarLeftSideJob(getUpBarLeftSide());
        Saw saw = Saw.getInstance(getActivity().getApplicationContext());
        saw.setUpBarLeftSideJob(getUpBarLeftSide());
        GoldArmor goldArmor = GoldArmor.getInstance(getActivity().getApplicationContext());
        goldArmor.setUpBarLeftSideJob(getUpBarLeftSide());
        Slime slime = Slime.getInstance(getActivity().getApplicationContext());
        slime.setUpBarLeftSideJob(getUpBarLeftSide());
        EducatedVillager educatedVillager = EducatedVillager.getInstance(getActivity().getApplicationContext());
        educatedVillager.setUpBarLeftSideJob(getUpBarLeftSide());
        VillagerSoldat villagerSoldat = VillagerSoldat.getInstance(getActivity().getApplicationContext());
        villagerSoldat.setUpBarLeftSideJob(getUpBarLeftSide());
        TwoTimeHalfRoof twoTimeHalfRoof = TwoTimeHalfRoof.getInstance(getActivity().getApplicationContext());
        twoTimeHalfRoof.setUpBarLeftSideJob(getUpBarLeftSide());
        TowerThree towerThree = TowerThree.getInstance(getActivity().getApplicationContext());
        towerThree.setUpBarLeftSideJob(getUpBarLeftSide());
        WheatBlock wheatBlock = WheatBlock.getInstance(getActivity().getApplicationContext());
        wheatBlock.setUpBarLeftSideJob(getUpBarLeftSide());


    }

    @Override
    public void onDestroyView() {
        gridForButtons.removeAllViews();
        buttonScrollContainer.removeAllViews();
        buttonContainer.removeAllViews();
        fragmentContainer.removeAllViews();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: in method for JobFragmrn");
        fflaunch = true;
    }

    @Override
    public void onPause() {
        gridForButtons.removeAllViews();
        buttonScrollContainer.removeAllViews();
        buttonContainer.removeAllViews();
        fragmentContainer.removeAllViews();
        super.onPause();
    }

    @Override
    public void onStop() {
        gridForButtons.removeAllViews();
        buttonScrollContainer.removeAllViews();
        buttonContainer.removeAllViews();
        fragmentContainer.removeAllViews();
        fflaunch = true;
        super.onStop();
    }


    public static synchronized JobFragment newInstance() {
        return new JobFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        thisView = inflater.inflate(R.layout.fragment_job, container, false);

        return thisView;
    }

    // TODO: Rename method, update argument and hook method into UI event
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public void initialiseStructure(){
        int wight;
        int height;



        Handler handler = new Handler();
        buttonContainer = new FrameLayout(getActivity().getApplicationContext());
        buttonContainer.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        buttonScrollContainer = new ScrollView(getActivity().getApplicationContext());
        if(fflaunch) {
            Log.d(TAG, "initialiseStructure:  innit grid view");
            gridForButtons = new GridLayout(getActivity().getApplicationContext());
            fflaunch = false;
        }
        try {
            gridForButtons.setLayoutParams(new GridLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)));
        } catch (NullPointerException e){
            gridForButtons = new GridLayout(getActivity().getApplicationContext());
            gridForButtons.setLayoutParams(new GridLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)));
        }

        bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.kraft_background01);
        wight = bitmap.getWidth();
        height = bitmap.getHeight();
        buttonScrollContainer.setLayoutParams(new FrameLayout.LayoutParams((int)(wight*0.9f), (int)(height*0.9f)));
        buttonContainer.setBackground(getResources().getDrawable(R.mipmap.kraft_background01));
        fragmentContainer.addView(buttonContainer);
        buttonContainer.setX((getResources().getDisplayMetrics().widthPixels - wight)/2);
        buttonContainer.setY(getResources().getDisplayMetrics().heightPixels*0.225f);
        buttonContainer.addView(buttonScrollContainer);
        buttonScrollContainer.setX(wight*0.05f);
        buttonScrollContainer.setY(height*0.048f);
        buttonScrollContainer.setScrollbarFadingEnabled(true);
        buttonScrollContainer.addView(gridForButtons);
        buttonScrollContainer.setVerticalFadingEdgeEnabled(true);
        buttonScrollContainer.setFadingEdgeLength(50);
        gridForButtons.setVerticalFadingEdgeEnabled(true);
        gridForButtons.setFadingEdgeLength(50);
        gridForButtons.setColumnCount(4);
        gridForButtons.setRowCount(41);
        Completable.fromRunnable(() ->{
                    objectInitializer = new ObjectInitializer(getActivity().getApplicationContext(),gridForButtons, viewPager);

                    objectInitializer.setHandler(handler);
                    objectInitializer.setUpWorld(upWorld);
                    objectInitializer.setMine(mine);
                    objectInitializer.setMobs(mobs);
                    objectInitializer.setFood(food);
                    objectInitializer.createView();
            }
                )
                .subscribeOn(Schedulers.io())
                .subscribe();


    }



}
