package content;


public class Student {

		private String Id;
		private String Name;
		private String Gender;
		private String Address;
		private String classStudent;
		private int Age ;
		private double Gpa;
		
		public Student() {};
		public Student(String id, String name, String Gender, String Address, String class1, int i, double d) {
			super();
			this.Id=id;
			this.Name = name;
			this.Gender = Gender;
			this.Address = Address;
			this.classStudent = class1;
			this.Age = i;
			this.Gpa = d;
		}
		public String getId() {
			return Id;
		}
		public String getName() {
			return Name;
		}
		public String getGender() {
			return Gender;
		}
		public String getAddress() {
			return Address;
		}
	
		public int getAge() {
			return Age;
		}
		public double getGpa() {
			return Gpa;
		}
		public String getClassStudent() {
			return classStudent;
		}
		public void setClassStudent(String classStudent) {
			this.classStudent = classStudent;
		}
		public void setId(String id) {
			Id = id;
		}
		public void setName(String name) {
			Name = name;
		}
		public void setAddress(String Address) {
			Address = Address;
		}
		public void setAge(int age) {
			Age = age;
		}
		public void setGpa(double gpa) {
			Gpa = gpa;
		}
		
		@Override
	public String toString() {
		return "Student: Id=" + Id + ", Name=" + Name + ", Address=" + Address + ", classStudent=" + classStudent
				+ ", Age=" + Age + ", Gpa=" + Gpa +"\n";
	}
		
		
}
