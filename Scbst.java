import java.lang.Math;
public class Scbst<K extends Comparable<K>, T> implements MyHashTable_<K,T>{	
	class Sentinel<K,T>
	{	Node<K,T> next;
		Sentinel()
		{
			this.next=null;
		}
	}
	class Node<K,T>{
		Pair<K,T> data;
		Node left;
		Node right;
		Node(Pair<K,T> data)
		{
			this.data=data;
			this.left=null;
			this.right=null;
		}
	}
	
	Sentinel[] chain;
	int size;
	
	 Scbst(int size)
	 {
		 this.size=size;
		 chain=new Sentinel[size];
	 }
	 
	public static long djb2(String str, int hashtableSize) { 
		 long hash = 5381; 
		 for (int i = 0; i < str.length(); i++) {	 
			 hash = ((hash << 5) + hash) + str.charAt(i); 
		 } 
		 
		 return Math.abs(hash) % hashtableSize; 
	 }
 
	public int insert(K key, T obj)
	{
		int i=1;
		long index = this.djb2(key.toString(), this.size);
		if(chain[(int)index]==null)
		{
			chain[(int)index]=new Sentinel();
			chain[(int)index].next=new Node(new Pair(key,obj));
		}
		else
		{
			Node root=chain[(int)index].next;
			Node curr=root;
			boolean inserted=false;
			while(!inserted)
			{
				if(key.compareTo((K) curr.data.key)<=0)
				{
					if(curr.left==null)
						{
							curr.left=new Node(new Pair(key,obj));
							inserted=true;
						}
					else
						curr=curr.left;
				}
				else
				{
					if(curr.right==null)
						{	
							curr.right=new Node(new Pair(key,obj));
							inserted=true;
						}
					else
						curr=curr.right;
				}
				i++;
			}
		}
		
		return i;
	}
	
	
public int update(K key, T obj) {
		   int i=0;
		   boolean found=false;
		   boolean terminate=false;
		   long index=this.djb2(key.toString(), this.size);
		   if(chain[(int)index]==null)
			   terminate=true;
		   else
		   {
			  Node curr=chain[(int)index].next;
			   while(!found && !terminate)
			   {
				   if(key.toString().compareTo(curr.data.key.toString())==0)
					   {found=true;
					   curr.data.value=obj;
					   }
				   else if(key.compareTo((K) curr.data.key)<=0)
				   {
					   if(curr.left==null)
						   terminate=true;
					   else
						   {	
						   	curr=curr.left;
						   }
				   }
				   else
				   {
					   if(curr.right==null)
						   terminate=true;
					   else
						   	{	
						   		curr=curr.right;
						   	}
				   }
				   i++;
			   }
			   
		   }
		   if(found==true)
			   return i;
		   else
			   return -1;
	   }
	   
	   
 public int delete(K key) {
		   int i=1;
		   boolean found=false;
		   boolean terminate=false;
		  
		   long index=this.djb2(key.toString(), this.size);
		   if(chain[(int)index]==null)
			   terminate=true;
		   else
		   {
			   Node root=chain[(int)index].next;
			   if(key.toString().compareTo(root.data.key.toString())==0)
			   	{
				   found =true;
				   if(root.left==null&&root.right==null)
				   {
					   chain[(int)index].next=null;
					   chain[(int)index]=null;
				   }
				   else if(root.left==null)
				   {	i++;
					   chain[(int)index].next=root.right;
				   }	
				   else if(root.right==null)
				   {	i++;
					   chain[(int)index].next=root.left;
				   }
				   else
				   {
					   Node curr1=root.left;
					   Node prev1;
					   i++;
					   if(curr1.right==null)
					   	{
						   curr1.right=root.right;
						   chain[(int)index].next=curr1;
						   i++;
					   	}
					   else
					   {
						  prev1=root.left;
						  curr1=prev1.right;
						  i+=1;
						  while(curr1.right!=null)
						  {
							  prev1=curr1;
							  curr1=curr1.right;
							  i++;
						  }
						  prev1.right=curr1.left;
						  curr1.left=root.left;
						  curr1.right=root.right;
						  chain[(int)index].next=curr1;
					   }
				   }
			   }
			   else
			   {
			   Node curr=root,prev;
			   prev=root;
			   if(root.left==null&&root.right==null)
				   terminate=true;
			   else if(key.compareTo((K) root.data.key)<=0)
			   {	if(prev.left==null)
				   terminate=true;
			   else
			   {
				   i++;
				   curr=prev.left;}
			   }
			   else
				   {
				   if(prev.right==null)
					   terminate=true;
				   else {
					   
				   i++;
				   	curr=prev.right;}
				   }
			   while(!found&&!terminate)
			   {
				   if(key.toString().compareTo(curr.data.key.toString())==0)
					   found=true;
				   else if(key.compareTo((K) curr.data.key)<=0)
				   {
					   if(curr.left==null)
						   terminate=true;
					   else
						   {	prev=curr;
						   	curr=curr.left;
						   }
					   i++;
				   }
				   else
				   {
					   if(curr.right==null)
						   terminate=true;
					   else
						   	{	prev=curr;
						   		curr=curr.right;
						   	}
					   i++;
				   } 
				   
			   }
			   if(found)
			   {
				   
				   char c;
				   if(prev.left!=null && prev.left.toString().compareTo(curr.toString())==0) 
					   c='l';
				   else
					   c='r';
				   
				   if(curr.right==null && curr.left==null)
				   {
					   if(c=='r')
						   prev.right=null;
					   else
						   prev.left=null;
					
				   }
				   else if(curr.right==null)
				   {
					   if(c=='r')
						   prev.right=curr.left;
					   else
						   prev.left=curr.left;
					   i++;
				   }
				   else if(curr.left==null)
				   {
					   if(c=='r')
						   prev.right=curr.right;
					   else
						   prev.left=curr.right;
					   i++;
				   }
				   else
				   {
					   Node curr1=curr.left;
					   Node prev1;
					   if(curr1.right==null)
					   	{
						   curr1.right=curr.right;
						   if(c=='r')
							   prev.right=curr1;
						   else
							   prev.left=curr1;
						   i++;
					   	}
					   else
					   {
						  prev1=curr.left;
						  curr1=prev1.right;
						  i+=2;
						  while(curr1.right!=null)
						  {
							  prev1=curr1;
							  curr1=curr1.right;
							  i++;
						  }
						  prev1.right=curr1.left;
						  curr1.left=curr.left;
						  curr1.right=curr.right;
						 // chain[(int)index].next=curr1;
						  if(c=='r')
							   prev.right=curr1;
						   else
							   prev.left=curr1;
					   }
					   
				   }
					   
			   }

			   }		   
		   }
		  if(found==true)
			  return i;
		  else
			  return -1;
		   
		   
	   }

	   public boolean contains(K key) {
	   boolean found=false;
	   boolean terminate=false;
	   long index=this.djb2(key.toString(), this.size);
	   if(chain[(int)index]==null)
		   terminate=true;
	   else
	   {
		  Node curr=chain[(int)index].next;
		   while(!found && !terminate)
		   {
			   if(key.toString().compareTo(curr.data.key.toString())==0)
				   found=true;
			   else if(key.compareTo((K) curr.data.key)<=0)
			   {
				   if(curr.left==null)
					   terminate=true;
				   else
					   {	
					   	curr=curr.left;
					   }
			   }
			   else
			   {
				   if(curr.right==null)
					   terminate=true;
				   else
					   	{	
					   		curr=curr.right;
					   	}
			   }	   
		   }
	   }
		   return found;
}
	 
	   // Return the object with given key 
	   public T get(K key) throws NotFoundException
	   {
		   boolean found=false;
		   boolean terminate=false;
		   long index=this.djb2(key.toString(), this.size);
		   T obj_rt=null;
		   if(chain[(int)index]==null)
			   terminate=true;
		   else
		   {
			  Node curr=chain[(int)index].next;
			   while(!found && !terminate)
			   {
				   if(key.toString().compareTo(curr.data.key.toString())==0)
					   {found=true;
					   obj_rt=(T) curr.data.value;
					   }
				   else if(key.compareTo((K) curr.data.key)<=0)
				   {
					   if(curr.left==null)
						   terminate=true;
					   else
						   {	
						   	curr=curr.left;
						   }
				   }
				   else
				   {
					   if(curr.right==null)
						   terminate=true;
					   else
						   	{	
						   		curr=curr.right;
						   	}
				   }	   
			   }
		   }
		   if(found==true)
			   return obj_rt;
		   else
			   throw new NotFoundException();
		   
  }
	 
	   // ”Address” of object with given key (explained below) 
	   public String address(K key) throws NotFoundException
	   {
		   boolean found=false;
		   boolean terminate=false;
		   long index=this.djb2(key.toString(), this.size);
		   String addr=Long.toString(index)+"-";
		   if(chain[(int)index]==null)
			   terminate=true;
		   else
		   {
			  Node curr=chain[(int)index].next;
			   while(!found && !terminate)
			   {
				   if(key.toString().compareTo(curr.data.key.toString())==0)
					   found=true;
				   else if(key.compareTo((K) curr.data.key)<=0)
				   {
					   if(curr.left==null)
						   terminate=true;
					   else
						   {	addr+="L";
						   	curr=curr.left;
						   }
				   }
				   else
				   {
					   if(curr.right==null)
						   terminate=true;
					   else
						   	{	addr+="R";
						   		curr=curr.right;
						   	}
				   }	   
			   }
		   }
		   if(found==true)
			   return addr;
		   else
			   throw new NotFoundException();
		   
		   
	   }





}
