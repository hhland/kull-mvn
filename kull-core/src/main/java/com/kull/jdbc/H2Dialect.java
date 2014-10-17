package com.kull.jdbc;

import java.util.List;

import org.apache.ibatis.mapping.ResultMapping;





/**
 * A dialect compatible with the H2 database.
 * 
 * @author Thomas Mueller
 *
 */
public class H2Dialect extends Dialect {

    public boolean supportsLimit() {
        return true;
    }

	public String getLimitString(String sql, int offset,String offsetPlaceholder, int limit, String limitPlaceholder) {
		return new StringBuffer(sql.length() + 40).
			append(sql).
			append((offset > 0) ? " limit "+limitPlaceholder+" offset "+offsetPlaceholder : " limit "+limitPlaceholder).
			toString();
	}

	@Override
	public boolean supportsLimitOffset() {
		return true;
	}

	
	

	@Override
	public String methodStrToDate(String regexp) {
		// TODO Auto-generated method stub
		return null;
	}

	

    

}