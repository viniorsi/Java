package com.vivo.territory.Service.UserProduct;

import com.vivo.territory.Domain.Products.Entity.Product;
import com.vivo.territory.Domain.Products.Repository.ProductRepository;
import com.vivo.territory.Domain.User.Entity.User;
import com.vivo.territory.Domain.User.Repository.UserRespository;
import com.vivo.territory.Domain.UserProduct.DTO.DTOUserProduct;
import com.vivo.territory.Domain.UserProduct.Entity.UserProduct;
import com.vivo.territory.Domain.UserProduct.Repository.UserProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProductService {

    @Autowired
    private UserProductRepository userProductRepository;

    @Autowired
    private UserRespository userRespository;

    @Autowired
    private ProductRepository productRepository;



    public String addProductToUser(@Valid DTOUserProduct userProduct) throws Exception {

        var userHave = userProductRepository.findUserProduct(userProduct.product_id(),userProduct.user_id());

        if (userHave == null) {

            User user = userRespository.getReferenceById(userProduct.user_id());
            Product product = productRepository.getReferenceById(userProduct.product_id());

            UserProduct up = new UserProduct(user,product);

            userProductRepository.save(up);


            return "Usuario recebeu produto com sucesso";
        } else {
            throw new Exception("Usuario ja possui produto");
        }
    }


}
