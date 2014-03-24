package exampleprogs;

import java.util.Collection;
import java.util.LinkedList;

import AST.*;

public class Main {

	private static List<CompilationUnit> compUnits = null;

	public static void main(String[] args) {
		String indir = getFilename(args);
		String outdir = args[1];

		mainOneLoop(indir);

		final long start = new java.util.Date().getTime();

		for (int i = 0; i < 1; i++) {
			mainOneLoop(indir);
		}

		final long end = new java.util.Date().getTime();
		System.out.println("\nExecution Time: " + (end - start) / 1 + "ms");

		//System.out.println("rewrites count: " + ASTNode.rewritesCount);
		Compiler.prettyPrint(compUnits, outdir);
	}

	private static void mainOneLoop(String indir) {

		compUnits = Compiler.compile(indir);

		CompositionProgram cp = CompositionLangCompiler
				.compile("in/composition.txt");
		assert (cp != null);

		Root root = new Root(compUnits, cp);

		try {
			root.checkWellformedness();
		} catch (CompositionException e) {
			System.out.println(e.getMessage());
		}

	}

	public static String getFilename(String[] args) {
		if (args.length != 2) {
			System.out
					.println("Usage: java Compiler: infile, outfile");
			System.exit(1);
		}
		return args[0];
	}

}
