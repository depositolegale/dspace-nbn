package org.dspace.identifier;


import org.apache.log4j.Logger;

import java.io.*;
import java.sql.SQLException;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.dspace.core.ConfigurationManager;
import org.dspace.content.Item;
import org.dspace.core.Context;
import org.dspace.core.LogManager;
import org.dspace.content.DCValue;
import org.dspace.app.webui.util.UIUtil;
import org.dspace.authorize.AuthorizeException;

/**
 * Utility class to get NBN:IT from
 * 
 *  http://www.depositolegale.it/istruzioni-tecniche-api-nbn/
 *
 *
 * @author Jordan PIŠČANC (piscanc@units.it)
 *
 */

public class NbnIdentifierProvider {

	
	 /** log4j logger */
    private static Logger log = Logger.getLogger(NbnIdentifierProvider.class);
   
    
    public NbnIdentifierProvider() {
    	
    }
   
    
/* Request new NBN: giving the handle of the ITEM  */
    
    public static String register(String Handle) throws HttpException, IOException
    {

    	
   /** Set credentials of NBN server*/
    
    
    String credenziali = ConfigurationManager.getProperty("identifier","nbn.user")+":"+ConfigurationManager.getProperty("identifier","nbn.passwd");
    String MyNBN="";
    
    

    /** Set URL  **/

    String HandlUrl = ConfigurationManager.getProperty("dspace.url") + "/handle/" + Handle;
    String OaiUrl=    ConfigurationManager.getProperty("oai","dspace.oai.url") + "/request?verb=GetRecord&metadataPrefix=oai_dc&identifier=oai:" + ConfigurationManager.getProperty("dspace.hostname") +":"+ Handle;

   
     /** set json string for post to NBN server **/
    String content;
  
        content ="{\"action\":\"nbn_create\",\"url\":\""+HandlUrl+"\",\"metadataURL\":\"" + OaiUrl+ "\"}";


      
         HttpClient myClient = new HttpClient();
    
         myClient.getState().setCredentials(
                 new AuthScope( ConfigurationManager.getProperty("identifier","nbn.host"),80),
                 new UsernamePasswordCredentials(ConfigurationManager.getProperty("identifier","nbn.user"),ConfigurationManager.getProperty("identifier","nbn.passwd"))
                 );
              
         StringRequestEntity  jsonContent = new StringRequestEntity(content,"application/json","UTF-8");  
         PostMethod post = new PostMethod(ConfigurationManager.getProperty("identifier","nbn.url"));
         post.setDoAuthentication(true);
         post.setParameter("Content-Type", "application/json");
         post.setRequestEntity(jsonContent);            
        try {
             
            
            int status = myClient.executeMethod(post);
            
            if (status == 201){
            	log.warn("NBN assigned: "+ post.getResponseBodyAsString() );
            DataInputStream input = new DataInputStream (post.getResponseBodyAsStream());
            int i =0;
            String str;

            while (null != ((str = input.readLine())))
               {
                 i=i+1;
                 if (i==3) {
                     if (str.indexOf("urn:") != -1)  {   MyNBN = str.substring(str.indexOf("urn:"),str.length()-1); }
                   } // if i = 3 row

               } // end WHILE

               input.close ();
              
         } // end status=201
       else
       {
    	   log.warn("NBN not assigned: "+ post.getResponseBodyAsString() );
       
       }
  
        } // end of Try..catch
        finally {
        	post.releaseConnection();
        }	
        
            
        return MyNBN;
  
        }

        

    
    
    

}
