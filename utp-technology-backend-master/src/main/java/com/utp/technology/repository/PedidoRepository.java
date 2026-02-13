package com.utp.technology.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.utp.technology.model.Pedido;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Repository
public class PedidoRepository {

  private final Firestore firestore;
  private final String COLLECTION_NAME = "pedidos";

  public PedidoRepository(Firestore firestore) {
    this.firestore = firestore;
  }

  public List<Pedido> findAll() throws ExecutionException, InterruptedException {
    ApiFuture<QuerySnapshot> query = firestore.collection(COLLECTION_NAME).get();
    QuerySnapshot querySnapshot = query.get();
    return querySnapshot.getDocuments().stream()
        .map(doc -> doc.toObject(Pedido.class))
        .collect(Collectors.toList());
  }

  public List<Pedido> findByUsuarioId(Integer idUsuario) throws ExecutionException, InterruptedException {
    ApiFuture<QuerySnapshot> query = firestore.collection(COLLECTION_NAME)
        .whereEqualTo("idUsuario", idUsuario)
        .get();
    QuerySnapshot querySnapshot = query.get();
    return querySnapshot.getDocuments().stream()
        .map(doc -> doc.toObject(Pedido.class))
        .collect(Collectors.toList());
  }

  public Pedido save(Pedido pedido) {
    if (pedido.getId() == null) {
      firestore.collection(COLLECTION_NAME).add(pedido);
    } else {
      firestore.collection(COLLECTION_NAME).document(String.valueOf(pedido.getId())).set(pedido);
    }
    return pedido;
  }

  public java.util.Optional<Pedido> findById(Integer id) throws ExecutionException, InterruptedException {
    ApiFuture<DocumentSnapshot> future = firestore.collection(COLLECTION_NAME).document(String.valueOf(id)).get();
    DocumentSnapshot document = future.get();
    if (document.exists()) {
      return java.util.Optional.ofNullable(document.toObject(Pedido.class));
    }
    return java.util.Optional.empty();
  }
}
