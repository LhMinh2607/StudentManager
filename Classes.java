package content;

public class Classes {
	private String ClassId;
	private String ClassName;
	
	public Classes() {};
	public Classes(String ClassId, String ClassName)
	{
		this.ClassId=ClassId;
		this.ClassName=ClassName;
	}
	
	public String getClassId()
	{
		return ClassId;
	}
	public String getClassName()
	{
		return ClassName;
	}
	
	public void setClassId(String classId)
	{
		ClassId=classId;
	}
	public void setClassName(String className)
	{
		ClassName=className;
	}
}
