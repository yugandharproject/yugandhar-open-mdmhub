package com.yugandhar.mdm.extern.dobj;
/* Generated Dec 12, 2017 6:24:10 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.yugandhar.mdm.abstractdobj.AbstractBatchEntityToProcessDO;

/**
 * DO class for mapped to database BATCH_ENTITY_TO_PROCESS entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see AbstractBatchEntityToProcessDO
*/

@Entity
@Table(name = "BATCH_ENTITY_TO_PROCESS")
public class BatchEntityToProcessDO extends AbstractBatchEntityToProcessDO {

	/**
	 *  Any additional attributes in the OOTB entity needs to be added in this class
	 */
	private static final long serialVersionUID = 1L;

	public BatchEntityToProcessDO() {
		super();
	}

	public BatchEntityToProcessDO(BatchEntityToProcessDO theBatchEntityToProcessDO) {
		super(theBatchEntityToProcessDO);
	}
}
