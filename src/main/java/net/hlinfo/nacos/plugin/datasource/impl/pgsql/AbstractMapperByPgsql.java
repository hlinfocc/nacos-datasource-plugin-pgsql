/**
 * 
 */
package net.hlinfo.nacos.plugin.datasource.impl.pgsql;

import com.alibaba.nacos.plugin.datasource.mapper.AbstractMapper;

import net.hlinfo.nacos.plugin.datasource.enums.pgsql.TrustedPgsqlFunctionEnum;

/**
 * @author hlinfo
 *
 */
public abstract class AbstractMapperByPgsql extends AbstractMapper {

	@Override
    public String getFunction(String functionName) {
        return TrustedPgsqlFunctionEnum.getFunctionByName(functionName);
    }

}
