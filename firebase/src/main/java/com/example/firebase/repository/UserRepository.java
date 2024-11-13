package com.example.firebase.repository;

import com.example.firebase.entity.User;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class UserRepository {

    private static final String COLLECTION_NAME = "users";

    public String save(User user) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection(COLLECTION_NAME).document(user.getId()).set(user).get();
        return "User saved successfully with ID: " + user.getId();
    }

    public List<User> findAll() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        return dbFirestore.collection(COLLECTION_NAME).get().get().toObjects(User.class);
    }

    public User findById(String id) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        return dbFirestore.collection(COLLECTION_NAME).document(id).get().get().toObject(User.class);
    }

    public String deleteById(String id) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection(COLLECTION_NAME).document(id).delete().get();
        return "User deleted with ID: " + id;
    }

    public List<User> findByAgeGreaterThan(int age) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        return dbFirestore.collection(COLLECTION_NAME)
                .whereGreaterThan("age", age)
                .get()
                .get()
                .toObjects(User.class);
    }
}
