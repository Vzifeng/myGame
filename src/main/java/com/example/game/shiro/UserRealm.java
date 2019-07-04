package com.example.game.shiro;

import com.example.game.error.BusinessErrorEnum;
import com.example.game.error.BusnessException;
import com.example.game.po.Permission;
import com.example.game.po.Role;
import com.example.game.po.User;
import com.example.game.po.UserPassword;
import com.example.game.service.PermissonService;
import com.example.game.service.RoleService;
import com.example.game.service.UserService;
import com.example.game.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.Authenticator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 16:33 2019/4/18
 * @Version ： $version$
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    PermissonService permissonService;



    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        Subject subject = SecurityUtils.getSubject();
        //String userName = principalCollection.getPrimaryPrincipal().toString();
        User user = (User) principalCollection.getPrimaryPrincipal();
        //构建权限的类
        SimpleAuthorizationInfo sim = new SimpleAuthorizationInfo();
        // 角色列表
        Set<String> roles = new HashSet<String>();
        //权限列表
        Set<String> permissons = new HashSet <>();
        if (user != null){
            List<Role> roleList = roleService.getRolesByUserId(user.getId());
           for (Role role : roleList){
               if (role.getIsUse()==0){
                   roles.add(role.getRoleName());
                   List<Permission> permissionList = permissonService.getPermissonByRoleId(role.getId());
                   for (Permission permisson : permissionList){
                       permissons.add(permisson.getPermissionName());
                   }
               }
           }
        }
        sim.setRoles(roles);
        sim.setStringPermissions(permissons);
        System.out.println("授权逻辑结束");
        return sim;
    }

    /**
     * 执行认证逻辑
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String userName = ((UsernamePasswordToken) token).getPrincipal().toString();
        String password = String.valueOf(usernamePasswordToken.getPassword());
        User user = userService.selectUserByUserName(userName.trim());
        if (user!=null){
            UserPassword userPassword = userService.getPasswordByUserId(user.getId());
            String realPwd = userPassword.getPassword();
            if (realPwd.equals(password)){
                SimpleAccount sa=new SimpleAccount(user,password,"REALM_NAME");
                Session session = SecurityUtils.getSubject().getSession();
                session.setAttribute("userSession",user);
                session.setAttribute("userSessionId",user.getId());
                return sa;
            }
        }
        return null;
    }
}
