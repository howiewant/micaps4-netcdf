package pers.howie.micaps.entity;

import java.util.List;

/**
 * @author howie
 * @since 2023/6/8
 * diamond 4 数据说明（字符串）
 * 年 月 日 时次 时效 层次（均为整数）经度格距 纬度格距 起始经度 终止经度 起始纬度 终止纬度（均为浮点数） 纬向格点数 经向格点数（均为整数） 等值线间隔 等值线起始值 终止值 平滑系数 加粗线值（均为浮点数）
 */
public class Micaps4 {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int aging;
    private int level;
    private float lonSpacing;
    private float latSpacing;
    private float startLon;
    private float endLon;
    private float startLat;
    private float endLat;
    private int latPoints;
    private int lonPoints;
    private float contourSpacing;
    private float contourStart;
    private float contourEnd;
    private float smoothingFactor;
    private float boldLine;
    private float[][] data;

    public static class Builder {
        private Micaps4 m4 = new Micaps4();

        public Builder header(String header) {
            String[] s = header.trim().split(" ");
            m4.setYear(Integer.parseInt(s[0]));
            m4.setMonth(Integer.parseInt(s[1]));
            m4.setDay(Integer.parseInt(s[2]));
            m4.setHour(Integer.parseInt(s[3]));
            m4.setAging(Integer.parseInt(s[4]));
            m4.setLevel(Integer.parseInt(s[5]));

            m4.setLonSpacing(Float.parseFloat(s[6]));
            m4.setLatSpacing(Float.parseFloat(s[7]));
            m4.setStartLon(Float.parseFloat(s[8]));
            m4.setEndLon(Float.parseFloat(s[9]));
            m4.setStartLat(Float.parseFloat(s[10]));
            m4.setEndLat(Float.parseFloat(s[11]));

            m4.setLonPoints(Integer.parseInt(s[12]));
            m4.setLatPoints(Integer.parseInt(s[13]));

            m4.setContourSpacing(Float.parseFloat(s[14]));
            m4.setContourStart(Float.parseFloat(s[15]));
            m4.setContourEnd(Float.parseFloat(s[16]));
            m4.setSmoothingFactor(Float.parseFloat(s[17]));
            m4.setBoldLine(Float.parseFloat(s[18]));
            return this;
        }

        public Builder data(List<String> datas) {
            float[][] dataset = new float[m4.getLatPoints()][m4.getLonPoints()];
            for (int i = 0; i < datas.size(); i++) {
                String[] s = datas.get(i).split(" ");
                for (int j = 0; j < s.length; j++) {
                    dataset[i][j] = Float.parseFloat(s[j]);
                }
            }
            m4.setData(dataset);
            return this;
        }

        public Micaps4 build(){
            return m4;
        }

    }

    public float[] getLats(){
        int p = latPoints;
        float b = startLat - endLat;
        float o = b / p;
        System.out.println(o);
        float[] res = new float[p];
        for (int i = 0; i < p; i++) {
            res[i] = endLat + (i * o);
        }
        return res;
    }
    public float[] getLons(){
        int p = lonPoints;
        float b = endLon - startLon;
        float o = b / p;
        System.out.println(o);
        float[] res = new float[p];
        for (int i = 0; i < p; i++) {
            res[i] = startLon + (i * o);
        }
        return res;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getAging() {
        return aging;
    }

    public int getLevel() {
        return level;
    }

    public float getLonSpacing() {
        return lonSpacing;
    }

    public float getLatSpacing() {
        return latSpacing;
    }

    public float getStartLon() {
        return startLon;
    }

    public float getEndLon() {
        return endLon;
    }

    public float getStartLat() {
        return startLat;
    }

    public float getEndLat() {
        return endLat;
    }

    public int getLatPoints() {
        return latPoints;
    }

    public int getLonPoints() {
        return lonPoints;
    }

    public float getContourSpacing() {
        return contourSpacing;
    }

    public float getContourStart() {
        return contourStart;
    }

    public float getContourEnd() {
        return contourEnd;
    }

    public float getSmoothingFactor() {
        return smoothingFactor;
    }

    public float getBoldLine() {
        return boldLine;
    }

    public float[][] getData() {
        return data;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setAging(int aging) {
        this.aging = aging;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setLonSpacing(float lonSpacing) {
        this.lonSpacing = lonSpacing;
    }

    public void setLatSpacing(float latSpacing) {
        this.latSpacing = latSpacing;
    }

    public void setStartLon(float startLon) {
        this.startLon = startLon;
    }

    public void setEndLon(float endLon) {
        this.endLon = endLon;
    }

    public void setStartLat(float startLat) {
        this.startLat = startLat;
    }

    public void setEndLat(float endLat) {
        this.endLat = endLat;
    }

    public void setLatPoints(int latPoints) {
        this.latPoints = latPoints;
    }

    public void setLonPoints(int lonPoints) {
        this.lonPoints = lonPoints;
    }

    public void setContourSpacing(float contourSpacing) {
        this.contourSpacing = contourSpacing;
    }

    public void setContourStart(float contourStart) {
        this.contourStart = contourStart;
    }

    public void setContourEnd(float contourEnd) {
        this.contourEnd = contourEnd;
    }

    public void setSmoothingFactor(float smoothingFactor) {
        this.smoothingFactor = smoothingFactor;
    }

    public void setBoldLine(float boldLine) {
        this.boldLine = boldLine;
    }

    public void setData(float[][] data) {
        this.data = data;
    }
}
