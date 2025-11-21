package com.localbiz.service;

import com.localbiz.model.Business;
import com.localbiz.service.BusinessService;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for searching businesses.
 */
public class SearchService implements ServiceInterface {
    private final BusinessService businessService = new BusinessService();

    public List<Business> searchByName(String name) throws SQLException {
        return businessService.listAllBusinesses().stream()
                .filter(b -> b.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Business> searchByCategory(String category) throws SQLException {
        return businessService.listAllBusinesses().stream()
                .filter(b -> b.getCategory() != null && b.getCategory().getName().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
}
