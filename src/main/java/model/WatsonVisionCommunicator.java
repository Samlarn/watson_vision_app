package model;


import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Communicates with the Watson Vision API
 */
public class WatsonVisionCommunicator {

    private VisualRecognition service;
    private String APIKey = "x";

    /**
     * Open up a service with Watson's visual recognition API.
     * @param APIkey - the API key.
     */
    public WatsonVisionCommunicator() {
        service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);
    }


    public void setAPIKey(String APIKey) {
        this.APIKey = APIKey;
        service.setApiKey(APIKey);
    }


    /**
     * Sends the image to Watson Vision API.
     * @param imageName the image name
     * @param imagePath path to the image
     * @param classifierIds the classifier ids that gonna be used.
     * @param threshold the scores threshold.
     * @return  the classification result.
     * @throws FileNotFoundException throws if image file not found.
     */
    public List<ClassifiedImageResult> getClassifiedImages(String imageName, String imagePath, String classifierIds, String threshold) throws FileNotFoundException {
        InputStream imagesStream = new FileInputStream(imagePath);

        String param = "{\"classifier_ids\": ["+classifierIds+"], \"threshold\": "+threshold+"}";

        ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
                .imagesFile(imagesStream)
                .imagesFilename(imageName)
                .parameters(param)
                .build();

        ClassifiedImages result = service.classify(classifyOptions).execute();
        System.out.println(result);
        return parseClassificationResult(result);
    }

    /**
     * Parses the JSON result from Watson.
     * @param result the JSON result
     * @return a list of the result for all the classified images.
     */
    private List<ClassifiedImageResult> parseClassificationResult(ClassifiedImages result) {
        List<ClassifiedImageResult> results = new ArrayList<ClassifiedImageResult>();

        List<ClassifiedImage> images = result.getImages();
        for(ClassifiedImage image : images) {
            ClassifiedImageResult imageResult = new ClassifiedImageResult(image.getImage());
            List<ClassifierResult> classifiers = image.getClassifiers();
            for(ClassifierResult classifierResult : classifiers) {
                List<ClassResult> classResults = classifierResult.getClasses();
                for(ClassResult classResult : classResults) {
                    imageResult.addResult(classResult.getClassName(), classResult.getScore().toString());
                }
            }
            results.add(imageResult);
        }
        return results;
    }



    /**
     * @return the classifiers from the list.
     */
    public List<Classifier> getCustomClassifiers() {
        ListClassifiersOptions listClassifiersOptions = new ListClassifiersOptions.Builder()
                .verbose(true)
                .build();
        Classifiers classifiers = service.listClassifiers(listClassifiersOptions).execute();
        System.out.println(classifiers);
        return classifiers.getClassifiers();
    }
}
