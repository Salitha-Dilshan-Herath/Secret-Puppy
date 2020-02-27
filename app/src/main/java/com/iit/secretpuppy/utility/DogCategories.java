package com.iit.secretpuppy.utility;


import java.util.ArrayList;
import java.util.Collections;

public class DogCategories {

    private static DogCategories singletonObj = new DogCategories();

    public static DogCategories getInstance(){
        return singletonObj;
    }

    //MARK: Variables
    private ArrayList breeds = new ArrayList() {{
        add("Golden Retriever");
        add("Beagle");
        add("Redbone");
        add("Cairn");
        add("Cardigan");
        add("Chow");
        add("Pomeranian");
        add("Great Pyrenees");
        add("EntleBucher");
        add("Appenzeller");
        add("Collie");
        add("Labrador Retriever");
        add("Lhasa");
        add("Cairn");
        add("Shih-Tzu");

    }};

    public ArrayList getBreeds() {
        return breeds;
    }

    private ArrayList randomIndexList = new ArrayList(){{

        add(1);add(2);add(3);add(4);add(5);add(6);add(7);add(8);add(9);add(10);

    }};

    private int currentIndex = 0;


    //MARK: Constructor
    private DogCategories () {

        Collections.shuffle(randomIndexList);
    }


    public void shuffleBreedList () {

        Collections.shuffle(breeds);
//        for (int i=0; i< breeds.size(); i++) {
//            System.out.println(breeds.get(i));
//        }
    }


    public String getRandomDogImageName(String breed){

        if (currentIndex == randomIndexList.size()) {
            currentIndex = 0;
            Collections.shuffle(randomIndexList);
        }



        int index = (int) randomIndexList.get(currentIndex++);

        return breed + "_" + index;
    }

}
