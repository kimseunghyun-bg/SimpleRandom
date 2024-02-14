package com.seunghyun.simpleRandom;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RandomFloatTest {

    @Test
    void testRandom() {
        //given
        float min = 0;
        float max = 1;
        //when
        float actual = new RandomFloat().random();
        //then
        assertThat(actual).isBetween(min, max);
    }

    @Test
    void testComplexRandom() {
        //given
        float min = -10f;
        float max = 100f;
        float actual = 0;
        //when
        for (int i = 0; i < 500000000; i++) {
            actual = new RandomFloat()
                    .min(min).exclusiveMin()
                    .max(max).inclusiveMax()
                    .random();
            if (actual <= min || actual > max) {
                break;
            }
        }
        //then
        assertThat(actual).isGreaterThan(min);
        assertThat(actual).isLessThanOrEqualTo(max);
    }


    @Nested
    @DisplayName("Test random exclusive min value")
    class testExclusiveMin {

        @Test
        @DisplayName("최솟값 0을 포함하지 않는 난수")
        void Random_exclusive_min_as_zero_value() {
            //given
            float min = 0;
            //when
            RandomFloat randomFloat = new RandomFloat().min(min).exclusiveMin();
            float actual = randomLoopToTarget(randomFloat, min);
            //then
            assertThat(actual).isNotEqualTo(min);
        }

        @Test
        @DisplayName("양의 최솟값 1.1을 포함하지 않는 난수")
        void Random_exclusive_min_as_positive_one_dot_one_value() {
            //given
            float min = 1.1f;
            //when
            RandomFloat randomFloat = new RandomFloat().min(min).exclusiveMin();
            float actual = randomLoopToTarget(randomFloat, min);
            //then
            assertThat(actual).isNotEqualTo(min);
        }

        @Test
        @DisplayName("음의 최솟값 -1.1을 포함하지 않는 난수")
        void Random_exclusive_min_as_negative_one_dot_one_value() {
            //given
            float min = -1.1f;
            //when
            RandomFloat randomFloat = new RandomFloat().min(min).exclusiveMin();
            float actual = randomLoopToTarget(randomFloat, min);
            //then
            assertThat(actual).isNotEqualTo(min);
        }
    }

    @Nested
    @DisplayName("Test random inclusive min value")
    class testInclusiveMin {

        @Test
        @DisplayName("최솟값 0을 포함하는 난수")
        void Random_inclusive_min_as_zero_value() {
            //given
            float min = 0;
            //when
            RandomFloat randomFloat = new RandomFloat().min(min);
            float actual = randomLoopToTarget(randomFloat, min);
            //then
            assertThat(actual).isEqualTo(min);
        }

        @Test
        @DisplayName("양의 최솟값 1.1을 포함하는 난수")
        void Random_inclusive_min_as_positive_one_dot_one_value() {
            //given
            float min = 1.1f;
            //when
            RandomFloat randomFloat = new RandomFloat().min(min);
            float actual = randomLoopToTarget(randomFloat, min);
            //then
            assertThat(actual).isEqualTo(min);
        }

        @Test
        @DisplayName("음의 최솟값 -1.1을 포함하는 난수")
        void Random_inclusive_min_as_negative_one_dot_one_value() {
            //given
            float min = -1.1f;
            //when
            RandomFloat randomFloat = new RandomFloat().min(min);
            float actual = randomLoopToTarget(randomFloat, min);
            //then
            assertThat(actual).isEqualTo(min);
        }
    }

    @Nested
    @DisplayName("Test random exclusive max value")
    class testExclusiveMax {

        @Test
        @DisplayName("최대값 0.5을 포함하지 않는 난수")
        void Random_exclusive_max_as_zero_dot_five_value() {
            //given
            float max = 0.5f;
            //when
            RandomFloat randomFloat = new RandomFloat().max(max);
            float actual = randomLoopToTarget(randomFloat, max);
            //then
            assertThat(actual).isNotEqualTo(max);
        }

        @Test
        @DisplayName("양의 최대값 1.1을 포함하지 않는 난수")
        void Random_exclusive_max_as_positive_one_dot_one_value() {
            //given
            float max = 1.1f;
            //when
            RandomFloat randomFloat = new RandomFloat().max(max);
            float actual = randomLoopToTarget(randomFloat, max);
            //then
            assertThat(actual).isNotEqualTo(max);
        }

        @Test
        @DisplayName("음의 최대값 -1.1을 포함하지 않는 난수")
        void Random_exclusive_max_as_negative_one_dot_one_value() {
            //given
            float max = -1.1f;
            //when
            RandomFloat randomFloat = new RandomFloat().max(max);
            float actual = randomLoopToTarget(randomFloat, max);
            //then
            assertThat(actual).isNotEqualTo(max);
        }
    }

    @Nested
    @DisplayName("Test random inclusive max value")
    class testInclusiveMax {

        @Test
        @DisplayName("최대값 0.5을 포함하는 난수")
        void Random_inclusive_max_as_zero_dot_five_value() {
            //given
            float max = 0.5f;
            //when
            RandomFloat randomFloat = new RandomFloat().max(max).inclusiveMax();
            float actual = randomLoopToTarget(randomFloat, max);
            //then
            assertThat(actual).isEqualTo(max);
        }

        @Test
        @DisplayName("양의 최대값 1.1을 포함하는 난수")
        void Random_inclusive_max_as_positive_one_dot_one_value() {
            //given
            float max = 1.1f;
            //when
            RandomFloat randomFloat = new RandomFloat().max(max).inclusiveMax();
            float actual = randomLoopToTarget(randomFloat, max);
            //then
            assertThat(actual).isEqualTo(max);
        }

        @Test
        @DisplayName("음의 최대값 -1.1을 포함하는 난수")
        void Random_inclusive_max_as_negative_one_dot_one_value() {
            //given
            float max = -1.1f;
            //when
            RandomFloat randomFloat = new RandomFloat().max(max).inclusiveMax();
            float actual = randomLoopToTarget(randomFloat, max);
            //then
            assertThat(actual).isEqualTo(max);
        }

    }

    private float randomLoopToTarget(RandomFloat randomFloat, float target) {
        int count = 500000000;
        float value = 0;
        for (int i = 0; i < count; i++) {
            value = randomFloat.random();
            if (value == target) {
                return value;
            }
        }
        return value;
    }

}