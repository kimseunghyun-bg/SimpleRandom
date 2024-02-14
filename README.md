# Simple Random Library
***
## Goal
- To provide a simple and easy-to-use random number generation library
- To set the maximum and minimum values of the random number to be generated
- to set the inclusive and exclusive conditions of the maximum and minimum values

## Example
***
[Basic usage of the library] <br>
It will generate a random float number between 0.0f(inclusive) and 1.0f(exclusive) <br>
This result will be same the method `nextfloat()` of `java.util.Random` class

```java
import com.seunghyun.simpleRandom.RandomFloat;

public class Main {
    public static void main(String[] args) {
        RandomFloat randomFloat = new RandomFloat();
        System.out.println(randomFloat.random());
    }
}
```

[Setting conditions by method chaining] <br>
`example 01` will generate a random float number between 0.1f(exclusive) and 1.0f(exclusive) <br>
`example 02` will generate a random float number between -10.32f(exclusive) and 821.0f(inclusive) <br>

```java
import com.seunghyun.simpleRandom.RandomFloat;

public class Main {
    public static void main(String[] args) {
        // example 01
        System.out.println(new RandomFloat().min(0.1).exclusiveMin());

        // example 02
        RandomFloat randomFloat = new RandomFloat()
                .min(-10.32).exclusiveMin()
                .max(821).inclusiveMax();
        System.out.println(randomFloat.random());
    }
}
```