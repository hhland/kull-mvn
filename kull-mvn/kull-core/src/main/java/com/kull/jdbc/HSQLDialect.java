package com.kull.jdbc;

import java.util.List;

import org.apache.ibatis.mapping.ResultMapping;




/**
 * Dialect for HSQLDB
 * @author badqiu
 */
public class HSQLDialect extends Dialect{

	public boolean supportsLimit() {
		return true;
	}

	public boolean supportsLimitOffset() {
		return true;
	}

	public String getLimitString(String sql, int offset,String offsetPlaceholder, int limit, String limitPlaceholder) {
		boolean hasOffset = offset>0;
		return new StringBuffer( sql.length() + 10 )
		.append( sql )
		.insert( sql.toLowerCase().indexOf( "select" ) + 6, hasOffset ? " limit "+offsetPlaceholder+" "+limitPlaceholder : " top "+limitPlaceholder )
		.toString();
	}

	



	@Override
	public String methodStrToDate(String regexp) {
		// TODO Auto-generated method stub
		return null;
	}

	
    
}
