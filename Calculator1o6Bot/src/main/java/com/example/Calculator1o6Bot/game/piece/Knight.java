package com.example.Calculator1o6Bot.game.piece;

import com.example.Calculator1o6Bot.game.common.Point;
import com.example.Calculator1o6Bot.piece.KnightStepKt;

public class Knight extends Piece{

    public Knight(Point point, Color color, Piece[][] matrix) {
        super(point, color, matrix);
    }

    @Override
    public boolean step(Point newPoint) {
        return KnightStepKt.stepOfKnight(this, newPoint);
    }
}
