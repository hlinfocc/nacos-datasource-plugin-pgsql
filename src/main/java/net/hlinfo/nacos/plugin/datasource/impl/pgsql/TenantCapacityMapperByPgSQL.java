package net.hlinfo.nacos.plugin.datasource.impl.pgsql;

import com.alibaba.nacos.common.utils.CollectionUtils;
import com.alibaba.nacos.plugin.datasource.constants.FieldConstant;
import com.alibaba.nacos.plugin.datasource.constants.TableConstant;
import com.alibaba.nacos.plugin.datasource.mapper.AbstractMapper;
import com.alibaba.nacos.plugin.datasource.mapper.TenantCapacityMapper;
import com.alibaba.nacos.plugin.datasource.model.MapperContext;
import com.alibaba.nacos.plugin.datasource.model.MapperResult;

import net.hlinfo.nacos.plugin.datasource.constants.DataBaseSourceConstant;

/**
 * The pgsql implementation of TenantCapacityMapper.
 *
 * @author hlinfo
 **/

public class TenantCapacityMapperByPgSQL extends AbstractMapper implements TenantCapacityMapper {
    
    @Override
    public String getTableName() {
        return TableConstant.TENANT_CAPACITY;
    }
    
    @Override
    public String getDataSource() {
        return DataBaseSourceConstant.PGSQL;
    }

	@Override
	public MapperResult getCapacityList4CorrectUsage(MapperContext context) {
		String sql = "SELECT id, tenant_id FROM tenant_capacity WHERE id>? LIMIT ?";
        return new MapperResult(sql, CollectionUtils.list(context.getWhereParameter(FieldConstant.ID),
                context.getWhereParameter(FieldConstant.LIMIT_SIZE)));
	}
}
