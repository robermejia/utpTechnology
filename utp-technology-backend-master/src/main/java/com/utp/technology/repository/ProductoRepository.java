package com.utp.technology.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.utp.technology.model.Producto;
import java.util.Optional;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Repository
public class ProductoRepository {

  private final Firestore firestore;
  private final String COLLECTION_NAME = "productos";

  public ProductoRepository(Firestore firestore) {
    this.firestore = firestore;
  }

  public List<Producto> findAll() throws ExecutionException, InterruptedException {
    ApiFuture<QuerySnapshot> query = firestore.collection(COLLECTION_NAME).get();
    QuerySnapshot querySnapshot = query.get();
    return querySnapshot.getDocuments().stream()
        .map(doc -> doc.toObject(Producto.class))
        .collect(Collectors.toList());
  }

  public List<Producto> findAllByIdIn(List<Integer> ids) throws ExecutionException, InterruptedException {
    if (ids == null || ids.isEmpty())
      return new ArrayList<>();
    // Firestore 'in' query supports up to 10-30 items depending on configuration
    ApiFuture<QuerySnapshot> query = firestore.collection(COLLECTION_NAME)
        .whereIn("id", ids)
        .get();
    QuerySnapshot querySnapshot = query.get();
    return querySnapshot.getDocuments().stream()
        .map(doc -> doc.toObject(Producto.class))
        .collect(Collectors.toList());
  }

  public Producto save(Producto producto) {
    if (producto.getId() == null) {
      // Generate unique integer ID based on time + random
      producto.setId(Math.abs((int) System.currentTimeMillis() + (int) (Math.random() * 10000)));
      firestore.collection(COLLECTION_NAME).document(String.valueOf(producto.getId())).set(producto);
    } else {
      firestore.collection(COLLECTION_NAME).document(String.valueOf(producto.getId())).set(producto);
    }
    return producto;
  }

  public void deleteById(Integer id) throws InterruptedException, ExecutionException {
    firestore.collection(COLLECTION_NAME).document(id.toString()).delete().get();
  }

  public void delete(Producto producto) throws InterruptedException, ExecutionException {
    if (producto != null && producto.getId() != null) {
      deleteById(producto.getId());
    }
  }

  public Optional<Producto> findById(Integer id) throws ExecutionException, InterruptedException {
    ApiFuture<DocumentSnapshot> future = firestore.collection(COLLECTION_NAME).document(String.valueOf(id)).get();
    DocumentSnapshot document = future.get();
    if (document.exists()) {
      return Optional.ofNullable(document.toObject(Producto.class));
    }
    return Optional.empty();
  }
}
