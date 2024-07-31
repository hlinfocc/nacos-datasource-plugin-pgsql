/**
 * 
 */
package net.hlinfo.nacos.plugin.datasource.enums.pgsql;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hlinfo
 *
 */
public enum TrustedPgsqlFunctionEnum {

	 /**
     * NOW().
     */
    NOW("NOW()", "NOW(3)");

    private static final Map<String, TrustedPgsqlFunctionEnum> LOOKUP_MAP = new HashMap<>();

    static {
        for (TrustedPgsqlFunctionEnum entry : TrustedPgsqlFunctionEnum.values()) {
            LOOKUP_MAP.put(entry.functionName, entry);
        }
    }

    private final String functionName;

    private final String function;

    TrustedPgsqlFunctionEnum(String functionName, String function) {
        this.functionName = functionName;
        this.function = function;
    }

    /**
     * Get the function name.
     *
     * @param functionName function name
     * @return function
     */
    public static String getFunctionByName(String functionName) {
    	TrustedPgsqlFunctionEnum entry = LOOKUP_MAP.get(functionName);
        if (entry != null) {
            return entry.function;
        }
        throw new IllegalArgumentException(String.format("Invalid function name: %s", functionName));
    }
}
