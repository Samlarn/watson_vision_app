package model;

import java.util.HashMap;

public class ClassifiedImageResult {

    private String imageName;
    private HashMap<String, String> imageResult;

    public ClassifiedImageResult(String imageName) {
        this.imageName = imageName;
        this.imageResult = new HashMap<String, String>();
    }

    public String getImageName() {
        return this.imageName;
    }

    public void addResult(String className, String score) {
        imageResult.put(className, score);
    }

    public HashMap<String, String> getImageResult() {
        return this.imageResult;
    }

    public String getClassScore(String className) {
        return imageResult.get(className);
    }

}
