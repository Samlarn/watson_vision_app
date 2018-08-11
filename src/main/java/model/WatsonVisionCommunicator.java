package model;


import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
     * fetches the classified images from IBM Watson image visualisation API.
     * @param key - the clients API key.
     * @param imagePath - the path/url to the image to be classified.
     * @return the classified image tags and scores.
     */
    public ClassifiedImages getClassifiedImages(String imageName, String imagePath, String classifierIds, String threshold) throws FileNotFoundException {
        InputStream imagesStream = new FileInputStream(imagePath);

        String param = "{\"classifier_ids\": ["+classifierIds+"], \"threshold\": "+threshold+"}";

        ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
                .imagesFile(imagesStream)
                .imagesFilename(imageName)
                .parameters(param)
                .build();

        ClassifiedImages result = service.classify(classifyOptions).execute();
        System.out.println(result);
        return result;
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
