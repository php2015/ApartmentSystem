package com.aoke.apartmentsystem.system.controller;

import com.aoke.apartmentsystem.common.authentication.ShiroHelper;
import com.aoke.apartmentsystem.common.controller.BaseController;
import com.aoke.apartmentsystem.common.entity.FebsConstant;
import com.aoke.apartmentsystem.common.utils.DateUtil;
import com.aoke.apartmentsystem.common.utils.FebsUtil;
import com.aoke.apartmentsystem.system.entity.*;
import com.aoke.apartmentsystem.system.service.*;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.ExpiredSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author xiaoxinglin
 */
@Controller("systemView")
public class ViewController extends BaseController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IVillageService villageService;

    @Autowired
    private IBuildingService buildingService;

    @Autowired
    private IUnitService unitService;

    @Autowired
    private IRoomService roomService;

    @Autowired
    private IDeviceService deviceService;

    @Autowired
    private IContractService contractService;

    @Autowired
    private ITemplateContentService templateContentService;

    @Autowired
    private ShiroHelper shiroHelper;

    @GetMapping("login")
    @ResponseBody
    public Object login(HttpServletRequest request) {
        if (FebsUtil.isAjaxRequest(request)) {
            throw new ExpiredSessionException();
        } else {
            ModelAndView mav = new ModelAndView();
            mav.setViewName(FebsUtil.view("login"));
            return mav;
        }
    }

    @GetMapping("unauthorized")
    public String unauthorized() {
        return FebsUtil.view("error/403");
    }



    @GetMapping("/")
    public String redirectIndex() {
        return "redirect:/index";
    }

    @GetMapping("index")
    public String index(Model model) {
        AuthorizationInfo authorizationInfo = shiroHelper.getCurrentuserAuthorizationInfo();
        User user = super.getCurrentUser();
        user.setPassword("It's a secret");
        model.addAttribute("user", userService.findByName(user.getUsername())); // 获取实时的用户信息
        model.addAttribute("permissions", authorizationInfo.getStringPermissions());
        model.addAttribute("roles",authorizationInfo.getRoles());
        return "index";
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "layout")
    public String layout() {
        return FebsUtil.view("layout");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "password/update")
    public String passwordUpdate() {
        return FebsUtil.view("system/user/passwordUpdate");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "user/profile")
    public String userProfile() {
        return FebsUtil.view("system/user/userProfile");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "user/avatar")
    public String userAvatar() {
        return FebsUtil.view("system/user/avatar");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "user/profile/update")
    public String profileUpdate() {
        return FebsUtil.view("system/user/profileUpdate");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/user")
    @RequiresPermissions("user:view")
    public String systemUser() {
        return FebsUtil.view("system/user/user");
    }

    //租户
    @GetMapping(FebsConstant.VIEW_PREFIX + "system/tenant")
    @RequiresPermissions("tenant:view")
    public String systemTenant() {
        return FebsUtil.view("system/tenant/tenant");
    }

    //小区
    @GetMapping(FebsConstant.VIEW_PREFIX + "system/village")
    @RequiresPermissions("village:view")
    public String systemVillage() {
        return FebsUtil.view("system/village/village");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/village/add")
    @RequiresPermissions("village:add")
    public String systemVillageAdd() {
        return FebsUtil.view("system/village/villageAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/village/detail/{villageName}")
    @RequiresPermissions("village:view")
    public String systemVillageDetail(@PathVariable String villageName, Model model) {
        resolveVillageModel(villageName, model, true);
        return FebsUtil.view("system/village/villageDetail");
    }
    @GetMapping(FebsConstant.VIEW_PREFIX + "system/village/update/{villageName}")
    @RequiresPermissions("village:update")
    public String systemVillageUpdate(@PathVariable String villageName, Model model) {
        resolveVillageModel(villageName, model, false);
        return FebsUtil.view("system/village/villageUpdate");
    }

    //楼栋 单元
    @GetMapping(FebsConstant.VIEW_PREFIX + "system/building")
    @RequiresPermissions("building:view")
    public String systemBuilding() {
        return FebsUtil.view("system/building/building");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/building/add")
    @RequiresPermissions("building:add")
    public String systemBuildingAdd(Village village,Model model) {
        getVillageModel(village,model);
        return FebsUtil.view("system/building/buildingAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/building/detail/{buildingName}")
    @RequiresPermissions("building:view")
    public String systemBuildingDetail(@PathVariable String buildingName, Model model) {
        resolveBuildingModel(buildingName, model, true);
        return FebsUtil.view("system/building/buildingDetail");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/building/update/{buildingName}")
    @RequiresPermissions("building:update")
    public String systemBuildingUpdate(@PathVariable String buildingName, Village village,Model model) {
        resolveBuildingModel(buildingName, model, false);
        getVillageModel(village,model);
        return FebsUtil.view("system/building/buildingUpdate");
    }

    //房间
    @GetMapping(FebsConstant.VIEW_PREFIX + "system/room")
    @RequiresPermissions("room:view")
    public String systemRoom() {
        return FebsUtil.view("system/room/room");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/room/add")
    @RequiresPermissions("room:add")
    public String systemRoomAdd(Village village,Building building,Unit unit,Model model) {
        getVBUModel(village,building,unit,model);
        return FebsUtil.view("system/room/roomAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/room/detail/{roomName}")
    @RequiresPermissions("room:view")
    public String systemRoomDetail(@PathVariable String roomName, Model model) {
        resolveRoomModel(roomName, model, true);
        return FebsUtil.view("system/room/roomDetail");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/room/update/{roomName}")
    @RequiresPermissions("room:update")
    public String systemRoomUpdate(@PathVariable String roomName,Village village,Building building,Unit unit, Model model) {
        resolveRoomModel(roomName, model, false);
        getVBUModel(village,building,unit,model);
        return FebsUtil.view("system/room/roomUpdate");
    }


    //设备
    @GetMapping(FebsConstant.VIEW_PREFIX + "system/device")
    @RequiresPermissions("device:view")
    public String systemDevice() {
        return FebsUtil.view("system/device/device");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/device/add")
    @RequiresPermissions("device:add")
    public String systemDeviceAdd() {
        return FebsUtil.view("system/device/deviceAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/device/detail/{deviceName}")
    @RequiresPermissions("device:view")
    public String systemDeviceDetail(@PathVariable String deviceName, Model model) {
        resolveDeviceModel(deviceName, model, true);
        return FebsUtil.view("system/device/deviceDetail");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/device/update/{deviceName}")
    @RequiresPermissions("device:update")
    public String systemDeviceUpdate(@PathVariable String deviceName, Model model) {
        resolveDeviceModel(deviceName, model, false);
        return FebsUtil.view("system/device/deviceUpdate");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/user/add")
    @RequiresPermissions("user:add")
    public String systemUserAdd() {
        return FebsUtil.view("system/user/userAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/user/detail/{username}")
    @RequiresPermissions("user:view")
    public String systemUserDetail(@PathVariable String username, Model model) {
        resolveUserModel(username, model, true);
        return FebsUtil.view("system/user/userDetail");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/user/update/{username}")
    @RequiresPermissions("user:update")
    public String systemUserUpdate(@PathVariable String username, Model model) {
        resolveUserModel(username, model, false);
        return FebsUtil.view("system/user/userUpdate");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/role")
    @RequiresPermissions("role:view")
    public String systemRole() {
        return FebsUtil.view("system/role/role");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/menu")
    @RequiresPermissions("menu:view")
    public String systemMenu() {
        return FebsUtil.view("system/menu/menu");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/dept")
    @RequiresPermissions("dept:view")
    public String systemDept() {
        return FebsUtil.view("system/dept/dept");
    }

    @RequestMapping(FebsConstant.VIEW_PREFIX + "index")
    public String pageIndex() {
        return FebsUtil.view("index");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "404")
    public String error404() {
        return FebsUtil.view("error/404");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "403")
    public String error403() {
        return FebsUtil.view("error/403");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "500")
    public String error500() {
        return FebsUtil.view("error/500");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/contract")
    public String contract() {
        return FebsUtil.view("system/contract/contract");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/contract/detail/{contractId}")
    @RequiresPermissions("contract:view")
    public String systemContractDetail(@PathVariable String contractId, Model model) {
        resolveContractModel(contractId, model);
        return FebsUtil.view("system/contract/contractDetail");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/contract/edit/{contractId}")
    @RequiresPermissions("contract:update")
    public String systemContractEdit(@PathVariable String contractId, Model model) {
        resolveContractModel(contractId, model);
        return FebsUtil.view("system/contract/contractEdit");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/contract/add")
    @RequiresPermissions("contract:add")
    public String systemContractAdd() {
        return FebsUtil.view("system/contract/contractAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/contract/template")
    public String template() {
        return FebsUtil.view("system/contract/template");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/contract/template/{templateId}")
    @RequiresPermissions("template:view")
    public String systemTemplateDetail(@PathVariable String templateId, Model model) {
        resolveTemplateModel(templateId, model);
        return FebsUtil.view("system/contract/templateDetail");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/contract/template/edit/{templateId}")
    @RequiresPermissions("template:update")
    public String systemTemplateEdit(@PathVariable String templateId, Model model) {
        resolveTemplateModel(templateId, model);
        return FebsUtil.view("system/contract/templateEdit");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/contract/template/add")
    @RequiresPermissions("template:add")
    public String systemTemplateAdd() {
        return FebsUtil.view("system/contract/templateAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/contract/template/select")
    public String templateSelect() {
        return FebsUtil.view("system/contract/templateSelect");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/contract/template/content/{templateId}")
    public String templateContent(@PathVariable String templateId, Model model) {
        resolveTemplateContentModel(templateId, model);
        return FebsUtil.view("system/contract/templateContent");
    }

    private void resolveUserModel(String username, Model model, Boolean transform) {
        User user = userService.findByName(username);
        model.addAttribute("user", user);
        if (transform) {
            String ssex = user.getSex();
            if (User.SEX_MALE.equals(ssex)) {
                user.setSex("男");
            }else if (User.SEX_FEMALE.equals(ssex)) {
                user.setSex("女");
            }else {user.setSex("保密");}
        }
        if (user.getLastLoginTime() != null) {
            model.addAttribute("lastLoginTime", DateUtil.getDateFormat(user.getLastLoginTime(), DateUtil.FULL_TIME_SPLIT_PATTERN));
        }
    }

    private void resolveContractModel(String contractId, Model model) {
        Contract contract = new Contract();
        contract.setContractId(contractId);
        Wrapper<Contract> wrapper = new QueryWrapper<>(contract);
        contract = contractService.getOne(wrapper);
        model.addAttribute("contract", contract);

        if (contract.getEffectiveTime() != null) {
            model.addAttribute("effectiveTime", DateUtil.getDateFormat(contract.getEffectiveTime(), DateUtil.FULL_TIME_SPLIT_PATTERN));
        }

        if (contract.getCreateTime() != null) {
            model.addAttribute("createTime", DateUtil.getDateFormat(contract.getCreateTime(), DateUtil.FULL_TIME_SPLIT_PATTERN));
        }

        if (contract.getUpdateTime() != null) {
            model.addAttribute("updateTime", DateUtil.getDateFormat(contract.getUpdateTime(), DateUtil.FULL_TIME_SPLIT_PATTERN));
        }

        if (contract.getCredentialsType() != null) {
            Integer type = contract.getCredentialsType();
            String credentialsType = type == 0 ? "居民身份证" : (type == 1 ? "护照" : "未知");

            model.addAttribute("credentialsType", credentialsType);
        }

        if (contract.getHostCredentialsType() != null) {
            Integer type = contract.getHostCredentialsType();
            String hostCredentialsType = type == 0 ? "居民身份证" : (type == 1 ? "护照" : "未知");

            model.addAttribute("hostCredentialsType", hostCredentialsType);
        }

        if (contract.getContractStatus() != null) {
            Integer status = contract.getContractStatus();
            String contractStatus = "未知";
            switch (status) {
                case 0:
                    contractStatus = "有效";
                    break;
                case 1:
                    contractStatus = "实现";
                    break;
                case 2:
                    contractStatus = "到期";
                    break;
                case 3:
                    contractStatus = "待审核";
                    break;
                case 4:
                    contractStatus = "驳回";
                    break;
                case 5:
                    contractStatus = "退租中";
                    break;
                case 6:
                    contractStatus = "退租";
                    break;
                case 7:
                    contractStatus = "未付款";
                    break;
                case 8:
                    contractStatus = "续租";
                    break;
            }

            model.addAttribute("contractStatus", contractStatus);
        }
    }

    private void resolveTemplateModel(String templateId, Model model) {

    }

    private void resolveTemplateContentModel(String templateId, Model model) {
        TemplateContent content = new TemplateContent();
        content.setTemplateId(templateId);
        Wrapper<TemplateContent> wrapper = new QueryWrapper<>(content);
        List<TemplateContent> contentList = templateContentService.list(wrapper);
        model.addAttribute("contentList", contentList);
    }

    private void resolveVillageModel(String villageName, Model model, Boolean transform) {
        Village village = villageService.findByVillageName(villageName);
        model.addAttribute("village", village);
//        if (transform) {
//            String ssex = village.getSex();
//            if (User.SEX_MALE.equals(ssex)) user.setSex("男");
//            else if (User.SEX_FEMALE.equals(ssex)) user.setSex("女");
//            else user.setSex("保密");
//        }
//        if (user.getLastLoginTime() != null)
//            model.addAttribute("lastLoginTime", DateUtil.getDateFormat(user.getLastLoginTime(), DateUtil.FULL_TIME_SPLIT_PATTERN));
    }

    private void resolveBuildingModel(String buildingName, Model model, Boolean transform) {
        Building building = buildingService.findByBuildingName(buildingName);
        model.addAttribute("building", building);
//        if (transform) {
//            String ssex = village.getSex();
//            if (User.SEX_MALE.equals(ssex)) user.setSex("男");
//            else if (User.SEX_FEMALE.equals(ssex)) user.setSex("女");
//            else user.setSex("保密");
//        }
//        if (user.getLastLoginTime() != null)
//            model.addAttribute("lastLoginTime", DateUtil.getDateFormat(user.getLastLoginTime(), DateUtil.FULL_TIME_SPLIT_PATTERN));
    }

    private void resolveRoomModel(String roomName, Model model, Boolean transform) {
        Room room = roomService.findByRoomName(roomName);
        model.addAttribute("room", room);
//        if (transform) {
//            String ssex = village.getSex();
//            if (User.SEX_MALE.equals(ssex)) user.setSex("男");
//            else if (User.SEX_FEMALE.equals(ssex)) user.setSex("女");
//            else user.setSex("保密");
//        }
//        if (user.getLastLoginTime() != null)
//            model.addAttribute("lastLoginTime", DateUtil.getDateFormat(user.getLastLoginTime(), DateUtil.FULL_TIME_SPLIT_PATTERN));
    }

    private void resolveDeviceModel(String deviceName, Model model, Boolean transform) {
        Device device = deviceService.findByDeviceName(deviceName);
        model.addAttribute("device", device);
//        if (transform) {
//            String ssex = village.getSex();
//            if (User.SEX_MALE.equals(ssex)) user.setSex("男");
//            else if (User.SEX_FEMALE.equals(ssex)) user.setSex("女");
//            else user.setSex("保密");
//        }
//        if (user.getLastLoginTime() != null)
//            model.addAttribute("lastLoginTime", DateUtil.getDateFormat(user.getLastLoginTime(), DateUtil.FULL_TIME_SPLIT_PATTERN));
    }

    private void getVillageModel(Village village,Model model) {
        List<Village> villageList = villageService.findListVillage(village);
        model.addAttribute("villageList", villageList);
    }

    private void getVBUModel(Village village,Building building,Unit unit,Model model) {
        List<Village> villageList = villageService.findListVillage(village);
        List<Building> buildingList = buildingService.findListBuilding(building);
        List<Unit> unitList = unitService.findListUnit(unit);
        model.addAttribute("villageList", villageList);
        model.addAttribute("buildingList", buildingList);
        model.addAttribute("unitList", unitList);
    }
}
