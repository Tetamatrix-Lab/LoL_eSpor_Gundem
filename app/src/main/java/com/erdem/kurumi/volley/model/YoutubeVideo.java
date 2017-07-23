package com.erdem.kurumi.volley.model;

/**
 * Created by Kurumi on 13.3.2015.
 */
public class YoutubeVideo {
    private String youtubeId;
    private String videoBasligi;
    private String izlenmaSayisi;
    private String kucukResimLinki;


    public YoutubeVideo(String youtubeId,String videoBasligi,String izlenmaSayisi,String kucukResimLinki) {
        this.setYoutubeId(youtubeId);
        this.setVideoBasligi(videoBasligi);
        this.setIzlenmaSayisi(izlenmaSayisi);
        this.setKucukResimLinki(kucukResimLinki);
    }

    public YoutubeVideo() {

    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    public String getVideoBasligi() {
        return videoBasligi;
    }

    public void setVideoBasligi(String videoBasligi) {
        this.videoBasligi = videoBasligi;
    }

    public String getIzlenmaSayisi() {
        return izlenmaSayisi;
    }

    public void setIzlenmaSayisi(String izlenmaSayisi) {
        this.izlenmaSayisi = izlenmaSayisi;
    }

    public String getKucukResimLinki() {
        return kucukResimLinki;
    }

    public void setKucukResimLinki(String kucukResimLinki) {
        this.kucukResimLinki = kucukResimLinki;
    }
}
