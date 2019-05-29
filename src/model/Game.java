package model;

import ui.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class Game extends JFrame {

    private Image screenImage;
    private Graphics screenGraphics;

    private Music introMusic = new Music("Ikson - Explore.mp3", true);
    private Image background = new ImageIcon(Main.class.getResource("../images/bg_start.png")).getImage();
    private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));

    private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exit_entered.png"));
    private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exit.png"));
    private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/START_entered.png"));
    private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/START.png"));
    private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/QUIT_entered.png"));
    private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/QUIT.png"));
    private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/left_entered.png"));
    private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/left.png"));
    private ImageIcon rightButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/right_entered.png"));
    private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/right.png"));
    private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/back_entered.png"));
    private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/back.png"));
    private ImageIcon easyButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/easy_entered.png"));
    private ImageIcon easyButtonBasicImage = new ImageIcon(Main.class.getResource("../images/easy_basic.png"));
    private ImageIcon hardButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/hard_entered.png"));
    private ImageIcon hardButtonBasicImage = new ImageIcon(Main.class.getResource("../images/hard_basic.png"));

    private JButton easyButton = new JButton(easyButtonBasicImage);
    private JButton hardButton = new JButton(hardButtonBasicImage);
    private JButton leftButton = new JButton(leftButtonBasicImage);
    private JButton rightButton = new JButton(rightButtonBasicImage);
    private JButton startButton = new JButton(startButtonBasicImage);
    private JButton quitButton = new JButton(quitButtonBasicImage);
    private JButton exitButton = new JButton(exitButtonBasicImage);
    // somehow imageicons got resources reversed, though I changed the file names. idk y;
    private JButton backButton = new JButton(backButtonEnteredImage);

    private int mouseX, mouseY;
    private boolean isMainScreen = false;
    private boolean isGameScreen = false;

    private ArrayList<Track> trackList = new ArrayList<>();

    private Image titleImage = new ImageIcon(Main.class.getResource("../images/title - mighty love.png")).getImage();
    private Image selectedImage = new ImageIcon(Main.class.getResource("../images/mighty love.png")).getImage();
    private Music selectedMusic;
    private int nowSelected = 0;

    public static Session session;

    // could I refactor buttons into a single method?
    public Game() {
        trackList.add(new Track("title - mighty love.png", "mighty love.png",
                "Joakim Karud - Mighty Love.mp3", "Joakim Karud - Mighty Love"));
        trackList.add(new Track("title - wild.png", "wild.jpg",
                "KV - Wild.mp3", "KV - Wild.mp3"));
        trackList.add(new Track("title - Reveur.png", "Reveur.png",
                "Peyruis - Reveur.mp3", "Peyruis - Reveur"));


        setUndecorated(true);
        setTitle("Rhythm");
        setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setBackground(new Color(0, 0, 0, 0));
        setLayout(null);

        addKeyListener(new KeyListener());

        leftButton.setVisible(false);
        rightButton.setVisible(false);

        // BGM setting
        introMusic.start();

        // startButton-------------------------------------------
        startButton.setBounds(100, 400, 300, 80);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);
        startButton.setFocusPainted(false);
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                startButton.setIcon(startButtonEnteredImage);
                startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music buttonEnteredMusic = new Music("mouseEntered.mp3", false);
                buttonEnteredMusic.start();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                startButton.setIcon(startButtonBasicImage);
                startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {

                Music buttonPressedMusic = new Music("mousePressed.mp3", false);
                buttonPressedMusic.start();
                introMusic.close();
                toMainScreen();
                selectTrack(0);
            }
        });
        add(startButton);

        // quitButton-------------------------------------------
        quitButton.setBounds(100, 530, 300, 80);
        quitButton.setContentAreaFilled(false);
        quitButton.setBorderPainted(false);
        quitButton.setFocusPainted(false);
        quitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                quitButton.setIcon(quitButtonEnteredImage);
                quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music buttonEnteredMusic = new Music("mouseEntered.mp3", false);
                buttonEnteredMusic.start();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                quitButton.setIcon(quitButtonBasicImage);
                quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Music buttonPressedMusic = new Music("mousePressed.mp3", false);
                buttonPressedMusic.start();
                try {
                    Thread.sleep(800);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.exit(0);
            }
        });
        add(quitButton);

        // exitButton-------------------------------------------
        exitButton.setBounds(1250, 0, 30, 30);
        exitButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);
        exitButton.setFocusPainted(false);
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setIcon(exitButtonEnteredImage);
                exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music buttonEnteredMusic = new Music("mouseEntered.mp3", false);
                buttonEnteredMusic.start();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setIcon(exitButtonBasicImage);
                exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Music buttonPressedMusic = new Music("mousePressed.mp3", false);
                buttonPressedMusic.start();
                try {
                    Thread.sleep(800);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.exit(0);
            }
        });
        add(exitButton);

        // leftButton-------------------------------------------
        leftButton.setVisible(false);
        leftButton.setBounds(140, 310, 60, 60);
        leftButton.setContentAreaFilled(false);
        leftButton.setBorderPainted(false);
        leftButton.setFocusPainted(false);
        leftButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                leftButton.setIcon(leftButtonEnteredImage);
                leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music buttonEnteredMusic = new Music("mouseEntered.mp3", false);
                buttonEnteredMusic.start();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                leftButton.setIcon(leftButtonBasicImage);
                leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Music buttonPressedMusic = new Music("mousePressed.mp3", false);
                buttonPressedMusic.start();
                selectLeft();
            }
        });
        add(leftButton);

        // rightButton-------------------------------------------
        rightButton.setVisible(false);
        rightButton.setBounds(1080, 310, 60, 60);
        rightButton.setContentAreaFilled(false);
        rightButton.setBorderPainted(false);
        rightButton.setFocusPainted(false);
        rightButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                rightButton.setIcon(rightButtonEnteredImage);
                rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music buttonEnteredMusic = new Music("mouseEntered.mp3", false);
                buttonEnteredMusic.start();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rightButton.setIcon(rightButtonBasicImage);
                rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Music buttonPressedMusic = new Music("mousePressed.mp3", false);
                buttonPressedMusic.start();
                selectRight();
            }
        });
        add(rightButton);

        // easyButton-------------------------------------------
        easyButton.setVisible(false);
        easyButton.setBounds(375, 620, 250, 67);
        easyButton.setContentAreaFilled(false);
        easyButton.setBorderPainted(false);
        easyButton.setFocusPainted(false);
        easyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                easyButton.setIcon(easyButtonEnteredImage);
                easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music buttonEnteredMusic = new Music("mouseEntered.mp3", false);
                buttonEnteredMusic.start();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                easyButton.setIcon(easyButtonBasicImage);
                easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Music buttonPressedMusic = new Music("mousePressed.mp3", false);
                buttonPressedMusic.start();
                gameStart(nowSelected, "Easy");
            }
        });
        add(easyButton);

        // hardButton-------------------------------------------
        hardButton.setVisible(false);
        hardButton.setBounds(655, 620, 250, 67);
        hardButton.setContentAreaFilled(false);
        hardButton.setBorderPainted(false);
        hardButton.setFocusPainted(false);
        hardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hardButton.setIcon(hardButtonEnteredImage);
                hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music buttonEnteredMusic = new Music("mouseEntered.mp3", false);
                buttonEnteredMusic.start();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hardButton.setIcon(hardButtonBasicImage);
                hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Music buttonPressedMusic = new Music("mousePressed.mp3", false);
                buttonPressedMusic.start();
                gameStart(nowSelected, "Hard");
            }
        });
        add(hardButton);


        // backButton-------------------------------------------
        backButton.setVisible(false);
        backButton.setBounds(1220, 30, 60, 60);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.addMouseListener(new MouseAdapter() {
            // basic and entered reversed.
            @Override
            public void mouseEntered(MouseEvent e) {
                backButton.setIcon(backButtonBasicImage);
                backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music buttonEnteredMusic = new Music("mouseEntered.mp3", false);
                buttonEnteredMusic.start();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backButton.setIcon(backButtonEnteredImage);
                backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Music buttonPressedMusic = new Music("mousePressed.mp3", false);
                buttonPressedMusic.start();
                selectedMusic.close();
                if (isMainScreen) {
                    toStartScreen();
                } else if(isGameScreen) {
                    toMainScreen();
                    selectTrack(nowSelected);
                }
            }
        });
        add(backButton);

        // menuBar-------------------------------------------
        menuBar.setBounds(0, 0, 1280, 30);
        menuBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });
        menuBar.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                setLocation(x - mouseX, y - mouseY);
            }
        });
        add(menuBar);
    }

    private void toStartScreen() {
        introMusic = new Music("Ikson - Explore.mp3", true);
        introMusic.start();
        startButton.setVisible(true);
        quitButton.setVisible(true);

        leftButton.setVisible(false);
        rightButton.setVisible(false);
        backButton.setVisible(false);
        easyButton.setVisible(false);
        hardButton.setVisible(false);

        background = new ImageIcon(Main.class.getResource("../images/bg_start.png")).getImage();
        isMainScreen = false;
        isGameScreen = false;
    }

    private void toMainScreen() {
        leftButton.setVisible(true);
        rightButton.setVisible(true);
        backButton.setVisible(true);
        easyButton.setVisible(true);
        hardButton.setVisible(true);

        startButton.setVisible(false);
        quitButton.setVisible(false);

        background = new ImageIcon(Main.class.getResource("../images/bg_main2.jpg")).getImage();

        if (isGameScreen) {
            session.close();
            isGameScreen = false;
        }
        isMainScreen = true;

    }

    // double buffering... works on Windows but not on Mac. wtf?
    @Override
    public void paint(Graphics g) {
        screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        screenGraphics = screenImage.getGraphics();
        screenDraw((Graphics2D) screenGraphics);
        g.drawImage(screenImage, 0, 0, null);
    }

    // work properly on windows but not on mac.
    public void screenDraw(Graphics2D screenGraphics) {
        screenGraphics.drawImage(background, 0, 0, null);
        if (isMainScreen) {
            screenGraphics.drawImage(titleImage, 340, 70, null);
            screenGraphics.drawImage(selectedImage, 340, 170, null);
        }
        if (isGameScreen) {
            session.screenDraw(screenGraphics);
        }
        paintComponents(screenGraphics);
        this.repaint();
    }

    public void selectTrack(int nowSelected) {
        if (selectedMusic != null) {
            selectedMusic.close();
        }
        titleImage = new ImageIcon(Main.class.
                getResource("../images/" + trackList.get(nowSelected).getTitleImage())).getImage();
        selectedImage = new ImageIcon(Main.class.
                getResource("../images/" + trackList.get(nowSelected).getStartImage())).getImage();
        selectedMusic = new Music(trackList.get(nowSelected).getGameMusic(), true);
        selectedMusic.start();
    }

    public void selectLeft() {
        if (nowSelected == 0) {
            nowSelected = trackList.size() - 1;
        } else {
            nowSelected -= 1;
        }
        selectTrack(nowSelected);
    }

    public void selectRight() {
        if(nowSelected == trackList.size() - 1) {
            nowSelected = 0;
        } else {
            nowSelected += 1;
        }
        selectTrack(nowSelected);
    }

    public void gameStart(int nowSelected, String difficulty) {
        if (selectedMusic != null) {
            selectedMusic.close();
        }
        isMainScreen = false;
        isGameScreen = true;
        leftButton.setVisible(false);
        rightButton.setVisible(false);
        easyButton.setVisible(false);
        hardButton.setVisible(false);
        session = new Session(trackList.get(nowSelected).getTitleName(), difficulty,
                trackList.get(nowSelected).getGameMusic());
        session.start();
        setFocusable(true);
    }
}

