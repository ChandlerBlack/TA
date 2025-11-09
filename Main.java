// Author - https://github.com/JimBuffenbarger/dws
// This is the main class/method for the interpreter/compiler.
// Each command-line argument is a complete program,
// which is scanned, parsed, and evaluated.
// All evaluations share the same environment,
// so they can share variables.

public class Main {

	/**
	 * The main method for the translator. It initializes the parser and environment.
	 * It iterates through each command-line argument (a complete program), 
	 * parses it into an AST, interprets it (eval), and compiles it (code), 
	 * sharing a single Environment across all programs.
	 * * @param args An array of source programs passed as command-line arguments.
	*/
	public static void main(String[] args) {
		Parser parser=new Parser();
		Environment env=new Environment();
		String code="";
		for (String prog: args)
			try {
				Node node=parser.parse(prog);
				node.eval(env);
				code+=node.code();
			} catch (Exception e) {
				System.err.println(e);
			}
		new Code(code,env);
	}

}
