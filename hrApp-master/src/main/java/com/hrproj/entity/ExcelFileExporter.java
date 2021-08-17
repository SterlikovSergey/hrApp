package com.hrproj.entity;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

public class ExcelFileExporter{

    public static ByteArrayInputStream contactListToExcelFile(List<Payroll> payrolls, int month, int amountPeople, List<Integer> amountPeoplePost) {
        try(Workbook workbook = new XSSFWorkbook()){
            Sheet sheet = workbook.createSheet("Payroll");

            List<Row> rows = new ArrayList<>();
            for(int i = 0; i < 15; i++) {
                rows.add(sheet.createRow(i));
            }

            CellStyle aroundCellStyle = workbook.createCellStyle();
            aroundCellStyle.setBorderTop(BorderStyle.MEDIUM);
            aroundCellStyle.setBorderRight(BorderStyle.MEDIUM);
            aroundCellStyle.setBorderBottom(BorderStyle.MEDIUM);
            aroundCellStyle.setBorderLeft(BorderStyle.MEDIUM);
            aroundCellStyle.setTopBorderColor   (IndexedColors.BLACK.getIndex());
            aroundCellStyle.setRightBorderColor (IndexedColors.BLACK.getIndex());
            aroundCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            aroundCellStyle.setLeftBorderColor  (IndexedColors.BLACK.getIndex());

            CellStyle bottomCellStyle = workbook.createCellStyle();
            bottomCellStyle.setBorderBottom(BorderStyle.MEDIUM);
            bottomCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());

            // Creating header
            Cell cell = rows.get(1).createCell(10);
            cell.setCellValue("Код");
            cell.setCellStyle(aroundCellStyle);

            cell = rows.get(2).createCell(8);
            cell.setCellValue("Форма");

            cell = rows.get(2).createCell(9);
            cell.setCellValue("по  ОКУД");

            cell = rows.get(2).createCell(10);
            cell.setCellValue("301017");
            cell.setCellStyle(aroundCellStyle);

            cell = rows.get(3).createCell(9);
            cell.setCellValue("по  ОКРО");
            cell = rows.get(3).createCell(10);
            cell.setCellValue("00000000");
            cell.setCellStyle(aroundCellStyle);

            cell = rows.get(3).createCell(0);
            cell.setCellStyle(bottomCellStyle);
            cell = rows.get(3).createCell(1);
            cell.setCellStyle(bottomCellStyle);
            cell = rows.get(3).createCell(2);
            cell.setCellStyle(bottomCellStyle);
            cell = rows.get(3).createCell(3);
            cell.setCellStyle(bottomCellStyle);
            cell = rows.get(3).createCell(4);
            cell.setCellStyle(bottomCellStyle);
            cell = rows.get(3).createCell(5);
            cell.setCellStyle(bottomCellStyle);
            cell = rows.get(3).createCell(6);
            cell.setCellStyle(bottomCellStyle);
            cell = rows.get(3).createCell(7);
            cell.setCellStyle(bottomCellStyle);
            cell = rows.get(3).createCell(8);
            cell.setCellStyle(bottomCellStyle);

            cell = rows.get(4).createCell(4);
            cell.setCellValue("(наименование организации)");

            cell = rows.get(5).createCell(0);
            cell.setCellStyle(bottomCellStyle);
            cell = rows.get(5).createCell(1);
            cell.setCellStyle(bottomCellStyle);
            cell = rows.get(5).createCell(2);
            cell.setCellStyle(bottomCellStyle);
            cell = rows.get(5).createCell(3);
            cell.setCellStyle(bottomCellStyle);
            cell = rows.get(5).createCell(4);
            cell.setCellStyle(bottomCellStyle);
            cell = rows.get(5).createCell(5);
            cell.setCellStyle(bottomCellStyle);
            cell = rows.get(5).createCell(6);
            cell.setCellStyle(bottomCellStyle);
            cell = rows.get(5).createCell(7);
            cell.setCellStyle(bottomCellStyle);
            cell = rows.get(5).createCell(8);
            cell.setCellStyle(bottomCellStyle);
            cell = rows.get(5).createCell(10);
            cell.setCellStyle(bottomCellStyle);

            cell = rows.get(6).createCell(3);
            cell.setCellValue("Штатное расписание");

            cell = rows.get(6).createCell(5);
            cell.setCellValue("Номер документа");
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(6).createCell(6);
            cell.setCellValue("Дата составления");
            cell.setCellStyle(aroundCellStyle);


            cell = rows.get(7).createCell(5);
            cell.setCellValue("1");
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(7).createCell(6);
            cell.setCellValue(LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(7).createCell(8);
            cell.setCellValue("Утверждено");
            cell = rows.get(8).createCell(8);
            cell.setCellValue("Приказом организации от");
            cell = rows.get(8).createCell(11);
            cell.setCellStyle(bottomCellStyle);
            cell = rows.get(8).createCell(12);
            cell.setCellStyle(bottomCellStyle);

            cell = rows.get(9).createCell(1);
            cell.setCellValue("на период");

            cell = rows.get(9).createCell(2);
            cell.setCellValue("1 месяц с 1");

            cell = rows.get(9).createCell(3);
            cell.setCellValue(month);

            cell = rows.get(9).createCell(4);
            cell.setCellValue(LocalDate.now().getYear());

            cell = rows.get(9).createCell(5);
            cell.setCellValue("г.");

            cell = rows.get(9).createCell(8);
            cell.setCellValue("Штат в количестве");

            cell = rows.get(9).createCell(10);
            cell.setCellValue(amountPeople);

            cell = rows.get(9).createCell(11);
            cell.setCellValue("единиц");


            cell = rows.get(10).createCell(5);
            cell.setCellValue("Структурное подразделение");


            cell = rows.get(11).createCell(0);
            cell.setCellValue("Наименование");
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(11).createCell(1);
            cell.setCellValue("Код");
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(11).createCell(2);
            cell.setCellValue("Должность");
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(11).createCell(3);
            cell.setCellValue("Количество штатных единиц");
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(11).createCell(4);
            cell.setCellValue("Тарифная ставка(оклад) и пр., руб");
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(11).createCell(5);
            cell.setCellValue("Надбавки, руб.");
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(11).createCell(6);
            cell.setCellValue("Вычеты, руб.");
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(11).createCell(7);
            cell.setCellValue("Всего, руб. ((гр.5 + гр.6 - гр.7) х гр. 4)");
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(11).createCell(11);
            cell.setCellValue("Примечание");
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(11).createCell(8);
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(11).createCell(9);
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(11).createCell(10);
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(11).createCell(12);
            cell.setCellStyle(aroundCellStyle);

            cell = rows.get(12).createCell(0);
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(12).createCell(1);
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(12).createCell(2);
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(12).createCell(3);
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(12).createCell(4);
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(12).createCell(5);
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(12).createCell(6);
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(12).createCell(7);
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(12).createCell(8);
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(12).createCell(9);
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(12).createCell(10);
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(12).createCell(12);
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(12).createCell(8);
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(12).createCell(9);
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(12).createCell(10);
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(12).createCell(12);
            cell.setCellStyle(aroundCellStyle);

            cell = rows.get(13).createCell(0);
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(13).createCell(1);
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(13).createCell(2);
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(13).createCell(3);
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(13).createCell(4);
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(13).createCell(5);
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(13).createCell(6);
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(13).createCell(7);
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(13).createCell(8);
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(13).createCell(9);
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(13).createCell(10);
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(13).createCell(12);
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(13).createCell(8);
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(13).createCell(9);
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(13).createCell(10);
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(13).createCell(12);
            cell.setCellStyle(aroundCellStyle);

            cell = rows.get(14).createCell(0);
            cell.setCellValue("1");
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(14).createCell(1);
            cell.setCellValue("2");
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(14).createCell(2);
            cell.setCellValue("3");
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(14).createCell(3);
            cell.setCellValue("4");
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(14).createCell(4);
            cell.setCellValue("5");
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(14).createCell(5);
            cell.setCellValue("6");
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(14).createCell(6);
            cell.setCellValue("7");
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(14).createCell(7);
            cell.setCellValue("8");
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(14).createCell(11);
            cell.setCellValue("9");
            cell.setCellStyle(aroundCellStyle);

            cell = rows.get(14).createCell(8);
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(14).createCell(9);
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(14).createCell(10);
            cell.setCellStyle(aroundCellStyle);
            cell = rows.get(14).createCell(12);
            cell.setCellStyle(aroundCellStyle);


            sheet.addMergedRegion(new CellRangeAddress(11,13,0,0));
            sheet.addMergedRegion(new CellRangeAddress(11,13,1,1));
            sheet.addMergedRegion(new CellRangeAddress(11,13,2,2));
            sheet.addMergedRegion(new CellRangeAddress(11,13,3,3));
            sheet.addMergedRegion(new CellRangeAddress(11,13,4,4));
            sheet.addMergedRegion(new CellRangeAddress(11,13,5,5));
            sheet.addMergedRegion(new CellRangeAddress(11,13,6,6));
            sheet.addMergedRegion(new CellRangeAddress(11,13,7,10));
            sheet.addMergedRegion(new CellRangeAddress(11,13,11,12));

            sheet.addMergedRegion(new CellRangeAddress(14,14,7,10));
            sheet.addMergedRegion(new CellRangeAddress(14,14,11,12));

            // Creating data rows for each customer
            for(int i = 15; i < 15+payrolls.size(); i++) {
                Row dataRow = sheet.createRow(i);
                cell = dataRow.createCell(0);
                cell.setCellValue(payrolls.get(i-15).getEmployee().getPost().getDepartment());
                cell.setCellStyle(aroundCellStyle);
                cell = dataRow.createCell(1);
                cell.setCellValue(payrolls.get(i-15).getEmployee().getPost().getId());
                cell.setCellStyle(aroundCellStyle);
                cell = dataRow.createCell(2);
                cell.setCellValue(payrolls.get(i-15).getEmployee().getPost().getPost());
                cell.setCellStyle(aroundCellStyle);
                cell = dataRow.createCell(3);
                cell.setCellValue(amountPeoplePost.get(i-15));
                cell.setCellStyle(aroundCellStyle);
                cell = dataRow.createCell(4);
                cell.setCellValue(payrolls.get(i-15).getAccrued());
                cell.setCellStyle(aroundCellStyle);
                cell = dataRow.createCell(5);
                cell.setCellValue(payrolls.get(i-15).getAllowances());
                cell.setCellStyle(aroundCellStyle);
                cell = dataRow.createCell(6);
                cell.setCellValue(payrolls.get(i-15).getHold());
                cell.setCellStyle(aroundCellStyle);
                cell = dataRow.createCell(7);
                cell.setCellValue(payrolls.get(i-15).getTotal());
                cell.setCellStyle(aroundCellStyle);
                sheet.addMergedRegion(new CellRangeAddress(i,i,7,10));
                sheet.addMergedRegion(new CellRangeAddress(i,i,11,12));
                cell = dataRow.createCell(8);
                cell.setCellStyle(aroundCellStyle);
                cell = dataRow.createCell(9);
                cell.setCellStyle(aroundCellStyle);
                cell = dataRow.createCell(10);
                cell.setCellStyle(aroundCellStyle);
                cell = dataRow.createCell(11);
                cell.setCellStyle(aroundCellStyle);
                cell = dataRow.createCell(12);
                cell.setCellStyle(aroundCellStyle);
            }

            // Making size of column auto resize to fit with data
            for(int i =0; i < 15+payrolls.size(); i++)
                sheet.autoSizeColumn(i);


            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}