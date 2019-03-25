package com.roozky

import com.roozky.model.{Product, ShoppingCart, ShoppingCartItem}
import org.scalatest.{FlatSpec, Matchers}

class ShoppingCartSpec extends FlatSpec with Matchers {
  val doveSoap = Product("Dove Soap", BigDecimal(39.99D))
  val axeDeo = Product("Axe Deo", BigDecimal(99.99D))
  val emptyCart = ShoppingCart(Map.empty)
  val tax = BigDecimal(0.125D)

  it should "possible to add a product to an empty shopping card" in {
    // when
    val cartWithProduct = emptyCart.addProduct(1, doveSoap)

    // then
    cartWithProduct should be(ShoppingCart(Map(doveSoap -> ShoppingCartItem(doveSoap, 1))))
  }

  it should "possible to add multiple products to an empty shopping card" in {
    // when
    val newCart = emptyCart.addProduct(5, doveSoap)

    // then
    newCart should be(ShoppingCart(Map(doveSoap -> ShoppingCartItem(doveSoap, 5))))
    newCart.getTotalPrice() should be(BigDecimal(199.95D))
  }

  it should "possible to add additional products of same type" in {
    // when
    val newCart = emptyCart
      .addProduct(5, doveSoap)
      .addProduct(3, doveSoap)

    // then
    newCart should be(ShoppingCart(Map(doveSoap -> ShoppingCartItem(doveSoap, 8))))

    // and
    newCart.getTotalPrice() should be(BigDecimal(319.92D))
  }

  it should "calculate tax rate of single item cart" in {
    // when
    val newCart = emptyCart
      .addProduct(1, doveSoap)

    // then
    newCart.getTax(tax) should be(BigDecimal(5D))
    newCart.getTotalPriceWithTax(tax) should be(BigDecimal(44.99D))
  }

  it should "calculate tax rate of multiple items" in {
    // when
    val newCart = emptyCart
      .addProduct(2, doveSoap)
      .addProduct(2, axeDeo)

    // then
    newCart.getTax(tax) should be(BigDecimal(35D))
    newCart.getTotalPriceWithTax(tax) should be(BigDecimal(314.96D))
  }
}
