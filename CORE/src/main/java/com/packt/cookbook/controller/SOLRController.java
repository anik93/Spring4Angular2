package com.packt.cookbook.controller;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings({ "unused", "deprecation" })
@RestController
@RequestMapping(value = "/solr")
public class SOLRController {
	
	//@Resource
	//private SolrTemplate solrTemplate;
	
	@SuppressWarnings({ "resource" })
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> test(HttpServletRequest request){
		//String[] words = {"book1"};
		//Criteria conditions = null;
		try {
			//conditions = new Criteria("id").contains("book1");
			//SimpleQuery search = new SimpleQuery(conditions);
			//ScoredPage<Book> result = solrTemplate.queryForPage(search, Book.class);
			
			String url = "http://localhost:8983/solr/#/demo/update";
			HttpSolrServer server = new HttpSolrServer( url );
			server.setMaxRetries(1); // defaults to 0.  > 1 not recommended.
			  server.setConnectionTimeout(5000); // 5 seconds to establish TCP
			  // Setting the XML response parser is only required for cross
			  // version compatibility and only when one side is 1.4.1 or
			  // earlier and the other side is 3.1 or later.
			  server.setParser(new XMLResponseParser()); // binary parser is used by default
			  // The following settings are provided here for completeness.
			  // They will not normally be required, and should only be used 
			  // after consulting javadocs to know whether they are truly required.
			  server.setSoTimeout(1000);  // socket read timeout
			 
			  server.setDefaultMaxConnectionsPerHost(100);
			  server.setMaxTotalConnections(100);
			  server.setFollowRedirects(false);  // defaults to false
			  // allowCompression defaults to false.
			  // Server side must support gzip or deflate for this to have any effect.
			  server.setAllowCompression(true);
			  
			 SolrInputDocument doc1 = new SolrInputDocument();
			    doc1.addField( "id", "id1", 1.0f );
			    doc1.addField( "name", "doc1", 1.0f );
			    doc1.addField( "price", 10 );
			    
			    Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
			    docs.add( doc1 );
			    server.deleteById("book1");
			    //server.add( docs );
			    server.commit();
			    /* UpdateRequest req = new UpdateRequest();
			    req.setAction( UpdateRequest.ACTION.COMMIT, false, false );
			    req.add( docs );
			    UpdateResponse rsp = req.process( server );*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("test", HttpStatus.OK);
	}
}
