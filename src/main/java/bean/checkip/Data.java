/**
  * Copyright 2019 bejson.com 
  */
package bean.checkip;

/**
 * Auto-generated: 2019-06-06 23:42:7
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Data {

    private Outside_gfw outside_gfw;
    private Inside_gfw inside_gfw;
    private int reports;
    private int total_reports;
    private int processing_time;
    public void setOutside_gfw(Outside_gfw outside_gfw) {
         this.outside_gfw = outside_gfw;
     }
     public Outside_gfw getOutside_gfw() {
         return outside_gfw;
     }

    public void setInside_gfw(Inside_gfw inside_gfw) {
         this.inside_gfw = inside_gfw;
     }
     public Inside_gfw getInside_gfw() {
         return inside_gfw;
     }

    public void setReports(int reports) {
         this.reports = reports;
     }
     public int getReports() {
         return reports;
     }

    public void setTotal_reports(int total_reports) {
         this.total_reports = total_reports;
     }
     public int getTotal_reports() {
         return total_reports;
     }

    public void setProcessing_time(int processing_time) {
         this.processing_time = processing_time;
     }
     public int getProcessing_time() {
         return processing_time;
     }

}