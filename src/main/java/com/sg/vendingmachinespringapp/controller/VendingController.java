/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringapp.controller;

import com.sg.vendingmachinespringapp.model.Change;
import com.sg.vendingmachinespringapp.model.Fruit;
import com.sg.vendingmachinespringapp.service.InsufficientFundsException;
import com.sg.vendingmachinespringapp.service.InsufficientInventoryException;
import com.sg.vendingmachinespringapp.service.VendingService;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Becca
 */
@Controller
public class VendingController {

    VendingService VendingService;
    String itemSelected = "";
    String currentTotalString = "";
    BigDecimal currentTotal;
    String message;
    Collection<Fruit> allFruit;

    @Inject
    public VendingController(VendingService VendingService) {
        this.VendingService = VendingService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {
        allFruit = VendingService.getInventory();
        model.put("allFruit", allFruit);
        model.put("total", currentTotalString);
        model.put("itemSelected", itemSelected);
        model.put("message", message);

        return "index";
    }

    @RequestMapping(value = "/addMoney", method = RequestMethod.POST)
    public String addMoney(HttpServletRequest request,
            Map<String, Object> model) {
        message = "";
        String money = request.getParameter("money");
        String total = request.getParameter("total");
        BigDecimal moneyToAdd;
        if (total.equals("")) {
            currentTotal = new BigDecimal("0");
        } else {
            currentTotal = new BigDecimal(total);
        }
        moneyToAdd = new BigDecimal(money);
        currentTotal = currentTotal.add(moneyToAdd);
        currentTotalString = currentTotal.setScale(2).toString();
        allFruit = VendingService.getInventory();
        return "redirect:/";
    }

    @RequestMapping(value = "/selectItem", method = RequestMethod.POST)
    public String selectItem(HttpServletRequest request,
            Map<String, Object> model) {
        itemSelected = request.getParameter("item");
        allFruit = VendingService.getInventory();
        return "redirect:/";
    }

    @RequestMapping(value = "/getChange", method = RequestMethod.GET)
    public String getChange(HttpServletRequest request,
            Map<String, Object> model) {
        Change change = VendingService.dispenseChange(currentTotal);
        allFruit = VendingService.getInventory();
        currentTotal = new BigDecimal("0");
        currentTotalString = "0.00";
        message = "Your change has been dispensed";
        
        model.put("message", message);
        model.put("quarters", change.getQuarters());
        model.put("dimes", change.getDimes());
        model.put("nickels", change.getNickels());
        model.put("allFruit", allFruit);
        return "index";
    }

    @RequestMapping(value = "/purchaseItem", method = RequestMethod.POST)
    public String purchaseItem(HttpServletRequest request,
            Map<String, Object> model) {

        try {
            VendingService.checkInventory(itemSelected);
            VendingService.checkUserEnoughMoney(currentTotal, itemSelected);
            VendingService.removeItem(itemSelected);
            currentTotal = VendingService.chargeAndReturnTotal(currentTotal, itemSelected);
            currentTotalString = currentTotal.toString();
            allFruit = VendingService.getInventory();
            message = "Your " + itemSelected + " has been dispensed. Please select another item or push 'Get change' to dispense your change.";
        } catch (InsufficientInventoryException | InsufficientFundsException e) {
            message = (e.getMessage());
            allFruit = VendingService.getInventory();

        } catch (IOException e) {
        }
        return "redirect:/";
    }
}
