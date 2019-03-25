package com.roozky.model

final case class ShoppingCartItem(product: Product, quantity: Int) {

}

object ShoppingCartItem {
  // todo - replace Int with NonNegatioveInt
  def increment(sci: ShoppingCartItem, quantity: Int): ShoppingCartItem = {
    ShoppingCartItem(sci.product, sci.quantity + quantity)
  }
}
