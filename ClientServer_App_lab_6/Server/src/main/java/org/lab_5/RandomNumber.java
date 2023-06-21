package org.lab_5;

import org.lab_5.Models.Organization;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
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
