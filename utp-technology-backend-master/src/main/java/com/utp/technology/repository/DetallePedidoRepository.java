package com.utp.technology.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.utp.technology.model.DetallePedido;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Repository
public class DetallePedidoRepository {

    private final Firestore firestore;
    private final String COLLECTION_NAME = "detalle_pedidos";

    public DetallePedidoRepository(Firestore firestore) {
        this.firestore = firestore;
    }

    public DetallePedido save(DetallePedido detallePedido) {
        try {
            if (detallePedido.getId() == null) {
                detallePedido.setId(Math.abs((int) System.currentTimeMillis() + (int) (Math.random() * 10000)));
            }
            firestore.collection(COLLECTION_NAME).document(String.valueOf(detallePedido.getId())).set(detallePedido);
            return detallePedido;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<DetallePedido> findByIdPedido(Integer idPedido) {
        try {
            ApiFuture<QuerySnapshot> future = firestore.collection(COLLECTION_NAME).whereEqualTo("idPedido", idPedido)
                    .get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            if (!documents.isEmpty()) {
                return documents.stream()
                        .map(doc -> doc.toObject(DetallePedido.class))
                        .collect(Collectors.toList());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
