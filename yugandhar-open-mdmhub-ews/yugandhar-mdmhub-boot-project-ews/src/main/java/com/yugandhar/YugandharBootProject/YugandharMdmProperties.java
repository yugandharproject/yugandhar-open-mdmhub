package com.yugandhar.YugandharBootProject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.yugandhar.common.util.YugandharEncoderDecoder;

/**
 * Properties class for micro services platform applicaiton. 
 * The properties from 'yugandhar-msp.properties' file are mapped to respective java properties.
 * @author Yugandhar
 * @version 2.0
 * @since 2.0
 * 
 */

@Configuration
@EnableTransactionManagement
@PropertySource("yugandhar-mdmhub-app.properties")
public class YugandharMdmProperties {

	@Value("${yugandhar.mdm.datasource.url}")
	private String datasourceurl;

	@Value("${yugandhar.mdm.datasource.username.plaintext:#{null}}")
	private String datasourceUsernamePlaintext;

	@Value("${yugandhar.mdm.datasource.password.plaintext:#{null}}")
	private String datasourcePasswordPlaintext;

	@Value("${yugandhar.mdm.datasource.username.encrypted:#{null}}")
	private String datasourceUsernameEncripted;

	@Value("${yugandhar.mdm.datasource.password.encrypted:#{null}}")
	private String datasourcePasswordEncripted;

	@Value("${yugandhar.mdm.datasource.driver-class-name}")
	private String datasourceDriveClassName;

	@Value("${yugandhar.mdm.datasource.xa.data-source-class-name}")
	private String datasourceXaDataSourceClassName;

	/**
	 * @return the datasourceurl
	 */
	public String getDatasourceurl() {
		return datasourceurl;
	}

	/**
	 * @param datasourceurl
	 *            the datasourceurl to set
	 */
	public void setDatasourceurl(String datasourceurl) {
		this.datasourceurl = datasourceurl;
	}

	/**
	 * @return the datasourceUsernamePlaintext
	 */
	public String getDatasourceUsernamePlaintext() {
		return datasourceUsernamePlaintext;
	}

	/**
	 * @param datasourceUsernamePlaintext
	 *            the datasourceUsernamePlaintext to set
	 */
	public void setDatasourceUsernamePlaintext(String datasourceUsernamePlaintext) {
		this.datasourceUsernamePlaintext = datasourceUsernamePlaintext;
	}

	/**
	 * @return the datasourcePasswordEncripted
	 */
	public String getDatasourcePasswordEncripted() {
		return datasourcePasswordEncripted;
	}

	/**
	 * @param datasourcePasswordEncripted
	 *            the datasourcePasswordEncripted to set
	 */
	public void setDatasourcePasswordEncripted(String datasourcePasswordEncripted) {
		this.datasourcePasswordEncripted = datasourcePasswordEncripted;
	}

	/**
	 * @return the datasourceUsernameEncripted
	 */
	public String getDatasourceUsernameEncripted() {
		return datasourceUsernameEncripted;
	}

	/**
	 * @param datasourceUsernameEncripted
	 *            the datasourceUsernameEncripted to set
	 */
	public void setDatasourceUsernameEncripted(String datasourceUsernameEncripted) {
		this.datasourceUsernameEncripted = datasourceUsernameEncripted;
	}

	/**
	 * @return the datasourcePasswordPlaintext
	 */
	public String getDatasourcePasswordPlaintext() {
		return datasourcePasswordPlaintext;
	}

	/**
	 * @param datasourcePasswordPlaintext
	 *            the datasourcePasswordPlaintext to set
	 */
	public void setDatasourcePasswordPlaintext(String datasourcePasswordPlaintext) {
		this.datasourcePasswordPlaintext = datasourcePasswordPlaintext;
	}

	/**
	 * @return the datasourceDriveClassName
	 */
	public String getDatasourceDriveClassName() {
		return datasourceDriveClassName;
	}

	/**
	 * @param datasourceDriveClassName
	 *            the datasourceDriveClassName to set
	 */
	public void setDatasourceDriveClassName(String datasourceDriveClassName) {
		this.datasourceDriveClassName = datasourceDriveClassName;
	}

	/**
	 * @return the datasourceXaDataSourceClassName
	 */
	public String getDatasourceXaDataSourceClassName() {
		return datasourceXaDataSourceClassName;
	}

	/**
	 * @param datasourceXaDataSourceClassName
	 *            the datasourceXaDataSourceClassName to set
	 */
	public void setDatasourceXaDataSourceClassName(String datasourceXaDataSourceClassName) {
		this.datasourceXaDataSourceClassName = datasourceXaDataSourceClassName;
	}

	/**
	 * @return String Returns the Plain Text username if present else returns
	 *         the decoded values of encrypted username else returns null
	 */
	public String getDatasourceUsername() {
		if (!isNullOrEmpty(getDatasourceUsernamePlaintext())) {
			return getDatasourceUsernamePlaintext();
		} else if (!isNullOrEmpty(getDatasourceUsernameEncripted())) {
			YugandharEncoderDecoder yugandharEncoderDecoder = new YugandharEncoderDecoder();
			return yugandharEncoderDecoder.decodeString(getDatasourceUsernameEncripted());
		}
		return null;
	}

	/**
	 * @return String Returns the Plain Text password if present else returns
	 *         the decoded values of encrypted password else returns null
	 */
	public String getDatasourcePassword() {
		if (!isNullOrEmpty(getDatasourcePasswordPlaintext())) {
			return getDatasourcePasswordPlaintext();
		} else if (!isNullOrEmpty(getDatasourcePasswordEncripted())) {
			YugandharEncoderDecoder yugandharEncoderDecoder = new YugandharEncoderDecoder();
			return yugandharEncoderDecoder.decodeString(getDatasourcePasswordEncripted());
		}
		return null;
	}

	public boolean isNullOrEmpty(String strToCheck) {

		if (null == strToCheck || strToCheck.trim().isEmpty()) {
			return true;
		} else {
			return false;
		}

	}
}
