package exampleprogs;

import java.io.FileReader;

import AST.CompositionProgram;
import AST.CompLangParser;
import AST.CompLangScanner;

public class CompositionLangCompiler {
	
public static CompositionProgram compile(String infile) {
        
        // Construct the AST
	CompositionProgram cp = parse(infile); 
        return cp;

    }
    
    
    public static CompositionProgram parse(String filename) {
        try {
            FileReader reader = new FileReader(filename);
            CompLangScanner scanner = new CompLangScanner(reader);
            CompLangParser parser = new CompLangParser();
            CompositionProgram result = (CompositionProgram)parser.parse(scanner);
            return result;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
