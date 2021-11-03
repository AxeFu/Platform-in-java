package Game;

import java.lang.String;

public class Maps {

    public static int mHeight = 8;
    public static int lvl = 0;
    public static void MapChange(char a, int b) {
        String map = "";
        String texturemap = "";
        for (int i = 0; i < m[lvl].length(); i++) {
            if (i == b) {
                map += a;
            } else map += m[lvl].charAt(i);
            if (i == b) {
                texturemap += a;
            } else texturemap += texturem[lvl].charAt(i);
        }
        m[lvl] = map;
        texturem[lvl] = texturemap;
    }

    public static void MapChange(String map, int a) {
        if (a == 1)
            m[lvl] = map;
        else if (a == 2) texturem[lvl] = map;
    }

    public static String[] m = new String[]{
                    "                         WWWWWW" +
                    "                              W" +
                    "                      m  m    W" +
                    "       WW  mmm                W" +
                    "                         WW    " +
                    " m  D    WW   WW      D  WW m  " +
                    "                         WW D W" +
                    "WWWEEEEEEEEEEEEEEEWWWWWWWWWEEEW",

                    "   mW                                     WWWWWW       " +
                    "    W         m              m            W    W       " +
                    "    W                                     W    W       " +
                    "WWWWW                               m     WW   W       " +
                    "    W   WDDW        WWW  D   D            W    W       " +
                    "    W   W mW    D     W          D            WW       " +
                    "    W   WDWW          WEEEEEEEEEEWWWWWWWWWWWWWWW       " +
                    "    W           mm                             W       " +
                    "    W   WWWW    WW                        WWWWWW       " +
                    "    W D mmmW D      D   m    m    m     m WW   W       " +
                    "    W   WmmWEEEEEEEEE                     WW          m" +
                    "WWWWWEEEWWWWWWWWWWWWWWWEEEWWEEEWWEEEWWWWEWWWWWWWWWWWWWW",

                    "                                "+
                    "                                "+
                    "                                "+
                    "                                "+
                    "                                "+
                    "                                "+
                    "                                "+
                    "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"
    };

    public static String[] texturem = new String[] {
                    "                   t     122225" +
                    "                   t          4" +
                    "                   t  m  mh   4" +
                    "       ad  mmm     t      n   1" +
                    "          h   y    r     79    " +
                    " mg D    ad   ad   t  D  46 m Z" +
                    "yRb                t   y 46 D 7" +
                    "889EEEEEEEEEEEEEEE788888856EEE5",

                    "   m4   t                        t        788889    t  " +
                    "    1   t     m              m   t        4 C  6    t  " +
                    " yC O   rC           h           r        4    6    t  " +
                    "88889   t y          n           t  m     45   6    t  " +
                    "    6   7889        as9  D   D   t        1    6    t  " +
                    "  5 6   4 m6    D     6          t   y   h    56    t  " +
                    "    6   1D23          6EEEEEEEEEE7sssssssd122223    t  " +
                    "    6    h y    mm                                  r  " +
                    " 5  6   7889    ad                        788889    t  " +
                    "    6 D 4 56 D      D   m  g m    m     m 122223    t  " +
                    "    6   4  6EEEEEEEEE      b         y    OO Z  h  Rt m" +
                    "22223EEE122388888888888EEE88EEE88EEE8888E88888888888888",

                    "                                "+
                    "                                "+
                    "                                "+
                    "                                "+
                    "                                "+
                    "                                "+
                    "                                "+
                    "88888888888888888888888888888888"
    };
}
