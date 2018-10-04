package cc.momas.news.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import cc.momas.news.common.BeanFactory;
import cc.momas.news.common.Constant;
import cc.momas.news.common.JsonUtil;
import cc.momas.news.entity.Category;
import cc.momas.news.entity.User;
import cc.momas.news.service.CategoryService;

@WebServlet(value = {"/category"}, description = "分类相关", displayName = "CategoryServlet", name = "CategoryServlet")
public class CategoryServlet extends BaseServlet {

    private static final long serialVersionUID = 1L;
    private CategoryService categoryService = (CategoryService) BeanFactory.getBean(BeanFactory.SERVICE_CATEGORY);

    /**
     * 用于获取分类
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 调用业务
        List<Category> categoryList = categoryService.list();
        // 返回结果
        String json = JsonUtil.toJson(categoryList);
        response.getWriter().println(json);
    }

    /**
     * 用于新增分类
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 验证登录
        Object loginUserObject = request.getSession().getAttribute(Constant.UserConstant.LOGIN_USER);
        if (loginUserObject == null) {
            throw new ServletException("请您登录后再新增评论");
        }

        // 接收参数
        String parentIdString = request.getParameter("parentId");
        String name = request.getParameter("name");

        // 数据校验
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("类别名称不能为空");
        }
        // 这一行的意思是,如果内容为空则直接传null进方法,不为空则转为数字再传,但转换失败的时候会转为 -1
        Integer parentId = StringUtils.isBlank(parentIdString) ? null : NumberUtils.toInt(parentIdString, -1);

        categoryService.add(parentId, name, (User) loginUserObject);
    }

    /**
     * 用于修改分类
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 验证登录
        Object loginUserObject = request.getSession().getAttribute(Constant.UserConstant.LOGIN_USER);
        if (loginUserObject == null) {
            throw new ServletException("请您登录后再修改分类");
        }

        // 接收参数
        String idString = request.getParameter("id");
        String parentIdString = request.getParameter("parentId");
        String name = request.getParameter("name");
        String statusString = request.getParameter("status");

        // 验证参数
        if (StringUtils.isBlank(idString)) {
            throw new IllegalArgumentException("分类 id 未指定");
        }
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("分类名称未指定");
        }
        Integer id = NumberUtils.toInt(idString);
        // 这一行的意思是,如果内容为空则直接传null进方法,不为空则转为数字再传,但转换失败的时候会转为 -1
        Integer parentId = StringUtils.isBlank(parentIdString) ? null : NumberUtils.toInt(parentIdString, -1);
        // null 表示不修改数据库, 0 是有意义的,转换失败的时候将状态变为审核中
        Byte status = StringUtils.isBlank(statusString) ? null : NumberUtils.toByte(statusString, Constant.StatusConstant.AUDIT);

        categoryService.update(id, parentId, name, status, (User) loginUserObject);

    }

    /**
     * 用于删除分类
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        // 验证登录
        Object loginUserObject = request.getSession().getAttribute(Constant.UserConstant.LOGIN_USER);
        if (loginUserObject == null) {
            throw new ServletException("请您登录后再删除分类");
        }

        String idString = request.getParameter("id");
        if (StringUtils.isBlank(idString)) {
            throw new IllegalArgumentException("分类 id 未指定");
        }
        Integer id = NumberUtils.toInt(idString, -1);
        categoryService.delete(id, (User) loginUserObject);
    }

}
