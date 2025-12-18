package com.comprl.urlshortner.service;


import com.comprl.urlshortner.config.FirebaseConfig;
import com.comprl.urlshortner.model.FirestoreServiceRequest;
import com.comprl.urlshortner.model.Url;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FirestoreService {


    @Autowired
    private Firestore firestore;

    @Value("${FIRESTORE_COLLECTION_NAME}")
    private String COLLECTION_NAME;


    public Url storeUrl(Url url) throws Exception{
        FirestoreServiceRequest request = new FirestoreServiceRequest();
        request.setUrl(url.getLongUrl());
        request.setDate(System.);

        DocumentReference documentReference = firestore.collection(COLLECTION_NAME).document();

        ApiFuture<WriteResult> storeRes = documentReference
                .set(request);

        storeRes.get();
        url.setShortUrl("comprl.web.app/"+documentReference.getId());
        return url;
    }


}
