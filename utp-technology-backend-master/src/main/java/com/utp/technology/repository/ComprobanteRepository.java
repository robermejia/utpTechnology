package com.utp.technology.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.utp.technology.model.Comprobante;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Repository
public class ComprobanteRepository {

    private final Firestore firestore;
    private final String COLLECTION_NAME = "comprobantes";

    public ComprobanteRepository(Firestore firestore) {
        this.firestore = firestore;
    }

    public Comprobante save(Comprobante comprobante) {
        if (comprobante.getId() == null) {
            comprobante.setId(Math.abs((int) System.currentTimeMillis() + (int) (Math.random() * 10000)));
            firestore.collection(COLLECTION_NAME).document(String.valueOf(comprobante.getId())).set(comprobante);
        } else {
            firestore.collection(COLLECTION_NAME).document(String.valueOf(comprobante.getId())).set(comprobante);
        }
        return comprobante;
    }

    public Optional<Comprobante> findByPedidoId(Integer idPedido) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> query = firestore.collection(COLLECTION_NAME)
                .whereEqualTo("idPedido", idPedido)
                .get();
        QuerySnapshot querySnapshot = query.get();
        return querySnapshot.getDocuments().stream()
                .findFirst()
                .map(doc -> doc.toObject(Comprobante.class));
    }
}
