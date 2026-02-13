package com.utp.technology.services.impl;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.utp.technology.model.Cliente;
import com.utp.technology.repository.ClienteRepository;
import com.utp.technology.services.PdfPedidoService;
import com.utp.technology.services.PedidoService;

@Service
public class PdfPedidoServiceImpl implements PdfPedidoService {

    private final PedidoService pedidoService;
    private final ClienteRepository clienteRepository;

    public PdfPedidoServiceImpl(PedidoService pedidoService, ClienteRepository clienteRepository) {
        this.pedidoService = pedidoService;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public byte[] generarComprobantePdf(Integer pedidoId) {

        try {
            var pedidoOpt = this.pedidoService.findById(pedidoId);

            if (pedidoOpt.isEmpty()) {
                return null;
            }

            var pedido = pedidoOpt.get();
            var detalles = this.pedidoService.listPedidoDetalles(pedidoId);

            // Fetch client name manually
            var clienteOpt = this.clienteRepository.findByUsuarioId(pedido.getIdUsuario());
            String nombreCliente = clienteOpt.isPresent()
                    ? (clienteOpt.get().getNombre() + " " + clienteOpt.get().getApellido())
                    : "Cliente Desconocido";

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PdfWriter writer = new PdfWriter(byteArrayOutputStream);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);
            document.setLeftMargin(25);
            document.setRightMargin(25);

            String nro = String.format("%05d", pedido.getId());
            Paragraph titulo = new Paragraph("COMPROBANTE N° " + nro).setTextAlignment(TextAlignment.CENTER);
            document.add(titulo);

            // Fecha / Cliente
            var fechaP = new Paragraph("Fecha: " + (pedido.getFecha() != null ? pedido.getFecha().toString() : "N/A"))
                    .setTextAlignment(TextAlignment.LEFT);
            document.add(fechaP);

            var clienteP = new Paragraph("Cliente: " + nombreCliente)
                    .setTextAlignment(TextAlignment.LEFT);
            document.add(clienteP);

            var separatorP = new Paragraph(
                    "----------------------------------------------------------------------------------------------------------------------------------------")
                    .setTextAlignment(TextAlignment.LEFT);
            document.add(separatorP);

            Table table = new Table(5);
            table.setWidth(UnitValue.createPercentValue(100));

            table.addHeaderCell(new Paragraph("#").setTextAlignment(TextAlignment.CENTER));
            table.addHeaderCell(new Paragraph("Producto").setTextAlignment(TextAlignment.CENTER));
            table.addHeaderCell(new Paragraph("Precio").setTextAlignment(TextAlignment.CENTER));
            table.addHeaderCell(new Paragraph("Cantidad").setTextAlignment(TextAlignment.CENTER));
            table.addHeaderCell(new Paragraph("Total Detalle").setTextAlignment(TextAlignment.CENTER));

            AtomicInteger index = new AtomicInteger(0);
            List<String[]> list = detalles.stream().map(detalle -> {
                Integer currentIndex = index.incrementAndGet();
                return new String[] {
                        currentIndex.toString(), // #
                        detalle.getProducto(), // Producto
                        "S/ " + (detalle.getPrecioUnitario() != null ? detalle.getPrecioUnitario().toString() : "0.0"), // Precio
                        (detalle.getCantidad() != null ? detalle.getCantidad().toString() : "0"), // Cantidad
                        "S/ " + (detalle.getSubtotal() != null ? detalle.getSubtotal().toString() : "0.0"), // Total
                                                                                                            // detalle
                };
            }).toList();

            for (var item : list) {
                table.addCell(new Paragraph(item[0]).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Paragraph(item[1]));
                table.addCell(new Paragraph(item[2]).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Paragraph(item[3]).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Paragraph(item[4]).setTextAlignment(TextAlignment.CENTER));
            }

            document.add(table);

            document.add(separatorP);

            // TOTAL
            Double total = detalles.stream().reduce(0.0, (sum, detalle) -> {
                return sum + (detalle.getSubtotal() != null ? detalle.getSubtotal() : 0.0);
            }, Double::sum);
            var totalP = new Paragraph("Total: " + total).setTextAlignment(TextAlignment.RIGHT);
            document.add(totalP);

            document.close();

            return byteArrayOutputStream.toByteArray();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
