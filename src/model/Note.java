package model;

import ui.Main;

import javax.swing.*;
import java.awt.*;

public class Note extends Thread {

    private Image note = new ImageIcon(Main.class.getResource("../images/note.png")).getImage();
    private int x;
    private int y = (Session.HITBOX_LOCATION - 1000 / Main.COOLTIME * Main.SPEED) * Main.REACH;
    private String noteType;
    private boolean proceed = true;

    public String getNoteType() {
        return noteType;
    }

    public boolean isProceed() {
        return this.proceed;
    }

    public void close() {
        proceed = false;
    }

    public Note(String noteType) {
        if (noteType.equals("s")) {
            x = 230;
        } else if (noteType.equals("d")) {
            x = 334;
        } else if (noteType.equals("f")) {
            x = 438;
        } else if (noteType.equals("S")) {
            x = 542;
        } else if (noteType.equals("j")) {
            x = 746;
        } else if (noteType.equals("k")) {
            x = 850;
        } else if (noteType.equals("l")) {
            x = 954;
        }
        this.noteType = noteType;
    }

    public void screenDraw(Graphics2D g) {
        if (y < 630) {
            if (!noteType.equals("S")) {
                g.drawImage(note, x, y, null);
            } else {
                g.drawImage(note, x, y, null);
                g.drawImage(note, x + 100, y, null);
            }
        }
    }

    public void drop() {
        y += Main.SPEED;
        if (y > 720) {
            System.out.println("---Miss---");
            close();
        }
    }

    @Override
    public void run() {
        try {
            while(true) {
                drop();
                if (proceed) {
                    Thread.sleep(Main.COOLTIME);
                } else {
                    interrupt();
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void judge() {
        if (y >= 575) {
            System.out.println("---Late---");
            close();
        } else if (y >= 550) {
            System.out.println("---Good---");
            close();
        } else if (y >= 530) {
            System.out.println("---Excellent---");
            close();
        } else if (y >= 520) {
            System.out.println("---Good---");
            close();
        }
    }
}
