package com.example.episodicevents;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StreamingProduct extends Product {

    private String streamUrl;

    public StreamingProduct(String sku, String name, int priceInCents, String streamUrl) {
        super(sku, name, priceInCents);
        this.streamUrl = streamUrl;
    }
}
