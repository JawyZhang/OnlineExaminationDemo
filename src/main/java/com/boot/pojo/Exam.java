package com.boot.pojo;

import java.util.ArrayList;

public class Exam {
    private int exam_id;
    private String exam_name;
    private Course course;
    private String start_time;
    private String finish_time;
    private String creater;
    private boolean is_auto_begin;
    private boolean is_archived;
    private boolean is_cleaned;
    private int can_delete;//教师下载完成考试信息，该考试就可以被删除了
    private String paper_path;

    private Score score;    //学生查询自己成绩
    private ArrayList<Score> scores;    //教师查询所有学生成绩

    public int getExam_id() {
        return exam_id;
    }

    public void setExam_id(int exam_id) {
        this.exam_id = exam_id;
    }

    public String getExam_name() {
        return exam_name;
    }

    public void setExam_name(String exam_name) {
        this.exam_name = exam_name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getFinish_time() {
        return finish_time;
    }

    public void setFinish_time(String finish_time) {
        this.finish_time = finish_time;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public boolean isIs_auto_begin() {
        return is_auto_begin;
    }

    public void setIs_auto_begin(boolean is_auto_begin) {
        this.is_auto_begin = is_auto_begin;
    }

    public boolean isIs_archived() {
        return is_archived;
    }

    public void setIs_archived(boolean is_archived) {
        this.is_archived = is_archived;
    }

    public boolean isIs_cleaned() {
        return is_cleaned;
    }

    public void setIs_cleaned(boolean is_cleaned) {
        this.is_cleaned = is_cleaned;
    }

    public int getCan_delete() {
        return can_delete;
    }

    public void setCan_delete(int can_delete) {
        this.can_delete = can_delete;
    }

    public String getPaper_path() {
        return paper_path;
    }

    public void setPaper_path(String paper_path) {
        this.paper_path = paper_path;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public ArrayList<Score> getScores() {
        return scores;
    }

    public void setScores(ArrayList<Score> scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "exam_id=" + exam_id +
                ", exam_name='" + exam_name + '\'' +
                ", course=" + course +
                ", start_time='" + start_time + '\'' +
                ", finish_time='" + finish_time + '\'' +
                ", creater='" + creater + '\'' +
                ", is_auto_begin=" + is_auto_begin +
                ", is_archived=" + is_archived +
                ", is_cleaned=" + is_cleaned +
                ", can_delete=" + can_delete +
                ", paper_path='" + paper_path + '\'' +
                ", score=" + score +
                ", scores=" + scores +
                '}';
    }
}
