package com.biblioteca.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Section {
    private ArrayList<Item> items;
    private HashMap<Item,String> issuedItems;

    public Section(ArrayList<Item> items) {
        this.items = items;
        issuedItems = new HashMap<>();
    }

    public String items() {
        String itemsDetails = "";
        for(Item item : items) {
            itemsDetails = itemsDetails + item.toString() + '\n';
        }
        return itemsDetails;
    }

    public boolean checkout(String name, User user) {
        for(Item item : items) {
            if(item.hasTitle(name)) {
                items.remove(item);
                issuedItems.put(item, user.getLibraryNumber());
                return true;
            }
        }
        return false;
    }

    public boolean checkin(String name, User user) {
        for(Map.Entry<Item, String > issuedItem: issuedItems.entrySet()) {
            if(issuedItem.getKey().hasTitle(name) && issuedItem.getValue().equals(user.getLibraryNumber())) {
                items.add(issuedItem.getKey());
                issuedItems.remove(issuedItem);
                return true;
            }
        }
        return false;
    }

    public String checkedOutItems() {
        String checkedOutItemsDetails = "";
        for(Map.Entry<Item, String> issuedItem : issuedItems.entrySet()) {
            checkedOutItemsDetails = checkedOutItemsDetails + issuedItem.getKey().toString() + " checked out by " + issuedItem.getValue()  +'\n';
        }
        return checkedOutItemsDetails;
    }
}