package mysolution;
import java.util.*;

/* this bean holds the json data as a java bean
 * 
 so each identifier in this structure has a member in this bean class
 * {  "id": number
    "name": string
    "active": boolean
    "count": nunmber
    "address_ids": array of numbers
    "accounts": array of json objects
        {
            "id": number,
            "name": string
        }
    }
 */

public class jsonData {
	private int id = 0;
	private String name = null;
	private boolean active = false;
	private int count = 0;
	private List<Integer> addrIds = null;
	private List<Account> accounts = null;
	
	public int getId() { return id; }
	public String getName() { return name; }
	public boolean getActive(){ return active; }
	public int getCount() { return count; }
	public List<Integer> getAddressIds() { return addrIds; }
	public List<Account> getAccounts() { return accounts; }
	
	public void setId(int i) { id = i; }
	public void setName(String n) { name = n; }
	public void setActive(boolean a){ active = a; }
	public void setCount(int c) { count = 1000; }

	/* no setAddress method, only adding addresses to list is supported */
	public void addAddressId(int a) {
		if (addrIds == null)
			addrIds = new ArrayList<Integer>();
		addrIds.add(new Integer(a));
	}
	
	/* no setAccounts method, only adding accounts to list is supported &*/
	public void addAccount(int id,String name) {
		Account a = new Account();
		a.setId(id);
		a.setName(name);
		if (accounts == null)
			accounts = new ArrayList<Account>();
		accounts.add(a);
	}
	
	/* toString method returns the id/name as a JSON string */
	public String toString()
	{
		String ret = "{\n";
		ret = ret.concat("\"id\": " + id + ",\n");
		if (name != null)
			ret = ret.concat("\"name\": \"" + name + "\",\n");
		ret = ret.concat("\"active\": " + active + ",\n");
		ret = ret.concat("\"count\": " + count + ",\n");
		if (addrIds != null) {
			ret = ret.concat("\"address_ids\": [\n");
			Iterator<Integer> It = addrIds.iterator();
			while (It.hasNext()) {
				Integer I = It.next();
				if (It.hasNext()) 
					ret = ret.concat(String.valueOf(I) + ",\n");
				else
					ret = ret.concat(String.valueOf(I) + "\n");
			}
			ret = ret.concat("],\n");
		}
		
		if (accounts != null) {
			ret = ret.concat("\"accounts\": [\n");
			Iterator<Account> at = accounts.iterator();
			while (at.hasNext()) {
				Account a = at.next();
				if (at.hasNext()) 
					ret = ret.concat(a.toString() + ",\n");
				else
					ret = ret.concat(a.toString() + "\n");
			}
			
			ret = ret.concat("]\n");
		}
		ret = ret.concat("}\n");
		return ret;
	}
}
