package net.hlinfo.nacos.plugin.datasource.impl.pgsql;

import com.alibaba.nacos.common.utils.CollectionUtils;
import com.alibaba.nacos.plugin.datasource.constants.FieldConstant;
import com.alibaba.nacos.plugin.datasource.constants.TableConstant;
import com.alibaba.nacos.plugin.datasource.mapper.AbstractMapper;
import com.alibaba.nacos.plugin.datasource.mapper.ConfigInfoAggrMapper;
import com.alibaba.nacos.plugin.datasource.model.MapperContext;
import com.alibaba.nacos.plugin.datasource.model.MapperResult;

import net.hlinfo.nacos.plugin.datasource.constants.DataBaseSourceConstant;

import java.util.List;

/**
 * The pgsql implementation of ConfigInfoAggrMapper.
 *
 * @author hlinfo
 **/

public class ConfigInfoAggrMapperByPgSQL extends AbstractMapper implements ConfigInfoAggrMapper {
	@Deprecated
    public String batchRemoveAggr(List<String> datumList) {
        final StringBuilder datumString = new StringBuilder();
        for (String datum : datumList) {
            datumString.append('\'').append(datum).append("',");
        }
        datumString.deleteCharAt(datumString.length() - 1);
        return "DELETE FROM config_info_aggr WHERE data_id = ? AND group_id = ? AND tenant_id = ? AND datum_id IN ("
                + datumString + ")";
    }
    @Deprecated
    public String aggrConfigInfoCount(int size, boolean isIn) {
        StringBuilder sql = new StringBuilder(
                "SELECT count(*) FROM config_info_aggr WHERE data_id = ? AND group_id = ? AND tenant_id = ? AND datum_id");
        if (isIn) {
            sql.append(" IN (");
        } else {
            sql.append(" NOT IN (");
        }
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sql.append(", ");
            }
            sql.append('?');
        }
        sql.append(')');
        
        return sql.toString();
    }
    @Deprecated
    public String findConfigInfoAggrIsOrdered() {
        return "SELECT data_id,group_id,tenant_id,datum_id,app_name,content FROM "
                + "config_info_aggr WHERE data_id = ? AND group_id = ? AND tenant_id = ? ORDER BY datum_id";
    }
    @Deprecated
    public String findConfigInfoAggrByPageFetchRows(int startRow, int pageSize) {
        return "SELECT data_id,group_id,tenant_id,datum_id,app_name,content FROM config_info_aggr WHERE data_id= ? AND "
                + "group_id= ? AND tenant_id= ? ORDER BY datum_id LIMIT " + pageSize + " offset " + startRow;
    }
    @Deprecated
    public String findAllAggrGroupByDistinct() {
        return "SELECT DISTINCT data_id, group_id, tenant_id FROM config_info_aggr";
    }
    
    @Override
    public String getTableName() {
        return TableConstant.CONFIG_INFO_AGGR;
    }
    
    @Override
    public String getDataSource() {
        return DataBaseSourceConstant.PGSQL;
    }

	@Override
	public MapperResult findConfigInfoAggrByPageFetchRows(MapperContext context) {
		int startRow =  context.getStartRow();
        int pageSize =  context.getPageSize();
        String dataId = (String) context.getWhereParameter(FieldConstant.DATA_ID);
        String groupId = (String) context.getWhereParameter(FieldConstant.GROUP_ID);
        String tenantId = (String) context.getWhereParameter(FieldConstant.TENANT_ID);
        
        String sql = 
                "SELECT data_id,group_id,tenant_id,datum_id,app_name,content FROM config_info_aggr WHERE data_id= ? AND "
                        + "group_id= ? AND tenant_id= ? ORDER BY datum_id LIMIT " + pageSize + " offset " + startRow;
        List<Object> paramList = CollectionUtils.list(dataId, groupId, tenantId);
        return new MapperResult(sql, paramList);
	}
}
