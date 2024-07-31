package net.hlinfo.nacos.plugin.datasource.impl.pgsql;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.nacos.plugin.datasource.constants.TableConstant;
import com.alibaba.nacos.plugin.datasource.mapper.AbstractMapper;
import com.alibaba.nacos.plugin.datasource.mapper.ConfigInfoBetaMapper;
import com.alibaba.nacos.plugin.datasource.model.MapperContext;
import com.alibaba.nacos.plugin.datasource.model.MapperResult;

import net.hlinfo.nacos.plugin.datasource.constants.DataBaseSourceConstant;

/**
 * The pgsql implementation of ConfigInfoBetaMapper.
 *
 * @author hlinfo
 **/

public class ConfigInfoBetaMapperByPgSQL extends AbstractMapperByPgsql implements ConfigInfoBetaMapper {
    
    @Override
    public String getTableName() {
        return TableConstant.CONFIG_INFO_BETA;
    }
    
    @Override
    public String getDataSource() {
        return DataBaseSourceConstant.PGSQL;
    }

	@Override
	public MapperResult findAllConfigInfoBetaForDumpAllFetchRows(MapperContext context) {
		int startRow = context.getStartRow();
        int pageSize = context.getPageSize();
        String sql = " SELECT t.id,data_id,group_id,tenant_id,app_name,content,md5,gmt_modified,beta_ips,encrypted_data_key "
                + " FROM ( SELECT id FROM config_info_beta  ORDER BY id LIMIT " + pageSize + " offset " + startRow  + " )"
                + "  g, config_info_beta t WHERE g.id = t.id ";
        List<Object> paramList = new ArrayList<>();
        paramList.add(startRow);
        paramList.add(pageSize);
        
        return new MapperResult(sql, paramList);
	}
}
