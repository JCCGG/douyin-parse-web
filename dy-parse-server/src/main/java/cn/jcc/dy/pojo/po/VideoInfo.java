package cn.jcc.dy.pojo.po;

public class VideoInfo {
    private String music;
    private String vedioUrl;
    private String bgImage;

    @Override
    public String toString() {
        return "VideoInfo{" +
                "music='" + music + '\'' +
                ", vedioUrl='" + vedioUrl + '\'' +
                ", bgImage='" + bgImage + '\'' +
                '}';
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getVedioUrl() {
        return vedioUrl;
    }

    public void setVedioUrl(String vedioUrl) {
        this.vedioUrl = vedioUrl;
    }

    public String getBgImage() {
        return bgImage;
    }

    public void setBgImage(String bgImage) {
        this.bgImage = bgImage;
    }
}
