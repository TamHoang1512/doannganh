/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hagh.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hagh.pojo.Bus;
import com.hagh.pojo.BusLine;
import com.hagh.pojo.BusOwner;
import com.hagh.pojo.BusTicket;
import com.hagh.pojo.Freight;
import com.hagh.pojo.StationNews;
import com.hagh.pojo.Users;
import com.hagh.service.BusService;
import com.hagh.service.FreightService;
import com.hagh.service.OrthersService;
import com.hagh.service.OwnerService;
import com.hagh.service.StationService;
import com.hagh.service.StatsService;
import com.hagh.service.TicketService;
import com.hagh.service.UserService;
import com.hagh.service.impl.AuthenticatedUserService;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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
@RequestMapping("/owner")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OwnerController {

    @Autowired
    private StatsService statsService;

    @Autowired
    MailSender mailSender;

    @Autowired
    private FreightService freightService;

    @Autowired
    private StationService stationService;

    @Autowired
    private OrthersService orthersService;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    OwnerService ownerService;

    @Autowired
    BusService busService;

    @Autowired
    TicketService ticketService;

    @Autowired
    UserService userService;

    @Autowired
    private AuthenticatedUserService authenticatedUserService;

    @GetMapping("/{id}")//tai khoan nha xe
    @PreAuthorize("@authenticatedUserService.hasId(#id)")
    public String ownerPage(Model model, @PathVariable("id") String id) {

        model.addAttribute("lead", ownerService.getBusOwnerByLead(Integer.parseInt(id)));
        model.addAttribute("bus", ownerService.getListAllBusByOwner(Integer.parseInt(id)));
        model.addAttribute("uId", id);
        return "ownerPage";
    }

    @GetMapping("/{id}/locked")
    @PreAuthorize("@authenticatedUserService.hasId(#id)")
    public String lockPage(Model model,
            @PathVariable("id") int id) {

        model.addAttribute("id", id);
        return "lockOwnerPage";
    }

//    user
    @GetMapping("/{id}/me")
    @PreAuthorize("@authenticatedUserService.hasId(#id)")
    public String accountPage(Model model,
            @PathVariable("id") int id) {

        model.addAttribute("me", userService.getUserById(id));
        return "myAccount";
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
//    
//   
    //BUS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
    @GetMapping("/{uId}/bus")//all bus
    @PreAuthorize("@authenticatedUserService.hasId(#uId)")
    public String ownerBusPage(Model model, @PathVariable("uId") int uId) {
        
        try  {
            BusOwner own = ownerService.getBusOwnerByUserId(uId);
            if(ownerService.checkBlockOrUnActive(own.getId()) == false)
            return "redirect:/owner/{uId}/locked";
            
            
        }
        catch(Exception ex){
            return "redirect:/owner/{uId}/locked";
        }
        model.addAttribute("busAll", busService.getAllBusByOwner(uId));
        model.addAttribute("uId2", uId);
        return "ownerBusPage";
    }

    @GetMapping("/{id}/own/{busOwnerId}/line/{lineId}")//cac chuyen xe trong tuyen xe /1/2/line/1
    @PreAuthorize("@authenticatedUserService.hasId(#id) && "
            + "@authenticatedUserService.hasTrueBusOwnerId(#id,#busOwnerId) &&"
            + "@authenticatedUserService.hasBusLineId(#busOwnerId,#lineId)")
    public String allBusPage(Model model,
            @PathVariable("id") int id,
            @PathVariable("busOwnerId") int busOwnerId,
            @PathVariable("lineId") int lineId) {

        if (ownerService.checkBlockOrUnActive(busOwnerId) == false) {
            return "redirect:/owner/{id}/locked";
        }

        model.addAttribute("uId", id);
        model.addAttribute("owner", busOwnerId);
        model.addAttribute("bus", ownerService.getBusByLine(lineId));
        model.addAttribute("busStarted", ownerService.getBusStartedByLine(lineId));
        return "allBus";

    }

    @GetMapping("/{id}/bus/{busId}")//chi tiet chuyen xe
    @PreAuthorize("@authenticatedUserService.hasId(#id) && "
            + "@authenticatedUserService.checkBusByUserId(#id,#busId)")
    public String BusDetailPage(Model model,
            @PathVariable("id") int id,
            @PathVariable("busId") int busId) {

        Bus bus = busService.getBusById(busId);

        if (ownerService.checkBlockOrUnActive(bus.getBusLineId().getBusOwnerId().getId()) == false) {
            return "redirect:/owner/{id}/locked";
        }

        Date now = new Date();
        Bus busDate = busService.getBusById(busId);
        int getDate = busDate.getStartAt().compareTo(now);
        boolean exDate;
        if (getDate < 0) {
            exDate = false;
        } else {
            exDate = true;
        }
        int emptySit = busService.getEmptySitByBus(busId);

        model.addAttribute("uId", id);
        model.addAttribute("busId", busService.getBusById(busId));
        model.addAttribute("emptySit", emptySit);
        model.addAttribute("now", exDate);
        return "busDetails";

    }

    @RequestMapping("/{id}/bus/delete/{bId}")
    @PreAuthorize("@authenticatedUserService.hasId(#id) && "
            + "@authenticatedUserService.checkBusByUserId(#id,#bId)")
    public String delBus(Model model, @PathVariable("id") int id,
            @PathVariable("bId") int bId) {

        busService.delBus(bId);
        return "redirect:/owner/{id}/";
    }

    @GetMapping("/{id}/bus/add")
    @PreAuthorize("@authenticatedUserService.hasId(#id)")
    public String addBusView(Model model, @PathVariable("id") int id) {
        model.addAttribute("addNewBus", new Bus());
        model.addAttribute("uid", id);
        BusOwner own = ownerService.getBusOwnerByUserId(id);
        model.addAttribute("busLine", ownerService.getListBusLineByOwner(own.getId()));
        model.addAttribute("own", own);

        return "addBusByOwn";
    }

    @PostMapping("/{id}/bus/add")
    @PreAuthorize("@authenticatedUserService.hasId(#id)")
    public String addBusProcess(Model model,
            @PathVariable("id") int id,
            @Valid @ModelAttribute(value = "addNewBus") Bus bus,
            BindingResult rsB,
            HttpServletRequest request) {

        if (rsB.hasErrors()) {
            rsB.addError(new FieldError("bus", "direction", "Đã có lỗi xảy ra, vui lòng thử lại"));
            model.addAttribute("busAdd", new Bus());
            model.addAttribute("u", id);
            BusOwner own = ownerService.getBusOwnerByUserId(id);
            model.addAttribute("busLine", ownerService.getListBusLineByOwner(own.getId()));
            return "redirect:/owner/{id}/bus/add";
        }
        Date now = new Date();
        if (bus.getStartAt() == null) {
            rsB.addError(new FieldError("bus", "direction", "Ngày khởi hành không được trống"));
            model.addAttribute("busAdd", new Bus());
            model.addAttribute("uid", id);
            BusOwner own = ownerService.getBusOwnerByUserId(id);
            model.addAttribute("busLine", ownerService.getListBusLineByOwner(own.getId()));
            return "addBusByOwn";
        }
        int getDate = bus.getStartAt().compareTo(now);
        if (getDate <= 0) {
            rsB.addError(new FieldError("bus", "direction", "Ngày khởi hành không được là ngày trong quá khứ"));
            model.addAttribute("busAdd", new Bus());
            model.addAttribute("uid", id);
            BusOwner own = ownerService.getBusOwnerByUserId(id);
            model.addAttribute("busLine", ownerService.getListBusLineByOwner(own.getId()));
            return "addBusByOwn";
        }

        //get role to set is active = true
        if (bus.getImgFile() != null && !bus.getImgFile().isEmpty()) {
            try {
                Map results = cloudinary.uploader().upload(bus.getImgFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                bus.setImg(results.get("secure_url").toString());
                if (this.busService.addBus(bus) == true) {
                    return "redirect:/owner/{id}";
                } else {
                    rsB.addError(new FieldError("bus", "direction", "Đã có lỗi xảy ra, vui lòng thử lại"));
                    model.addAttribute("busAdd", new Bus());
                    model.addAttribute("uid", id);
                    BusOwner own = ownerService.getBusOwnerByUserId(id);
                    model.addAttribute("busLine", ownerService.getListBusLineByOwner(own.getId()));
                    return "addBusByOwn";
                }
            } catch (IOException ex) {
                rsB.addError(new FieldError("bus", "direction", "Đã có lỗi xảy ra, vui lòng thử lại"));
                model.addAttribute("busAdd", new Bus());
                model.addAttribute("uid", id);
                BusOwner own = ownerService.getBusOwnerByUserId(id);
                model.addAttribute("busLine", ownerService.getListBusLineByOwner(own.getId()));
                return "addBusByOwn";
            }
        } else {
            try {
                bus.setImg("https://res.cloudinary.com/hoangtam/image/upload/v1660666079/psuuxpmapuh3vhgtwtxl.png");
                if (this.busService.addBus(bus) == true) {
                    return "redirect:/owner/{id}";
                } else {
                    rsB.addError(new FieldError("bus", "direction", "Đã có lỗi xảy ra, vui lòng thử lại"));
                    model.addAttribute("busAdd", new Bus());
                    model.addAttribute("uid", id);
                    BusOwner own = ownerService.getBusOwnerByUserId(id);
                    model.addAttribute("busLine", ownerService.getListBusLineByOwner(own.getId()));
                    return "addBusByOwn";
                }
            } catch (Exception ex) {
                rsB.addError(new FieldError("bus", "direction", "Đã có lỗi xảy ra, vui lòng thử lại"));
                model.addAttribute("busAdd", new Bus());
                model.addAttribute("uid", id);
                BusOwner own = ownerService.getBusOwnerByUserId(id);
                model.addAttribute("busLine", ownerService.getListBusLineByOwner(own.getId()));
                return "addBusByOwn";
            }
        }
    }

    @GetMapping("/{id}/bus/update/{bId}")
    @PreAuthorize("@authenticatedUserService.hasId(#id) &&  @authenticatedUserService.checkBusByUserId(#id, #bId)")
    public String updateBusView(Model model, @PathVariable("id") int id, @PathVariable("bId") int bId) {
        model.addAttribute("updateBus", this.busService.getBusById(bId));
        model.addAttribute("uid", id);
        model.addAttribute("bId", bId);
        BusOwner own = ownerService.getBusOwnerByUserId(id);
        model.addAttribute("busLine", ownerService.getListBusLineByOwner(own.getId()));
        model.addAttribute("own", own);

        return "updateBus";
    }

    @PostMapping("/{id}/bus/update/{bId}")
    @PreAuthorize("@authenticatedUserService.hasId(#id) &&  @authenticatedUserService.checkBusByUserId(#id, #bId)")
    public String updateBusProcess(Model model,
            @PathVariable("id") int id,
            @PathVariable("bId") int bId,
            @Valid @ModelAttribute(value = "addNewBus") Bus bus,
            BindingResult rsBU,
            HttpServletRequest request) {

        if (rsBU.hasErrors()) {
            rsBU.addError(new FieldError("bus", "direction", "Đã có lỗi xảy ra, vui lòng thử lại"));
            model.addAttribute("updateBus", this.busService.getBusById(bId));
            model.addAttribute("uid", id);
            model.addAttribute("bId", bId);
            BusOwner own = ownerService.getBusOwnerByUserId(id);
            model.addAttribute("busLine", ownerService.getListBusLineByOwner(own.getId()));
            model.addAttribute("own", own);

            return "updateBus";
        }
        Date now = new Date();
        int getDate = bus.getStartAt().compareTo(now);
        if (getDate <= 0) {
            rsBU.addError(new FieldError("bus", "direction", "Ngày khởi hành không được là ngày trong quá khứ"));
            model.addAttribute("updateBus", this.busService.getBusById(bId));
            model.addAttribute("uid", id);
            model.addAttribute("bId", bId);
            BusOwner own = ownerService.getBusOwnerByUserId(id);
            model.addAttribute("busLine", ownerService.getListBusLineByOwner(own.getId()));
            model.addAttribute("own", own);

            return "updateBus";
        }

        //get role to set is active = true
        if (bus.getImgFile() != null && !bus.getImgFile().isEmpty()) {
            try {
                Map results = cloudinary.uploader().upload(bus.getImgFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                bus.setImg(results.get("secure_url").toString());
                if (this.busService.updateBus(bus) == true) {
                    return "redirect:/owner/{id}";
                } else {
                    rsBU.addError(new FieldError("bus", "direction", "Đã có lỗi xảy ra, vui lòng thử lại"));
                    model.addAttribute("updateBus", this.busService.getBusById(bId));
                    model.addAttribute("uid", id);
                    model.addAttribute("bId", bId);
                    BusOwner own = ownerService.getBusOwnerByUserId(id);
                    model.addAttribute("busLine", ownerService.getListBusLineByOwner(own.getId()));
                    model.addAttribute("own", own);

                    return "updateBus";
                }
            } catch (IOException ex) {
                rsBU.addError(new FieldError("bus", "direction", "Đã có lỗi xảy ra, vui lòng thử lại"));
                model.addAttribute("updateBus", this.busService.getBusById(bId));
                model.addAttribute("uid", id);
                model.addAttribute("bId", bId);
                BusOwner own = ownerService.getBusOwnerByUserId(id);
                model.addAttribute("busLine", ownerService.getListBusLineByOwner(own.getId()));
                model.addAttribute("own", own);

                return "updateBus";
            }
        } else {
            try {
                if (this.busService.updateBus(bus) == true) {
                    return "redirect:/owner/{id}";
                } else {
                    model.addAttribute("updateBus", this.busService.getBusById(bId));
                    model.addAttribute("uid", id);
                    model.addAttribute("bId", bId);
                    BusOwner own = ownerService.getBusOwnerByUserId(id);
                    model.addAttribute("busLine", ownerService.getListBusLineByOwner(own.getId()));
                    model.addAttribute("own", own);

                    return "updateBus";
                }
            } catch (Exception ex) {
                rsBU.addError(new FieldError("bus", "direction", "Đã có lỗi xảy ra, vui lòng thử lại"));
                model.addAttribute("updateBus", this.busService.getBusById(bId));
                model.addAttribute("uid", id);
                model.addAttribute("bId", bId);
                BusOwner own = ownerService.getBusOwnerByUserId(id);
                model.addAttribute("busLine", ownerService.getListBusLineByOwner(own.getId()));
                model.addAttribute("own", own);

                return "updateBus";
            }
        }
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
//    
//   
    //line

    @GetMapping("/{id}/own/{busOwnerId}/line")//all busline of that owner // cac tuyen xe trong nha xe //1/2/line
    @PreAuthorize("@authenticatedUserService.hasId(#id) && "
            + "@authenticatedUserService.hasTrueBusOwnerId(#id,#busOwnerId) ")
    public String linePage(Model model,
            @PathVariable("id") int id,
            @PathVariable("busOwnerId") int busOwnerId) {

        if (ownerService.checkBlockOrUnActive(busOwnerId) == false) {
            return "redirect:/owner/{id}/locked";
        }

        model.addAttribute("uId", id);
        model.addAttribute("busOwnerId", busOwnerId);
        model.addAttribute("owner", ownerService.getListBusLineByOwner(busOwnerId));
        return "allBusLine";
    }

    @GetMapping("/{uId}/line")//all line
    @PreAuthorize("@authenticatedUserService.hasId(#uId)")
    public String ownerLinePage(Model model, @PathVariable("uId") int uId) {
        try  {
            BusOwner own = ownerService.getBusOwnerByUserId(uId);
            if(ownerService.checkBlockOrUnActive(own.getId()) == false)
            return "redirect:/owner/{uId}/locked";
            
            
        }
        catch(Exception ex){
            return "redirect:/owner/{uId}/locked";
        }
        BusOwner own = ownerService.getBusOwnerByUserId(uId);
        model.addAttribute("uId", uId);
        model.addAttribute("busOwnerId", own.getId());
        model.addAttribute("owner", ownerService.getListBusLineByOwner(own.getId()));
        return "allBusLine";
    }

    @GetMapping("/{id}/line/add")
    @PreAuthorize("@authenticatedUserService.hasId(#id)")
    public String addBusLineView(Model model, @PathVariable("id") int id) {
        model.addAttribute("addNewLine", new BusLine());
        model.addAttribute("uid", id);
        model.addAttribute("own", ownerService.getBusOwnerByUserId(id));
        model.addAttribute("station", stationService.getStation());

        return "addBusLineByOwn";
    }

    @PostMapping("/{id}/line/add")
    @PreAuthorize("@authenticatedUserService.hasId(#id)")
    public String addBusLineProcess(Model model,
            @PathVariable("id") int id,
            @Valid @ModelAttribute(value = "addNewLine") BusLine busLine,
            BindingResult rsL,
            HttpServletRequest request) {

        if (rsL.hasErrors()) {
            rsL.addError(new FieldError("busLine", "fromPos", "Đã có lỗi xảy ra, vui lòng thử lại"));
            model.addAttribute("addNewLine", new BusLine());
            model.addAttribute("uid", id);
            model.addAttribute("own", ownerService.getBusOwnerByUserId(id));
            model.addAttribute("station", stationService.getStation());
            return "redirect:/owner/{id}/line/add";
        }
        if (busLine.getFromPos().getId().equals(busLine.getToPos().getId())) {
            rsL.addError(new FieldError("busLine", "fromPos", "Địa điểm đi và đến không được trùng"));
            model.addAttribute("addNewLine", new BusLine());
            model.addAttribute("uid", id);
            model.addAttribute("own", ownerService.getBusOwnerByUserId(id));
            model.addAttribute("station", stationService.getStation());
            return "redirect:/owner/{id}/line/add";
        }

        if (busLine.getImgFile() != null && !busLine.getImgFile().isEmpty()) {
            try {
                Map results = cloudinary.uploader().upload(busLine.getImgFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                busLine.setImg(results.get("secure_url").toString());
                if (this.busService.addBusLine(busLine) == true) {
                    return "redirect:/owner/{id}";
                } else {
                    rsL.addError(new FieldError("busLine", "fromPos", "Đã có lỗi xảy ra, vui lòng thử lại"));
                    model.addAttribute("addNewLine", new BusLine());
                    model.addAttribute("uid", id);
                    model.addAttribute("own", ownerService.getBusOwnerByUserId(id));
                    model.addAttribute("station", stationService.getStation());
                    return "redirect:/owner/{id}/line/add";
                }
            } catch (IOException ex) {
                rsL.addError(new FieldError("busLine", "fromPos", "Đã có lỗi xảy ra, vui lòng thử lại"));
                model.addAttribute("addNewLine", new BusLine());
                model.addAttribute("uid", id);
                model.addAttribute("own", ownerService.getBusOwnerByUserId(id));
                model.addAttribute("station", stationService.getStation());
                return "redirect:/owner/{id}/line/add";
            }
        } else {
            try {
                busLine.setImg("https://res.cloudinary.com/hoangtam/image/upload/v1660666079/psuuxpmapuh3vhgtwtxl.png");
                if (this.busService.addBusLine(busLine) == true) {
                    return "redirect:/owner/{id}";
                }
            } catch (Exception ex) {
                rsL.addError(new FieldError("busLine", "fromPos", "Đã có lỗi xảy ra, vui lòng thử lại"));
                model.addAttribute("addNewLine", new BusLine());
                model.addAttribute("uid", id);
                model.addAttribute("own", ownerService.getBusOwnerByUserId(id));
                model.addAttribute("station", stationService.getStation());
                return "redirect:/owner/{id}/line/add";
            }
            rsL.addError(new FieldError("busLine", "fromPos", "Đã có lỗi xảy ra, vui lòng thử lại"));
            model.addAttribute("addNewLine", new BusLine());
            model.addAttribute("uid", id);
            model.addAttribute("own", ownerService.getBusOwnerByUserId(id));
            model.addAttribute("station", stationService.getStation());
            return "redirect:/owner/{id}/line/add";
        }

    }

    @GetMapping("/{id}/line/update/{lId}")
    @PreAuthorize("@authenticatedUserService.hasId(#id) && @authenticatedUserService.checkBusLineByUserId(#lId)")
    public String updateBusLineView(Model model, @PathVariable("id") int id, @PathVariable("lId") int lId) {
        model.addAttribute("updateLine", this.busService.getBusLineById(lId));
        model.addAttribute("uid", id);
        model.addAttribute("lId", lId);
        model.addAttribute("own", ownerService.getBusOwnerByUserId(id));
        model.addAttribute("station", stationService.getStation());
        return "updateBusLine";
    }

    @PostMapping("/{id}/line/update/{lId}")
    @PreAuthorize("@authenticatedUserService.hasId(#id) && @authenticatedUserService.checkBusLineByUserId(#lId)")
    public String updateBusLineProcess(Model model,
            @PathVariable("id") int id,
            @PathVariable("lId") int lId,
            @Valid @ModelAttribute(value = "updateLine") BusLine busLine,
            BindingResult rsLU,
            HttpServletRequest request) {

        if (rsLU.hasErrors()) {
            rsLU.addError(new FieldError("busLine", "fromPos", "Đã có lỗi xảy ra, vui lòng thử lại"));
            model.addAttribute("updateLine", this.busService.getBusLineById(lId));
            model.addAttribute("uid", id);
            model.addAttribute("lId", lId);
            model.addAttribute("own", ownerService.getBusOwnerByUserId(id));
            model.addAttribute("station", stationService.getStation());
            return "updateBusLineu";
        }
        if (busLine.getFromPos().getId() == busLine.getToPos().getId()) {
            rsLU.addError(new FieldError("busLine", "fromPos", "Đã có lỗi xảy ra, vui lòng thử lại"));
            model.addAttribute("updateLine", this.busService.getBusLineById(lId));
            model.addAttribute("uid", id);
            model.addAttribute("lId", lId);
            model.addAttribute("own", ownerService.getBusOwnerByUserId(id));
            model.addAttribute("station", stationService.getStation());
            return "updateBusLine";
        }

        if (busLine.getImgFile() != null && !busLine.getImgFile().isEmpty()) {
            try {
                Map results = cloudinary.uploader().upload(busLine.getImgFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                busLine.setImg(results.get("secure_url").toString());
                if (this.busService.updateBusLine(busLine) == true) {
                    return "redirect:/owner/{id}";
                } else {
                    rsLU.addError(new FieldError("busLine", "fromPos", "Đã có lỗi xảy ra, vui lòng thử lại"));
                    model.addAttribute("updateLine", this.busService.getBusLineById(lId));
                    model.addAttribute("uid", id);
                    model.addAttribute("lId", lId);
                    model.addAttribute("own", ownerService.getBusOwnerByUserId(id));
                    model.addAttribute("station", stationService.getStation());
                    return "updateBusLine";
                }
            } catch (IOException ex) {
                rsLU.addError(new FieldError("busLine", "fromPos", "Đã có lỗi xảy ra, vui lòng thử lại"));
                model.addAttribute("updateLine", this.busService.getBusLineById(lId));
                model.addAttribute("uid", id);
                model.addAttribute("lId", lId);
                model.addAttribute("own", ownerService.getBusOwnerByUserId(id));
                model.addAttribute("station", stationService.getStation());
                return "updateBusLine";
            }
        } else {
            try {
                if (this.busService.updateBusLine(busLine) == true) {
                    return "redirect:/owner/{id}";
                } else {
                    rsLU.addError(new FieldError("busLine", "fromPos", "Đã có lỗi xảy ra, vui lòng thử lại"));
                    model.addAttribute("updateLine", this.busService.getBusLineById(lId));
                    model.addAttribute("uid", id);
                    model.addAttribute("lId", lId);
                    model.addAttribute("own", ownerService.getBusOwnerByUserId(id));
                    model.addAttribute("station", stationService.getStation());
                    return "updateBusLine";
                }
            } catch (Exception ex) {
                rsLU.addError(new FieldError("busLine", "fromPos", "Đã có lỗi xảy ra, vui lòng thử lại"));
                model.addAttribute("updateLine", this.busService.getBusLineById(lId));
                model.addAttribute("uid", id);
                model.addAttribute("lId", lId);
                model.addAttribute("own", ownerService.getBusOwnerByUserId(id));
                model.addAttribute("station", stationService.getStation());
                return "updateBusLine";
            }

        }

    }

    @RequestMapping("/{id}/line/delete/{lId}")
    @PreAuthorize("@authenticatedUserService.hasId(#id) && "
            + "@authenticatedUserService.checkBusLineByUserId(#lId)")
    public String delBusLine(Model model, @PathVariable("id") int id,
            @PathVariable("lId") int lId) {

        busService.delBusLine(lId);
        return "redirect:/owner/{id}/";
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
//    
//    
    //ticket
    @GetMapping("/{id}/bus/{busId}/ticket/book") //add new ticket
    @PreAuthorize("@authenticatedUserService.hasId(#id) && "
            + "@authenticatedUserService.checkBusByUserId(#id,#busId)")
    public String bookTicketPage(Model model,
            @PathVariable("id") int id,
            @PathVariable("busId") int busId) {

        Bus bus = busService.getBusById(busId);

        if (ownerService.checkBlockOrUnActive(bus.getBusLineId().getBusOwnerId().getId()) == false) {
            return "redirect:/owner/{id}/locked";
        }

        String errMsg = null;
        Date now = new Date();
        Bus busDate = busService.getBusById(busId);
        int getDate = busDate.getStartAt().compareTo(now);
        if (getDate < 0) {
            errMsg = "Xe da khoi hanh, xin chon xe khac";
            model.addAttribute("errMsg", errMsg);
            return "redirect:/login";
        }
        int emptySit = busService.getEmptySitByBus(busId);
        if (emptySit == 0) {
            errMsg = "Xe khong con cho trong, xin chon xe khac";
            model.addAttribute("errMsg", errMsg);
            return "redirect:/login";
        }
        model.addAttribute("uId", id);
        model.addAttribute("u", userService.getUserById(id));
        model.addAttribute("busId", busService.getBusById(busId));
        model.addAttribute("ticket", new BusTicket());
        return "bookTicket";

    }

    @PostMapping("/{id}/bus/{busId}/ticket/book") //add new ticket
    @PreAuthorize("@authenticatedUserService.hasId(#id) && "
            + "@authenticatedUserService.checkBusByUserId(#id,#busId)")
    public String bookTicketProcess(Model model,
            @PathVariable("id") String id,
            @PathVariable("busId") String busId,
            @Valid @ModelAttribute("ticket") BusTicket ticket,
            BindingResult rsT,
            HttpServletRequest request) {

        if (rsT.hasErrors()) {
            rsT.addError(new FieldError("ticket", "numberOfSit", "Đã có lỗi xảy ra, vui lòng thử lại"));
            model.addAttribute("uId", id);
            model.addAttribute("u", userService.getUserById(Integer.parseInt(id)));
            model.addAttribute("busId", busService.getBusById(Integer.parseInt(busId)));
            return "bookTicket";
        }
        int emptySit = busService.getEmptySitByBus(Integer.parseInt(busId));
        if (ticket.getNumberOfSit() > emptySit) {
            rsT.addError(new FieldError("ticket", "numberOfSit", "Số lượng ghế vượt quá giới hạn chỗ còn lại"));
            model.addAttribute("uId", id);
            model.addAttribute("u", userService.getUserById(Integer.parseInt(id)));
            model.addAttribute("busId", busService.getBusById(Integer.parseInt(busId)));
            return "bookTicket";
        }

        try {
            ticket.setIsPurchased(Short.parseShort("0"));
            if (this.ticketService.bookTicket(ticket) == true) {
                return "redirect:/owner/{id}";
            }
            rsT.addError(new FieldError("ticket", "numberOfSit", "Đã có lỗi xảy ra, vui lòng thử lại"));
            model.addAttribute("uId", id);
            model.addAttribute("u", userService.getUserById(Integer.parseInt(id)));
            model.addAttribute("busId", busService.getBusById(Integer.parseInt(busId)));
            return "bookTicket";
        } catch (Exception e) {
            rsT.addError(new FieldError("ticket", "numberOfSit", "Đã có lỗi xảy ra, vui lòng thử lại"));
            model.addAttribute("uId", id);
            model.addAttribute("u", userService.getUserById(Integer.parseInt(id)));
            model.addAttribute("busId", busService.getBusById(Integer.parseInt(busId)));
            return "bookTicket";
        }

    }

    @GetMapping("/{id}/ticket")
    @PreAuthorize("@authenticatedUserService.hasId(#id)")
    public String loadTicket(Model model, @PathVariable("id") int id) {

        model.addAttribute("allTicket", ticketService.getAllTicketByOwn(id));
        model.addAttribute("uId", id);
        return "allTicket";
    }

    @RequestMapping("/{id}/ticket/delete/{tId}")
    @PreAuthorize("@authenticatedUserService.hasId(#id)&& "
            + "@authenticatedUserService.hasTicketId(#tId)")
    public String delTicket(Model model, @PathVariable("id") int id,
            @PathVariable("tId") int tId) {

        ticketService.delTicket(tId);
        return "redirect:/owner/{id}/ticket/";
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
//    
//   
    //owner
    @GetMapping("/{id}/own/add")
    @PreAuthorize("@authenticatedUserService.hasId(#id)")
    public String addOwnerView(Model model, @PathVariable("id") int id) {
        model.addAttribute("ownerAdd", new BusOwner());
        Users u = userService.getUserById(id);
        model.addAttribute("u", u);
        BusOwner own = ownerService.getBusOwnerByUserId(id);
        String errMsg = "";
        if (own != null) {
            errMsg = "Mỗi tài khoản chỉ được đăng ký 1 nhà xe";
            model.addAttribute("errMsg", errMsg);
            return "ownerAdd";
        }
        return "ownerAdd";
    }

    @PostMapping("/{id}/own/add")
    @PreAuthorize("@authenticatedUserService.hasId(#id)")
    public String addBusOwnerProcess(Model model,
            @PathVariable("id") int id,
            @Valid @ModelAttribute(value = "ownerAdd") BusOwner owner,
            BindingResult rsOA,
            HttpServletRequest request) {
        if (rsOA.hasErrors()) {
            rsOA.addError(new FieldError("owner", "name", "Đã có lỗi xảy ra, vui lòng thử lại"));
            Users u = userService.getUserById(id);
            model.addAttribute("u", u);
            return "ownerAdd";
        }
        //get role to set is active = true
        if (owner.getImgFile() != null && !owner.getImgFile().isEmpty()) {
            try {
                Map results = cloudinary.uploader().upload(owner.getImgFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                owner.setImg(results.get("secure_url").toString());
                owner.setIsActive(Short.parseShort("0"));
                if (owner.getIsFreight() != 0 && owner.getIsFreight() != 1) {
                    owner.setIsFreight(Short.parseShort("0"));
                }
                if (this.busService.addBusOwner(owner) == true) {
                    return "redirect:/owner/{id}";
                } else {
                    rsOA.addError(new FieldError("owner", "name", "Đã có lỗi xảy ra, vui lòng thử lại"));
                    Users u = userService.getUserById(id);
                    model.addAttribute("u", u);
                    return "ownerAdd";
                }
            } catch (IOException ex) {
                rsOA.addError(new FieldError("owner", "name", "Đã có lỗi xảy ra, vui lòng thử lại"));
                System.err.println(ex.getMessage());
                return "ownerAdd";
            }
        } else {
            owner.setImg("https://res.cloudinary.com/hoangtam/image/upload/v1660666079/psuuxpmapuh3vhgtwtxl.png");
            owner.setIsActive(Short.parseShort("0"));
            if (owner.getIsFreight() != 0 && owner.getIsFreight() != 1) {
                owner.setIsFreight(Short.parseShort("0"));
            }
            if (this.busService.addBusOwner(owner) == true) {
                return "redirec:/owner/{id}";
            } else {
                rsOA.addError(new FieldError("owner", "name", "Đã có lỗi xảy ra, vui lòng thử lại"));
                Users u = userService.getUserById(id);
                model.addAttribute("u", u);
                return "ownerAdd";
            }
        }
    }

    @GetMapping("/{id}/own/update/{oId}")
    @PreAuthorize("@authenticatedUserService.hasId(#id) && @authenticatedUserService.hasTrueBusOwnerId(#id,#oId)")
    public String updateOwnerView(Model model, @PathVariable("id") int id, @PathVariable("oId") int oId) {
        model.addAttribute("ownerUpdateV", this.busService.getBusOwnerById(id));
        Users u = userService.getUserById(id);
        model.addAttribute("u", u);
        model.addAttribute("oId", oId);
        return "ownerUpdate";
    }

    @PostMapping("/{id}/own/update/{oId}")
    @PreAuthorize("@authenticatedUserService.hasId(#id) && @authenticatedUserService.hasTrueBusOwnerId(#id,#oId)")
    public String updateBusOwnerProcess(Model model,
            @PathVariable("id") int id,
            @PathVariable("oId") int oId,
            @Valid @ModelAttribute(value = "ownerUpdateV") BusOwner owner,
            BindingResult rsOU,
            HttpServletRequest request) {
        if (rsOU.hasErrors()) {
            rsOU.addError(new FieldError("owner", "name", "Đã có lỗi xảy ra, vui lòng thử lại"));
            model.addAttribute("ownerUpdateV", this.busService.getBusOwnerById(id));
            Users u = userService.getUserById(id);
            model.addAttribute("u", u);
            model.addAttribute("oId", oId);
            return "ownerUpdate";
        }
        if (owner.getImgFile() != null && !owner.getImgFile().isEmpty()) {
            try {
                Map results = cloudinary.uploader().upload(owner.getImgFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                owner.setImg(results.get("secure_url").toString());
                if (owner.getIsFreight() != 0 && owner.getIsFreight() != 1) {
                    owner.setIsFreight(Short.parseShort("0"));
                }
                if (this.busService.updateBusOwner(owner) == true) {
                    return "redirect:/owner/{id}";
                } else {
                    rsOU.addError(new FieldError("owner", "name", "Đã có lỗi xảy ra, vui lòng thử lại"));
                    model.addAttribute("ownerUpdateV", this.busService.getBusOwnerById(id));
                    Users u = userService.getUserById(id);
                    model.addAttribute("u", u);
                    model.addAttribute("oId", oId);
                    return "ownerUpdate";
                }
            } catch (Exception ex) {
                rsOU.addError(new FieldError("owner", "name", "Đã có lỗi xảy ra, vui lòng thử lại"));
                model.addAttribute("ownerUpdateV", this.busService.getBusOwnerById(id));
                Users u = userService.getUserById(id);
                model.addAttribute("u", u);
                model.addAttribute("oId", oId);
                return "ownerUpdate";
            }
        } else {
            if (owner.getIsFreight() != 0 && owner.getIsFreight() != 1) {
                owner.setIsFreight(Short.parseShort("0"));
            }
            if (this.busService.updateBusOwner(owner) == true) {
                return "redirec:/owner/{id}";
            } else {
                rsOU.addError(new FieldError("owner", "name", "Đã có lỗi xảy ra, vui lòng thử lại"));
                model.addAttribute("ownerUpdateV", this.busService.getBusOwnerById(id));
                Users u = userService.getUserById(id);
                model.addAttribute("u", u);
                model.addAttribute("oId", oId);
                return "ownerUpdate";
            }
        }
    }

    @RequestMapping("/{id}/own/delete/{oId}")
    @PreAuthorize("@authenticatedUserService.hasId(#id)&& "
            + "@authenticatedUserService.hasTrueBusOwnerId(#id,#oId) ")
    public String delOwner(@PathVariable("id") int id,
            @PathVariable("oId") int oId) {

        busService.delBusOwner(oId);
        return "redirect:/owner/{id}";
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
//    
//   
    //news

    @GetMapping("/{id}/news")
    @PreAuthorize("@authenticatedUserService.hasId(#id)")
    public String NewsPage(Model model, @PathVariable("id") int id) {
        model.addAttribute("orthers", orthersService.getNewsByOwner(id));
        return "newsManagerOwner";
    }

    @RequestMapping("/{id}/news/delete/{nid}")
    @PreAuthorize("@authenticatedUserService.hasId(#id) && @authenticatedUserService.hasNewsId(#nid)")
    public String delNews(@PathVariable("id") int id, @PathVariable("nid") int nid) {
        orthersService.delNews(nid);
        return "redirect:/owner/{id}/news/";
    }

    @GetMapping("/{id}/news/info/{nid}")
    @PreAuthorize("@authenticatedUserService.hasId(#id) && @authenticatedUserService.hasNewsId(#nid)")
    public String NewsInFo(Model model, @PathVariable("id") int id,
            @PathVariable("nid") int nid) {
        model.addAttribute("news", this.orthersService.getNewsById(nid));
        return "newsDetailsOwner";
    }

    @GetMapping("/{id}/news/add")
    @PreAuthorize("@authenticatedUserService.hasId(#id)")
    public String addNewsView(Model model, @PathVariable("id") int id) {
        model.addAttribute("tintuc", new StationNews());

        model.addAttribute("allUsers", this.userService.getUserById(id));
        return "newsAddOwner";
    }

    @PostMapping("/{id}/news/add")
    @PreAuthorize("@authenticatedUserService.hasId(#id)")
    public String addNewsProcess(Model model, @PathVariable("id") int id,
            @Valid @ModelAttribute(value = "tintuc") StationNews tintuc,
            BindingResult rsN,
            HttpServletRequest request) {
        if (rsN.hasErrors()) {
            rsN.addError(new FieldError("owner", "name", "Đã có lỗi xảy ra, vui lòng thử lại"));
            model.addAttribute("allUsers", this.userService.getUserById(id));
            return "newsAddOwner";
        }

        if (tintuc.getImgFile() != null && !tintuc.getImgFile().isEmpty()) {
            try {
                Map results = cloudinary.uploader().upload(tintuc.getImgFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                tintuc.setImg(results.get("secure_url").toString());

                if (this.orthersService.addNews(tintuc) == true) {
                    return "redirect:/owner/{id}/news";
                } else {
                    rsN.addError(new FieldError("owner", "name", "Đã có lỗi xảy ra, vui lòng thử lại"));
                    model.addAttribute("allUsers", this.userService.getUserById(id));
                    return "newsAddOwner";
                }
            } catch (IOException ex) {
                rsN.addError(new FieldError("owner", "name", "Đã có lỗi xảy ra, vui lòng thử lại"));
                System.err.println(ex.getMessage());
                model.addAttribute("allUsers", this.userService.getUserById(id));
                return "newsAddOwner";
            }
        } else {
            tintuc.setImg("https://res.cloudinary.com/hoangtam/image/upload/v1660666079/psuuxpmapuh3vhgtwtxl.png");

            if (this.orthersService.addNews(tintuc) == true) {
                return "redirect:/owner/{id}/news";
            } else {
                rsN.addError(new FieldError("owner", "name", "Đã có lỗi xảy ra, vui lòng thử lại"));
                model.addAttribute("allUsers", this.userService.getUserById(id));
                return "newsAddOwner";
            }
        }

    }

    @GetMapping("/{id}/news/{nId}/update")
    @PreAuthorize("@authenticatedUserService.hasId(#id) && @authenticatedUserService.hasNewsId(#nId)")
    public String updateNewsView(Model model, @PathVariable("id") int id, @PathVariable("nId") int nId) {
        model.addAttribute("tintuc", orthersService.getNewsById(nId));
        model.addAttribute("nId", nId);
        model.addAttribute("allUsers", this.userService.getUserById(id));
        return "newsUpdateOwner";
    }

    @PostMapping("/{id}/news/{nId}/update")
    @PreAuthorize("@authenticatedUserService.hasId(#id) && @authenticatedUserService.hasNewsId(#nId)")
    public String updateNewsProcess(Model model, @PathVariable("nId") int nId, @PathVariable("id") int id,
            @Valid @ModelAttribute(value = "tintuc") StationNews tintuc,
            BindingResult rsNU,
            HttpServletRequest request) {
        if (rsNU.hasErrors()) {
            rsNU.addError(new FieldError("owner", "name", "Đã có lỗi xảy ra, vui lòng thử lại"));
            model.addAttribute("nId", nId);
            model.addAttribute("allUsers", this.userService.getUserById(id));
            return "newsUpdateOwner";
        }

        if (tintuc.getImgFile() != null && !tintuc.getImgFile().isEmpty()) {
            try {
                Map results = cloudinary.uploader().upload(tintuc.getImgFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                tintuc.setImg(results.get("secure_url").toString());

                if (this.orthersService.updateNews(tintuc) == true) {
                    return "redirect:/owner/{id}/news";
                } else {
                    rsNU.addError(new FieldError("owner", "name", "Đã có lỗi xảy ra, vui lòng thử lại"));
                    model.addAttribute("nId", nId);
                    model.addAttribute("allUsers", this.userService.getUserById(id));
                    return "newsUpdateOwner";
                }
            } catch (IOException ex) {
                rsNU.addError(new FieldError("owner", "name", "Đã có lỗi xảy ra, vui lòng thử lại"));
                System.err.println(ex.getMessage());
                model.addAttribute("nId", nId);
                model.addAttribute("allUsers", this.userService.getUserById(id));
                return "newsUpdateOwner";
            }
        } else {

            if (this.orthersService.updateNews(tintuc) == true) {
                return "redirect:/owner/{id}/news";
            } else {
                rsNU.addError(new FieldError("owner", "name", "Đã có lỗi xảy ra, vui lòng thử lại"));
                model.addAttribute("nId", nId);
                model.addAttribute("allUsers", this.userService.getUserById(id));
                return "newsUpdateOwner";
            }
        }

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
//    
//   
    //freight
    @GetMapping("/{uId}/freight")//all
    @PreAuthorize("@authenticatedUserService.hasId(#uId)")
    public String getFreight(Model model, @PathVariable("uId") int uId) {

        BusOwner own = ownerService.getBusOwnerByUserId(uId);
        model.addAttribute("freight", freightService.getFreightByOwn(own.getId()));
        model.addAttribute("uId", uId);
        return "viewFreight";
    }

    public void sendMailForReceive(String to) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("1951012111tam@ou.edu.vn");
        mailMessage.setTo(to);
        mailMessage.setSubject("DA CHUYEN HANG TOI");
        mailMessage.setText("Xin chao ban, nha xe xin thong bao hang ban gui da duoc giao toi, "
                + "vui long toi ben xe de lien he nhan hang, xin cam on.");

        mailSender.send(mailMessage);
    }

    @RequestMapping("/{id}/freight/send/{frId}")
    @PreAuthorize("@authenticatedUserService.hasId(#id) && @authenticatedUserService.hasFreightId(#frId)")
    public String sendFreight(@PathVariable("id") int id, @PathVariable("frId") int frId) {
        try {
            Freight fr = freightService.getFreightById(frId);
            sendMailForReceive(fr.getReceiveEmail());
            freightService.sendFreight(frId);
            return "redirect:/owner/{id}/freight/";
        } catch (Exception ex) {
            return "redirect:/owner/{id}";
        }
    }

    @GetMapping("/{id}/freight/add")
    @PreAuthorize("@authenticatedUserService.hasId(#id)")
    public String addFreightView(Model model, @PathVariable("id") int id) {
//        if()
        BusOwner owner = ownerService.getBusOwnerByUserId(id);
        if (owner.getIsFreight() != 1) {
            return "noneFR";
        }
        model.addAttribute("freight", new Freight());
        model.addAttribute("uId", id);
        model.addAttribute("own", owner);
        return "addFreight";
    }

    @PostMapping("/{id}/freight/add")
    @PreAuthorize("@authenticatedUserService.hasId(#id)")
    public String addFreightProcess(Model model, @PathVariable("id") int id,
            @Valid @ModelAttribute(value = "freight") Freight fr,
            BindingResult rsFR,
            HttpServletRequest request) {
        if (rsFR.hasErrors()) {
            rsFR.addError(new FieldError("fr", "sendName", "Đã có lỗi xảy ra, vui lòng thử lại"));
            model.addAttribute("freight", new Freight());
            model.addAttribute("uId", id);
            model.addAttribute("own", ownerService.getBusOwnerByUserId(id));
            return "addFreight";
        }

        try {

            if (this.freightService.addFreightByOwn(fr) == true) {
                return "redirect:/owner/{id}/freight";
            } else {
                rsFR.addError(new FieldError("fr", "sendName", "Đã có lỗi xảy ra, vui lòng thử lại"));
                model.addAttribute("freight", new Freight());
                model.addAttribute("uId", id);
                model.addAttribute("own", ownerService.getBusOwnerByUserId(id));
                return "addFreight";
            }
        } catch (Exception ex) {
            rsFR.addError(new FieldError("fr", "sendName", "Đã có lỗi xảy ra, vui lòng thử lại"));
            model.addAttribute("freight", new Freight());
            model.addAttribute("uId", id);
            model.addAttribute("own", ownerService.getBusOwnerByUserId(id));
            return "addFreight";
        }

    }

    @GetMapping("/{id}/freight/update/{frId}")
    @PreAuthorize("@authenticatedUserService.hasId(#id) && @authenticatedUserService.hasFreightId(#frId)")
    public String updateFreightView(Model model, @PathVariable("id") int id, @PathVariable("frId") int frId) {
        model.addAttribute("freightUpdate", this.freightService.getFreightById(frId));
        model.addAttribute("uId", id);
        model.addAttribute("frId", frId);
        model.addAttribute("own", ownerService.getBusOwnerByUserId(id));
        return "updateFreight";
    }

    @PostMapping("/{id}/freight/update/{frId}")
    @PreAuthorize("@authenticatedUserService.hasId(#id) && @authenticatedUserService.hasFreightId(#frId)")
    public String updateFreightProcess(
            Model model,
            @PathVariable("id") int id,
            @PathVariable("frId") int frId,
            @Valid @ModelAttribute(value = "freightUpdate") Freight fr,
            BindingResult rsFR,
            HttpServletRequest request) {

        if (rsFR.hasErrors()) {
            rsFR.addError(new FieldError("fr", "sendName", "Đã có lỗi xảy ra, vui lòng thử lại"));
            model.addAttribute("freightUpdate", this.freightService.getFreightById(frId));
            model.addAttribute("uId", id);
            model.addAttribute("frId", frId);
            model.addAttribute("own", ownerService.getBusOwnerByUserId(id));
            return "updateFreight";
        }

        try {

            if (this.freightService.upDateFreightByOwn(fr) == true) {
                return "redirect:/owner/{id}/freight";
            } else {
                rsFR.addError(new FieldError("fr", "sendName", "Đã có lỗi xảy ra, vui lòng thử lại"));
                model.addAttribute("freightUpdate", this.freightService.getFreightById(frId));
                model.addAttribute("uId", id);
                model.addAttribute("frId", frId);
                model.addAttribute("own", ownerService.getBusOwnerByUserId(id));
                return "updateFreight";
            }
        } catch (Exception ex) {
            rsFR.addError(new FieldError("fr", "sendName", "Đã có lỗi xảy ra, vui lòng thử lại"));
            model.addAttribute("freightUpdate", this.freightService.getFreightById(frId));
            model.addAttribute("uId", id);
            model.addAttribute("frId", frId);
            model.addAttribute("own", ownerService.getBusOwnerByUserId(id));
            return "updateFreight";
        }

    }

    @RequestMapping("/{id}/freight/delete/{frId}")
    @PreAuthorize("@authenticatedUserService.hasId(#id) && @authenticatedUserService.hasFreightId(#frId)")
    public String delFreight(@PathVariable("id") int id, @PathVariable("frId") int frId) {
        freightService.delFreightByOwn(frId);
        return "redirect:/owner/{id}/freight/";
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
//    
//   
    //statistic
    @GetMapping("/{id}/stats")
    @PreAuthorize("@authenticatedUserService.hasId(#id)")
    public String statsView(Model model, @PathVariable("id") int id,
            @RequestParam(value = "quarter", defaultValue = "0") int quarter,
            @RequestParam(value = "year", defaultValue = "2022") int year) {
        model.addAttribute("uId", id);
        BusOwner own = ownerService.getBusOwnerByUserId(id);

        model.addAttribute("countStats", statsService.countBusByLine(own.getId()));
        model.addAttribute("uId", id);
        model.addAttribute("revenuStats", statsService.revenueStats(quarter, year, own.getId()));

        return "stats";
    }
}
