package com.example.Calculator1o6Bot.game.common;

import lombok.Data;

@Data
public class Point {
    private int x;
    private int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void setXY (int x, int y){
        setX(x);
        setY(y);
    }

}
