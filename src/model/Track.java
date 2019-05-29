package model;

public class Track {

    private String titleImage;
    private String startImage;
    private String gameMusic;
    private String titleName;

    public Track(String titleImage, String startImage, String gameMusic, String titleName) {
        this.titleImage = titleImage;
        this.startImage = startImage;
        this.gameMusic = gameMusic;
        this.titleName = titleName;
    }

    public String getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(String titleImage) {
        this.titleImage = titleImage;
    }

    public String getStartImage() {
        return startImage;
    }

    public void setStartImage(String startImage) {
        this.startImage = startImage;
    }

    public String getGameMusic() {
        return gameMusic;
    }

    public void setGameMusic(String gameMusic) {
        this.gameMusic = gameMusic;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }
}
