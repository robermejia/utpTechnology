package com.utp.technology.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.utp.technology.model.Rol;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Repository
public class RolRepository {

    private final Firestore firestore;
    private final String COLLECTION_NAME = "roles";

    public RolRepository(Firestore firestore) {
        this.firestore = firestore;
    }

    public Optional<Rol> findByNombreRol(String nombreRol) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> query = firestore.collection(COLLECTION_NAME)
                .whereEqualTo("nombreRol", nombreRol)
                .get();
        QuerySnapshot querySnapshot = query.get();
        return querySnapshot.getDocuments().stream()
                .findFirst()
                .map(doc -> doc.toObject(Rol.class));
    }

    public Optional<Rol> findById(Integer id) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentSnapshot> future = firestore.collection(COLLECTION_NAME).document(String.valueOf(id)).get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            return Optional.ofNullable(document.toObject(Rol.class));
        }
        return Optional.empty();
    }

    public Rol save(Rol rol) {
        if (rol.getId() == null) {
            firestore.collection(COLLECTION_NAME).add(rol);
        } else {
            firestore.collection(COLLECTION_NAME).document(String.valueOf(rol.getId())).set(rol);
        }
        return rol;
    }
}
