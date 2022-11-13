/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hagh.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hagh.pojo.Bus;
import com.hagh.pojo.BusOwner;
import com.hagh.pojo.BusTicket;
import com.hagh.pojo.Rating;
import com.hagh.pojo.Users;
import com.hagh.service.BusService;
import com.hagh.service.OrthersService;
import com.hagh.service.OwnerService;
import com.hagh.service.RoleService;
import com.hagh.service.StationService;
import com.hagh.service.TicketService;
import com.hagh.service.UserService;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//momo import
import com.mservice.enums.*;
import com.mservice.models.*;
import com.mservice.processor.*;
import com.mservice.config.Environment;
import com.mservice.shared.utils.LogUtils;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author 84344
 */
@Controller
@PropertySource("classpath:messages.properties")
public class HomeController {

//    private Logger log = LogManager.getLogger(HomeController.class);
    @Autowired
    TicketService ticketService;

    @Autowired
    OwnerService ownerService;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private RoleService roleService;

    @Autowired
    private StationService stationService;

    @Autowired
    private BusService busService;

    @Autowired
    private OrthersService orthersService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;
//
//    @Autowired//bug here
//    private RegisterUsersValidator userValidator;
//
//    @InitBinder("user")
//    public void initBinder(WebDataBinder binder) {
//        binder.setValidator(userValidator);
//        
//    }

    @RequestMapping("/home")
    public String index(Model model) throws Exception {
//        log.info("This is an info log entry");

        model.addAttribute("station", this.stationService.getStation());
        model.addAttribute("line", this.stationService.getBusLine());
        model.addAttribute("bus", busService.getBus());
        model.addAttribute("orthers", orthersService.getNews("admin"));
        model.addAttribute("own", this.ownerService.getBusOwner());

        return "index";
    }

    @GetMapping("/find")
    public String statsView(Model model,
            @RequestParam(value = "fromPos", defaultValue = "0") int fromPos,
            @RequestParam(value = "toPos", defaultValue = "0") int toPos,
            @RequestParam(value = "price", defaultValue = "100000") int price) {

        model.addAttribute("station", stationService.getStation());
        model.addAttribute("bus", busService.findBus(fromPos, toPos, price));

        return "listBus";
    }

    @RequestMapping("/news")
    public String NewsPage(Model model) {
        model.addAttribute("orthers", orthersService.getNews(""));
        return "News";
    }

    @RequestMapping("/news/info/{id}")
    public String NewsInFo(Model model, @PathVariable("id") int id) {
        model.addAttribute("news", this.orthersService.getNewsById(id));
        return "newsDetailsMain";
    }
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
// 
//    station zone

    @RequestMapping("/station")
    public String AllBusStationInFo(Model model) {
        model.addAttribute("station", this.stationService.getStation());
        return "allStation";
    }

    @RequestMapping("/busstation/{id}")
    public String BusStationInFo(Model model, @PathVariable("id") int id) {
        model.addAttribute("t", this.stationService.getStationById(id));
        model.addAttribute("line", stationService.getOwnerByStation(id));
        return "BusStation";
    }

//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
// 
//    own home zone
    @RequestMapping("/busown/")
    public String ViewBusOwn(Model model) {
        model.addAttribute("own", this.ownerService.getBusOwner());
        return "viewBusOwn";
    }

    @RequestMapping("/busown/{id}")
    public String ViewBusOwnInfo(Model model, @PathVariable("id") int id) {
        BusOwner own = this.ownerService.getBusOwnerById(id);

        model.addAttribute("ratingPost", new Rating());
        model.addAttribute("owninfo", own);
        model.addAttribute("rated", ownerService.getRateByOwnId(id));
        model.addAttribute("line", ownerService.getListBusLineByOwner(own.getId()));
        return "ownDetails";
    }

    @RequestMapping("/busown/{id}/comment")
    public String postCMT(Model model, @PathVariable("id") int id,
            @Valid @ModelAttribute("ratingPost") Rating rate,
            RedirectAttributes reAtt,
            BindingResult rs,
            HttpServletRequest request
    ) {
        String errMsg = null;
        if (rs.hasErrors()) {
            errMsg = "Tài khoản đã đánh giá bài viết hoặc không phải là tài khoản khách hàng.";
            BusOwner own = this.ownerService.getBusOwnerById(id);
            model.addAttribute("rating", new Rating());
            model.addAttribute("owninfo", own);
            model.addAttribute("rated", ownerService.getRateByOwnId(id));
            model.addAttribute("line", ownerService.getListBusLineByOwner(own.getId()));
            reAtt.addFlashAttribute("errMsg", errMsg);
            return "redirect:/busown/{id}";
        }

        if (rate.getRate() == null) {
            errMsg = "Chưa nhập đánh giá, vui lòng thử lại";
            BusOwner own = this.ownerService.getBusOwnerById(id);
            model.addAttribute("rating", new Rating());
            model.addAttribute("owninfo", own);
            model.addAttribute("rated", ownerService.getRateByOwnId(id));
            model.addAttribute("line", ownerService.getListBusLineByOwner(own.getId()));
            reAtt.addFlashAttribute("errMsg", errMsg);
            return "redirect:/busown/{id}";
        }
        try {
            if (ownerService.addComment(rate) == true) {
                String okMsg = "Đánh giá thành công.";
                reAtt.addFlashAttribute("okMsg", okMsg);
                return "redirect:/busown/{id}";
            } else {
                errMsg = "Da co loi xay ra, vui long thu lai.";
                BusOwner own = this.ownerService.getBusOwnerById(id);
                model.addAttribute("rating", new Rating());
                model.addAttribute("owninfo", own);
                model.addAttribute("rated", ownerService.getRateByOwnId(id));
                model.addAttribute("line", ownerService.getListBusLineByOwner(own.getId()));
                reAtt.addFlashAttribute("errMsg", errMsg);
                return "redirect:/busown/{id}";
            }

        } catch (Exception ex) {
            errMsg = "Tài khoản đã đánh giá bài viết.";
            BusOwner own = this.ownerService.getBusOwnerById(id);
            model.addAttribute("rating", new Rating());
            model.addAttribute("owninfo", own);
            model.addAttribute("rated", ownerService.getRateByOwnId(id));
            model.addAttribute("line", ownerService.getListBusLineByOwner(own.getId()));
            reAtt.addFlashAttribute("errMsg", errMsg);
            return "redirect:/busown/{id}";
        }

    }

    @RequestMapping("/busown/{id}/line/{lId}")
    @PreAuthorize("@authenticatedUserService.hasBusLineId(#id,#lId)")
    public String ViewLineInfo(Model model,
            @PathVariable("id") int id,
            @PathVariable("lId") int lId
    ) {
        BusOwner own = this.ownerService.getBusOwnerById(id);
        model.addAttribute("owninfo", own);
        model.addAttribute("bus", ownerService.getBusByLine(lId));
        return "lineInfo";
    }

//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    bus zone 
    @RequestMapping("/bus/{id}")
    public String BusInFo(Model model, @PathVariable("id") int id) {
        int emptySit = busService.getEmptySitByBus(id);
        model.addAttribute("bus", this.busService.getBusById(id));
        model.addAttribute("offerBus", busService.offerTrip(id));
        model.addAttribute("emptySit", emptySit);
        return "BusInfo";
    }

    @RequestMapping("/list_bus")
    public String BusList(Model model, @PathVariable("id") int id) {
        int emptySit = busService.getEmptySitByBus(id);
        model.addAttribute("bus", this.busService.getBusById(id));
        model.addAttribute("emptySit", emptySit);
        return "listBus";
    }
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
// 
    /////////////////////////////////////////////

    @GetMapping("/bus/{id}/book")
    public String bookTicket(Model model, @PathVariable("id") int id, RedirectAttributes reAtt) throws Exception {

        Bus bus = busService.getBusById(id);
        if (ownerService.checkBlockOrUnActive(bus.getBusLineId().getBusOwnerId().getId()) == false) {
            return "redirect:/locked";
        }

        String errMsg = null;
        Date now = new Date();
        Bus busDate = busService.getBusById(id);
        int getDate = busDate.getStartAt().compareTo(now);
        if (getDate < 0) {
            errMsg = "Xe da khoi hanh, xin chon xe khac";
            reAtt.addFlashAttribute("errMsg", errMsg);
            return "redirect:/bus/{id}";
        }
        int emptySit = busService.getEmptySitByBus(id);
        if (emptySit == 0) {
            errMsg = "Xe khong con cho trong, xin chon xe khac";
            reAtt.addFlashAttribute("errMsg", errMsg);
            return "redirect:/bus/{id}";
        }
        model.addAttribute("bus", busService.getBusById(id));

        model.addAttribute("ticketBook", new BusTicket());

        return "bookTicketMain";
    }

    @PostMapping("/bus/{id}/book") //add new ticket
    public RedirectView bookTicketProcess(Model model,
            @PathVariable("id") int id,
            @Valid @ModelAttribute("ticketBook") BusTicket ticket,
            RedirectAttributes reAtt,
            BindingResult rs,
            HttpServletRequest request) {
        String errMsg = null;
        Bus bus = busService.getBusById(id);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8080/bus/{id}/book");
        if (rs.hasErrors()) {
            errMsg = "Da co loi xay ra., vui long thu lai.";
            model.addAttribute("errMsg", errMsg);
            model.addAttribute("bus", busService.getBusById(id));
            return redirectView;
        }
        int emptySit = busService.getEmptySitByBus(id);
        if (ticket.getNumberOfSit() > emptySit) {
            errMsg = "So luong ghe trong khong du.";
            model.addAttribute("errMsg", errMsg);
            model.addAttribute("bus", busService.getBusById(id));
            return redirectView;
        }
        String okMsg = null;
        
        try {
            ticket.setIsPurchased(ticket.getIsPurchased());
            if (ticket.getIsPurchased() == -1) {
                //momo
                LogUtils.init();
                String requestId = String.valueOf(System.currentTimeMillis());
                String orderId = String.valueOf(System.currentTimeMillis());
                long amount = ticket.getNumberOfSit()*bus.getBusLineId().getTicketPrice();

                String orderInfo = "Pay With MoMo";
                String returnURL = "https://google.com.vn";
                String notifyURL = "https://google.com.vn";

                Environment environment = Environment.selectEnv("dev");

                PaymentResponse captureWalletMoMoResponse = CreateOrderMoMo.process(environment,
                        orderId, requestId, Long.toString(amount), orderInfo, returnURL, notifyURL, "",
                        RequestType.CAPTURE_WALLET, Boolean.TRUE);

                model.addAttribute("momoUrl", captureWalletMoMoResponse.getPayUrl());
                redirectView.setUrl(captureWalletMoMoResponse.getPayUrl());
                return redirectView;
            }
            if (this.ticketService.bookTicket(ticket) == true) {
                okMsg = "DAT VE THANH CONG";
                redirectView.setUrl("http://localhost:8080/bus/{id}/");
                reAtt.addFlashAttribute("okMsg", okMsg);
                return redirectView; //redirect to lich su dat ve.
            }
            errMsg = "DA CO LOI XAY RA";
            model.addAttribute("errMsg", errMsg);
            model.addAttribute("bus", busService.getBusById(id));
            return redirectView;
        } catch (Exception e) {
            errMsg = "DA CO LOI XAY RA, cacth";
            model.addAttribute("errMsg", errMsg);
            model.addAttribute("bus", busService.getBusById(id));
            return redirectView;
        }

    }
   

    @RequestMapping("/{uId}/booked")
    @PreAuthorize("@authenticatedUserService.hasId(#uId)")
    public String bookHistory(Model model, @PathVariable("uId") int uId) {
        model.addAttribute("allTicket", ticketService.getAllTicketByOwn(uId));
        return "bookHistory";
    }
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
// 

    @RequestMapping("/login")
    public String login() {
        return "Login";
    }

    @RequestMapping("/accessDenied")
    public String AccessDenied() {
        return "accessDenied";
    }

    @RequestMapping("/{uId}/me")
    @PreAuthorize("@authenticatedUserService.hasId(#uId)")
    public String myAccount(Model model, @PathVariable("uId") int uId) {
        model.addAttribute("me", userService.getUserById(uId));
        return "myAccount";
    }
    @GetMapping("/register")
    public String registerCus(Model model) {
        model.addAttribute("user", new Users());
        model.addAttribute("customer", roleService.getRolesById(3));
        model.addAttribute("owner", roleService.getRolesById(2));
        return "register";
    }

    @PostMapping("/register")
    public String registerCusProcess(Model model,
            @ModelAttribute(value = "user") @Valid Users u,
            BindingResult result) {

        try {
            if (userService.getUserByUsername(u.getUsername().trim()) != null) {
                result.addError(new FieldError("u", "username", "Username đã tồn tại"));
                model.addAttribute("customer", roleService.getRolesById(3));
                model.addAttribute("owner", roleService.getRolesById(2));
                return "register";
            }
        } catch (Exception ex) {

        }
        try {
            if (userService.getUserByEmail(u.getEmail().trim()) != null) {
                result.addError(new FieldError("u", "email", "Email đã tồn tại"));
                model.addAttribute("customer", roleService.getRolesById(3));
                model.addAttribute("owner", roleService.getRolesById(2));
                return "register";
            }
        } catch (Exception ex) {

        }
        if (!u.getPassword().trim().equals(u.getConfirmPassword().trim())) {
            result.addError(new FieldError("u", "password", "Password không trùng khớp"));
            model.addAttribute("customer", roleService.getRolesById(3));
            model.addAttribute("owner", roleService.getRolesById(2));
            return "register";
        }

        if (result.hasErrors()) {
            result.addError(new FieldError("u", "username", "Đã có lỗi xảy ra, vui lòng thử lại.(rs)"));
            model.addAttribute("customer", roleService.getRolesById(3));
            model.addAttribute("owner", roleService.getRolesById(2));
            return "register";
        }
        if (u.getAvatarFile() != null && !u.getAvatarFile().isEmpty()) {
            try {

                Map results = cloudinary.uploader().upload(u.getAvatarFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                u.setAvatar(results.get("secure_url").toString());
                u.setPassword(passwordEncoder.encode(u.getPassword()));
                if (this.userService.addUser(u) == true) {
                    return "redirect:/login";
                } else {
                    result.addError(new FieldError("u", "username", "Đã có lỗi xảy ra, vui lòng thử lại."));
                    model.addAttribute("customer", roleService.getRolesById(3));
                    model.addAttribute("owner", roleService.getRolesById(2));
                    return "register";
                }

            } catch (IOException ex) {
                result.addError(new FieldError("u", "username", "Đã có lỗi xảy ra, vui lòng thử lại.)"));
                model.addAttribute("customer", roleService.getRolesById(3));
                model.addAttribute("owner", roleService.getRolesById(2));
                return "register";
            }
        } else {
            try {
                u.setAvatar("https://res.cloudinary.com/hoangtam/image/upload/v1661743896/fvxksvvwif0fgmpmwclv.jpg");
                u.setPassword(passwordEncoder.encode(u.getPassword()));
                if (this.userService.addUser(u) == true) {
                    return "redirect:/login";
                } else {
                    result.addError(new FieldError("u", "username", "Đã có lỗi xảy ra, vui lòng thử lại.(else2)"));
                    model.addAttribute("customer", roleService.getRolesById(3));
                    model.addAttribute("owner", roleService.getRolesById(2));
                    return "register";
                }
            } catch (Exception ex) {
                result.addError(new FieldError("u", "username", "Đã có lỗi xảy ra, vui lòng thử lại.(catch2)"));
                model.addAttribute("customer", roleService.getRolesById(3));
                model.addAttribute("owner", roleService.getRolesById(2));
                return "register";
            }
        }

    }
}
