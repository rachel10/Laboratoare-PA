package controller;

import java.util.List;

public interface AbstractController<T> {
    List<T> getAll();
}
