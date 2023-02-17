package com.example.Calculator1o6Bot.piece

import com.example.Calculator1o6Bot.game.common.Point
import com.example.Calculator1o6Bot.game.piece.Color
import com.example.Calculator1o6Bot.game.piece.Knight
import kotlin.math.abs


fun Knight.stepOfKnight(newPoint: Point): Boolean {
    when {
        ((abs(newPoint.x - point.x) == 2 &&
                abs(newPoint.y - point.y) == 1)) ||
                ((abs(newPoint.x - point.x) == 1 &&
                        abs(newPoint.y - point.y) == 2))-> {
            if (matrix[point.x][point.y] != null) {
                eat(newPoint)
            } else {
                changePoint(newPoint)
            }
            return true
        }
    }
    return false;
}

