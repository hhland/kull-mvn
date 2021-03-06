/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kull.common.action;


import com.kull.common.Utils;
import com.kull.orm.dialect.Dialect;
import com.kull.orm.dialect.SqliteDialect;

import com.kull.web.struts.LSActionSupport;
import com.kull.web.struts.SqlLSActionSupport;
import com.opensymphony.xwork2.Preparable;
import java.io.IOException;
import java.text.MessageFormat;
import javax.servlet.ServletException;


/**
 *
 * @author lin
 */
public abstract class LSAction extends SqlLSActionSupport implements Preparable{
  
    public final static  Dialect dialect=new SqliteDialect();
    
    protected String pk,namespace,action;

    public void setPk(String pk) {
        this.pk = pk;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public void setAction(String action) {
        this.action = action;
    }

   

    
   
   
    
    
    
    
    @Override
    protected String createPageSql(String dataSql, int start, int limit) {
         return dialect.getLimitString(dataSql,start, limit);
    }

    @Override
    public void prepare() throws Exception {
        this.connection=Utils.dbmeta();
    }
    
    public void forward() throws ServletException, IOException{
        String path=MessageFormat.format("/view/{0}/{1}.{2}.jsp",namespace,action,pk);
        this.request.getRequestDispatcher(path).forward(request, response);
        
    }
    
   
    
}
