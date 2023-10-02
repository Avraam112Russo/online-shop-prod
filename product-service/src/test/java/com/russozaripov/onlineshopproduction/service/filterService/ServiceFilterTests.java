package com.russozaripov.onlineshopproduction.service.filterService;

import com.russozaripov.onlineshopproduction.entity.Brand;
import com.russozaripov.onlineshopproduction.entity.Product;
import com.russozaripov.onlineshopproduction.entity.Type;
import com.russozaripov.onlineshopproduction.repository.brandRepository.BrandRepository;
import com.russozaripov.onlineshopproduction.repository.typeRepository.TypeRepository;
import com.russozaripov.onlineshopproduction.service.productService.filterService.ServiceFilter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ServiceFilterTests {
    @Mock
    private TypeRepository typeRepository;
    @Mock
    private BrandRepository brandRepository;
    @InjectMocks
    private ServiceFilter serviceFilter;

    @DisplayName("Junit test for create JPA specification operation.")
    @Test
    public void givenArgumentsForCreateSpecification_whenFilterProductsWithSpecification_thenReturnSpecificationObject(){
        // given
//        Type type = new Type(1, "MobilePhone");
//        Brand brand = new Brand(1, "Apple");
//        BDDMockito.given(typeRepository.findTypeByName(ArgumentMatchers.anyString())).willReturn(Optional.of(type));
//        BDDMockito.given(brandRepository.findBrandByName(ArgumentMatchers.anyString())).willReturn(Optional.of(brand));

        // TODO
        //when
        Specification<Product> specification = serviceFilter.filterProductsWithSpecification(
                "any-type", "any-brand", 10, 1000, true
        );
        //then;
        Assertions.assertThat(specification).isNotNull();
//        Assertions.assertThat(specification).isInstanceOf(Specification.class);

    }
}
