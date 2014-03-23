package exampleprogs;

import AST.*;
import java.io.*;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class Compiler {

	public static List<CompilationUnit> compile(String indir) {

		File inputdir = new File(indir);
		Collection<File> inputFiles = new LinkedList<File>();
		inputFiles.add(inputdir);
		inputFiles = readFiles(inputFiles, new String[] { ".tlang" });

		List<CompilationUnit> compUnits = new List<CompilationUnit>();
		CompilationUnit cu = null;
		for (File currentFile : inputFiles) {
			cu = parse(currentFile);
			compUnits.add(cu);
		}
		
		return compUnits;

	}

	public static String getFilename(String[] args) {
		if (args.length != 2) {
			System.out.println("Usage: java Compiler filename, outfile");
			System.exit(1);
		}
		return args[0];
	}

	public static CompilationUnit parse(File file) {
		try {
			FileReader reader = new FileReader(file);
			TLangScanner scanner = new TLangScanner(reader);
			TLangParser parser = new TLangParser();
			CompilationUnit result = (CompilationUnit) parser.parse(scanner);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void prettyPrint(List<CompilationUnit> compUnits,
			String outfile) {
		for (CompilationUnit cu : compUnits) {
			File out = new File(cu.getClassDeclaration(0).qname() + ".txt");
			Writer writer = null;
			try {
				writer = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(outfile + '/' + out.getName()),
						"utf-8"));
				cu.pp(writer);
			} catch (IOException e) {
				System.out.println("whatever");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Given a Collection of file handlers and an array of file endings as
	 * filter strings this methods filters the given file handlers according to
	 * their file endings. If a File object points to a directory, it will be
	 * recursively inspected.
	 */
	public static Collection<File> readFiles(Collection<File> files,
			String[] filter) {
		LinkedList<File> resultList = new LinkedList<File>();
		for (File file : files) {
			if (file.exists()) {
				if (file.isDirectory()) {
					Collection<File> subFiles = new LinkedList<File>();
					Collections.addAll(subFiles, file.listFiles());
					resultList.addAll(readFiles(subFiles, filter));
				} else if (filter.length == 0) {
					resultList.add(file);
				} else {
					for (int k = 0; k < filter.length; k++) {
						if (file.getName().endsWith(filter[k]))
							resultList.add(file);
					}
				}
			}
		}
		return resultList;
	}
}
