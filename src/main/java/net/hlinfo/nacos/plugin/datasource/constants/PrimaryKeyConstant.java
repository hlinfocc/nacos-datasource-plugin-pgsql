package net.hlinfo.nacos.plugin.datasource.constants;

public class PrimaryKeyConstant {
	 /**
     * replace lower Statement.RETURN_GENERATED_KEYS into id key.
     */
    public static final String[] LOWER_RETURN_PRIMARY_KEYS = new String[]{"id"};

    /**
     * upper replace Statement.RETURN_GENERATED_KEYS into id key.
     * using dameng database.
     */
    public static final String[] UPPER_RETURN_PRIMARY_KEYS = new String[]{"ID"};
}
