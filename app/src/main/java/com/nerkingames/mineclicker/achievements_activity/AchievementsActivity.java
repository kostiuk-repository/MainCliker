package com.nerkingames.mineclicker.achievements_activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.nerkingames.mineclicker.DataBase.AchievementsDao;
import com.nerkingames.mineclicker.DataBase.AchievementsTable;
import com.nerkingames.mineclicker.DataBase.DataBase;
import com.nerkingames.mineclicker.R;
import com.nerkingames.mineclicker.Views.AchievementsButtons.SampleAchievements;
import com.nerkingames.mineclicker.Views.Buttons.SettingActivityButtons.MusicSwitcher;
import com.nerkingames.mineclicker.app.App;
import com.nerkingames.mineclicker.music.MusicPlayer;

import java.util.List;

public class AchievementsActivity extends AppCompatActivity {

    private static final String TAG = "ACH_FRAG";
    private FrameLayout buttonContainer;
    private ScrollView buttonScrollContainer;
    private GridLayout listForAChivments;
    private RelativeLayout archFrag;
    private FrameLayout mainStage;
    private BitmapDrawable logo;
    private Bitmap bitmap;
    private AchievementsDao achievementsDao;
    private DataBase dataBase;
    List<AchievementsTable> achievementsTableList;
    private BroadcastReceiver broadcastReceiver;
    MusicPlayer musicPlayer = MusicPlayer.getInstanse(this);
    private AdView mAdView;

    @Override
    protected void onResume() {
        super.onResume();
        if(musicPlayer.getMediaPlayer() != null) {
            Log.d("Media player","NOT NULL");
            if (musicPlayer.dontWork() && musicPlayer.isInit("Start Activity Check")
                    && MusicSwitcher.MUZIK_CHEKER == 1) {
                musicPlayer.start();
            }
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_achievements);
        archFrag = findViewById(R.id.AchAct);
        dataBase = App.getInstance().getDatabase();
        achievementsDao = dataBase.getAchievementsDao();
        achievementsTableList = achievementsDao.getAllCounters();

        MobileAds.initialize(this,"ca-app-pub-7435705988077014~3307924235");

        mAdView = findViewById(R.id.adViewAch);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);




        mainStage = new FrameLayout(getApplicationContext());
        mainStage.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        logo = new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(),R.mipmap.achivment_logo));
        mainStage.setBackground(logo);
        mainStage.setX(getResources().getDisplayMetrics().widthPixels/2 - logo.getBitmap().getWidth()/2);
        mainStage.setY(getResources().getDisplayMetrics().heightPixels * 0.09f);
        archFrag.addView(mainStage);

        int wight;
        int height;

        buttonContainer = new FrameLayout(getApplicationContext());
        buttonContainer.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        buttonScrollContainer = new ScrollView(getApplicationContext());
        listForAChivments = new GridLayout(getApplicationContext());
        listForAChivments.setColumnCount(1);
        listForAChivments.setRowCount(40);
        listForAChivments.setLayoutParams(new ListView.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)));
        bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.achivment_fon);
        wight = bitmap.getWidth();
        height = bitmap.getHeight();
        buttonScrollContainer.setLayoutParams(new FrameLayout.LayoutParams(wight, (int)(height*0.9f)));
        buttonContainer.setBackground(getResources().getDrawable(R.mipmap.achivment_fon));
        archFrag.addView(buttonContainer);
        buttonContainer.setX((getResources().getDisplayMetrics().widthPixels - wight)/2);
        buttonContainer.setY(getResources().getDisplayMetrics().heightPixels*0.225f);
        buttonContainer.addView(buttonScrollContainer);
        buttonScrollContainer.setY(height*0.035f);
        buttonScrollContainer.setScrollbarFadingEnabled(true);
        buttonScrollContainer.addView(listForAChivments);
        buttonScrollContainer.setVerticalFadingEdgeEnabled(true);
        buttonScrollContainer.setFadingEdgeLength(50);
        listForAChivments.setVerticalFadingEdgeEnabled(true);
        listForAChivments.setFadingEdgeLength(50);


        IntentFilter intentFilterACSD = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);


        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                    Log.d("Start ACtivity","Home button");
                    if(!musicPlayer.dontWork() && musicPlayer.isInit("Start Activity Check")
                            && MusicSwitcher.MUZIK_CHEKER == 1)
                        musicPlayer.pause();
                }
            }
        };
        this.registerReceiver(broadcastReceiver, intentFilterACSD);



        Log.d(TAG, "onCreate: " + achievementsTableList.size());
        if(achievementsTableList.size() != 0) {
            Log.d(TAG, "inMetod: addVIEWS" + achievementsTableList.size());
            for (AchievementsTable achievementsTable : achievementsTableList) {
                if (achievementsTable.getId().equals("WOOD")) {
                    Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.mipmap.wood01);
                    SampleAchievements sampleAchievements = new SampleAchievements(getApplicationContext(), R.mipmap.wood01);
                    GridLayout.LayoutParams f = new GridLayout.LayoutParams(new ViewGroup.MarginLayoutParams(bitmap2.getWidth(),bitmap2.getHeight()));
                    f.setMargins((bitmap.getWidth() - bitmap2.getWidth())/2,10,(bitmap.getWidth() - bitmap2.getWidth())/2,0);
                    sampleAchievements.setLayoutParams(f);
                    listForAChivments.addView(sampleAchievements);
                }
                if (achievementsTable.getId().equals("VILLAGER")) {
                    Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.mipmap.villager04);
                    SampleAchievements sampleAchievements = new SampleAchievements(getApplicationContext(), R.mipmap.villager04);
                    GridLayout.LayoutParams f = new GridLayout.LayoutParams(new ViewGroup.MarginLayoutParams(bitmap2.getWidth(),bitmap2.getHeight()));
                    f.setMargins((bitmap.getWidth() - bitmap2.getWidth())/2,10,(bitmap.getWidth() - bitmap2.getWidth())/2,0);
                    sampleAchievements.setLayoutParams(f);
                    listForAChivments.addView(sampleAchievements);
                }
                if (achievementsTable.getId().equals("WORKER")) {
                    Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.mipmap.villager05);
                    SampleAchievements sampleAchievements = new SampleAchievements(getApplicationContext(), R.mipmap.villager05);
                    GridLayout.LayoutParams f = new GridLayout.LayoutParams(new ViewGroup.MarginLayoutParams(bitmap2.getWidth(),bitmap2.getHeight()));
                    f.setMargins((bitmap.getWidth() - bitmap2.getWidth())/2,10,(bitmap.getWidth() - bitmap2.getWidth())/2,0);
                    sampleAchievements.setLayoutParams(f);
                    listForAChivments.addView(sampleAchievements);
                }
                if (achievementsTable.getId().equals("WHEAT")) {
                    Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.mipmap.wheat03);
                    SampleAchievements sampleAchievements = new SampleAchievements(getApplicationContext(), R.mipmap.wheat03);
                    GridLayout.LayoutParams f = new GridLayout.LayoutParams(new ViewGroup.MarginLayoutParams(bitmap2.getWidth(),bitmap2.getHeight()));
                    f.setMargins((bitmap.getWidth() - bitmap2.getWidth())/2,10,(bitmap.getWidth() - bitmap2.getWidth())/2,0);
                    sampleAchievements.setLayoutParams(f);
                    listForAChivments.addView(sampleAchievements);
                }
                if (achievementsTable.getId().equals("IRON_INGOT")) {
                    Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.mipmap.iron02);
                    SampleAchievements sampleAchievements = new SampleAchievements(getApplicationContext(), R.mipmap.iron02);
                    GridLayout.LayoutParams f = new GridLayout.LayoutParams(new ViewGroup.MarginLayoutParams(bitmap2.getWidth(),bitmap2.getHeight()));
                    f.setMargins((bitmap.getWidth() - bitmap2.getWidth())/2,10,(bitmap.getWidth() - bitmap2.getWidth())/2,0);
                    sampleAchievements.setLayoutParams(f);
                    listForAChivments.addView(sampleAchievements);
                }
                if (achievementsTable.getId().equals("BOOK")) {
                    Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.mipmap.book06);
                    SampleAchievements sampleAchievements = new SampleAchievements(getApplicationContext(), R.mipmap.book06);
                    GridLayout.LayoutParams f = new GridLayout.LayoutParams(new ViewGroup.MarginLayoutParams(bitmap2.getWidth(),bitmap2.getHeight()));
                    f.setMargins((bitmap.getWidth() - bitmap2.getWidth())/2,10,(bitmap.getWidth() - bitmap2.getWidth())/2,0);
                    sampleAchievements.setLayoutParams(f);
                    listForAChivments.addView(sampleAchievements);
                }

                if (achievementsTable.getId().equals("AROOW")) {
                    Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.mipmap.arrow11);
                    SampleAchievements sampleAchievements = new SampleAchievements(getApplicationContext(), R.mipmap.arrow11);
                    GridLayout.LayoutParams f = new GridLayout.LayoutParams(new ViewGroup.MarginLayoutParams(bitmap2.getWidth(),bitmap2.getHeight()));
                    f.setMargins((bitmap.getWidth() - bitmap2.getWidth())/2,10,(bitmap.getWidth() - bitmap2.getWidth())/2,0);
                    sampleAchievements.setLayoutParams(f);
                    listForAChivments.addView(sampleAchievements);
                }
                if (achievementsTable.getId().equals("CASTLE_BEST")) {
                    Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.mipmap.castle29);
                    SampleAchievements sampleAchievements = new SampleAchievements(getApplicationContext(), R.mipmap.castle29);
                    GridLayout.LayoutParams f = new GridLayout.LayoutParams(new ViewGroup.MarginLayoutParams(bitmap2.getWidth(),bitmap2.getHeight()));
                    f.setMargins((bitmap.getWidth() - bitmap2.getWidth())/2,10,(bitmap.getWidth() - bitmap2.getWidth())/2,0);
                    sampleAchievements.setLayoutParams(f);
                    listForAChivments.addView(sampleAchievements);
                }
                if (achievementsTable.getId().equals("VILLAGER_UPGRATE_SERGANT")) {
                    Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.mipmap.comandir15);
                    SampleAchievements sampleAchievements = new SampleAchievements(getApplicationContext(), R.mipmap.comandir15);
                    GridLayout.LayoutParams f = new GridLayout.LayoutParams(new ViewGroup.MarginLayoutParams(bitmap2.getWidth(),bitmap2.getHeight()));
                    f.setMargins((bitmap.getWidth() - bitmap2.getWidth())/2,10,(bitmap.getWidth() - bitmap2.getWidth())/2,0);
                    sampleAchievements.setLayoutParams(f);
                    listForAChivments.addView(sampleAchievements);
                }
                if (achievementsTable.getId().equals("DIAMOND")) {
                    Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.mipmap.diamond23);
                    SampleAchievements sampleAchievements = new SampleAchievements(getApplicationContext(), R.mipmap.diamond23);
                    GridLayout.LayoutParams f = new GridLayout.LayoutParams(new ViewGroup.MarginLayoutParams(bitmap2.getWidth(),bitmap2.getHeight()));
                    f.setMargins((bitmap.getWidth() - bitmap2.getWidth())/2,10,(bitmap.getWidth() - bitmap2.getWidth())/2,0);
                    sampleAchievements.setLayoutParams(f);
                    listForAChivments.addView(sampleAchievements);
                }
                if (achievementsTable.getId().equals("DIAMONDBLOCK")) {
                    Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.mipmap.diamondblock28);
                    SampleAchievements sampleAchievements = new SampleAchievements(getApplicationContext(), R.mipmap.diamondblock28);
                    GridLayout.LayoutParams f = new GridLayout.LayoutParams(new ViewGroup.MarginLayoutParams(bitmap2.getWidth(),bitmap2.getHeight()));
                    f.setMargins((bitmap.getWidth() - bitmap2.getWidth())/2,10,(bitmap.getWidth() - bitmap2.getWidth())/2,0);
                    sampleAchievements.setLayoutParams(f);
                    listForAChivments.addView(sampleAchievements);
                }
                if (achievementsTable.getId().equals("THE_END")) {
                    Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.mipmap.end30);
                    SampleAchievements sampleAchievements = new SampleAchievements(getApplicationContext(), R.mipmap.end30);
                    GridLayout.LayoutParams f = new GridLayout.LayoutParams(new ViewGroup.MarginLayoutParams(bitmap2.getWidth(),bitmap2.getHeight()));
                    f.setMargins((bitmap.getWidth() - bitmap2.getWidth())/2,10,(bitmap.getWidth() - bitmap2.getWidth())/2,0);
                    sampleAchievements.setLayoutParams(f);
                    listForAChivments.addView(sampleAchievements);
                }
                if (achievementsTable.getId().equals("HORSE_TRENER")) {
                    Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.mipmap.fermer20);
                    SampleAchievements sampleAchievements = new SampleAchievements(getApplicationContext(), R.mipmap.fermer20);
                    GridLayout.LayoutParams f = new GridLayout.LayoutParams(new ViewGroup.MarginLayoutParams(bitmap2.getWidth(),bitmap2.getHeight()));
                    f.setMargins((bitmap.getWidth() - bitmap2.getWidth())/2,10,(bitmap.getWidth() - bitmap2.getWidth())/2,0);
                    sampleAchievements.setLayoutParams(f);
                    listForAChivments.addView(sampleAchievements);
                }
                if (achievementsTable.getId().equals("VILLAGER_FIRE_TEAM")) {
                    Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.mipmap.fire12);
                    SampleAchievements sampleAchievements = new SampleAchievements(getApplicationContext(), R.mipmap.fire12);
                    GridLayout.LayoutParams f = new GridLayout.LayoutParams(new ViewGroup.MarginLayoutParams(bitmap2.getWidth(),bitmap2.getHeight()));
                    f.setMargins((bitmap.getWidth() - bitmap2.getWidth())/2,10,(bitmap.getWidth() - bitmap2.getWidth())/2,0);
                    sampleAchievements.setLayoutParams(f);
                    listForAChivments.addView(sampleAchievements);
                }
                if (achievementsTable.getId().equals("FORT")) {
                    Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.mipmap.fort17);
                    SampleAchievements sampleAchievements = new SampleAchievements(getApplicationContext(), R.mipmap.fort17);
                    GridLayout.LayoutParams f = new GridLayout.LayoutParams(new ViewGroup.MarginLayoutParams(bitmap2.getWidth(),bitmap2.getHeight()));
                    f.setMargins((bitmap.getWidth() - bitmap2.getWidth())/2,10,(bitmap.getWidth() - bitmap2.getWidth())/2,0);
                    sampleAchievements.setLayoutParams(f);
                    listForAChivments.addView(sampleAchievements);
                }
                if (achievementsTable.getId().equals("GOLD")) {
                    Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.mipmap.gold14);
                    SampleAchievements sampleAchievements = new SampleAchievements(getApplicationContext(), R.mipmap.gold14);
                    GridLayout.LayoutParams f = new GridLayout.LayoutParams(new ViewGroup.MarginLayoutParams(bitmap2.getWidth(),bitmap2.getHeight()));
                    f.setMargins((bitmap.getWidth() - bitmap2.getWidth())/2,10,(bitmap.getWidth() - bitmap2.getWidth())/2,0);
                    sampleAchievements.setLayoutParams(f);
                    listForAChivments.addView(sampleAchievements);
                }
                if (achievementsTable.getId().equals("HOE")) {
                    Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.mipmap.hoe26);
                    SampleAchievements sampleAchievements = new SampleAchievements(getApplicationContext(), R.mipmap.hoe26);
                    GridLayout.LayoutParams f = new GridLayout.LayoutParams(new ViewGroup.MarginLayoutParams(bitmap2.getWidth(),bitmap2.getHeight()));
                    f.setMargins((bitmap.getWidth() - bitmap2.getWidth())/2,10,(bitmap.getWidth() - bitmap2.getWidth())/2,0);
                    sampleAchievements.setLayoutParams(f);
                    listForAChivments.addView(sampleAchievements);
                }
                if (achievementsTable.getId().equals("HORSE")) {
                    Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.mipmap.horse22);
                    SampleAchievements sampleAchievements = new SampleAchievements(getApplicationContext(), R.mipmap.horse22);
                    GridLayout.LayoutParams f = new GridLayout.LayoutParams(new ViewGroup.MarginLayoutParams(bitmap2.getWidth(),bitmap2.getHeight()));
                    f.setMargins((bitmap.getWidth() - bitmap2.getWidth())/2,10,(bitmap.getWidth() - bitmap2.getWidth())/2,0);
                    sampleAchievements.setLayoutParams(f);
                    listForAChivments.addView(sampleAchievements);
                }
                if (achievementsTable.getId().equals("DIAMOND_VILLAGER")) {
                    Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.mipmap.juwelir25);
                    SampleAchievements sampleAchievements = new SampleAchievements(getApplicationContext(), R.mipmap.juwelir25);
                    GridLayout.LayoutParams f = new GridLayout.LayoutParams(new ViewGroup.MarginLayoutParams(bitmap2.getWidth(),bitmap2.getHeight()));
                    f.setMargins((bitmap.getWidth() - bitmap2.getWidth())/2,10,(bitmap.getWidth() - bitmap2.getWidth())/2,0);
                    sampleAchievements.setLayoutParams(f);
                    listForAChivments.addView(sampleAchievements);
                }
                if (achievementsTable.getId().equals("KING")) {
                    Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.mipmap.king24);
                    SampleAchievements sampleAchievements = new SampleAchievements(getApplicationContext(), R.mipmap.king24);
                    GridLayout.LayoutParams f = new GridLayout.LayoutParams(new ViewGroup.MarginLayoutParams(bitmap2.getWidth(),bitmap2.getHeight()));
                    f.setMargins((bitmap.getWidth() - bitmap2.getWidth())/2,10,(bitmap.getWidth() - bitmap2.getWidth())/2,0);
                    sampleAchievements.setLayoutParams(f);
                    listForAChivments.addView(sampleAchievements);
                }
                if (achievementsTable.getId().equals("LEATHER")) {
                    Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.mipmap.leather09);
                    SampleAchievements sampleAchievements = new SampleAchievements(getApplicationContext(), R.mipmap.leather09);
                    GridLayout.LayoutParams f = new GridLayout.LayoutParams(new ViewGroup.MarginLayoutParams(bitmap2.getWidth(),bitmap2.getHeight()));
                    f.setMargins((bitmap.getWidth() - bitmap2.getWidth())/2,10,(bitmap.getWidth() - bitmap2.getWidth())/2,0);
                    sampleAchievements.setLayoutParams(f);
                    listForAChivments.addView(sampleAchievements);
                }
                if (achievementsTable.getId().equals("MAYOR")) {
                    Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.mipmap.mer13);
                    SampleAchievements sampleAchievements = new SampleAchievements(getApplicationContext(), R.mipmap.mer13);
                    GridLayout.LayoutParams f = new GridLayout.LayoutParams(new ViewGroup.MarginLayoutParams(bitmap2.getWidth(),bitmap2.getHeight()));
                    f.setMargins((bitmap.getWidth() - bitmap2.getWidth())/2,10,(bitmap.getWidth() - bitmap2.getWidth())/2,0);
                    sampleAchievements.setLayoutParams(f);
                    listForAChivments.addView(sampleAchievements);
                }
                if (achievementsTable.getId().equals("SAW")) {
                    Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.mipmap.saw08);
                    SampleAchievements sampleAchievements = new SampleAchievements(getApplicationContext(), R.mipmap.saw08);
                    GridLayout.LayoutParams f = new GridLayout.LayoutParams(new ViewGroup.MarginLayoutParams(bitmap2.getWidth(),bitmap2.getHeight()));
                    f.setMargins((bitmap.getWidth() - bitmap2.getWidth())/2,10,(bitmap.getWidth() - bitmap2.getWidth())/2,0);
                    sampleAchievements.setLayoutParams(f);
                    listForAChivments.addView(sampleAchievements);
                }
                if (achievementsTable.getId().equals("SHMOT")) {
                    Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.mipmap.shmot16);
                    SampleAchievements sampleAchievements = new SampleAchievements(getApplicationContext(), R.mipmap.shmot16);
                    GridLayout.LayoutParams f = new GridLayout.LayoutParams(new ViewGroup.MarginLayoutParams(bitmap2.getWidth(),bitmap2.getHeight()));
                    f.setMargins((bitmap.getWidth() - bitmap2.getWidth())/2,10,(bitmap.getWidth() - bitmap2.getWidth())/2,0);
                    sampleAchievements.setLayoutParams(f);
                    listForAChivments.addView(sampleAchievements);
                }
                if (achievementsTable.getId().equals("SLIME")) {
                    Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.mipmap.slime21);
                    SampleAchievements sampleAchievements = new SampleAchievements(getApplicationContext(), R.mipmap.slime21);
                    GridLayout.LayoutParams f = new GridLayout.LayoutParams(new ViewGroup.MarginLayoutParams(bitmap2.getWidth(),bitmap2.getHeight()));
                    f.setMargins((bitmap.getWidth() - bitmap2.getWidth())/2,10,(bitmap.getWidth() - bitmap2.getWidth())/2,0);
                    sampleAchievements.setLayoutParams(f);
                    listForAChivments.addView(sampleAchievements);
                }
                if (achievementsTable.getId().equals("EDU_VILL")) {
                    Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.mipmap.smart07);
                    SampleAchievements sampleAchievements = new SampleAchievements(getApplicationContext(), R.mipmap.smart07);
                    GridLayout.LayoutParams f = new GridLayout.LayoutParams(new ViewGroup.MarginLayoutParams(bitmap2.getWidth(),bitmap2.getHeight()));
                    f.setMargins((bitmap.getWidth() - bitmap2.getWidth())/2,10,(bitmap.getWidth() - bitmap2.getWidth())/2,0);
                    sampleAchievements.setLayoutParams(f);
                    listForAChivments.addView(sampleAchievements);
                }
                if (achievementsTable.getId().equals("SOLDAT")) {
                    Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.mipmap.soldat10);
                    SampleAchievements sampleAchievements = new SampleAchievements(getApplicationContext(), R.mipmap.soldat10);
                    GridLayout.LayoutParams f = new GridLayout.LayoutParams(new ViewGroup.MarginLayoutParams(bitmap2.getWidth(),bitmap2.getHeight()));
                    f.setMargins((bitmap.getWidth() - bitmap2.getWidth())/2,10,(bitmap.getWidth() - bitmap2.getWidth())/2,0);
                    sampleAchievements.setLayoutParams(f);
                    listForAChivments.addView(sampleAchievements);
                }
                if (achievementsTable.getId().equals("STROPILO")) {
                    Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.mipmap.stropilo19);
                    SampleAchievements sampleAchievements = new SampleAchievements(getApplicationContext(), R.mipmap.stropilo19);
                    GridLayout.LayoutParams f = new GridLayout.LayoutParams(new ViewGroup.MarginLayoutParams(bitmap2.getWidth(),bitmap2.getHeight()));
                    f.setMargins((bitmap.getWidth() - bitmap2.getWidth())/2,10,(bitmap.getWidth() - bitmap2.getWidth())/2,0);
                    sampleAchievements.setLayoutParams(f);
                    listForAChivments.addView(sampleAchievements);
                }
                if (achievementsTable.getId().equals("TOWER_T")) {
                    Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.mipmap.tower27);
                    SampleAchievements sampleAchievements = new SampleAchievements(getApplicationContext(), R.mipmap.tower27);
                    GridLayout.LayoutParams f = new GridLayout.LayoutParams(new ViewGroup.MarginLayoutParams(bitmap2.getWidth(),bitmap2.getHeight()));
                    f.setMargins((bitmap.getWidth() - bitmap2.getWidth())/2,10,(bitmap.getWidth() - bitmap2.getWidth())/2,0);
                    sampleAchievements.setLayoutParams(f);
                    listForAChivments.addView(sampleAchievements);
                }
                if (achievementsTable.getId().equals("WHEAT_BLOCK")) {
                    Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.mipmap.wheat18);
                    SampleAchievements sampleAchievements = new SampleAchievements(getApplicationContext(), R.mipmap.wheat18);
                    GridLayout.LayoutParams f = new GridLayout.LayoutParams(new ViewGroup.MarginLayoutParams(bitmap2.getWidth(),bitmap2.getHeight()));
                    f.setMargins((bitmap.getWidth() - bitmap2.getWidth())/2,10,(bitmap.getWidth() - bitmap2.getWidth())/2,0);
                    sampleAchievements.setLayoutParams(f);
                    listForAChivments.addView(sampleAchievements);
                }

            }
        }
    }
}
