package com.luv2code.springcoredemo.common;

public class SwimCoach implements Coach{
    public SwimCoach(){
        System.out.println("In Constructor: " + getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout() {
        return "Swimm 1000 meters as warm 888up";
    }
}
