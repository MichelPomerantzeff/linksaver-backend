package com.michelpomerantzeff.linksaverbackend.controller;

import com.michelpomerantzeff.linksaverbackend.exception.UserNotFoundException;
import com.michelpomerantzeff.linksaverbackend.model.Product;
import com.michelpomerantzeff.linksaverbackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/product")
    Product newProduct (@RequestBody Product newProduct){
        return productRepository.save(newProduct);
    }

    @GetMapping("/products")
    List<Product> getAllProducts (){
        return productRepository.findAll();
    }

    @GetMapping("/product/{id}")
    Product getProductById (@PathVariable Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/product/{id}")
    Product updateProduct (@RequestBody Product newProduct, @PathVariable Long id){
        return productRepository.findById(id)
                .map(product -> {
                    product.setProductName(newProduct.getProductName());
                    product.setShop(newProduct.getShop());
                    product.setPrice(newProduct.getPrice());
                    product.setLink(newProduct.getLink());
                    return productRepository.save(product);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/product/{id}")
    String deleteProduct (@PathVariable Long id){

        if(!productRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }

        productRepository.deleteById(id);

        return "User with id " + id + "has been deleted successfully. ";
    }


}
