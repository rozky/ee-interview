package com.roozky.model

import scala.math.BigDecimal.RoundingMode

case class ShoppingCart(items: Map[Product, ShoppingCartItem]) {

  def addProduct(quantity: Int, product: Product): ShoppingCart = {
    val item: ShoppingCartItem = items.get(product)
      .map(sci => ShoppingCartItem.increment(sci, quantity))
      .getOrElse(ShoppingCartItem(product, quantity))

    ShoppingCart(items.updated(product, item))
  }

  def getTotalPrice(): BigDecimal = {
    val sum: BigDecimal = items.values
      .map(i => i.quantity * i.product.price)
      .sum

    sum.setScale(2, RoundingMode.HALF_UP)
  }

  def getTotalPriceWithTax(tax: BigDecimal): BigDecimal = {
    getTotalPrice() + getTax(tax)
  }

  def getTax(tax: BigDecimal): BigDecimal = {
    getTotalPrice().*(tax).setScale(2, RoundingMode.HALF_UP)
  }
}
