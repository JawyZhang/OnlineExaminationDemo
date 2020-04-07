package com.boot.service.impl;

import com.boot.mapper.TeacherMapper;
import com.boot.pojo.PageInfo;
import com.boot.pojo.Teacher;
import com.boot.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Mango
 * @Date 2020-04-03 18:46
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public List<Teacher> selectAll() {
        return teacherMapper.selectAll();
    }

    @Override
    public PageInfo selectAllByPage(PageInfo pageInfo) {
        pageInfo.setPageStart((pageInfo.getPageNumber() - 1) * pageInfo.getPageSize());
        pageInfo.setTotal(teacherMapper.selectCount());
        int count = pageInfo.getTotal();
        pageInfo.setTotalPage(count % pageInfo.getPageSize() == 0 ? count / pageInfo.getPageSize() : count / pageInfo.getPageSize() + 1);
        pageInfo.setList(teacherMapper.selectAllByPage(pageInfo));
        return pageInfo;
    }

    @Override
    public Teacher selectByUsernameAndPassword(Teacher teacher) {
        return teacherMapper.selectByUsernameAndPassword(teacher);
    }

    @Override
    public Teacher selectByTeacherIdAndPassword(Teacher teacher) {
        return teacherMapper.selectByTeacherIdAndPassword(teacher);
    }

    @Override
    public List<Teacher> selectByUsername(String username) {
        return teacherMapper.selectByUsername(username);
    }

    @Override
    public List<Teacher> selectByTeacherId(String teacher_id) {
        return teacherMapper.selectByTeacherId(teacher_id);
    }

    @Override
    public Integer asAdmin(Integer id) {
        return teacherMapper.asAdmin(id);
    }

    @Override
    public Integer cancelAdmin(Integer id) {
        return teacherMapper.cancelAdmin(id);
    }

    @Override
    public Integer insertTeacher(Teacher teacher) {
        return teacherMapper.insertTeacher(teacher);
    }

    @Override
    public Integer updateTeacherInfo(Teacher teacher) {
        return teacherMapper.updateTeacherInfo(teacher);
    }

    @Override
    public Integer updateTeacher(Teacher teacher) {
        return teacherMapper.updateTeacher(teacher);
    }

    @Override
    public Integer deleteTeacher(Integer id) {
        return teacherMapper.deleteTeacher(id);
    }
}
