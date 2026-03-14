package com.example.entity;
import javax.persistence.*;
@Entity
@Table(name = "student2")
public class Student 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String department;
    private int marks;
    public Student() 
    {
    	
    }
    public Student(String name, String department, int marks) 
    {
        this.name = name;
        this.department = department;
        this.marks = marks;
    }

    public int getId() 
    { 
        return id; 
    }

    public String getName() 
    { 
        return name; 
    }

    public String getDepartment() 
    { 
        return department; 
    }

    public int getMarks() 
    { 
        return marks; 
    }
}
