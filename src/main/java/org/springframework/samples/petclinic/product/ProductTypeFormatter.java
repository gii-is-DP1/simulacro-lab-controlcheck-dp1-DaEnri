package org.springframework.samples.petclinic.product;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class ProductTypeFormatter implements Formatter<ProductType>{

	private final ProductService pService;
	
	@Autowired
	public ProductTypeFormatter(ProductService pService) {
		this.pService = pService;
	}
	
    @Override
    public String print(ProductType object, Locale locale) {
        return object.getName();
    }

    @Override
    public ProductType parse(String text, Locale locale) throws ParseException {
    	if (this.pService.getProductType(text) == null) {
    		throw new ParseException("type not found: " + text, 0);
		}else {
			return this.pService.getProductType(text);
		}
    }
    
}
