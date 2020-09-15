import java.lang.Math; 
public class Double_hash<K,T> implements MyHashTable_<K,T> {
	class Node_DH<K,T>{
		Pair<K,T> data;
		boolean status;
		Node_DH(Pair<K,T> p)
		{
			this.data=p;
		}
	}
	int size;
	Node_DH[] hashmap;
	
	Double_hash(int size)
	{
		this.size=size;
		hashmap=new Node_DH[size];
	}
	
	
	public static long djb2(String str, int hashtableSize) { 
	    long hash = 5381; 
	    for (int i = 0; i < str.length(); i++) { 
	        hash = ((hash << 5) + hash) + str.charAt(i); 
	    } 
	    return Math.abs(hash) % hashtableSize; 
	}
	
	public static long sdbm(String str, int hashtableSize) { 
	    long hash = 0; 
	    for (int i = 0; i < str.length(); i++) { 
	        hash = str.charAt(i) + (hash << 6) + (hash << 16) - hash; 
	    } 
	    return Math.abs(hash) % (hashtableSize - 1) + 1; 
	}
	   public int insert(K key, T obj)
	   {
		   int i=0;
		   long h1=this.djb2(key.toString(), this.size);
		   long h2=this.sdbm(key.toString(), this.size);
		   long index=(h1+(h2*i)%this.size)%this.size;
		   boolean inserted=false;
		   while(!inserted && i<this.size)
		   {
			   if(hashmap[(int) index] == null)
			   {
				   hashmap[(int)index]=new Node_DH(new Pair(key,obj));
				   hashmap[(int)index].status=true;
				   inserted=true;
			   }
		   else if(hashmap[(int) index].status==false)
			   {
				   hashmap[(int)index]=new Node_DH(new Pair(key,obj));
				   hashmap[(int)index].status=true;
				   inserted=true;
			   }
			   else
			   {
				   i++;
				   index=(h1+(h2*i)%this.size)%this.size;
			   }
		   }
		   return i+1;
	   }
	   
	   public int update(K key, T obj)
	   {
		   int i=0;
		   long h1=this.djb2(key.toString(), this.size);
		   long h2=this.sdbm(key.toString(), this.size);
		   long index=(h1+(h2*i)%this.size)%this.size;
		   boolean updated=false;
		   boolean pfound=false;
		   while(!updated && i<this.size && !pfound)
		   {
			   if(hashmap[(int) index]==null)
			   pfound=true;			   
			   else if(hashmap[(int) index].data.key.toString().equals(key.toString()) &&  hashmap[(int) index].status==true)
			   {
				   hashmap[(int)index].data.value=obj;
				   updated=true;
			   }
			   else
			   {
				   i++;
				   index=(h1+(h2*i)%this.size)%this.size;
			   }
				   
		   }
		   if(updated==true)
			   return i+1;
		   else
			   return -1;
	   }
	   public int delete(K key)
	   {
		   int i=0;
		   long h1=this.djb2(key.toString(), this.size);
		   long h2=this.sdbm(key.toString(), this.size);
		   long index=(h1+(h2*i)%this.size)%this.size;
		   boolean deleted=false;
		   boolean pfound=false;
		   while(!deleted && i<this.size && !pfound)
		   {
			   if(hashmap[(int) index]==null)
				   pfound=true;
		   else if(hashmap[(int) index].data.key.toString().equals(key.toString()) && hashmap[(int) index].status==true)
			   {
				   hashmap[(int)index].status=false;
				   deleted=true;
			   }
			   else
			   {
				   i++;
				   index=(h1+(h2*i)%this.size)%this.size;
			   }
		   }
		   if(deleted==true)
			   return i+1;
		   else
			   return -1;
	   }
	   public boolean contains(K key)
	   {
		   int i=0;
		   long h1=this.djb2(key.toString(), this.size);
		   long h2=this.sdbm(key.toString(), this.size);
		   long index=(h1+(h2*i)%this.size)%this.size;
		   boolean found=false;
		   boolean pfound=false;
		   while(!found && !pfound && i<this.size)
		   {
			   if(hashmap[(int)index]==null)
				   pfound = true;
		   else if(hashmap[(int) index].data.key.toString().equals(key.toString()) &&  hashmap[(int) index].status==true)
			   {
				   found=true;
			   }
			   else
			   {
				   i++;
				   index=(h1+(h2*i)%this.size)%this.size;
			   }
				  
		   }
		   return found;
	   }
	 
	   // Return the object with given key 
	   public T get(K key) throws NotFoundException
	   {
		   int i=0;
		   T obj_rt=null;
		   long h1=this.djb2(key.toString(), this.size);
		   long h2=this.sdbm(key.toString(), this.size);
		   long index=(h1+(h2*i)%this.size)%this.size;
		   boolean found=false;
		   boolean pfound=false;
		   while(!found && !pfound && i<this.size)
		   {
			   if(hashmap[(int)index]==null)
				   pfound = true;
			   else if(hashmap[(int) index].data.key.toString().equals(key.toString()) &&  hashmap[(int) index].status==true)
			   {
				   obj_rt=(T) hashmap[(int)index].data.value;
				   found=true;
			   }
			   else
			   {
				   i++;
				   index=(h1+(h2*i)%this.size)%this.size;
			   }
				  
		   }
		   if(found==true)
			   return obj_rt;
		   else
			   throw new NotFoundException();
	   }
	   public String address(K key) throws NotFoundException
	   {
		   int i=0;
		   String addr="";
		   long h1=this.djb2(key.toString(), this.size);
		   long h2=this.sdbm(key.toString(), this.size);
		   long index=(h1+(h2*i)%this.size)%this.size;
		   boolean found=false;
		   boolean pfound=false;
		   while(!found && !pfound && i<this.size)
		   {
			   if(hashmap[(int)index]==null)
				   pfound = true;
			   else if(hashmap[(int) index].data.key.toString().equals(key.toString()) &&  hashmap[(int) index].status==true)
			   {
				   addr=Long.toString(index);
				   found=true;
			   }
			   else
			   {
				   i++;
				   index=(h1+(h2*i)%this.size)%this.size;
			   }
				  
		   }
		   if(found==true)
			   return addr;
		   else
			   throw new NotFoundException();
	   }

}
