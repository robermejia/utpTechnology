package com.utp.technology.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.utp.technology.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Repository
public class UsuarioRepository {

    private final Firestore firestore;
    private final String COLLECTION_NAME = "usuarios";

    public UsuarioRepository(Firestore firestore) {
        this.firestore = firestore;
    }

    public Optional<Usuario> findByNombre(String nombre) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> query = firestore.collection(COLLECTION_NAME)
                .whereEqualTo("nombre", nombre)
                .get();
        QuerySnapshot querySnapshot = query.get();
        return querySnapshot.getDocuments().stream()
                .findFirst()
                .map(doc -> doc.toObject(Usuario.class));
    }

    public Optional<Usuario> findByCorreo(String correo) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> query = firestore.collection(COLLECTION_NAME)
                .whereEqualTo("correo", correo)
                .get();
        QuerySnapshot querySnapshot = query.get();
        return querySnapshot.getDocuments().stream()
                .findFirst()
                .map(doc -> doc.toObject(Usuario.class));
    }

    public Optional<Usuario> findById(Integer id) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentSnapshot> future = firestore.collection(COLLECTION_NAME).document(String.valueOf(id)).get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            return Optional.ofNullable(document.toObject(Usuario.class));
        }
        return Optional.empty();
    }

    public Usuario save(Usuario usuario) {
        if (usuario.getId() == null) {
            firestore.collection(COLLECTION_NAME).add(usuario);
        } else {
            firestore.collection(COLLECTION_NAME).document(String.valueOf(usuario.getId())).set(usuario);
        }
        return usuario;
    }

    public void deleteById(Integer id) {
        firestore.collection(COLLECTION_NAME).document(String.valueOf(id)).delete();
    }

    public boolean existsByNombre(String nombre) throws ExecutionException, InterruptedException {
        return findByNombre(nombre).isPresent();
    }

    public boolean existsByCorreo(String correo) throws ExecutionException, InterruptedException {
        return findByCorreo(correo).isPresent();
    }

    public java.util.List<Usuario> findAll() throws ExecutionException, InterruptedException {
        return firestore.collection(COLLECTION_NAME).get().get().getDocuments().stream()
                .map(doc -> doc.toObject(Usuario.class))
                .collect(java.util.stream.Collectors.toList());
    }
}
