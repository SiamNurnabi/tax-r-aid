package com.example.taxraid.controller;

import com.example.taxraid.entity.TaxSlab;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/calculator")
public class CalculatorController {

    private static final Map<String, List<TaxSlab>> TAX_SLABS = Map.of(
            "Individual", List.of(
                    new TaxSlab(0, 350000, 0.0),
                    new TaxSlab(350001, 450000, 0.05),
                    new TaxSlab(450001, 750000, 0.10),
                    new TaxSlab(750001, 1150000, 0.15),
                    new TaxSlab(1150001, 1650000, 0.20),
                    new TaxSlab(1650001, Double.MAX_VALUE, 0.25)
            ),
            "Female/Senior Citizen", List.of(
                    new TaxSlab(0, 400000, 0.0),
                    new TaxSlab(400001, 500000, 0.05),
                    new TaxSlab(500001, 800000, 0.10),
                    new TaxSlab(800001, 1200000, 0.15),
                    new TaxSlab(1200001, 1700000, 0.20),
                    new TaxSlab(1700001, Double.MAX_VALUE, 0.25)
            ),
            "Disabled", List.of(
                    new TaxSlab(0, 450000, 0.0),
                    new TaxSlab(450001, 550000, 0.05),
                    new TaxSlab(550001, 850000, 0.10),
                    new TaxSlab(850001, 1250000, 0.15),
                    new TaxSlab(1250001, 1750000, 0.20),
                    new TaxSlab(1750001, Double.MAX_VALUE, 0.25)
            )
    );

    private static final List<RebateRule> REBATE_RULES = List.of(
            new RebateRule("Investment Rebate", "15% of tax amount (maximum ৳15,000)"),
            new RebateRule("Senior Citizen", "Additional 10% rebate for taxpayers above 65"),
            new RebateRule("Disabled Person", "Additional ৳50,000 tax-free income")
    );

    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("taxpayerTypes", TAX_SLABS.keySet());
        // Add tax slabs and rebate rules to the model
        model.addAttribute("taxSlabs", TAX_SLABS);
        model.addAttribute("rebateRules", REBATE_RULES);
        return "tax-calculator";
    }

    @PostMapping("/calculate-tax")
    public String calculateTax(
            @RequestParam double annualIncome,
            @RequestParam String taxpayerType,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false, defaultValue = "0") int age,
            @RequestParam(required = false) String residentStatus,
            @RequestParam(required = false) String assessmentYear,
            @RequestParam(required = false, defaultValue = "false") boolean hasInvestment,
            Model model,
            HttpServletRequest request) {

        // Your existing calculation logic
        double taxAmount = calculateBangladeshTax(annualIncome, taxpayerType);
        double rebateAmount = hasInvestment ? Math.min(taxAmount * 0.15, 15000) : 0;
        double netTax = taxAmount - rebateAmount;

        // Add attributes to model
        model.addAttribute("annualIncome", annualIncome);
        model.addAttribute("taxpayerType", taxpayerType);
        model.addAttribute("gender", gender);
        model.addAttribute("age", age);
        model.addAttribute("residentStatus", residentStatus);
        model.addAttribute("assessmentYear", assessmentYear);
        model.addAttribute("hasInvestment", hasInvestment);
        model.addAttribute("taxAmount", taxAmount);
        model.addAttribute("rebateAmount", rebateAmount);
        model.addAttribute("netTax", netTax);

        model.addAttribute("taxpayerTypes", TAX_SLABS.keySet());
        // Add tax slabs and rebate rules to the model
        model.addAttribute("taxSlabs", TAX_SLABS);
        model.addAttribute("rebateRules", REBATE_RULES);

        // Check if it's an AJAX request
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            return "tax-calculator :: #resultSection";
        }
        return "tax-calculator";
    }

    private double calculateBangladeshTax(double income, String taxpayerType) {
        List<TaxSlab> slabs = TAX_SLABS.getOrDefault(taxpayerType, TAX_SLABS.get("Individual"));
        double tax = 0;

        for (TaxSlab slab : slabs) {
            if (income > slab.getMin()) {
                double taxable = Math.min(income, slab.getMax()) - slab.getMin();
                tax += taxable * slab.getRate();
            }
        }

        if (income > slabs.get(0).getMax() && tax < 5000) {
            tax = 5000;
        }

        return tax;
    }

    // Add this inner class to your controller
    private static class RebateRule {
        private final String title;
        private final String description;

        public RebateRule(String title, String description) {
            this.title = title;
            this.description = description;
        }

        // Getters
        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }
    }
}
