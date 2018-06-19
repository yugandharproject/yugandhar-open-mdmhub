package com.yugandhar.YugandharBootProject;

import javax.sql.DataSource;
import javax.sql.XADataSource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.boot.context.properties.source.ConfigurationPropertyNameAliases;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.MapConfigurationPropertySource;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.boot.jdbc.metadata.DataSourcePoolMetadataProvider;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;


/**
 * Default Data source bean for Yugandhar MSP data source. It creates the data source based on properties
 * mentioned in 'yugandhar-msp.properties' file.
 * @author Yugandhar
 * @version 2.0
 * @since 2.0
 * 
 */


@Configuration
@EnableTransactionManagement
public class DataSourceBean {

	@Autowired
	YugandharMdmProperties yugandharMspProperties;

	private ClassLoader classLoader;

	@Autowired
	private DataSourceProperties properties;

	@Primary
	@Bean(name = "dataSource")
	public DataSource dataSource() {

		XADataSource dataSource = createXaDataSource();
		AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
		atomikosDataSourceBean.setUniqueResourceName("YugDatabaseConnection");
		atomikosDataSourceBean.setXaDataSource(dataSource);
		return atomikosDataSourceBean;

	}

	@Configuration
	@ConditionalOnClass(AtomikosDataSourceBean.class)
	class AtomikosDataSourcePoolMetadataProviderConfiguration {

		@Bean
		public DataSourcePoolMetadataProvider AtomikosPoolDataSourceMetadataProvider() {
			return (dataSource) -> {
				if (dataSource instanceof AtomikosDataSourceBean) {
					return new AtomikosDataSourcePoolMetadata((AtomikosDataSourceBean) dataSource);
				}
				return null;
			};
		}

	}

	private XADataSource createXaDataSource() {
		String className = yugandharMspProperties.getDatasourceXaDataSourceClassName();
		if (!StringUtils.hasLength(className)) {
			className = DatabaseDriver.fromJdbcUrl(this.properties.determineUrl()).getXaDataSourceClassName();
		}
		Assert.state(StringUtils.hasLength(className), "No XA DataSource class name specified");
		XADataSource dataSource = createXaDataSourceInstance(className);
		bindXaProperties(dataSource, this.properties);
		return dataSource;
	}

	private XADataSource createXaDataSourceInstance(String className) {
		try {
			Class<?> dataSourceClass = ClassUtils.forName(className, this.classLoader);
			Object instance = BeanUtils.instantiateClass(dataSourceClass);
			Assert.isInstanceOf(XADataSource.class, instance);
			return (XADataSource) instance;
		} catch (Exception ex) {
			throw new IllegalStateException("Unable to create XADataSource instance from '" + className + "'");
		}
	}

	private void bindXaProperties(XADataSource target, DataSourceProperties dataSourceProperties) {
		Binder binder = new Binder(getBinderSource(dataSourceProperties));
		binder.bind(ConfigurationPropertyName.EMPTY, Bindable.ofInstance(target));
	}

	private ConfigurationPropertySource getBinderSource(DataSourceProperties dataSourceProperties) {
		MapConfigurationPropertySource source = new MapConfigurationPropertySource();
		source.put("user", yugandharMspProperties.getDatasourceUsername());
		source.put("password", yugandharMspProperties.getDatasourcePassword());
		source.put("url", yugandharMspProperties.getDatasourceurl());
		source.putAll(dataSourceProperties.getXa().getProperties());
		ConfigurationPropertyNameAliases aliases = new ConfigurationPropertyNameAliases();
		aliases.addAliases("user", "username");
		return source.withAliases(aliases);

	}

}
