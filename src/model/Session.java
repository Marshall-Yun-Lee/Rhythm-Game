package model;

import ui.Main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Session extends Thread {

    public static final int HITBOX_LOCATION = 530;

    private Image letterBox = new ImageIcon(Main.class.getResource("../images/letterbox.png")).getImage();
    private Image hitBox = new ImageIcon(Main.class.getResource("../images/hitbox.png")).getImage();
    private Image division = new ImageIcon(Main.class.getResource("../images/division.png")).getImage();
    private Image route_s = new ImageIcon(Main.class.getResource("../images/route.png")).getImage();
    private Image route_d = new ImageIcon(Main.class.getResource("../images/route.png")).getImage();
    private Image route_f = new ImageIcon(Main.class.getResource("../images/route.png")).getImage();
    private Image route_S = new ImageIcon(Main.class.getResource("../images/route.png")).getImage();
    private Image route_j = new ImageIcon(Main.class.getResource("../images/route.png")).getImage();
    private Image route_k = new ImageIcon(Main.class.getResource("../images/route.png")).getImage();
    private Image route_l = new ImageIcon(Main.class.getResource("../images/route.png")).getImage();

    private String titleName;
    private String difficulty;
    private String musicTitle;
    private Music gameMusic;

    ArrayList<Note> notes = new ArrayList<>();

    public Session(String titleName, String difficulty, String musicTitle) {
        this.titleName = titleName;
        this.difficulty = difficulty;
        this.musicTitle = musicTitle;
        gameMusic = new Music(this.musicTitle, false);
    }

    public void screenDraw(Graphics2D g) {
        routeSetup(g);
        //... dont understand this line. gotta dig out further
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString(titleName, 20, 702);
        g.drawString(difficulty, 1190, 702);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("S", 274, 559);
        g.drawString("D", 378, 559);
        g.drawString("F", 482, 559);
        g.drawString("SPACE", 613, 559);
        g.drawString("J", 792, 559);
        g.drawString("K", 895, 559);
        g.drawString("L", 1000, 559);
        g.setFont(new Font("Elephant", Font.BOLD, 30));
        g.drawString("000000", 565, 702);

        for (int i = 0; i < notes.size(); i++) {
            Note note = notes.get(i);
            if (!note.isProceed()) {
                notes.remove(i);
                i--;
            } else {
                note.screenDraw(g);
            }
        }
    }

    private void routeSetup(Graphics2D g) {
        g.drawImage(division, 226, 30, null);
        g.drawImage(route_s, 230, 30, null);
        g.drawImage(division, 330, 30, null);

        g.drawImage(route_d, 334, 30, null);
        g.drawImage(division, 434, 30, null);

        g.drawImage(route_f, 438, 30, null);
        g.drawImage(division, 538, 30, null);

        g.drawImage(route_S, 542, 30, null);
        g.drawImage(route_S, 642, 30, null);
        g.drawImage(division, 742, 30, null);

        g.drawImage(route_j, 746, 30, null);
        g.drawImage(division, 846, 30, null);

        g.drawImage(route_k, 850, 30, null);
        g.drawImage(division, 950, 30, null);

        g.drawImage(route_l, 954, 30, null);
        g.drawImage(division, 1054, 30, null);

        g.drawImage(letterBox, 0, 660, null);
        g.drawImage(hitBox, 0, HITBOX_LOCATION, null);
    }

    public void press(String key) {
        if (key.equals("s")) {
            judge("s");
            route_s = new ImageIcon(Main.class.getResource("../images/keypressing.png")).getImage();
        } else if (key.equals("d")) {
            judge("d");
            route_d = new ImageIcon(Main.class.getResource("../images/keypressing.png")).getImage();
        } else if (key.equals("f")) {
            judge("f");
            route_f = new ImageIcon(Main.class.getResource("../images/keypressing.png")).getImage();
        } else if (key.equals("S")) {
            judge("S");
            route_S = new ImageIcon(Main.class.getResource("../images/keypressing.png")).getImage();
        } else if (key.equals("j")) {
            judge("j");
            route_j = new ImageIcon(Main.class.getResource("../images/keypressing.png")).getImage();
        } else if (key.equals("k")) {
            judge("k");
            route_k = new ImageIcon(Main.class.getResource("../images/keypressing.png")).getImage();
        } else if (key.equals("l")) {
            judge("l");
            route_l = new ImageIcon(Main.class.getResource("../images/keypressing.png")).getImage();
        }
    }

    public void release(String key) {
        if (key.equals("s")) {
            route_s = new ImageIcon(Main.class.getResource("../images/route.png")).getImage();
        } else if (key.equals("d")) {
            route_d = new ImageIcon(Main.class.getResource("../images/route.png")).getImage();
        } else if (key.equals("f")) {
            route_f = new ImageIcon(Main.class.getResource("../images/route.png")).getImage();
        } else if (key.equals("S")) {
            route_S = new ImageIcon(Main.class.getResource("../images/route.png")).getImage();
        } else if (key.equals("j")) {
            route_j = new ImageIcon(Main.class.getResource("../images/route.png")).getImage();
        } else if (key.equals("k")) {
            route_k = new ImageIcon(Main.class.getResource("../images/route.png")).getImage();
        } else if (key.equals("l")) {
            route_l = new ImageIcon(Main.class.getResource("../images/route.png")).getImage();
        }
    }

    @Override
    public void run() {
        dropNotes(this.titleName);
    }

    public void close() {
        gameMusic.close();
        this.interrupt();
    }

    public void dropNotes(String titleName) {
        Beat[] beats = null;
        System.out.println(titleName);
        if (titleName.equals("Joakim Karud - Mighty Love") && difficulty.equals("Easy")) {
            int startTime = 4460 - Main.REACH * 1000;
            int gap = 125;
            beats = new Beat[] {
                    new Beat(startTime, "s"),
                    new Beat(startTime + gap * 2, "d"),
                    new Beat(startTime + gap * 4, "s"),
                    new Beat(startTime + gap * 6, "d"),
                    new Beat(startTime + gap * 8, "s"),
                    new Beat(startTime + gap * 10, "d"),
                    new Beat(startTime + gap * 12, "s"),
                    new Beat(startTime + gap * 14, "d"),
                    new Beat(startTime + gap * 18, "k"),
                    new Beat(startTime + gap * 20, "l"),
                    new Beat(startTime + gap * 22, "k"),
                    new Beat(startTime + gap * 24, "l"),
                    new Beat(startTime + gap * 26, "k"),
                    new Beat(startTime + gap * 28, "l"),
                    new Beat(startTime + gap * 30, "k"),
                    new Beat(startTime + gap * 32, "l"),
                    new Beat(startTime + gap * 36, "s"),
                    new Beat(startTime + gap * 38, "d"),
                    new Beat(startTime + gap * 40, "s"),
                    new Beat(startTime + gap * 42, "d"),
                    new Beat(startTime + gap * 44, "s"),
                    new Beat(startTime + gap * 46, "d"),
                    new Beat(startTime + gap * 48, "j"),
                    new Beat(startTime + gap * 49, "k"),
                    new Beat(startTime + gap * 50, "l"),
                    new Beat(startTime + gap * 52, "f"),
                    new Beat(startTime + gap * 52, "S"),
                    new Beat(startTime + gap * 52, "j"),
                    new Beat(startTime + gap * 54, "s"),
                    new Beat(startTime + gap * 56, "d"),
                    new Beat(startTime + gap * 59, "f"),
                    new Beat(startTime + gap * 59, "S"),
                    new Beat(startTime + gap * 59, "j"),
                    new Beat(startTime + gap * 61, "l"),
                    new Beat(startTime + gap * 63, "k"),
                    new Beat(startTime + gap * 65, "f"),
                    new Beat(startTime + gap * 65, "S"),
                    new Beat(startTime + gap * 65, "j"),
                    new Beat(startTime + gap * 70, "s"),
                    new Beat(startTime + gap * 72, "s"),
                    new Beat(startTime + gap * 74, "s"),
                    new Beat(startTime + gap * 78, "j"),
                    new Beat(startTime + gap * 79, "k"),
                    new Beat(startTime + gap * 80, "l"),
                    new Beat(startTime + gap * 83, "S"),
                    new Beat(startTime + gap * 85, "s"),
                    new Beat(startTime + gap * 87, "d"),
                    new Beat(startTime + gap * 89, "s"),
                    new Beat(startTime + gap * 91, "d"),
                    new Beat(startTime + gap * 93, "f"),
                    new Beat(startTime + gap * 96, "S"),
                    new Beat(startTime + gap * 98, "l"),
                    new Beat(startTime + gap * 100, "S"),
                    new Beat(startTime + gap * 102, "s"),
                    new Beat(startTime + gap * 104, "d"),
                    new Beat(startTime + gap * 106, "S"),
                    new Beat(startTime + gap * 109, "S"),
                    new Beat(startTime + gap * 112, "S"),
                    new Beat(startTime + gap * 118, "S"),
            };
        } else if (titleName.equals("Joakim Karud - Mighty Love") && difficulty.equals("Hard")) {
            int startTime = 4460 - Main.REACH * 1000;
            int gap = 125;
            beats = new Beat[]{
                    new Beat(startTime, "s"),
                    new Beat(startTime + gap * 2, "d"),
                    new Beat(startTime + gap * 4, "s"),
                    new Beat(startTime + gap * 6, "d"),
                    new Beat(startTime + gap * 8, "s"),
                    new Beat(startTime + gap * 10, "d"),
                    new Beat(startTime + gap * 12, "s"),
                    new Beat(startTime + gap * 14, "d"),
                    new Beat(startTime + gap * 18, "k"),
                    new Beat(startTime + gap * 20, "l"),
                    new Beat(startTime + gap * 22, "k"),
                    new Beat(startTime + gap * 24, "l"),
                    new Beat(startTime + gap * 26, "k"),
                    new Beat(startTime + gap * 28, "l"),
                    new Beat(startTime + gap * 30, "k"),
                    new Beat(startTime + gap * 32, "l"),
                    new Beat(startTime + gap * 36, "s"),
                    new Beat(startTime + gap * 38, "d"),
                    new Beat(startTime + gap * 40, "s"),
                    new Beat(startTime + gap * 42, "d"),
                    new Beat(startTime + gap * 44, "s"),
                    new Beat(startTime + gap * 46, "d"),
                    new Beat(startTime + gap * 48, "j"),
                    new Beat(startTime + gap * 49, "k"),
                    new Beat(startTime + gap * 50, "l"),
                    new Beat(startTime + gap * 52, "f"),
                    new Beat(startTime + gap * 52, "S"),
                    new Beat(startTime + gap * 52, "j"),
                    new Beat(startTime + gap * 54, "s"),
                    new Beat(startTime + gap * 56, "d"),
                    new Beat(startTime + gap * 59, "f"),
                    new Beat(startTime + gap * 59, "S"),
                    new Beat(startTime + gap * 59, "j"),
                    new Beat(startTime + gap * 61, "l"),
                    new Beat(startTime + gap * 63, "k"),
                    new Beat(startTime + gap * 65, "f"),
                    new Beat(startTime + gap * 65, "S"),
                    new Beat(startTime + gap * 65, "j"),
                    new Beat(startTime + gap * 70, "s"),
                    new Beat(startTime + gap * 72, "s"),
                    new Beat(startTime + gap * 74, "s"),
                    new Beat(startTime + gap * 78, "j"),
                    new Beat(startTime + gap * 79, "k"),
                    new Beat(startTime + gap * 80, "l"),
                    new Beat(startTime + gap * 83, "S"),
                    new Beat(startTime + gap * 85, "s"),
                    new Beat(startTime + gap * 87, "d"),
                    new Beat(startTime + gap * 89, "s"),
                    new Beat(startTime + gap * 91, "d"),
                    new Beat(startTime + gap * 93, "f"),
                    new Beat(startTime + gap * 96, "S"),
                    new Beat(startTime + gap * 98, "l"),
                    new Beat(startTime + gap * 100, "S"),
                    new Beat(startTime + gap * 102, "s"),
                    new Beat(startTime + gap * 104, "d"),
                    new Beat(startTime + gap * 106, "S"),
                    new Beat(startTime + gap * 109, "S"),
                    new Beat(startTime + gap * 112, "S"),
                    new Beat(startTime + gap * 118, "S"),
            };
        }

        else if (titleName.equals("KV - Wild") && difficulty.equals("Easy")) {
            int startTime = 5000 - Main.REACH * 1000;
            beats = new Beat[] {
                    new Beat(startTime, "d"),
                    new Beat(startTime + 1000, "s"),
            };
        } else if (titleName.equals("KV - Wild") && difficulty.equals("Hard")) {
            int startTime = 5000 - Main.REACH * 1000;
            beats = new Beat[] {
                    new Beat(startTime, "d"),
                    new Beat(startTime + 1000, "s"),
            };
        }

        else if (titleName.equals("Peyruis - Reveur") && difficulty.equals("Easy")) {
            int startTime = 0 - Main.REACH * 1000;
            beats = new Beat[] {
                    new Beat(startTime, "d"),
                    new Beat(startTime, "k"),
                    new Beat(startTime + 1000, "j"),
            };
        } else if (titleName.equals("Peyruis - Reveur") && difficulty.equals("Hard")) {
            int startTime = 0 - Main.REACH * 1000;
            beats = new Beat[] {
                    new Beat(startTime, "d"),
                    new Beat(startTime, "k"),
                    new Beat(startTime + 1000, "j"),
            };
        }

        int i = 0;
        gameMusic.start();
        while (i < beats.length && !isInterrupted()) {
            boolean dropped = false;
            if (beats[i].getTime() <= gameMusic.getTime()) {
                Note note = new Note(beats[i].getNoteName());
                note.start();
                notes.add(note);
                i++;
                dropped = true;
            }
            if (!dropped) {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void judge(String input) {
        for (Note note : notes) {
            if (input.equals(note.getNoteType())) {
                note.judge();
                break;
            }
        }
    }
}

