package com.cst3014.lab05;

import java.util.ArrayList;

public class Msg {
    String text;
    int type;
    static ArrayList<Msg> list = new ArrayList<>();

    public Msg(){}

    public Msg(String text, int type){
        this.text = text;
        this.type = type;
    }

    public void setText(){}
    public void setType(){}
    public int getType(){
        return type;
    }
    public String getText(){
        return text;
    }
    public static Msg getMsg(int index){
        return list.get(index);
    }
    public static void addMsg(String text, int type){
        list.add(new Msg(text, type));
    }
    public static  void removeMsg(int index){
        list.remove(index);
    }

}
