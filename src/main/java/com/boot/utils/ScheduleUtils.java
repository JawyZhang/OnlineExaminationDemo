package com.boot.utils;

import com.boot.pojo.Exam;
import com.boot.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Mango
 * @Date 2020-06-04 14:33
 */
@Component
public class ScheduleUtils {
    private List<Exam> exams = new ArrayList<>();
    @Autowired
    private TeacherService teacherServiceImpl;

    @Scheduled(cron = "0/1 * * * * ?")
    private void refreshTask() {
        exams = teacherServiceImpl.selectScheduleExam();
        System.out.println("定时任务刷新，任务个数：" + exams.size());
    }

    @Scheduled(cron = "0/1 * * * * ?")
    private void scheduleTask() {
        long current = System.currentTimeMillis();
        for (int i = 0; i < exams.size(); i++) {
            Exam exam = exams.get(i);
            if (exam.getStatus() == 0) {
                if (current >= TimeUtils.getMillisTime(exam.getStart_time())) {
                    teacherServiceImpl.changeExamStatus(exam.getExam_id(), exam.getStatus() + 1);
                    System.out.println("考试:" + exam.getExam_name() + "已经自动开始");
                    exams.remove(exam);
                }
            } else if (exam.getStatus() == 1) {
                if (current >= TimeUtils.getMillisTime(exam.getFinish_time())) {
                    teacherServiceImpl.changeExamStatus(exam.getExam_id(), exam.getStatus() + 1);
                    System.out.println("考试:" + exam.getExam_name() + "已经自动结束");
                    exams.remove(exam);
                }
            }
        }
    }
}
