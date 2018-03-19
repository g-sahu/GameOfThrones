package com.thoughtworks.gsahu.gameofthrones.utils;

/**
 * Created by gasahu on 08-Jan-17.
 */

public class EloRating {
    public static int[] calculateRating(int currentRating1, int currentRating2, double score1, double score2) {
        int ratings[] = new int[2];
        double newRating1, expectedScore1, transRating1;
        double newRating2, expectedScore2, transRating2;

        transRating1 = Math.pow(10, currentRating1/GoTConstants.N);
        transRating2 = Math.pow(10, currentRating2/GoTConstants.N);

        expectedScore1 = transRating1 / (transRating1 + transRating2);
        expectedScore2 = transRating2 / (transRating1 + transRating2);

        newRating1 = currentRating1 + (GoTConstants.K * (score1 - expectedScore1));
        newRating2 = currentRating2 + (GoTConstants.K * (score2 - expectedScore2));

        ratings[0] = (int) newRating1;
        ratings[1] = (int) newRating2;

        return ratings;
    }
}
