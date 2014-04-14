package exampleprogs;

import java.util.Collection;
import java.util.LinkedList;

import AST.*;

public class Main {

	private static List<CompilationUnit> compUnits = null;

	public static void main(String[] args) {
		String recipe = getFilename(args);
		String indir = args[1];
		String outdir = "out/decl";

		mainOneLoop(recipe, indir);

		final long start = new java.util.Date().getTime();

		for (int i = 0; i < 10; i++) {
			mainOneLoop(recipe, indir);
		}

		final long end = new java.util.Date().getTime();
		System.out.println("\nExecution Time: " + (end - start) / 10 + "ms");

		Compiler.prettyPrint(compUnits, outdir);
	}

	private static void mainOneLoop(String recipe, String indir) {
		
		compUnits = Compiler.compile(indir);

		CompositionProgram cp = CompositionLangCompiler
				.compile(recipe);
		assert (cp != null);

		Root root = new Root(compUnits, cp);

		try {
			root.checkWellformedness();
			//root.traverse();
		} catch (CompositionException e) {
			System.out.println(e.getMessage());
		}

	}

	public static String getFilename(String[] args) {
		if (args.length != 2) {
			System.out
					.println("Usage: composition recipe file, infolder");
			System.exit(1);
		}
		return args[0];
	}

}
