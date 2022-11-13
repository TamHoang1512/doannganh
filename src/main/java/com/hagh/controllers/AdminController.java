/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hagh.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hagh.pojo.BusOwner;
import com.hagh.pojo.BusStation;
import com.hagh.pojo.StationNews;
import com.hagh.pojo.Users;
import com.hagh.service.BusService;
import com.hagh.service.OrthersService;
import com.hagh.service.RoleService;
import com.hagh.service.StationService;
import com.hagh.service.UserService;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

/**
 *
 * @author 84344
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private Cloudinary cloudinary;
//    private Logger log = LogManager.getLogger(AdminController.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrthersService orthersService;

    @Autowired
    private StationService station;

    @Autowired
    private BusService busService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @ModelAttribute
    public void commonAttr(Model model) {
        model.addAttribute("nofi", this.busService.getBusOwnersActive(0));
    }

    //for admin
    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("orthers", orthersService.getNews("admin"));
        return "adminPage";
    }

    //for news
    @GetMapping("/news")
    public String NewsPage(Model model) {
        model.addAttribute("orthers", orthersService.getNews(""));
        return "newsManagerAdmin";
    }

    @GetMapping("/news/block")
    public String BlockNewsPage(Model model) {
        model.addAttribute("block", orthersService.getBlockNews(""));
        return "newsBlockAdmin";
    }

    @RequestMapping("/news/block/{id}")
    public String blockNews(@PathVariable("id") int id) {
        orthersService.blockNews(id);
        return "redirect:/admin/news/";
    }

    @RequestMapping("/news/delete/{id}")
    public String delNews(@PathVariable("id") int id) {
        orthersService.delNews(id);
        return "redirect:/admin/news/";
    }

    @GetMapping("/news/info/{id}")
    public String NewsInFo(Model model, @PathVariable("id") int id) {
        model.addAttribute("news", this.orthersService.getNewsById(id));
        return "newsDetailsAdmin";
    }

    @GetMapping("/news/add")
    public String addNewsView(Model model) {
        model.addAttribute("tintuc", new StationNews());
        model.addAttribute("allUsers", this.userService.getUser());
        return "newsAddAdmin";
    }

    @PostMapping("/news/add")
    public String addNewsProcess(Model model,
            @Valid @ModelAttribute(value = "tintuc") StationNews tintuc,
            BindingResult rsN,
            HttpServletRequest request) {
        if (rsN.hasErrors()) {
//            rsN.addError(new FieldError("tintuc", "title", "Đã có lỗi xảy ra, vui lòng thử lại"));
            model.addAttribute("allUsers", this.userService.getUser());
            return "newsAddAdmin";
        }
        if (!tintuc.getImgFile().isEmpty() && tintuc.getImgFile() != null) {

            try {
                Map results = cloudinary.uploader().upload(tintuc.getImgFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                tintuc.setImg(results.get("secure_url").toString());
                if (this.orthersService.addNews(tintuc) == true) {
                    return "redirect:/admin/news";
                } else {
                    rsN.addError(new FieldError("tintuc", "title", "Đã có lỗi xảy ra, vui lòng thử lại"));
                    model.addAttribute("allUsers", userService.getUser());
                    return "newsAddAdmin";
                }
            } catch (IOException ex) {
                rsN.addError(new FieldError("tintuc", "title", "Đã có lỗi xảy ra, vui lòng thử lại"));
                model.addAttribute("allUsers", userService.getUser());
                return "newsAddAdmin";
            }
        } else {
            try {
                tintuc.setImg("https://res.cloudinary.com/hoangtam/image/upload/v1658122999/sample.jpg");
                if (this.orthersService.addNews(tintuc) == true) {
                    return "redirect:/admin/news";
                } else {
                    rsN.addError(new FieldError("tintuc", "title", "Đã có lỗi xảy ra, vui lòng thử lại"));
                    model.addAttribute("allUsers", userService.getUser());
                    return "newsAddAdmin";
                }
            } catch (Exception ex) {
                rsN.addError(new FieldError("tintuc", "title", "Đã có lỗi xảy ra, vui lòng thử lại"));
                model.addAttribute("allUsers", userService.getUser());
                return "newsAddAdmin";
            }
        }

    }

    @GetMapping("/news/update/{nId}")
    public String UpdateNewsView(Model model, @PathVariable(name = "nId") int nId) {
        model.addAttribute("tintucUpdate", orthersService.getNewsById(nId));
        model.addAttribute("allUsers", this.userService.getUser());
        return "newsUpdateAdmin";
    }

    @PostMapping("/news/update/{nId}")
    public String updateNewsProcess(Model model,
            @Valid @ModelAttribute(value = "tintuc") StationNews tintuc,
            BindingResult rsNU,
            @PathVariable(name = "nId") int nId,
            HttpServletRequest request) {
        if (rsNU.hasErrors()) {
            rsNU.addError(new FieldError("tintuc", "title", "Đã có lỗi xảy ra, vui lòng thử lại"));
            model.addAttribute("tintucUpdate", orthersService.getNewsById(nId));
            model.addAttribute("allUsers", this.userService.getUser());
            return "newsUpdateAdmin";
        }
        if (!tintuc.getImgFile().isEmpty() && tintuc.getImgFile() != null) {

            try {
                Map results = cloudinary.uploader().upload(tintuc.getImgFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                tintuc.setImg(results.get("secure_url").toString());
                if (this.orthersService.updateNews(tintuc) == true) {
                    return "redirect:/admin/news";
                } else {
                    rsNU.addError(new FieldError("tintuc", "title", "Đã có lỗi xảy ra, vui lòng thử lại"));
                    model.addAttribute("tintuc", orthersService.getNewsById(nId));
                    model.addAttribute("allUsers", userService.getUser());
                    return "newsUpdateAdmin";
                }
            } catch (IOException ex) {
                rsNU.addError(new FieldError("tintuc", "title", "Đã có lỗi xảy ra, vui lòng thử lại"));
                model.addAttribute("tintuc", orthersService.getNewsById(nId));
                model.addAttribute("allUsers", userService.getUser());
                return "newsUpdateAdmin";
            }
        } else {
            try {
                if (this.orthersService.updateNews(tintuc) == true) {
                    return "redirect:/admin/news";
                } else {
                    rsNU.addError(new FieldError("tintuc", "title", "Đã có lỗi xảy ra, vui lòng thử lại"));
                    model.addAttribute("tintuc", orthersService.getNewsById(nId));
                    model.addAttribute("allUsers", userService.getUser());
                    return "newsUpdateAdmin";
                }
            } catch (Exception ex) {
                rsNU.addError(new FieldError("tintuc", "title", "Đã có lỗi xảy ra, vui lòng thử lại"));
                model.addAttribute("tintuc", orthersService.getNewsById(nId));
                model.addAttribute("allUsers", userService.getUser());
                return "newsUpdateAdmin";
            }
        }

    }

    //for user
    @GetMapping("/user")
    public String UserManager(Model model, @RequestParam Map<String, String> params) {
        int page = Integer.parseInt(params.getOrDefault("page", "0"));
        model.addAttribute("role", this.roleService.getRoles());
        model.addAttribute("allUsers", this.userService.getUserByAdmin(params, page));
        return "userManager";
    }

    @GetMapping("/user/{id}")
    public String UserInfo(Model model, @PathVariable(name = "id") int id) {
        model.addAttribute("u", this.userService.getUserById(id));
        return "userInfo";
    }

    @GetMapping(value = "/register")
    public String registerView(Model model) {
        model.addAttribute("admin", roleService.getRolesById(1));
        model.addAttribute("user", new Users());
        return "userRegisterAdmin";
    }

    @PostMapping(value = "/register")
    public String registerProcess(@ModelAttribute(name = "user") @Valid Users user,
            BindingResult resultA, HttpServletRequest request, Model model) {
        try {
            if (userService.getUserByUsername(user.getUsername().trim()) != null) {
                resultA.addError(new FieldError("user", "username", "Username đã tồn tại"));
                model.addAttribute("admin", roleService.getRolesById(1));
                return "userRegisterAdmin";
            }
        } catch (Exception ex) {

        }
        try {
            if (userService.getUserByEmail(user.getEmail().trim()) != null) {
                resultA.addError(new FieldError("user", "email", "Email đã tồn tại"));
                model.addAttribute("admin", roleService.getRolesById(1));
                return "userRegisterAdmin";
            }
        } catch (Exception ex) {

        }
        if (!user.getPassword().trim().equals(user.getConfirmPassword().trim())) {
            resultA.addError(new FieldError("user", "password", "Password không trùng khớp"));
            model.addAttribute("admin", roleService.getRolesById(1));
            return "userRegisterAdmin";
        }

        if (resultA.hasErrors()) {
            resultA.addError(new FieldError("u", "username", "Đã có lỗi xảy ra, vui lòng thử lại.(rs)"));
            model.addAttribute("admin", roleService.getRolesById(1));
            return "userRegisterAdmin";
        }
        if (user.getAvatarFile() != null && !user.getAvatarFile().isEmpty()) {
            try {

                Map results = cloudinary.uploader().upload(user.getAvatarFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                user.setAvatar(results.get("secure_url").toString());
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                if (this.userService.addUser(user) == true) {
                    return "redirect:/login";
                } else {
                    resultA.addError(new FieldError("user", "username", "Đã có lỗi xảy ra, vui lòng thử lại.(else1)"));
                    model.addAttribute("admin", roleService.getRolesById(1));
                    return "userRegisterAdmin";
                }

            } catch (IOException ex) {
                resultA.addError(new FieldError("user", "username", "Đã có lỗi xảy ra, vui lòng thử lại.(catch1)"));
                model.addAttribute("admin", roleService.getRolesById(1));
                return "userRegisterAdmin";
            }
        } else {
            try {
                user.setAvatar("https://res.cloudinary.com/hoangtam/image/upload/v1661743896/fvxksvvwif0fgmpmwclv.jpg");
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                if (this.userService.addUser(user) == true) {
                    return "redirect:/login";
                } else {
                    resultA.addError(new FieldError("user", "username", "Đã có lỗi xảy ra, vui lòng thử lại.(else2)"));
                    model.addAttribute("admin", roleService.getRolesById(1));
                    return "userRegisterAdmin";
                }
            } catch (Exception ex) {
                resultA.addError(new FieldError("user", "username", "Đã có lỗi xảy ra, vui lòng thử lại.(catch2)"));
                model.addAttribute("admin", roleService.getRolesById(1));
                return "userRegisterAdmin";
            }
        }
    }

    @RequestMapping("/user/delete/{id}")
    public String delUser(@PathVariable("id") int id) {
        userService.delUser(id);
        return "redirect:/admin/user/";
    }

    @GetMapping(value = "/user/update/{id}")
    public String updateUserView(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "userUpdateAdmin";
    }

    @RequestMapping("/user/update/{id}")
    public String updateUser(@Valid @ModelAttribute("user") Users user,
            BindingResult result, HttpServletRequest request, Model model,
            @PathVariable("id") int id) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("user", userService.getUserById(id));
            return "userUpdateAdmin";
        }
        if (user.getAvatarFile() != null && !user.getAvatarFile().isEmpty()) {
            try {

                Map results = cloudinary.uploader().upload(user.getAvatarFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                user.setAvatar(results.get("secure_url").toString());

                if (!user.getPassword().trim().equals(user.getConfirmPassword().trim())) {
                    model.addAttribute("user", userService.getUserById(id));
                    return "userUpdateAdmin";
                } else {
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    if (userService.updateUser(user) == true) {
                        return "redirect:/admin/user/";
                    }
                }
                return "userUpdateAdmin";
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
                model.addAttribute("user", userService.getUserById(id));

            }
        } else {

            if (!user.getPassword().trim().equals(user.getConfirmPassword().trim())) {
                model.addAttribute("user", userService.getUserById(id));
                return "userUpdateAdmin";
            } else {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                if (userService.updateUser(user) == true) {
                    return "redirect:/admin/user/";
                }
                return "userUpdateAdmin";
            }

        }
        return "userUpdateAdmin";
    }

    //for station 
    @GetMapping("/station")
    public String stationPage(Model model) {
        model.addAttribute("station", station.getStation());
        return "stationManager";
    }

    @GetMapping("/station/block")
    public String BlockStation(Model model) {
        model.addAttribute("blockSta", station.getBlockStation());
        return "stationBlock";
    }

    @RequestMapping("/station/delete/{id}")
    public String delStation(@PathVariable("id") int id) {
        station.delStation(id);
        return "redirect:/admin/station";
    }

    @RequestMapping("/station/block/{id}")
    public String blockStation(@PathVariable("id") int id) {
        if (station.blockStation(id) == true) {
            return "redirect:/admin/station/block";
        }
        return "redirect:/admin/station";
    }

    @GetMapping("/station/info/{id}")
    public String stationInFo(Model model, @PathVariable("id") int id) {
        model.addAttribute("station", this.station.getStationById(id));
        return "stationDetails";
    }

    @GetMapping("/station/add")
    public String addStationView(Model model) {
        model.addAttribute("station", new BusStation());

        model.addAttribute("allUsers", this.userService.getUser());
        return "stationAdd";
    }

    @PostMapping("/station/add")
    public String addStationProcess(Model model,
            @Valid @ModelAttribute(value = "station") BusStation station,
            BindingResult rsST,
            HttpServletRequest request) {
        if (rsST.hasErrors()) {
            rsST.addError(new FieldError("station", "name", "Đã có lỗi xảy ra, vui lòng thử lại"));
            model.addAttribute("allUsers", this.userService.getUser());
            return "stationAdd";
        }

        if (!station.getImgFile().isEmpty() && station.getImgFile() != null) {
            try {
                Map results = cloudinary.uploader().upload(station.getImgFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                station.setImg(results.get("secure_url").toString());
                if (this.station.addStation(station) == true) {
                    return "redirect:/admin/station";
                } else {
                    rsST.addError(new FieldError("station", "name", "Đã có lỗi xảy ra, vui lòng thử lại"));
                    model.addAttribute("allUsers", userService.getUser());
                    return "stationAdd";
                }
            } catch (IOException ex) {
                rsST.addError(new FieldError("station", "name", "Đã có lỗi xảy ra, vui lòng thử lại"));
                model.addAttribute("allUsers", userService.getUser());
                return "stationAdd";
            }
        } else {
            try {
                if (this.station.addStation(station) == true) {
                    return "redirect:/admin/station";
                } else {
                    rsST.addError(new FieldError("station", "name", "Đã có lỗi xảy ra, vui lòng thử lại"));
                    model.addAttribute("allUsers", userService.getUser());
                    return "stationAdd";
                }
            } catch (Exception ex) {
                rsST.addError(new FieldError("station", "name", "Đã có lỗi xảy ra, vui lòng thử lại"));
                model.addAttribute("allUsers", userService.getUser());
                return "stationAdd";
            }
        }

    }

    @GetMapping("/station/update/{sId}")
    public String updateStationView(Model model, @PathVariable("sId") int sId
    ) {
        model.addAttribute("stationUpdate", station.getStationById(sId));
        model.addAttribute("allUsers", this.userService.getUser());
        return "stationAdd";
    }

    @PostMapping("/station/update/{sId}")
    public String updateStationProcess(Model model,
            @PathVariable("sId") int sId,
            @Valid @ModelAttribute(value = "stationUpdate") BusStation stationnn,
            BindingResult rsSTU,
            HttpServletRequest request
    ) {
        if (rsSTU.hasErrors()) {
            rsSTU.addError(new FieldError("stationnn", "name", "Đã có lỗi xảy ra, vui lòng thử lại"));
            model.addAttribute("stationUpdate", station.getStationById(sId));
            model.addAttribute("allUsers", this.userService.getUser());
            return "stationAdd";
        }
        if (!stationnn.getImgFile().isEmpty() && stationnn.getImgFile()!= null) {
            try {
                Map results = cloudinary.uploader().upload(stationnn.getImgFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                stationnn.setImg(results.get("secure_url").toString());
                if (this.station.addStation(stationnn) == true) {
                    return "redirect:/admin/station";
                } else {
                    rsSTU.addError(new FieldError("stationnn", "name", "Đã có lỗi xảy ra, vui lòng thử lại"));
                    model.addAttribute("stationUpdate", station.getStationById(sId));
                    model.addAttribute("allUsers", userService.getUser());
                    return "stationAdd";
                }
            } catch (IOException ex) {
                model.addAttribute("allUsers", userService.getUser());
                rsSTU.addError(new FieldError("stationnn", "name", "Đã có lỗi xảy ra, vui lòng thử lại"));
                model.addAttribute("stationUpdate", station.getStationById(sId));
                return "stationAdd";
            }

        } else {
            try {
                if (this.station.addStation(stationnn) == true) {
                    return "redirect:/admin/station";
                } else {
                    model.addAttribute("stationUpdate", station.getStationById(sId));
                    rsSTU.addError(new FieldError("stationnn", "name", "Đã có lỗi xảy ra, vui lòng thử lại"));
                    model.addAttribute("allUsers", userService.getUser());
                    return "stationAdd";
                }
            } catch (Exception ex) {
                model.addAttribute("stationUpdate", station.getStationById(sId));
                rsSTU.addError(new FieldError("stationnn", "name", "Đã có lỗi xảy ra, vui lòng thử lại"));
                model.addAttribute("allUsers", userService.getUser());
                return "stationAdd";
            }
        }

    }

    //for bus owner manage
    @GetMapping("/owner")
    public String ownerPage(Model model
    ) {
        model.addAttribute("owner", busService.getBusOwners(0));
        return "ownerManagerAdmin";
    }

    @GetMapping("/owner/block")
    public String BlockOwner(Model model
    ) {
        model.addAttribute("blockOwner", busService.getBusOwners(1));
        return "ownerBlockAdmin";
    }

    @RequestMapping("/owner/delete/{id}")
    public String delOwner(@PathVariable("id") int id
    ) {
        busService.delBusOwner(id);
        return "redirect:/admin/owner";
    }

    @RequestMapping("/owner/block/{id}")
    public String blockOwner(@PathVariable("id") int id
    ) {
        if (busService.blockBusOwner(id) == true) {
            return "redirect:/admin/owner/block";
        }
        return "redirect:/admin/owner";
    }

    @GetMapping("/owner/active")//aaaaaaaaaaaaaaaaaaaaaaaaaa
    public String activeOwner(Model model
    ) {
        model.addAttribute("activeOwner", busService.getBusOwnersActive(0));
        return "ownerActiveAdmin";
    }

    @RequestMapping("/owner/active/{id}")//aaaaaaaaaaaaaaaaa
    public String activeOwnerPro(@PathVariable("id") int id
    ) {
        if (busService.activeBusOwner(id) == true) {
            return "redirect:/admin/owner";
        }
        return "redirect:/admin/owner/active";
    }

    @GetMapping("/owner/info/{id}")
    public String ownerInFo(Model model, @PathVariable("id") int id
    ) {
        model.addAttribute("ownerInfo", this.busService.getBusOwnerById(id));
        return "ownerDetailsAdmin";
    }

    @GetMapping("/owner/add")
    public String addOwnerView(Model model
    ) {
        model.addAttribute("ownerAdd", new BusOwner());
        model.addAttribute("allUsers", this.userService.getUserByRole("bus_owner"));
        return "ownerAddAdmin";
    }

    @PostMapping("/owner/add")
    public String addStationProcess(Model model,
            @Valid @ModelAttribute(value = "ownerAdd") BusOwner owner,
            BindingResult rsO,
            HttpServletRequest request
    ) {
        if (rsO.hasErrors()) {
            rsO.addError(new FieldError("owner", "name", "Đã có lỗi xảy ra, vui lòng thử lại"));
            model.addAttribute("allUsers", this.userService.getUserByRole("bus_owner"));
            return "ownerAddAdmin";
        }
        //get role to set is active = true
        if (owner.getImgFile() != null && !owner.getImgFile().isEmpty()) {
            try {
                Map results = cloudinary.uploader().upload(owner.getImgFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                owner.setImg(results.get("secure_url").toString());
                owner.setIsActive(Short.parseShort("1"));
                if (this.busService.addBusOwner(owner) == true) {
                    return "redirect:/admin/owner";
                } else {
                    rsO.addError(new FieldError("owner", "name", "Đã có lỗi xảy ra, vui lòng thử lại"));
                    model.addAttribute("allUsers", this.userService.getUserByRole("bus_owner"));
                    return "ownerAddAdmin";
                }
            } catch (IOException ex) {
                rsO.addError(new FieldError("owner", "name", "Đã có lỗi xảy ra, vui lòng thử lại"));
                model.addAttribute("allUsers", this.userService.getUserByRole("bus_owner"));
                return "ownerAddAdmin";
            }
        } else {
            try {
            owner.setImg("https://res.cloudinary.com/hoangtam/image/upload/v1660666079/psuuxpmapuh3vhgtwtxl.png");
            owner.setIsActive(Short.parseShort("1"));
            if (this.busService.addBusOwner(owner) == true) {
                return "redirect:/admin/owner";
            } else {
                rsO.addError(new FieldError("owner", "name", "Đã có lỗi xảy ra, vui lòng thử lại"));
                model.addAttribute("allUsers", this.userService.getUserByRole("bus_owner"));
                return "ownerAddAdmin";
            }
            } catch (Exception ex) {
                rsO.addError(new FieldError("owner", "name", "Đã có lỗi xảy ra, vui lòng thử lại"));
                model.addAttribute("allUsers", this.userService.getUserByRole("bus_owner"));
                return "ownerAddAdmin";
            }
        }
    }

}
