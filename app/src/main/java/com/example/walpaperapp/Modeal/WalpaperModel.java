package com.example.walpaperapp.Modeal;

public class WalpaperModel {

    private int id;
    private String mediumurl, orginalurl;

    public WalpaperModel() {
    }

    public WalpaperModel(int id, String mediumurl, String orginalurl) {
        this.id = id;
        this.mediumurl = mediumurl;
        this.orginalurl = orginalurl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMediumurl() {
        return mediumurl;
    }

    public void setMediumurl(String mediumurl) {
        this.mediumurl = mediumurl;
    }

    public String getOrginalurl() {
        return orginalurl;
    }

    public void setOrginalurl(String orginalurl) {
        this.orginalurl = orginalurl;
    }
}
