package ir.comprehensive.reporting;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.io.*;
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
        if (destinationPath == null || beanCollection == null || beanCollection.isEmpty()) {
            return;
        }
        try (InputStream jrxmlPathStream =  Thread.currentThread().getContextClassLoader().getResourceAsStream(jrxmlPath)) {
            JasperDesign d = JRXmlLoader.load(jrxmlPathStream);
            JasperReport r = JasperCompileManager.compileReport(d);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(beanCollection);
            JasperPrint p = JasperFillManager.fillReport(r, parameterValue, dataSource);

            switch (printType) {
                case PDF:
                    destinationPath += ".pdf";
                    JasperExportManager.exportReportToPdfFile(p, destinationPath);
                    break;
                case HTML:
                    destinationPath += ".html";
                    JasperExportManager.exportReportToHtmlFile(p, destinationPath);
                    break;
                case EXCEL:
                    destinationPath += ".xls";
                    JRXlsxExporter exporter = new JRXlsxExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, p);
                    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream(destinationPath));
                    exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destinationPath);
                    exporter.exportReport();
                    break;
                default:
                    break;

            }

        } catch (IOException | JRException e) {
            e.printStackTrace();
        }
    }


}
