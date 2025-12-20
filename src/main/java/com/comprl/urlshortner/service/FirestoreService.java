package com.comprl.urlshortner.service;


import com.comprl.urlshortner.exception.ConflictException;
import com.comprl.urlshortner.exception.FirebaseServiceException;
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

import java.util.concurrent.atomic.AtomicBoolean;

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
        ApiFuture<WriteResult> storeRes;
        for (int i=0;i<5;i++){

            String slug = randomIDGeneratorService.generateRandomID();

            if(checkSlug(slug)){
                storeRes = firestore.collection(COLLECTION_NAME)
                        .document(slug)
                        .set(request);
            }else{
                continue;
            }

            try {
                storeRes.get();
                url.setShortUrl("comprl.web.app/"+slug);
                return url;
            }catch (Exception e){
                System.out.println(e);
            }

        }

        throw  new RuntimeException("No such url");
    }

    public Url storeURLwAI(Url url, String slug){

        try {
            FirestoreServiceRequest request = new FirestoreServiceRequest();
            request.setUrl(url.getLongUrl());

            ApiFuture<WriteResult> storeRes = firestore.collection(COLLECTION_NAME).document(slug)
                    .set(request);

            storeRes.get();

            url.setShortUrl("comprl.web.app/" + slug);

            return url;
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("No such url");
        }

    }


    public Boolean checkSlug(String slug) {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(slug);

        ApiFuture<Boolean> storeRes = firestore.runTransaction(transaction -> {

            DocumentSnapshot snapshot = transaction.get(docRef).get();

            return snapshot.exists();
        });

        try {
            return storeRes.get();
        }catch (Exception e){
            throw new FirebaseServiceException("Firestore Service Error");
        }

    }



    public Url storeURLwCustom(Url url, Object slug) {
        String customSlug;
        ApiFuture<WriteResult> storeRes;
        try {
            customSlug = slug.toString();
        }catch (Exception e){
            System.out.println(e);
            throw new ConflictException("Not a valid slug");
        }


        FirestoreServiceRequest request = new FirestoreServiceRequest();
        request.setUrl(url.getLongUrl());

        if(checkSlug(customSlug)){
            throw new ConflictException("Not a valid slug");
        }else{
            storeRes = firestore.collection(COLLECTION_NAME).document(customSlug)
                    .set(request);
        }

        try {
            storeRes.get();
            url.setShortUrl("comprl.web.app/" + customSlug);
            return url;
        }catch (Exception e){
            System.out.println("Firebase Exception");
            throw new FirebaseServiceException("Firestore Service Error");
        }

    }

}
