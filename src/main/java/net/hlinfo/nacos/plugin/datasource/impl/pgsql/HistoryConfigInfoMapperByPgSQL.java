package net.hlinfo.nacos.plugin.datasource.impl.pgsql;

import com.alibaba.nacos.common.utils.CollectionUtils;
import com.alibaba.nacos.plugin.datasource.constants.FieldConstant;
import com.alibaba.nacos.plugin.datasource.constants.TableConstant;
import com.alibaba.nacos.plugin.datasource.mapper.AbstractMapper;
import com.alibaba.nacos.plugin.datasource.mapper.HistoryConfigInfoMapper;
import com.alibaba.nacos.plugin.datasource.model.MapperContext;
import com.alibaba.nacos.plugin.datasource.model.MapperResult;

import net.hlinfo.nacos.plugin.datasource.constants.DataBaseSourceConstant;

/**
 * The pgsql implementation of HistoryConfigInfoMapper.
 *
 * @author hlinfo
 **/

public class HistoryConfigInfoMapperByPgSQL extends AbstractMapper implements HistoryConfigInfoMapper {
    
	@Deprecated
    public String removeConfigHistory() {
        return "DELETE FROM his_config_info WHERE gmt_modified < ? LIMIT ?";
    }
    
	@Deprecated
    public String findConfigHistoryCountByTime() {
        return "SELECT count(*) FROM his_config_info WHERE gmt_modified < ?";
    }
    
	@Deprecated
    public String findDeletedConfig() {
        return "SELECT DISTINCT data_id, group_id, tenant_id FROM his_config_info WHERE op_type = 'D' AND gmt_modified >= ? AND gmt_modified <= ?";
    }
    
	@Deprecated
    public String findConfigHistoryFetchRows() {
        return  "SELECT nid,data_id,group_id,tenant_id,app_name,src_ip,src_user,op_type,gmt_create,gmt_modified FROM his_config_info "
                + "WHERE data_id = ? AND group_id = ? AND tenant_id = ? ORDER BY nid DESC";
    }
    
	@Deprecated
    public String detailPreviousConfigHistory() {
        return "SELECT nid,data_id,group_id,tenant_id,app_name,content,md5,src_user,src_ip,op_type,gmt_create,gmt_modified "
                + "FROM his_config_info WHERE nid = (SELECT max(nid) FROM his_config_info WHERE id = ?) ";
    }
    
    @Override
    public String getTableName() {
        return TableConstant.HIS_CONFIG_INFO;
    }
    
    @Override
    public String getDataSource() {
        return DataBaseSourceConstant.PGSQL;
    }

	@Override
	public MapperResult removeConfigHistory(MapperContext context) {
		String sql = "DELETE FROM his_config_info WHERE gmt_modified < ? LIMIT ?";
        return new MapperResult(sql, CollectionUtils.list(context.getWhereParameter(FieldConstant.START_TIME),
                context.getWhereParameter(FieldConstant.LIMIT_SIZE)));
	}

	@Override
	public MapperResult pageFindConfigHistoryFetchRows(MapperContext context) {
		String sql =
                "SELECT nid,data_id,group_id,tenant_id,app_name,src_ip,src_user,op_type,gmt_create,gmt_modified FROM his_config_info "
                        + "WHERE data_id = ? AND group_id = ? AND tenant_id = ? ORDER BY nid DESC  LIMIT "
                        + context.getPageSize() + " offset " + context.getStartRow();
        return new MapperResult(sql, CollectionUtils.list(context.getWhereParameter(FieldConstant.DATA_ID),
                context.getWhereParameter(FieldConstant.GROUP_ID), context.getWhereParameter(FieldConstant.TENANT_ID)));
	}
}
