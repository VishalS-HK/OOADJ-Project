package com.jtspringproject.JtSpringProject.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jtspringproject.JtSpringProject.models.Product;
import com.jtspringproject.JtSpringProject.models.User;
import com.jtspringproject.JtSpringProject.services.productService;
import com.jtspringproject.JtSpringProject.services.userService;



@Controller
public class UserController{

	@Autowired
	private userService userService;

	@Autowired
	private productService productService;

	@GetMapping("/register")
	public String registerUser()
	{
		return "register";
	}

	@GetMapping("/buy")
	public String buy()
	{
		return "buy";
	}


	@GetMapping("/")
	public String userlogin(Model model) {

		return "userLogin";
	}
	@RequestMapping(value = "userloginvalidate", method = RequestMethod.POST)
	public ModelAndView userlogin( @RequestParam("username") String username, @RequestParam("password") String pass,Model model,HttpServletResponse res) {

		System.out.println(pass);
		User u = this.userService.checkLogin(username, pass);
		System.out.println(u.getUsername());

		if(username.equals(u.getUsername())) {

			res.addCookie(new Cookie("username", u.getUsername()));
			ModelAndView mView  = new ModelAndView("index");
			mView.addObject("user", u);
			List<Product> products = this.productService.getProducts();

			if (products.isEmpty()) {
				mView.addObject("msg", "No products are available");
			} else {
				mView.addObject("products", products);
			}
			return mView;

		}else {
			ModelAndView mView = new ModelAndView("userLogin");
			mView.addObject("msg", "Please enter correct email and password");
			return mView;
		}

	}


	@GetMapping("/user/products")
	public ModelAndView getproduct() {

		ModelAndView mView = new ModelAndView("uproduct");

		List<Product> products = this.productService.getProducts();

		if(products.isEmpty()) {
			mView.addObject("msg","No products are available");
		}else {
			mView.addObject("products",products);
		}

		return mView;
	}
	@RequestMapping(value = "newuserregister", method = RequestMethod.POST)
	public ModelAndView newUseRegister(@ModelAttribute User user)
	{
		// Check if username already exists in database
		boolean exists = this.userService.checkUserExists(user.getUsername());

		if(!exists) {
			System.out.println(user.getEmail());
			user.setRole("ROLE_NORMAL");
			this.userService.addUser(user);

			System.out.println("New user created: " + user.getUsername());
			ModelAndView mView = new ModelAndView("userLogin");
			return mView;
		} else {
			System.out.println("New user not created - username taken: " + user.getUsername());
			ModelAndView mView = new ModelAndView("register");
			mView.addObject("msg", user.getUsername() + " is taken. Please choose a different username.");
			return mView;
		}
	}



	//for Learning purpose of model
	@GetMapping("/test")
	public String Test(Model model)
	{
		System.out.println("test page");
		model.addAttribute("author","jay gajera");
		model.addAttribute("id",40);

		List<String> friends = new ArrayList<String>();
		model.addAttribute("f",friends);
		friends.add("xyz");
		friends.add("abc");

		return "test";
	}

	// for learning purpose of model and view ( how data is pass to view)

	@GetMapping("/test2")
	public ModelAndView Test2()
	{
		System.out.println("test page");
		//create modelandview object
		ModelAndView mv=new ModelAndView();
		mv.addObject("name","jay gajera 17");
		mv.addObject("id",40);
		mv.setViewName("test2");

		List<Integer> list=new ArrayList<Integer>();
		list.add(10);
		list.add(25);
		mv.addObject("marks",list);
		return mv;


	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		// Perform logout logic here
		request.getSession().invalidate();
		return "redirect:/"; // Redirect to login page after logout
	}




//	@GetMapping("carts")
//	public ModelAndView  getCartDetail()
//	{
//		ModelAndView mv= new ModelAndView();
//		List<Cart>carts = cartService.getCarts();
//	}
public class NewUserController {

    @Autowired
    private userService userService;

    @Autowired
    private productService productService;

    private User userPrototype = new User();

    public NewUserController() {
        userPrototype.setRole("ROLE_NORMAL");  // Default role for new users
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

  //  @PostMapping("/register")
    public ModelAndView registerUser(@ModelAttribute User userForm) {
        User newUser = userPrototype.clone();  // Cloning the prototype
        newUser.setUsername(userForm.getUsername());
        newUser.setPassword(userForm.getPassword());
        newUser.setEmail(userForm.getEmail());

        boolean exists = this.userService.checkUserExists(newUser.getUsername());
        if (!exists) {
            this.userService.addUser(newUser);
            ModelAndView modelAndView = new ModelAndView("userLogin");
            modelAndView.addObject("msg", "Registration successful");
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("register");
            modelAndView.addObject("msg", "Username already exists");
            return modelAndView;
        }
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "userLogin";
    }

   // @PostMapping("/login")
    public ModelAndView loginUser(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse response) {
        User user = this.userService.checkLogin(username, password);
        if (user != null && username.equals(user.getUsername())) {
            response.addCookie(new Cookie("username", user.getUsername()));
            ModelAndView modelAndView = new ModelAndView("index");
            modelAndView.addObject("user", user);
            List<Product> products = productService.getProducts();
            if (products.isEmpty()) {
                modelAndView.addObject("msg", "No products are available");
            } else {
                modelAndView.addObject("products", products);
            }
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("userLogin");
            modelAndView.addObject("msg", "Invalid credentials");
            return modelAndView;
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }

    @GetMapping("/user/products")
    public ModelAndView getproduct() {
        ModelAndView mView = new ModelAndView("uproduct");
       // List<Product> products

}
