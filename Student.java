package content;


public class Student {

		private String Id;
		private String Name;
		private String Gender;
		private String Address;
		private int Age ;
		private double Gpa;
		private String ClassId;
		
		public Student() {};
		public Student(String id, String name, String Gender, String Address, String ClassId, int age, double gpa) {
			//super();
			this.Id=id;
			this.Name = name;
			this.Gender = Gender;
			this.Address = Address;
			this.Age = age;
			this.Gpa = gpa;
			this.ClassId = ClassId;
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
		public String getClassId() {
			return ClassId;
		}
		public void setId(String id) {
			Id = id;
		}
		public void setName(String name) {
			Name = name;
		}
		public void setAddress(String address) {
			Address = address;
		}
		public void setAge(int age) {
			Age = age;
		}
		public void setGpa(double gpa) {
			Gpa = gpa;
		}
		public void setClassId(String classId) {
			ClassId = classId;
		}
		
		@Override
	public String toString() {
		return "Student: Id=" + Id + ", Name=" + Name + ", Address=" + Address + ", classStudent=" + ClassId
				+ ", Age=" + Age + ", Gpa=" + Gpa +"\n";
	}
		
		
}
