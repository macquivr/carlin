package mysolution;

/*
 * This bean holds the id/name pairs

   the 
   {
            "id": 1,
            "name": "Test Account 1"
        }
   part of the json
 */

public class Account {
	private int id = 0;
	private String name = null;
	
	public String getName() { return name; }
	public int getId() { return id; }
	
	public String setName(String n) { return name = n; }
	public int setId(int i) { return id = i; }
	
	/* toString method returns the id/name as a JSON string */
	
	public String toString() { 
		String ret = "{\n";
		ret = ret.concat("\"id\": " + id + ",\n");
		if (name != null)
			ret = ret.concat("\"name\": \"" + name + "\"\n");
		ret = ret.concat("}");
		
		return ret;
	}
	
}
