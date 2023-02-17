package com.example.Calculator1o6Bot.game.piece;

import com.example.Calculator1o6Bot.game.common.Point;
import lombok.Data;

@Data
public abstract class Piece {

    private Point point;
    private Color color;
    private Piece[][] matrix;

    public Piece(Point point, Color color, Piece[][] matrix){
        this.point = point;
        this.color = color;
        this.matrix = matrix;
    }

    public abstract boolean step(Point newPoint);

    public void eat(Point newPoint){
        getMatrix()[newPoint.getX()][newPoint.getY()].getPoint().setXY(-1,-1);
        getPoint().setXY(newPoint.getX(), newPoint.getY());
    }

}
