package com.example.Calculator1o6Bot.game.piece;

import com.example.Calculator1o6Bot.game.common.Point;

public class King extends Piece {

    public King(Point point, Color color, Piece[][] matrix) {
        super(point, color, matrix);
    }

    @Override
    public  boolean step(Point newPoint) {
        int x = getPoint().getX();
        int y = getPoint().getY();
        int newX = newPoint.getX();
        int newY = newPoint.getY();
        if ((((x - newX == 1) ||
                (x - newX == -1)) &&
                ((y - newY == 1) ||
                        (y - newY == 0) ||
                        (y - newY == -1))) ||
                (x == newX) &&
                        ((y - newY == 1) ||
                                (y - newY == -1))) {
            if (getMatrix()[newX][newY] != null) {
                if (getMatrix()[newX][newY].getColor() != getColor()) {
                    eat(newPoint);
                    return true;
                } else {
                    return false;
                }
            } else {
                getPoint().setXY(newX, newY);
                return true;
            }

        } else {
            return false;
        }
    }

}
