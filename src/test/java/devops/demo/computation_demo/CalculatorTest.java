package devops.demo.computation_demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

public class CalculatorTest {

	private static int testCounter = 0;
	private Calculator calculator;

	@BeforeAll
	static void setupAll() {
		System.out.println("[Before All] Calculator Test suite starting ... by cjw2025");
	}

	@BeforeEach
	void setup() {
		testCounter++;
		calculator = new Calculator();
	}

	@AfterEach
	void tearDown() {
	}

	@AfterAll
	static void tearDownAll() {
		System.out.println("\n[After All] completed " + testCounter + " test invocations by cjw2025.");
	}

	// Task 1: add() method using @MethodSource
	@ParameterizedTest
	@MethodSource("addTestData")
	void testAdd(int input1, int input2, int expected) {
		System.out.println(
				"[Before Each] Starting Test #" + testCounter + ": " + input1 + " + " + input2 + " = " + expected);
		int result = calculator.add(input1, input2);
		assertEquals(expected, result);
		System.out.println(
				"[After Each] Finished Test #" + testCounter + ": " + input1 + " + " + input2 + " = " + expected);
	}

	static Stream<Arguments> addTestData() {
		return Stream.of(Arguments.of(100, 2, 102), Arguments.of(100, -2, 98), Arguments.of(-100, 2, -98),
				Arguments.of(-100, -2, -102));
	}

	// Task 3: multiple() method using @CsvFileSource
	@ParameterizedTest
	@CsvFileSource(resources = "/data/multiply_test_data.csv", numLinesToSkip = 1)
	void testMultiple(int input1, int input2, int expected) {
		System.out.println("[Before Each] Starting Test #" + testCounter + ": \"" + input1 + "\" * \"" + input2
				+ "\" = \"" + expected + "\"");
		int result = calculator.multiple(input1, input2);
		assertEquals(expected, result);
		System.out.println("[After Each] Finished Test #" + testCounter + ": \"" + input1 + "\" * \"" + input2
				+ "\" = \"" + expected + "\"");
	}

	// Task 2: subtract() method using @CsvSource
	@ParameterizedTest
	@CsvSource({ "100, 2, 98", "100, -2, 102", "-100, 2, -102", "-100, -2, -98" })
	void testSubtract(int input1, int input2, int expected) {
		System.out.println("[Before Each] Starting Test #" + testCounter + ": \"" + input1 + "\" - \"" + input2
				+ "\" = \"" + expected + "\"");
		int result = calculator.substract(input1, input2);
		assertEquals(expected, result);
		System.out.println("[After Each] Finished Test #" + testCounter + ": \"" + input1 + "\" - \"" + input2
				+ "\" = \"" + expected + "\"");
	}

	// Task 4: divide() method - ONE Negative Test
	@Test
	void testDivide_byZero() {
		System.out.println("[Before Each] Starting Test #" + testCounter + ": divide_byZero()");
		assertThrows(IllegalArgumentException.class, () -> {
			calculator.divide(100, 0);
		});
		System.out.println("[After Each] Finished Test #" + testCounter + ": divide_byZero()");
	}
	
	// Task 5: divide() method - Positive test cases
	@ParameterizedTest
	@CsvSource({
	    "100, 2, 50",
	    "100, -2, -50",
	    "-100, 2, -50",
	    "-100, -2, 50",
	    "0, 5, 0"
	})
	void testDivide(int input1, int input2, int expected) {
	    System.out.println("[Before Each] Starting Test #" + testCounter + ": \"" + input1 + "\" / \"" + input2
	            + "\" = \"" + expected + "\"");
	    int result = calculator.divide(input1, input2);
	    assertEquals(expected, result);
	    System.out.println("[After Each] Finished Test #" + testCounter + ": \"" + input1 + "\" / \"" + input2
	            + "\" = \"" + expected + "\"");
	}
	
}