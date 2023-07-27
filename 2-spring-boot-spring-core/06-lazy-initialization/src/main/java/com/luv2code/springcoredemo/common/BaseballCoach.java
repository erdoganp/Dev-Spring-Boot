package com.luv2code.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class BaseballCoach  implements Coach{

    public BaseballCoach(){
        System.out.println("ın constructor: "+ getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout() {
        return "Spend 30minutes in batting practice";
    }
}
