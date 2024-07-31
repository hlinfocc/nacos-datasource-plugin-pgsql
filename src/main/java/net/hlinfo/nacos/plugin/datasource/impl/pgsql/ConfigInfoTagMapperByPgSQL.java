package net.hlinfo.nacos.plugin.datasource.impl.pgsql;

import java.util.Collections;

import com.alibaba.nacos.plugin.datasource.constants.TableConstant;
import com.alibaba.nacos.plugin.datasource.mapper.AbstractMapper;
import com.alibaba.nacos.plugin.datasource.mapper.ConfigInfoTagMapper;
import com.alibaba.nacos.plugin.datasource.model.MapperContext;
import com.alibaba.nacos.plugin.datasource.model.MapperResult;

import net.hlinfo.nacos.plugin.datasource.constants.DataBaseSourceConstant;

/**
 * The pgsql implementation of ConfigInfoTagMapper.
 *
 * @author hlinfo
 **/

public class ConfigInfoTagMapperByPgSQL extends AbstractMapperByPgsql implements ConfigInfoTagMapper {
    
	@Deprecated
    public String getTableName() {
        return TableConstant.CONFIG_INFO_TAG;
    }
    
    @Override
    public String getDataSource() {
        return DataBaseSourceConstant.PGSQL;
    }

	@Override
	public MapperResult findAllConfigInfoTagForDumpAllFetchRows(MapperContext context) {
		String sql = " SELECT t.id,data_id,group_id,tenant_id,tag_id,app_name,content,md5,gmt_modified "
                + " FROM (  SELECT id FROM config_info_tag  ORDER BY id LIMIT " + context.getPageSize() + " offset "
                + context.getStartRow() + " ) " + "g, config_info_tag t  WHERE g.id = t.id  ";
        return new MapperResult(sql, Collections.emptyList());
	}
}
