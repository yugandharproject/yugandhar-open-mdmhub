package com.yugandhar.YugandharBootProject;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.metadata.AbstractDataSourcePoolMetadata;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;

/**
 * AtomikosDataSourcePoolMetadata provides the datasource pool matrics when spring metrics are enabled.
 * It provides the active pool size, min and Max pool size parameter details.
 * @author Yugandhar
 * @version 2.0
 * @since 2.0
 * 
 */


public class AtomikosDataSourcePoolMetadata extends AbstractDataSourcePoolMetadata<DataSource> {

	public AtomikosDataSourcePoolMetadata(AtomikosDataSourceBean dataSource) {
		super(dataSource);
	}

	@Override
	public Integer getActive() {
		AtomikosDataSourceBean dataSourceBean = (AtomikosDataSourceBean) getDataSource();
		return (dataSourceBean.poolTotalSize());
	}

	@Override
	public Integer getMax() {
		AtomikosDataSourceBean dataSourceBean = (AtomikosDataSourceBean) getDataSource();
		return dataSourceBean.getMaxPoolSize();
	}

	@Override
	public Integer getMin() {
		AtomikosDataSourceBean dataSourceBean = (AtomikosDataSourceBean) getDataSource();
		return dataSourceBean.getMinPoolSize();
	}

	@Override
	public String getValidationQuery() {
		return null;
	}

	@Override
	public Boolean getDefaultAutoCommit() {
		return null;
	}
	
	
	public int getPoolSize() {
		AtomikosDataSourceBean dataSourceBean = (AtomikosDataSourceBean) getDataSource();
		return dataSourceBean.poolAvailableSize();
	}

}
