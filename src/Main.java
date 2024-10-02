import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;
import processing.sound.*;

public class Main extends PApplet
{
    public static ArrayList<GameObject> engine;
    private final int MENU = 0;
    private final int PLAY = 1;
    private final int OVER = 2;
    private final int PAUSE = 3;
    private final int SCORES = 4;
    private final int MUSIC = 5;
    private int gameState;
    private int stage; // Split game into different stages
    public static int time;
    public static int score;
    private int loadingFrame;
    private int squareFrame;
    private int selection;

    private int y;
    private int transparency;
    private int bombTransparency;

    private int deathFrame;

    private PImage stageOneBG;
    private PImage spellCard;
    private PImage[] titles;
    private PImage[] menuOptions;
    private PImage[] loading;
    private PImage[] stageLoad;
    private PImage[] sidebar;
    public static PImage deathParticle;
    public static PImage[] numbers;
    public static PImage[] charOneImages;
    public static PImage[] ghosts;
    public static PImage[] blueFairies;
    public static PImage[] redFairy;
    public static PImage[] circleBG;
    public static PImage[] bullets;
    public static PImage[] drops;
    public static PImage[] deathBubble;
    public static PImage[] spawnIns;
    public static PImage[] circleBullets;

    public static PImage balls;

    public static SoundFile click;
    public static SoundFile select;
    public static SoundFile death;
    public static SoundFile bleep;
    public static SoundFile boom;
    private SoundFile menuTheme;
    private SoundFile bombSound;

    // Change to just stageTheme and stageBossTheme (load respective files during intervals)
    private SoundFile stageOneTheme;
    private SoundFile stageOneBossTheme;
    private SoundFile scoreTheme;
    private SoundFile theClassic;

    private SoundFile[] OST;

    public static Player player;
    public static boolean upKey, downKey, leftKey, rightKey, zKey, xKey, shiftKey, escKey, backspace;

    private boolean musicPlaying;
    private boolean hasBombed;

    public static void main(String[] args)
    {
        PApplet.main("Main");
    }

    public void settings()
    {
        // Original is 640x400
        size(640,400); // Test whether FX2D works well
    }

    public void setup()
    {
        engine = new ArrayList<>(10000);
        imageMode(CORNER);
        rectMode(CORNER);

        loadingFrame = 0;
        squareFrame = 0;
        selection = 0;
        gameState = 0;

        deathFrame = 0;

        // Need to create reset method (toggle between menu and game)
        y = 368;
        transparency = 255;
        bombTransparency = 255;
        score = 00000000; // 8 decimal places

        click = new SoundFile(this, "data/click_effect.wav");
        select = new SoundFile(this, "data/menu_effect.wav");
        death = new SoundFile(this, "data/death_effect.wav");
        bombSound = new SoundFile(this, "data/bomb_effect.wav");
        bleep = new SoundFile(this, "data/bleep_effect.wav");
        boom = new SoundFile(this, "data/boom_effect.wav");


        menuTheme = new SoundFile(this, "data/01. Wondrous Tales of Romance ~ Mystic Square.wav");
        stageOneTheme = new SoundFile(this, "data/02. Dream Express.wav");
        stageOneBossTheme = new SoundFile(this, "data/03. Magic Formation ~ Magic Square.wav");
        scoreTheme = new SoundFile(this, "data/23. Soul's Resting Place.wav");
        theClassic = new SoundFile(this, "data/02. Eternal Shrine Maiden.wav");

        musicPlaying = false;
        hasBombed = false;

        // Character sprites
        loading = new PImage[9];
        for (int i = 0; i < loading.length; i++) {
            loading[i] = loadImage("resources/Balls/yin_yang_" + nf(i, 1) + ".png");
        }
        stageLoad = new PImage[8];
        for (int i = 0; i < stageLoad.length; i++) {
            stageLoad[i] = loadImage("resources/Squares/Square_" + nf(i, 1) + ".png");
        }
        titles = new PImage[14];
        for (int i = 0; i < titles.length; i++) {
            titles[i] = loadImage("resources/Titles/MS_title_" + nf(i, 2) + ".png");
        }
        menuOptions = new PImage[5];
        for (int i = 0; i < menuOptions.length; i++) {
            menuOptions[i] = loadImage("resources/Selections/selection_" + nf(i, 1) + ".png");
        }
        charOneImages = new PImage[3];
        for (int i = 0; i < charOneImages.length; i++) {
            charOneImages[i] = loadImage("resources/Reimu/Sprite/Reimu_" + nf(i,1) + ".png");
        }
        bullets = new PImage[6];
        for (int i = 0; i < bullets.length; i++) {
            bullets[i] = loadImage("resources/Reimu/Hanafuda/Reimu_hanafuda_" + nf(i, 1) + ".png");
        }
        drops = new PImage[7];
        for (int i = 0; i < drops.length; i++) {
            drops[i] = loadImage("resources/Drops/Power_Item_" + nf(i, 1) + ".png");
        }
        deathBubble = new PImage[8];
        for (int i = 0; i < deathBubble.length; i++) {
            deathBubble[i] = loadImage("resources/Death/Death_" + nf(i, 1) + ".png");
        }
        sidebar = new PImage[13];
        for (int i = 0; i < sidebar.length; i++) {
            sidebar[i] = loadImage("resources/Sidebar/Score_Display_" + nf(i, 2) + ".png");
        }
        numbers = new PImage[10];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = loadImage("resources/Numbers/" + nf(i, 1) + "_16x16.png");
        }
        ghosts = new PImage[8];
        for (int i = 0; i < ghosts.length; i++) {
            ghosts[i] = loadImage("resources/Enemies/Ghost/Ghost_" + nf(i, 1) + ".png");
        }
        blueFairies = new PImage[12];
        for (int i = 0; i < blueFairies.length; i++) {
            blueFairies[i] = loadImage("resources/Enemies/Fairies/fairy_" + nf(i, 2) + ".png");
        }
        redFairy = new PImage[4];
        for (int i = 0; i < redFairy.length; i++) {
            redFairy[i] = loadImage("resources/Enemies/Red Fairy/Red_Fairy_" + nf(i, 1) + ".png");
        }
        spawnIns = new PImage[4];
        for (int i = 0; i < spawnIns.length; i++) {
            spawnIns[i] = loadImage("resources/Spawns/Spawn_" + nf(i, 1) + ".png");
        }
        circleBullets = new PImage[4];
        for (int i = 0; i < circleBullets.length; i++) {
            circleBullets[i] = loadImage("resources/Bullets/Circle_Bullet_" + nf(i, 1) + ".png");
        }
        circleBG = new PImage[8];
        for (int i = 0; i < circleBG.length; i++) {
            circleBG[i] = loadImage("resources/Circles/Circle_" + nf(i, 1) + ".png");
        }

        stageOneBG = loadImage("resources/Stages/Stage_One.png");
        balls = loadImage("resources/Reimu/Reimu_yin_yang.png");
        deathParticle = loadImage("resources/Reimu/Reimu_boom.png");
        spellCard = loadImage("resources/Reimu/Reimu_sc.png");

        player = new Player(this);
        engine.add(player);
        engine.add(new Launcher(this));
    }

    public void draw()
    {
        switch(gameState) {
            case MENU:
                gameMenu();
                break;
            case PLAY:
                gamePlay();
                break;
            case OVER:
                gameOver();
                break;
            case PAUSE:
                gamePaused();
                break;
            case SCORES:
                scoreBoard();
                break;
            case MUSIC:
                musicRoom();
                break;
        }
    }

    // Could add stage parameter (int)
    public void gameMenu()
    {
        // Loading animation
        if (loadingFrame < 8) {
            background(64, 0, 112);
            image(loading[loadingFrame], 0, 278);
        }
        if (frameCount % 8 == 0 && loadingFrame < 8) {
            loadingFrame++;
        }

        if (loadingFrame == 8) {
            // Display title screen
            image(titles[3], 0, 0);
            // Transition
            fill(0, transparency);
            rect(0, 0, width, height);

            image(loading[8], 474, 278);
            if (transparency == 0)
            {
                image(menuOptions[selection], 256, 250);
                if (!musicPlaying)
                {
                    menuTheme.loop();
                    musicPlaying = true;
                }
                // Controls menu selection
                if (zKey)
                {
                    if (selection == 0)
                    {
                        gameState = 1; // PLAY
                        menuTheme.stop();
                        musicPlaying = false;
                    }
                    else if (selection == 1)
                    {
                        gameState = 4; // SCORES
                        menuTheme.stop();
                        musicPlaying = false;
                    }
                    else if (selection == 2)
                    {
                        gameState = 5;
                        menuTheme.stop();
                        musicPlaying = false;
                    }
                    else if (selection == 3)
                    {

                    }
                    else if (selection == 4)
                    {
                        exit();
                    }
                }
            }
            if (transparency > 0) {
                transparency -= 5;
            }
        }
    }

    public void gamePlay()
    {
        //System.out.println(engine.size());

        background(0);

        fill(0, 255, 0);
        // 384x368, left offset +32, upper/bottom offset +-16
        copy(stageOneBG, 0, stageOneBG.height - y, 384, 368, 32, 16, 384, 368);

        // Amazing loading animation 10/10
        for (int x = 0; x < 24; x++) {
            for (int y = 0; y < 23; y++) {
                if (squareFrame < 8) {
                    image(stageLoad[squareFrame], 32 + x * 16, 16 + y * 16);
                }
            }
        }

        if (time % 10 == 0) {
            squareFrame++;
        }

        if (!musicPlaying) {
            stageOneTheme.loop();
            musicPlaying = true;
        }

        if (hasBombed) {
            fill(240, 240, 240);
            rect(32, 16, 384, 368);
            image(spellCard, 80, 16);

            if (bombTransparency > 0) {
                bombTransparency -= 5;
            }
        }

        if (bombTransparency == 0) {
            hasBombed = false;
            bombTransparency = 255;
        }

        int i = engine.size() - 1;
        while (i >= 0) {
            GameObject obj = engine.get(i);
            if (obj.hasDied()) {
                animateDeath(obj, i);
            }
            if (!obj.hasDied()) {
                obj.show();
                obj.act();
            }
            i--;
        }

        createSideMenu();

        // Might want to change
        if (time % 5 == 0) {
            if (y <= stageOneBG.height) {
                y += 2;
            }
        }

        if (backspace) {
            //gameState = PAUSE;
            gameState = 0;
            stageOneTheme.stop();
            musicPlaying = false;
        }

        time++;
    }

    public void gameOver()
    {
        image(titles[7], (float)width / 2, (float)height / 2);

    }

    public void gamePaused()
    {

    }

    public void scoreBoard()
    {
        image(titles[7], 0, 0);
        if (!musicPlaying) {
            scoreTheme.loop();
            musicPlaying = true;
        }

        if (backspace) {
            gameState = 0;
            scoreTheme.stop();
            musicPlaying = false;
        }
    }

    public void musicRoom()
    {
        image(titles[9], 0, 0);
        if (!musicPlaying) {
            theClassic.loop();
            musicPlaying = true;
        }

        if (backspace) {
            gameState = 0;
            theClassic.stop();
            musicPlaying = false;
        }
    }

    public void animateDeath(GameObject object, int index)
    {
        if (object instanceof Enemy) {
            if (deathFrame < 8) {
                image(deathBubble[deathFrame], object.getX(), object.getY());
            }
            if (time % 3 == 0) {
                deathFrame++;
            }

            if (deathFrame > 8) {
                engine.remove(index);
                deathFrame = 0;
            }
        }
        else {
            engine.remove(index);
        }
    }

    public void createSideMenu()
    {
        // Surrounding bars
        fill(0);
        noStroke();
        rect(0, 0, 31, height);
        rect(32, 0, 384, 15);
        rect(32, 384, 384, 16);
        // Scoreboard: numbers 16x16
        rect(416, 0, 224, 400);

        image(sidebar[0], 480, 48);     // HiScore
        image(sidebar[1], 488, 80);     // Score
        image(sidebar[2], 488, 128);    // Enemy
        image(sidebar[3], 457, 177);    // Bombs
        // Add bombs
        for (int j = 0; j < Player.bombs; j++) {
            image(sidebar[4], 496 + (16 * j), 177);
        }

        image(sidebar[5], 457, 210);    // Lives
        // Add lives
        for (int k = 0; k < Player.lives; k++) {
            image(sidebar[6], 496 + (16 * k), 209);
        }

        image(sidebar[7], 457, 241);    // Total Points
        image(sidebar[8], 465, 257);    // Stage Points
        image(sidebar[9], 466, 289);    // Power
        image(sidebar[10], 505, 305);   // Dream Bonus
        image(sidebar[11], 497, 338);   // Difficulty
        image(sidebar[12], 457, 369);   // Power Level
    }

    public void bomb()
    {
        bombSound.play();

        int i = engine.size() - 1;
        while (i >= 0)
        {
            GameObject obj = engine.get(i);
            if (obj instanceof Enemy || obj instanceof Bullet)
            {
                engine.remove(i);
            }
            i--;
        }
        Player.bombs--;
    }

    public void keyPressed()
    {
        if (keyCode == UP) {
            upKey = true;
            if (gameState == 0 && loadingFrame == 8) {
                select.play();
                selection--;
                if (selection < 0) {
                    selection = 4;
                }
            }
        }
        if (keyCode == DOWN) {
            downKey = true;
            if (gameState == 0) {
                select.play();
                selection++;
                if (selection > 4) {
                    selection = 0;
                }
            }
        }
        if (keyCode == LEFT) {
            leftKey = true;
        }
        if (keyCode == RIGHT) {
            rightKey = true;
        }
        if (key == 'z' || key == 'Z') {
            zKey = true;
            if (gameState != 1 && loadingFrame == 8) {
                click.play();
            }
        }
        if (key == 'x' || key == 'X') {
            xKey = true;
        }
        if (keyCode == SHIFT) {
            shiftKey = true;
        }
        if (keyCode == ESC) {
            escKey = true;
        }
        if (keyCode == BACKSPACE) {
            backspace = true;
        }
    }

    public void keyReleased()
    {
        if (keyCode == UP) {
            upKey = false;
        }
        if (keyCode == DOWN) {
            downKey = false;
        }
        if (keyCode == LEFT) {
            leftKey = false;
        }
        if (keyCode == RIGHT) {
            rightKey = false;
        }
        if (key == 'z' || key == 'Z') {
            zKey = false;
        }
        if (key == 'x' || key == 'X') {
            xKey = false;
            if (gameState == 1 && Player.bombs > 0) {
                bomb();
                hasBombed = true;
            }
        }
        if (keyCode == SHIFT) {
            shiftKey = false;
        }
        if (keyCode == ESC) {
            escKey = false;
        }
        if (keyCode == BACKSPACE) {
            backspace = false;
        }
    }
}
