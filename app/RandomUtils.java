package com.lottery.app.helperPackages.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class RandomUtils {
    private static final boolean IS_TRUE = false;

    public static List<HashMap<String, Object>> generateRandomPath(int entryAmount, int maxLength) {
        List<HashMap<String, Object>> path = new ArrayList<>();

        for (int i=0; i<maxLength; i++) {
            HashMap<String, Object> pathEntry = new HashMap<>();
            boolean randomBoolean = RandomUtils.getRandomBoolean();
            double newAmount = entryAmount * (i+1);

            pathEntry.put("value", newAmount);
            pathEntry.put("left", randomBoolean);
            pathEntry.put("right", !randomBoolean);
            pathEntry.put("hasWon", IS_TRUE);
            pathEntry.put("isActive", i==0);
            pathEntry.put("shouldShowCross", IS_TRUE);
            pathEntry.put("hasCrossed", IS_TRUE);

            path.add(pathEntry);
        }

        return path;
    }

    private static boolean getRandomBoolean() {
        return new Random().nextBoolean();
    }
}
