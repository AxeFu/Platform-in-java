package Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Game.Game;

public class KeyBoard extends KeyAdapter {
    public static boolean right = false;
    public static boolean left = false;
    public static boolean up = false;
    public static boolean down = true;
    public static boolean E = false;

    public KeyBoard() {
    }

    public void keyPressed(KeyEvent e) {
        if (Game.death < Game.tDeath && e.getKeyCode() == 10) {
            Game.death = Game.tDeath - 1;
        }

        if (Game.play) {
            if (e.getKeyCode() == KeyEvent.VK_NUMPAD8) {
                Game.redk = true;
                Game.redd = true;
            }

            if (e.getKeyCode() == 27) {
                Game.play = false;
            }

            if (e.getKeyCode() == 10) {
                E = true;
            }

            if (e.getKeyCode() == 39) {
                right = true;
            }

            if (e.getKeyCode() == 37) {
                left = true;
            }

            if (e.getKeyCode() == 38) {
                Game.up = true;
                up = true;
            }

            if (e.getKeyCode() == 40) {
                down = true;
            }

        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 10) {
            E = false;
        }

        if (e.getKeyCode() == 38) {
            Game.up = false;
        }

        if (e.getKeyCode() == 39) {
            right = false;
        }

        if (e.getKeyCode() == 37) {
            left = false;
        }

        if (e.getKeyCode() == 40) {
            down = false;
        }

    }
}
