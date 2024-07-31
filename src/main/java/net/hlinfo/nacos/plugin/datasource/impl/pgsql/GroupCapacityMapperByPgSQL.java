package net.hlinfo.nacos.plugin.datasource.impl.pgsql;

import com.alibaba.nacos.common.utils.CollectionUtils;
import com.alibaba.nacos.plugin.datasource.constants.FieldConstant;
import com.alibaba.nacos.plugin.datasource.constants.TableConstant;
import com.alibaba.nacos.plugin.datasource.mapper.AbstractMapper;
import com.alibaba.nacos.plugin.datasource.mapper.GroupCapacityMapper;
import com.alibaba.nacos.plugin.datasource.model.MapperContext;
import com.alibaba.nacos.plugin.datasource.model.MapperResult;

import net.hlinfo.nacos.plugin.datasource.constants.DataBaseSourceConstant;

/**
 * The pgsql implementation of {@link GroupCapacityMapper}.
 *
 * @author hlinfo
 */
public class GroupCapacityMapperByPgSQL extends AbstractMapperByPgsql implements GroupCapacityMapper {
    
    @Override
    public String getTableName() {
        return TableConstant.GROUP_CAPACITY;
    }
    
    @Override
    public String getDataSource() {
        return DataBaseSourceConstant.PGSQL;
    }
    
	@Override
	public MapperResult selectGroupInfoBySize(MapperContext context) {
		String sql = "SELECT id, group_id FROM group_capacity WHERE id > ? LIMIT ?";
        return new MapperResult(sql, CollectionUtils.list(context.getWhereParameter(FieldConstant.ID), context.getPageSize()));
	}
}

