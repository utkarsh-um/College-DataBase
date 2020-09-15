import java.io.*;
public class assignment3  {
	public static void main(String args[]) throws IOException
	{
		MyHashTable_<Key<String>,Student> hashtable;
		if(args[1].equals("SCBST"))
			hashtable=new Scbst<Key<String>,Student>(Integer.parseInt(args[0]));
		else
			hashtable=new Double_hash<Key<String>,Student>(Integer.parseInt(args[0]));
		File text_file=new File(args[2]);
		BufferedReader br=new BufferedReader(new FileReader(text_file));
		String st;
		while((st=br.readLine())!=null)
		{
			String [] str=st.split(" ");
			if(str[0].equals("insert"))
			{
				
				Student stud=new Student(str[1],str[2],str[3],str[4],str[5]);
				Key<String> key=new Key<String>(str[1],str[2]);
				int k=hashtable.insert(key, stud);
				
					System.out.println(k);
			}
			else if(str[0].equals("update"))
			{	Student stud=new Student(str[1],str[2],str[3],str[4],str[5]);
				Key<String> key=new Key<String>(str[1],str[2]);
				int k=hashtable.update(key, stud);
				if(k==-1)
					System.out.println("E");
				else
					System.out.println(k);
			}
			else if(str[0].equals("delete"))
			{
				Key<String> key=new Key<String>(str[1],str[2]);
				int k=hashtable.delete(key);
				if(k==-1)
					System.out.println("E");
				else
					System.out.println(k);
			}
			else if(str[0].equals("get"))
			{
				try {
				Key<String> key=new Key<String>(str[1],str[2]);
				Student st1=hashtable.get(key);
				System.out.println(st1.fname()+" "+st1.lname()+" "+st1.hostel()+" "+st1.department()+" "+st1.cgpa());
				}
				catch (Exception e)
				{
					System.out.println("E");
				}
			}
			else if(str[0].equals("contains"))
			{
				Key<String> key=new Key<String>(str[1],str[2]);
				boolean check=hashtable.contains(key);
				if(check)
					System.out.println("T");
				else
					System.out.println("F");
			}
			else if(str[0].equals("address"))
			{
				try {
					Key<String> key=new Key<String>(str[1],str[2]);
					System.out.println(hashtable.address(key));
					}
					catch (Exception e)
					{
						System.out.println("E");
					}
			}
			
		}
		
	}

}
