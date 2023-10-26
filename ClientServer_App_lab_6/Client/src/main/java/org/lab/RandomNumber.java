package org.lab;

import org.lab.Models.Organization;

import java.util.Hashtable;
import java.util.concurrent.ThreadLocalRandom;

public class RandomNumber {
    public static int createRandomNum(Hashtable<Integer, Organization> organizationTable){
        int randomNum = ThreadLocalRandom.current().nextInt(1,10000);
        while(organizationTable.containsKey(randomNum)){
            randomNum = ThreadLocalRandom.current().nextInt(1,10000);
        }
        return randomNum;
    }
}
