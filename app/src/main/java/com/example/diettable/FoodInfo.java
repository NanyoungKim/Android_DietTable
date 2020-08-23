package com.example.diettable;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class FoodInfo implements Serializable {

    private long foodCode;       //국, 밥, 죽, ,,
    private String foodName;    //요리 이름 (감자밥,,)
    private long ingredNum;      //재료 종류
    private String ingredName;  //재료 이름
    private double portion;        //1인량

    /*public FoodInfo(int code, String fname, int inum, String iname, int portion){
        this.foodCode = code;
        this.foodName = fname;
        this.ingredNum = inum;
        this.ingredName = iname;
        this.portion = portion;
    }*/

    public FoodInfo() {

    }



    public void setFoodCode(long code){
        this.foodCode = code;
    }
    public void setFoodName(String fname){
        this.foodName = fname;
    }
    public void setIngredNum(long inum){
        this.ingredNum = inum;
    }
    public void setIngredName(String iname){
        this.ingredName = iname;
    }
    public void setPortion(double portion){
        this.portion = portion;
    }



    public long getFoodCode(){
        return foodCode;
    }
    public String getFoodName(){
        return foodName;
    }
    public long getIngredNum(){
        return ingredNum;
    }
    public String getIngredName(){
        return ingredName;
    }
    public double getPortion(){
        return portion;
    }



}
