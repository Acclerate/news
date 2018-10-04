package cc.momas.news.service;

import java.util.List;

import cc.momas.news.entity.User;
import cc.momas.news.exception.BizException;

public interface UserService {

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 用户密码
     * @return 登录成功的用户
     * @throws BizException 当用户验证发生异常
     */
    User login(String username, String password) throws BizException;

    /**
     * 分页查询用户
     *
     * @param pageStart 起始页下标
     * @param pageCount 页容量,每页多少行数据
     * @return 用户列表
     */
    List<User> listPage(int pageStart, int pageCount);

    /**
     * 注册/添加一个用户
     *
     * @param username 用户名
     * @param password 用户密码,这个实时时要加密
     * @param isAdmin  添加的用户是否作为管理员存在
     * @param oprator  操作者
     */
    void registe(String username, String password, boolean isAdmin, User oprator);


    /**
     * 更新用户资料,多用于修改个人资料,管理员可以修改,用户自己也可修改
     * <p>
     * 空值表示不修改
     * <p>
     * TODO: 这里其实不应该可以更改密码的
     *
     * @param id       用户id,不可空
     * @param username 用户名
     * @param password 密码,实现中记得加密
     * @param isAdmin  是否为管理员, 可用于提权
     * @param status   更新后用户的状态,默认为审核中
     * @param oprator  操作者
     */
    void update(Integer id, String username, String password, Boolean isAdmin, Byte status, User oprator);

    /**
     * 删除用户
     *
     * @param id      用户id
     * @param oprator 操作人,如果不是管理员不允许操作
     */
    void delete(Integer id, User oprator);

}
