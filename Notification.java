package zb.hermes.util;

public class Notification<T> {
    private Operation _operation;
    private T _item;

    public Notification(Operation operation, T item) {
        _operation = operation;
        _item = item;
    }

    public Operation getOperation() {
        return _operation;
    }

    public T getItem() {
        return _item;
    }

    public enum Operation {
        ADD,
        REMOVE
    }
}
