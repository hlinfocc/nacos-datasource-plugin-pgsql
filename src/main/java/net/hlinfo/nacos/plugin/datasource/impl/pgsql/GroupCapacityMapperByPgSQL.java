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
public class GroupCapacityMapperByPgSQL extends AbstractMapper implements GroupCapacityMapper {
    
    @Override
    public String getTableName() {
        return TableConstant.GROUP_CAPACITY;
    }
    
    @Override
    public String getDataSource() {
        return DataBaseSourceConstant.PGSQL;
    }
    
    @Deprecated
    public String insertIntoSelect() {
        return "INSERT INTO group_capacity (group_id, quota, `usage`, `max_size`, max_aggr_count, max_aggr_size,gmt_create,"
                + " gmt_modified) SELECT ?, ?, count(*), ?, ?, ?, ?, ? FROM config_info";
    }
    
    @Deprecated
    public String insertIntoSelectByWhere() {
        return "INSERT INTO group_capacity (group_id, quota,`usage`, `max_size`, max_aggr_count, max_aggr_size, gmt_create,"
                + " gmt_modified) SELECT ?, ?, count(*), ?, ?, ?, ?, ? FROM config_info WHERE group_id=? AND tenant_id = ''";
    }
    
    @Deprecated
    public String incrementUsageByWhereQuotaEqualZero() {
        return "UPDATE group_capacity SET `usage` = `usage` + 1, gmt_modified = ? WHERE group_id = ? AND `usage` < ? AND quota = 0";
    }
    
    @Deprecated
    public String incrementUsageByWhereQuotaNotEqualZero() {
        return "UPDATE group_capacity SET `usage` = `usage` + 1, gmt_modified = ? WHERE group_id = ? AND `usage` < quota AND quota != 0";
    }
    
    @Deprecated
    public String incrementUsageByWhere() {
        return "UPDATE group_capacity SET `usage` = `usage` + 1, gmt_modified = ? WHERE group_id = ?";
    }
    
    @Deprecated
    public String decrementUsageByWhere() {
        return "UPDATE group_capacity SET `usage` = `usage` - 1, gmt_modified = ? WHERE group_id = ? AND `usage` > 0";
    }
    
    @Deprecated
    public String updateUsage() {
        return "UPDATE group_capacity SET `usage` = (SELECT count(*) FROM config_info), gmt_modified = ? WHERE group_id = ?";
    }
    
    @Deprecated
    public String updateUsageByWhere() {
        return "UPDATE group_capacity SET `usage` = (SELECT count(*) FROM config_info WHERE group_id=? AND tenant_id = ''),"
                + " gmt_modified = ? WHERE group_id= ?";
    }
    
    @Deprecated
    public String selectGroupInfoBySize() {
        return "SELECT id, group_id FROM group_capacity WHERE id > ? LIMIT ?";
    }

	@Override
	public MapperResult selectGroupInfoBySize(MapperContext context) {
		String sql = "SELECT id, group_id FROM group_capacity WHERE id > ? LIMIT ?";
        return new MapperResult(sql, CollectionUtils.list(context.getWhereParameter(FieldConstant.ID), context.getPageSize()));
	}
}

