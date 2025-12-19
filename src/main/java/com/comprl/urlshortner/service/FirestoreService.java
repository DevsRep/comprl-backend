package com.comprl.urlshortner.service;


import com.comprl.urlshortner.config.FirebaseConfig;
import com.comprl.urlshortner.model.FirestoreServiceRequest;
import com.comprl.urlshortner.model.Url;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class FirestoreService {


    @Autowired
    private Firestore firestore;

    @Autowired
    private RandomIDGeneratorService randomIDGeneratorService;

    @Value("${FIRESTORE_COLLECTION_NAME}")
    private String COLLECTION_NAME;


//    public Url storeUrl(Url url) throws Exception{
//        FirestoreServiceRequest request = new FirestoreServiceRequest();
//        request.setUrl(url.getLongUrl());
////        request.setDate(Timestamp.
//
//
//        DocumentReference documentReference = firestore.collection(COLLECTION_NAME).document();
//
//        ApiFuture<WriteResult> storeRes = documentReference
//                .set(request);
//
//        storeRes.get();
//        url.setShortUrl("comprl.web.app/"+documentReference.getId());
//        return url;
//    }


    public Url storeURL(Url url) {
        FirestoreServiceRequest request = new FirestoreServiceRequest();
        request.setUrl(url.getLongUrl());

        for (int i=0;i<5;i++){

            String slug = randomIDGeneratorService.generateRandomID();
            DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(slug);

            ApiFuture<String> storeRes = firestore.runTransaction(transaction -> {

                DocumentSnapshot snapshot =  transaction.get(docRef).get();

                if(snapshot.exists()){
                    throw new RuntimeException(slug + " already exists");
                }

                transaction.set(docRef, request);

                return slug;

            });

            try {
                String urlSlug =  storeRes.get();
                url.setShortUrl("comprl.web.app/"+urlSlug);
                return url;
            }catch (Exception e){
                System.out.println(e);
            }

        }

        throw  new RuntimeException("No such url");
    }

    public Url storeURLwAI(Url url, String slug) throws Exception{

        FirestoreServiceRequest request = new FirestoreServiceRequest();
        request.setUrl(url.getLongUrl());

        ApiFuture<WriteResult> storeRes =  firestore.collection(COLLECTION_NAME).document(slug)
                .set(request);

        storeRes.get();

        url.setShortUrl("comprl.web.app/"+slug);

        return url;

    }

}
