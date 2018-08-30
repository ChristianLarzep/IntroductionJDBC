/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dto;

/**
 *
 * @author christian
 */
public class Student {
    
    private int id_student;
    private String name;
    private int age;
    private int cohort;
    
    public Student(){}
    
    public Student(int id_student){
        this.id_student = id_student;
    }

    public int getId_student() {
        return id_student;
    }

    public void setId_student(int id_student) {
        this.id_student = id_student;
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

    public int getCohort() {
        return cohort;
    }

    public void setCohort(int cohort) {
        this.cohort = cohort;
    }
    
}
