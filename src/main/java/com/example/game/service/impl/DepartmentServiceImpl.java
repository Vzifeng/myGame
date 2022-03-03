//package com.example.game.service.impl;
//
//import com.example.game.service.DepartmentService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @ Author   ：yangyunlong.
// * @ Date     ：Created in 9:42 2019/11/28
// * @Version ： 1.0
// */
//@Service
//public class DepartmentServiceImpl implements DepartmentService {
//
//    private final static Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceImpl.class);
//
//    @Autowired
//    private CompanyMapper companyMapper;
//
//    @Autowired
//    private DepartmentMapper departmentMapper;
//
//    @Override
//    public List<Map<String, Object>> departmentTree() {
//        List<Map<String,Object>> companylist = companyMapper.selectCompanyList();
//        List<Map<String,Object>> result = new ArrayList <>();
//        for (Map companyMap : companylist){
//            Map<String,Object> map = new HashMap <>();
//            int parentId = (int) companyMap.get("parentId");
//            int id = (int) companyMap.get("companyId");
//            map.put("id",id);
//            map.put("parentId",parentId);
//            map.put("companyName",companyMap.get("companyName"));
//            map.put("child",getchild(id,companylist));
//            map.put("department",getDepartment(id));
//            result.add(map);
//        }
//        return result;
//    }
//
//    private Object getDepartment(int id) {
//        List<Map<String,Object>> departmentList1 = new ArrayList <>();
//        List<Map<String,Object>> departmentlist = departmentMapper.selectdepartmentList(id);
//        for (Map map1 : departmentlist){
//            Map<String,Object> department = new HashMap <>();
//            int dparentId = (int) map1.get("parentId");
//            int departmentId = (int) map1.get("id");
//
//            department.put("departmentId",departmentId);
//            department.put("departmentName",map1.get("departmentName"));
//            department.put("departmentParentId",dparentId);
//            department.put("departmentCompanyId",map1.get("companyId"));
//            department.put("departmentChild",departmentChild(departmentId,departmentlist));
//            departmentList1.add(department);
//        }
//        return departmentList1;
//    }
//
//    private Object departmentChild(int departmentId, List<Map<String,Object>> departmentlist) {
//        List<Map<String,Object>> result = new ArrayList <>();
//        List<Map<String,Object>> list = departmentMapper.selectDepartmentByParentId(departmentId);
//        for (Map map : list){
//            Map<String,Object> map1 = new HashMap <>();
//            int parentId = (int) map.get("parentId");
//            int id1 = (int) map.get("id");
//            map1.put("departmentId",id1);
//            map1.put("departmentParentId",parentId);
//            map1.put("departmentName",map.get("departmentName"));
//            map1.put("departmentCompanyId",map.get("companyId"));
//            map1.put("child",departmentChild(id1,departmentlist));
//            result.add(map1);
//        }
//        return result;
//    }
//
//    private Object getchild(int id, List<Map<String,Object>> companylist) {
//        List<Map<String,Object>> result = new ArrayList <>();
//        List<Map<String,Object>> list = companyMapper.selectCompanyByParentId(id);
//        for (Map map : list){
//            int id1 = (int) map.get("companyId");
//            int parentId = (int) map.get("parentId");
//            Map<String,Object> map1 = new HashMap <>();
//            map1.put("id",id1);
//            map1.put("parentId",parentId);
//            map1.put("companyName",map.get("companyName"));
//            map1.put("department",getDepartment(id1));
//            map1.put("child",getchild(id1,companylist));
//            result.add(map1);
//        }
//        return result;
//    }
//}
