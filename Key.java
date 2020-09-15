
public class Key<K> implements Comparable<Key<K>>{
	K first;
	K last;
	Key(K first,K last)
	{
		this.first=first;
		this.last=last;
	}
	public String toString()
	{
		return first.toString()+last.toString();
	}
	public int compareTo(Key k1)
	{
		return first.toString().compareTo(k1.first.toString());
	}
	
}
