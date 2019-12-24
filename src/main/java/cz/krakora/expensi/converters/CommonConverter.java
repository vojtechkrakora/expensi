package cz.krakora.expensi.converters;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonConverter<T,E> {

    public CommonConverter() {
    }

    public List<T> allToDto(List<E> data) {
        if (data == null) {
            return null;
        }

        List<T> dtoList = new ArrayList<T>();
        for (E e: data) {
            dtoList.add(toDto(e));
        }

        return dtoList;
    }

    public List<E> allToData(List<T> dtoList) {
        if (dtoList == null) {
            return null;
        }

        List<E> data = new ArrayList<E>();
        for (T t: dtoList) {
            data.add(toData(t));
        }

        return data;
    }

    public abstract T toDto(E data);
    public abstract E toData(T dto);
}
