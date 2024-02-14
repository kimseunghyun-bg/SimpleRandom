package com.seunghyun.simpleRandom;

import java.util.Random;


/**
 * An instance of this class is used to generate a simple random float numbers.
 * It is the default range 0.0f (inclusive) to 1.0f (exclusive).
 * <p>
 * You can make bound using {@code max()} and {@code min()} methods.
 * You can also make include conditions using {@code inclusiveMin()},
 * {@code exclusiveMin()}, {@code inclusiveMax()} and {@code exclusiveMax()} methods.
 * <p>
 * this using {@link Random} class to generate base random value. So, it might be slower
 * than {@link Random} class.
 */
public class RandomFloat {
    private final Random random = new Random();
    // 실수형 연산 오류로 인하여 1,000,000 을 기준으로 나눈다.
    private static final float DECIMAL_PLACES = 1000000f;
    // max value refer to @link java.util.Random class nextFloat method
    private static final float DEFAULT_MAX_VALUE = 1f;
    private float max = DEFAULT_MAX_VALUE;
    private boolean inclusiveMax = false;
    // min value refer to Random class nextFloat method
    private static final float DEFAULT_MIN_VALUE = 0f;
    private float min = DEFAULT_MIN_VALUE;
    private boolean exclusiveMin = false;

    /**
     * generate new random number by conditions.
     *
     * @return random float value
     */
    public float random() {
        float baseValue = randomByInclusiveConditions();
        return calculateUsingMinAndMaxValues(baseValue);
    }

    private float calculateUsingMinAndMaxValues(float baseValue) {
        if (min != DEFAULT_MIN_VALUE && max != DEFAULT_MAX_VALUE) {
            return min + baseValue * (max - min);
        } else if (min != DEFAULT_MIN_VALUE) {
            return min + baseValue;
        } else if (max != DEFAULT_MAX_VALUE) {
            return baseValue * max;
        } else {
            return baseValue;
        }
    }

    /**
     * Returns the random value from the range 0.0f to 1.0f by inclusive/exclusive conditions.
     *
     * @return random float value
     */
    private float randomByInclusiveConditions() {
        float value;
        if (exclusiveMin && inclusiveMax) {
            value = exclusiveZeroAndInclusiveOne();
        } else if (inclusiveMax) {
            value = inclusiveZeroAndInclusiveOne();
        } else if (exclusiveMin) {
            value = exclusiveZeroAndExclusiveOne();
        } else {
            value = random.nextFloat();
        }
        return value;
    }

    /**
     * Returns the random value from the range 0.0f (**Exclusive**) to 1.0f (**Inclusive**).
     *
     * @return random float value
     */
    private float exclusiveZeroAndInclusiveOne() {
        return (randomInt((int) DECIMAL_PLACES) + 1) / DECIMAL_PLACES;
    }

    /**
     * Returns the random value from the range 0.0f (**Inclusive**) to 1.0f (**Inclusive**).
     *
     * @return random float value
     */
    private float inclusiveZeroAndInclusiveOne() {
        return randomInt((int) DECIMAL_PLACES + 1) / DECIMAL_PLACES;
    }

    /**
     * Returns the random value from the range 0.0f (**Exclusive**) to 1.0f (**Exclusive**).
     *
     * @return random float value
     */
    private float exclusiveZeroAndExclusiveOne() {
        return (randomInt((int) DECIMAL_PLACES - 1) + 1) / DECIMAL_PLACES;
    }

    public RandomFloat min(float min) {
        this.min = min;
        return this;
    }

    public RandomFloat max(float max) {
        this.max = max;
        return this;
    }

    public RandomFloat inclusiveMax() {
        inclusiveMax = true;
        return this;
    }

    public RandomFloat exclusiveMax() {
        inclusiveMax = false;
        return this;
    }

    public RandomFloat inclusiveMin() {
        exclusiveMin = false;
        return this;
    }

    public RandomFloat exclusiveMin() {
        exclusiveMin = true;
        return this;
    }

    private int randomInt(int bound) {
        return random.nextInt(bound);
    }
}
