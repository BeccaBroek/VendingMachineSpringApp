/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floor.service;

import com.mycompany.floor.dao.FloorOrderDao;
import com.mycompany.floor.dao.FloorProductDao;
import com.mycompany.floor.dao.FloorStateDao;
import com.mycompany.floor.model.Order;
import com.mycompany.floor.model.Product;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Character.isDigit;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author Becca
 */
public class FloorServiceImpl implements FloorService {

    FloorOrderDao orderDao;
    FloorStateDao stateDao;
    FloorProductDao productDao;

    public FloorServiceImpl(FloorOrderDao orderDao, FloorStateDao stateDao, FloorProductDao productDao) {
        this.orderDao = orderDao;
        this.stateDao = stateDao;
        this.productDao = productDao;
    }

    @Override
    public void load() throws FileNotFoundException {
        orderDao.load();
    }

    @Override
    public HashMap getByDate(String date) throws FileNotFoundException {
        if (orderDao.getByDate(date).isEmpty()) {
            throw new FileNotFoundException();
        } else {
            return orderDao.getByDate(date);
        }
    }

    @Override
    public void addOrder(String customerName, String customerState, String productType, String area) throws FileNotFoundException {
        LocalDate ld = LocalDate.now();
        String orderDate = ld.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        int orderNumber;
        if (!orderDao.getAll().containsKey(orderDate)) {
            orderNumber = 1;
        } else {
            Set<String> orderNumbers = orderDao.getByDate(orderDate).keySet();
            int lastValue = 0;
            for (String number : orderNumbers) {
                if (parseInt(number) > lastValue) {
                    lastValue = parseInt(number);
                }
            }
            orderNumber = lastValue + 1;
        }
        String orderNum = String.valueOf(orderNumber);
        String taxRate = stateDao.getTax(customerState);

        Product currentProduct = productDao.getProduct(productType);
        String costPerSqFt = currentProduct.getCostPerSqFt();
        String laborCostPerSqFt = currentProduct.getLaborCostPerSqFt();

        Order orderToAdd = new Order(orderNum, customerName, customerState, taxRate, productType, area, costPerSqFt, laborCostPerSqFt);

        orderDao.add(orderToAdd, orderDate, orderNum);
    }

    @Override
    public void check(String type, String value) throws InvalidInputException, FileNotFoundException {
        switch (type) {
            case "Name":
                checkName(value);
                break;
            case "State":
                checkState(value);
                break;
            case "Product type":
                checkProductType(value);
                break;
            case "Area":
                checkArea(value);
                break;
            default:
                break;
        }
    }

    @Override
    public void checkName(String name) throws InvalidInputException {
        for (int f = 0; f < name.length(); f++) {
            if (isDigit(name.charAt(f))) {
                InvalidInputException i = new InvalidInputException("name");
                throw i;
            }
        }
    }

    @Override
    public void checkState(String State) throws InvalidInputException, FileNotFoundException {
        Set<String> stateSet = stateDao.getStates();
        if (State.isEmpty()) {
            return;
        }
        if (!stateSet.contains(State)) {
            throw new InvalidInputException("State");
        }
    }

    @Override
    public void checkProductType(String productType) throws InvalidInputException, FileNotFoundException {
        Set<String> productSet = productDao.getProducts();
        if (productType.equals("")) {
            return;
        }
        if (!productSet.contains(productType)) {
            throw new InvalidInputException("Product");
        }
    }

    @Override
    public void checkArea(String area) throws InvalidInputException {
        if (parseDouble(area) <= 0) {
            throw new InvalidInputException("area");
        }
        for (int f = 0; f < area.length(); f++) {
            if (area.charAt(f) != ('.') && !isDigit(area.charAt(f))) {
                throw new InvalidInputException("area");
            }
        }
    }

    @Override
    public void save() throws IOException {
        orderDao.save();
    }

    @Override
    public void checkForOrder(String orderDate, String orderNumber) throws FileNotFoundException {
        if (!orderDao.getAll().containsKey(orderDate)) {
            throw new FileNotFoundException();
        }
        if (!orderDao.getByDate(orderDate).containsKey(orderNumber)) {
            throw new FileNotFoundException();
        }
    }

    @Override
    public Order getOrder(String orderNumber, String date
    ) {
        HashMap<String, Order> ordersForDate = orderDao.getByDate(date);
        return ordersForDate.get(orderNumber);
    }

    @Override
    public void editOrder(String orderNumber, String date,
             String name, String state,
             String product, String area) throws IOException {
        if (!name.equals("")) {
            getOrder(orderNumber, date).setCustomerName(name);
        }
        if (!state.equals("")) {
            getOrder(orderNumber, date).setCustomerState(state);
        }
        if (!product.equals("")) {
            getOrder(orderNumber, date).setProductType(product);
        }
        if (!area.equals("")) {
            getOrder(orderNumber, date).setArea(area);
        }

    }

    @Override
    public void removeOrder(String date, String orderNumber) throws IOException {
        orderDao.remove(date, orderNumber);
    }
}
