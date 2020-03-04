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
        add("goldenretriever");
        add("beagle");
        add("redbone");
        add("cairn");
        add("cardigan");
        add("chow");
        add("pomeranian");
        add("greatpyrenees");
        add("entlebucher");
        add("appenzeller");
        add("collie");
        add("labradorretriever");
        add("lhasa");
        add("cairn");
        add("shihtzu");

    }};

    private ArrayList showbreeds = new ArrayList() {{
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
        add("Kuvasz");
        add("Shih Tzu");

    }};

    public ArrayList getBreeds() {
        return breeds;
    }

    public ArrayList getShowBreeds() {
        return showbreeds;
    }

    private ArrayList imageRandomIndexList = new ArrayList(){{

        add(1);add(2);add(3);add(4);add(5);add(6);add(7);add(8);add(9);add(10);

    }};

    private int currentImageIndex = 0;

    private ArrayList breedRandomIndexList = new ArrayList(){{

        add(1);add(2);add(3);add(4);add(5);add(6);add(7);add(8);add(9);add(10);

    }};

    private int currentBreedIndex = 0;


    //MARK: Constructor
    private DogCategories () {

        Collections.shuffle(imageRandomIndexList);
        Collections.shuffle(breeds);
    }


    public String getRandomDogImageName(String breed){

        if (currentImageIndex == imageRandomIndexList.size()) {
            currentImageIndex = 0;
            Collections.shuffle(imageRandomIndexList);
        }

        int index = (int) imageRandomIndexList.get(currentImageIndex++);

        return breed + "_" + index;
    }

    public String getRandomBreedName() {

        if (currentBreedIndex == breedRandomIndexList.size()) {
            currentBreedIndex = 0;
            Collections.shuffle(breedRandomIndexList);
            System.out.println("resuffl breeds =================================");
        }



        String name =  (String) breeds.get( (int) breedRandomIndexList.get(currentBreedIndex++));

        return name;

    }
}
