package org.example.repository;

import org.example.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProductRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Transactional
  public void saveProduct(Product product) {

    String sql = "INSERT INTO PRODUCT VALUES (?,?)";
    Object[] args = {product.getId(), product.getName()};
    jdbcTemplate.update(sql, args);
  }

  @Transactional
  public void deleteAll() {

    String sql = "delete FROM product";
    jdbcTemplate.update(sql);
  }
}
