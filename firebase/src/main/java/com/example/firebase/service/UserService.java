package com.example.demo.service;

import com.example.demo.entity.User;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    private static final String COLLECTION_NAME = "users";

    public String saveUser(User user) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection(COLLECTION_NAME).document(user.getId()).set(user).get();
        return "User added successfully";
    }

    public List<User> getUsers() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        return dbFirestore.collection(COLLECTION_NAME).get().get().toObjects(User.class);
    }
}
//    List<User> users = userService.getUsers();
