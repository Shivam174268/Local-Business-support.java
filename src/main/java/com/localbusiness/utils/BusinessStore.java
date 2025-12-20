package com.localbusiness.util;

import java.util.ArrayList;
import java.util.List;
import com.localbusiness.model.Business;

public class BusinessStore {
    private static final List<Business> businesses = new ArrayList<>();

    public static void addBusiness(Business b) {
        businesses.add(b);
    }

    public static List<Business> getBusinesses() {
        return businesses;
    }
}
