package mn.edu.must.sqat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ScholarshipCalculatorTest {

    private final ScholarshipCalculator calc = new ScholarshipCalculator();

    // --- Happy path (Part A) ---
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

    // --- Part B: letterGrade & gpa remaining branches ---
    @Test
    void letterGradeOutOfRangeThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(-1));
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(101));
    }

    @Test
    void letterGradeOtherGrades() {
        assertEquals("B", calc.letterGrade(85));
        assertEquals("C", calc.letterGrade(75));
        assertEquals("D", calc.letterGrade(65));
        assertEquals("F", calc.letterGrade(50));
    }

    @Test
    void gpaSwitchAllCases() {
        assertEquals(2.0, calc.gpa(new double[]{95, 85, 75, 65, 50}), 1e-6);
    }

    // --- Part B: scholarship CFG edges ---
    @Test
    void scholarshipInvalidGpaLowThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.scholarship(-0.1, 15, false));
    }

    @Test
    void scholarshipInvalidGpaHighThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.scholarship(4.1, 15, false));
    }

    @Test
    void scholarshipInvalidCreditsThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.scholarship(3.5, -1, false));
    }

    @Test
    void scholarshipViolationReturnsZero() {
        assertEquals(0, calc.scholarship(4.0, 18, true));
    }

    @Test
    void scholarshipFullHighGpaLowCreditsFallsThrough() {
        assertEquals(70, calc.scholarship(3.9, 14, false));
    }

    @Test
    void scholarshipSeventyPercent() {
        assertEquals(70, calc.scholarship(3.5, 12, false));
    }

    @Test
    void scholarshipSeventyHighGpaLowCreditsFallsThrough() {
        assertEquals(50, calc.scholarship(3.5, 11, false));
    }

    @Test
    void scholarshipLowGpaReturnsZero() {
        assertEquals(0, calc.scholarship(2.5, 16, false));
    }

    // --- Part C: Loop boundary tests (0, 1, 2+ and null/empty) ---
    @Test
    void gpaNullThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.gpa(null));
    }

    @Test
    void gpaEmptyZeroIterationsThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.gpa(new double[]{}));
    }

    @Test
    void gpaOneIteration() {
        assertEquals(4.0, calc.gpa(new double[]{95}), 1e-6);
    }

    @Test
    void gpaMultipleIterations() {
        assertEquals(2.5, calc.gpa(new double[]{95, 85, 75, 65}), 1e-6);
    }

    // --- Part D: Boundary value tests ---
    @Test
    void letterGradeBoundaryValues() {
        assertEquals("D", calc.letterGrade(60));
        assertEquals("F", calc.letterGrade(59.99));
        assertEquals("D", calc.letterGrade(69.99));
        assertEquals("F", calc.letterGrade(0));
        assertEquals("A", calc.letterGrade(100));
        assertEquals("B", calc.letterGrade(89.99));
        assertEquals("A", calc.letterGrade(90));
    }
}