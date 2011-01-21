package ebank.tools;

import java.util.Hashtable;
import java.util.Vector;

/**
 * @author Vivien DIDELOT
 * @author Alexandre THOMAS
 * Parse command line arguments.
 */
public class ArgumentParser {
    
    private Vector<String> params = new Vector<String>();
    private Hashtable<String, String> options = new Hashtable<String, String>();
    private int paramIndex = 0;
    
    /**
     * Create a new ArgumentParser
     * @param args command line arguments to parse
     */
    public ArgumentParser(String[] args) {
        
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("--")) {
                int loc = args[i].indexOf("=");
                String key = (loc > 0) ? args[i].substring(2, loc) : args[i].substring(2);
                String value = (loc > 0) ? args[i].substring(loc+1) : "";
                options.put(key.toLowerCase(), value);
            }
            else params.addElement(args[i]);
        }
    }

    /**
     * Check if args have an option.
     * @param opt to check
     * @return true if opt is present, else false.
     */
    public boolean hasOption(String opt) {
        return options.containsKey(opt.toLowerCase());
    }

    /**
     * Get the argument of a option. i.e. the right side of a --option=arg option.
     * @param opt
     * @return the argument of the option (i.e. arg).
     */
    public String getOption(String opt) {
        return (String) options.get(opt.toLowerCase());
    }

    /**
     * Get the next parameter
     * @return the next parameter if present, else null.
     */
    public String nextParam() {
        if (paramIndex < params.size()) {
            return (String) params.elementAt(paramIndex++);
        }
        return null;
    }
}
