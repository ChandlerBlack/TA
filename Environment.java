
// Author - https://github.com/JimBuffenbarger/dws
import java.util.HashMap;

// This class provides a stubbed-out environment.
// You are expected to implement the methods.
// Accessing an undefined variable should throw an exception.

// Hint!
// Use the Java API to implement your Environment.
// Browse:
//   https://docs.oracle.com/javase/tutorial/tutorialLearningPaths.html
// Read about Collections.
// Focus on the Map interface and HashMap implementation.
// Also:
//   https://www.tutorialspoint.com/java/java_map_interface.htm
//   http://www.javatpoint.com/java-map
// and elsewhere.

public class Environment {

	private HashMap<String, Double> map = new HashMap<>();

	/**
	 * Puts a variable and its value into the environment. 
	 * This method is used by the interpreter (eval) for assignment statements.
	 * You must implement storage here to fix the multi-statement issue.
	 * * @param var The name of the variable (identifier).
	 * @param val The value to be assigned to the variable.
	 * @return The value assigned (val).
	 */
	public double put(String var, Double val) {
		map.put(var, val);
		return val;
	}
	/**
	 * Retrieves the value of a variable from the environment.
	 * This method is used by the interpreter (eval) for identifiers (NodeFactId).
	 * Accessing an undefined variable must throw an EvalException.
	 * * @param pos The position in the source program (for error reporting).
	 * @param var The name of the variable (identifier).
	 * @return The current value of the variable.
	 * @throws EvalException If the variable is not defined.
	 */
	public double get(int pos, String var) throws EvalException {
		if (!map.containsKey(var)) {
			throw new EvalException(pos, "Undefined variable" + var);
		}
		return map.get(var);
	}
	/**
	 * Generates the C code string for variable declarations.
	 * This is used by the compiler (Code.java) to create global C variables.
	 * * @return A C code string containing variable declarations (e.g., "int x,y;").
	 */
	public String toC() {
		String s = "";
		String sep = " ";
		// Collecting all variables for C 
		for (String v : map.keySet()) {
			s += sep + v;
			sep = ",";
		}
		return s == "" ? "" : "double" + s + ";\nx=0;x=x;\n";
	}

}
