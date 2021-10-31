package com.example.record.excel.poi;

import com.example.record.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author KPQ
 * @since 1.0
 */
@Slf4j
public class ExcelHelper<T> {

    /**
     * 实体类信息
     */
    private final Class<T> clazz;
    /**
     * 字段信息集合
     */
    private final List<Field> fieldList;
    /**
     * excel 工作簿
     */
    private final Workbook wb;

    public ExcelHelper(Class<T> clazz) {
        this.clazz = clazz;
        this.wb = new SXSSFWorkbook(500);
        this.fieldList = getFieldList();
    }

    private List<Field> getFieldList() {
        Field[] fields = clazz.getDeclaredFields();
        List<Field> fieldList = new ArrayList<>(Arrays.asList(fields));
        Class<?> superClass = clazz.getSuperclass();
        if (Objects.nonNull(superClass)) {
            fieldList.addAll(Arrays.asList(superClass.getDeclaredFields()));
        }
        return fieldList.stream().filter(e -> Objects.nonNull(e.getAnnotation(Excel.class)))
                .sorted(Comparator.comparingInt(o -> o.getAnnotation(Excel.class).sort()))
                .collect(Collectors.toList());
    }

    /**
     * 单SheetExcel表格创建
     */
    private Workbook createWorkBook(List<T> data, String sheetName) {
        Sheet sheet = wb.createSheet();
        wb.setSheetName(0, sheetName);
        Row row = sheet.createRow(0);
        //创建sheet表头
        createHeader(sheet, row);
        fillExcelData(sheet, data);
        return wb;
    }

    /**
     * 创建多sheet页Excel工作簿
     *
     * @param map
     * @return
     */
    private Workbook createWorkBook(Map<String, List<T>> map) {
        int index = 0;
        for (Map.Entry entry : map.entrySet()) {
            String sheetName = (String) entry.getKey();
            //创建sheet页
            Sheet sheet = wb.createSheet();
            wb.setSheetName(index++, sheetName);
            Row row = sheet.createRow(0);
            //创建sheet表头
            createHeader(sheet, row);
            fillExcelData(sheet, (List<T>) entry.getValue());
        }
        return wb;
    }

    /**
     * 创建sheet页表头
     */
    private void createHeader(Sheet sheet, Row row) {
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            Excel attr = field.getAnnotation(Excel.class);
            //设置表头的列宽和高度
            sheet.setColumnWidth(i, (int) ((attr.width() + 0.72) * 256));
            row.setHeight((short) (attr.height() * 20));
            Cell cell = row.createCell(i);
            cell.setCellStyle(createCellStyle());
            // 写入列名
            cell.setCellValue(attr.name());
        }
    }

    private CellStyle createCellStyle() {
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        Font font = wb.createFont();
        // 粗体显示
        font.setBold(true);
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIGHT_YELLOW.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setWrapText(true);
        return cellStyle;
    }

    private void fillExcelData(Sheet sheet, List<T> data) {
        //样式设置
        CellStyle cs = wb.createCellStyle();
        cs.setAlignment(HorizontalAlignment.CENTER);
        cs.setVerticalAlignment(VerticalAlignment.CENTER);
        Row row;
        Cell cell;
        try {
            for (int i = 0; i < data.size(); i++) {
                //创建Excel行
                row = sheet.createRow(i + 1);
                T vo = data.get(i);
                for (int j = 0; j < fieldList.size(); j++) {
                    Field field = fieldList.get(j);
                    field.setAccessible(true);
                    Excel attr = field.getAnnotation(Excel.class);
                    row.setHeight((short) (attr.height() * 20));
                    cell = row.createCell(j);
                    cell.setCellStyle(cs);
                    Object o = field.get(vo);
                    // 如果数据存在就填入,不存在填入空格.
                    cell.setCellValue(Objects.isNull(o) ? attr.defaultValue() : o + attr.suffix());
                }
            }
        } catch (Exception e) {
            log.error("Excel导出失败{}", e.getMessage(), e);
            throw new BusinessException("Excel导出失败");
        }
    }

    public ResponseEntity export(String excelName, List<T> data, HttpServletResponse response) {
        try (OutputStream out = response.getOutputStream()) {
            setResponseParam(response, excelName);
            createWorkBook(data, excelName).write(out);
            return ResponseEntity.ok(excelName);
        } catch (Exception e) {
            log.error("导出Excel异常{}", e.getMessage());
            throw new BusinessException("导出Excel异常");
        }
    }

    /**
     * Excel导出：多Sheet页
     *
     * @param excelName
     * @param data
     * @param response
     * @return
     */
    public ResponseEntity exportMultiSheetExcel(String excelName, Map<String, List<T>> data, HttpServletResponse response) {
        try (OutputStream out = response.getOutputStream()) {
            setResponseParam(response, excelName);
            createWorkBook(data).write(out);
            return ResponseEntity.ok(excelName);
        } catch (Exception e) {
            log.error("导出Excel异常{}", e.getMessage());
            throw new BusinessException("导出Excel异常");
        }
    }

    private void setResponseParam(HttpServletResponse response, String excelName) throws UnsupportedEncodingException {
        response.setContentType("application/octet-stream;charset=ISO8859-1");
        response.addHeader("Pargam", "no-cache");
        response.setCharacterEncoding("utf-8");
        response.addHeader("Cache-Control", "no-cache");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(excelName + ".xlsx", "utf-8"));
        response.setHeader("content-filedownloadname", URLEncoder.encode(excelName + ".xlsx", "utf-8"));
    }

}
