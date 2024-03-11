package com.springboot.springbootref.shops;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopsService {
    @Autowired
    private ShopsRepository shopsRepository;

    public Shops save(Shops shop) {
        return shopsRepository.save(shop);
    }

    public Shops findById(Long id) {
        return shopsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shop not found"));
    }
    public Shops edit(Long id, Shops shops){
        Shops shop = findById(id);
        shop.setName(shops.getName());
        shop.setAddress(shops.getAddress());
        shop.setCertified(shops.isCertified());
        return shopsRepository.save(shop);
    }
    public List<Shops> findAll() {
        return shopsRepository.findAll();
    }

    public void deleteById(Long id) {
        shopsRepository.deleteById(id);
    }
}
