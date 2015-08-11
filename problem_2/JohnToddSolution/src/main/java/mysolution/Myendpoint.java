package mysolution;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.apache.log4j.Logger;
import org.json.*;
import java.util.*;

/* ENDPOINT taking in JSON 
 * returning JSON that matches the predefined structure
   
   jsonData is the java Bean class to represent the JSON data as a java bean
   predefined structure is described in jsonData bean class comments
  */

public class Myendpoint extends HttpServlet {
	private static final Logger log = Logger.getLogger(Myendpoint.class);

	public Myendpoint() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// GET NOT SUPPORTED
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
        // read the JSON data from the request
		String data = getPostData(request);

		// if we didn't get it just kick out
		if (data == null)
			return;
		
		JSONObject obj = null;
		// current new bucket
		jsonData dataObj = new jsonData();
		
		try {
			// parse the JSON
			obj = new JSONObject(data);
			
			// look for the top level components
			String name = obj.getString("name");		
			int id = obj.getInt("id");
			boolean active = obj.getBoolean("active");
			int count = obj.getInt("count");
			
			// populate the bucket
			dataObj.setName(name);
			dataObj.setId(id);
			dataObj.setActive(active);
			dataObj.setCount(count);
			
			// look for array of account
			JSONArray arr = obj.getJSONArray("accounts");
			if (arr != null) {
				for (int i = 0; i < arr.length(); i++)
				{
					int aid = arr.getJSONObject(i).getInt("id");
					String aname = arr.getJSONObject(i).getString("name");
					// add accounts to the bucket
					dataObj.addAccount(aid, aname);
				}
			}
			
			// look for array of addresses
			JSONArray arr2 = obj.getJSONArray("address_ids");
			if (arr2 != null) {
				for (int i = 0; i < arr2.length(); i++)
				{
					// add addresses to bucket
					int addr = arr2.getInt(i);
					dataObj.addAddressId(addr);
				}
			}
		} catch (Exception ex) {
			// in the event something goes wrong log the error and kick out
			log.error("BAD JSON NO BISCUIT!",ex);
			return;
		}
		
		// translate bucket to JSON string
		String jstr = dataObj.toString();
		
		// send back to client
		Writer w = response.getWriter();
		w.write(jstr);
		// log activity
		log.info("JSON RECEIVED = " + data);
		log.info("JSON SENT = " + jstr);		
	}
	
	// read in the JSON data sent in on the request
	public static String getPostData(HttpServletRequest req) {
	    StringBuilder sb = new StringBuilder();
	    try {
	        BufferedReader reader = req.getReader();
	        reader.mark(10000);

	        String line;
	        do {
	            line = reader.readLine();
	            sb.append(line).append("\n");
	        } while (line != null);
	        reader.reset();
	        // do NOT close the reader here, or you won't be able to get the post data twice
	    } catch(IOException e) {
	        System.out.println("getPostData couldn't.. get the post data");  // This has happened if the request's reader is closed    
	        return null;
	    }

	    return sb.toString();
	}
}
