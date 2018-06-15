package com.yugandhar.cache.ehcache.eventlogger;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yugandhar.common.constant.yugandharConstants;

/**
 *
 * @author GGIB
 */
public class EhcacheEventLogger implements CacheEventListener<Object, Object>{

  private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_CACHE_LOGGER);

  /*@Override
  public void onEvent(CacheEvent<Object, Object> event) {
    logger.info("Event: " + event.getType() + " Key: " + event.getKey() + " old value: " + event.getOldValue() + " new value: " + event.getNewValue());
  }*/

	@Override
	public void onEvent(CacheEvent<? extends Object, ? extends Object> event) {
		logger.info("Event: " + event.getType() + " Key: " + event.getKey() + " old value: " + event.getOldValue() + " new value: " + event.getNewValue());
	}

}
