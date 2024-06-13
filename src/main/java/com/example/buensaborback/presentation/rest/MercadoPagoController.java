package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.domain.dto.Pedido.PedidoCreateDto;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MercadoPagoController {

    public Preference getPreferenciaIdMercadoPago(PedidoCreateDto pedido) {

        try {
            MercadoPagoConfig.setAccessToken("TEST-4479067771027558-060504-052cb3541b7f6cc58be11bfeed44259f-694496798");
            PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                    .id(pedido.getId().toString())
                    .title("pedido de id:" + pedido.getId().toString())
                    .description("Pedido realizado desde el carrito de compra")
                    .pictureUrl("https://www.recetasnestle.com.ec/sites/default/files/srh_recipes/4e4293857c03d819e4ae51de1e86d66a.jpg")
                    .quantity(1)
                    .currencyId("ARG")
                    .unitPrice(BigDecimal.valueOf(pedido.getTotal()))
                    .build();
            List<PreferenceItemRequest> items = new ArrayList<>();
            items.add(itemRequest);

            PreferenceBackUrlsRequest backURL = PreferenceBackUrlsRequest.builder().success("http://localhost:5173/")
                    .pending("http://localhost:5173/").failure("http://localhost:5173/").build();

            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(items)
                    .backUrls(backURL)
                    .build();
            PreferenceClient client = new PreferenceClient();
            System.out.println(client.create(preferenceRequest));
            Preference preference = client.create(preferenceRequest);


            return preference;

        } catch (MPApiException e) {
            // Obtener los detalles del error de la API
            System.err.println("API Error: " + e.getApiResponse().getContent());
            e.printStackTrace();
            return new Preference(); // Devuelve un objeto vacío para evitar null
        } catch (MPException e) {
            e.printStackTrace();
            return new Preference(); // Devuelve un objeto vacío para evitar null
        }
    }

}

