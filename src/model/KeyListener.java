package model;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
        if(Game.session == null) {
            return;
        }
        if(e.getKeyCode() == KeyEvent.VK_S) {
            Game.session.press("s");
            new Music("snare2.mp3", false).start();
        } else if(e.getKeyCode() == KeyEvent.VK_D) {
            Game.session.press("d");
            new Music("snare.mp3", false).start();
        } else if(e.getKeyCode() == KeyEvent.VK_F) {
            Game.session.press("f");
            new Music("hihat.mp3", false).start();
        } else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            Game.session.press("S");
            new Music("kick.mp3", false).start();
        } else if(e.getKeyCode() == KeyEvent.VK_J) {
            Game.session.press("j");
            new Music("hihat.mp3", false).start();
        } else if(e.getKeyCode() == KeyEvent.VK_K) {
            Game.session.press("k");
            new Music("snare.mp3", false).start();
        } else if(e.getKeyCode() == KeyEvent.VK_L) {
            Game.session.press("l");
            new Music("snare2.mp3", false).start();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(Game.session == null) {
            return;
        }
        if(e.getKeyCode() == KeyEvent.VK_S) {
            Game.session.release("s");
        } else if(e.getKeyCode() == KeyEvent.VK_D) {
            Game.session.release("d");
        } else if(e.getKeyCode() == KeyEvent.VK_F) {
            Game.session.release("f");
        } else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            Game.session.release("S");
        } else if(e.getKeyCode() == KeyEvent.VK_J) {
            Game.session.release("j");
        } else if(e.getKeyCode() == KeyEvent.VK_K) {
            Game.session.release("k");
        } else if(e.getKeyCode() == KeyEvent.VK_L) {
            Game.session.release("l");
        }
    }
}
