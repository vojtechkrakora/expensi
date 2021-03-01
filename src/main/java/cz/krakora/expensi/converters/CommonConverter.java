package cz.krakora.expensi.converters;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface CommonConverter<T,E> {

    default List<T> allToDto(List<E> data) {
        if (data == null) {
            return new ArrayList<>();
        }

        return data.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    default List<E> allToData(List<T> dtoList) {
        if (dtoList == null) {
            return new ArrayList<>();
        }

        return dtoList.stream()
                .map(this::toData)
                .collect(Collectors.toList());
    }

    T toDto(E data);
    E toData(T dto);
}
