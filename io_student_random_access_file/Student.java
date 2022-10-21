package io_student_random_access_file;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Student {
	private int id;
	private String name;
	private int year;
	private double score;
	
	public Student() {};
	
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
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", year=" + year + ", score=" + score + "]";
	}
	
	public void save(DataOutput stream) throws IOException {
		stream.writeInt(id);
		stream.writeUTF(name);
		stream.writeInt(year);
		stream.writeDouble(score);
		
	}
	public void read(DataInput stream) throws IOException {
		id = stream.readInt();
		name = stream.readUTF();
		year = stream.readInt();
		score = stream.readDouble();
		
		
	}
	
	
}
