package org.kakao.kakaoshopping.web.controller.cart;

import java.util.List;

import org.kakao.kakaoshopping.domain.entity.cart.Cart;
import org.kakao.kakaoshopping.domain.service.cart.CartService;
import org.kakao.kakaoshopping.web.annotaion.LoginMember;
import org.kakao.kakaoshopping.web.dto.cart.request.CartToOrder;
import org.kakao.kakaoshopping.web.dto.cart.request.CreateCart;
import org.kakao.kakaoshopping.web.dto.cart.request.EditCart;
import org.kakao.kakaoshopping.web.dto.cart.response.CartSimpleView;
import org.kakao.kakaoshopping.web.dto.user.login.LoggedInUser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class CartController {

  private final CartService cartService;

  /**
   * 기능: 장바구니에 아이템을 담는다
   * 작성자 - 장규민
   * 작성일 - 2023.07.25
   *
   * @param createCart
   * @param id
   * @param loggedInUser
   * @return String
   */
  @PostMapping("/createCart")
  @ResponseBody
  public ResponseEntity<Void> createCart(@RequestBody CreateCart createCart, @LoginMember LoggedInUser loggedInUser) {
      cartService.addCart(createCart.toEntity(), createCart.getItemId(), loggedInUser.getUserId());
      return ResponseEntity.ok().build();
  }

  /**
   * 기능: 장바구니를 조회한다.
   * 작성자 - 장규민
   * 작성일 - 2023.07.25
   *
   * @param model
   * @param loggedInUser
   * @return String
   */
  @GetMapping("/carts")
  public String viewCarts(Model model, @LoginMember LoggedInUser loggedInUser) {
	  List<Cart> carts = cartService.getItemsInCart(loggedInUser.getUserId());
	  List<CartSimpleView> cartSimpleView = carts.stream().map(CartSimpleView::new)
		  .toList();
	  model.addAttribute("carts", cartSimpleView);
	  return "cart/cartViews";
  }


  /**
   * 기능: 장바구니 안 아이템을 삭제한다.
   * 작성자 - 박가연
   * 작성일 - 2023.07.25
   *
   * @param cart
   * @return String
   */
  @PostMapping("/deleteItemInCart")
  @ResponseBody
  public ResponseEntity<Void> deleteItemInCart(@RequestBody EditCart cart) {
      cartService.deleteItemInCart(cart.toEntity());
      return ResponseEntity.ok().build();
  }

  /**
   * 기능: 장바구니 자체를 삭제한다.
   * 작성자 - 박가연
   * 작성일 - 2023.07.25
   *
   * @param loggedInUser
   * @return String
   */
  @PostMapping("/deleteCart")
  public String deleteCart(@LoginMember LoggedInUser loggedInUser) {
      cartService.deleteCart(loggedInUser.getUserId());
      return "redirect:/user/carts";
  }

	// 장바구니 수량 업데이트
	@PostMapping("/updateQuantityCart")
	@ResponseBody
	public ResponseEntity<Integer> updateQuantityCart(@RequestBody EditCart editCart) {
		Integer savedQuantity = cartService.updateCart(editCart.toEntity());
		return ResponseEntity.ok(savedQuantity);
	}

	@GetMapping("/orderForm")
	public String test(List<CartToOrder> carts) {
		// DB 가서 아이템 조회 후, 주문에 필요한 정보를 dto에 담아서\
		// 뷰로 전달해 주면 된다.
		return "cart/cartViews";
	}
}
