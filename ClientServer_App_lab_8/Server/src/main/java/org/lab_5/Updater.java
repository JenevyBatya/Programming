package org.lab_5;

public class Updater<T> {
    public T compare(T newForm, T old) {
        if (newForm.equals("null")) {
            return old;
        } else {
            return newForm;
        }
    }
}
