package net.hlinfo.nacos.plugin.datasource.impl.pgsql;

import com.alibaba.nacos.plugin.datasource.constants.TableConstant;
import com.alibaba.nacos.plugin.datasource.mapper.AbstractMapper;
import com.alibaba.nacos.plugin.datasource.mapper.TenantInfoMapper;

import net.hlinfo.nacos.plugin.datasource.constants.DataBaseSourceConstant;

/**
 * The pgsql implementation of TenantInfoMapper.
 *
 * @author hlinfo
 **/

public class TenantInfoMapperByPgSQL extends AbstractMapper implements TenantInfoMapper {
    
    @Override
    public String getTableName() {
        return TableConstant.TENANT_INFO;
    }
    
    @Override
    public String getDataSource() {
        return DataBaseSourceConstant.PGSQL;
    }
}
