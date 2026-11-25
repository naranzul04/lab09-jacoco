package mn.edu.must.sqat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ScholarshipCalculatorTest {

    private final ScholarshipCalculator calc = new ScholarshipCalculator();

    @Test
    void letterGradeHappyPath() {
        assertEquals("A", calc.letterGrade(95));
    }

    @Test
    void gpaHappyPath() {
        assertEquals(3.5, calc.gpa(new double[]{95, 85}), 1e-6);
    }

    @Test
    void scholarshipFullHappyPath() {
        assertEquals(100, calc.scholarship(3.9, 16, false));
    }

    @Test
    void scholarshipPartialHappyPath() {
        assertEquals(50, calc.scholarship(3.3, 15, false));
    }
}