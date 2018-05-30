package com.nerkingames.mineclicker.Game.GameFragments.JobFragment;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.GridLayout;

import com.nerkingames.mineclicker.Game.GameActivity.GameActivity;
import com.nerkingames.mineclicker.Views.Buttons.UpWorldButtons.IViewGetter;
import com.nerkingames.mineclicker.Views.JobControllers.*;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * Created by vladyslav on 14.03.18.
 */

public class ObjectInitializer  {

    private IronSword ironSword;
    private IronPickaxe ironPickaxe;
    private Leather leather;
    private FoodWorker foodVillager;

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    private Handler handler;
    private  static ObjectInitializer objectInitializer;
    private GridLayout gridLayout;
    private Context context;
    private WoodButton woodButton;
    private PlankButton plankButton;
    private Stick stick;
    private WoodPickaxe woodPickaxe;
    private WoodShovel woodShovel;
    private WoodSword woodSword;
    private WoodAxe woodAxe;
    private WoodHoe woodHoe;
    private Cobbl cobbl;
    private Crafttable crafttable;
    private StoneShovel stoneShovel;
    private StoneSword stoneSword;
    private StoneAxe stoneAxe;
    private StonePickaxe stonePickaxe;
    private StoneHoe stoneHoe;
    private Furnece furnece;
    private BucketEmpty bucket;
    private Coal coal;
    private IronOre ironOre;
    private IronIngot ironIngot;
    private Dirt dirt;
    private DirtPodzol dirtPodzol;
    private Water water;
    private Ferma ferma;
    private WheatSeeds wheatSeeds;
    private FermaWithGrass fermaWithGrass;
    private Door door;
    private Wall wall;
    private Storogka storogka;
    private VillagerSimple villager;
    private UpWorldWorker upWorldVillager;
    private Bambuk bambuk;
    private Wheat wheat;
    private Flint flint;
    private GoldOre goldOre;
    private Diamond diamond;
    private Lava lava;
    private MineWorker mineVillager;
    private Arrow arrow;
    private Slime slim;
    private Sttring sttring;
    private Father father;
    private Wool wool;
    private Horse horse;
    private AggressiveMobsWorker aggressiveMobsWorker;
    private GoodMobsWorker goodMobsWorker;
    private IronShovel ironShovel;
    private IronAxe ironAxe;
    private IronHoe ironHoe;
    private GoldIngot goldIngot;
    private GoldSword goldSword;
    private GoldPickaxe goldPickaxe;
    private GoldShovel goldShovel;
    private GoldAxe goldAxe;
    private GoldHoe goldHoe;
    private DiamondSword diamondSword;
    private DiamondPickaxe diamondPickaxe;
    private DiamondShovel diamondShovel;
    private DiamondAxe diamondAxe;
    private DiamondHoe diamondHoe;
    private ZaborUpdate zaborUpdate;
    private Zabor zabor;
    private Stairs stairs;
    private HalfRoof halfRoof;
    private TwoTimeHalfRoof twoTimeHalfRoof;
    private Roof roof;
    private Home home;
    private BambukFerma bambukFerma;
    private Paper paper;
    private Book book;
    private Bookshelf bookshelf;
    private BookshelfUpdate bookshelfUpdate;
    private Library library;
    private EducatedVillager educatedVillager;
    private VillagerStroitel villagerStroitel;
    private LavaPool lavaPool;
    private Kuznica kuznica;
    private VillagerAutosmelt villagerAutosmelt;
    private Pitchwork pitchwork;
    private VillagerFermer villagerFermer;
    private Saw saw;
    private VillagerSaw villagerSaw;
    private Parta parta;
    private VillagerMayor villagerMayor;
    private FermaAuto fermaAuto;
    private StorogkaAuto storogkaAuto;
    private VillagerLeather villagerLeather;
    private LeatherHelmet leatherHelmet;
    private LeatherChestplate leatherChestplate;
    private LeatherLegins leatherLegins;
    private LeatherBoots leatherBoots;
    private LeatherArmor leatherArmor;
    private VillagerSegant villagerSegant;
    private VillagerSoldat villagerSoldat;
    private VillagerLetherSoldat villagerLetherSoldat;
    private StorogkaUpdate storogkaUpdate;
    private IronBlock ironBlock;
    private Anvil anvil;
    private VillagerAutosmeltUpdte villagerAutosmeltUpdte;
    private IronHelmet ironHelmet;
    private IronChestplate ironChestplate;
    private IronLegins ironLegins;
    private IronBoots ironBoots;
    private IronArmor ironArmor;
    private VillagerIronSoldat villagerIronSoldat;
    private VillagerArowmaker villagerArowmaker;
    private VillagerBow villagerBow;
    private VillagerFireTeam villagerFireTeam;
    private Ladder ladder;
    private Tower tower;
    private TowerAttak towerAttak;
    private TowerUpdate towerUpdate;
    private TowerBest towerBest;
    private TowerBestDownload towerBestDownload;
    private VillagerPrezident villagerPrezident;
    private VillagerWorkerGold villagerWorkerGold;
    private VillagerWorker villagerWorker;
    private VvillagerUpgrageSergant villagerUpgrageSergant;
    private VillagerSimpleAuto villagerSimpleAuto;
    private VillagerSoldatAuto villagerSoldatAuto;
    private GoldHelmet goldHelmet;
    private GoldChestplate goldChestplate;
    private GoldLegins goldLegins;
    private GoldBoots goldBoots;
    private GoldArmor goldArmor;
    private VillagerGold villagerGold;
    private WallTower wallTower;
    private WallTowerUpgrade wallTowerUpgrade;
    private Fort fort;
    private FortDownload fortDownload;
    private WheatBlock wheatBlock;
    private ZaborCopy zaborCopy;
    private ZaborUpdateCopy zaborUpdateCopy;
    private TwoTimeHalfRoofCopy twoTimeHalfRoofCopy;
    private RoofCopy roofCopy;
    private Stoilo stoilo;
    private Staini staini;
    private Lead lead;
    private VillagerHorseTrener villagerHorseTrener;
    private Saddle saddle;
    private TamedHorse tamedHorse;
    private HomeCopy homeCopy;
    private DoubleHouse doubleHouse;
    private Castle castle;
    private DiamonSipitr diamonSipitr;
    private VillagerKing villagerKing;
    private VillagerWorkDiamond villagerWorkDiamond;
    private DiamondArmor diamondArmor;
    private VillagerDiamond villagerDiamond;
    private Diamondblock diamondblock;
    private DiamondHorse diamondHorse;
    private WheahPool wheahPool;
    private TowerThree towerThree;
    private TowerWall towerWall;
    private CastleBest castleBest;
    private CastleBestDownload castleBestDownload;
    private Bow bow;

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    private ViewPager viewPager;

    public void setUpWorld(IViewGetter upWorld) {
        this.upWorld = upWorld;
    }

    public void setMine(IViewGetter mine) {
        this.mine = mine;
    }

    public void setFood(IViewGetter food) {
        this.food = food;
    }

    public void setMobs(IViewGetter mobs) {
        this.mobs = mobs;
    }

    private IViewGetter upWorld;
    private IViewGetter mine;
    private IViewGetter food;
    private IViewGetter mobs;










    public ObjectInitializer(Context context, GridLayout gridLayout, ViewPager viewPager){
        this.context = context;
        this.gridLayout = gridLayout;

        woodButton = WoodButton.getInstance(context);
        woodPickaxe = WoodPickaxe.getInstance(context);
        woodShovel = WoodShovel.getInstance(context);
        woodSword = WoodSword.getInstance(context);
        woodAxe = WoodAxe.getInstance(context);
        woodHoe = WoodHoe.getInstance(context);
        cobbl = Cobbl.getInstance(context);
        stoneShovel = StoneShovel.getInstance(context);
        stoneSword = StoneSword.getInstance(context);
        stoneAxe = StoneAxe.getInstance(context);
        stonePickaxe = StonePickaxe.getInstance(context);
        stoneHoe = StoneHoe.getInstance(context);
        furnece = Furnece.getInstance(context);
        bucket = BucketEmpty.getInstance(context);
        ironOre = IronOre.getInstance(context);
        coal = Coal.getInstance(context);
        ironIngot = IronIngot.getInstance(context);
        dirt = Dirt.getInstance(context);
        dirtPodzol = DirtPodzol.getInstance(context);
        water = Water.getInstance(context);
        ferma = Ferma.getInstance(context);
        wheatSeeds = WheatSeeds.getInstance(context);
        fermaWithGrass = FermaWithGrass.getInstance(context);
        plankButton = PlankButton.getInstance(context);
        crafttable = Crafttable.getInstance(context);
        stick = Stick.getInstance(context);
        door = Door.getInstance(context);
        wall = Wall.getInstance(context);
        storogka = Storogka.getInstance(context);
        villager = VillagerSimple.getInstance(context);
        /*31*/upWorldVillager = UpWorldWorker.getInstance(context);
        /*32*/mineVillager = MineWorker.getInstance(context);
        /*33*/foodVillager = FoodWorker.getInstance(context);
        /*34*/aggressiveMobsWorker = AggressiveMobsWorker.getInstance(context);
        /*35*/zaborUpdate = ZaborUpdate.getInstance(context);
        /*36*/zabor = Zabor.getInstance(context);
        /*37*/goodMobsWorker = GoodMobsWorker.getInstance(context);
        /*38*/stairs = Stairs.getInstance(context);
        /*39*/halfRoof = HalfRoof.getInstance(context);
        /*40*/twoTimeHalfRoof = TwoTimeHalfRoof.getInstance(context);
        /*41*/roof = Roof.getInstance(context);
        /*42*/home = Home.getInstance(context);
        /*43*/bambuk = Bambuk.getInstance(context);
        /*44*/bambukFerma = BambukFerma.getInstance(context);
        /*45*/paper = Paper.getInstance(context);
        /*46*/book = Book.getInstance(context);
        /*47*/bookshelf = Bookshelf.getInstance(context);
        /*48*/bookshelfUpdate = BookshelfUpdate.getInstance(context);
        /*49*/library = Library.getInstance(context);
        /*50*/educatedVillager = EducatedVillager.getInstance(context);
        /*51*/villagerStroitel = VillagerStroitel.getInstance(context);
        /*52*/lava = Lava.getInstance(context);
        /*53*/
          lavaPool = LavaPool.getInstance(context);
          kuznica = Kuznica.getInstance(context);
          villagerAutosmelt = VillagerAutosmelt.getInstance(context);
          pitchwork = Pitchwork.getInstance(context);
          villagerFermer = VillagerFermer.getInstance(context);
          saw = Saw.getInstance(context);
          villagerSaw = VillagerSaw.getInstance(context);
          parta = Parta.getInstance(context);
          villagerMayor = VillagerMayor.getInstance(context);
          fermaAuto = FermaAuto.getInstance(context);
          storogkaAuto = StorogkaAuto.getInstance(context);
        /*63*/
        /*64*/ironPickaxe = IronPickaxe.getInstance(context);
        /*65*/ironShovel = IronShovel.getInstance(context);
        /*66*/ironSword = IronSword.getInstance(context);
        /*67*/ironAxe = IronAxe.getInstance(context);
        /*68*/ironHoe = IronHoe.getInstance(context);
        villagerLeather = VillagerLeather.getInstance(context);
        /*70*/leather = Leather.getInstance(context);
          leatherHelmet = LeatherHelmet.getInstance(context);
          leatherChestplate = LeatherChestplate.getInstance(context);
          leatherLegins = LeatherLegins.getInstance(context);
          leatherBoots = LeatherBoots.getInstance(context);
          leatherArmor = LeatherArmor.getInstance(context);
          villagerSegant = VillagerSegant.getInstance(context);
          villagerSoldat = VillagerSoldat.getInstance(context);
          villagerLetherSoldat = VillagerLetherSoldat.getInstance(context);
          storogkaUpdate = StorogkaUpdate.getInstance(context);
          ironBlock = IronBlock.getInstance(context);
          anvil = Anvil.getInstance(context);
          villagerAutosmeltUpdte = VillagerAutosmeltUpdte.getInstance(context);
          ironHelmet = IronHelmet.getInstance(context);
          ironChestplate = IronChestplate.getInstance(context);
          ironLegins = IronLegins.getInstance(context);
          ironBoots = IronBoots.getInstance(context);
          ironArmor = IronArmor.getInstance(context);
          villagerIronSoldat = VillagerIronSoldat.getInstance(context);
          villagerArowmaker = VillagerArowmaker.getInstance(context);
        /*90*/wool = Wool.getInstance(context);
        /*91*/sttring = Sttring.getInstance(context);
          bow = Bow.getInstance(context);
        /*93*/father = Father.getInstance(context);
        /*94*/flint = Flint.getInstance(context);
        /*95*/arrow = Arrow.getInstance(context);
          villagerBow = VillagerBow.getInstance(context);
          villagerFireTeam = VillagerFireTeam.getInstance(context);
          ladder = Ladder.getInstance(context);
          tower = Tower.getInstance(context);
          towerAttak = TowerAttak.getInstance(context);
          towerUpdate = TowerUpdate.getInstance(context);
          towerBest = TowerBest.getInstance(context);
          towerBestDownload = TowerBestDownload.getInstance(context);
          villagerPrezident = VillagerPrezident.getInstance(context);
        /*105*/goldOre = GoldOre.getInstance(context);
        /*106*/goldIngot = GoldIngot.getInstance(context);
        /*107*/goldPickaxe = GoldPickaxe.getInstance(context);
        /*108*/goldShovel = GoldShovel.getInstance(context);
        /*109*/goldSword = GoldSword.getInstance(context);
        /*110*/goldAxe = GoldAxe.getInstance(context);
        /*111*/goldHoe = GoldHoe.getInstance(context);
          villagerWorkerGold = VillagerWorkerGold.getInstance(context);
          villagerWorker = VillagerWorker.getInstance(context);
          villagerUpgrageSergant = VvillagerUpgrageSergant.getInstance(context);
          villagerSimpleAuto = VillagerSimpleAuto.getInstance(context);
          villagerSoldatAuto = VillagerSoldatAuto.getInstance(context);
          goldHelmet = GoldHelmet.getInstance(context);
          goldChestplate = GoldChestplate.getInstance(context);
          goldLegins = GoldLegins.getInstance(context);
          goldBoots = GoldBoots.getInstance(context);
          goldArmor = GoldArmor.getInstance(context);
          villagerGold = VillagerGold.getInstance(context);
          wallTower = WallTower.getInstance(context);
          wallTowerUpgrade = WallTowerUpgrade.getInstance(context);
          fort = Fort.getInstance(context);
          fortDownload = FortDownload.getInstance(context);
        /*127*/wheat = Wheat.getInstance(context);
          wheatBlock = WheatBlock.getInstance(context);
          zaborCopy = ZaborCopy.getInstance(context);
          zaborUpdateCopy = ZaborUpdateCopy.getInstance(context);
          twoTimeHalfRoofCopy = TwoTimeHalfRoofCopy.getInstance(context);
          roofCopy = RoofCopy.getInstance(context);
          stoilo = Stoilo.getInstance(context);
          staini = Staini.getInstance(context);
        /*135*/slim = Slime.getInstance(context);
          lead = Lead.getInstance(context);
          villagerHorseTrener = VillagerHorseTrener.getInstance(context);
          saddle = Saddle.getInstance(context);
        /*139*/horse = Horse.getInstance(context);
          tamedHorse = TamedHorse.getInstance(context);
          homeCopy = HomeCopy.getInstance(context);
          doubleHouse = DoubleHouse.getInstance(context);
          castle = Castle.getInstance(context);
          diamonSipitr = DiamonSipitr.getInstance(context);
          villagerKing = VillagerKing.getInstance(context);
          villagerWorkDiamond = VillagerWorkDiamond.getInstance(context);
        /*147*/diamond = Diamond.getInstance(context);
          diamondArmor = DiamondArmor.getInstance(context);
          villagerDiamond = VillagerDiamond.getInstance(context);
          diamondblock = Diamondblock.getInstance(context);
        /*151*/diamondPickaxe = DiamondPickaxe.getInstance(context);
        /*152*/diamondShovel = DiamondShovel.getInstance(context);
        /*153*/diamondSword = DiamondSword.getInstance(context);
        /*154*/diamondAxe = DiamondAxe.getInstance(context);
        /*155*/diamondHoe = DiamondHoe.getInstance(context);
          diamondHorse = DiamondHorse.getInstance(context);
          wheahPool = WheahPool.getInstance(context);
          towerThree = TowerThree.getInstance(context);
          towerWall = TowerWall.getInstance(context);
          castleBest = CastleBest.getInstance(context);
          castleBestDownload = CastleBestDownload.getInstance(context);



    }

    public void createView(){
        if (GameActivity.CHECK_VAR == 0){
            GameActivity.CHECK_VAR = 1;
            firstInit(gridLayout);
        } else {
            postInit(gridLayout,context);
        }
    }

    private void postInit(GridLayout gridLayout, Context context) {
        Log.d(TAG, "postInit: in metod");
        GameActivity.onStop = false;

        woodButton = WoodButton.getInstance(context);
        woodPickaxe = WoodPickaxe.getInstance(context);
        woodShovel = WoodShovel.getInstance(context);
        woodSword = WoodSword.getInstance(context);
        woodAxe = WoodAxe.getInstance(context);
        woodHoe = WoodHoe.getInstance(context);
        stoneShovel = StoneShovel.getInstance(context);
        stoneSword = StoneSword.getInstance(context);
        stoneAxe = StoneAxe.getInstance(context);
        stonePickaxe = StonePickaxe.getInstance(context);
        stoneHoe = StoneHoe.getInstance(context);
        furnece = Furnece.getInstance(context);
        cobbl = Cobbl.getInstance(context);
        bucket = BucketEmpty.getInstance(context);
        ironOre = IronOre.getInstance(context);
        coal = Coal.getInstance(context);
        ironIngot = IronIngot.getInstance(context);
        dirt = Dirt.getInstance(context);
        dirtPodzol = DirtPodzol.getInstance(context);
        water = Water.getInstance(context);
        ferma = Ferma.getInstance(context);
        wheatSeeds = WheatSeeds.getInstance(context);
        fermaWithGrass = FermaWithGrass.getInstance(context);
        plankButton = PlankButton.getInstance(context);
        crafttable = Crafttable.getInstance(context);
        stick = Stick.getInstance(context);
        door = Door.getInstance(context);
        wall = Wall.getInstance(context);
        storogka = Storogka.getInstance(context);
        villager = VillagerSimple.getInstance(context);
        /*31*/upWorldVillager = UpWorldWorker.getInstance(context);
        /*32*/mineVillager = MineWorker.getInstance(context);
        /*33*/foodVillager = FoodWorker.getInstance(context);
        /*34*/aggressiveMobsWorker = AggressiveMobsWorker.getInstance(context);
        /*35*/zaborUpdate = ZaborUpdate.getInstance(context);
        /*36*/zabor = Zabor.getInstance(context);
        /*37*/goodMobsWorker = GoodMobsWorker.getInstance(context);
        /*38*/stairs = Stairs.getInstance(context);
        /*39*/halfRoof = HalfRoof.getInstance(context);
        /*40*/twoTimeHalfRoof = TwoTimeHalfRoof.getInstance(context);
        /*41*/roof = Roof.getInstance(context);
        /*42*/home = Home.getInstance(context);
        /*43*/bambuk = Bambuk.getInstance(context);
        /*44*/bambukFerma = BambukFerma.getInstance(context);
        /*45*/paper = Paper.getInstance(context);
        /*46*/book = Book.getInstance(context);
        /*47*/bookshelf = Bookshelf.getInstance(context);
        /*48*/bookshelfUpdate = BookshelfUpdate.getInstance(context);
        /*49*/library = Library.getInstance(context);
        /*50*/educatedVillager = EducatedVillager.getInstance(context);
        /*51*/villagerStroitel = VillagerStroitel.getInstance(context);
        /*52*/lava = Lava.getInstance(context);
        /*53*/
        lavaPool = LavaPool.getInstance(context);
        kuznica = Kuznica.getInstance(context);
        villagerAutosmelt = VillagerAutosmelt.getInstance(context);
        pitchwork = Pitchwork.getInstance(context);
        villagerFermer = VillagerFermer.getInstance(context);
        saw = Saw.getInstance(context);
        villagerSaw = VillagerSaw.getInstance(context);
        parta = Parta.getInstance(context);
        villagerMayor = VillagerMayor.getInstance(context);
        fermaAuto = FermaAuto.getInstance(context);
        storogkaAuto = StorogkaAuto.getInstance(context);
        /*63*/
        /*64*/ironPickaxe = IronPickaxe.getInstance(context);
        /*65*/ironShovel = IronShovel.getInstance(context);
        /*66*/ironSword = IronSword.getInstance(context);
        /*67*/ironAxe = IronAxe.getInstance(context);
        /*68*/ironHoe = IronHoe.getInstance(context);
        villagerLeather = VillagerLeather.getInstance(context);
        /*70*/leather = Leather.getInstance(context);
        leatherHelmet = LeatherHelmet.getInstance(context);
        leatherChestplate = LeatherChestplate.getInstance(context);
        leatherLegins = LeatherLegins.getInstance(context);
        leatherBoots = LeatherBoots.getInstance(context);
        leatherArmor = LeatherArmor.getInstance(context);
        villagerSegant = VillagerSegant.getInstance(context);
        villagerSoldat = VillagerSoldat.getInstance(context);
        villagerLetherSoldat = VillagerLetherSoldat.getInstance(context);
        storogkaUpdate = StorogkaUpdate.getInstance(context);
        ironBlock = IronBlock.getInstance(context);
        anvil = Anvil.getInstance(context);
        villagerAutosmeltUpdte = VillagerAutosmeltUpdte.getInstance(context);
        ironHelmet = IronHelmet.getInstance(context);
        ironChestplate = IronChestplate.getInstance(context);
        ironLegins = IronLegins.getInstance(context);
        ironBoots = IronBoots.getInstance(context);
        ironArmor = IronArmor.getInstance(context);
        villagerIronSoldat = VillagerIronSoldat.getInstance(context);
        villagerArowmaker = VillagerArowmaker.getInstance(context);
        /*90*/wool = Wool.getInstance(context);
        /*91*/sttring = Sttring.getInstance(context);
        bow = Bow.getInstance(context);
        /*93*/father = Father.getInstance(context);
        /*94*/flint = Flint.getInstance(context);
        /*95*/arrow = Arrow.getInstance(context);
        villagerBow = VillagerBow.getInstance(context);
        villagerFireTeam = VillagerFireTeam.getInstance(context);
        ladder = Ladder.getInstance(context);
        tower = Tower.getInstance(context);
        towerAttak = TowerAttak.getInstance(context);
        towerUpdate = TowerUpdate.getInstance(context);
        towerBest = TowerBest.getInstance(context);
        towerBestDownload = TowerBestDownload.getInstance(context);
        villagerPrezident = VillagerPrezident.getInstance(context);
        /*105*/goldOre = GoldOre.getInstance(context);
        /*106*/goldIngot = GoldIngot.getInstance(context);
        /*107*/goldPickaxe = GoldPickaxe.getInstance(context);
        /*108*/goldShovel = GoldShovel.getInstance(context);
        /*109*/goldSword = GoldSword.getInstance(context);
        /*110*/goldAxe = GoldAxe.getInstance(context);
        /*111*/goldHoe = GoldHoe.getInstance(context);
        villagerWorkerGold = VillagerWorkerGold.getInstance(context);
        villagerWorker = VillagerWorker.getInstance(context);
        villagerUpgrageSergant = VvillagerUpgrageSergant.getInstance(context);
        villagerSimpleAuto = VillagerSimpleAuto.getInstance(context);
        villagerSoldatAuto = VillagerSoldatAuto.getInstance(context);
        goldHelmet = GoldHelmet.getInstance(context);
        goldChestplate = GoldChestplate.getInstance(context);
        goldLegins = GoldLegins.getInstance(context);
        goldBoots = GoldBoots.getInstance(context);
        goldArmor = GoldArmor.getInstance(context);
        villagerGold = VillagerGold.getInstance(context);
        wallTower = WallTower.getInstance(context);
        wallTowerUpgrade = WallTowerUpgrade.getInstance(context);
        fort = Fort.getInstance(context);
        fortDownload = FortDownload.getInstance(context);
        /*127*/wheat = Wheat.getInstance(context);
        wheatBlock = WheatBlock.getInstance(context);
        zaborCopy = ZaborCopy.getInstance(context);
        zaborUpdateCopy = ZaborUpdateCopy.getInstance(context);
        twoTimeHalfRoofCopy = TwoTimeHalfRoofCopy.getInstance(context);
        roofCopy = RoofCopy.getInstance(context);
        stoilo = Stoilo.getInstance(context);
        staini = Staini.getInstance(context);
        /*135*/slim = Slime.getInstance(context);
        lead = Lead.getInstance(context);
        villagerHorseTrener = VillagerHorseTrener.getInstance(context);
        saddle = Saddle.getInstance(context);
        /*139*/horse = Horse.getInstance(context);
        tamedHorse = TamedHorse.getInstance(context);
        homeCopy = HomeCopy.getInstance(context);
        doubleHouse = DoubleHouse.getInstance(context);
        castle = Castle.getInstance(context);
        diamonSipitr = DiamonSipitr.getInstance(context);
        villagerKing = VillagerKing.getInstance(context);
        villagerWorkDiamond = VillagerWorkDiamond.getInstance(context);
        /*147*/diamond = Diamond.getInstance(context);
        diamondArmor = DiamondArmor.getInstance(context);
        villagerDiamond = VillagerDiamond.getInstance(context);
        diamondblock = Diamondblock.getInstance(context);
        /*151*/diamondPickaxe = DiamondPickaxe.getInstance(context);
        /*152*/diamondShovel = DiamondShovel.getInstance(context);
        /*153*/diamondSword = DiamondSword.getInstance(context);
        /*154*/diamondAxe = DiamondAxe.getInstance(context);
        /*155*/diamondHoe = DiamondHoe.getInstance(context);
        diamondHorse = DiamondHorse.getInstance(context);
        wheahPool = WheahPool.getInstance(context);
        towerThree = TowerThree.getInstance(context);
        towerWall = TowerWall.getInstance(context);
        castleBest = CastleBest.getInstance(context);
        castleBestDownload = CastleBestDownload.getInstance(context);


        handler.post(() -> {

                if(gridLayout.getChildCount() != 0){
                    gridLayout.removeAllViews();
                }
                gridLayout.addView(woodButton.getViewScene());
                gridLayout.addView(woodPickaxe.getViewScene());
                gridLayout.addView(woodShovel.getViewScene());
                gridLayout.addView(woodSword.getViewScene());
                gridLayout.addView(woodAxe.getViewScene());
                gridLayout.addView(woodHoe.getViewScene());
                gridLayout.addView(cobbl.getViewScene());
                gridLayout.addView(stoneShovel.getViewScene());
                gridLayout.addView(stoneSword.getViewScene());
                gridLayout.addView(stoneAxe.getViewScene());
                gridLayout.addView(stonePickaxe.getViewScene());
                gridLayout.addView(stoneHoe.getViewScene());
                gridLayout.addView(furnece.getViewScene());
                gridLayout.addView(bucket.getViewScene());
                gridLayout.addView(ironOre.getViewScene());
                gridLayout.addView(coal.getViewScene());
                gridLayout.addView(ironIngot.getViewScene());
                gridLayout.addView(dirt.getViewScene());
                gridLayout.addView(dirtPodzol.getViewScene());
                gridLayout.addView(water.getViewScene());
                gridLayout.addView(ferma.getViewScene());
                gridLayout.addView(wheatSeeds.getViewScene());
                gridLayout.addView(fermaWithGrass.getViewScene());
                gridLayout.addView(plankButton.getViewScene());
                gridLayout.addView(crafttable.getViewScene());
                gridLayout.addView(stick.getViewScene());
                gridLayout.addView(door.getViewScene());
                gridLayout.addView(wall.getViewScene());
                gridLayout.addView(storogka.getViewScene());
                gridLayout.addView(villager.getViewScene());
            /*31*/
                gridLayout.addView(upWorldVillager.getViewScene());
            /*32*/
                gridLayout.addView(mineVillager.getViewScene());
            /*33*/
                gridLayout.addView(foodVillager.getViewScene());
            /*34*/
                gridLayout.addView(aggressiveMobsWorker.getViewScene());
            /*35*/
                gridLayout.addView(zaborUpdate.getViewScene());
            /*36*/
                gridLayout.addView(zabor.getViewScene());
            /*37*/
                gridLayout.addView(goodMobsWorker.getViewScene());
            /*38*/
                gridLayout.addView(stairs.getViewScene());
            /*39*/
                gridLayout.addView(halfRoof.getViewScene());
            /*40*/
                gridLayout.addView(twoTimeHalfRoof.getViewScene());
            /*41*/
                gridLayout.addView(roof.getViewScene());
            /*42*/
                gridLayout.addView(home.getViewScene());
            /*43*/
                gridLayout.addView(bambuk.getViewScene());
            /*44*/
                gridLayout.addView(bambukFerma.getViewScene());
            /*45*/
                gridLayout.addView(paper.getViewScene());
            /*46*/
                gridLayout.addView(book.getViewScene());
            /*47*/
                gridLayout.addView(bookshelf.getViewScene());
            /*48*/
                gridLayout.addView(bookshelfUpdate.getViewScene());
            /*49*/
                gridLayout.addView(library.getViewScene());
            /*50*/
                gridLayout.addView(educatedVillager.getViewScene());
            /*51*/
                gridLayout.addView(villagerStroitel.getViewScene());
            /*52*/
                gridLayout.addView(lava.getViewScene());

            /*53*/
                gridLayout.addView(lavaPool.getViewScene());
            /*54*/
                gridLayout.addView(kuznica.getViewScene());
            /*55*/
                gridLayout.addView(villagerAutosmelt.getViewScene());
            /*56*/
                gridLayout.addView(pitchwork.getViewScene());
            /*57*/
                gridLayout.addView(villagerFermer.getViewScene());
            /*58*/
                gridLayout.addView(saw.getViewScene());
            /*59*/
                gridLayout.addView(villagerSaw.getViewScene());
            /*60*/
                gridLayout.addView(parta.getViewScene());
            /*61*/
                gridLayout.addView(villagerMayor.getViewScene());
            /*62*/
                gridLayout.addView(fermaAuto.getViewScene());
            /*63*/
                gridLayout.addView(storogkaAuto.getViewScene());

            /*64*/
                gridLayout.addView(ironPickaxe.getViewScene());
            /*65*/
                gridLayout.addView(ironShovel.getViewScene());
            /*66*/
                gridLayout.addView(ironSword.getViewScene());
            /*67*/
                gridLayout.addView(ironAxe.getViewScene());
            /*68*/
                gridLayout.addView(ironHoe.getViewScene());
            /*69*/
                gridLayout.addView(villagerLeather.getViewScene());
            /*71*/
                gridLayout.addView(leather.getViewScene());
            /*72*/
                gridLayout.addView(leatherHelmet.getViewScene());
            /*73*/
                gridLayout.addView(leatherChestplate.getViewScene());
            /*74*/
                gridLayout.addView(leatherLegins.getViewScene());
            /*75*/
                gridLayout.addView(leatherBoots.getViewScene());
            /*76*/
                gridLayout.addView(leatherArmor.getViewScene());
            /*77*/
                gridLayout.addView(villagerSegant.getViewScene());
            /*78*/
                gridLayout.addView(villagerSoldat.getViewScene());
            /*79*/
                gridLayout.addView(villagerLetherSoldat.getViewScene());
            /*80*/
                gridLayout.addView(storogkaUpdate.getViewScene());
            /*81*/
                gridLayout.addView(ironBlock.getViewScene());
            /*82*/
                gridLayout.addView(anvil.getViewScene());
            /*83*/
                gridLayout.addView(villagerAutosmeltUpdte.getViewScene());
            /*84*/
                gridLayout.addView(ironHelmet.getViewScene());
            /*85*/
                gridLayout.addView(ironChestplate.getViewScene());
            /*86*/
                gridLayout.addView(ironLegins.getViewScene());
            /*87*/
                gridLayout.addView(ironBoots.getViewScene());
            /*88*/
                gridLayout.addView(ironArmor.getViewScene());
            /*89*/
                gridLayout.addView(villagerIronSoldat.getViewScene());
            /*90*/
                gridLayout.addView(villagerArowmaker.getViewScene());
            /*91*/
                gridLayout.addView(wool.getViewScene());
            /*92*/
                gridLayout.addView(sttring.getViewScene());
            /*93*/
                gridLayout.addView(bow.getViewScene());
            /*94*/
                gridLayout.addView(father.getViewScene());
            /*95*/
                gridLayout.addView(flint.getViewScene());
            /*96*/
                gridLayout.addView(arrow.getViewScene());

            /*97*/
                gridLayout.addView(villagerBow.getViewScene());
            /*98*/
                gridLayout.addView(villagerFireTeam.getViewScene());
            /*99*/
                gridLayout.addView(ladder.getViewScene());
            /*99*/
                gridLayout.addView(tower.getViewScene());
            /*101*/
                gridLayout.addView(towerAttak.getViewScene());
            /*102*/
                gridLayout.addView(towerUpdate.getViewScene());
            /*103*/
                gridLayout.addView(towerBest.getViewScene());
            /*104*/
                gridLayout.addView(towerBestDownload.getViewScene());
            /*105*/
                gridLayout.addView(villagerPrezident.getViewScene());

            /*106*/
                gridLayout.addView(goldOre.getViewScene());
            /*107*/
                gridLayout.addView(goldIngot.getViewScene());
            /*108*/
                gridLayout.addView(goldPickaxe.getViewScene());
            /*109*/
                gridLayout.addView(goldShovel.getViewScene());
            /*110*/
                gridLayout.addView(goldSword.getViewScene());
            /*111*/
                gridLayout.addView(goldAxe.getViewScene());
            /*112*/
                gridLayout.addView(goldHoe.getViewScene());

            /*112*/
                gridLayout.addView(villagerWorkerGold.getViewScene());
            /*112*/
                gridLayout.addView(villagerWorker.getViewScene());
            /*112*/
                gridLayout.addView(villagerUpgrageSergant.getViewScene());
            /*112*/
                gridLayout.addView(villagerSimpleAuto.getViewScene());
            /*112*/
                gridLayout.addView(villagerSoldatAuto.getViewScene());
            /*112*/
                gridLayout.addView(goldHelmet.getViewScene());
            /*112*/
                gridLayout.addView(goldChestplate.getViewScene());
            /*112*/
                gridLayout.addView(goldLegins.getViewScene());
            /*112*/
                gridLayout.addView(goldBoots.getViewScene());
            /*112*/
                gridLayout.addView(goldArmor.getViewScene());
            /*112*/
                gridLayout.addView(villagerGold.getViewScene());
            /*112*/
                gridLayout.addView(wallTower.getViewScene());
            /*112*/
                gridLayout.addView(wallTowerUpgrade.getViewScene());
            /*112*/
                gridLayout.addView(fort.getViewScene());
            /*112*/
                gridLayout.addView(fortDownload.getViewScene());

            /*128*/
                gridLayout.addView(wheat.getViewScene());

            /*128*/
                gridLayout.addView(wheatBlock.getViewScene());
            /*128*/
                gridLayout.addView(zaborCopy.getViewScene());
            /*128*/
                gridLayout.addView(zaborUpdateCopy.getViewScene());
            /*128*/
                gridLayout.addView(twoTimeHalfRoofCopy.getViewScene());
            /*128*/
                gridLayout.addView(roofCopy.getViewScene());
            /*128*/
                gridLayout.addView(stoilo.getViewScene());
            /*128*/
                gridLayout.addView(staini.getViewScene());

            /*136*/
                gridLayout.addView(slim.getViewScene());
            /*137*/
                gridLayout.addView(lead.getViewScene());
            /*138*/
                gridLayout.addView(villagerHorseTrener.getViewScene());
            /*139*/
                gridLayout.addView(saddle.getViewScene());
            /*140*/
                gridLayout.addView(horse.getViewScene());
            /*141*/
                gridLayout.addView(tamedHorse.getViewScene());
            /*142*/
                gridLayout.addView(homeCopy.getViewScene());
            /*143*/
                gridLayout.addView(doubleHouse.getViewScene());
            /*144*/
                gridLayout.addView(castle.getViewScene());
            /*148*/
                gridLayout.addView(diamond.getViewScene());
            /*145*/
                gridLayout.addView(diamonSipitr.getViewScene());
            /*146*/
                gridLayout.addView(villagerKing.getViewScene());
            /*147*/
                gridLayout.addView(villagerWorkDiamond.getViewScene());
            /*152*/
                gridLayout.addView(diamondPickaxe.getViewScene());
            /*153*/
                gridLayout.addView(diamondShovel.getViewScene());
            /*154*/
                gridLayout.addView(diamondSword.getViewScene());
            /*155*/
                gridLayout.addView(diamondAxe.getViewScene());
            /*156*/
                gridLayout.addView(diamondHoe.getViewScene());
            /*157*/
                gridLayout.addView(diamondArmor.getViewScene());
            /*150*/
                gridLayout.addView(villagerDiamond.getViewScene());
            /*150*/
                gridLayout.addView(diamondblock.getViewScene());
            /*158*/
                gridLayout.addView(diamondHorse.getViewScene());
            /*159*/
                gridLayout.addView(wheahPool.getViewScene());
            /*160*/
                gridLayout.addView(towerThree.getViewScene());
            /*161*/
                gridLayout.addView(towerWall.getViewScene());
            /*162*/
                gridLayout.addView(castleBest.getViewScene());
            /*163*/
                gridLayout.addView(castleBestDownload.getViewScene());
            /*164*/


            upWorld.setClickabled();
            mine.setClickabled();
            mobs.setClickabled();
            food.setClickabled();

        });

    }

    private void firstInit(GridLayout gridLayout) {
        Log.d(TAG, "firstInit: in metod");

        handler.post(() -> {


    /*1WOOD_BUTTON*/
                gridLayout.addView(woodButton.getViewScene(10, 10
                        , plankButton, storogkaAuto, villagerWorkerGold, villagerWorker
                        , villagerUpgrageSergant, villagerSimpleAuto, villagerSoldatAuto
                        , zaborCopy, twoTimeHalfRoofCopy, villagerHorseTrener
                    /**/
                        , plankButton, storogkaAuto, villagerWorkerGold, villagerWorker
                        , villagerUpgrageSergant, villagerSimpleAuto, villagerSoldatAuto
                        , zaborCopy, twoTimeHalfRoofCopy, villagerHorseTrener));
    /*2WOOD_PICKAXE*/
                gridLayout.addView(woodPickaxe.getViewScene(3, 3
                        , crafttable, plankButton, stick
                        , crafttable, plankButton, stick));
    /*3WOOD_SHOVEL*/
                gridLayout.addView(woodShovel.getViewScene(3, 3
                        , crafttable, plankButton, stick
                        , crafttable, plankButton, stick));
    /*4WOOD_SWORD*/
                gridLayout.addView(woodSword.getViewScene(3, 3
                        , crafttable, plankButton, stick
                        , crafttable, plankButton, stick ));
    /*5WOOD_SWORD*/
                gridLayout.addView(woodAxe.getViewScene(3, 3
                        , crafttable, plankButton, stick
                        , crafttable, plankButton, stick));
    /*6WOOD_HOE*/
                gridLayout.addView(woodHoe.getViewScene(4, 4
                        , crafttable, stick, plankButton, dirtPodzol
                        , crafttable, stick, plankButton, dirtPodzol));
    /*7COBL_BUTTON*/
                gridLayout.addView(cobbl.getViewScene(14, 14
                        , stoneShovel, stoneSword, stoneAxe, stonePickaxe, stoneHoe, furnece, wall, lavaPool, storogkaAuto
                        , villagerWorkerGold, villagerWorker, villagerUpgrageSergant, villagerSimpleAuto, villagerHorseTrener
                    /**/
                        , stoneShovel, stoneSword, stoneAxe, stonePickaxe, stoneHoe, furnece, wall, lavaPool, storogkaAuto
                        , villagerWorkerGold, villagerWorker, villagerUpgrageSergant, villagerSimpleAuto, villagerHorseTrener));
    /*8STONE_SHOVEL*/
                gridLayout.addView(stoneShovel.getViewScene(4, 4
                        , crafttable, stick, cobbl, upWorldVillager
                        , crafttable, stick, cobbl, upWorldVillager));
    /*9STONE_SWORD*/
                gridLayout.addView(stoneSword.getViewScene(6, 6
                        , crafttable, stick, cobbl, upWorldVillager, aggressiveMobsWorker, goodMobsWorker
                        , crafttable, stick, cobbl, upWorldVillager, aggressiveMobsWorker, goodMobsWorker));
    /*10STONE_AXE*/
                gridLayout.addView(stoneAxe.getViewScene(4, 4
                        , crafttable, stick, cobbl, upWorldVillager
                        , crafttable, stick, cobbl, upWorldVillager));
    /*11STONE_PICKAXE*/
                gridLayout.addView(stonePickaxe.getViewScene(5, 5
                        , crafttable, stick, cobbl, mineVillager, villagerStroitel
                        , crafttable, stick, cobbl, mineVillager, villagerStroitel));
    /*12STONE_HOE*/
                gridLayout.addView(stoneHoe.getViewScene(4, 4
                        , crafttable, stick, cobbl, foodVillager
                        , crafttable, stick, cobbl, foodVillager));
    /*13FURNECE*/
                gridLayout.addView(furnece.getViewScene(4, 4
                        ,crafttable, cobbl, ironIngot, kuznica
                        ,crafttable, cobbl, ironIngot, kuznica));
    /*14BUCKET*/
                gridLayout.addView(bucket.getViewScene(2, 2
                        , ironIngot, upWorldVillager
                        , ironIngot, upWorldVillager));
    /*15IRON_ORE*/
                gridLayout.addView(ironOre.getViewScene(1, 1
                        , ironIngot
                        , ironIngot));
    /*16COAL*/
                gridLayout.addView(coal.getViewScene(2, 2
                        , ironIngot, goldIngot
                        , ironIngot, goldIngot));
    /*17IRON_INGOT*/
                gridLayout.addView(ironIngot.getViewScene(16, 16
                        , furnece, coal, ironOre, bucket, ironPickaxe, ironShovel, ironSword
                        , ironAxe, ironHoe, pitchwork, saw, ironBlock, anvil, ironHelmet, ironChestplate
                        , ironBoots, ironLegins
                    /**/
                        , furnece, coal, ironOre, bucket, ironPickaxe, ironShovel, ironSword
                        , ironAxe, ironHoe, pitchwork, saw, ironBlock, anvil, ironHelmet, ironChestplate
                        , ironBoots, ironLegins));
    /*18DIRT*/
                gridLayout.addView(dirt.getViewScene(7, 7
                        , dirtPodzol, fermaAuto, villagerWorkerGold, villagerWorker, villagerUpgrageSergant, villagerSimpleAuto
                        , villagerHorseTrener
                    /**/
                        , dirtPodzol, fermaAuto, villagerWorkerGold, villagerWorker, villagerUpgrageSergant, villagerSimpleAuto
                        , villagerHorseTrener));
    /*19DIRT_PODZOL*/
                gridLayout.addView(dirtPodzol.getViewScene(3, 3
                        , woodHoe, dirt, ferma
                        , woodHoe, dirt, ferma));
    /*20WATER*/
                gridLayout.addView(water.getViewScene(7, 7
                        , ferma, fermaAuto, villagerWorkerGold, villagerWorker, villagerUpgrageSergant, villagerSimpleAuto
                        , villagerHorseTrener
                    /**/
                        , ferma, fermaAuto, villagerWorkerGold, villagerWorker, villagerUpgrageSergant, villagerSimpleAuto
                        , villagerHorseTrener));
    /*21FERMA*/
                gridLayout.addView(ferma.getViewScene(3, 3
                        , dirtPodzol, water, fermaWithGrass
                        , dirtPodzol, water, fermaWithGrass));
    /*22WHEAT_SEEDS*/
                gridLayout.addView(wheatSeeds.getViewScene(4, 4
                        , fermaWithGrass, fermaAuto, villagerSimpleAuto, villagerHorseTrener
                        , fermaWithGrass, fermaAuto, villagerSimpleAuto, villagerHorseTrener));
    /*23FERMA_WITH_GRASS*/
                gridLayout.addView(fermaWithGrass.getViewScene(9, 9
                        , ferma, wheatSeeds, villager, villagerLeather, villagerSegant, villagerSoldat,
                        villagerAutosmeltUpdte, villagerArowmaker, villagerPrezident
                    /**/
                        , ferma, wheatSeeds, villager, villagerLeather, villagerSegant, villagerSoldat,
                        villagerAutosmeltUpdte, villagerArowmaker, villagerPrezident));
    /*24PLANK_BUTTON*/
                gridLayout.addView(plankButton.getViewScene(12, 12
                        , woodButton, stick, woodPickaxe, woodShovel, woodSword, woodAxe, crafttable, door, woodHoe, zabor
                        , stairs, bookshelf
                        , woodButton, stick, woodPickaxe, woodShovel, woodSword, woodAxe, crafttable, door, woodHoe, zabor
                        , stairs, bookshelf));
     /*25CRAFTTABLE*/
                gridLayout.addView(crafttable.getViewScene(1, 1
                        , plankButton
                        , plankButton));
    /*26STICK*/
                gridLayout.addView(stick.getViewScene(34, 34
            /**/
                        , crafttable, plankButton, woodPickaxe, woodShovel, woodSword, woodAxe, woodHoe, stoneShovel, stoneSword
                        , stoneAxe, stonePickaxe, stoneHoe, arrow, ironPickaxe, ironShovel, ironSword
                        , ironAxe, ironHoe, diamondPickaxe, diamondShovel, diamondSword, diamondAxe, diamondHoe,
                        goldPickaxe, goldShovel, goldSword, goldAxe, goldHoe, zabor, pitchwork, saw, bow, ladder, diamonSipitr
            /**/
                        , crafttable, plankButton, woodPickaxe, woodShovel, woodSword, woodAxe, woodHoe, stoneShovel, stoneSword
                        , stoneAxe, stonePickaxe, stoneHoe, arrow, ironPickaxe, ironShovel, ironSword
                        , ironAxe, ironHoe, diamondPickaxe, diamondShovel, diamondSword, diamondAxe, diamondHoe,
                        goldPickaxe, goldShovel, goldSword, goldAxe, goldHoe, zabor, pitchwork, saw, bow, ladder, diamonSipitr));
    /*27DOOR*/
                gridLayout.addView(door.getViewScene(3, 3
                        , crafttable, plankButton, storogka
                        , crafttable, plankButton, storogka));
    /*28WALL*/
                gridLayout.addView(wall.getViewScene(2, 2
                        , cobbl, storogka
                        , cobbl, storogka));
    /*29STOROGKA*/
                gridLayout.addView(storogka.getViewScene(11, 11
                        , crafttable, wall, door, villager, home, villagerLeather, storogkaUpdate
                        , villagerAutosmeltUpdte, villagerArowmaker, tower, villagerPrezident
                    /**/
                        , crafttable, wall, door, villager, home, villagerLeather, storogkaUpdate
                        , villagerAutosmeltUpdte, villagerArowmaker, tower, villagerPrezident));
    /*30VILLAGER*/
                gridLayout.addView(villager.getViewScene(12, 12
                        , storogka, storogkaAuto, fermaWithGrass, fermaAuto, upWorldVillager
                        , educatedVillager, upWorldVillager, mineVillager, foodVillager, aggressiveMobsWorker,
                        goodMobsWorker, villagerSoldatAuto
                    /**/
                        , storogka, storogkaAuto, fermaWithGrass, fermaAuto, upWorldVillager
                        , educatedVillager, upWorldVillager, mineVillager, foodVillager, aggressiveMobsWorker,
                        goodMobsWorker, villagerSoldatAuto));
    /*31VILLAGER_UPW*/
                gridLayout.addView(upWorldVillager.getViewScene(6, 6
                        , villager, villagerSimpleAuto, stoneShovel, stoneAxe, stoneSword, bucket
                        , villager, villagerSimpleAuto, stoneShovel, stoneAxe, stoneSword, bucket));
    /*32VILLAGER_MINE*/
                gridLayout.addView(mineVillager.getViewScene(3, 3
                        , villager, villagerSimpleAuto, stonePickaxe
                        , villager, villagerSimpleAuto, stonePickaxe));
    /*33VILLAGER_FOOD*/
                gridLayout.addView(foodVillager.getViewScene(3, 3
                        , villager, villagerSimpleAuto, stoneHoe
                        , villager, villagerSimpleAuto, stoneHoe));
    /*34VILLAGER_AGGRESSIVE_MOBS_WORKER*/
                gridLayout.addView(aggressiveMobsWorker.getViewScene(3, 3
                        , villager, villagerSimpleAuto, stoneSword
                        , villager, villagerSimpleAuto, stoneSword));
    /*35ZABOR_UPDATE*/
                gridLayout.addView(zaborUpdate.getViewScene(4, 4
                        , crafttable, zabor, zaborCopy, goodMobsWorker
                        , crafttable, zabor, zaborCopy, goodMobsWorker));
    /*36ZABOR*/
                gridLayout.addView(zabor.getViewScene(4, 4
                        , crafttable, plankButton, stick, zaborUpdate
                        , crafttable, plankButton, stick, zaborUpdate));
    /*37VILLAGER_GOOD_MOBS_WORKER*/
                gridLayout.addView(goodMobsWorker.getViewScene(4, 4
                        , villager, villagerSimpleAuto, stoneSword, zaborUpdate
                        , villager, villagerSimpleAuto, stoneSword, zaborUpdate));
    /*38STAIRS*/
                gridLayout.addView(stairs.getViewScene(4, 4
                        , crafttable, plankButton, halfRoof, parta
                        , crafttable, plankButton, halfRoof, parta));
    /*39HALF_ROOF*/
                gridLayout.addView(halfRoof.getViewScene(2, 2
                        , stairs, twoTimeHalfRoof
                        , stairs, twoTimeHalfRoof));
    /*40TWO_TIME_HALF_ROOF*/
                gridLayout.addView(twoTimeHalfRoof.getViewScene(2, 2
                        , halfRoof, roof
                        , halfRoof, roof));
    /*41ROOF*/
                gridLayout.addView(roof.getViewScene(4, 4
                        , twoTimeHalfRoof, twoTimeHalfRoofCopy, home, homeCopy
                        , twoTimeHalfRoof, twoTimeHalfRoofCopy, home, homeCopy));
    /*42HOME*/
                gridLayout.addView(home.getViewScene(8, 8
                        , roof, roofCopy, storogka, storogkaAuto, library, kuznica, doubleHouse,villagerSegant
                        , roof, roofCopy, storogka, storogkaAuto, library, kuznica, doubleHouse,villagerSegant));
    /*43BAMBUK*/
                gridLayout.addView(bambuk.getViewScene(2, 2
                        , bambukFerma, paper
                        , bambukFerma, paper));
    /*44FERMA_BAMBUK*/
                gridLayout.addView(bambukFerma.getViewScene(2, 2
                        , bambuk, ferma
                        , bambuk, ferma));
    /*45PAPER*/
                gridLayout.addView(paper.getViewScene(3, 3
                        , crafttable, bambuk, book
                        , crafttable, bambuk, book));
    /*46BOOK*/
                gridLayout.addView(book.getViewScene(4, 4
                        , crafttable, paper, leather, bookshelf
                        , crafttable, paper, leather, bookshelf));

    /*47BOOK_SHELF*/
                gridLayout.addView(bookshelf.getViewScene(4, 4
                        , crafttable, plankButton, book, bookshelfUpdate
                        , crafttable, plankButton, book, bookshelfUpdate));
    /*48BOOK_SHELF_UPDATE*/
                gridLayout.addView(bookshelfUpdate.getViewScene(2, 2
                        , bookshelf, library
                        , bookshelf, library));
    /*49LIBRARY*/
                gridLayout.addView(library.getViewScene(4, 4
                        , bookshelfUpdate, home,homeCopy, educatedVillager
                        , bookshelfUpdate, home,homeCopy, educatedVillager));
    /*50EDUCATED_VILLAGER*/
                gridLayout.addView(educatedVillager.getViewScene(9, 9
                        , library, villager, villagerSimpleAuto, crafttable, villagerStroitel, villagerSaw, villagerMayor, villagerFermer, villagerAutosmelt
                        , library, villager, villagerSimpleAuto, crafttable, villagerStroitel, villagerSaw, villagerMayor, villagerFermer, villagerAutosmelt));
    /*51VILLAGER_STROITEL*/
                gridLayout.addView(villagerStroitel.getViewScene(9, 9
                        , stonePickaxe, educatedVillager, storogkaAuto, lavaPool, storogkaUpdate, tower
                        , towerAttak, towerUpdate, towerBest
                    /**/
                        , stonePickaxe, educatedVillager, storogkaAuto, lavaPool, storogkaUpdate, tower
                        , towerAttak, towerUpdate, towerBest));
    /*52LAVA*/
                gridLayout.addView(lava.getViewScene(1, 1
                        , lavaPool
                        , lavaPool));
    /*53LAVA_POOL*/
                gridLayout.addView(lavaPool.getViewScene(4, 4
                        , villagerStroitel, cobbl, lava, kuznica
                        , villagerStroitel, cobbl, lava, kuznica));
    /*54KUZNICA*/
                gridLayout.addView(kuznica.getViewScene(5, 5
                        , home,homeCopy, lavaPool, furnece, villagerAutosmelt
                        , home,homeCopy, lavaPool, furnece, villagerAutosmelt));
    /*55VILLAGER_AUROSMELT*/
                gridLayout.addView(villagerAutosmelt.getViewScene(2, 2
                        , educatedVillager, kuznica
                        , educatedVillager, kuznica));
    /*56PITCHWORK*/
                gridLayout.addView(pitchwork.getViewScene(4, 4
                        , crafttable, stick, ironIngot, villagerFermer
                        , crafttable, stick, ironIngot, villagerFermer));
    /*57VILLAGER_FERMER*/
                gridLayout.addView(villagerFermer.getViewScene(3, 3
                        , educatedVillager, pitchwork, fermaAuto
                        , educatedVillager, pitchwork, fermaAuto));
    /*58SAW*/
                gridLayout.addView(saw.getViewScene(4, 4
                        , crafttable, stick, ironIngot, villagerSaw
                        , crafttable, stick, ironIngot, villagerSaw));
    /*59VILLAGER_SAW*/
                gridLayout.addView(villagerSaw.getViewScene(8, 8
                        , educatedVillager, saw, zaborCopy, zaborUpdateCopy, twoTimeHalfRoofCopy, roofCopy, stoilo, staini
                        , educatedVillager, saw, zaborCopy, zaborUpdateCopy, twoTimeHalfRoofCopy, roofCopy, stoilo, staini));
    /*60PARTA*/
                gridLayout.addView(parta.getViewScene(2, 2
                        , stairs, villagerMayor
                        , stairs, villagerMayor));
    /*61VILLAGER_MAYOR*/
                gridLayout.addView(villagerMayor.getViewScene(8, 8
                        , home,homeCopy, parta, educatedVillager, villagerLeather, villagerSegant, villagerAutosmeltUpdte
                        , villagerArowmaker
                    /**/
                        , home,homeCopy, parta, educatedVillager, villagerLeather, villagerSegant, villagerAutosmeltUpdte
                        , villagerArowmaker));
    /*62FERMA_AUTO*/
                gridLayout.addView(fermaAuto.getViewScene(10, 10
                        , villagerFermer, dirt, water, wheatSeeds, villagerLeather, villagerSoldat, villagerSegant,
                        villagerAutosmeltUpdte, villagerArowmaker, villagerPrezident
                    /**/
                        , villagerFermer, dirt, water, wheatSeeds, villagerLeather, villagerSoldat, villagerSegant,
                        villagerAutosmeltUpdte, villagerArowmaker, villagerPrezident));
    /*63STOROGKA_AUTO*/
                gridLayout.addView(storogkaAuto.getViewScene(9, 9
                        , villagerStroitel, cobbl, woodButton, villagerLeather, storogkaUpdate,
                        villagerAutosmeltUpdte, villagerArowmaker, tower, villagerPrezident
                    /**/
                        , villagerStroitel, cobbl, woodButton, villagerLeather, storogkaUpdate,
                        villagerAutosmeltUpdte, villagerArowmaker, tower, villagerPrezident));
    /*64IRON_PICKAXE*/
                gridLayout.addView(ironPickaxe.getViewScene(3, 3
                        , crafttable, stick, ironIngot
                        , crafttable, stick, ironIngot));
    /*65IRON_SHOWEL*/
                gridLayout.addView(ironShovel.getViewScene(3, 3
                        , crafttable, stick, ironIngot
                        , crafttable, stick, ironIngot));
    /*66IRON_SWORD*/
                gridLayout.addView(ironSword.getViewScene(5, 5
                        , crafttable, stick, ironIngot, villagerSoldat, villagerSegant
                        , crafttable, stick, ironIngot, villagerSoldat, villagerSegant));
    /*67IRON_AXE*/
                gridLayout.addView(ironAxe.getViewScene(3, 3
                        , crafttable, stick, ironIngot
                        , crafttable, stick, ironIngot));
    /*68IRON_HOE*/
                gridLayout.addView(ironHoe.getViewScene(3, 3
                        , crafttable, stick, ironIngot
                        , crafttable, stick, ironIngot));
    /*69VILLAGER_LEATHER*/
                gridLayout.addView(villagerLeather.getViewScene(10, 10
                        , villagerMayor, storogka, storogkaAuto, fermaWithGrass, fermaAuto, leather
                        , leatherHelmet, leatherChestplate, leatherBoots, leatherLegins
                    /**/
                        , villagerMayor, storogka, storogkaAuto, fermaWithGrass, fermaAuto, leather
                        , leatherHelmet, leatherChestplate, leatherBoots, leatherLegins));
    /*71LEATHER*/
                gridLayout.addView(leather.getViewScene(7, 7
                        , book, villagerLeather, leatherHelmet, leatherChestplate, leatherLegins, leatherBoots
                        , saddle
                    /**/
                        , book, villagerLeather, leatherHelmet, leatherChestplate, leatherLegins, leatherBoots
                        , saddle));
     /*71LEATHER_HELMET*/
                gridLayout.addView(leatherHelmet.getViewScene(3, 3
                        , villagerLeather, leather, leatherArmor
                        , villagerLeather, leather, leatherArmor));
     /*71LEATHER_CHESTPLATE*/
                gridLayout.addView(leatherChestplate.getViewScene(3, 3
                        , villagerLeather, leather, leatherArmor
                        , villagerLeather, leather, leatherArmor));
    /*71LEATHER_LEGINS*/
                gridLayout.addView(leatherLegins.getViewScene(3, 3
                        , villagerLeather, leather, leatherArmor
                        , villagerLeather, leather, leatherArmor));
    /*71LEATHER_BOOTS*/
                gridLayout.addView(leatherBoots.getViewScene(3, 3
                        , villagerLeather, leather, leatherArmor
                        , villagerLeather, leather, leatherArmor));
    /*71LEATHER_ARMOR*/
                gridLayout.addView(leatherArmor.getViewScene(5, 5
                        , leatherHelmet, leatherChestplate, leatherLegins, leatherBoots, villagerLetherSoldat
                        , leatherHelmet, leatherChestplate, leatherLegins, leatherBoots, villagerLetherSoldat));
    /*91VILLAGER_SERGANT*/
                gridLayout.addView(villagerSegant.getViewScene(7, 7
                        , villagerMayor, home,homeCopy, fermaAuto, fermaWithGrass, ironSword, villagerSoldat
                        , villagerMayor, home,homeCopy, fermaAuto, fermaWithGrass, ironSword, villagerSoldat));
    /*91VILLAGER_SOLDAT*/
                gridLayout.addView(villagerSoldat.getViewScene(7, 7
                        , villagerSegant, ironSword, fermaAuto, fermaWithGrass, villagerLetherSoldat
                        , villagerIronSoldat, villagerGold
                    /**/
                        , villagerSegant, ironSword, fermaAuto, fermaWithGrass, villagerLetherSoldat
                        , villagerIronSoldat, villagerGold));
    /*91VILLAGER_LEATHER_SOLDAT*/
                gridLayout.addView(villagerLetherSoldat.getViewScene(5, 5
                        , villagerSoldat, villagerSoldatAuto, leatherArmor, storogkaUpdate, villagerBow
                        , villagerSoldat, villagerSoldatAuto, leatherArmor, storogkaUpdate, villagerBow));
    /*91STOROGKA_UPDATE*/
                gridLayout.addView(storogkaUpdate.getViewScene(5, 5
                        , villagerStroitel, storogka, storogkaAuto, villagerLetherSoldat, towerUpdate
                        , villagerStroitel, storogka, storogkaAuto, villagerLetherSoldat, towerUpdate));
    /*91IRON_BLOCK*/
                gridLayout.addView(ironBlock.getViewScene(3, 3
                        , crafttable, ironIngot, anvil
                        , crafttable, ironIngot, anvil));
    /*91ANVIL*/
                gridLayout.addView(anvil.getViewScene(4, 4
                        , crafttable, ironBlock, ironIngot, villagerAutosmeltUpdte
                        , crafttable, ironBlock, ironIngot, villagerAutosmeltUpdte));
    /*91villagerAutosmeltUpdte*/
                gridLayout.addView(villagerAutosmeltUpdte.getViewScene(10, 10
                        , villagerMayor, fermaWithGrass, fermaAuto, storogkaAuto, storogka, anvil,
                        ironHelmet, ironChestplate, ironBoots, ironLegins
                    /**/
                        , villagerMayor, fermaWithGrass, fermaAuto, storogkaAuto, storogka, anvil,
                        ironHelmet, ironChestplate, ironBoots, ironLegins));
    /*71iron_HELMET*/
                gridLayout.addView(ironHelmet.getViewScene(3, 3
                        , villagerAutosmeltUpdte, ironIngot, ironArmor
                        , villagerAutosmeltUpdte, ironIngot, ironArmor));
     /*71iron_CHESTPLATE*/
                gridLayout.addView(ironChestplate.getViewScene(3, 3
                        , villagerAutosmeltUpdte, ironIngot, ironArmor
                        , villagerAutosmeltUpdte, ironIngot, ironArmor));
    /*71iron_LEGINS*/
                gridLayout.addView(ironLegins.getViewScene(3, 3
                        , villagerAutosmeltUpdte, ironIngot, ironArmor
                        , villagerAutosmeltUpdte, ironIngot, ironArmor));
    /*71iron_BOOTS*/
                gridLayout.addView(ironBoots.getViewScene(3, 3
                        , villagerAutosmeltUpdte, ironIngot, ironArmor
                        , villagerAutosmeltUpdte, ironIngot, ironArmor));
    /*71iron_ARMOR*/
                gridLayout.addView(ironArmor.getViewScene(5, 5
                        , ironHelmet, ironChestplate, ironBoots, ironLegins, villagerIronSoldat
                        , ironHelmet, ironChestplate, ironBoots, ironLegins, villagerIronSoldat));
    /*91VILLAGER_IRON_SOLDAT*/
                gridLayout.addView(villagerIronSoldat.getViewScene(4, 4
                        , villagerSoldat, villagerSoldatAuto, ironArmor, villagerFireTeam
                        , villagerSoldat, villagerSoldatAuto, ironArmor, villagerFireTeam));
    /*91VILLAGER_AROWMAKER*/
                gridLayout.addView(villagerArowmaker.getViewScene(8, 8
                        , villagerMayor, storogka, storogkaAuto, fermaAuto, fermaWithGrass, arrow
                        , arrow, bow
                    /**/
                        , villagerMayor, storogka, storogkaAuto, fermaAuto, fermaWithGrass, arrow
                        , arrow, bow));
     /*91WOOL*/
                gridLayout.addView(wool.getViewScene(1, 1
                        , plankButton
                        , plankButton));
    /*92STTRING*/
                gridLayout.addView(sttring.getViewScene(1, 1
                        , bow
                        , bow));
    /*92BOW*/
                gridLayout.addView(bow.getViewScene(4, 4
                        , villagerArowmaker, sttring, stick, villagerBow
                        , villagerArowmaker, sttring, stick, villagerBow));
    /*94FATHER*/
                gridLayout.addView(father.getViewScene(1, 1
                        , arrow
                        , arrow));
    /*95FLINT*/
                gridLayout.addView(flint.getViewScene(1, 1
                        , arrow
                        , arrow));
    /*96ARROW*/
                gridLayout.addView(arrow.getViewScene(6, 6
                        , villagerArowmaker, flint, stick, father, villagerArowmaker, villagerBow
                        , villagerArowmaker, flint, stick, father, villagerArowmaker, villagerBow));
    /*92VILLAGER_BOW*/
                gridLayout.addView(villagerBow.getViewScene(4, 4
                        , villagerLetherSoldat, bow, arrow, villagerFireTeam
                        , villagerLetherSoldat, bow, arrow, villagerFireTeam));
    /*92VILLAGER_FIRE_TEAM*/
                gridLayout.addView(villagerFireTeam.getViewScene(3, 3
                        , villagerIronSoldat, villagerBow, towerAttak
                        , villagerIronSoldat, villagerBow, towerAttak));
    /*106LADDER*/
                gridLayout.addView(ladder.getViewScene(3, 3
                        , crafttable, stick, tower
                        , crafttable, stick, tower));
    /*106TOWER*/
                gridLayout.addView(tower.getViewScene(6, 6
                        , villagerStroitel, storogkaAuto, storogka, ladder, towerAttak, towerThree
                        , villagerStroitel, storogkaAuto, storogka, ladder, towerAttak, towerThree));
    /*106TOWER_ATTACK*/
                gridLayout.addView(towerAttak.getViewScene(4, 4
                        , villagerStroitel, tower, villagerFireTeam, towerUpdate
                        , villagerStroitel, tower, villagerFireTeam, towerUpdate));
    /*106TOWER_UPDATE*/
                gridLayout.addView(towerUpdate.getViewScene(4, 4
                        , villagerStroitel, storogkaUpdate, towerAttak, towerBest
                        , villagerStroitel, storogkaUpdate, towerAttak, towerBest));
    /*106TOWER_BEST*/
                gridLayout.addView(towerBest.getViewScene(5, 5
                        , villagerStroitel, towerUpdate, towerBestDownload, villagerPrezident, fort
                        , villagerStroitel, towerUpdate, towerBestDownload, villagerPrezident, fort));
    /*106TOWER_BEST_DOWNLOAD*/
                gridLayout.addView(towerBestDownload.getViewScene(1, 1
                        , towerBest
                        , towerBest));
    /*106VILLAGER_PRESIDENT*/
                gridLayout.addView(villagerPrezident.getViewScene(8, 8
                        , towerBest, storogka, storogkaAuto, fermaWithGrass, fermaAuto, villagerWorkerGold,
                        villagerWorker, villagerUpgrageSergant
                    /**/
                        , towerBest, storogka, storogkaAuto, fermaWithGrass, fermaAuto, villagerWorkerGold,
                        villagerWorker, villagerUpgrageSergant
                ));
    /*106GOLD_ORE*/
                gridLayout.addView(goldOre.getViewScene(1, 1
                        , goldIngot
                        , goldIngot));
    /*107GOLD_INGOT*/
                gridLayout.addView(goldIngot.getViewScene(13, 13
                        , furnece, coal, goldOre, goldPickaxe, goldShovel, goldSword
                        , goldAxe, goldHoe, villagerSoldatAuto, goldHelmet, goldBoots,
                        goldLegins, goldChestplate
                    /**/
                        , furnece, coal, goldOre, goldPickaxe, goldShovel, goldSword
                        , goldAxe, goldHoe, villagerSoldatAuto, goldHelmet, goldBoots,
                        goldLegins, goldChestplate));
    /*108GOLD_PICKAXE*/
                gridLayout.addView(goldPickaxe.getViewScene(3, 3
                        , crafttable, stick, goldIngot
                        , crafttable, stick, goldIngot));
    /*109GOLD_SHOWEL*/
                gridLayout.addView(goldShovel.getViewScene(3, 3
                        , crafttable, stick, goldIngot
                        , crafttable, stick, goldIngot));
    /*110GOLD_SWORD*/
                gridLayout.addView(goldSword.getViewScene(3, 3
                        , crafttable, stick, goldIngot
                        , crafttable, stick, goldIngot));
    /*111GOLD_AXE*/
                gridLayout.addView(goldAxe.getViewScene(3, 3
                        , crafttable, stick, goldIngot
                        , crafttable, stick, goldIngot));
    /*112GOLD_HOE*/
                gridLayout.addView(goldHoe.getViewScene(3, 3
                        , crafttable, stick, goldIngot
                        , crafttable, stick, goldIngot));
    /*128VILLAGER_WORKER_GOLD*/
                gridLayout.addView(villagerWorkerGold.getViewScene(10, 10
                        , villagerPrezident, cobbl, woodButton, dirt, water, wheat,
                        goldHelmet, goldBoots, goldChestplate, goldLegins
                    /**/
                        , villagerPrezident, cobbl, woodButton, dirt, water, wheat,
                        goldHelmet, goldBoots, goldChestplate, goldLegins));
    /*128VILLAGER_WORKER*/
                gridLayout.addView(villagerWorker.getViewScene(13, 13
                        , villagerPrezident, cobbl, woodButton, dirt, water, wheat,
                        villagerSimpleAuto, wallTower, wallTowerUpgrade, fort,
                        homeCopy, doubleHouse, castle
                    /**/
                        , villagerPrezident, cobbl, woodButton, dirt, water, wheat,
                        villagerSimpleAuto, wallTower, wallTowerUpgrade, fort,
                        homeCopy, doubleHouse, castle));
    /*128VILLAGER_UPgrATE_SERGANT*/
                gridLayout.addView(villagerUpgrageSergant.getViewScene(7, 7
                        , villagerPrezident, cobbl, woodButton, dirt, water, wheat,
                        villagerSoldatAuto
                    /**/
                        , villagerPrezident, cobbl, woodButton, dirt, water, wheat,
                        villagerSoldatAuto));
    /*136VILLAGER_SIMPLE_AUTO*/
                gridLayout.addView(villagerSimpleAuto.getViewScene(6, 6
                        , villagerWorker, cobbl, woodButton, dirt, water, wheatSeeds
                        , villagerWorker, cobbl, woodButton, dirt, water, wheatSeeds));
    /*136VILLAGER_SOLDAT_AUTO*/
                gridLayout.addView(villagerSoldatAuto.getViewScene(5, 5
                        , villagerUpgrageSergant, villager, villagerSimpleAuto, woodButton, goldIngot
                        , villagerUpgrageSergant, villager, villagerSimpleAuto, woodButton, goldIngot));
    /*71gold_HELMET*/
                gridLayout.addView(goldHelmet.getViewScene(3, 3
                        , villagerWorkerGold, goldIngot, goldArmor
                        , villagerWorkerGold, goldIngot, goldArmor));
     /*71gold_CHESTPLATE*/
                gridLayout.addView(goldChestplate.getViewScene(3, 3
                        , villagerWorkerGold, goldIngot, goldArmor
                        , villagerWorkerGold, goldIngot, goldArmor));
    /*71gold_LEGINS*/
                gridLayout.addView(goldLegins.getViewScene(3, 3
                        , villagerWorkerGold, goldIngot, goldArmor
                        , villagerWorkerGold, goldIngot, goldArmor));
    /*71gold_BOOTS*/
                gridLayout.addView(goldBoots.getViewScene(3, 3
                        , villagerWorkerGold, goldIngot, goldArmor
                        , villagerWorkerGold, goldIngot, goldArmor));
    /*71gold_ARMOR*/
                gridLayout.addView(goldArmor.getViewScene(5, 5
                        , goldHelmet, goldChestplate, goldLegins, goldBoots, villagerGold
                        , goldHelmet, goldChestplate, goldLegins, goldBoots, villagerGold));
     /*91VILLAGER_GOLD_SOLDAT*/
                gridLayout.addView(villagerGold.getViewScene(4, 4
                        , villagerSoldat, villagerSoldatAuto, goldArmor, wallTower
                        , villagerSoldat, villagerSoldatAuto, goldArmor, wallTower));
    /*136WALL_TOWER*/
                gridLayout.addView(wallTower.getViewScene(4, 4
                        , villagerWorker, cobbl, villagerGold, wallTowerUpgrade
                        , villagerWorker, cobbl, villagerGold, wallTowerUpgrade));
    /*136WALL_TOWER_UPGRATE*/
                gridLayout.addView(wallTowerUpgrade.getViewScene(3, 3
                        , villagerWorker, wallTower, fort
                        , villagerWorker, wallTower, fort));
    /*136FORT*/
                gridLayout.addView(fort.getViewScene(5, 5
                        , villagerWorker, towerBest, wallTowerUpgrade, castle, fortDownload
                        , villagerWorker, towerBest, wallTowerUpgrade, castle, fortDownload));
    /*136FORT_DOWNLOAD*/
                gridLayout.addView(fortDownload.getViewScene(1, 1
                        , fort
                        , fort));
    /*128WHEAT*/
                gridLayout.addView(wheat.getViewScene(4, 4
                        , villagerWorkerGold, villagerWorker, villagerUpgrageSergant, wheatBlock
                        , villagerWorkerGold, villagerWorker, villagerUpgrageSergant, wheatBlock));
    /*128WHEAT_BLOCK*/
                gridLayout.addView(wheatBlock.getViewScene(4, 4
                        , crafttable, wheat, stoilo, wheahPool
                        , crafttable, wheat, stoilo, wheahPool));
    /*136zaborCopy*/
                gridLayout.addView(zaborCopy.getViewScene(3, 3
                        , villagerSaw, woodButton, zaborUpdateCopy
                        , villagerSaw, woodButton, zaborUpdateCopy));
    /*136zabor_update_copy*/
                gridLayout.addView(zaborUpdateCopy.getViewScene(4, 4
                        , villagerSaw, zabor, zaborCopy, stoilo
                        , villagerSaw, zabor, zaborCopy, stoilo));
    /*136two_time_half_roof_copy*/
                gridLayout.addView(twoTimeHalfRoofCopy.getViewScene(2, 2
                        , villagerSaw, woodButton, roofCopy
                        , villagerSaw, woodButton, roofCopy));
    /*136roof_copy*/
                gridLayout.addView(roofCopy.getViewScene(4, 4
                        , villagerSaw, twoTimeHalfRoofCopy, twoTimeHalfRoof, stoilo
                        , villagerSaw, twoTimeHalfRoofCopy, twoTimeHalfRoof, stoilo));
    /*136stoilo*/
                gridLayout.addView(stoilo.getViewScene(7, 7
                        , villagerSaw, zaborUpdate, zaborUpdateCopy, roof, roofCopy, wheatBlock, staini
                        , villagerSaw, zaborUpdate, zaborUpdateCopy, roof, roofCopy, wheatBlock, staini));
    /*136staini*/
                gridLayout.addView(staini.getViewScene(2, 2
                        , villagerSaw, stoilo
                        , villagerSaw, stoilo));
    /*136SLIM*/
                gridLayout.addView(slim.getViewScene(1, 1
                        , lead
                        , lead));
    /*136lead*/
                gridLayout.addView(lead.getViewScene(3, 3
                        , villagerHorseTrener, sttring, slim
                        , villagerHorseTrener, sttring, slim));
    /*136villager_horse_trener*/
                gridLayout.addView(villagerHorseTrener.getViewScene(8, 8
                        , villagerPrezident, cobbl, woodButton, dirt, water, wheatSeeds, lead, tamedHorse
                        , villagerPrezident, cobbl, woodButton, dirt, water, wheatSeeds, lead, tamedHorse));
    /*136saddle*/
                gridLayout.addView(saddle.getViewScene(4, 4
                        , crafttable, villagerLeather, leather, tamedHorse
                        , crafttable, villagerLeather, leather, tamedHorse));
    /*140HORSE*/
                gridLayout.addView(horse.getViewScene(1, 1
                        , tamedHorse
                        , tamedHorse));
    /*140tamed_HORSE*/
                gridLayout.addView(tamedHorse.getViewScene(4, 4
                        , villagerHorseTrener, horse, saddle, diamondHorse
                        , villagerHorseTrener, horse, saddle, diamondHorse));
    /*140home_copy*/
                gridLayout.addView(homeCopy.getViewScene(7, 7
                        , villagerWorker, cobbl, woodButton, roof, roofCopy, doubleHouse, villagerSegant
                        , villagerWorker, cobbl, woodButton, roof, roofCopy, doubleHouse, villagerSegant));
    /*140double_house*/
                gridLayout.addView(doubleHouse.getViewScene(4, 4
                        , villagerWorker, homeCopy, home, castle
                        , villagerWorker, homeCopy, home, castle));
    /*140castle*/
                gridLayout.addView(castle.getViewScene(7, 7
                        , villagerWorker, fort, doubleHouse, villagerGold, villagerKing,
                        wheahPool, castleBest
                    /**/
                        , villagerWorker, fort, doubleHouse, villagerGold, villagerKing,
                        wheahPool, castleBest));
    /*148DIAMOND*/
                gridLayout.addView(diamond.getViewScene(7, 7
                        , diamondPickaxe, diamondShovel, diamondSword, diamondAxe, diamondHoe
                        , diamonSipitr, diamondblock
                    /**/
                        , diamondPickaxe, diamondShovel, diamondSword, diamondAxe, diamondHoe
                        , diamonSipitr, diamondblock));
     /*148DIAMON_SIPITR*/
                gridLayout.addView(diamonSipitr.getViewScene(4, 4
                        , crafttable, stick, diamond, villagerKing
                        , crafttable, stick, diamond, villagerKing));
    /*148VILLAGER_KING*/
                gridLayout.addView(villagerKing.getViewScene(6, 6
                        , castle, villager, villagerSimpleAuto, diamonSipitr, villagerWorkDiamond, villagerDiamond
                        , castle, villager, villagerSimpleAuto, diamonSipitr, villagerWorkDiamond, villagerDiamond));
    /*148VILLAGER_KING*/
                gridLayout.addView(villagerWorkDiamond.getViewScene(6, 6
                        , villagerKing, villager, villagerSimpleAuto, diamond, diamondArmor, diamondHorse
                        , villagerKing, villager, villagerSimpleAuto, diamond, diamondArmor, diamondHorse));
    /*152DIAMOND_PICKAXE*/
                gridLayout.addView(diamondPickaxe.getViewScene(3, 3
                        , crafttable, stick, diamond
                        , crafttable, stick, diamond));
    /*153DIAMOND_SHOWEL*/
                gridLayout.addView(diamondShovel.getViewScene(3, 3
                        , crafttable, stick, diamond
                        , crafttable, stick, diamond));
    /*154DIAMOND_SWORD*/
                gridLayout.addView(diamondSword.getViewScene(3, 3
                        , crafttable, stick, diamond
                        , crafttable, stick, diamond));
    /*155DIAMOND_AXE*/
                gridLayout.addView(diamondAxe.getViewScene(3, 3
                        , crafttable, stick, diamond
                        , crafttable, stick, diamond));
    /*156DIAMOND_HOE*/
                gridLayout.addView(diamondHoe.getViewScene(3, 3
                        , crafttable, stick, diamond
                        , crafttable, stick, diamond));
    /*156DIAMOND_ARMOR*/
                gridLayout.addView(diamondArmor.getViewScene(3, 3
                        , villagerWorkDiamond, diamond, villagerDiamond
                        , villagerWorkDiamond, diamond, villagerDiamond));
    /*156DIAMOND_ARMOR*/
                gridLayout.addView(villagerDiamond.getViewScene(5, 5
                        , villagerKing, diamondArmor, villager, villagerSimpleAuto, castleBest
                        , villagerKing, diamondArmor, villager, villagerSimpleAuto, castleBest));
    /*156DIAMOND_ARMOR*/
                gridLayout.addView(diamondblock.getViewScene(3, 3
                        , crafttable, diamond, diamondHorse
                        , crafttable, diamond, diamondHorse));
    /*156DIAMOND_HORSE*/
                gridLayout.addView(diamondHorse.getViewScene(4, 4
                        , villagerWorkDiamond, tamedHorse, diamondblock, towerBest
                        , villagerWorkDiamond, tamedHorse, diamondblock, towerBest));
    /*156WEAT_POOL*/
                gridLayout.addView(wheahPool.getViewScene(2, 2
                        , wheatBlock, towerThree
                        , wheatBlock, towerThree));
    /*156TOWER_THREE*/
                gridLayout.addView(towerThree.getViewScene(4, 4
                        , castle, tower, wheahPool, towerWall
                        , castle, tower, wheahPool, towerWall));
    /*156TOWER_WALL*/
                gridLayout.addView(towerWall.getViewScene(3, 3
                        , castle, towerThree, castleBest
                        , castle, towerThree, castleBest));
    /*156CASTLE_BEST*/
                gridLayout.addView(castleBest.getViewScene(5, 5
                        , castle, diamondHorse, villagerDiamond, towerWall, castleBestDownload
                        , castle, diamondHorse, villagerDiamond, towerWall, castleBestDownload));
    /*156CASTLE_BEST_Download*/
                gridLayout.addView(castleBestDownload.getViewScene(1, 1
                        , castleBest
                        , castleBest));
    /*156CASTLE_BEST_Download*/



            Observable.timer(500, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(upWorld.finishLoad());
            Observable.timer(500, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(mine.finishLoad());
            Observable.timer(500, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(food.finishLoad());
            Observable.timer(500, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(mobs.finishLoad());
        });



        Observable.timer(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        woodPickaxe.getIT().lockUnlockFunction(woodPickaxe.getIT());
                        woodShovel.getIT().lockUnlockFunction(woodShovel.getIT());
                        woodSword.getIT().lockUnlockFunction(woodSword.getIT());
                        woodAxe.getIT().lockUnlockFunction(woodAxe.getIT());
                        woodHoe.getIT().lockUnlockFunction(woodHoe.getIT());
                        stoneShovel.getIT().lockUnlockFunction(stoneShovel.getIT());
                        stoneSword.getIT().lockUnlockFunction(stoneSword.getIT());
                        stoneAxe.getIT().lockUnlockFunction(stoneAxe.getIT());
                        stonePickaxe.getIT().lockUnlockFunction(stonePickaxe.getIT());
                        stoneHoe.getIT().lockUnlockFunction(stoneHoe.getIT());
                        furnece.getIT().lockUnlockFunction(furnece.getIT());
                        bucket.getIT().lockUnlockFunction(bucket.getIT());
                        ironIngot.getIT().lockUnlockFunction(ironIngot.getIT());
                        dirtPodzol.getIT().lockUnlockFunction(dirtPodzol.getIT());
                        ferma.getIT().lockUnlockFunction(ferma.getIT());
                        fermaWithGrass.getIT().lockUnlockFunction(fermaWithGrass.getIT());
                        plankButton.getIT().lockUnlockFunction(plankButton.getIT());
                        crafttable.getIT().lockUnlockFunction(crafttable.getIT());
                        stick.getIT().lockUnlockFunction(stick.getIT());
                        door.getIT().lockUnlockFunction(door.getIT());
                        wall.getIT().lockUnlockFunction(wall.getIT());
                        storogka.getIT().lockUnlockFunction(storogka.getIT());
                        villager.getIT().lockUnlockFunction(villager.getIT());
                        /*31*/upWorldVillager.lockUnlockFunction(upWorldVillager.getIT());
                        /*32*/mineVillager.lockUnlockFunction(mineVillager.getIT());
                        /*33*/foodVillager.lockUnlockFunction(foodVillager.getIT());
                        /*33*/aggressiveMobsWorker.lockUnlockFunction(aggressiveMobsWorker.getIT());
                        /*33*/goodMobsWorker.lockUnlockFunction(goodMobsWorker.getIT());
                        /*33*/foodVillager.lockUnlockFunction(foodVillager.getIT());
                        /*34*/zaborUpdate.lockUnlockFunction(zaborUpdate.getIT());
                        /*35*/zabor.lockUnlockFunction(zabor.getIT());
                        /*38*/stairs.lockUnlockFunction(stairs.getIT());
                        /*39*/halfRoof.lockUnlockFunction(halfRoof.getIT());
                        /*40*/twoTimeHalfRoof.lockUnlockFunction(twoTimeHalfRoof.getIT());
                        /*41*/roof.lockUnlockFunction(roof.getIT());
                        /*42*/home.lockUnlockFunction(home.getIT());
                        /*44*/bambukFerma.lockUnlockFunction(bambukFerma.getIT());
                        /*45*/paper.lockUnlockFunction(paper.getIT());
                        /*46*/book.lockUnlockFunction(book.getIT());
                        /*47*/bookshelf.lockUnlockFunction(bookshelf.getIT());
                        /*48*/bookshelfUpdate.lockUnlockFunction(bookshelfUpdate.getIT());
                        /*49*/library.lockUnlockFunction(library.getIT());
                        /*50*/educatedVillager.lockUnlockFunction(educatedVillager.getIT());
                        /*51*/villagerStroitel.lockUnlockFunction(villagerStroitel.getIT());
                        /*53*/lavaPool.lockUnlockFunction(villagerStroitel.getIT());
                            kuznica.lockUnlockFunction(kuznica.getIT());
                            villagerAutosmelt.lockUnlockFunction(villagerAutosmelt.getIT());
                            pitchwork.lockUnlockFunction(pitchwork.getIT());
                            villagerFermer.lockUnlockFunction(villagerFermer.getIT());
                            saw.lockUnlockFunction(saw.getIT());
                            villagerSaw.lockUnlockFunction(villagerSaw.getIT());
                            parta.lockUnlockFunction(parta.getIT());
                            villagerMayor.lockUnlockFunction(villagerMayor.getIT());
                            fermaAuto.lockUnlockFunction(fermaAuto.getIT());
                        /*63*/storogkaAuto.lockUnlockFunction(storogkaAuto.getIT());
                        /*64*/ironPickaxe.lockUnlockFunction(ironPickaxe.getIT());
                        /*65*/ironShovel.lockUnlockFunction(ironShovel.getIT());
                        /*66*/ironSword.lockUnlockFunction(ironSword.getIT());
                        /*67*/ironAxe.lockUnlockFunction(ironAxe.getIT());
                        /*68*/ironHoe.lockUnlockFunction(ironHoe.getIT());
                        /*107*/goldIngot.getIT().lockUnlockFunction(goldIngot.getIT());
                        /*108*/goldPickaxe.lockUnlockFunction(goldPickaxe.getIT());
                        /*109*/goldShovel.lockUnlockFunction(goldShovel.getIT());
                        /*110*/goldSword.lockUnlockFunction(goldSword.getIT());
                        /*111*/goldAxe.lockUnlockFunction(goldAxe.getIT());
                        /*112*/goldHoe.lockUnlockFunction(goldHoe.getIT());
                        /*96*/arrow.lockUnlockFunction(arrow.getIT());
                        /*152*/diamondPickaxe.lockUnlockFunction(diamondPickaxe.getIT());
                        /*153*/diamondShovel.lockUnlockFunction(diamondShovel.getIT());
                        /*154*/diamondSword.lockUnlockFunction(diamondSword.getIT());
                        /*155*/diamondAxe.lockUnlockFunction(diamondAxe.getIT());
                        /*156*/diamondHoe.lockUnlockFunction(diamondHoe.getIT());

                          villagerLeather.lockUnlockFunction(diamondHoe.getIT());
                          leatherHelmet.lockUnlockFunction(diamondHoe.getIT());
                          leatherLegins.lockUnlockFunction(diamondHoe.getIT());
                          leatherBoots.lockUnlockFunction(diamondHoe.getIT());
                          villagerSegant.lockUnlockFunction(diamondHoe.getIT());
                          villagerSoldat.lockUnlockFunction(diamondHoe.getIT());
                          villagerLetherSoldat.lockUnlockFunction(diamondHoe.getIT());
                          storogkaUpdate.lockUnlockFunction(diamondHoe.getIT());
                          ironBlock.lockUnlockFunction(diamondHoe.getIT());
                          anvil.lockUnlockFunction(diamondHoe.getIT());
                          villagerAutosmeltUpdte.lockUnlockFunction(diamondHoe.getIT());
                          ironHelmet.lockUnlockFunction(diamondHoe.getIT());
                          ironChestplate.lockUnlockFunction(diamondHoe.getIT());
                          ironLegins.lockUnlockFunction(diamondHoe.getIT());
                          ironBoots.lockUnlockFunction(diamondHoe.getIT());
                          ironArmor.lockUnlockFunction(diamondHoe.getIT());
                          villagerIronSoldat.lockUnlockFunction(diamondHoe.getIT());
                          villagerArowmaker.lockUnlockFunction(diamondHoe.getIT());
                          villagerBow.lockUnlockFunction(diamondHoe.getIT());
                          villagerFireTeam.lockUnlockFunction(diamondHoe.getIT());
                          ladder.lockUnlockFunction(diamondHoe.getIT());
                          tower.lockUnlockFunction(diamondHoe.getIT());
                          towerAttak.lockUnlockFunction(diamondHoe.getIT());
                          towerUpdate.lockUnlockFunction(diamondHoe.getIT());
                          towerBest.lockUnlockFunction(diamondHoe.getIT());
                          towerBestDownload.lockUnlockFunction(diamondHoe.getIT());
                          villagerPrezident.lockUnlockFunction(diamondHoe.getIT());
                          villagerWorkerGold.lockUnlockFunction(diamondHoe.getIT());
                          villagerWorker.lockUnlockFunction(diamondHoe.getIT());
                          villagerUpgrageSergant.lockUnlockFunction(diamondHoe.getIT());
                          villagerSimpleAuto.lockUnlockFunction(diamondHoe.getIT());
                          villagerSoldatAuto.lockUnlockFunction(diamondHoe.getIT());
                          goldHelmet.lockUnlockFunction(diamondHoe.getIT());
                          goldChestplate.lockUnlockFunction(diamondHoe.getIT());
                          goldLegins.lockUnlockFunction(diamondHoe.getIT());
                          goldBoots.lockUnlockFunction(diamondHoe.getIT());
                          goldArmor.lockUnlockFunction(diamondHoe.getIT());
                          villagerGold.lockUnlockFunction(diamondHoe.getIT());
                          wallTower.lockUnlockFunction(diamondHoe.getIT());
                          wallTowerUpgrade.lockUnlockFunction(diamondHoe.getIT());
                          fort.lockUnlockFunction(diamondHoe.getIT());
                          fortDownload.lockUnlockFunction(diamondHoe.getIT());
                          wheatBlock.lockUnlockFunction(diamondHoe.getIT());
                          zaborCopy.lockUnlockFunction(diamondHoe.getIT());
                          zaborUpdateCopy.lockUnlockFunction(diamondHoe.getIT());
                          twoTimeHalfRoofCopy.lockUnlockFunction(diamondHoe.getIT());
                          roofCopy.lockUnlockFunction(diamondHoe.getIT());
                          stoilo.lockUnlockFunction(diamondHoe.getIT());
                          staini.lockUnlockFunction(diamondHoe.getIT());
                          lead.lockUnlockFunction(diamondHoe.getIT());
                          villagerHorseTrener.lockUnlockFunction(diamondHoe.getIT());
                          saddle.lockUnlockFunction(diamondHoe.getIT());
                          tamedHorse.lockUnlockFunction(diamondHoe.getIT());
                          homeCopy.lockUnlockFunction(diamondHoe.getIT());
                          doubleHouse.lockUnlockFunction(diamondHoe.getIT());
                          castle.lockUnlockFunction(diamondHoe.getIT());
                          diamonSipitr.lockUnlockFunction(diamondHoe.getIT());
                          villagerKing.lockUnlockFunction(diamondHoe.getIT());
                          villagerWorkDiamond.lockUnlockFunction(diamondHoe.getIT());
                          diamondArmor.lockUnlockFunction(diamondHoe.getIT());
                          villagerDiamond.lockUnlockFunction(diamondHoe.getIT());
                          diamondblock.lockUnlockFunction(diamondHoe.getIT());
                          diamondHorse.lockUnlockFunction(diamondHoe.getIT());
                          wheahPool.lockUnlockFunction(diamondHoe.getIT());
                          towerThree.lockUnlockFunction(diamondHoe.getIT());
                          towerWall.lockUnlockFunction(diamondHoe.getIT());
                          castleBest.lockUnlockFunction(diamondHoe.getIT());
                          castleBestDownload.lockUnlockFunction(diamondHoe.getIT());
                          bow.lockUnlockFunction(diamondHoe.getIT());

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public static ObjectInitializer getInstance(Context context, GridLayout gridLayout, ViewPager v){

        if (objectInitializer == null){
            objectInitializer = new ObjectInitializer(context,gridLayout,v);
        }
        return objectInitializer;
    }


}
