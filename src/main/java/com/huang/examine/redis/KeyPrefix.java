package com.huang.examine.redis;

/**
 * @author DELL
 */
public interface KeyPrefix {
		
	public int expireSeconds();
	
	public String getPrefix();
	
}
