package com.example.Calculator1o6Bot.game.piece;

import com.example.Calculator1o6Bot.game.common.Point;

public class Pawn  extends Piece{
    public Pawn(Point point, Color color, Piece[][] matrix) {
        super(point, color, matrix);
    }

    @Override
    public boolean step(Point newPoint) {
        int x = getPoint().getX();
        int y = getPoint().getY();
        int newX = newPoint.getX();
        int newY = newPoint.getY();
        if ((x == newX) &&
                ((newY-y > 0) &&
                        ((newY-y <= 2) &&
                                getColor() == Color.WHITE) ||
                ((newY-y < 0) &&
                        (newY-y >= -2) &&
                        getColor() == Color.BLACK)) &&
                getMatrix()[newX][newY] == null){
            getPoint().setXY(newX, newY);
            return true;
        }
        if ((((newX-x == 1) &&
                (getColor() == Color.WHITE)) ||
                ((newX - x == -1) &&
                        (getColor() == Color.BLACK))) &&
                ((newY - y == 1) ||
                        (newY - y == -1)) &&
                (getMatrix()[newX][newY] != null &&
                        getMatrix()[newX][newY].getColor() != getColor() )) {
            eat(newPoint);
            return true;
        }
        return false;
    }

}
