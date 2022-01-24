package net.guides.springboot2.crud.model.enums;

public enum PersonStatuse {
    CONFIRMED, WAITING, NEW;

    public static PersonStatuse getValue(String status) {
        if ("confirmed".equals(status.toLowerCase())) {
            return CONFIRMED;
        } else if ("new".equals(status.toLowerCase())) {
            return NEW;
        } else {
            return WAITING;
        }
    }
}
