package com.aoke.apartmentsystem.system.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aoke.apartmentsystem.common.entity.FebsConstant;
import com.aoke.apartmentsystem.common.entity.QueryRequest;
import com.aoke.apartmentsystem.common.utils.DESEncrypt;
import com.aoke.apartmentsystem.common.utils.HttpClient;
import com.aoke.apartmentsystem.common.utils.SortUtil;
import com.aoke.apartmentsystem.system.entity.Device;
import com.aoke.apartmentsystem.system.entity.Village;
import com.aoke.apartmentsystem.system.mapper.DeviceMapper;
import com.aoke.apartmentsystem.system.mapper.VillageMapper;
import com.aoke.apartmentsystem.system.service.IDeviceService;
import com.aoke.apartmentsystem.system.service.IVillageService;
import com.aoke.apartmentsystem.third.constant.AccessToken;
import com.aoke.apartmentsystem.third.constant.Constant;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuwenze.poi.annotation.Excel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements IDeviceService {
    @Override
    public Device findByDeviceName(String deviceName) {
        return this.baseMapper.findByDeviceName(deviceName);
    }

    @Override
    public IPage<Device> findDeviceDetail(Device device, QueryRequest request) {
        Page<Device> page = new Page<>(request.getPageNum(), request.getPageSize());
        SortUtil.handlePageSort(request, page, "deviceId", FebsConstant.ORDER_DESC, false);
        return this.baseMapper.findDeviceDetailPage(page, device);
    }

    @Override
    public Device findDeviceDetail(String deviceName) {
        Device param = new Device();
        param.setDeviceName(deviceName);
        List<Device> devices = this.baseMapper.findDeviceDetail(param);
        return CollectionUtils.isNotEmpty(devices) ? devices.get(0) : null;
    }

    @Override
    @Transactional
    public void createDevice(Device device) {
        device.setDeviceId(UUID.randomUUID().toString().replace("-", ""));
        device.setDeviceBelong(device.getVillageName() + device.getBuildingName() + device.getUnitName() + device.getRoomName());
        device.setDelFlag(0);
        device.setThirdDevice(0);
        device.setCreateBy(AccessToken.username);
        device.setCreateTime(new Date());
        save(device);
    }


    @Override
    @Transactional
    public void createDeviceThird() {
        if (AccessToken.access_token == null) {
            getToken();
        }
        String thirdDevice = getDeviceThird();
        JSONObject jsonObject = JSONObject.parseObject(thirdDevice);
        int total = JSONObject.parseObject(jsonObject.getString("data")).getInteger("total");
        if (total == 0) {
            return;
        }
        List<Device> list = new ArrayList<Device>();
        JSONArray jsonArray = JSONObject.parseObject(jsonObject.getString("data")).getJSONArray("rows");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject obj = JSONObject.parseObject(jsonArray.get(i).toString());
            Device device = new Device();
            device.setDeviceId(obj.getString("lock_no"));
            device.setDeviceName(obj.getString("lock_no"));
            device.setDeviceType(typeChang(Integer.parseInt(obj.getString("lock_kind"))));
            device.setDeviceModel(modelChang(obj.getString("type")));
            device.setVillageName(obj.getString("house_code"));
            device.setDeviceBelong(obj.getString("address") + obj.getString("room_code"));
            device.setEffectiveYear(new Long((obj.getLong("guarantee_time_end") - obj.getLong("guarantee_time_start")) / 1000 / 3600 / 24 / 365).intValue());
            device.setCreateTime(new Date(obj.getLong("install_time")));
            device.setCreateBy(AccessToken.username);
            device.setDelFlag(0);
            device.setDeviceStatus(1);
            device.setThirdDevice(1);
            Device device1 = this.baseMapper.getOne(obj.getString("lock_no"));
            if(device1==null){
                list.add(device);
            }
        }
        if(list.size()>0){
            saveBatch(list);
        }
    }

    private int typeChang(int lock_kind) {
        int newType = 4;
        switch (lock_kind) {
            case 0:
                newType = 7;
                break;
            case 1:
                newType = 8;
                break;
            case 3:
                newType = 9;
                break;
            default:
                newType = 4;
        }
        return newType;
    }

    private String modelChang(String type) {
        String modelStr = null;
        switch (type) {
            case "01":
                modelStr = "蓝牙外门锁 A221";
                break;
            case "02":
                modelStr = "433 内门锁120T";
                break;
            case "03":
                modelStr = "蓝牙内门锁 A121";
                break;
            case "04":
                modelStr = "433 外门锁A220T";
                break;
            case "05":
                modelStr = "433 内门锁A120Q";
                break;
            case "32":
                modelStr = "433 内门锁A130";
                break;
            case "48":
                modelStr = "433 外门锁 A230门锁";
                break;
            case "49":
                modelStr = "刷身份证外门锁 A230ID";
                break;
            default:
                modelStr = "";
        }
        return modelStr;
    }

    @Override
    @Transactional
    public void deleteDevices(String[] deviceIds) {
        List<String> list = Arrays.asList(deviceIds);
        // 删除用户
        this.removeByIds(list);
    }

    @Override
    @Transactional
    public void updateDevice(Device device) {
        // 更新用户
//        village.setPassword(null);
//        village.setTenantAcount(null);
//        village.setTenantName(null);
        device.setUpdateTime(new Date());
        updateById(device);
    }

    @Override
    @Transactional
    public void updateProfile(Device device) {

    }

    private void getToken() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("account", Constant.OPS_HOST);
        DESEncrypt desPlus2 = new DESEncrypt(Constant.HH_KEY);
        paramMap.put("password", desPlus2.encrypt(Constant.PASSWORD));
        String res = HttpClient.post(Constant.BASE_URL + Constant.ApiAddress.LOGIN, JSONObject.toJSONString(paramMap), null, UUID.randomUUID().toString());
        AccessToken.access_token = JSONObject.parseObject(JSONObject.parseObject(res).getString("data")).getString("access_token");
    }

    private String getDeviceThird() {
        Map<String, Object> paramMapLock = new HashMap<String, Object>();
        paramMapLock.put("page_size", 10);
        paramMapLock.put("current_page", 1);
        paramMapLock.put("node_no", null);
        paramMapLock.put("lock_no", null);
        paramMapLock.put("house_code", null);
        paramMapLock.put("room_code", null);
        String resLock = HttpClient.post(Constant.BASE_URL + Constant.ApiAddress.LOCK_LIST, JSONObject.toJSONString(paramMapLock), AccessToken.access_token, UUID.randomUUID().toString());
        System.out.println(resLock);
        return resLock;
    }

    @Override
    public Device getOne(String deviceName) {
        return baseMapper.getOne(deviceName);
    }
}
