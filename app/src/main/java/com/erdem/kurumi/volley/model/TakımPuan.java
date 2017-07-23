package com.erdem.kurumi.volley.model;

/**
 * Created by Kurumi on 9.3.2015.
 */
public class TakımPuan {
    private String title;
    private String galibiyet;
    private String malubiyet;
    private String sira;
    public TakımPuan(String name,String galibiyet,String malubiyet,String sira) {
        this.setTitle(name);
        this.setMalubiyet(malubiyet);
        this.setGalibiyet(galibiyet);
        this.setSira(sira);
    }

    public TakımPuan() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGalibiyet() {
        return galibiyet;
    }

    public void setGalibiyet(String galibiyet) {
        this.galibiyet = galibiyet;
    }

    public String getMalubiyet() {
        return malubiyet;
    }

    public void setMalubiyet(String malubiyet) {
        this.malubiyet = malubiyet;
    }

    public String getSira() {
        return sira;
    }

    public void setSira(String sira) {
        this.sira = sira;
    }

}
