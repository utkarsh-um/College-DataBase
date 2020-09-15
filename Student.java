
public class Student implements Student_{
	String fname,lname,hostel,cgpa,dept;
	Student(String fname,String lname,String hostel,String dept,String cgpa)
	{
		this.cgpa=cgpa;
		this.fname=fname;
		this.lname=lname;
		this.hostel=hostel;
		this.dept=dept;
	}
	public String fname()
	{
		return this.fname;
	}
	   public String lname()
	   {
		return this.lname;
	   }
	   public String hostel()
	   {
		   return this.hostel;
	   }
	   public String department()
	   {
		   return this.dept;
	   }
	   public String cgpa()
	   {
		   return this.cgpa;
	   }
	

}
