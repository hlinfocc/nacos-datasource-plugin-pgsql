package net.hlinfo.nacos.plugin.datasource.dialect;

/**
 * DatabaseDialect interface.
 */
public interface DatabaseDialect {

    /**
     * get database type.
     * @return return database type name
     */
    public String getType();

    /**
     * get database return primary keys.
     * @return return primary keys
     */
    public String[] getReturnPrimaryKeys();

}
