package org.springframework.samples.petclinic.product;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	private final ProductService productService;
    
	@Autowired
	public ProductController(ProductService pService) {
		this.productService = pService;
	}
	
//	@GetMapping()
//	public String listProducts(Product product, BindingResult result, Map<String, Object> model) {
//
//		// find owners by last name
//		Collection<Product> results = this.productService.getAllProducts();
//		if (results.isEmpty()) {
//			model.put("message", "No products found");
//			return "redirect:/product";
//		}
//		else {
//			model.put("selections", results);
//			return "redirect:/product";
//		}
//	}
	
	@GetMapping(value = "/create")
	public String initCreationForm(ModelMap model) {
		Product p = new Product();
		List<ProductType> pts =  productService.findAllProductTypes();
		model.addAttribute("product", p);
		model.addAttribute("productType", pts);
		return "products/createOrUpdateProductForm";
	}
	
	@PostMapping(value = "/create")
	public String saveNewProduct(@Valid Product product, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			model.put("message", "Error, try again");
			List<ProductType> pts =  productService.findAllProductTypes();
			model.addAttribute("product", product);
			model.addAttribute("productType", pts);
			return "products/createOrUpdateProductForm";
		}else {
			this.productService.save(product);
		}
		return "welcome";
	}
	
}
