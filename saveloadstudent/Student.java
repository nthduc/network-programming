package saveloadstudent;

public class Student {
	private int id;
	private String name;
	private int year;
	private double score;
	public Student(int id, String name, int year, double score) {
		this.id = id;
		this.name = name;
		this.year = year;
		this.score = score;
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
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	
	
	
}
