package com.example.mechsrit.misfit_shine.roomdatabase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "product")
public class RoomModel {
    @NonNull
    @PrimaryKey(autoGenerate = false)
    private int prid;
    private String prbrand;
    private String prname;
    private String prprice;
    private String primageLink;
    private String pproductLink;
    private String prwebsiteLink;
    private String prdescription;
    private String prrating;
    private String prcategory;
    private String pproductType;

    @NonNull
    public int getPrid() {
        return prid;
    }

    public void setPrid(@NonNull int prid) {
        this.prid = prid;
    }

    public String getPrbrand() {
        return prbrand;
    }

    public void setPrbrand(String prbrand) {
        this.prbrand = prbrand;
    }

    public String getPrname() {
        return prname;
    }

    public void setPrname(String prname) {
        this.prname = prname;
    }

    public String getPrprice() {
        return prprice;
    }

    public void setPrprice(String prprice) {
        this.prprice = prprice;
    }

    public String getPrimageLink() {
        return primageLink;
    }

    public void setPrimageLink(String primageLink) {
        this.primageLink = primageLink;
    }

    public String getPproductLink() {
        return pproductLink;
    }

    public void setPproductLink(String pproductLink) {
        this.pproductLink = pproductLink;
    }

    public String getPrwebsiteLink() {
        return prwebsiteLink;
    }

    public void setPrwebsiteLink(String prwebsiteLink) {
        this.prwebsiteLink = prwebsiteLink;
    }

    public String getPrdescription() {
        return prdescription;
    }

    public void setPrdescription(String prdescription) {
        this.prdescription = prdescription;
    }

    public String getPrrating() {
        return prrating;
    }

    public void setPrrating(String prrating) {
        this.prrating = prrating;
    }

    public String getPrcategory() {
        return prcategory;
    }

    public void setPrcategory(String prcategory) {
        this.prcategory = prcategory;
    }

    public String getPproductType() {
        return pproductType;
    }

    public void setPproductType(String pproductType) {
        this.pproductType = pproductType;
    }
}


