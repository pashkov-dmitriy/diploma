package com.mymusic.catalog.domain.interfaces;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collection;

public interface ItemCollection<T extends Durable> {
    @JsonIgnore
    Collection<T> getItems();

    @JsonIgnore
    default Number getItemCount() {
        return getItems().size();
    }

    @JsonIgnore
    default Number getCollectionLength() {
        return getItems().stream()
                .mapToLong(T::getDuration).sum();
    }
}
