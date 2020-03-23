package ir.comprehensive.utils;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

public class ReportUtils {
    public static enum PrintType {
        PDF, HTML, EXCEL
    }

    private ReportUtils() {
        // only static class
    }

    public static void print(String jrxmlPath, String destinationPath, Map<String, Object> parameterValue, Collection<?> beanCollection) {
        ReportUtils.print(jrxmlPath, destinationPath, parameterValue, beanCollection, PrintType.PDF);
    }

    public static void print(String jrxmlPath, String destinationPath, Map<String, Object> parameterValue, Collection<?> beanCollection, PrintType printType) {

        try (InputStream jrxmlPathStream = new FileInputStream(ReportUtils.class.getClassLoader().getResource(jrxmlPath).getFile())) {
            JasperDesign d = JRXmlLoader.load(jrxmlPathStream);
            JasperReport r = JasperCompileManager.compileReport(d);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(beanCollection);
            JasperPrint p = JasperFillManager.fillReport(r, parameterValue, dataSource);

            switch(printType)

            {
                case PDF:
                    destinationPath+= ".pdf";
                    JasperExportManager.exportReportToPdfFile(p, destinationPath);
                    break;
                case HTML:
                    destinationPath+= ".html";
                    JasperExportManager.exportReportToHtmlFile(p, destinationPath);
                    break;
                case EXCEL:
                    break;
                default:
                    break;

            }

        } catch (IOException | JRException e) {
            e.printStackTrace();
        }
    }


}
