package com.kull.mybatis;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.apache.ibatis.type.JdbcType;

import com.kull.ObjectHelper;
import com.kull.annotation.SimpleOrmTable;
import com.kull.bean.JdbcBean.Database;
import com.kull.jdbc.Dialect;
import com.kull.bean.JdbcBean;













public class MapperFactory {

	private Database database=Database.unknow;
	private Dialect dialect=null;
	
	public MapperFactory(Connection connection) throws Exception{
		this.connection=connection;
	    database= JdbcBean.refDatabase(connection);
	    this.dialect=database.getDialect();
	
	}
	
	public MapperFactory(Connection connection,Dialect dialect){
		this.connection=connection;
		this.dialect=dialect;
		try {
			database=JdbcBean.refDatabase(connection);
			this.dialect=Dialect.createDialect(database);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Connection connection;
    

	public  Set<MapperTemplate> createMapperTemplate(String tablePrefix,Class<? extends IMapper>... nameSpaces) throws Exception{
		
		Set<MapperTemplate> mapperTemplates=new HashSet<MapperTemplate>();
		for(Class<? extends IMapper> nameSpace :nameSpaces){
		Class modelClass=ObjectHelper.getClazz(nameSpace,IMapper.INDEX_MODEL_CLASS);
		SimpleOrmTable tableConfig=nameSpace.getAnnotation(SimpleOrmTable.class);
		if(tableConfig==null){
			throw new NullPointerException(MessageFormat.format("class {0} don't have annotation:{1}",nameSpace.getName(),SimpleOrmTable.class.getName()));
		}
		tablePrefix=tablePrefix==null?"":tablePrefix;
		if(database==Database.oracle&& !"".equals(tableConfig.oracleSeqIdRegexp())){
			mapperTemplates.add(createMapperTemplate(nameSpace,tablePrefix+tableConfig.name(), tableConfig.pk(),ObjectHelper.toSet(tableConfig.excludeColumns()),tableConfig.oracleSeqIdRegexp()));
		  }else{
				mapperTemplates.add(createMapperTemplate(nameSpace,tablePrefix+tableConfig.name(), tableConfig.pk(),ObjectHelper.toSet(tableConfig.excludeColumns())));
		  }
		}
		return mapperTemplates ;
	}
    
	public  MapperTemplate createMapperTemplate(Class<? extends IMapper> nameSpace,String table,String pk,Set<String> excludeColumns) throws Exception{
		return createMapperTemplate(nameSpace,table,pk,excludeColumns,null);
	}
	
	public  MapperTemplate createMapperTemplate(Class<? extends IMapper> nameSpace,String table,String pk,Set<String> excludeColumns,String idGener) throws Exception{
		Class modelClass=(Class) ObjectHelper.getClazz(nameSpace,IMapper.INDEX_MODEL_CLASS);
		if(modelClass==null || nameSpace==null)throw new Exception("MapperFactory creating error: nameSpace and modelclass can't be null");
		MapperTemplate mapper=null;
		try {
			
			String pattern="select * from {0} where 1=2";
			String querySql=MessageFormat.format(pattern, table);
		    PreparedStatement ps=  this.connection.prepareStatement(querySql);
		    ResultSet rs=  ps.executeQuery();
		    ResultSetMetaData mtdata= rs.getMetaData();
		    
		    ArrayList<String> dbColNames=new ArrayList<String>();
		    ArrayList<String> dbColTypes=new ArrayList<String>();
		    ArrayList<Integer> precisions=new ArrayList<Integer>();
		    for (int i = 1; i <= mtdata.getColumnCount(); i++) {
		    	String colName=mtdata.getColumnName(i);
		    	String colType=mtdata.getColumnTypeName(i);
		        String colCName=mtdata.getCatalogName(i);
		    	if(ObjectHelper.isNotEmpty(excludeColumns)){
		    		boolean isExclude=false;
		    		for(String column : excludeColumns){
		    			if(colName.equalsIgnoreCase(column)){
		    				isExclude=true;
		    				break;
		    			}
		    		}
		    		if(isExclude)continue;
		    		
		    	}
				dbColNames.add(colName);
				dbColTypes.add(colType);
				try{
				precisions.add(mtdata.getPrecision(i));
				}catch (NumberFormatException nfe){
					precisions.add(100);
				}
			}
		    mapper=new MapperTemplate(dialect, table, pk, dbColNames, dbColTypes, precisions,idGener);
		    mapper.setModelName(modelClass);
		    mapper.setNamespace(nameSpace);
		    return mapper;
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{return mapper;}
	}
	
	
	
	
	public static void main(String[] args)
	{
//        MapperTemplate mt=createMapperTemplate("pp_personc","staff_no");
//        System.err.println(mt.getDbCols());
//        System.err.println(mt.getInsertCols());
//        System.err.println(mt.getUpdateCols());
//        System.err.println(mt.getJavaCols());
//        System.err.println(mt.getModelCols());
//        System.err.println(mt.getResultMap());
//
//        System.err.println(mt.getCondition());
//        System.err.println(mt.getPropertiesKey());
//        //System.out.println(mt.getMapper(IMapper.class));
	}
}