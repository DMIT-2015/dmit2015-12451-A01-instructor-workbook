package dmit2015;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    @Test
    void area_whenCreatedWithValidValues_shouldReturnCorrectArea() {
       var rect = new Rectangle(10,20);

        // JUnit assertions
        assertEquals(200, rect.area());

        // AssertJ assertions
        assertThat(rect.area())
            .isEqualTo(200);
    }

    @Test
    void perimeter_whenCreatedWithValidValues_shouldReturnCorrectPerimeter() {
        var rect = new Rectangle(10,20);

        // JUnit assertions
        assertEquals(60, rect.perimeter());

        // AssertJ assertions
        assertThat(rect.perimeter())
                .isEqualTo(60);
    }

    @Test
    void constructor_whenCreatedWithInvalidLength_shouldThrowException() {
        // JUnit assertion
        assertThrows(RuntimeException.class, () -> {new Rectangle(-1,20);});

        // AssertJ assertion
        assertThatThrownBy(() -> {new Rectangle(-1,20);});
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> {new Rectangle(-1,20);});
    }

    @Test
    void constructor_whenCreatedWithInvalidWidth_shouldThrowException() {
        // JUnit assertion
        assertThrows(RuntimeException.class, () -> {new Rectangle(10,-20);});

        // AssertJ assertion
        assertThatThrownBy(() -> {new Rectangle(10,-20);});
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> {new Rectangle(10,-20);});
    }
}