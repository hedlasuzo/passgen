package checks;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import passgen.CharGroup;

class EntropyTest {

    @ParameterizedTest
    @MethodSource
    void calc(int len, int result) {
        int actual = Entropy.calc(len, CharGroup.binary);

        assertEquals(result, actual);
    }

    static Stream<Arguments> calc() {
        return Stream.of(
            Arguments.arguments(1, 1),
            Arguments.arguments(2, 2),
            Arguments.arguments(8, 8),
            Arguments.arguments(10, 10),
            Arguments.arguments(100, 100),
            Arguments.arguments(1000, 999)
        );
    }

}
