package com.thwh.buls_own_shop.repository;

import com.thwh.buls_own_shop.domain.Member;
import com.thwh.buls_own_shop.domain.product.Bat;
import com.thwh.buls_own_shop.domain.product.Glove;
import com.thwh.buls_own_shop.domain.product.Product;
import com.thwh.buls_own_shop.domain.product.Spike;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final EntityManager em;

    public void save(Product product) {
        if (product.getId() == null) {
            em.persist(product);
        } else {
            em.merge(product);
        }
    }

    public Product findOne(Long id) {
        return em.find(Product.class, id);
    }

    public List<Product> findAll() {
        return em.createQuery("select p from Product p", Product.class)
                .getResultList();
    }

    public List<Glove> findGloves() {
        List<Product> products = findAll();
        Iterator<Product> it = products.iterator();
        List<Glove> gloves = new ArrayList<>();
        while (it.hasNext()) {
            Product next = it.next();
            if(next.getClass() == Glove.class)
                gloves.add((Glove) next);
        }
        return gloves;
    }

    public List<Bat> findBats() {
        List<Product> products = findAll();
        Iterator<Product> it = products.iterator();
        List<Bat> bats = new ArrayList<>();
        while (it.hasNext()) {
            Product next = it.next();
            if(next.getClass() == Bat.class)
                bats.add((Bat) next);
        }
        return bats;
    }

    public List<Spike> findSpikes() {
        List<Product> products = findAll();
        Iterator<Product> it = products.iterator();
        List<Spike> spikes = new ArrayList<>();
        while (it.hasNext()) {
            Product next = it.next();
            if(next.getClass() == Spike.class)
                spikes.add((Spike) next);
        }
        return spikes;
    }

    public List<Product> findByName(String name) {
        return em.createQuery("select p from Product p where p.name = :name", Product.class)
                .setParameter("name", name)
                .getResultList();
    }
}
