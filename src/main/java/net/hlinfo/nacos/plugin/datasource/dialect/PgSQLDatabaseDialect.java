package net.hlinfo.nacos.plugin.datasource.dialect;

import net.hlinfo.nacos.plugin.datasource.constants.DataBaseSourceConstant;
import net.hlinfo.nacos.plugin.datasource.constants.PrimaryKeyConstant;

/**
 * PostgreSQL database dialect.
 */
public class PgSQLDatabaseDialect implements DatabaseDialect {

    @Override
    public String getType() {
        return DataBaseSourceConstant.PGSQL;
    }
    
    @Override
    public String[] getReturnPrimaryKeys() {
        return PrimaryKeyConstant.LOWER_RETURN_PRIMARY_KEYS;
    }

}
