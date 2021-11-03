package Game;

import java.awt.*;
import java.lang.String;
import javax.swing.*;
import java.awt.event.*;

import Textures.Textures;


public class Game extends JPanel implements Runnable {

    String map = Maps.m[Maps.lvl];
    String texturemap = Maps.texturem[Maps.lvl];
    private int FPS = 1000000000/120;
    private boolean run = false;
    private JFrame frame;
    private Thread thread = new Thread(this);
    private int rotate, srotate;
    private int SCALE;
    private int HEIGHT;
    private int WIDTH;
    private int timeU = 0;
    private int timeD = 0;
    private int money = 0;
    static int tDeath = 180;
    static int death = tDeath+1;
    static boolean up = false;
    static int mapsMove = 0;
    private static Point p;
    static boolean play = false;
    private static int x = 0,y = 0;
    private int animated = 0;
    private boolean ball = false;
    private Textures t = new Textures();

    public void run() {
        double time = System.nanoTime();
        double now;
        double dif = 1;
        while (true) {
            dif--;
            while (true) {
                now = System.nanoTime();
                dif += (now - time)/FPS;
                time = now;
                if (dif >= 1) {
                    if (play) {
                        init();
                    }
                    if (death < tDeath) death++;
                    if (death == tDeath) {
                        respawn();
                        death++;
                    }
                    repaint();
                    break;
                }
            }
        }
    }

    public void init() {
        if (!KeyBoard.up && !KeyBoard.down && (Maps.m[Maps.lvl].charAt(x/SCALE + (y-(SCALE-5)/30)/SCALE * Maps.m[Maps.lvl].length()/Maps.mHeight) != 'W' ||
                Maps.m[Maps.lvl].charAt((x+(SCALE-5))/SCALE + (y-(SCALE-5)/30)/SCALE * Maps.m[Maps.lvl].length()/Maps.mHeight) != 'W')) KeyBoard.down = true;
        if (KeyBoard.right) {
            if (!OptionBlock('R')) {
                int l = 0;
                if (Maps.mHeight == 12) l = 42;
                else if (Maps.mHeight == 8) l = 700;
                if (x > WIDTH / 2 - SCALE - 5 && mapsMove > -l -((Maps.m[Maps.lvl].length()/Maps.mHeight - 22) * SCALE) + SCALE/30) mapsMove -= SCALE / 30;
                x += SCALE / 30;
            }
        }
        if (KeyBoard.left) {
            if (!OptionBlock('L')) {
                if (mapsMove < 0 && x < Maps.m[Maps.lvl].length()/Maps.mHeight * SCALE - WIDTH/2) mapsMove += SCALE / 30;
                x -= SCALE / 30;
            }
        }
        if (KeyBoard.up && !KeyBoard.down) {
            if (!OptionBlock('U')) {
                timeU++;
                y -= (int) (SCALE/10.2 - ((float) SCALE/8160)*timeU*timeU/2);
                if ((int) (SCALE/10.2 -((float) SCALE/8160)*timeU*timeU/2) <= 1) {
                    timeU = 1;
                    KeyBoard.up = false;
                    KeyBoard.down = true;
                }
            }
        }
        if (KeyBoard.down) {
            if (!OptionBlock('D')) {
                timeD++;
                y += SCALE/90 + (int) (((float) SCALE/8160)*timeD*timeD/2);
            }
        }

        if (up && OptionBlock('O')) {
            ball = true;
            animated = 0;
            KeyBoard.up = true;
            KeyBoard.down = false;
            timeU = 0;
            timeD = 0;
        } else {
            up = false;
        }
        if (
                Maps.m[Maps.lvl].charAt(x/SCALE + (y + SCALE - 5 - SCALE/2)/SCALE * Maps.m[Maps.lvl].length()/Maps.mHeight) == 'E' ||
                Maps.m[Maps.lvl].charAt((x+SCALE)/SCALE + (y + SCALE - 5 - SCALE/2)/SCALE * Maps.m[Maps.lvl].length()/Maps.mHeight) == 'E'
        ) {
            death();
        }
        try {
            if (KeyBoard.E) {
                keys('Z');
                keys('X');
                keys('C');
                door(0);
            }
            if (Maps.m[Maps.lvl].charAt(x / SCALE + y / SCALE * Maps.m[Maps.lvl].length() / Maps.mHeight) == 'm') {
                Maps.MapChange(' ', x / SCALE + y / SCALE * Maps.m[Maps.lvl].length() / Maps.mHeight);
                money++;
            } else if (Maps.m[Maps.lvl].charAt((x + SCALE-5) / SCALE + y / SCALE * Maps.m[Maps.lvl].length() / Maps.mHeight) == 'm') {
                Maps.MapChange(' ',(x + SCALE - 5) / SCALE + y / SCALE * Maps.m[Maps.lvl].length() / Maps.mHeight);
                money++;
            } else if (Maps.m[Maps.lvl].charAt(x / SCALE + (y + SCALE- 5) / SCALE * Maps.m[Maps.lvl].length() / Maps.mHeight) == 'm') {
                Maps.MapChange(' ', x / SCALE + (y + SCALE- 5) / SCALE * Maps.m[Maps.lvl].length() / Maps.mHeight);
                money++;
            } else if (Maps.m[Maps.lvl].charAt((x+SCALE - 5) / SCALE + (y + SCALE - 5) / SCALE * Maps.m[Maps.lvl].length() / Maps.mHeight) == 'm') {
                Maps.MapChange(' ', (x+SCALE-5) / SCALE + (y + SCALE - 5) / SCALE * Maps.m[Maps.lvl].length() / Maps.mHeight);
                money++;
            }
        } catch (Exception e) {
            OptionBlock('D');
        }
    }

    public static boolean redk = false;
    public boolean bluek = false;
    public boolean orangek = false;
    public static boolean redd = false;
    public boolean blued = false;
    public boolean oranged = false;

    public void keys(char a) {
        if (Maps.texturem[Maps.lvl].charAt(x / SCALE + y / SCALE * Maps.texturem[Maps.lvl].length() / Maps.mHeight) == a) {
            switch (a) {
                case 'Z':
                    Maps.MapChange(' ', x / SCALE + y / SCALE * Maps.m[Maps.lvl].length() / Maps.mHeight);
                    redk = true;
                    break;

                case 'X':
                    Maps.MapChange(' ', x / SCALE + y / SCALE * Maps.m[Maps.lvl].length() / Maps.mHeight);
                    bluek = true;
                    break;

                case 'C':
                    Maps.MapChange(' ', x / SCALE + y / SCALE * Maps.m[Maps.lvl].length() / Maps.mHeight);
                    orangek = true;
                    break;
                case 'R':
                    redd = true;
                    break;
            }
        }
        else if (Maps.texturem[Maps.lvl].charAt((x+SCALE - 5) / SCALE + (y + SCALE - 5) / SCALE * Maps.texturem[Maps.lvl].length() / Maps.mHeight) == a) {
            switch (a) {
                case 'Z':
                    Maps.MapChange(' ', (x+SCALE-5) / SCALE + (y + SCALE - 5) / SCALE * Maps.m[Maps.lvl].length() / Maps.mHeight);
                    redk = true;
                    break;

                case 'X':
                    Maps.MapChange(' ', (x+SCALE-5) / SCALE + (y + SCALE - 5) / SCALE * Maps.m[Maps.lvl].length() / Maps.mHeight);
                    bluek = true;
                    break;

                case 'C':
                    Maps.MapChange(' ', (x+SCALE-5) / SCALE + (y + SCALE - 5) / SCALE * Maps.m[Maps.lvl].length() / Maps.mHeight);
                    orangek = true;
                    break;
                case 'R':
                    redd = true;
                    break;
            }
        }
    }

    public void door(int a) {
        keys('R');
        if (redk && redd) {
            Maps.lvl++;
            map = Maps.m[Maps.lvl];
            texturemap = Maps.texturem[Maps.lvl];
            respawn();
            play = false;
        } else redd = false;
        if (oranged && orangek) {
            if (a == 2) {
                Maps.MapChange(' ', (x - SCALE / 2) / SCALE + (y + SCALE / 2) / SCALE * Maps.m[Maps.lvl].length() / Maps.mHeight);
                orangek = false;
            }
            if (a == 1) {
                Maps.MapChange(' ', (x + (SCALE - 5) + SCALE / 2) / SCALE + (y + SCALE / 2) / SCALE * Maps.m[Maps.lvl].length() / Maps.mHeight);
                orangek = false;
            }
        } else oranged = false;
    }

    public boolean OptionBlock(char a) {
        try {
            if (a == 'L')
                if (Maps.m[Maps.lvl].charAt((x - (SCALE - 5) / 30) / SCALE + y / SCALE * Maps.m[Maps.lvl].length() / Maps.mHeight) == 'W' || Maps.m[Maps.lvl].charAt((x - (SCALE - 5) / 30) / SCALE + (y + (SCALE - 5)) / SCALE * Maps.m[Maps.lvl].length() / Maps.mHeight) == 'W' || x < 0) {
                    if (Maps.texturem[Maps.lvl].charAt((x - (SCALE - 5) / 30) / SCALE + y / SCALE * Maps.texturem[Maps.lvl].length() / Maps.mHeight) == 'O') {
                        oranged = true;
                        door(2);
                    } else oranged = false;
                    return true;
                }
            if (a == 'R')
                if (Maps.m[Maps.lvl].charAt((x + 31 * (SCALE - 5) / 30) / SCALE + y / SCALE * Maps.m[Maps.lvl].length() / Maps.mHeight) == 'W' || Maps.m[Maps.lvl].charAt((x + 31 * (SCALE - 5) / 30) / SCALE + (y + (SCALE - 5)) / SCALE * Maps.m[Maps.lvl].length() / Maps.mHeight) == 'W' || x + 31 * (SCALE - 5) / 30 + 1 > Maps.m[Maps.lvl].length() / Maps.mHeight * SCALE) {
                    if (Maps.texturem[Maps.lvl].charAt((x + 31 * (SCALE - 5) / 30) / SCALE + y / SCALE * Maps.texturem[Maps.lvl].length() / Maps.mHeight) == 'O') {
                        oranged = true;
                        door(1);
                    } else oranged = false;
                    return true;
                }
            if (a == 'U')
                if (Maps.m[Maps.lvl].charAt(x / SCALE + (y - (SCALE - 5) / 30) / SCALE * Maps.m[Maps.lvl].length() / Maps.mHeight) == 'W' || Maps.m[Maps.lvl].charAt((x + (SCALE - 5)) / SCALE + (y - (SCALE - 5) / 30) / SCALE * Maps.m[Maps.lvl].length() / Maps.mHeight) == 'W') {
                    timeU = 1;
                    KeyBoard.up = false;
                    KeyBoard.down = true;
                    return true;
                }
            if (a == 'D')
                if (Maps.m[Maps.lvl].charAt(x / SCALE + ((y + 1 + (int) (((float) SCALE / 8160) * (timeD + 1) * (timeD + 1) / 2)) + SCALE) / SCALE * Maps.m[Maps.lvl].length() / Maps.mHeight) == 'W' || Maps.m[Maps.lvl].charAt((x + (SCALE - 5)) / SCALE + ((y + 1 + (int) (((float) SCALE / 8160) * (timeD + 1) * (timeD + 1) / 2)) + SCALE) / SCALE * Maps.m[Maps.lvl].length() / Maps.mHeight) == 'W') {
                    timeD = 1;
                    y = 4 + (y + SCALE - 5) / SCALE * SCALE;
                    KeyBoard.down = false;
                    return true;
                }
            if (a == 'O')
                if (
                        Maps.m[Maps.lvl].charAt(x/SCALE + y/SCALE * Maps.m[Maps.lvl].length()/Maps.mHeight) == 'D' ||
                        Maps.m[Maps.lvl].charAt(x/SCALE + (y + SCALE - 5)/SCALE * Maps.m[Maps.lvl].length()/Maps.mHeight) == 'D' ||
                        Maps.m[Maps.lvl].charAt((x + SCALE - 5)/SCALE + y/SCALE * Maps.m[Maps.lvl].length()/Maps.mHeight) == 'D' ||
                        Maps.m[Maps.lvl].charAt((x + SCALE - 5)/SCALE + (y + SCALE - 5)/SCALE * Maps.m[Maps.lvl].length()/Maps.mHeight) == 'D'
                ) return true;
        } catch (Exception e) {
            respawn();
        }
        return false;
    }

    public void death() {
        play = false;
        death = 0;
    }

    public void respawn() {
        Maps.MapChange(map, 1);
        Maps.MapChange(texturemap, 2);
        KeyBoard.up = false;
        KeyBoard.down = false;
        KeyBoard.right = false;
        KeyBoard.left = false;
        if (Maps.lvl == 1) {
            Maps.mHeight = 12;
            SCALE = HEIGHT/Maps.mHeight;
        } else {
            Maps.mHeight = 8;
            SCALE = HEIGHT/Maps.mHeight;
        }
        redk = false;
        bluek = false;
        orangek = false;
        money = 0;
        timeU = 1;
        timeD = 1;
        x = 0;
        y = 0;
        mapsMove = 0;
        play = true;
        repaint();
    }

    public void start() {
        if (run) return;
        run = true;
        play = true;
        thread.start();
        repaint();
    }

    public Game(JFrame frame) {
        this.frame = frame;
        HEIGHT = frame.getHeight();
        WIDTH = frame.getWidth();
        SCALE = HEIGHT/Maps.mHeight;
        addMouseListener(new Mouse());
        addMouseMotionListener(new Mouse());
        addKeyListener(new KeyBoard());
        setFocusable(true);
    }

    public void paint(Graphics g) {
        g.setColor(new Color(71,45,60));
        g.fillRect(0,0,WIDTH,HEIGHT);

//        Game
        if (play || death < tDeath) {
//            if (p != null) {
//                g.setColor(Color.CYAN);
//                g.fillRect((p.x-mapsMove)/SCALE*SCALE + mapsMove,p.y/SCALE*SCALE,SCALE,SCALE);
//            }
                if (ball) {
                    animated+=6;
                    if (animated == 120) {
                        ball = false;
                        animated = 0;
                    }
                } else {
                    animated ++;
                    if (animated == 60) animated = 0;
                }
            for (int y = 0; y < Maps.mHeight; y++) {
                for (int x = 0; x < Maps.texturem[Maps.lvl].length() / Maps.mHeight; x++) {
                    switch (Maps.texturem[Maps.lvl].charAt(x + y * Maps.m[Maps.lvl].length() / Maps.mHeight)) {
                        case '1':
                        g.setColor(Color.GREEN);
                        g.fillRect(x * SCALE + mapsMove, y * SCALE, SCALE, SCALE);
                        g.drawImage(t.grass[0],x * SCALE + mapsMove, y * SCALE, SCALE, SCALE,null);
                        break;
                        case '2':
                            g.setColor(Color.GREEN);
                            g.fillRect(x * SCALE + mapsMove, y * SCALE, SCALE, SCALE);
                            g.drawImage(t.grass[2 - 1],x * SCALE + mapsMove, y * SCALE, SCALE, SCALE,null);
                            break;
                        case '3':
                            g.setColor(Color.GREEN);
                            g.fillRect(x * SCALE + mapsMove, y * SCALE, SCALE, SCALE);
                            g.drawImage(t.grass[3 - 1],x * SCALE + mapsMove, y * SCALE, SCALE, SCALE,null);
                            break;
                        case '4':
                            g.setColor(Color.GREEN);
                            g.fillRect(x * SCALE + mapsMove, y * SCALE, SCALE, SCALE);
                            g.drawImage(t.grass[4 - 1],x * SCALE + mapsMove, y * SCALE, SCALE, SCALE,null);
                            break;
                        case '5':
                            g.setColor(Color.GREEN);
                            g.fillRect(x * SCALE + mapsMove, y * SCALE, SCALE, SCALE);
                            g.drawImage(t.grass[5 - 1],x * SCALE + mapsMove, y * SCALE, SCALE, SCALE,null);
                            break;
                        case '6':
                            g.setColor(Color.GREEN);
                            g.fillRect(x * SCALE + mapsMove, y * SCALE, SCALE, SCALE);
                            g.drawImage(t.grass[6 - 1],x * SCALE + mapsMove, y * SCALE, SCALE, SCALE,null);
                            break;
                        case '7':
                            g.setColor(Color.GREEN);
                            g.fillRect(x * SCALE + mapsMove, y * SCALE, SCALE, SCALE);
                            g.drawImage(t.grass[7 - 1],x * SCALE + mapsMove, y * SCALE, SCALE, SCALE,null);
                            break;
                        case '8':
                            g.setColor(Color.GREEN);
                            g.fillRect(x * SCALE + mapsMove, y * SCALE, SCALE, SCALE);
                            g.drawImage(t.grass[8 - 1],x * SCALE + mapsMove, y * SCALE, SCALE, SCALE,null);
                            break;
                        case '9':
                            g.setColor(Color.GREEN);
                            g.fillRect(x * SCALE + mapsMove, y * SCALE, SCALE, SCALE);
                            g.drawImage(t.grass[9 - 1],x * SCALE + mapsMove, y * SCALE, SCALE, SCALE,null);
                            break;
                        case 'a':
                            g.setColor(Color.GREEN);
                            g.fillRect(x * SCALE + mapsMove, y * SCALE, SCALE, SCALE);
                            g.drawImage(t.grass[9],x * SCALE + mapsMove, y * SCALE, SCALE, SCALE,null);
                            break;
                        case 's':
                            g.setColor(Color.GREEN);
                            g.fillRect(x * SCALE + mapsMove, y * SCALE, SCALE, SCALE);
                            g.drawImage(t.grass[10],x * SCALE + mapsMove, y * SCALE, SCALE, SCALE,null);
                            break;
                        case 'd':
                            g.setColor(Color.GREEN);
                            g.fillRect(x * SCALE + mapsMove, y * SCALE, SCALE, SCALE);
                            g.drawImage(t.grass[11],x * SCALE + mapsMove, y * SCALE, SCALE, SCALE,null);
                            break;
                        case 'f':
                            g.setColor(Color.GREEN);
                            g.fillRect(x * SCALE + mapsMove, y * SCALE, SCALE, SCALE);
                            g.drawImage(t.grass[12],x * SCALE + mapsMove, y * SCALE, SCALE, SCALE,null);
                            break;
                        case 'k':
                            g.setColor(Color.GREEN);
                            g.fillRect(x * SCALE + mapsMove, y * SCALE, SCALE, SCALE);
                            g.drawImage(t.grass[13],x * SCALE + mapsMove, y * SCALE, SCALE, SCALE,null);
                            break;
                        case ',':
                            g.setColor(Color.GREEN);
                            g.fillRect(x * SCALE + mapsMove, y * SCALE, SCALE, SCALE);
                            g.drawImage(t.grass[14],x * SCALE + mapsMove, y * SCALE, SCALE, SCALE,null);
                            break;
                        case 'l':
                            g.setColor(Color.GREEN);
                            g.fillRect(x * SCALE + mapsMove, y * SCALE, SCALE, SCALE);
                            g.drawImage(t.grass[15],x * SCALE + mapsMove, y * SCALE, SCALE, SCALE,null);
                            break;
                        case 'o':
                            g.setColor(Color.GREEN);
                            g.fillRect(x * SCALE + mapsMove, y * SCALE, SCALE, SCALE);
                            g.drawImage(t.grass[16],x * SCALE + mapsMove, y * SCALE, SCALE, SCALE,null);
                            break;
                        case 'r':
                            g.setColor(Color.GREEN);
                            g.fillRect(x * SCALE + mapsMove, y * SCALE, SCALE, SCALE);
                            g.drawImage(t.decor[0],x * SCALE + mapsMove, y * SCALE, SCALE, SCALE,null);
                            break;
                        case 't':
                            g.setColor(Color.GREEN);
                            g.fillRect(x * SCALE + mapsMove, y * SCALE, SCALE, SCALE);
                            g.drawImage(t.decor[1],x * SCALE + mapsMove, y * SCALE, SCALE, SCALE,null);
                            break;
                        case 'y':
                            g.setColor(Color.GREEN);
                            g.fillRect(x * SCALE + mapsMove, y * SCALE, SCALE, SCALE);
                            g.drawImage(t.decor[2],x * SCALE + mapsMove, y * SCALE, SCALE, SCALE,null);
                            break;
                        case 'g':
                            g.setColor(Color.GREEN);
                            g.fillRect(x * SCALE + mapsMove, y * SCALE, SCALE, SCALE);
                            g.drawImage(t.decor[3],x * SCALE + mapsMove, y * SCALE, SCALE, SCALE,null);
                            break;
                        case 'b':
                            g.setColor(Color.GREEN);
                            g.fillRect(x * SCALE + mapsMove, y * SCALE, SCALE, SCALE);
                            g.drawImage(t.decor[4],x * SCALE + mapsMove, y * SCALE, SCALE, SCALE,null);
                            break;
                        case 'h':
                            g.setColor(Color.GREEN);
                            g.fillRect(x * SCALE + mapsMove, y * SCALE, SCALE, SCALE);
                            g.drawImage(t.decor[5],x * SCALE + mapsMove, y * SCALE, SCALE, SCALE,null);
                            break;
                        case 'n':
                            g.setColor(Color.GREEN);
                            g.fillRect(x * SCALE + mapsMove, y * SCALE, SCALE, SCALE);
                            g.drawImage(t.decor[6],x * SCALE + mapsMove, y * SCALE, SCALE, SCALE,null);
                            break;
                        case 'R':
                            g.setColor(Color.GREEN);
                            g.fillRect(x * SCALE + mapsMove, y * SCALE, SCALE, SCALE);
                            g.drawImage(t.door[0],x * SCALE + mapsMove, y * SCALE, SCALE, SCALE,null);
                            break;
                        case 'B':
                            g.setColor(Color.GREEN);
                            g.fillRect(x * SCALE + mapsMove, y * SCALE, SCALE, SCALE);
                            g.drawImage(t.door[1],x * SCALE + mapsMove, y * SCALE, SCALE, SCALE,null);
                            break;
                        case 'O':
                            g.setColor(Color.GREEN);
                            g.fillRect(x * SCALE + mapsMove, y * SCALE, SCALE, SCALE);
                            g.drawImage(t.door[2],x * SCALE + mapsMove, y * SCALE, SCALE, SCALE,null);
                            break;
                        case 'Z':
                            g.setColor(Color.GREEN);
                            g.fillRect(x * SCALE + mapsMove, y * SCALE, SCALE, SCALE);
                            g.drawImage(t.key[0],x * SCALE + mapsMove, y * SCALE, SCALE, SCALE,null);
                            break;
                        case 'X':
                            g.setColor(Color.GREEN);
                            g.fillRect(x * SCALE + mapsMove, y * SCALE, SCALE, SCALE);
                            g.drawImage(t.key[1],x * SCALE + mapsMove, y * SCALE, SCALE, SCALE,null);
                            break;
                        case 'C':
                            g.setColor(Color.GREEN);
                            g.fillRect(x * SCALE + mapsMove, y * SCALE, SCALE, SCALE);
                            g.drawImage(t.key[2],x * SCALE + mapsMove, y * SCALE, SCALE, SCALE,null);
                            break;
                        case 'D':
                        g.setColor(Color.CYAN);
                        g.drawImage(t.djump[animated/30],x * SCALE + mapsMove, y * SCALE, SCALE, SCALE,null);
                        break;
                        case 'E':
                            g.setColor(Color.RED);
                            g.drawImage(t.death[0],x*SCALE+mapsMove, 2+y * SCALE, SCALE, SCALE,null);
                        break;
                        case 'm':
                            g.setColor(Color.WHITE);
                            g.drawImage(t.money,x*SCALE+mapsMove, y * SCALE,SCALE,SCALE,null);
                        break;
                    }
                }
            }

//            Score
            String score = "" + money;
            g.drawImage(t.money, 0, 0, SCALE/2, SCALE/2, null);
            for (int i = 0; i < score.length(); i++) {
                int a = Integer.parseInt("" + score.charAt(i));
                g.drawImage(t.numbers[a], SCALE/2 * i + SCALE/2, 0, SCALE/2, SCALE/2, null);
            }

//            key
            if (redk) g.drawImage(t.key[0], 0, SCALE/2, SCALE/2, SCALE/2, null);
            if (bluek)  g.drawImage(t.key[1], 0, 2*SCALE/2, SCALE/2, SCALE/2, null);
            if (orangek) g.drawImage(t.key[2], 0, 3*SCALE/2, SCALE/2, SCALE/2, null);

//            Character
//            g.setColor(Color.ORANGE);
//            g.fillRect(x+mapsMove, y+1, SCALE-5,SCALE-5);
            if (rotate == 59) rotate = 0;
            if (death < tDeath) g.drawImage(t.cHar[5],x+mapsMove + (SCALE-5)*srotate, y+1, SCALE-5 - 2*(SCALE - 5)*srotate,SCALE-5,null);
            else if (KeyBoard.up) {
                if (KeyBoard.right) srotate = 0;
                if (KeyBoard.left) srotate = 1;
                g.drawImage(t.cHar[3],x+mapsMove + (SCALE-5)*srotate, y+1, SCALE-5 - 2*(SCALE - 5)*srotate,SCALE-5,null);
            }
            else if (KeyBoard.down) {
                if (KeyBoard.right) srotate = 0;
                if (KeyBoard.left) srotate = 1;
                g.drawImage(t.cHar[4],x+mapsMove + (SCALE-5)*srotate, y+1, SCALE-5 - 2*(SCALE - 5)*srotate,SCALE-5,null);
            }
            else if (KeyBoard.left) {
                rotate++;
                g.drawImage(t.cHar[rotate/30 + 1],x+mapsMove + SCALE-5, y+1, -1*(SCALE-5),SCALE-5,null);
                srotate = 1;
            }
            else if (KeyBoard.right) {
                rotate++;
                g.drawImage(t.cHar[rotate/30 + 1],x+mapsMove, y+1, (SCALE-5),SCALE-5,null);
                srotate = 0;
            }
            else {
                g.drawImage(t.cHar[0],x+mapsMove + (SCALE-5)*srotate, y+1, SCALE-5 - 2*(SCALE - 5)*srotate,SCALE-5,null);
            }
        }

//        MainMenu
        if (p != null && (!play && death > tDeath)) {
            g.setColor(new Color(207,198,184));
            g.drawImage(t.menu[2],0,0,WIDTH,HEIGHT,null);
//            g.drawLine(0,0,p.x,p.y);

            if ((p.x < WIDTH/3 || p.y < HEIGHT/3 + HEIGHT/10) || (p.x > (WIDTH*2)/3 || p.y > HEIGHT/3 + 2 * HEIGHT/10)) {
                g.setColor(new Color(207, 198, 184));
            } else g.setColor(Color.ORANGE);
                g.fillRect(WIDTH / 3, HEIGHT / 3 + HEIGHT / 10, WIDTH / 3, HEIGHT / 10);
                g.drawImage(t.menu[0],WIDTH / 3, HEIGHT / 3 + HEIGHT / 10, WIDTH / 3, HEIGHT / 10,null);

            if ((p.x < WIDTH/3 || p.y < HEIGHT/3 + 2 * HEIGHT/10) || (p.x > (WIDTH*2)/3 ||
                    p.y > HEIGHT/3 + 3 * HEIGHT/10)) {
                g.setColor(new Color(207, 198, 184));
            } else g.setColor(Color.ORANGE);
            g.fillRect(WIDTH/3,HEIGHT/3 + 2 * HEIGHT/10,WIDTH/3,HEIGHT/10);
            g.drawImage(t.menu[1],WIDTH/3,HEIGHT/3 + 2 * HEIGHT/10,WIDTH/3,HEIGHT/10,null);
        }
    }

    class Mouse extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            if (play) return;
            p = e.getPoint();
            if ((p.x > WIDTH / 3 && p.y > HEIGHT / 3 + HEIGHT / 10) &&
                    (p.x < (WIDTH * 2) / 3 && p.y < HEIGHT / 3 + 2 * HEIGHT / 10))
                if (!run)
                start();
                else {
                    play = true;
                }
            if ((p.x > WIDTH / 3 && p.y > HEIGHT / 3 + 2 * HEIGHT / 10) &&
                    (p.x < (WIDTH * 2) / 3 && p.y < HEIGHT / 3 + 3 * HEIGHT / 10))
                System.exit(0);
        }

        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            if (play) return;
            p = null;
        }

        public void mouseMoved(MouseEvent e) {
            super.mouseMoved(e);
            if (play) {
                p = e.getPoint();
                repaint();
                return;
            }
            p = e.getPoint();
            repaint();
        }
    }
}
