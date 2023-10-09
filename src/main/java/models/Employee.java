package models;

public class Employee {

	private int id;
	private String name;
	private int age;
	private int depId;
	private double salary;
	
	public Employee(int id, String name, int age, double salary, int depId) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.depId = depId;
		this.salary = salary;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getDepId() {
		return depId;
	}
	public void setDepId(int depId) {
		this.depId = depId;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
}
