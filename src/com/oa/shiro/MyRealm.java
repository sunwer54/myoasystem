package com.oa.shiro;

import com.oa.mapper.EmployeeMapper;
import com.oa.pojo.Employee;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    private EmployeeMapper employeeMapper;
    //用来授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //给每个登录人进行授权
         //1.获取登录人的身份信息
        Employee employee = (Employee)principalCollection.getPrimaryPrincipal();
        List<String> roles = employeeMapper.selRolesByEmpid(employee.getEmpid());
        //创建AuthorizationInfo对象
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roles);//把角色放入到SimpleAuthorizationInfo对象中
        return info;
    }

    //用来认证用户信息(登录)
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("authenticationToken里含有从用户提交的数据:"+authenticationToken);
        //获取用户提交的信息
        String empid = (String)authenticationToken.getPrincipal();
        //根据用户名查数据库
        Employee employee = employeeMapper.selectByPrimaryKey(empid);
        //数据库里面的用户名和密码会封装到一个SimpleAuthenticationInfo
        //把用户提交的密码+盐进行加密计算,并且把登录的身份信息放入shiro的SessionManager中
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(employee,employee.getPassword(), ByteSource.Util.bytes("likeyou"),"haha");//第三个参数是设置realm的自定义名字,随便写
        return info;
    }
}
